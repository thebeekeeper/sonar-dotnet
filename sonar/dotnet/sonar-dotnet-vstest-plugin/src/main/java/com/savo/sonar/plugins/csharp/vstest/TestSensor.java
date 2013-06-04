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

import com.savo.tools.vstest.FileUtil;
import com.savo.tools.vstest.TestResultFiles;
import com.savo.tools.vstest.VsTestArguments;
import com.savo.tools.vstest.VsTestRunner;
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
        //args.setSettingsFile("coverage.runsettings");
        File[] testAssemblies = FileUtil.findFiles(project.getFileSystem().getBasedir().getAbsolutePath(), "dll", "UnitTest");
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
        LOG.info("resultsFile: " + resultsFile);
        int[] testResults = ResultsParser.parseTrx(new File(resultsFile));
        sensorContext.saveMeasure(CoreMetrics.TESTS, (double)testResults[0]);
        sensorContext.saveMeasure(CoreMetrics.TEST_FAILURES, (double)testResults[2]);
    }

    public boolean shouldExecuteOnProject(Project project) {
        LOG.info("Should execute tests on " + project.getName());
        return project.getName().endsWith(".UnitTest");
    }

    private static final Logger LOG = LoggerFactory.getLogger(VsTestRunner.class);
}
