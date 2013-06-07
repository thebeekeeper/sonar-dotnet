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

import org.sonar.api.utils.command.StreamConsumer;

/**
 * Created with IntelliJ IDEA.
 * User: ngamroth
 * Date: 6/6/13
 * Time: 3:46 PM
 * To change this template use File | Settings | File Templates.
 */
public class SimpleCoverageStreamReader implements StreamConsumer {
    public void consumeLine(String s) {
        if(s.startsWith("Lines")) {
           lines = Integer.parseInt(s.substring(s.indexOf(' ') + 1));
        }
        if(s.startsWith("Covered")) {
            covered = Integer.parseInt(s.substring(s.indexOf(' ') + 1));
        }
        if(s.startsWith("NotCovered")) {
            notCovered = Integer.parseInt(s.substring(s.indexOf(' ') + 1));
        }
        if(s.startsWith("Coverage")) {
            coverage = Integer.parseInt(s.substring(s.indexOf(' ') + 1));
        }
    }

    public int getLines() {
        return lines;
    }

    public int getCovered() {
        return covered;
    }

    public int getNotCovered() {
        return notCovered;
    }

    public int getCoverage() {
        return coverage;
    }

    private int lines = 0;
    private int covered = 0;
    private int notCovered = 0;
    private int coverage = 0;
}
