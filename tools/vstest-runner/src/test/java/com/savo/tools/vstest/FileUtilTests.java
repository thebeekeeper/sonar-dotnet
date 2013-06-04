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

import org.junit.Assert;
import org.junit.Test;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: ngamroth
 * Date: 5/30/13
 * Time: 3:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class FileUtilTests {
    @Test
    public void canFindUnitTestAssemblies() {
        //String path = "C:\\temp\\SonarSample";
        String path = "E:\\workspace\\Laurus\\Laurus.TaskBoss";
        File[] files = FileUtil.findFiles(path, "dll", "UnitTest");
        Assert.assertEquals(2, files.length);
        //Assert.assertTrue(files.length > 0);
    }

    @Test
    public void fileWithNoNameDoesNotExist() {
        File f = new File("");
        Assert.assertFalse(f.exists());

        /*
            java can't figure out if files with null names are valid so it just throws
            String s = null;
            File f2 = new File(s);
            Assert.assertFalse(f2.exists());
        */
    }
}
