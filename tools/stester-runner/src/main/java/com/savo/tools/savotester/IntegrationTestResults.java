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

/**
 * User: ngamroth
 * Date: 7/25/13
 * Time: 4:07 PM
 */
public class IntegrationTestResults {
    public double getCoverage() {
        return this.coverage;
    }

    public void setCoverage(double coverage) {
        this.coverage = coverage;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getTests() {
        return this.tests;
    }

    public void setTests(int tests) {
        this.tests = tests;
    }

    public int getPassed() {
        return this.passed;
    }

    public void setPassed(int passed) {
        this.passed = passed;
    }

    private double coverage;
    private String category;
    private int tests;
    private int passed;
}
