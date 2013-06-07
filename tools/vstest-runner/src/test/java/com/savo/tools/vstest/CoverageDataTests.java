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

import junit.framework.Assert;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: ngamroth
 * Date: 6/6/13
 * Time: 4:01 PM
 * To change this template use File | Settings | File Templates.
 */
public class CoverageDataTests {
    @Test
    public void knownTestDataGivesExpectedData() {
        String file = "C:\\Workspaces\\Savo-Lifecycle\\Development\\TestResults\\a6567e42-2508-46e8-b527-57d741d425d2\\ngamroth_CORWS10348 2013-06-06 15_14_38.coverage";
        CoverageFileParser parser = new CoverageFileParser();
        CoverageData result = parser.parseCoverage(file);
        Assert.assertEquals(898, result.getLines());

    }
}
