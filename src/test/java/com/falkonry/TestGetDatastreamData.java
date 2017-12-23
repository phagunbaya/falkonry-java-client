package com.falkonry;

/*!
 * falkonry-java-client
 * Copyright(c) 2017 Falkonry Inc
 * MIT Licensed
 */
import com.falkonry.client.Falkonry;
import com.falkonry.helper.models.*;
import org.junit.*;
import java.util.*;

@Ignore
public class TestGetDatastreamData {

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

	@Test

	/**
	 * Should add narrow format datastream and add facts to assessment with CSV
	 * format
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
		// String data = "{\"time\" : \"2016-03-01 01:01:01\", \"tag\" :
		// \"signal1_entity1\", \"value\" : 3.4}";
		String data = "{\"time\" :\"2016-03-01T01:01:01.000Z\",\"entity\":\"entity1\", \"signal\" : \"current\", \"value\" : 12.5}"
				+ "{\"time\" :\"2016-03-01T01:01:01.000Z\",\"entity\":\"entity2\", \"signal\" : \"vibration\", \"value\" : 3.4}";
		options.put("timeIdentifier", "time");
		options.put("timeFormat", "iso_8601");
		options.put("timeZone", time.getZone());
		options.put("signalIdentifier", "signal");
		options.put("entityIdentifier", "entity");
		options.put("valueIdentifier", "value");
		options.put("fileFormat", "csv");
		options.put("streaming", "false");
		options.put("hasMoreData", "false");
		falkonry.addInput(datastream.getId(), data, options);

		Map<String, String> queryParams = new HashMap<String, String>();
		queryParams.put("startTimeIdentifier", "time");
		queryParams.put("endTimeIdentifier", "end");
		queryParams.put("timeFormat", time.getFormat());
		queryParams.put("timeZone", time.getZone());
		queryParams.put("entityIdentifier", "entity");
		queryParams.put("valueIdentifier", "Health");

		data = "time,end,entity,Health\n2011-03-31T00:00:00.000Z,2011-04-01T00:00:00.000Z,entity1,Normal\n2011-03-31T00:00:00.000Z,2011-04-01T00:00:00.000Z,entity1,Normal";
		InputStatus response = falkonry.addFacts(assessment.getId(), data, queryParams);
		Assert.assertEquals(response.getAction(), "ADD_FACT_DATA");
		Assert.assertEquals(response.getStatus(), "PENDING");
	}
	
	public void createBatchDatastreamWithCsvFacts() throws Exception {

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
		field.setBatchIdentifier("batch");

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
		// String data = "{\"time\" : \"2016-03-01 01:01:01\", \"tag\" :
		// \"signal1_entity1\", \"value\" : 3.4}";
		String data = "{\"time\" :\"2016-03-01T01:01:01.000Z\",\"entity\":\"entity1\", \"signal\" : \"current\", \"value\" : 12.5, \"batch\" : \"batch1\"}"
				+ "{\"time\" :\"2016-03-01T01:01:01.000Z\",\"entity\":\"entity2\", \"signal\" : \"vibration\", \"value\" : 3.4, \"batch\" : \"batch2\"}";
		options.put("timeIdentifier", "time");
		options.put("timeFormat", "iso_8601");
		options.put("timeZone", time.getZone());
		options.put("signalIdentifier", "signal");
		options.put("entityIdentifier", "entity");
		options.put("valueIdentifier", "value");
		options.put("batchIdentifier", "batch");
		options.put("fileFormat", "csv");
		options.put("streaming", "false");
		options.put("hasMoreData", "false");
		falkonry.addInput(datastream.getId(), data, options);

		Map<String, String> queryParams = new HashMap<String, String>();
		queryParams.put("startTimeIdentifier", "time");
		queryParams.put("endTimeIdentifier", "end");
		queryParams.put("timeFormat", time.getFormat());
		queryParams.put("timeZone", time.getZone());
		queryParams.put("entityIdentifier", "entity");
		queryParams.put("valueIdentifier", "Health");
		queryParams.put("batchIdentifier", "batch");

		data = "time,end,entity,Health,batch\n2011-03-31T00:00:00.000Z,2011-04-01T00:00:00.000Z,entity1,Normal,batch\n2011-03-31T00:00:00.000Z,2011-04-01T00:00:00.000Z,entity1,Normal,batch2";
		InputStatus response = falkonry.addFacts(assessment.getId(), data, queryParams);
		Assert.assertEquals(response.getAction(), "ADD_FACT_DATA");
		Assert.assertEquals(response.getStatus(), "PENDING");
	}

	
	
	@Test

	/**
	 * Should add wide format datastream and add facts to assessment with CSV
	 * format
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

		Datasource dataSource = new Datasource();
		dataSource.setType("STANDALONE");

		List<Input> inputList = new ArrayList<Input>();

		Input input1 = new Input();
		input1.setName("signal1");
		EventType eventType1 = new EventType();
		eventType1.setType("Samples");
		input1.setEventType(eventType1);
		ValueType valueType1 = new ValueType();
		valueType1.setType("Numeric");
		input1.setValueType(valueType1);
		inputList.add(input1);

		Input input2 = new Input();
		input2.setName("signal2");
		EventType eventType2 = new EventType();
		eventType2.setType("Samples");
		input2.setEventType(eventType2);
		ValueType valueType2 = new ValueType();
		valueType2.setType("Numeric");
		input2.setValueType(valueType2);
		inputList.add(input2);

		Input input3 = new Input();
		input3.setName("signal3");
		EventType eventType3 = new EventType();
		eventType3.setType("Samples");
		input3.setEventType(eventType3);
		ValueType valueType3 = new ValueType();
		valueType3.setType("Numeric");
		input3.setValueType(valueType3);
		inputList.add(input3);

		Input input4 = new Input();
		input4.setName("signal4");
		EventType eventType4 = new EventType();
		eventType4.setType("Samples");
		input4.setEventType(eventType4);
		ValueType valueType4 = new ValueType();
		valueType4.setType("Numeric");
		input4.setValueType(valueType4);
		inputList.add(input4);

		Input input5 = new Input();
		input5.setName("signal5");
		EventType eventType5 = new EventType();
		eventType5.setType("Samples");
		input5.setEventType(eventType5);
		ValueType valueType5 = new ValueType();
		valueType5.setType("Numeric");
		input5.setValueType(valueType5);
		inputList.add(input5);

		ds.setInputList(inputList);

		Field field = new Field();
		field.setTime(time);
		field.setEntityIdentifier("entity");

		ds.setDatasource(dataSource);
		ds.setField(field);

		Datastream datastream = falkonry.createDatastream(ds);
		datastreams.add(datastream);

		Map<String, String> options = new HashMap<String, String>();
		String data = "time,entity,signal1,signal2,signal3\n2016-03-01 01:01:01,entity1,3.4,4.8,8.3";
		options.put("fileFormat", "csv");
		options.put("streaming", "false");
		options.put("hasMoreData", "false");
		falkonry.addInput(datastream.getId(), data, options);

		AssessmentRequest assessmentRequest = new AssessmentRequest();
		String name = "Test-AS-" + Math.random();
		assessmentRequest.setName(name);
		assessmentRequest.setDatastream(datastream.getId());
		assessmentRequest.setAssessmentRate("PT1S");
		Assessment assessment = falkonry.createAssessment(assessmentRequest);
		assessments.add(assessment);

		Map<String, String> queryParams = new HashMap<String, String>();
		queryParams.put("startTimeIdentifier", "time");
		queryParams.put("endTimeIdentifier", "end");
		queryParams.put("timeFormat", time.getFormat());
		queryParams.put("timeZone", time.getZone());
		queryParams.put("entityIdentifier", "entity");
		queryParams.put("valueIdentifier", "Health");

		data = "time,end,entity,Health\n2011-03-31T00:00:00.000Z,2011-04-01T00:00:00.000Z,entity1,Normal\n2011-03-31T00:00:00.000Z,2011-04-01T00:00:00.000Z,entity1,Normal";
		InputStatus response = falkonry.addFacts(assessment.getId(), data, queryParams);
		Assert.assertEquals(response.getAction(), "ADD_FACT_DATA");
		Assert.assertEquals(response.getStatus(), "PENDING");
	}

	/**
	 * Should add narrow format datastream and add facts to assessment with JSON
	 * format
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

		AssessmentRequest assessmentRequest = new AssessmentRequest();
		String name = "Test-AS-" + Math.random();
		assessmentRequest.setName(name);
		assessmentRequest.setDatastream(datastream.getId());
		assessmentRequest.setAssessmentRate("PT1S");
		Assessment assessment = falkonry.createAssessment(assessmentRequest);
		assessments.add(assessment);
		Assert.assertEquals(assessment.getName(), assessmentRequest.getName());

		Map<String, String> options = new HashMap<String, String>();

		String data = "{\"time\" : \"2016-03-01 01:01:01\", \"tag\" : \"signal1_entity1\", \"value\" : 3.4}";
		options.put("fileFormat", "json");
		options.put("streaming", "false");
		options.put("hasMoreData", "false");
		falkonry.addInput(datastream.getId(), data, options);

		Interval interval = new Interval();
		interval.setDuration("PT1S");

		Map<String, String> queryParams = new HashMap<String, String>();
		queryParams.put("startTimeIdentifier", "time");
		queryParams.put("endTimeIdentifier", "end");
		queryParams.put("timeFormat", time.getFormat());
		queryParams.put("timeZone", time.getZone());
		queryParams.put("entityIdentifier", "entity");
		queryParams.put("valueIdentifier", "Health");

		data = "{\"time\" : \"2011-03-26T12:00:00.000Z\", \"entity\" : \"entity1\", \"end\" : \"2012-06-01T00:00:00.000Z\", \"Health\" : \"Normal\"}";
		InputStatus response = falkonry.addFacts(assessment.getId(), data, queryParams);
		Assert.assertEquals(response.getAction(), "ADD_FACT_DATA");
		Assert.assertEquals(response.getStatus(), "PENDING");
	}

	@Test

	/**
	 * Should add wide format datastream and add facts to assessment with JSON
	 * format
	 *
	 * @throws Exception
	 */
	public void createAssessmentWithWideJsonFacts() throws Exception {

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
		Assert.assertEquals(assessment.getName(), assessment.getName());

		Map<String, String> options = new HashMap<String, String>();

		String data = "{\"time\":1467729675422,\"entity\":\"entity1\",\"signal1\":41.11,\"signal2\":82.34,\"signal3\":74.63,\"signal4\":4.8,\"signal5\":72.01}";
		options.put("fileFormat", "json");
		options.put("streaming", "false");
		options.put("hasMoreData", "false");
		falkonry.addInput(datastream.getId(), data, options);

		Interval interval = new Interval();
		interval.setDuration("PT1S");

		Map<String, String> queryParams = new HashMap<String, String>();
		queryParams.put("startTimeIdentifier", "time");
		queryParams.put("endTimeIdentifier", "end");
		queryParams.put("timeFormat", time.getFormat());
		queryParams.put("timeZone", time.getZone());
		queryParams.put("entityIdentifier", "entity");
		queryParams.put("valueIdentifier", "Health");

		data = "{\"time\" : \"2011-03-26T12:00:00.000Z\", \"entity\" : \"entity1\", \"end\" : \"2012-06-01T00:00:00.000Z\", \"Health\" : \"Normal\"}";
		InputStatus response = falkonry.addFacts(assessment.getId(), data, queryParams);
		Assert.assertEquals(response.getAction(), "ADD_FACT_DATA");
		Assert.assertEquals(response.getStatus(), "PENDING");
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
