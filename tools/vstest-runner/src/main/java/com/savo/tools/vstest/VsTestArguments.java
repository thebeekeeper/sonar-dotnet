package com.savo.tools.vstest;

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

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if(codeCoverage)
            sb.append("/EnableCodeCoverage ");
        if(inIsolation)
            sb.append("/InIsolation ");
        if(!logger.isEmpty())
            sb.append("/Logger:" + logger + " ");
        if(!settings.isEmpty())
            sb.append("/Settings:" + settings + " ");
        for(int i = 0 ; i < testAssemblies.length ; i++)
            sb.append(testAssemblies[i] + " ");

        return sb.toString();
    }

    private boolean codeCoverage;
    private boolean inIsolation;
    private String logger;
    private String[] testAssemblies;
    private String settings;
}
