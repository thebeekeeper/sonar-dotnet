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

import org.sonar.api.measures.CoreMetrics;
import org.sonar.api.measures.Metric;
import org.sonar.api.measures.Metrics;
import org.sonar.api.measures.SumChildValuesFormula;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ngamroth
 * Date: 6/28/13
 * Time: 9:58 AM
 * To change this template use File | Settings | File Templates.
 */
public class IntegrationTestMetrics implements Metrics {
    public static final Metric SERVICE_COVERAGE = new Metric.Builder("service_coverage", "Service Coverage", Metric.ValueType.FLOAT)
            .setDescription("Service Integration Coverage").setDirection(Metric.DIRECTION_BETTER).setQualitative(true)
            .setDomain(CoreMetrics.DOMAIN_TESTS).setFormula(new SumChildValuesFormula(true)).create();

    public static final Metric CONTROLLER_COVERAGE = new Metric.Builder("controller_coverage", "Controller Coverage", Metric.ValueType.FLOAT)
            .setDescription("Controller Integration Coverage").setDirection(Metric.DIRECTION_BETTER).setQualitative(true)
            .setDomain(CoreMetrics.DOMAIN_TESTS).setFormula(new SumChildValuesFormula(true)).create();

    public static final Metric SERVICE_TEST_COUNT = new Metric.Builder("service_test_count", "Service Test Count", Metric.ValueType.INT)
            .setDescription("Service Test Count").setDirection(Metric.DIRECTION_BETTER).setQualitative(true)
            .setDomain(CoreMetrics.DOMAIN_INTEGRATION_TESTS).setFormula(new SumChildValuesFormula(true)).create();

    public static final Metric SERVICE_TEST_FAILURES = new Metric.Builder("service_test_failures", "Service Test Failures", Metric.ValueType.INT)
            .setDescription("Service Test Failure Count").setDirection(Metric.DIRECTION_WORST).setQualitative(true)
            .setDomain(CoreMetrics.DOMAIN_INTEGRATION_TESTS).setFormula(new SumChildValuesFormula(true)).create();

    public static final Metric CONTROLLER_TEST_COUNT = new Metric.Builder("controller_test_count", "Controller Test Count", Metric.ValueType.INT)
            .setDescription("Controller Test Count").setDirection(Metric.DIRECTION_BETTER).setQualitative(true)
            .setDomain(CoreMetrics.DOMAIN_INTEGRATION_TESTS).setFormula(new SumChildValuesFormula(true)).create();

    public static final Metric CONTROLLER_TEST_FAILURES = new Metric.Builder("controller_test_failures", "Controller Test Failures", Metric.ValueType.INT)
            .setDescription("Controller Test Failure Count").setDirection(Metric.DIRECTION_WORST).setQualitative(true)
            .setDomain(CoreMetrics.DOMAIN_INTEGRATION_TESTS).setFormula(new SumChildValuesFormula(true)).create();

    public List<Metric> getMetrics() {
        ArrayList<Metric> metrics = new ArrayList<Metric>();
        metrics.add(SERVICE_COVERAGE);
        metrics.add(CONTROLLER_COVERAGE);
        metrics.add(SERVICE_TEST_COUNT);
        metrics.add(SERVICE_TEST_FAILURES);
        metrics.add(CONTROLLER_TEST_COUNT);
        metrics.add(CONTROLLER_TEST_FAILURES);
        return metrics;
    }
}
