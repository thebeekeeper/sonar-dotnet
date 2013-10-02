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
package com.savo.sonar.plugins.csharp.vstest.ui;

import org.sonar.api.web.*;

/**
 * User: ngamroth
 * Date: 7/2/13
 * Time: 10:31 AM
 */
@UserRole(UserRole.USER)
@Description("Displays NPercept coverage data for a web application")
@WidgetCategory({"Savo", "C#"})
public class IntegrationTestCoverageWidget extends AbstractRubyTemplate implements RubyRailsWidget {
    public String getId() {
        return "integrationtestcoverage";
    }

    public String getTitle() {
        return "Integration Test Coverage";
    }

    @Override
    protected String getTemplatePath() {
        //return "/com/savo/qualityscore/ui/qualityscore.html.erb";
        return "/com/savo/sonar/plugins/csharp/vstest/ui/integrationtest.html.erb";
        //return "C:\\Users\\ngamroth\\Documents\\GitHub\\sonar-dotnet\\sonar\\dotnet\\sonar-dotnet-vstest-plugin\\src\\main\\resources\\com\\savo\\sonar\\plugins\\csharp\\vstest\\ui\\integrationtest.html.erb";
    }
}
