/*
 * Sonar .NET Plugin :: Gallio
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
package org.sonar.plugins.csharp.gallio;

import org.sonar.dotnet.tools.gallio.GallioRunnerConstants;

/**
 * Constants of the Gallio plugin.
 */
public final class GallioConstants {

  private GallioConstants() {
  }

  public static final String BARRIER_GALLIO_EXECUTED = "Gallio executed";

  public static final String GALLIO_REPORT_XML = "gallio-report.xml";
  public static final String GALLIO_COVERAGE_REPORT_XML = "coverage-report.xml";

  public static final String RUNNER_TYPE_KEY = "sonar.gallio.runner";

  public static final String[] SUPPORTED_LANGUAGES = new String[] {"cs", "vbnet"};

  // ----------- Plugin Configuration Properties ----------- //
  public static final String INSTALL_FOLDER_KEY = "sonar.gallio.installDirectory";
  public static final String INSTALL_FOLDER_DEFVALUE = "C:/Program Files/Gallio";

  public static final String FILTER_KEY = "sonar.gallio.filter";
  public static final String FILTER_DEFVALUE = "";

  public static final String PART_COVER_INSTALL_KEY = "sonar.partcover.installDirectory";
  public static final String PART_COVER_INSTALL_DEFVALUE = "C:/Program Files/PartCover/PartCover .NET 4.0";

  public static final String COVERAGE_TOOL_KEY = "sonar.gallio.coverage.tool";
  public static final String COVERAGE_TOOL_DEFVALUE = GallioRunnerConstants.COVERAGE_TOOL_PARTCOVER_KEY;

  public static final String COVERAGE_EXCLUDES_KEY = "sonar.gallio.coverage.excludes";
  public static final String COVERAGE_EXCLUDES_DEFVALUE = null;

  public static final String TIMEOUT_MINUTES_KEY = "sonar.gallio.timeoutMinutes";
  public static final int TIMEOUT_MINUTES_DEFVALUE = 30;

  public static final String MODE = "sonar.gallio.mode";

  public static final String SAFE_MODE_KEY = "sonar.gallio.safe.mode";
  public static final boolean SAFE_MODE_DEFVALUE = false;

  public static final String REPORTS_PATH_KEY = "sonar.gallio.reports.path";
  public static final String REPORTS_COVERAGE_PATH_KEY = "sonar.gallio.coverage.reports.path";

  public static final String OPEN_COVER_INSTALL_KEY = "sonar.opencover.installDirectory";

  public static final String OPEN_COVER_INSTALL_DEFVALUE = "C:/Program Files/OpenCover/";

  public static final String DOT_COVER_INSTALL_KEY = "sonar.dotcover.installDirectory";

  public static final String DOT_COVER_INSTALL_DEFVALUE = "C:/Program Files/JetBrains/dotCover/v1.2/Bin";

  //
  // Integration tests constants
  //

  public static final String IT_GALLIO_REPORT_XML = "it-gallio-report.xml";
  public static final String IT_GALLIO_COVERAGE_REPORT_XML = "it-coverage-report.xml";

  public static final String IT_FILTER_KEY = "sonar.gallio.it.filter";
  public static final String IT_FILTER_DEFVALUE = "";

  public static final String IT_MODE_KEY = "sonar.gallio.it.mode";

  public static final String IT_MODE_ACTIVE = "active";

  public static final String IT_REPORTS_PATH_KEY = "sonar.gallio.it.reports.path";
  public static final String IT_REPORTS_COVERAGE_PATH_KEY = "sonar.gallio.it.coverage.reports.path";

  public static final String IT_TEST_ASSEMBLIES_KEY = "sonar.dotnet.it.assemblies";

  //
  // Utility method
  //

  public static boolean isLanguageSupported(String languageKey) {
    for (String key : SUPPORTED_LANGUAGES) {
      if (key.equals(languageKey)) {
        return true;
      }
    }
    return false;
  }

}
