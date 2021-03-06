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
package com.savo.sonar.plugins.csharp.vstest;

import com.savo.sonar.plugins.csharp.vstest.ui.IntegrationTestCoverageWidget;
import org.sonar.api.Extension;
import org.sonar.api.SonarPlugin;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ngamroth
 * Date: 5/30/13
 * Time: 9:20 AM
 * To change this template use File | Settings | File Templates.
 */
public class VsTestPlugin extends SonarPlugin {

    public List<Class<? extends Extension>> getExtensions() {
        List<Class<? extends Extension>> extensions = new ArrayList<Class<? extends Extension>>();

        extensions.add(IntegrationTestCoverageWidget.class);
        extensions.add(IntegrationTestMetrics.class);
        extensions.add(TestSensor.class);
        extensions.add(IntegrationTestSensor.class);

        return extensions;
    }
}
