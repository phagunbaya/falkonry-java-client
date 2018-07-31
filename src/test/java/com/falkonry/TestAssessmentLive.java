package com.falkonry;

/*!
 * falkonry-java-client
 * Copyright(c) 2017-2018 Falkonry Inc
 * MIT Licensed
 */

import com.falkonry.client.Falkonry;
import com.falkonry.helper.models.Datasource;
import com.falkonry.helper.models.Datastream;
import com.falkonry.helper.models.Assessment;
import com.falkonry.helper.models.AssessmentRequest;
import com.falkonry.helper.models.Field;
import com.falkonry.helper.models.TimeObject;
import com.falkonry.helper.models.Signal;
import org.junit.*;
import java.util.*;

public class TestAssessmentLive {

	Falkonry falkonry = null;
	String host = System.getenv("FALKONRY_HOST_URL");
	String token = System.getenv("FALKONRY_TOKEN");
	String datastreamId = System.getenv("FALKONRY_DATASTREAM_SLIDING_ID");
	String assessmentId = System.getenv("FALKONRY_ASSESSMENT_SLIDING_ID");
	
	List<Datastream> datastreams = new ArrayList<Datastream>();

	/**
	 *
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		falkonry = new Falkonry(host, token);
	}

	/**
	 * Should get exception when turning on the assessment without active model
	 * 
	 * @throws Exception
	 */
	@Test
	public void testOnAssessmentException() throws Exception {
		// creating datastream and assessment
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
		Assessment assessmentObj = falkonry.createAssessment(assessmentRequest);
		
		try {
			Assessment assessment = falkonry.onAssessment(assessmentObj.getId());
			Assert.assertEquals(assessment.getId() == assessmentObj.getId(), false);
		} catch (Exception e) {
			Assert.assertEquals(e.getMessage(), "{\"message\":\"No Active model assigned in Assessment: " + assessmentObj.getName() + "\"}");
		}
	}
	
	
	/**
	 * Should get live monitoring status of assessment
	 * 
	 * @throws Exception
	 */
	@Test
	public void testLiveMonitoringStatus() throws Exception {
		Assessment assessment = falkonry.getAssessment(assessmentId);
		Assert.assertEquals(assessment.getLive(), "OFF");
	}

	/**
	 * Should turn on and off the assessment
	 * 
	 * @throws Exception
	 */
	@Test
	public void testOnOffAssessment() throws Exception {
		// Here assessment should have an active model
		Assessment assessment1 = falkonry.onAssessment(assessmentId);
		Assert.assertEquals(assessment1.getId(), assessmentId);
		
		Thread.sleep(30000);
		
		Assessment assessment2 = falkonry.offAssessment(assessmentId);
		Assert.assertEquals(assessment2.getId(), assessmentId);
		
		Thread.sleep(30000);

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
