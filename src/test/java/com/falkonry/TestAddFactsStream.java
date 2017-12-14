package com.falkonry;

/*!
 * falkonry-java-client
 * Copyright(c) 2017 Falkonry Inc
 * MIT Licensed
 */
import com.falkonry.client.Falkonry;
import com.falkonry.helper.models.*;
import org.apache.commons.io.FileUtils;
import org.junit.*;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.*;

@Ignore
public class TestAddFactsStream {

    Falkonry falkonry = null;
    String host = "https://localhost:8080";
    String token = "auth-token";

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

    /**
     * Should create datastream and add fact stream data in CSV format
     *
     * @throws Exception
     */
    @Test
    public void createDatastreamWithCsvFactsStream() throws Exception {

    	Datastream ds = new Datastream();
		ds.setName("Test-DS-" + Math.random());
		TimeObject time = new TimeObject();
		time.setIdentifier("time");
		time.setFormat("iso_8601");
		time.setZone("GMT");
		Signal signal = new Signal();

		signal.setValueIdentifier("value");
		signal.setSignalIdentifier("signal");
		Field field = new Field();
		field.setEntityIdentifier("entity");
		
		field.setSignal(signal);
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

        String data = "time,entity,signal,value\n2016-03-01 01:01:01,entity1,signal1,3.4";
        options.put("timeIdentifier", "time");
        options.put("timeFormat", "YYYY-MM-DD HH:mm:ss");
        options.put("timeZone", time.getZone());
		options.put("signalIdentifier", "signal");
		options.put("entityIdentifier", "entity");
		options.put("valueIdentifier", "value");
		options.put("fileFormat", "csv");
		options.put("streaming", "false");
		options.put("hasMoreData", "false");
        falkonry.addInput(datastream.getId(), data, options);

        File file = new File("res/factsData.csv");
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(FileUtils.readFileToByteArray(file));

        Map<String, String> queryParams = new HashMap<String, String>();
        queryParams.put("startTimeIdentifier", "time");
        queryParams.put("endTimeIdentifier", "end");
        queryParams.put("timeFormat", time.getFormat());
        queryParams.put("timeZone", time.getZone());
        queryParams.put("entityIdentifier", "entity");
        queryParams.put("valueIdentifier", "Health");

        InputStatus response = falkonry.addFactsStream(assessment.getId(), byteArrayInputStream, queryParams);
        Assert.assertEquals(response.getAction(), "ADD_FACT_DATA");
        Assert.assertEquals(response.getStatus(), "PENDING");
    }
    
    /**
     * Should create datastream and add fact stream data with tags in CSV format
     *
     * @throws Exception
     */
    @Test
    public void createDatastreamWithCsvFactsWithTagsStream() throws Exception {

    	Datastream ds = new Datastream();
		ds.setName("Test-DS-" + Math.random());
		TimeObject time = new TimeObject();
		time.setIdentifier("time");
		time.setFormat("iso_8601");
		time.setZone("GMT");
		Signal signal = new Signal();

		signal.setValueIdentifier("value");
		signal.setSignalIdentifier("signal");
		Field field = new Field();
		field.setEntityIdentifier("entity");
		
		field.setSignal(signal);
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

        
        String data = "time,entity,signal,value\n2016-03-01 01:01:01,entity1,signal1,3.4";
        options.put("timeIdentifier", "time");
        options.put("timeFormat", "YYYY-MM-DD HH:mm:ss");
        options.put("timeZone", time.getZone());
		options.put("signalIdentifier", "signal");
		options.put("entityIdentifier", "entity");
		options.put("valueIdentifier", "value");
		options.put("fileFormat", "csv");
		options.put("streaming", "false");
		options.put("hasMoreData", "false");
        falkonry.addInput(datastream.getId(), data, options);

		File file = new File("res/factsDataWithTags.csv");
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(FileUtils.readFileToByteArray(file));

        Map<String, String> queryParams = new HashMap<String, String>();
        queryParams.put("startTimeIdentifier", "time");
        queryParams.put("endTimeIdentifier", "end");
        queryParams.put("timeFormat", time.getFormat());
        queryParams.put("timeZone", time.getZone());
        queryParams.put("entityIdentifier", "entity");
        queryParams.put("valueIdentifier", "Health");
        queryParams.put("tagIdentifier", "Tags");

        InputStatus response = falkonry.addFactsStream(assessment.getId(), byteArrayInputStream, queryParams);
        Assert.assertEquals(response.getAction(), "ADD_FACT_DATA");
        Assert.assertEquals(response.getStatus(), "PENDING");
    }
    
    /**
     * Should create datastream and add fact stream data with additional Tag in CSV format
     *
     * @throws Exception
     */
    @Test
    public void createDatastreamWithCsvFactsWithAdditionalTagStream() throws Exception {

    	Datastream ds = new Datastream();
		ds.setName("Test-DS-" + Math.random());
		TimeObject time = new TimeObject();
		time.setIdentifier("time");
		time.setFormat("iso_8601");
		time.setZone("GMT");
		Signal signal = new Signal();

		signal.setValueIdentifier("value");
		signal.setSignalIdentifier("signal");
		Field field = new Field();
		field.setEntityIdentifier("entity");
		
		field.setSignal(signal);
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

        String data = "time,entity,signal,value\n2016-03-01 01:01:01,entity1,signal1,3.4";
        options.put("timeIdentifier", "time");
        options.put("timeFormat", "YYYY-MM-DD HH:mm:ss");
        options.put("timeZone", time.getZone());
		options.put("signalIdentifier", "signal");
		options.put("entityIdentifier", "entity");
		options.put("valueIdentifier", "value");
		options.put("fileFormat", "csv");
		options.put("streaming", "false");
		options.put("hasMoreData", "false");
        falkonry.addInput(datastream.getId(), data, options);

        File file = new File("res/factsData.csv");
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(FileUtils.readFileToByteArray(file));

        Map<String, String> queryParams = new HashMap<String, String>();
        queryParams.put("startTimeIdentifier", "time");
        queryParams.put("endTimeIdentifier", "end");
        queryParams.put("timeFormat", time.getFormat());
        queryParams.put("timeZone", time.getZone());
        queryParams.put("entityIdentifier", "entity");
        queryParams.put("valueIdentifier", "Health");
        queryParams.put("additionalTag", "testTag");

        InputStatus response = falkonry.addFactsStream(assessment.getId(), byteArrayInputStream, queryParams);
        Assert.assertEquals(response.getAction(), "ADD_FACT_DATA");
        Assert.assertEquals(response.getStatus(), "PENDING");
    }

    

    /**
     * Should create datastream and add fact data in JSON format
     *
     * @throws Exception
     */
    @Test
    public void createDatastreamWithJsonFacts() throws Exception {

    	Datastream ds = new Datastream();
		ds.setName("Test-DS-" + Math.random());
		TimeObject time = new TimeObject();
		time.setIdentifier("time");
		time.setFormat("iso_8601");
		time.setZone("GMT");
		Signal signal = new Signal();

		signal.setValueIdentifier("value");
		signal.setSignalIdentifier("signal");
		Field field = new Field();
		field.setEntityIdentifier("entity");
		
		field.setSignal(signal);
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
        Assert.assertEquals(assessment.getName(), assessmentRequest.getName());

        Map<String, String> options = new HashMap<String, String>();

        String data = "time,entity,signal,value\n2016-03-01 01:01:01,entity1,signal1,3.4";
        options.put("timeIdentifier", "time");
        options.put("timeFormat", "YYYY-MM-DD HH:mm:ss");
        options.put("timeZone", time.getZone());
		options.put("signalIdentifier", "signal");
		options.put("entityIdentifier", "entity");
		options.put("valueIdentifier", "value");
		options.put("fileFormat", "csv");
		options.put("streaming", "false");
		options.put("hasMoreData", "false");
        falkonry.addInput(datastream.getId(), data, options);

        File file = new File("res/factsData.json");
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(FileUtils.readFileToByteArray(file));
        
        Map<String, String> queryParams = new HashMap<String, String>();
        queryParams.put("startTimeIdentifier", "time");
        queryParams.put("endTimeIdentifier", "end");
        queryParams.put("timeFormat", time.getFormat());
        queryParams.put("timeZone", time.getZone());
        queryParams.put("entityIdentifier", "thing");
        queryParams.put("valueIdentifier", "Health");

        InputStatus response = falkonry.addFactsStream(assessment.getId(), byteArrayInputStream, queryParams);
        Assert.assertEquals(response.getAction(), "ADD_FACT_DATA");
        Assert.assertEquals(response.getStatus(), "PENDING");
    }

    /**
     * Should create datastream and add fact data in JSON format
     *
     * @throws Exception
     */
    @Test
    public void createDatastreamWithJsonFactsAndRetriveFacts() throws Exception {

    	Datastream ds = new Datastream();
		ds.setName("Test-DS-" + Math.random());
		TimeObject time = new TimeObject();
		time.setIdentifier("time");
		time.setFormat("iso_8601");
		time.setZone("GMT");
		Signal signal = new Signal();

		signal.setValueIdentifier("value");
		signal.setSignalIdentifier("signal");
		Field field = new Field();
		field.setEntityIdentifier("entity");
		
		field.setSignal(signal);
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
        Assert.assertEquals(assessment.getName(), assessmentRequest.getName());

        Map<String, String> options = new HashMap<String, String>();

        String data = "time,entity,signal,value\n2016-03-01 01:01:01,entity1,signal1,3.4";
        options.put("timeIdentifier", "time");
        options.put("timeFormat", "YYYY-MM-DD HH:mm:ss");
        options.put("timeZone", time.getZone());
		options.put("signalIdentifier", "signal");
		options.put("entityIdentifier", "entity");
		options.put("valueIdentifier", "value");
		options.put("fileFormat", "csv");
		options.put("streaming", "false");
		options.put("hasMoreData", "false");
        falkonry.addInput(datastream.getId(), data, options);

        File file = new File("res/factsData.json");
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(FileUtils.readFileToByteArray(file));
        
        Map<String, String> queryParams = new HashMap<String, String>();
        queryParams.put("startTimeIdentifier", "time");
        queryParams.put("endTimeIdentifier", "end");
        queryParams.put("timeFormat", time.getFormat());
        queryParams.put("timeZone", time.getZone());
        queryParams.put("entityIdentifier", "thing");
        queryParams.put("valueIdentifier", "Health");

        InputStatus response = falkonry.addFactsStream(assessment.getId(), byteArrayInputStream, queryParams);
        Assert.assertEquals(response.getAction(), "ADD_FACT_DATA");
        Assert.assertEquals(response.getStatus(), "PENDING");

        String asmtId = "743cveg32hkwl2";
        Assessment asmtResponse = falkonry.getAssessment(assessment.getId());
        // Get Facts
        options = new HashMap<String, String>();
        options.put("startTime", "2011-01-17T01:00:00.000Z"); // in the format YYYY-MM-DDTHH:mm:ss.SSSZ
        options.put("endTime", "2016-08-18T01:00:00.000Z");  // in the format YYYY-MM-DDTHH:mm:ss.SSSZ
        options.put("responseFormat", "application/json");  // also available options 1. text/csv 2. application/json
        HttpResponseFormat factsResponse = falkonry.getFactsData(assessment.getId(), options);
        Assert.assertEquals(factsResponse.getResponse().length() > 0, true);

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
