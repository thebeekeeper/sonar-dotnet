/*
 * .NET Tools :: SavoTester Runner
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
package com.savo.tools.savotester;

import org.sonar.api.utils.command.Command;
import org.sonar.api.utils.command.CommandExecutor;

/**
 * User: ngamroth
 * Date: 7/25/13
 * Time: 4:06 PM
 */
public class SavoTesterRunner {
    public void setSettingsFile(String file) {
        this.settingsFile = file;
    }

    public void setTestAssembly(String testAssembly) {
        this.testAssembly = testAssembly;
    }

    private static String testerExe ="C:\\Workspaces\\Savo-QA\\Development\\Savo.Tester\\Savo.Tester.Console\\bin\\Debug\\Savo.Tester.Console.exe";
    public IntegrationTestResults Run() {
        Command c = Command.create(testerExe);
        c.addArgument(settingsFile);
        c.addArgument(testAssembly);
        StdOutStreamConsumer stdConsumer = new StdOutStreamConsumer();
        StdOutStreamConsumer stdErrConsumer = new StdOutStreamConsumer();
        CommandExecutor.create().execute(c, stdConsumer, stdErrConsumer, 9999999);
        IntegrationTestResults results = stdConsumer.getResults();
        return results;
    }

    private String settingsFile;
    private String testAssembly;
}
