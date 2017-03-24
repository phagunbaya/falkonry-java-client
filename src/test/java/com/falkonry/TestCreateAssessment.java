package com.falkonry;

import com.falkonry.client.Falkonry;
import com.falkonry.helper.models.Assessment;
import com.falkonry.helper.models.AssessmentRequest;
import com.falkonry.helper.models.Datasource;
import com.falkonry.helper.models.Datastream;
import com.falkonry.helper.models.Field;
import com.falkonry.helper.models.TimeObject;
import com.falkonry.helper.models.Signal;
import org.junit.*;

import java.util.*;

/*!
 * falkonry-java-client
 * Copyright(c) 2016 Falkonry Inc
 * MIT Licensed
 */
public class TestCreateAssessment {

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
    public void createAssessment() throws Exception {
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

        AssessmentRequest assessmentRequest = new AssessmentRequest();
        String name = "Test-AS-" + Math.random();
        assessmentRequest.setName(name);
        assessmentRequest.setDatastream(datastream.getId());
        assessmentRequest.setAssessmentRate("PT1S");
        Assessment assessment = falkonry.createAssessment(assessmentRequest);
        assessments.add(assessment);

        Assert.assertEquals(assessment.getName(), assessmentRequest.getName());
        Assert.assertEquals(assessment.getRate(), assessmentRequest.getAssessmentRate());
        falkonry.deleteAssessment(assessment.getId());

    }
    
    @Test
    public void deleteAssessment() throws Exception {
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

        AssessmentRequest assessmentRequest = new AssessmentRequest();
        String name = "Test-AS-" + Math.random();
        assessmentRequest.setName(name);
        assessmentRequest.setDatastream(datastream.getId());
        assessmentRequest.setAssessmentRate("PT1S");
        Assessment assessment = falkonry.createAssessment(assessmentRequest);
        assessments.add(assessment);

        Assert.assertEquals(assessment.getName(), assessmentRequest.getName());
        Assert.assertEquals(assessment.getRate(), assessmentRequest.getAssessmentRate());
        falkonry.deleteAssessment(assessment.getId());
        
        //Assessment deletedAssessment = falkonry.getAssessment(assessment.getId());
        //Assert.assertEquals(assessment.getName(), assessmentRequest.getName());

    }

    @Test
    public void getAssessmentList() throws Exception {

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

        AssessmentRequest assessmentRequest = new AssessmentRequest();
        String name = "Test-AS-" + Math.random();
        assessmentRequest.setName(name);
        assessmentRequest.setDatastream(datastream.getId());
        assessmentRequest.setAssessmentRate("PT1S");
        Assessment assessment = falkonry.createAssessment(assessmentRequest);
        assessments.add(assessment);

        Assert.assertEquals(assessment.getName(), assessmentRequest.getName());
        Assert.assertEquals(assessment.getRate(), assessmentRequest.getAssessmentRate());

        List<Assessment> assessmentList = falkonry.getAssessments();
        Assert.assertEquals(true, assessmentList.size() > 2);

        falkonry.deleteAssessment(assessment.getId());
    }

    @Test
    public void getAssessmentById() throws Exception {

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

        AssessmentRequest assessmentRequest = new AssessmentRequest();
        String name = "Test-AS-" + Math.random();
        assessmentRequest.setName(name);
        assessmentRequest.setDatastream(datastream.getId());
        assessmentRequest.setAssessmentRate("PT1S");
        Assessment assessment = falkonry.createAssessment(assessmentRequest);
        assessments.add(assessment);

        Assert.assertEquals(assessment.getName(), assessmentRequest.getName());
        Assert.assertEquals(assessment.getRate(), assessmentRequest.getAssessmentRate());

        Assessment assessment1 = falkonry.getAssessment(assessment.getId());
        Assert.assertEquals(assessment1.getName(), assessment.getName());
        Assert.assertEquals(assessment1.getRate(), assessment.getRate());
        Assert.assertEquals(assessment1.getDatastream(), assessment.getDatastream());

        falkonry.deleteAssessment(assessment.getId());
    }

    //@Test
    public void updateAssessment() throws Exception {

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

        AssessmentRequest assessmentRequest = new AssessmentRequest();
        String name = "Test-AS-" + Math.random();
        assessmentRequest.setName(name);
        assessmentRequest.setDatastream(datastream.getId());
        assessmentRequest.setAssessmentRate("PT1S");
        Assessment assessment = falkonry.createAssessment(assessmentRequest);
        assessments.add(assessment);

        Assert.assertEquals(assessment.getName(), assessmentRequest.getName());
        Assert.assertEquals(assessment.getRate(), assessmentRequest.getAssessmentRate());

        Assessment assessment1 = falkonry.getAssessment(assessment.getId());
        Assert.assertEquals(assessment1.getName(), assessment.getName());
        Assert.assertEquals(assessment1.getRate(), assessment.getRate());
        Assert.assertEquals(assessment1.getDatastream(), assessment.getDatastream());
        
        name = "Test-AS-" + Math.random();
        assessment1.setName(name);
        Assessment assessmentUpd = falkonry.updateAssessment(assessment1);
        Assert.assertEquals(assessmentUpd.getName(), assessment1.getName());
        Assert.assertEquals(assessmentUpd.getRate(), assessment1.getRate());
        Assert.assertEquals(assessmentUpd.getDatastream(), assessment1.getDatastream());
        
        

        falkonry.deleteAssessment(assessment.getId());
    }

    @Test
    public void createDatastreamWithCsvData() throws Exception {

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

        String data = "time, tag, value\n2016-03-01 01:01:01, signal1_entity1, 3.4";
        Map<String, String> options = new HashMap<String, String>();
        falkonry.addInput(datastream.getId(), data, options);

        Assert.assertEquals(ds.getName(), datastream.getName());
        Assert.assertNotEquals(null, datastream.getId());

        Assert.assertEquals(datastream.getField().getTime().getFormat(), ds.getField().getTime().getFormat());
        Assert.assertEquals(datastream.getField().getTime().getIdentifier(), ds.getField().getTime().getIdentifier());
        Assert.assertEquals(datastream.getField().getTime().getZone(), ds.getField().getTime().getZone());

        Assert.assertEquals(datastream.getDatasource().getType(), ds.getDatasource().getType());

        Assert.assertEquals(datastream.getField().getSignal().getDelimiter(), ds.getField().getSignal().getDelimiter());
        Assert.assertEquals(datastream.getField().getSignal().getIsSignalPrefix(), ds.getField().getSignal().getIsSignalPrefix());
        Assert.assertEquals(datastream.getField().getSignal().getTagIdentifier(), ds.getField().getSignal().getTagIdentifier());
        Assert.assertEquals(datastream.getField().getSignal().getValueIdentifier(), ds.getField().getSignal().getValueIdentifier());

    }

    @Test
    public void createDatastreamWithWideCsvData() throws Exception {

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
//        field.setEntityIdentifier("unit");

        ds.setDatasource(dataSource);
        ds.setField(field);

        Datastream datastream = falkonry.createDatastream(ds);
        datastreams.add(datastream);

        String data = "time,entity,signal1,signal2,signal3,signal4,signal5\n"
                + "1467729675422,entity1,41.11,82.34,74.63,4.8,72.01";
        Map<String, String> options = new HashMap<String, String>();
        falkonry.addInput(datastream.getId(), data, options);

        Assert.assertEquals(ds.getName(), datastream.getName());
        Assert.assertNotEquals(null, datastream.getId());

        Assert.assertEquals(datastream.getField().getTime().getFormat(), ds.getField().getTime().getFormat());
        Assert.assertEquals(datastream.getField().getTime().getIdentifier(), ds.getField().getTime().getIdentifier());
        Assert.assertEquals(datastream.getField().getTime().getZone(), ds.getField().getTime().getZone());

        Assert.assertEquals(datastream.getDatasource().getType(), ds.getDatasource().getType());

        Assert.assertEquals(datastream.getField().getSignal().getDelimiter(), ds.getField().getSignal().getDelimiter());
        Assert.assertEquals(datastream.getField().getSignal().getIsSignalPrefix(), ds.getField().getSignal().getIsSignalPrefix());
        Assert.assertEquals(datastream.getField().getSignal().getTagIdentifier(), ds.getField().getSignal().getTagIdentifier());
        Assert.assertEquals(datastream.getField().getSignal().getValueIdentifier(), ds.getField().getSignal().getValueIdentifier());

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
