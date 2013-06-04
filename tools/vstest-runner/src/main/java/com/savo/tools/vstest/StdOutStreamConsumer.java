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
import org.sonar.api.utils.command.StreamConsumer;

/**
 * Created with IntelliJ IDEA.
 * User: ngamroth
 * Date: 5/30/13
 * Time: 1:33 PM
 * To change this template use File | Settings | File Templates.
 */
public class StdOutStreamConsumer implements StreamConsumer {
    public void consumeLine(String s) {
        LOG.info("VsTest: " + s);
        if(s.startsWith("Results File"))
        {
            resultsFile = s.substring(s.indexOf(":") + 1).trim();
            LOG.info("Detected test results file: " + resultsFile);
        }
        if(s.endsWith(".coverage"))
        {
            attachmentFile = s.trim();
            LOG.info("Detected test coverage file: " + attachmentFile);
        }
    }

    public String getResultsFile() {
        return resultsFile;
    }

    public String getAttachment() {
        return attachmentFile;
    }

    private String resultsFile;
    private String attachmentFile;
    private static final Logger LOG = LoggerFactory.getLogger(StdOutStreamConsumer.class);
}
