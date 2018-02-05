package com.falkonry;

/*!
 * falkonry-java-client
 * Copyright(c) 2017 Falkonry Inc
 * MIT Licensed
 */

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

public class TestCreateAssessment {

	Falkonry falkonry = null;
	String host = System.getenv("FALKONRY_HOST_URL");
	String token = System.getenv("FALKONRY_TOKEN");

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
	 * Should create assessment
	 * 
	 * @throws Exception
	 */
	@Test
	public void createAssessment() throws Exception {

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
		Assert.assertEquals(assessment.getDatastream(), assessmentRequest.getDatastream());
		Assert.assertEquals(assessment.getRate(), assessmentRequest.getAssessmentRate());

	}

	/**
	 * Should delete assessment
	 * 
	 * @throws Exception
	 */
	@Test
	public void deleteAssessment() throws Exception {

		// creating assessment
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

		AssessmentRequest assessmentRequest = new AssessmentRequest();
		String name = "Test-AS-" + Math.random();
		assessmentRequest.setName(name);
		assessmentRequest.setDatastream(datastream.getId());
		assessmentRequest.setAssessmentRate("PT1S");
		Assessment assessment = falkonry.createAssessment(assessmentRequest);

		Assert.assertEquals(assessment.getName(), assessmentRequest.getName());
		Assert.assertEquals(assessment.getRate(), assessmentRequest.getAssessmentRate());

		// deleting assessment
		falkonry.deleteAssessment(assessment.getId());

	}

	/**
	 * Should get assessment list
	 * 
	 * @throws Exception
	 */
	@Test
	public void getAssessmentList() throws Exception {

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
		Assert.assertEquals(assessment.getRate(), assessmentRequest.getAssessmentRate());

		List<Assessment> assessmentList = falkonry.getAssessments();
		Assert.assertEquals(assessmentList.size() > 1, true);
	}

	/**
	 * Should get assessment by ID
	 * 
	 * @throws Exception
	 */
	@Test
	public void getAssessmentById() throws Exception {

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
		Assert.assertEquals(assessment.getRate(), assessmentRequest.getAssessmentRate());

		// fetching assessment
		Assessment fetched_assessment = falkonry.getAssessment(assessment.getId());
		Assert.assertEquals(fetched_assessment.getName(), assessment.getName());
		Assert.assertEquals(fetched_assessment.getRate(), assessment.getRate());
		Assert.assertEquals(fetched_assessment.getDatastream(), assessment.getDatastream());
		Assert.assertEquals(fetched_assessment.getAprioriConditionList().size(), 0);

	}

	/**
	 * Should update assessment
	 * 
	 * @throws Exception
	 */
	@Test
	public void updateAssessment() throws Exception {

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
		Assert.assertEquals(assessment.getRate(), assessmentRequest.getAssessmentRate());

		// updating name of the assessment
		name = "Test-AS-" + Math.random();
		assessmentRequest.setName(name);
		Assessment assessmentUpd = falkonry.updateAssessment(assessmentRequest);
		Assert.assertEquals(assessmentUpd.getName(), assessment.getName());
		Assert.assertEquals(assessmentUpd.getRate(), assessment.getRate());
		Assert.assertEquals(assessmentUpd.getDatastream(), assessment.getDatastream());

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
