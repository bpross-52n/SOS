/**
 * Copyright (C) 2012-2015 52°North Initiative for Geospatial Open Source
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
package org.n52.sos.ds.hibernate.entities;

import java.io.Serializable;

import org.n52.iceland.util.Constants;
import org.n52.sos.ds.hibernate.entities.HibernateRelations.HasCoordinate;
import org.n52.sos.ds.hibernate.entities.HibernateRelations.HasDeletedFlag;
import org.n52.sos.ds.hibernate.entities.HibernateRelations.HasDisabledFlag;
import org.n52.sos.ds.hibernate.entities.HibernateRelations.HasGeometry;
import org.n52.sos.ds.hibernate.entities.HibernateRelations.HasProcedureDescriptionFormat;

/**
 * @since 4.0.0
 *
 */
/**
 * @author <a href="mailto:c.hollmann@52north.org">Carsten Hollmann</a>
 * @since
 *
 */
public class Procedure extends SpatialEntity implements Serializable, HasDeletedFlag, HasProcedureDescriptionFormat,
        HasGeometry, HasCoordinate, HasDisabledFlag {

    private static final long serialVersionUID = -3115365895730874831L;

    public static final String ID = "procedureId";

    public static final String DESCRIPTION_URL = "descriptionUrl";
    
    public static final String ALIAS = "proc";
    
    public static final String ALIAS_DOT = ALIAS + Constants.DOT_STRING;

    private long procedureId;

    private ProcedureDescriptionFormat procedureDescriptionFormat;

    private boolean deleted;

    private String descriptionFile;

    private boolean disabled;

    private boolean reference;
    
    private Procedure typeOf;
    
    private boolean isType;
    
    private boolean isAggregation;
    
    public long getProcedureId() {
        return this.procedureId;
    }

    public void setProcedureId(long procedureId) {
        this.procedureId = procedureId;
    }

    @Override
    public ProcedureDescriptionFormat getProcedureDescriptionFormat() {
        return this.procedureDescriptionFormat;
    }

    @Override
    public void setProcedureDescriptionFormat(ProcedureDescriptionFormat procedureDescriptionFormat) {
        this.procedureDescriptionFormat = procedureDescriptionFormat;
    }

    @Override
    public boolean isDeleted() {
        return this.deleted;
    }

    @Override
    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public String getDescriptionFile() {
        return this.descriptionFile;
    }

    public void setDescriptionFile(String descriptionFile) {
        this.descriptionFile = descriptionFile;
    }

    @Override
    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    @Override
    public boolean getDisabled() {
        return disabled;
    }

    @Override
    public boolean isDisabled() {
        return getDisabled();
    }

    @Override
    public boolean getDeleted() {
        return deleted;
    }

    /**
     * @return the reference
     */
    public boolean isReference() {
        return reference;
    }

    /**
     * @param reference the reference to set
     */
    public void setReference(boolean reference) {
        this.reference = reference;
    }

    /**
     * @return the typeOf
     */
    public Procedure getTypeOf() {
        return typeOf;
    }

    /**
     * @param typeOf the typeOf to set
     */
    public void setTypeOf(Procedure typeOf) {
        this.typeOf = typeOf;
    }
    
    /**
     * @return <code>true</code>, if is not null
     */
    public boolean isSetTypeOf() {
        return getTypeOf() != null;
    }

    /**
     * @return the isType
     */
    public boolean isType() {
        return isType;
    }
    
    /**
     * @return the isType
     */
    public boolean getIsType() {
        return isType;
    }

    /**
     * @param isType the isType to set
     */
    public void setIsType(boolean isType) {
        this.isType = isType;
    }

    /**
     * @return the isAggregation
     */
    public boolean isAggregation() {
        return isAggregation;
    }
    
    /**
     * @return the isAggregation
     */
    public boolean getIsAggregation() {
        return isAggregation;
    }

    /**
     * @param isAggregation the isAggregation to set
     */
    public void setIsAggregation(boolean isAggregation) {
        this.isAggregation = isAggregation;
    }
}
