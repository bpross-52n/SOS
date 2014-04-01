/**
 * Copyright (C) 2012-2014 52°North Initiative for Geospatial Open Source
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
package org.n52.sos.encode;

import java.io.OutputStream;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

public abstract class XmlStreamWriter extends XmlWriter<XMLStreamWriter> {

    private XMLStreamWriter w;

    @Override
    protected void init(OutputStream out) throws XMLStreamException {
        init(out, "UTF-8");
    }

    @Override
    protected void init(OutputStream out, String encoding) throws XMLStreamException {
        this.w = getXmlOutputFactory().createXMLStreamWriter(out, encoding);
    }

    @Override
    protected XMLStreamWriter getXmlWriter() {
        return w;
    }

    @Override
    protected void finish() throws XMLStreamException {
        getXmlWriter().flush();
        getXmlWriter().close();
    }

    @Override
    protected void attr(QName name, String value) throws XMLStreamException {
        getXmlWriter().writeAttribute(name.getPrefix(), name.getNamespaceURI(), name.getLocalPart(), value);
    }

    @Override
    protected void attr(String name, String value) throws XMLStreamException {
        getXmlWriter().writeAttribute(name, value);
    }

    @Override
    protected void chars(String chars) throws XMLStreamException {
        getXmlWriter().writeCharacters(chars);
    }

    @Override
    protected void end(QName name) throws XMLStreamException {
        getXmlWriter().writeEndElement();
    }

    @Override
    protected void end() throws XMLStreamException {
        getXmlWriter().writeEndDocument();
    }

    @Override
    protected void namespace(String prefix, String namespace) throws XMLStreamException {
        getXmlWriter().writeNamespace(prefix, namespace);
    }

    @Override
    protected void start(QName name) throws XMLStreamException {
        getXmlWriter().writeStartElement(name.getPrefix(), name.getLocalPart(), name.getNamespaceURI());
    }

    @Override
    protected void start() throws XMLStreamException {
        getXmlWriter().writeStartDocument();
    }

    @Override
    protected void empty(QName name) throws XMLStreamException {
        getXmlWriter().writeEmptyElement(name.getPrefix(), name.getLocalPart(), name.getNamespaceURI());
    }

}