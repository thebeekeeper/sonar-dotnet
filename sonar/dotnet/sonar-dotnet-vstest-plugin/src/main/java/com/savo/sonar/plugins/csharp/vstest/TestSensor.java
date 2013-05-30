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

import com.savo.tools.vstest.VsTestArguments;
import com.savo.tools.vstest.VsTestRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonar.api.batch.Sensor;
import org.sonar.api.batch.SensorContext;
import org.sonar.api.resources.Project;

/**
 * Created with IntelliJ IDEA.
 * User: ngamroth
 * Date: 5/30/13
 * Time: 9:30 AM
 * To change this template use File | Settings | File Templates.
 */
public class TestSensor implements Sensor {

    public TestSensor(VsTestRunner runner) {
        testRunner = runner;
    }

    public void analyse(Project project, SensorContext sensorContext) {
        LOG.info("Building test execution for " + project.getName());
        VsTestArguments args = new VsTestArguments();
        args.setCodeCoverage(true);
        args.setInIsolation(true);
        args.setLogger("trx");
        args.setSettingsFile("coverage.runsettings");
        args.setTestAssemblies(new String[] { "*.UnitTest.dll" });
        testRunner.execute(args);
    }

    public boolean shouldExecuteOnProject(Project project) {
        LOG.info("Should execute tests on " + project.getName());
        return project.getName().endsWith(".UnitTest");
    }

    private VsTestRunner testRunner;
    private static final Logger LOG = LoggerFactory.getLogger(VsTestRunner.class);
}
