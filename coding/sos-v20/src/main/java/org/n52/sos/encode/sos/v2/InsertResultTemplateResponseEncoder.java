/**
 * Copyright (C) 2012-2016 52°North Initiative for Geospatial Open Source
 * Software GmbH
 *
 * This program is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 as published
 * by the Free Software Foundation.
 *
 * If the program is linked with libraries which are licensed under one of
 * the following licenses, the combination of the program with the linked
 * library is not considered a "derivative work" of the program:
 *
 *     - Apache License, version 2.0
 *     - Apache Software License, version 1.0
 *     - GNU Lesser General Public License, version 3
 *     - Mozilla Public License, versions 1.0, 1.1 and 2.0
 *     - Common Development and Distribution License (CDDL), version 1.0
 *
 * Therefore the distribution of the program linked with libraries licensed
 * under the aforementioned licenses, is permitted by the copyright holders
 * if the distribution is compliant with both the GNU General Public
 * License version 2 and the aforementioned licenses.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General
 * Public License for more details.
 */
package org.n52.sos.encode.sos.v2;

import java.util.Set;

import net.opengis.sos.x20.InsertResultTemplateResponseDocument;
import net.opengis.sos.x20.InsertResultTemplateResponseType;

import org.apache.xmlbeans.XmlObject;
import org.n52.sos.ogc.ows.OwsExceptionReport;
import org.n52.sos.ogc.sos.Sos2Constants;
import org.n52.sos.response.InsertResultTemplateResponse;
import org.n52.sos.w3c.SchemaLocation;

import com.google.common.collect.Sets;

/**
 * TODO JavaDoc
 * 
 * @author Christian Autermann <c.autermann@52north.org>
 * 
 * @since 4.0.0
 */
public class InsertResultTemplateResponseEncoder extends AbstractSosResponseEncoder<InsertResultTemplateResponse> {
    public InsertResultTemplateResponseEncoder() {
        super(Sos2Constants.Operations.InsertResultTemplate.name(), InsertResultTemplateResponse.class);
    }

    @Override
    protected XmlObject create(InsertResultTemplateResponse response) throws OwsExceptionReport {
        InsertResultTemplateResponseDocument doc =
                InsertResultTemplateResponseDocument.Factory.newInstance(getXmlOptions());
        InsertResultTemplateResponseType irtr = doc.addNewInsertResultTemplateResponse();
        irtr.setAcceptedTemplate(response.getAcceptedTemplate());
        return doc;
    }

    @Override
    protected Set<SchemaLocation> getConcreteSchemaLocations() {
        return Sets.newHashSet(Sos2Constants.SOS_INSERT_RESULT_TEMPLATE_SCHEMA_LOCATION);
    }
}
