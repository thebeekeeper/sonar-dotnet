/*
 * .NET Tools :: VsTest Runner
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
package com.savo.tools.vstest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonar.api.utils.command.CommandExecutor;

/*
    Runs tests using vstest.console
 */
public class VsTestRunner {
    private static final Logger LOG = LoggerFactory.getLogger(VsTestRunner.class);

    public TestResultFiles execute(VsTestArguments arguments) {
        LOG.info("Running tests with VsTest");
        VsTestCommandBuilder builder = VsTestCommandBuilder.create(arguments);
        StdOutStreamConsumer stdOutConsumer = new StdOutStreamConsumer();
        StdOutStreamConsumer stdErrConsumer = new StdOutStreamConsumer();
        CommandExecutor.create().execute(builder.toCommand(), stdOutConsumer, stdErrConsumer, 60000);
        TestResultFiles results = new TestResultFiles();
        results.setCoverageFile(stdOutConsumer.getAttachment());
        results.setResultsFile(stdOutConsumer.getResultsFile());
        return results;
    }
}
