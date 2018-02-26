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

public class TestAddFactsFromStream {

	Falkonry falkonry = null;
	String host = System.getenv("FALKONRY_HOST_URL");
	String token = System.getenv("FALKONRY_TOKEN");

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
	 * Should create datastream and add fact stream data in CSV format
	 *
	 * @throws Exception
	 */
	@Test
	public void createDatastreamWithCsvFactsStream() throws Exception {

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
		File file = new File(this.getClass().getClassLoader().getResource("factsData.csv").getFile());
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

		// checking ingestion status
		checkStatus(response.getId());
	}
	
	/**
	 * Should create batch datastream and add fact stream data in CSV format
	 *
	 * @throws Exception
	 */
	@Test
	public void createBatchDatastreamWithCsvFactsStream() throws Exception {

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

		String data = "time,entity,signal,value,batch\n2016-03-01 01:01:01,entity1,signal1,3.4,batch1";
		options.put("timeIdentifier", "time");
		options.put("timeFormat", "YYYY-MM-DD HH:mm:ss");
		options.put("timeZone", time.getZone());
		options.put("signalIdentifier", "signal");
		options.put("entityIdentifier", "entity");
		options.put("valueIdentifier", "value");
		options.put("fileFormat", "csv");
		options.put("streaming", "false");
		options.put("hasMoreData", "false");
		options.put("batchIdentifier", "batch");
		falkonry.addInput(datastream.getId(), data, options);

		File file = new File("res/factsDataBatch.csv");
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(FileUtils.readFileToByteArray(file));

		Map<String, String> queryParams = new HashMap<String, String>();
		queryParams.put("startTimeIdentifier", "time");
		queryParams.put("endTimeIdentifier", "end");
		queryParams.put("timeFormat", time.getFormat());
		queryParams.put("timeZone", time.getZone());
		queryParams.put("entityIdentifier", "entity");
		queryParams.put("valueIdentifier", "Health");
		queryParams.put("batchIdentifier", "batch");

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
		File file = new File(this.getClass().getClassLoader().getResource("factsDataWithTags.csv").getFile());
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(FileUtils.readFileToByteArray(file));

		Map<String, String> queryParams = new HashMap<String, String>();
		queryParams.put("startTimeIdentifier", "time");
		queryParams.put("endTimeIdentifier", "end");
		queryParams.put("timeFormat", time.getFormat());
		queryParams.put("timeZone", time.getZone());
		queryParams.put("entityIdentifier", "entity");
		queryParams.put("valueIdentifier", "Health");
		queryParams.put("keyIdentifier", "Tags");

		InputStatus response = falkonry.addFactsStream(assessment.getId(), byteArrayInputStream, queryParams);
		Assert.assertEquals(response.getAction(), "ADD_FACT_DATA");
		Assert.assertEquals(response.getStatus(), "PENDING");

		// checking ingestion status
		checkStatus(response.getId());
	}

	/**
	 * Should create datastream and add fact stream data with additional Tag in
	 * CSV format
	 *
	 * @throws Exception
	 */
	@Test
	public void createDatastreamWithCsvFactsWithadditionalKeywordStream() throws Exception {

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
		File file = new File(this.getClass().getClassLoader().getResource("factsData.csv").getFile());
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(FileUtils.readFileToByteArray(file));

		Map<String, String> queryParams = new HashMap<String, String>();
		queryParams.put("startTimeIdentifier", "time");
		queryParams.put("endTimeIdentifier", "end");
		queryParams.put("timeFormat", time.getFormat());
		queryParams.put("timeZone", time.getZone());
		queryParams.put("entityIdentifier", "entity");
		queryParams.put("valueIdentifier", "Health");
		queryParams.put("additionalKeyword", "testTag");

		InputStatus response = falkonry.addFactsStream(assessment.getId(), byteArrayInputStream, queryParams);
		Assert.assertEquals(response.getAction(), "ADD_FACT_DATA");
		Assert.assertEquals(response.getStatus(), "PENDING");

		// checking ingestion status
		checkStatus(response.getId());
	}

	/**
	 * Should create datastream and add fact data in JSON format
	 *
	 * @throws Exception
	 */
	@Test
	public void createDatastreamWithJsonFacts() throws Exception {

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

		Assert.assertEquals(assessment.getName(), assessmentRequest.getName());

		// adding fact to the assessment
		File file = new File(this.getClass().getClassLoader().getResource("factsData.json").getFile());
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

		// checking ingestion status
		checkStatus(response.getId());
	}

	/**
	 * Should create datastream and add fact data in JSON format
	 *
	 * @throws Exception
	 */
	@Test
	public void createDatastreamWithJsonFactsAndRetrieveFacts() throws Exception {

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

		Assert.assertEquals(assessment.getName(), assessmentRequest.getName());

		// adding fact to the assessment
		File file = new File(this.getClass().getClassLoader().getResource("factsData.json").getFile());
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

		// checking ingestion status
		checkStatus(response.getId());

		// fetch facts from the assessment
		Map<String, String> options = new HashMap<String, String>();
		options.put("startTime", "2011-01-17T01:00:00.000Z"); // time should be in ISO format YYYY-MM-DDTHH:mm:ss.SSSZ
		options.put("endTime", "2016-08-18T01:00:00.000Z");   // time should be in ISO format YYYY-MM-DDTHH:mm:ss.SSSZ
		options.put("responseFormat", "application/json");    // can be text/csv or application/json based on your requirement
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
