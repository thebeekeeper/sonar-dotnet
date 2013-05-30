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
import org.sonar.api.utils.command.Command;

/**
 * Created with IntelliJ IDEA.
 * User: ngamroth
 * Date: 5/29/13
 * Time: 11:18 AM
 * To change this template use File | Settings | File Templates.
 */
public class VsTestCommandBuilder {
    private static final Logger LOG = LoggerFactory.getLogger(VsTestCommandBuilder.class);
    String vstestCommand = "C:\\Program Files (x86)\\Microsoft Visual Studio 11.0\\Common7\\IDE\\CommonExtensions\\Microsoft\\TestWindow\\vstest.console.exe";

    private VsTestCommandBuilder() {
    }

    public static VsTestCommandBuilder create(VsTestArguments arguments) {
        VsTestCommandBuilder builder = new VsTestCommandBuilder();
        builder.arguments = arguments;
        return builder;
    }

    public VsTestArguments getArguments() {
        return this.arguments;
    }

    public Command toCommand() {
        Command c = Command.create(vstestCommand);
        c.addArguments(this.arguments.toArguments());
        return c;
    }

    private VsTestArguments arguments;
}
