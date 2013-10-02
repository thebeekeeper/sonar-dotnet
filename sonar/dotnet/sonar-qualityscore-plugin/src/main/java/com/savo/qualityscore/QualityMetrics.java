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
package com.savo.qualityscore;

import org.sonar.api.measures.CoreMetrics;
import org.sonar.api.measures.Metric;
import org.sonar.api.measures.Metrics;
import org.sonar.api.measures.SumChildValuesFormula;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ngamroth
 * Date: 6/11/13
 * Time: 2:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class QualityMetrics implements Metrics {
    public static final Metric QUALITY_SCORE = new Metric.Builder("quality_score", "Quality Score", Metric.ValueType.FLOAT)
            .setDescription("Overall project quality").setDirection(Metric.DIRECTION_BETTER).setQualitative(true)
            .setDomain(CoreMetrics.DOMAIN_TESTS).setFormula(new SumChildValuesFormula(true)).create();

    public List<Metric> getMetrics() {
        ArrayList<Metric> metrics = new ArrayList<Metric>();
        metrics.add(QUALITY_SCORE);
        return metrics;
    }
}
