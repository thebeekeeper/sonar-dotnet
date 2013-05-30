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

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonar.api.resources.Project;
import org.sonar.api.utils.command.Command;
import org.sonar.api.utils.command.CommandExecutor;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ngamroth
 * Date: 5/30/13
 * Time: 3:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class ResultsParser {
    public static int[] parseTrx(File trxFile) {
        int[] rval = new int[3];
        try {
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            LOG.info("Parsing TRX file: " + trxFile.getName());
            Document doc = docBuilder.parse(trxFile);

            // normalize text representation
            doc.getDocumentElement ().normalize ();
            System.out.println ("Root element of the doc is " +
                    doc.getDocumentElement().getNodeName());

            Node results = doc.getElementsByTagName("ResultSummary").item(0);
            LOG.info("results node: " + results.getNodeName());
            LOG.info("results node has " + results.getChildNodes().getLength() + " children");
            NodeList childNodes = results.getChildNodes();
            for(int i = 0 ; i < childNodes.getLength() ; i++) {
                Node c = childNodes.item(i);
                LOG.info("child name: " + c.getNodeName());
                if(c.getNodeName().equals("Counters")) {
                    NamedNodeMap attributes = c.getAttributes();
                    LOG.info("attr count: " + attributes.getLength());
                    int total = Integer.parseInt(attributes.getNamedItem("total").getNodeValue());
                    int passed = Integer.parseInt(attributes.getNamedItem("passed").getNodeValue());
                    int failed = Integer.parseInt(attributes.getNamedItem("failed").getNodeValue());
                    rval[0] = total;
                    rval[1] = passed;
                    rval[2] = failed;
                    break;
                }
            }
        }catch (SAXParseException err) {
            System.out.println ("** Parsing error" + ", line "
                    + err.getLineNumber () + ", uri " + err.getSystemId ());
            System.out.println(" " + err.getMessage ());

        }catch (SAXException e) {
            Exception x = e.getException ();
            ((x == null) ? e : x).printStackTrace ();

        }catch (Throwable t) {
            t.printStackTrace();
        }
        return rval;
    }

    private static final Logger LOG = LoggerFactory.getLogger(ResultsParser.class);
}
