package com.falkonry;

/*!
 * falkonry-java-client
 * Copyright(c) 2017 Falkonry Inc
 * MIT Licensed
 */

import com.falkonry.client.Falkonry;
import com.falkonry.helper.models.Datasource;
import com.falkonry.helper.models.Datastream;
import com.falkonry.helper.models.Assessment;
import com.falkonry.helper.models.Field;
import com.falkonry.helper.models.TimeObject;
import com.falkonry.helper.models.Signal;
import org.junit.*;
import java.util.*;

public class TestDatastreamLive {

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
	 * Should get exception when turning on the datastream
	 * 
	 * @throws Exception
	 */
	@Test
	public void testOnDatastreamException() throws Exception {
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

		try {
			List<Assessment> assessments = falkonry.onDatastream(datastream.getId());
			Assert.assertEquals(assessments.size() != 0, false);
		} catch (Exception e) {
			Assert.assertEquals(e.getMessage(), "{\"message\":\"Active model is not assigned in any assessment\"}");
		}
	}

	/**
	 * Should turn on the datastream
	 * 
	 * @throws Exception
	 */
	@Ignore
	@Test
	public void testOnDatastream() throws Exception {
		// Id of datastream which has assessment with active model available
		String datastreamId = "cptv9ldj4n9clt";

		List<Assessment> assessments = falkonry.onDatastream(datastreamId);
		Assert.assertEquals(true, assessments.size() != 0);
	}

	/**
	 * Should turn off the datastream
	 * 
	 * @throws Exception
	 */
	@Ignore
	@Test
	public void testOffDatastream() throws Exception {
		// Id of datastream which has assessment with active model available
		String datastreamId = "cptv9ldj4n9clt";

		List<Assessment> assessments1 = falkonry.onDatastream(datastreamId);
		Assert.assertEquals(true, assessments1.size() != 0);

		List<Assessment> assessments2 = falkonry.offDatastream(datastreamId);
		Assert.assertEquals(true, assessments2.size() != 0);

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
