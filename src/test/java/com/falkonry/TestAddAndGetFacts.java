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

public class TestAddAndGetFacts {

	Falkonry falkonry = null;
	String host = System.getenv("FALKONRY_HOST_URL");
	String token = System.getenv("FALKONRY_TOKEN");
	String assessmentId = System.getenv("FALKONRY_ASSESSMENT_SLIDING_ID");

	List<Datastream> datastreams = new ArrayList<Datastream>();

	private void checkStatus(String trackerId) throws Exception {
		for(int i=0; i < 12; i++) {
			Tracker tracker = falkonry.getStatus(trackerId);
			if (tracker.getStatus().equals("FAILED") || tracker.getStatus().equals("ERROR")) {
				throw new Exception(tracker.getMessage());
			}
			else if(tracker.getStatus().equals("SUCCESS") || tracker.getStatus().equals("COMPLETED")){
				break;
			}
			Thread.sleep(5000);
		}
	}

	/**
	 *
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		falkonry = new Falkonry(host, token);
	}

	/**
	 * Should add narrow format datastream and add facts to assessment with CSV
	 * format
	 *
	 * @throws Exception
	 */
	@Test
	public void createDatastreamWithCsvFacts() throws Exception {

		// creating datastream
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

		// creating assessment
		AssessmentRequest assessmentRequest = new AssessmentRequest();
		assessmentRequest.setName("Health");
		assessmentRequest.setDatastream(datastream.getId());
		assessmentRequest.setAssessmentRate("PT1S");
		Assessment assessment = falkonry.createAssessment(assessmentRequest);

		// adding fact to the assessment
		Map<String, String> queryParams = new HashMap<String, String>();
		queryParams.put("startTimeIdentifier", "time");
		queryParams.put("endTimeIdentifier", "end");
		queryParams.put("timeFormat", time.getFormat());
		queryParams.put("timeZone", time.getZone());
		queryParams.put("entityIdentifier", "entity");
		queryParams.put("valueIdentifier", "Health");

		String data = "time,end,entity,Health\n2011-03-31T00:00:00.000Z,2011-04-01T00:00:00.000Z,entity1,Normal\n2011-03-31T00:00:00.000Z,2011-04-01T00:00:00.000Z,entity1,Normal";
		InputStatus response = falkonry.addFacts(assessment.getId(), data, queryParams);
		Assert.assertEquals(response.getAction(), "ADD_FACT_DATA");
		Assert.assertEquals(response.getStatus(), "PENDING");

		// checking ingestion status
		checkStatus(response.getId());

		// fetching fact data
		Map<String, String> options = new HashMap<String, String>();
		options.put("startTime", "2011-01-17T01:00:00.000Z"); // time should be in ISO format YYYY-MM-DDTHH:mm:ss.SSSZ
		options.put("endTime", "2011-08-18T01:00:00.000Z");   // time should be in ISO format YYYY-MM-DDTHH:mm:ss.SSSZ
		options.put("responseFormat", "application/json");    // can be text/csv or application/json based on your requirement

		HttpResponseFormat factsResponse = falkonry.getFactsData(assessment.getId(), options);
		Assert.assertEquals(factsResponse.getResponse().length() > 0, true);
	}

	/**
	 * Should add fact to assessment of batch datastream
	 *
	 * @throws Exception
	 */
	@Test
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

		String data = "{\"time\" :\"2016-03-01T01:01:01.000Z\",\"batch\":\"batch1\", \"entity\" : \"entity1\", \"signal\" : \"current\", \"value\" : 12.5}\n"
				+ "{\"time\" :\"2016-03-01T01:01:02.000Z\",\"batch\":\"batch2\", \"entity\" : \"entity1\", \"signal\" : \"current\", \"value\" : 12.5}";

		Map<String, String> options = new HashMap<String, String>();
		options.put("timeIdentifier", "time");
		options.put("timeFormat", "iso_8601");
		options.put("timeZone", time.getZone());
		options.put("entityIdentifier", "entity");
		options.put("signalIdentifier", "signal");
		options.put("batchIdentifier", "batch");
		options.put("valueIdentifier", "value");
		options.put("fileFormat", "json");
		options.put("streaming", "false");
		options.put("hasMoreData", "false");

		InputStatus ins = falkonry.addInput(datastream.getId(), data, options);
		Assert.assertEquals(ins.getAction(), "ADD_DATA_DATASTREAM");
		Assert.assertEquals(ins.getStatus(), "PENDING");

		// checking ingestion status
		checkStatus(ins.getId());
		Thread.sleep(10000); //10s of delay

		// creating assessment
		AssessmentRequest assessmentRequest = new AssessmentRequest();
		assessmentRequest.setName("Health");
		assessmentRequest.setDatastream(datastream.getId());
		assessmentRequest.setAssessmentRate("PT1S");
		Assessment assessment = falkonry.createAssessment(assessmentRequest);

		// adding fact to the assessment
		Map<String, String> queryParams = new HashMap<String, String>();
		queryParams.put("entityIdentifier", "entity");
		queryParams.put("valueIdentifier", "Health");
		queryParams.put("batchIdentifier", "batch");

		data = "entity,Health,batch\n" +
				"entity1,Normal,batch1\n" +
				"entity1,Normal,batch2";
		InputStatus response = falkonry.addFacts(assessment.getId(), data, queryParams);
		Assert.assertEquals(response.getAction(), "ADD_FACT_DATA");
		Assert.assertEquals(response.getStatus(), "PENDING");

		// checking ingestion status
		checkStatus(response.getId());

		options = new HashMap<String, String>();
		options.put("responseFormat", "application/json"); // can be text/csv or application/json based on your requirement
		HttpResponseFormat factsResponse = falkonry.getFactsData(assessment.getId(), options);
		Assert.assertEquals(factsResponse.getResponse().length() > 0, true);

	}

	/**
	 * Should add narrow format datastream and add facts with Tags to assessment
	 * with CSV format
	 *
	 * @throws Exception
	 */
	@Test
	public void createDatastreamWithTagsCsvFacts() throws Exception {

		// creating datastream
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

		// creating assessment
		AssessmentRequest assessmentRequest = new AssessmentRequest();
		assessmentRequest.setName("Health");
		assessmentRequest.setDatastream(datastream.getId());
		assessmentRequest.setAssessmentRate("PT1S");
		Assessment assessment = falkonry.createAssessment(assessmentRequest);

		// adding fact to the assessment
		Map<String, String> queryParams = new HashMap<String, String>();
		queryParams.put("startTimeIdentifier", "time");
		queryParams.put("endTimeIdentifier", "end");
		queryParams.put("timeFormat", time.getFormat());
		queryParams.put("timeZone", time.getZone());
		queryParams.put("entityIdentifier", "entity");
		queryParams.put("valueIdentifier", "Health");
		queryParams.put("keywordIdentifier", "Tags");

		String data = "time,end,entity,Health,Tags\n" +
				"2011-03-31T00:00:00.000Z,2011-04-01T00:00:00.000Z,entity1,Normal,testTag1\n" +
				"2011-03-31T00:00:00.000Z,2011-04-01T00:00:00.000Z,entity1,Normal,testTag2";
		InputStatus response = falkonry.addFacts(assessment.getId(), data, queryParams);
		Assert.assertEquals(response.getAction(), "ADD_FACT_DATA");
		Assert.assertEquals(response.getStatus(), "PENDING");

		// checking ingestion status
		checkStatus(response.getId());

		Map<String, String> options = new HashMap<String, String>();
		options.put("startTime", "2011-01-17T01:00:00.000Z"); // time should be in ISO format YYYY-MM-DDTHH:mm:ss.SSSZ
		options.put("endTime", "2011-08-18T01:00:00.000Z");   // time should be in ISO format YYYY-MM-DDTHH:mm:ss.SSSZ
		options.put("responseFormat", "application/json");    // can be text/csv or application/json based on your requirement
		HttpResponseFormat factsResponse = falkonry.getFactsData(assessment.getId(), options);
		Assert.assertEquals(factsResponse.getResponse().length() > 0, true);
	}

	/**
	 * Should add narrow format datastream and add facts with additional Tags to
	 * assessment with CSV format
	 *
	 * @throws Exception
	 */
	@Test
	public void createDatastreamWithadditionalKeywordsCsvFacts() throws Exception {

		// creating datastream
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

		// creating assessment
		AssessmentRequest assessmentRequest = new AssessmentRequest();
		assessmentRequest.setName("Health");
		assessmentRequest.setDatastream(datastream.getId());
		assessmentRequest.setAssessmentRate("PT1S");
		Assessment assessment = falkonry.createAssessment(assessmentRequest);

		// adding fact to the assessment
		Map<String, String> queryParams = new HashMap<String, String>();
		queryParams.put("startTimeIdentifier", "time");
		queryParams.put("endTimeIdentifier", "end");
		queryParams.put("timeFormat", time.getFormat());
		queryParams.put("timeZone", time.getZone());
		queryParams.put("entityIdentifier", "entity");
		queryParams.put("valueIdentifier", "Health");
		queryParams.put("additionalKeyword", "testTag");

		String data = "time,end,entity,Health\n" +
				"2011-03-31T00:00:00.000Z,2011-04-01T00:00:00.000Z,entity1,Normal\n" +
				"2011-03-31T00:00:00.000Z,2011-04-01T00:00:00.000Z,entity1,Normal";
		InputStatus response = falkonry.addFacts(assessment.getId(), data, queryParams);
		Assert.assertEquals(response.getAction(), "ADD_FACT_DATA");
		Assert.assertEquals(response.getStatus(), "PENDING");

		// checking ingestion status
		checkStatus(response.getId());

		Map<String, String> options = new HashMap<String, String>();
		options.put("startTime", "2011-01-17T01:00:00.000Z"); // time should be in ISO format YYYY-MM-DDTHH:mm:ss.SSSZ
		options.put("endTime", "2011-08-18T01:00:00.000Z");   // time should be in ISO format YYYY-MM-DDTHH:mm:ss.SSSZ
		options.put("responseFormat", "application/json");    // can be text/csv or application/json based on your requirement
		HttpResponseFormat factsResponse = falkonry.getFactsData(assessment.getId(), options);
		Assert.assertEquals(factsResponse.getResponse().length() > 0, true);
	}

	/**
	 * Should add wide format datastream and add facts to assessment with CSV
	 * format
	 *
	 * @throws Exception
	 */
	@Test
	public void createDatastreamWithWideCsvFacts() throws Exception {

		// creating datastream
		Datastream ds = new Datastream();
		ds.setName("Test-DS-" + Math.random());

		TimeObject time = new TimeObject();
		time.setIdentifier("time");
		time.setFormat("iso_8601");
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

		// creating assessment
		AssessmentRequest assessmentRequest = new AssessmentRequest();
		String name = "Test-AS-" + Math.random();
		assessmentRequest.setName(name);
		assessmentRequest.setDatastream(datastream.getId());
		assessmentRequest.setAssessmentRate("PT1S");
		Assessment assessment = falkonry.createAssessment(assessmentRequest);

		// adding fact to the assessment
		Map<String, String> queryParams = new HashMap<String, String>();
		queryParams.put("startTimeIdentifier", "time");
		queryParams.put("endTimeIdentifier", "end");
		queryParams.put("timeFormat", time.getFormat());
		queryParams.put("timeZone", time.getZone());
		queryParams.put("entityIdentifier", "entity");
		queryParams.put("valueIdentifier", "Health");

		String data = "time,end,entity,Health\n" +
				"2011-03-31T00:00:00.000Z,2011-04-01T00:00:00.000Z,entity1,Normal\n" +
				"2011-03-31T00:00:00.000Z,2011-04-01T00:00:00.000Z,entity1,Normal";
		InputStatus response = falkonry.addFacts(assessment.getId(), data, queryParams);
		Assert.assertEquals(response.getAction(), "ADD_FACT_DATA");
		Assert.assertEquals(response.getStatus(), "PENDING");

		// checking ingestion status
		checkStatus(response.getId());

		Map<String, String> options = new HashMap<String, String>();
		options.put("startTime", "2011-01-17T01:00:00.000Z"); // time should be in ISO format YYYY-MM-DDTHH:mm:ss.SSSZ
		options.put("endTime", "2011-08-18T01:00:00.000Z");   // time should be in ISO format YYYY-MM-DDTHH:mm:ss.SSSZ
		options.put("responseFormat", "application/json");    // can be text/csv or application/json based on your requirement
		HttpResponseFormat factsResponse = falkonry.getFactsData(assessment.getId(), options);
		Assert.assertEquals(factsResponse.getResponse().length() > 0, true);
	}

	/**
	 * Should add narrow format datastream and add facts to assessment with JSON
	 * format
	 *
	 * @throws Exception
	 */
	@Test
	public void createDatastremWithJsonFacts() throws Exception {

		// creating datastream
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

		// creating assessment
		AssessmentRequest assessmentRequest = new AssessmentRequest();
		String name = "Test-AS-" + Math.random();
		assessmentRequest.setName(name);
		assessmentRequest.setDatastream(datastream.getId());
		assessmentRequest.setAssessmentRate("PT1S");
		Assessment assessment = falkonry.createAssessment(assessmentRequest);
		Assert.assertEquals(assessment.getName(), assessmentRequest.getName());

		Map<String, String> queryParams = new HashMap<String, String>();
		queryParams.put("startTimeIdentifier", "time");
		queryParams.put("endTimeIdentifier", "end");
		queryParams.put("timeFormat", time.getFormat());
		queryParams.put("timeZone", time.getZone());
		queryParams.put("entityIdentifier", "entity");
		queryParams.put("valueIdentifier", "Health");

		String data = "{\"time\" : \"2011-03-26T12:00:00.000Z\", \"entity\" : \"entity1\", \"end\" : \"2012-06-01T00:00:00.000Z\", \"Health\" : \"Normal\"}";
		InputStatus response = falkonry.addFacts(assessment.getId(), data, queryParams);
		Assert.assertEquals(response.getAction(), "ADD_FACT_DATA");
		Assert.assertEquals(response.getStatus(), "PENDING");

		// checking ingestion status
		checkStatus(response.getId());

		Map<String, String> options = new HashMap<String, String>();
		options.put("startTime", "2011-01-17T01:00:00.000Z"); // time should be in ISO format YYYY-MM-DDTHH:mm:ss.SSSZ
		options.put("endTime", "2011-08-18T01:00:00.000Z");   // time should be in ISO format YYYY-MM-DDTHH:mm:ss.SSSZ
		options.put("responseFormat", "application/json");    // can be text/csv or application/json based on your requirement
		HttpResponseFormat factsResponse = falkonry.getFactsData(assessment.getId(), options);
		Assert.assertEquals(factsResponse.getResponse().length() > 0, true);
	}

	/**
	 * Should add wide format datastream and add facts to assessment with JSON
	 * format
	 *
	 * @throws Exception
	 */
	@Test
	public void createAssessmentWithWideJsonFacts() throws Exception {

		// creating datastream
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

		// creating assessment
		AssessmentRequest assessmentRequest = new AssessmentRequest();
		String name = "Test-AS-" + Math.random();
		assessmentRequest.setName(name);
		assessmentRequest.setDatastream(datastream.getId());
		assessmentRequest.setAssessmentRate("PT1S");
		Assessment assessment = falkonry.createAssessment(assessmentRequest);
		Assert.assertEquals(assessment.getName(), assessment.getName());

		// adding fact to the assessment
		Map<String, String> queryParams = new HashMap<String, String>();
		queryParams.put("startTimeIdentifier", "time");
		queryParams.put("endTimeIdentifier", "end");
		queryParams.put("timeFormat", "iso_8601");
		queryParams.put("timeZone", time.getZone());
		queryParams.put("entityIdentifier", "entity");
		queryParams.put("valueIdentifier", "Health");

		String data = "{\"time\" : \"2011-03-26T12:00:00.000Z\", \"entity\" : \"entity1\", \"end\" : \"2012-06-01T00:00:00.000Z\", \"Health\" : \"Normal\"}";
		InputStatus response = falkonry.addFacts(assessment.getId(), data, queryParams);
		Assert.assertEquals(response.getAction(), "ADD_FACT_DATA");
		Assert.assertEquals(response.getStatus(), "PENDING");

		// checking ingestion status
		checkStatus(response.getId());
	}

	/**
	 * Should get facts of an specific assessment
	 * format
	 *
	 * @throws Exception
	 */
	@Test
	public void getFactsFromAssessment() throws Exception {
		Map<String, String> queryParams = new HashMap<String, String>();
		queryParams.put("model", "2");
		queryParams.put("start", "2011-01-01T00:00:00.000Z");
		queryParams.put("end", "2014-12-31T00:00:00.000Z");

		HttpResponseFormat response = falkonry.getFactsData(assessmentId, queryParams);
		Assert.assertEquals(response.getResponse().length() > 0, true);

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
