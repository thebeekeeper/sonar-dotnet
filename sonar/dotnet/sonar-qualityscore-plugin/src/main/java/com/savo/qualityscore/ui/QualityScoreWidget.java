/*
 * Savo Quality Score Plugin
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
package com.savo.qualityscore.ui;

import org.sonar.api.web.*;

/**
 * Created with IntelliJ IDEA.
 * User: ngamroth
 * Date: 6/11/13
 * Time: 1:33 PM
 * To change this template use File | Settings | File Templates.
 */
@UserRole(UserRole.USER)
@Description("Displays an overall quality score for a project")
@WidgetCategory({"Savo", "C#"})
public class QualityScoreWidget extends AbstractRubyTemplate implements RubyRailsWidget {
    public String getId() {
        return "qualityscore";
    }

    public String getTitle() {
        return "Quality Score";
    }

    @Override
    protected String getTemplatePath() {
        //return "/com/savo/qualityscore/ui/qualityscore.html.erb";
        return "C:\\Users\\ngamroth\\Documents\\GitHub\\sonar-dotnet\\sonar\\dotnet\\sonar-qualityscore-plugin\\src\\main\\resources\\com\\savo\\qualityscore\\ui\\qualityscore.html.erb";
    }
}
