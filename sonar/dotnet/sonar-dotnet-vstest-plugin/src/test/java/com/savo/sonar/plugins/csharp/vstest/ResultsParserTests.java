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

import junit.framework.Assert;
import org.junit.Test;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: thebeekeeper
 * Date: 6/4/13
 * Time: 3:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class ResultsParserTests {
    @Test
    public void canReadFile() {
        String resultsFile = "  E:\\workspace\\Laurus\\Laurus.TaskBoss\\TestResults\\thebeekeeper_APOLLO 2013-06-04 15_23_00.trx".trim();
        int[] results = ResultsParser.parseTrx(new File(resultsFile));
        Assert.assertEquals(3, results.length);
    }

    @Test
    public void canParseNumberWithSpace() {
        // wow cool java can't parse an int with a leading space cool
        String withSpace = "28";
        int twentyEight = Integer.parseInt(withSpace);
        Assert.assertEquals(28, twentyEight);
    }

    @Test
    public void canParseDouble() {
        String coverageString = "0.38166666667";
        double coverage = Double.parseDouble(coverageString) * 100.0;
        Assert.assertEquals(38.166666667, coverage);
    }
}
