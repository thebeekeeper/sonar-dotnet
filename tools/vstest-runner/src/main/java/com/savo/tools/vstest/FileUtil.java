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

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created with IntelliJ IDEA.
 * User: ngamroth
 * Date: 5/30/13
 * Time: 3:07 PM
 * To change this template use File | Settings | File Templates.
 */
public class FileUtil {
    public static File[] findFiles(String path, String extension, String contains){
        File baseDir = new File(path);
        LOG.info("Looking for files in " + path);
        if(!baseDir.isDirectory())
        {
            return new File[0];
        }
        String[] extensions = new String[] { extension };
        Collection<File> files = FileUtils.listFiles(baseDir, extensions, true);
        List<File> tempList = new ArrayList<File>();
        for(File f : files)
        {
            LOG.info("Found file " + f.getName());
            if(f.getName().contains(contains))
            {
                LOG.info("Found match for " + contains);
                tempList.add(f);
            }
        }
        return tempList.toArray(new File[tempList.size()]);
    }

    private static final Logger LOG = LoggerFactory.getLogger(FileUtil.class);
}
