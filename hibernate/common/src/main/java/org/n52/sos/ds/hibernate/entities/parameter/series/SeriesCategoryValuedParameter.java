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
package org.n52.sos.ds.hibernate.entities.parameter.series;

import org.n52.sos.ds.hibernate.entities.HibernateRelations.HasUnit;
import org.n52.sos.ds.hibernate.entities.Unit;
import org.n52.sos.ds.hibernate.entities.parameter.ParameterVisitor;
import org.n52.sos.ds.hibernate.entities.parameter.VoidParameterVisitor;
import org.n52.sos.ogc.om.NamedValue;
import org.n52.sos.ogc.ows.OwsExceptionReport;

import com.google.common.base.Strings;

public class SeriesCategoryValuedParameter extends SeriesParameter<String> implements org.n52.sos.ds.hibernate.entities.parameter.CategoryValuedParameter, HasUnit {

    private static final long serialVersionUID = -4090016662565122282L;
    private String value;
    private Unit unit;

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean isSetValue() {
        return !Strings.isNullOrEmpty(getValue());
    }

    @Override
    public String getValueAsString() {
        return getValue();
    }

    @Override
    public Unit getUnit() {
        return unit;
    }

    @Override
    public void setUnit(final Unit unit) {
        this.unit = unit;
    }

    @Override
    public boolean isSetUnit() {
        return getUnit() != null && getUnit().isSetUnit();
    }
    
    @Override
    public void accept(VoidParameterVisitor visitor) throws OwsExceptionReport {
        visitor.visit(this);
    }

    @Override
    public <T> NamedValue<T> accept(ParameterVisitor<T> visitor) throws OwsExceptionReport {
        return visitor.visit(this);
    }

}
