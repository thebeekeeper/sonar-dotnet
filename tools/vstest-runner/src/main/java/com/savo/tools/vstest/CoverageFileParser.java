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

import org.sonar.api.utils.command.Command;
import org.sonar.api.utils.command.CommandExecutor;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: ngamroth
 * Date: 6/6/13
 * Time: 3:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class CoverageFileParser {
    public CoverageData parseCoverage(String coverageFile) {
        // TODO: fix this
        Command command = Command.create("c:\\temp\\SimpleCoverage\\SimpleCoverage\\bin\\Debug\\SimpleCoverage.exe");
        command.addArgument(coverageFile);
        SimpleCoverageStreamReader streamConsumer = new SimpleCoverageStreamReader();
        // don't really care what this one does
        StdOutStreamConsumer stdErrStreamConsumer = new StdOutStreamConsumer();
        CommandExecutor.create().execute(command, streamConsumer, stdErrStreamConsumer, 60000);
        CoverageData results = new CoverageData(streamConsumer.getLines(), streamConsumer.getCovered(), streamConsumer.getNotCovered(), streamConsumer.getCoverage());
        return results;
    }
}
