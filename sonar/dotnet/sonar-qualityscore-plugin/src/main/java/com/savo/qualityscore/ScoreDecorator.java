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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonar.api.batch.Decorator;
import org.sonar.api.batch.DecoratorContext;
import org.sonar.api.measures.CoreMetrics;
import org.sonar.api.measures.Measure;
import org.sonar.api.resources.Project;
import org.sonar.api.resources.Resource;

import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: ngamroth
 * Date: 6/11/13
 * Time: 2:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class ScoreDecorator implements Decorator {
    public void decorate(Resource resource, DecoratorContext decoratorContext) {
        double coverage = 0.0;
        double complexity = 0.0;

        Measure measure = decoratorContext.getMeasure(CoreMetrics.COVERAGE);
        if(measure != null && measure.hasData())
        {
            LOG.debug("project has coverage data");
            coverage = measure.getValue();
        }
        else
        {
            Collection<Measure> childrenMeasures = decoratorContext.getChildrenMeasures(CoreMetrics.COVERAGE);
            for (Measure m : childrenMeasures) {
                LOG.debug("Child measure " + m.getValue());
                coverage += m.getValue();
            }
        }
        LOG.debug("coverage: " + coverage);
        Measure x = decoratorContext.getMeasure(CoreMetrics.COMPLEXITY);
        if(x != null && x.hasData())
        {
            LOG.debug("project has complexity metric");
            complexity = x.getValue();
        }
        else
        {
            Collection<Measure> childrenMeasures = decoratorContext.getChildrenMeasures(CoreMetrics.COMPLEXITY);
            for(Measure m : childrenMeasures) {
                LOG.debug("Child complexity: " + m.getValue());
                complexity += m.getValue();
            }
        }
        LOG.debug("Complexity " + complexity);
        double quality = (coverage + complexity) / 2;
        LOG.debug("Saving quality score as " + quality);
        decoratorContext.saveMeasure(QualityMetrics.QUALITY_SCORE, quality);
    }

    public boolean shouldExecuteOnProject(Project project) {
        LOG.debug("Should execute on " + project.getName() + "? " + project.isRoot());
        return project.isRoot();
    }

    private static final Logger LOG = LoggerFactory.getLogger(ScoreDecorator.class);
}
