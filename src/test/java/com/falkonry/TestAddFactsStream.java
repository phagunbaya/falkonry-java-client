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

public class TestAddFactsStream {

	Falkonry falkonry = null;
	String host = "https://localhost:8080";
	String token = "lmm3orvm1yaa4j1y5b78i8f870fhon6z";
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

		String data = "time, tag, value\n2016-03-01 01:01:01, entity1_signal1, 3.4";
		falkonry.addInput(datastream.getId(), data, options);

		File file = new File("res/factsData.csv");
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(FileUtils.readFileToByteArray(file));
		
		InputStatus response = falkonry.addFactsStream(assessment.getId(), byteArrayInputStream, null);
		Assert.assertEquals(response.getAction(), "ADD_FACT_DATA");
		Assert.assertEquals(response.getStatus(), "PENDING");
	}

	/**
	 * Should create datastream and add fact data in JSON format
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
		Assert.assertEquals(assessment.getName(), assessmentRequest.getName());

		Map<String, String> options = new HashMap<String, String>();

		String data = "{\"time\" : \"2016-03-01 01:01:01\", \"tag\" : \"signal1_entity1\", \"value\" : 3.4}";
		falkonry.addInput(datastream.getId(), data, options);

		File file = new File("res/factsData.json");
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(FileUtils.readFileToByteArray(file));
		
		InputStatus response = falkonry.addFactsStream(assessment.getId(), byteArrayInputStream, null);
		Assert.assertEquals(response.getAction(), "ADD_FACT_DATA");
		Assert.assertEquals(response.getStatus(), "PENDING");
	}
	
	/**
	 * Should create datastream and add fact data in JSON format
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
		Assert.assertEquals(assessment.getName(), assessmentRequest.getName());

		Map<String, String> options = new HashMap<String, String>();

		String data = "{\"time\" : \"2016-03-01 01:01:01\", \"tag\" : \"signal1_entity1\", \"value\" : 3.4}";
		falkonry.addInput(datastream.getId(), data, options);

		File file = new File("res/factsData.json");
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(FileUtils.readFileToByteArray(file));
		
		InputStatus response = falkonry.addFactsStream(assessment.getId(), byteArrayInputStream, null);
		Assert.assertEquals(response.getAction(), "ADD_FACT_DATA");
		Assert.assertEquals(response.getStatus(), "PENDING");
		
		String asmtId = "743cveg32hkwl2";
		Assessment asmtResponse = falkonry.getAssessment(asmtId);
				
		// Get Facts
		HttpResponseFormat factsResponse = falkonry.getFactsData(asmtResponse, options);
		Assert.assertEquals(factsResponse.getResponse().length()>0,true);
		
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
