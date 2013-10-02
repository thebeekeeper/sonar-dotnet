/*
 * Sonar .NET Plugin :: VsTest
 * Copyright (C) 2010 Jose Chillan, Alexandre Victoor and SonarSource
 * dev@sonar.codehaus.org
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02
 */
package com.savo.sonar.plugins.csharp.vstest;

import com.savo.tools.vstest.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonar.api.batch.Sensor;
import org.sonar.api.batch.SensorContext;
import org.sonar.api.measures.CoreMetrics;
import org.sonar.api.resources.Project;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: ngamroth
 * Date: 5/30/13
 * Time: 9:30 AM
 * To change this template use File | Settings | File Templates.
 */
public class TestSensor implements Sensor {

    public TestSensor() {
    }

    public void analyse(Project project, SensorContext sensorContext) {
        LOG.info("Building test execution for " + project.getName());
        VsTestRunner testRunner = new VsTestRunner();
        VsTestArguments args = new VsTestArguments();
        args.setCodeCoverage(true);
        args.setInIsolation(true);
        args.setLogger("trx");
        String coverageSettings = project.getName() + ".runsettings";
        LOG.info("Using coverage settings: " + coverageSettings);
        args.setSettingsFile(coverageSettings);
        File[] testAssemblies = null;
        LOG.info("Looking for test assemblies in bin\\release");
        testAssemblies = FileUtil.findFiles(project.getFileSystem().getBasedir().getAbsolutePath() + "\\bin\\release", "dll", "UnitTest");
        if(testAssemblies.length == 0)
        {
            LOG.info("Found 0 test assemblies in bin\\release - looking in bin\\debug");
            testAssemblies = FileUtil.findFiles(project.getFileSystem().getBasedir().getAbsolutePath() + "\\bin\\debug", "dll", "UnitTest");
        }
        LOG.info("found " + testAssemblies.length + " test assemblies");
        String[] fileNames = new String[testAssemblies.length];
        int i = 0;
        for(File f : testAssemblies)
        {
            if(f != null)
            {
                LOG.info("Found test assembly: " + f.getAbsolutePath());
                fileNames[i] = f.getAbsolutePath();
                LOG.info("Got here");
                i++;
            }
        }
        args.setTestAssemblies(fileNames);
        TestResultFiles results = testRunner.execute(args);

        String resultsFile = results.getResultsFile();
        if(resultsFile != null)
        {
            if((new File(resultsFile)).exists())
            {
                LOG.info("resultsFile: " + resultsFile);
                int[] testResults = ResultsParser.parseTrx(new File(resultsFile));
                sensorContext.saveMeasure(CoreMetrics.TESTS, (double)testResults[0]);
                sensorContext.saveMeasure(CoreMetrics.TEST_FAILURES, (double)testResults[2]);
            }
            else
            {
                sensorContext.saveMeasure(CoreMetrics.TESTS, 0.0);
                sensorContext.saveMeasure(CoreMetrics.TEST_FAILURES, 0.0);
                sensorContext.saveMeasure(CoreMetrics.LINE_COVERAGE, 0.0);
                sensorContext.saveMeasure(CoreMetrics.COVERAGE, 0.0);
            }
        }

        String coverageFile = results.getCoverageFile();
        if(coverageFile != null) {
            if((new File(coverageFile).exists())) {
                LOG.info("Parsing coverage file " + coverageFile);
                CoverageFileParser parser = new CoverageFileParser();
                CoverageData data = parser.parseCoverage(coverageFile);
                LOG.info("Publishing coverage data: lines = " + data.getLines());
                sensorContext.saveMeasure(CoreMetrics.LINES, (double)data.getLines());
                sensorContext.saveMeasure(CoreMetrics.COVERAGE, (double)data.getCoverage());
                sensorContext.saveMeasure(CoreMetrics.LINE_COVERAGE, (double)data.getCoverage());
                sensorContext.saveMeasure(CoreMetrics.LINES_TO_COVER, (double)data.getLines());
                sensorContext.saveMeasure(CoreMetrics.UNCOVERED_LINES, (double)data.getNotCovered());
            }
        }
        else {
            sensorContext.saveMeasure(CoreMetrics.LINES, 0.0);
            sensorContext.saveMeasure(CoreMetrics.COVERAGE, 0.0);
            sensorContext.saveMeasure(CoreMetrics.LINE_COVERAGE, 0.0);
            sensorContext.saveMeasure(CoreMetrics.LINES_TO_COVER, 0.0);
            sensorContext.saveMeasure(CoreMetrics.UNCOVERED_LINES, 0.0);
        }
    }

    public boolean shouldExecuteOnProject(Project project) {
        LOG.info("Should execute tests on " + project.getName());
        return project.getName().endsWith(".UnitTest");
    }

    private static final Logger LOG = LoggerFactory.getLogger(VsTestRunner.class);
}
