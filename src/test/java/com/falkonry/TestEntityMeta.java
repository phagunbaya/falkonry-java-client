package com.falkonry;

/*!
 * falkonry-java-client
 * Copyright(c) 2017 Falkonry Inc
 * MIT Licensed
 */

import com.falkonry.client.Falkonry;
import com.falkonry.helper.models.Datasource;
import com.falkonry.helper.models.Datastream;
import com.falkonry.helper.models.EntityMeta;
import com.falkonry.helper.models.EntityMetaRequest;
import com.falkonry.helper.models.Field;
import com.falkonry.helper.models.Signal;
import com.falkonry.helper.models.TimeObject;
import org.junit.*;
import java.util.*;

@Ignore
public class TestEntityMeta {

	Falkonry falkonry = null;
	String host = "https://localhost:8080";
	String token = "auth-token";
	

	List<Datastream> datastreams = new ArrayList<Datastream>();
	List<EntityMetaRequest> entityMetaRequests = new ArrayList<EntityMetaRequest>();

	/**
	 *
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		falkonry = new Falkonry(host, token);
	}

	/**
	 * Should create entity meta
	 * 
	 * @throws Exception
	 */
	@Test
	public void createEntityMeta() throws Exception {

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
		String data = "time,entity,signal,value\n";
		for (int i = 0; i < 10; i++) {
			data += "2016-03-01 01:01:0" + i + ",entity1,signal1,3.4\n";
		}
		Map<String, String> options = new HashMap<String, String>();
		
		options.put("fileFormat", "csv");
		options.put("streaming", "false");
		options.put("hasMoreData", "false");
		falkonry.addInput(datastream.getId(), data, options);

		datastream = falkonry.getDatastream(datastream.getId());
		EntityMetaRequest entityMetaRequest = new EntityMetaRequest();
		entityMetaRequest.setSourceId("entity1");
		entityMetaRequest.setLabel("UNIT1");
		entityMetaRequest.setPath("entity1/UNIT1");
		entityMetaRequests.add(entityMetaRequest);
		List<EntityMeta> entityMetas = falkonry.postEntityMeta(entityMetaRequests, datastream.getId());
		Assert.assertEquals(true, entityMetas.size() == 1);

		List<EntityMeta> entityMetas1 = falkonry.getEntityMeta(datastream.getId());
		Assert.assertEquals(true, entityMetas1.size() == 1);
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
