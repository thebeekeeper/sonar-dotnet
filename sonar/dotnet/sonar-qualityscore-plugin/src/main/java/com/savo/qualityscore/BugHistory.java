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

import com.microsoft.tfs.core.TFSTeamProjectCollection;
import com.microsoft.tfs.core.clients.workitem.WorkItemClient;
import com.microsoft.tfs.core.clients.workitem.project.Project;
import com.microsoft.tfs.core.clients.workitem.query.WorkItemCollection;
import com.microsoft.tfs.core.httpclient.Credentials;
import com.microsoft.tfs.core.httpclient.DefaultNTCredentials;

import java.net.URI;

/**
 * User: ngamroth
 * Date: 6/28/13
 * Time: 3:14 PM
 */
public class BugHistory {
    // TODO: replace this with a wiql file for each project?
    private static String query = "SELECT [System.Id], [System.WorkItemType], [System.Title], [System.AssignedTo], [System.State] FROM WorkItems WHERE [System.TeamProject] = @project  AND  [System.WorkItemType] = 'Bug'  AND  [System.State] = 'Active'  AND  [System.AreaPath] UNDER 'SAVO\\\\RFP' ORDER BY [System.Id]";
    private static String tfs = "http://tfs.chicago.savogroup.net:8080/tfs/defaultcollection";
    private static String teamProject = "SAVO";

    public int getActiveBugCount() {
        TFSTeamProjectCollection tpc = null;
        try {
            tpc = new TFSTeamProjectCollection(new URI(tfs), new DefaultNTCredentials() {
        });
        } catch (Exception e) {
            // couldn't parse url
        }
        Project project = tpc.getWorkItemClient().getProjects().get(teamProject);
        WorkItemClient client = project.getWorkItemClient();

        WorkItemCollection workItems = client.query(query);
        return workItems.size();
    }
}
