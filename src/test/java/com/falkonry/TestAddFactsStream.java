package com.falkonry;

import com.falkonry.client.Falkonry;
import com.falkonry.helper.models.*;
import org.apache.commons.io.FileUtils;
import org.junit.*;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.*;

/*!
 * falkonry-java-client
 * Copyright(c) 2016 Falkonry Inc
 * MIT Licensed
 */
public class TestAddFactsStream {

    Falkonry falkonry = null;
    String host = "https://localhost:8080";
    String token = "yf15jw8igeppzqba86essum3ycdeqi9u";
    List<Datastream> datastreams = new ArrayList<Datastream>();
    List<Assessment> assessments = new ArrayList<Assessment>();

    @Before
    public void setUp() throws Exception {
        falkonry = new Falkonry(host, token);
    }

    @Test
    public void createDatastreamWithCsvFactsStream() throws Exception {

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

        Field field = new Field();
        field.setSiganl(signal);
        field.setTime(time);
        ds.setField(field);
        Datasource dataSource = new Datasource();
        dataSource.setType("STANDALONE");
        ds.setDatasource(dataSource);
        
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
       
        String data = "time, tag, value\n2016-03-01 01:01:01, signal1_entity1, 3.4";
        falkonry.addInput(datastream.getId(), data, options);


        File file = new File("res/factsData.csv");
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(FileUtils.readFileToByteArray(file));
        String response = falkonry.addFactsStream(assessment.getId(), byteArrayInputStream, null);
        Assert.assertEquals(response, "{\"message\":\"Data submitted successfully\"}");
        falkonry.deleteAssessment(assessment.getId());
    }

    //@Test
    public void createDatastreamWithJsonFacts() throws Exception {
        
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

        Field field = new Field();
        field.setSiganl(signal);
        field.setTime(time);
        ds.setField(field);
        Datasource dataSource = new Datasource();
        dataSource.setType("STANDALONE");
        ds.setDatasource(dataSource);
        
        Datastream datastream = falkonry.createDatastream(ds);
        datastreams.add(datastream);

       
        List<Assessment> assessments = new ArrayList<Assessment>();
        AssessmentRequest assessmentRequest = new AssessmentRequest();
        assessmentRequest.setName("Health");
        assessmentRequest.setDatastream(ds.getId());
        assessmentRequest.setAssessmentRate("PT1S");
        Assessment assessment = falkonry.createAssessment(assessmentRequest);
        assessments.add(assessment);
        
        Map<String, String> options = new HashMap<String, String>();
        

        String data = "{\"time\" : \"2016-03-01 01:01:01\", \"tag\" : \"signal1_entity1\", \"value\" : 3.4}";
        falkonry.addInput(ds.getId(), data, options);

        

        File file = new File("res/factsData.json");
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(FileUtils.readFileToByteArray(file));
        String response = falkonry.addFactsStream(assessment.getId(), byteArrayInputStream, null);
        String response_id = response.split("(:)|(,)")[1];
        Assert.assertNotEquals(response_id, null);
        Assert.assertEquals(assessment.getName(), assessmentRequest.getName());
        falkonry.deleteAssessment(assessment.getId());
    }

    @After
    public void cleanUp() throws Exception {
        Iterator<Datastream> itr = datastreams.iterator();
        while (itr.hasNext()) {
            Datastream ds = itr.next();
            falkonry.deleteDatastream(ds.getId());
        }
    }
}
