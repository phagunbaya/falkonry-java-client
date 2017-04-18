package com.falkonry;

import com.falkonry.client.Falkonry;
import com.falkonry.helper.models.*;
import org.junit.*;

import java.util.*;

/*!
 * falkonry-java-client
 * Copyright(c) 2016 Falkonry Inc
 * MIT Licensed
 */

/**
 *
 * @author dev-falkonry-10
 */

public class TestAddFacts {

    Falkonry falkonry = null;
    String host = "https://localhost:8080";
    String token = "8g462njx92e1yc0fxzrbdxqtx90hsr1s";
    List<Datastream> datastreams = new ArrayList<Datastream>();
    List<Assessment> assessments = new ArrayList<Assessment>();

    /**
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        falkonry = new Falkonry(host, token);
    }

    //@Test

    /**
     *
     * @throws Exception
     */
    public void createDatastreamWithCsvFacts() throws Exception {

        Datastream ds = new Datastream();
        ds.setName("Test-DS-" + Math.random());

        TimeObject time = new TimeObject();
        time.setIdentifier("time");
        time.setFormat("iso_8601");
        time.setZone("GMT");

        Signal signal = new Signal();
        signal.setTagIdentifier("tag");
        signal.setValueIdentifier("value");
        signal.setDelimiter("_");
        signal.setIsSignalPrefix(false);

        Datasource dataSource = new Datasource();
        dataSource.setType("STANDALONE");

        Field field = new Field();
        field.setSiganl(signal);
        field.setTime(time);
//        field.setEntityIdentifier("unit");

        ds.setDatasource(dataSource);
        ds.setField(field);

        Datastream datastream = falkonry.createDatastream(ds);
        datastreams.add(datastream);

        List<Assessment> assessments = new ArrayList<Assessment>();
        AssessmentRequest assessmentRequest = new AssessmentRequest();
        assessmentRequest.setName("Health");
        assessmentRequest.setDatastream(datastream.getId());
        assessmentRequest.setAssessmentRate("PT1S");
        Assessment assessment = falkonry.createAssessment(assessmentRequest);
        assessments.add(assessment);

        Map<String, String> options = new HashMap<String, String>();
        String data = "{\"time\" : \"2016-03-01 01:01:01\", \"tag\" : \"signal1_entity1\", \"value\" : 3.4}";
//        String data = "time, tag, value\n2016-03-01 01:01:01, signal1_entity1, 3.4";
        options.put("timeIdentifier", "time");
        options.put("timeFormat", "iso_8601");
        options.put("fileFormat", "csv");
        falkonry.addInput(datastream.getId(), data, options);

        data = "time,end,entity,Health\n2011-03-31T00:00:00Z,2011-04-01T00:00:00Z,entity1,Normal\n2011-03-31T00:00:00Z,2011-04-01T00:00:00Z,entity1,Normal";
        InputStatus response = falkonry.addFacts(assessment.getId(), data, null);
//        falkonry.deleteAssessment(assessment.getId());
        falkonry.deleteDatastream(datastream.getId());
    }

    //@Test

    /**
     *
     * @throws Exception
     */
    public void createDatastreamWithWideCsvFacts() throws Exception {

        Datastream ds = new Datastream();
        ds.setName("Test-DS-" + Math.random());

        TimeObject time = new TimeObject();
        time.setIdentifier("time");
        time.setFormat("millis");
        time.setZone("GMT");

        Signal signal = new Signal();
        signal.setTagIdentifier("tag");
        signal.setValueIdentifier("value");
        signal.setDelimiter("_");
        signal.setIsSignalPrefix(false);

        Datasource dataSource = new Datasource();
        dataSource.setType("STANDALONE");

        Field field = new Field();
        field.setSiganl(signal);
        field.setTime(time);

        ds.setDatasource(dataSource);
        ds.setField(field);

        Datastream datastream = falkonry.createDatastream(ds);
        datastreams.add(datastream);

        Map<String, String> options = new HashMap<String, String>();
//        String data = "{\"time\" : \"2016-03-01 01:01:01\", \"tag\" : \"signal1_entity1\", \"value\" : 3.4}";
        String data = "time, tag, entity, signal1, signal2, signal3\n2016-03-01 01:01:01, signal1_entity1, entity1, 3.4, 4.8, 8.3";
        falkonry.addInput(datastream.getId(), data, options);

        AssessmentRequest assessmentRequest = new AssessmentRequest();
        String name = "Test-AS-" + Math.random();
        assessmentRequest.setName(name);
        assessmentRequest.setDatastream(datastream.getId());
        assessmentRequest.setAssessmentRate("PT1S");
        Assessment assessment = falkonry.createAssessment(assessmentRequest);
        assessments.add(assessment);

        data = "time,end,entity,Health\n2011-03-31T00:00:00Z,2011-04-01T00:00:00Z,entity1,Normal\n2011-03-31T00:00:00Z,2011-04-01T00:00:00Z,entity1,Normal";
        InputStatus response = falkonry.addFacts(assessment.getId(), data, null);
        falkonry.deleteAssessment(assessment.getId());
    }

    /**
     *
     * @throws Exception
     */
    @Test
    public void createDatastremWithJsonFacts() throws Exception {

        Datastream ds = new Datastream();
        ds.setName("Test-DS-" + Math.random());

        TimeObject time = new TimeObject();
        time.setIdentifier("time");
        time.setFormat("iso_8601");
        time.setZone("GMT");

        Signal signal = new Signal();
        signal.setTagIdentifier("tag");
        signal.setValueIdentifier("value");
        signal.setDelimiter("_");
        signal.setIsSignalPrefix(false);

        Datasource dataSource = new Datasource();
        dataSource.setType("STANDALONE");

        Field field = new Field();
        field.setSiganl(signal);
        field.setTime(time);

        ds.setDatasource(dataSource);
        ds.setField(field);

        Datastream datastream = falkonry.createDatastream(ds);
        datastreams.add(datastream);

        AssessmentRequest assessmentRequest = new AssessmentRequest();
        String name = "Test-AS-" + Math.random();
        assessmentRequest.setName(name);
        assessmentRequest.setDatastream(datastream.getId());
        assessmentRequest.setAssessmentRate("PT1S");
        Assessment assessment = falkonry.createAssessment(assessmentRequest);
        assessments.add(assessment);

        Map<String, String> options = new HashMap<String, String>();

        String data = "{\"time\" : \"2016-03-01 01:01:01\", \"tag\" : \"signal1_entity1\", \"value\" : 3.4}";
        falkonry.addInput(datastream.getId(), data, options);

        Interval interval = new Interval();
        interval.setDuration("PT1S");

        data = "{\"time\" : \"2011-03-26T12:00:00Z\", \"entity\" : \"entity1\", \"end\" : \"2012-06-01T00:00:00Z\", \"Health\" : \"Normal\"}";
        InputStatus response = falkonry.addFacts(assessment.getId(), data, null);
        Assert.assertEquals(assessment.getName(), assessmentRequest.getName());
        falkonry.deleteAssessment(assessment.getId());
    }

    //@Test

    /**
     *
     * @throws Exception
     */
    public void createPipelineWithWideJsonFacts() throws Exception {

        Datastream ds = new Datastream();
        ds.setName("Test-DS-" + Math.random());

        TimeObject time = new TimeObject();
        time.setIdentifier("time");
        time.setFormat("millis");
        time.setZone("GMT");

        Datasource dataSource = new Datasource();
        dataSource.setType("STANDALONE");

        Field field = new Field();
        field.setTime(time);
        field.setEntityIdentifier("entity");

        ds.setDatasource(dataSource);
        ds.setField(field);

        Datastream datastream = falkonry.createDatastream(ds);
        datastreams.add(datastream);

        AssessmentRequest assessmentRequest = new AssessmentRequest();
        String name = "Test-AS-" + Math.random();
        assessmentRequest.setName(name);
        assessmentRequest.setDatastream(datastream.getId());
        assessmentRequest.setAssessmentRate("PT1S");
        Assessment assessment = falkonry.createAssessment(assessmentRequest);
        assessments.add(assessment);

        Map<String, String> options = new HashMap<String, String>();

        String data = "{\"time\":1467729675422,\"entity\":\"entity1\",\"signal1\":41.11,\"signal2\":82.34,\"signal3\":74.63,\"signal4\":4.8,\"signal5\":72.01}";
        falkonry.addInput(datastream.getId(), data, options);

        Interval interval = new Interval();
        interval.setDuration("PT1S");

        data = "{\"time\" : \"2011-03-26T12:00:00Z\", \"entity\" : \"entity1\", \"end\" : \"2012-06-01T00:00:00Z\", \"Health\" : \"Normal\"}";
        InputStatus response = falkonry.addFacts(assessment.getId(), data, null);
        Assert.assertEquals(assessment.getName(), assessment.getName());
        falkonry.deleteAssessment(assessment.getId());
    }

    /**
     *
     * @throws Exception
     */
    @After
    public void cleanUp() throws Exception {
        Iterator<Datastream> itr = datastreams.iterator();
        while (itr.hasNext()) {
            Datastream ds = itr.next();
            falkonry.deleteDatastream(ds.getId());
        }
    }
}
