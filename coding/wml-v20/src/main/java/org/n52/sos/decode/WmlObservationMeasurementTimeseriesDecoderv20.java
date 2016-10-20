/*
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
package org.n52.sos.decode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import net.opengis.gml.x32.TimePositionType;
import net.opengis.waterml.x20.MeasureTVPType;
import net.opengis.waterml.x20.MeasurementTimeseriesDocument;
import net.opengis.waterml.x20.MeasurementTimeseriesType;
import net.opengis.waterml.x20.MeasurementTimeseriesType.Point;

import org.joda.time.DateTime;
import org.n52.iceland.coding.decode.DecoderKey;
import org.n52.iceland.exception.ows.OwsExceptionReport;
import org.n52.iceland.exception.ows.concrete.UnsupportedDecoderInputException;
import org.n52.iceland.ogc.gml.time.TimeInstant;
import org.n52.iceland.ogc.gml.time.TimePosition;
import org.n52.sos.ogc.om.TimeValuePair;
import org.n52.sos.ogc.om.values.QuantityValue;
import org.n52.sos.ogc.wml.MeasurementTimeseries;
import org.n52.sos.ogc.wml.MeasurementTimeseriesPoint;
import org.n52.sos.ogc.wml.WaterMLConstants;
import org.n52.sos.util.CodingHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Joiner;

public class WmlObservationMeasurementTimeseriesDecoderv20 extends AbstractWmlDecoderv20 {

    private static final Logger LOGGER = LoggerFactory.getLogger(WmlObservationMeasurementTimeseriesDecoderv20.class);

    private static final Set<DecoderKey> DECODER_KEYS = CodingHelper.decoderKeysForElements(WaterMLConstants.NS_WML_20, MeasurementTimeseriesDocument.class);

    public WmlObservationMeasurementTimeseriesDecoderv20() {
        LOGGER.debug("Decoder for the following keys initialized successfully: {}!",
                Joiner.on(", ").join(DECODER_KEYS));
    }

    @Override
    public Set<DecoderKey> getDecoderKeyTypes() {
        return Collections.unmodifiableSet(DECODER_KEYS);
    }

    @Override
    public Object decode(Object object) throws OwsExceptionReport, UnsupportedDecoderInputException {
        if (object instanceof MeasurementTimeseriesDocument) {
            return parseMeasurementTimeseries(((MeasurementTimeseriesDocument) object).getMeasurementTimeseries());
        }
        return super.decode(object);
    }

    private Object parseMeasurementTimeseries(MeasurementTimeseriesType measurementTimeseriesType) throws OwsExceptionReport {
        MeasurementTimeseries measurementTimeseries = new MeasurementTimeseries();
        parsePoints(measurementTimeseriesType, measurementTimeseries);
        return measurementTimeseries;
    }

    private void parsePoints(
            MeasurementTimeseriesType measurementTimeseriesType,
            MeasurementTimeseries measurementTimeseries) {
        Point[] points = measurementTimeseriesType.getPointArray();

        List<MeasurementTimeseriesPoint> timeseriesPointList = new ArrayList<>(points.length);

        for (Point point : points) {

            MeasurementTimeseriesPoint measurementTimeseriesPoint = new MeasurementTimeseriesPoint();

            MeasureTVPType measurementTVP = point.getMeasurementTVP();

            TimePositionType timePositionType = measurementTVP.getTime();

            TimePosition timePosition = new TimePosition(DateTime.parse(timePositionType.getStringValue()));

            TimeInstant time = new TimeInstant(timePosition.getTime());

            QuantityValue value = new QuantityValue(measurementTVP.getValue().getDoubleValue());

            TimeValuePair measurementTimeValuePair = new TimeValuePair(time, value);

            measurementTimeseriesPoint.setMeasurementTimeValuePair(measurementTimeValuePair);

            timeseriesPointList.add(measurementTimeseriesPoint);

        }

        measurementTimeseries.setPoints(timeseriesPointList.toArray(new MeasurementTimeseriesPoint[timeseriesPointList.size()]));
    }

    @Override
    public Set<DecoderKey> getKeys() {
        return DECODER_KEYS;
    }

}