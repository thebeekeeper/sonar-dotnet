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

import org.sonar.api.utils.command.StreamConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * User: ngamroth
 * Date: 7/25/13
 * Time: 4:19 PM
 */
public class StdOutStreamConsumer implements StreamConsumer {
    public StdOutStreamConsumer() {
        results = new IntegrationTestResults();
    }
    public void consumeLine(String s) {
        LOG.info("consuming line: " + s);
        if(s.startsWith("-Overall Coverage")) {
            String coverageString = s.substring(s.indexOf(":")+1).trim();
            LOG.info("coverage string: " + coverageString);
            double coverage = Double.parseDouble(coverageString) * 100.0;
            results.setCoverage(coverage);
        } else if(s.startsWith("-Category")) {
            results.setCategory(s.substring(s.indexOf(":")+1));
        } else if(s.startsWith("-Tests")) {
            String testsString = s.substring(s.indexOf(":")+1).trim();
            results.setTests(Integer.parseInt(testsString));
        } else if(s.startsWith("-Passed")) {
            String passedString = s.substring(s.indexOf(":")+1).trim();
            results.setPassed(Integer.parseInt(passedString));
        }
    }

    public IntegrationTestResults getResults() {
        return  results;
    }

    private IntegrationTestResults results;
    private static final Logger LOG = LoggerFactory.getLogger(StdOutStreamConsumer.class);
}
