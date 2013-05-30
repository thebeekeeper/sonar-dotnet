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

/**
 * Created with IntelliJ IDEA.
 * User: ngamroth
 * Date: 5/30/13
 * Time: 1:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestResultFiles {
    public void setResultsFile(String resultsFile) {
        this.resultsFile = resultsFile;
    }

    public String getResultsFile() {
        return this.resultsFile;
    }

    public void setCoverageFile(String coverageFile) {
        this.coverageFile = coverageFile;
    }

    public String getCoverageFile() {
        return this.coverageFile;
    }

    private String resultsFile;
    private String coverageFile;
}
