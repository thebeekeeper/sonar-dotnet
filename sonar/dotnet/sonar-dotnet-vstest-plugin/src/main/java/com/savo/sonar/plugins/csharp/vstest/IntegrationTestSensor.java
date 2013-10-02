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

//import com.savo.tools.savotester.IntegrationTestResults;
//import com.savo.tools.savotester.SavoTesterRunner;
import com.savo.tools.vstest.FileUtil;
import com.savo.tools.vstest.TestResultFiles;
import com.savo.tools.vstest.VsTestArguments;
import com.savo.tools.vstest.VsTestRunner;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonar.api.batch.Sensor;
import org.sonar.api.batch.SensorContext;
import org.sonar.api.measures.CoreMetrics;
import org.sonar.api.resources.Project;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ngamroth
 * Date: 6/12/13
 * Time: 9:27 AM
 * To change this template use File | Settings | File Templates.
 */
public class IntegrationTestSensor implements Sensor {
    public void analyse(Project project, SensorContext sensorContext) {
        /*File[] testAssemblies = FileUtil.findFiles(project.getFileSystem().getBasedir().getAbsolutePath() + "\\bin\\debug", "dll", "IntegrationTest");
        if(testAssemblies.length == 0)
        {
            LOG.error("Can't find any test assemblies");
            return;
        }
        File[] settingsFile = FileUtil.findFiles(project.getFileSystem().getBasedir().getAbsolutePath(), "settings", "test");*/
        // there may be multiple settings files - 1 for service, 1 for controller
        /*for(File s : settingsFile) {
            SavoTesterRunner runner = new SavoTesterRunner();
            runner.setSettingsFile(s.getAbsolutePath());
            runner.setTestAssembly(testAssemblies[0].getAbsolutePath());
            LOG.info("settings file " + s.getAbsolutePath());
            LOG.info("test assembly: " + testAssemblies[0].getAbsolutePath());
            IntegrationTestResults results = runner.Run();

            double coverage = results.getCoverage();
            double tests = (double)results.getTests();
            double failed = (double)(results.getTests() - results.getPassed());
            LOG.info("coverage: " + coverage);
            LOG.info("tests: " + tests);
            LOG.info("failed: " + failed);
            sensorContext.saveMeasure(IntegrationTestMetrics.SERVICE_COVERAGE, coverage);
            sensorContext.saveMeasure(IntegrationTestMetrics.SERVICE_TEST_COUNT, tests);
            sensorContext.saveMeasure(IntegrationTestMetrics.SERVICE_TEST_FAILURES, failed);
        }*/


        File[] files = FileUtil.findFiles(project.getFileSystem().getBasedir().getAbsolutePath(), "test", "integration");
        if(files.length > 0) {
            File f = files[0];
            List<String> content = new ArrayList<String>();
            try {
                content = FileUtils.readLines(f);
            } catch (IOException ioe) {
                LOG.error("Error reading file: " + ioe.getMessage());
            }
            double serviceCoverage = Double.parseDouble(content.get(0));
            double serviceTests = Double.parseDouble(content.get(1));
            double serviceFailures = Double.parseDouble(content.get(2));
            double controllerCoverage = Double.parseDouble(content.get(3));
            double controllerTests = Double.parseDouble(content.get(4));
            double controllerFailures = Double.parseDouble(content.get(5));

            sensorContext.saveMeasure(IntegrationTestMetrics.SERVICE_COVERAGE, serviceCoverage);
            sensorContext.saveMeasure(IntegrationTestMetrics.SERVICE_TEST_COUNT, serviceTests);
            sensorContext.saveMeasure(IntegrationTestMetrics.SERVICE_TEST_FAILURES, serviceFailures);
            sensorContext.saveMeasure(IntegrationTestMetrics.CONTROLLER_COVERAGE, controllerCoverage);
            sensorContext.saveMeasure(IntegrationTestMetrics.CONTROLLER_TEST_COUNT, controllerTests);
            sensorContext.saveMeasure(IntegrationTestMetrics.CONTROLLER_TEST_FAILURES, controllerFailures);
        }
    }

    public boolean shouldExecuteOnProject(Project project) {
        /*boolean shouldExecute = project.getName().endsWith(".IntegrationTest");
        LOG.info("Should execute on " + project.getName() + "? " + shouldExecute);
        return shouldExecute;*/
        if(project.isRoot()) {
            LOG.info("executing integration test sensor on project " + project.getName());
            return true;
        } else {
            LOG.info(project.getName() + " is not a root project - not doing integration tests");
            return false;
        }
    }

    private static final Logger LOG = LoggerFactory.getLogger(IntegrationTestSensor.class);
}
