package com.falkonry;

import com.falkonry.client.Falkonry;
import com.falkonry.helper.models.Datasource;
import com.falkonry.helper.models.Datastream;
import com.falkonry.helper.models.Assessment;
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

/**
 *
 */

public class TestDatastreamLive {

	Falkonry falkonry = null;
	String host = "https://localhost:8080";
	String token = "8g462njx92e1yc0fxzrbdxqtx90hsr1s";
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
		signal.setTagIdentifier("tag");
		signal.setValueIdentifier("value");
		signal.setDelimiter("_");
		signal.setIsSignalPrefix(false);

		Datasource dataSource = new Datasource();
		dataSource.setType("STANDALONE");

		Field field = new Field();
		field.setSiganl(signal);
		field.setTime(time);
		// field.setEntityIdentifier("unit");

		ds.setDatasource(dataSource);
		ds.setField(field);

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
	 *
	 * @throws Exception
	 */
	@Test
	public void testOnDatastream() throws Exception {
		// Id of datastream which has assessment with active model available
		String datastreamId = "hk7cgt56r3yln0";

		List<Assessment> assessments = falkonry.onDatastream(datastreamId);
		Assert.assertEquals(true, assessments.size() != 0);
	}

	/**
	 *
	 * @throws Exception
	 */
	@Test
	public void testOffDatastream() throws Exception {
		// Id of datastream which has assessment with active model available
		String datastreamId = "hk7cgt56r3yln0";

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
