package com.falkonry;

/*!
 * falkonry-java-client
 * Copyright(c) 2017 Falkonry Inc
 * MIT Licensed
 */

import com.falkonry.client.Falkonry;
import com.falkonry.helper.models.Datasource;
import com.falkonry.helper.models.Datastream;
import com.falkonry.helper.models.EventType;
import com.falkonry.helper.models.Field;
import com.falkonry.helper.models.Input;
import com.falkonry.helper.models.TimeObject;
import com.falkonry.helper.models.ValueType;
import com.falkonry.helper.models.Signal;
import org.junit.*;
import java.util.*;

@Ignore
public class TestCreateDatastream {

	Falkonry falkonry = null;
	String host = "https://localhost:8080";
	// String token = "auth-token";
	String token = "npp766l2hghmhrc7ygrbldjnkb9rn7mg";
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
	 * Should create datastream
	 * 
	 * @throws Exception
	 */
	@Test
	public void createDatastream() throws Exception {
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
		// field.setEntityIdentifier("entity");

		field.setSignal(signal);
		field.setTime(time);
		ds.setField(field);
		Datasource dataSource = new Datasource();
		dataSource.setType("STANDALONE");
		ds.setDatasource(dataSource);
		Datastream datastream = falkonry.createDatastream(ds);
		datastreams.add(datastream);

		Assert.assertEquals(ds.getName(), datastream.getName());
		Assert.assertNotEquals(null, datastream.getId());

		Assert.assertEquals(datastream.getField().getTime().getFormat(), ds.getField().getTime().getFormat());
		Assert.assertEquals(datastream.getField().getTime().getIdentifier(), ds.getField().getTime().getIdentifier());
		Assert.assertEquals(datastream.getField().getTime().getZone(), ds.getField().getTime().getZone());

		Assert.assertEquals(datastream.getDatasource().getType(), ds.getDatasource().getType());
		Assert.assertEquals(datastream.getField().getSignal().getValueIdentifier(),
				ds.getField().getSignal().getValueIdentifier());
	}
	
	
	/**
	 * Should create batch datastream
	 * 
	 * @throws Exception
	 */
	@Test
	public void createBatchDatastream() throws Exception {
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
	    field.setBatchIdentifier("batch");

		field.setSignal(signal);
		field.setTime(time);
		ds.setField(field);
		Datasource dataSource = new Datasource();
		dataSource.setType("STANDALONE");
		ds.setDatasource(dataSource);
		Datastream datastream = falkonry.createDatastream(ds);
		datastreams.add(datastream);

		Assert.assertEquals(ds.getName(), datastream.getName());
		Assert.assertNotEquals(null, datastream.getId());

		Assert.assertEquals(datastream.getField().getTime().getFormat(), ds.getField().getTime().getFormat());
		Assert.assertEquals(datastream.getField().getTime().getIdentifier(), ds.getField().getTime().getIdentifier());
		Assert.assertEquals(datastream.getField().getTime().getZone(), ds.getField().getTime().getZone());

		Assert.assertEquals(datastream.getDatasource().getType(), ds.getDatasource().getType());
		Assert.assertEquals(datastream.getField().getSignal().getValueIdentifier(),
				ds.getField().getSignal().getValueIdentifier());
	}

	
	/**
	 * Should get datastream list
	 * 
	 * @throws Exception
	 */
	@Test
	public void getDatastreamList() throws Exception {
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
		// field.setEntityIdentifier("entity");

		field.setSignal(signal);
		field.setTime(time);
		ds.setField(field);
		Datasource dataSource = new Datasource();
		dataSource.setType("STANDALONE");
		ds.setDatasource(dataSource);

		Datastream datastream = falkonry.createDatastream(ds);
		datastreams.add(datastream);

		Assert.assertEquals(ds.getName(), datastream.getName());
		Assert.assertNotEquals(null, datastream.getId());

		Assert.assertEquals(datastream.getField().getTime().getFormat(), ds.getField().getTime().getFormat());
		Assert.assertEquals(datastream.getField().getTime().getIdentifier(), ds.getField().getTime().getIdentifier());
		Assert.assertEquals(datastream.getField().getTime().getZone(), ds.getField().getTime().getZone());

		Assert.assertEquals(datastream.getDatasource().getType(), ds.getDatasource().getType());
		Assert.assertEquals(datastream.getField().getSignal().getValueIdentifier(),
				ds.getField().getSignal().getValueIdentifier());

		List<Datastream> datastream1 = falkonry.getDatastreams();
		Assert.assertEquals(true, datastream1.size() != 0);

	}

	/**
	 * Should get datstream by ID
	 * 
	 * @throws Exception
	 */
	@Test
	public void getDatastreamById() throws Exception {
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
		// field.setEntityIdentifier("entity");

		field.setSignal(signal);
		field.setTime(time);
		ds.setField(field);
		Datasource dataSource = new Datasource();
		dataSource.setType("STANDALONE");
		ds.setDatasource(dataSource);
		Datastream datastream = falkonry.createDatastream(ds);
		datastreams.add(datastream);

		Assert.assertEquals(ds.getName(), datastream.getName());
		Assert.assertNotEquals(null, datastream.getId());

		Assert.assertEquals(datastream.getField().getTime().getFormat(), ds.getField().getTime().getFormat());
		Assert.assertEquals(datastream.getField().getTime().getIdentifier(), ds.getField().getTime().getIdentifier());
		Assert.assertEquals(datastream.getField().getTime().getZone(), ds.getField().getTime().getZone());

		Assert.assertEquals(datastream.getDatasource().getType(), ds.getDatasource().getType());

		Assert.assertEquals(datastream.getField().getSignal().getValueIdentifier(),
				ds.getField().getSignal().getValueIdentifier());

		Datastream datastream1 = falkonry.getDatastream(datastream.getId());
		Assert.assertEquals(datastream1.getName(), datastream.getName());
		Assert.assertEquals(datastream1.getId(), datastream.getId());

		Assert.assertEquals(datastream.getField().getTime().getFormat(), datastream1.getField().getTime().getFormat());
		Assert.assertEquals(datastream.getField().getTime().getIdentifier(),
				datastream1.getField().getTime().getIdentifier());
		Assert.assertEquals(datastream.getField().getTime().getZone(), datastream1.getField().getTime().getZone());

		Assert.assertEquals(datastream.getDatasource().getType(), datastream1.getDatasource().getType());

		Assert.assertEquals(datastream.getField().getSignal().getValueIdentifier(),
				datastream1.getField().getSignal().getValueIdentifier());

	}

	// @Test

	/**
	 * Should update datastream
	 * 
	 * @throws Exception
	 */
	public void updateDatastream() throws Exception {
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
		// field.setEntityIdentifier("entity");

		field.setSignal(signal);
		field.setTime(time);
		ds.setField(field);
		Datasource dataSource = new Datasource();
		dataSource.setType("STANDALONE");
		ds.setDatasource(dataSource);

		Datastream datastream = falkonry.createDatastream(ds);
		datastreams.add(datastream);

		Assert.assertEquals(ds.getName(), datastream.getName());
		Assert.assertNotEquals(null, datastream.getId());

		Assert.assertEquals(datastream.getField().getTime().getFormat(), ds.getField().getTime().getFormat());
		Assert.assertEquals(datastream.getField().getTime().getIdentifier(), ds.getField().getTime().getIdentifier());
		Assert.assertEquals(datastream.getField().getTime().getZone(), ds.getField().getTime().getZone());

		Assert.assertEquals(datastream.getDatasource().getType(), ds.getDatasource().getType());

		Assert.assertEquals(datastream.getField().getSignal().getValueIdentifier(),
				ds.getField().getSignal().getValueIdentifier());

		datastream.setName("Test-DS-" + Math.random());
		Datastream datastream1 = falkonry.updateDatastream(datastream);
		Assert.assertEquals(datastream1.getName(), datastream.getName());
		Assert.assertEquals(datastream1.getId(), datastream.getId());

		Assert.assertEquals(datastream.getField().getTime().getFormat(), datastream1.getField().getTime().getFormat());
		Assert.assertEquals(datastream.getField().getTime().getIdentifier(),
				datastream1.getField().getTime().getIdentifier());
		Assert.assertEquals(datastream.getField().getTime().getZone(), datastream1.getField().getTime().getZone());

		Assert.assertEquals(datastream.getDatasource().getType(), datastream1.getDatasource().getType());

		Assert.assertEquals(datastream.getField().getSignal().getValueIdentifier(),
				datastream1.getField().getSignal().getValueIdentifier());

	}

	/**
	 * Should create wide format datastream
	 * 
	 * @throws Exception
	 */
	@Test
	public void createWideDatastream() throws Exception {

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
		field.setEntityIdentifier("unit");

		field.setSignal(signal);
		field.setTime(time);
		ds.setField(field);
		Datasource dataSource = new Datasource();
		dataSource.setType("STANDALONE");
		ds.setDatasource(dataSource);

		Datastream datastream = falkonry.createDatastream(ds);
		datastreams.add(datastream);

		Assert.assertEquals(ds.getName(), datastream.getName());
		Assert.assertNotEquals(null, datastream.getId());

		Assert.assertEquals(datastream.getField().getTime().getFormat(), ds.getField().getTime().getFormat());
		Assert.assertEquals(datastream.getField().getTime().getIdentifier(), ds.getField().getTime().getIdentifier());
		Assert.assertEquals(datastream.getField().getTime().getZone(), ds.getField().getTime().getZone());

		Assert.assertEquals(datastream.getDatasource().getType(), ds.getDatasource().getType());
	}

	/**
	 * Should create narrow format datastream and add JSON format data
	 * 
	 * @throws Exception
	 */
	@Test
	public void createDatastreamWithJsonData() throws Exception {

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
		// field.setEntityIdentifier("entity");

		field.setSignal(signal);
		field.setTime(time);
		ds.setField(field);
		Datasource dataSource = new Datasource();
		dataSource.setType("STANDALONE");
		ds.setDatasource(dataSource);

		Datastream datastream = falkonry.createDatastream(ds);
		datastreams.add(datastream);

		String data = "{\"time\" : \"2016-03-01 01:01:01\", \"signal\" : \"signal1\", \"entity\" : \"entity1\", \"value\" : 3.4}";
		Map<String, String> options = new HashMap<String, String>();
		options.put("timeIdentifier", "time");
		options.put("timeFormat", "YYYY-MM-DD HH:mm:ss");
		options.put("timeZone", time.getZone());
		options.put("signalIdentifier", "signal");
		options.put("entityIdentifier", "entity");
		options.put("valueIdentifier", "value");
		options.put("fileFormat", "csv");
		options.put("streaming", "false");
		options.put("hasMoreData", "false");
		falkonry.addInput(datastream.getId(), data, options);

		Assert.assertEquals(ds.getName(), datastream.getName());
		Assert.assertNotEquals(null, datastream.getId());

		Assert.assertEquals(datastream.getField().getTime().getFormat(), ds.getField().getTime().getFormat());
		Assert.assertEquals(datastream.getField().getTime().getIdentifier(), ds.getField().getTime().getIdentifier());
		Assert.assertEquals(datastream.getField().getTime().getZone(), ds.getField().getTime().getZone());

		Assert.assertEquals(datastream.getDatasource().getType(), ds.getDatasource().getType());

		Assert.assertEquals(datastream.getField().getSignal().getValueIdentifier(),
				ds.getField().getSignal().getValueIdentifier());

	}

	/**
	 * Should create wide format datastream and add JSON format data
	 * 
	 * @throws Exception
	 */
	@Test
	public void createDatastreamWithWideJsonData() throws Exception {

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
		// field.setEntityIdentifier("entity");

		field.setSignal(signal);
		field.setTime(time);
		ds.setField(field);
		Datasource dataSource = new Datasource();
		dataSource.setType("STANDALONE");
		ds.setDatasource(dataSource);

		Datastream datastream = falkonry.createDatastream(ds);
		datastreams.add(datastream);

		String data = "{\"time\":1467729675422,\"entity\":\"entity1\",\"signal1\":41.11,\"signal2\":82.34,\"signal3\":74.63,\"signal4\":4.8,\"signal5\":72.01}";
		Map<String, String> options = new HashMap<String, String>();
		options.put("fileFormat", "json");
		options.put("streaming", "false");
		options.put("hasMoreData", "false");
		falkonry.addInput(datastream.getId(), data, options);

		Assert.assertEquals(ds.getName(), datastream.getName());
		Assert.assertNotEquals(null, datastream.getId());

		Assert.assertEquals(datastream.getField().getTime().getFormat(), ds.getField().getTime().getFormat());
		Assert.assertEquals(datastream.getField().getTime().getIdentifier(), ds.getField().getTime().getIdentifier());
		Assert.assertEquals(datastream.getField().getTime().getZone(), ds.getField().getTime().getZone());

		Assert.assertEquals(datastream.getDatasource().getType(), ds.getDatasource().getType());

		Assert.assertEquals(datastream.getField().getSignal().getValueIdentifier(),
				ds.getField().getSignal().getValueIdentifier());

	}

	/**
	 * Should create narrow format datastream and add CSV format data
	 * 
	 * @throws Exception
	 */
	@Test
	public void createDatastreamWithCsvData() throws Exception {

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
		// field.setEntityIdentifier("entity");

		field.setSignal(signal);
		field.setTime(time);
		ds.setField(field);
		Datasource dataSource = new Datasource();
		dataSource.setType("STANDALONE");
		ds.setDatasource(dataSource);

		Datastream datastream = falkonry.createDatastream(ds);
		datastreams.add(datastream);

		String data = "time,signal,entity,value\n2016-03-01 01:01:01,signal1,entity1,3.4";
		Map<String, String> options = new HashMap<String, String>();
		options.put("timeIdentifier", "time");
		options.put("timeFormat", "YYYY-MM-DD HH:mm:ss");
		options.put("timeZone", time.getZone());
		options.put("signalIdentifier", "signal");
		options.put("entityIdentifier", "entity");
		options.put("valueIdentifier", "value");
		options.put("fileFormat", "csv");
		options.put("streaming", "false");
		options.put("hasMoreData", "false");
		falkonry.addInput(datastream.getId(), data, options);

		Assert.assertEquals(ds.getName(), datastream.getName());
		Assert.assertNotEquals(null, datastream.getId());

		Assert.assertEquals(datastream.getField().getTime().getFormat(), ds.getField().getTime().getFormat());
		Assert.assertEquals(datastream.getField().getTime().getIdentifier(), ds.getField().getTime().getIdentifier());
		Assert.assertEquals(datastream.getField().getTime().getZone(), ds.getField().getTime().getZone());

		Assert.assertEquals(datastream.getDatasource().getType(), ds.getDatasource().getType());

		Assert.assertEquals(datastream.getField().getSignal().getValueIdentifier(),
				ds.getField().getSignal().getValueIdentifier());

	}

	/**
	 * Should create wide format datastream and add CSV format data
	 * 
	 * @throws Exception
	 */
	@Test
	public void createDatastreamWithWideCsvData() throws Exception {

		Datastream ds = new Datastream();
		ds.setName("Test-DS-" + Math.random());

		TimeObject time = new TimeObject();
		time.setIdentifier("time");
		time.setFormat("millis");
		time.setZone("GMT");

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

		Datasource dataSource = new Datasource();
		dataSource.setType("STANDALONE");

		field.setTime(time);
		// field.setEntityIdentifier("unit");

		ds.setDatasource(dataSource);
		ds.setField(field);

		Datastream datastream = falkonry.createDatastream(ds);
		datastreams.add(datastream);

		String data = "time,entity,signal1,signal2,signal3,signal4,signal5\n"
				+ "1467729675422,entity1,41.11,82.34,74.63,4.8,72.01";
		Map<String, String> options = new HashMap<String, String>();
		options.put("fileFormat", "csv");
		options.put("streaming", "false");
		options.put("hasMoreData", "false");
		falkonry.addInput(datastream.getId(), data, options);

		Assert.assertEquals(ds.getName(), datastream.getName());
		Assert.assertNotEquals(null, datastream.getId());

		Assert.assertEquals(datastream.getField().getTime().getFormat(), ds.getField().getTime().getFormat());
		Assert.assertEquals(datastream.getField().getTime().getIdentifier(), ds.getField().getTime().getIdentifier());
		Assert.assertEquals(datastream.getField().getTime().getZone(), ds.getField().getTime().getZone());
		Assert.assertEquals(datastream.getDatasource().getType(), ds.getDatasource().getType());
		Assert.assertEquals(datastream.getInputList().size(), inputList.size());

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
