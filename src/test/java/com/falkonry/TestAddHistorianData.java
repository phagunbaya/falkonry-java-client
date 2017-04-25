package com.falkonry;

import com.falkonry.client.Falkonry;
import com.falkonry.helper.models.Assessment;
import com.falkonry.helper.models.AssessmentRequest;
import com.falkonry.helper.models.Datastream;
import com.falkonry.helper.models.TimeObject;
import com.falkonry.helper.models.Field;
import com.falkonry.helper.models.Datasource;
import com.falkonry.helper.models.HttpResponseFormat;
import com.falkonry.helper.models.InputStatus;
import com.falkonry.helper.models.Signal;
import com.falkonry.helper.models.TrackerReponse;
import org.junit.*;

import java.util.*;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.json.JSONObject;

/*!
 * falkonry-java-client
 * Copyright(c) 2016 Falkonry Inc
 * MIT Licensed
 */

/**
 *
 * 
 */

public class TestAddHistorianData {

	Falkonry falkonry = null;

	String host = "https://localhost:8080";
	String token = "267ummc4hjyywop631wfogkwhb6t95wr";
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
	 * Should create narrow format datastream and add data in CSV format
	 * @throws Exception
	 */
	@Test
	public void AddDataNarrowFormatCsvForLearning() throws Exception {
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
		String data = "time, tag, value \n"
				+ "2016-05-05T12:00:00Z, Unit1_current, 12.4 \n 2016-03-01 01:01:01, Unit1_vibration, 20.4";

		Map<String, String> options = new HashMap<String, String>();
		options.put("timeIdentifier", "time");
		options.put("timeFormat", "iso_8601");
		options.put("fileFormat", "csv");
		options.put("streaming", "false");
		options.put("hasMoreData", "false");
		
		InputStatus inputStatus = falkonry.addInput(datastream.getId(), data, options);
		Assert.assertEquals(inputStatus.getAction(), "ADD_DATA_DATASTREAM");
		Assert.assertEquals(inputStatus.getStatus(), "PENDING");
	}

	/**
	 * Should create narrow format datastream and add stream data in CSV format
	 * @throws Exception
	 */
	@Test
	public void AddDataNarrowFormatCsvForStreaming() throws Exception {

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
		String data = "time, tag, value \n"
				+ "2016-05-05T12:00:00Z, Unit1_current, 12.4 \n 2016-03-01 01:01:01, Unit1_vibration, 20.4";

		Map<String, String> options = new HashMap<String, String>();
		options.put("timeIdentifier", "time");
		options.put("timeFormat", "iso_8601");
		options.put("fileFormat", "csv");
		options.put("streaming", "true");
		options.put("hasMoreData", "false");
		InputStatus inputStatus = falkonry.addInput(datastream.getId(), data, options);
		Assert.assertEquals(inputStatus.getAction(), "ADD_DATA_DATASTREAM");
		Assert.assertEquals(inputStatus.getStatus(), "PENDING");
	}

	// Set assessment if before calling GetHistoricalOutput

	/**
	 * Should get historian output data
	 * @throws Exception
	 */
	@Test
	public void TestHistoricalOutput() throws Exception {

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
		signal.setIsSignalPrefix(true);

		Field field = new Field();
		field.setSiganl(signal);
		field.setTime(time);
		// field.setEntityIdentifier("unit");
		ds.setField(field);
		Datasource dataSource = new Datasource();
		dataSource.setType("PI");
		dataSource.sethost("https://test.piserver.com/piwebapi");
		dataSource.setElementTemplateName("SampleElementTempalte");

		ds.setDatasource(dataSource);

		Datastream datastream = falkonry.createDatastream(ds);
		datastreams.add(datastream);

		AssessmentRequest assessmentRequest = new AssessmentRequest();
		String name = "Test-AS-" + Math.random();
		assessmentRequest.setName(name);
		assessmentRequest.setDatastream(datastream.getId());
		assessmentRequest.setAssessmentRate("PT0S");
		Assessment assessment = falkonry.createAssessment(assessmentRequest);
		assessments.add(assessment);

		// assessment.id = "lqv606xtcxnlca";
		// Go to Falkonry UI and run a model revision
		// Fetch Historical output data for given assessment, startTime ,
		// endtime
		Map<String, String> options = new HashMap<String, String>();
		options.put("startTime", "2011-05-02T02:53:00Z"); // in the format
															// YYYY-MM-DDTHH:mm:ss.SSSZ
		options.put("endTime", "2011-06-01T02:53:00Z"); // in the format
														// YYYY-MM-DDTHH:mm:ss.SSSZ
		options.put("responseFormat", "application/json"); // also avaibale
															// options 1.
															// text/csv 2.
															// application/json

		// assessment.setId("wpyred1glh6c5r");
		Assessment asmt = falkonry.getAssessment("x1y4ob0ex5mmy1");
		HttpResponseFormat httpResponse = falkonry.getHistoricalOutput(asmt, options);

		// If data is not readily available then, a tracker id will be sent with
		// 202 status code. While falkonry will genrate ouptut data
		// Client should do timely pooling on the using same method, sending
		// tracker id (__id) in the query params
		// Once data is available server will response with 200 status code and
		// data in json/csv format.
		if (httpResponse.getStatusCode() == 202) {
			ObjectMapper mapper = new ObjectMapper();
			String trackerResponse_json = httpResponse.getResponse();

			InputStatus trackerResponse = mapper.readValue(trackerResponse_json, InputStatus.class);
			// get id from the tracker
			String id = trackerResponse.getId();
			// string __id = "phzpfmvwsgiy7ojc";

			// use this tracker for checking the status of the process.
			Map<String, String> options1 = new HashMap<String, String>();

			options1.put("trackerId", id);
			options1.put("responseFormat", "application/json");

			httpResponse = falkonry.getHistoricalOutput(asmt, options);

			// if status is 202 call the same request again
			// if status is 200, output data will be present in
			// httpResponse.response field
		}
		if (httpResponse.getStatusCode() > 400) {
			// Some Error has occurred. Please httpResponse.response for detail
			// message
		}
	}

	/**
	 * @throws Exception
	 * @return void
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
