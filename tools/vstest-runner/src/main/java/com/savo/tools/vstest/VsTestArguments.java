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

import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ngamroth
 * Date: 5/29/13
 * Time: 3:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class VsTestArguments {
    public void setCodeCoverage(boolean codeCoverage){
        this.codeCoverage = codeCoverage;
    }

    public boolean getCodeCoverage(){
        return this.codeCoverage;
    }

    public void setInIsolation(boolean inIsolation) {
        this.inIsolation = inIsolation;
    }

    public boolean getInIsolation() {
        return  this.inIsolation;
    }

    public void setLogger(String logger) {
        this.logger = logger;
    }

    public String getLogger(){
        return this.logger;
    }

    public void setTestAssemblies(String[] assemblies) {
        this.testAssemblies = assemblies;
    }

    public String[] getTestAssemblies() {
        return  this.testAssemblies;
    }

    public void setSettingsFile(String settingsFile) {
        this.settings = settingsFile;
    }

    public String getSettingsFile() {
        return this.settings;
    }

    public List<String> toArguments() {
        List<String> args = new LinkedList<String>();
        if(codeCoverage)
            args.add("/EnableCodeCoverage");
        if(inIsolation)
            args.add("/InIsolation");
        if(!logger.isEmpty())
            args.add("/Logger:" + logger);
        if(!settings.isEmpty())
            args.add("/Settings:" + settings);
        for(int i = 0 ; i < testAssemblies.length ; i++)
            args.add(testAssemblies[i]);
        return args;
    }

    private boolean codeCoverage;
    private boolean inIsolation;
    private String logger;
    private String[] testAssemblies;
    private String settings;
}
