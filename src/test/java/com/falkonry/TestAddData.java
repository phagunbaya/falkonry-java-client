package com.falkonry;

import com.falkonry.client.Falkonry;
import com.falkonry.helper.models.Datastream;
import com.falkonry.helper.models.TimeObject;
import com.falkonry.helper.models.Field;
import com.falkonry.helper.models.Input;
import com.falkonry.helper.models.InputStatus;
import com.falkonry.helper.models.ValueType;
import com.falkonry.helper.models.EventType;
import com.falkonry.helper.models.Datasource;
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

public class TestAddData {

	Falkonry falkonry = null;

	String host = "https://dev.falkonry.ai";
	String token = "267ummc4hjyywop631wfogkwhb6t95wr";
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
	 * Should add data to datastream in JSON format
	 * @throws Exception
	 */
	@Test
	public void addDataJson() throws Exception {
		Datastream ds = new Datastream();
		ds.setName("Test-DS-" + Math.random());
		TimeObject time = new TimeObject();
		time.setIdentifier("time");
		time.setFormat("iso_8601");
		time.setZone("GMT");

		Field field = new Field();
		field.setTime(time);
		ds.setField(field);
		Datasource dataSource = new Datasource();
		dataSource.setType("PI");
		dataSource.sethost("https://test.piserver.com/piwebapi");
		dataSource.setElementTemplateName("SampleElementTempalte");
		ds.setDatasource(dataSource);

		// Input List
		List<Input> inputList = new ArrayList<Input>();
		Input currents = new Input();
		ValueType valueType = new ValueType();
		EventType eventType = new EventType();
		currents.setName("current");
		valueType.setType("Numeric");
		eventType.setType("Samples");
		currents.setValueType(valueType);
		currents.setEventType(eventType);
		inputList.add(currents);

		Input vibration = new Input();
		vibration.setName("vibration");
		valueType.setType("Numeric");
		eventType.setType("Samples");
		vibration.setValueType(valueType);
		vibration.setEventType(eventType);
		inputList.add(vibration);

		Input state = new Input();
		state.setName("state");
		valueType.setType("Categorical");
		eventType.setType("Samples");
		state.setValueType(valueType);
		state.setEventType(eventType);
		inputList.add(state);

		ds.setInputList(inputList);

		Datastream datastream = falkonry.createDatastream(ds);
		datastreams.add(datastream);
		String data = "{\"time\" :\"2016-03-01 01:01:01\",\"Unit\":\"Unit1\", \"current\" : 12.4, \"vibration\" : 3.4, \"state\" : \"On\"}";

		Map<String, String> options = new HashMap<String, String>();
		options.put("timeIdentifier", "time");
		options.put("timeFormat", "iso_8601");
		options.put("fileFormat", "csv");
		options.put("streaming", "false");
		options.put("hasMoreData", "false");
		
		InputStatus ins = falkonry.addInput(datastream.getId(), data, options);
		Assert.assertEquals(ins.getAction(), "ADD_DATA_DATASTREAM");
		Assert.assertEquals(ins.getStatus(), "PENDING");
	}

	/**
	 * Should add data to datastream in CSV format
	 * @throws Exception
	 */
	@Test
	public void addDataCsv() throws Exception {

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
		String data = "time, tag, value\n" + "2016-03-01 01:01:01, signal1_entity1, 3.4";

		Map<String, String> options = new HashMap<String, String>();
		options.put("timeIdentifier", "time");
		options.put("timeFormat", "iso_8601");
		options.put("fileFormat", "csv");
		options.put("streaming", "false");
		options.put("hasMoreData", "false");
		
		InputStatus ins = falkonry.addInput(datastream.getId(), data, options);
		Assert.assertEquals(ins.getAction(), "ADD_DATA_DATASTREAM");
		Assert.assertEquals(ins.getStatus(), "PENDING");
	}

	@After
	public void cleanUp() throws Exception {
		Iterator<Datastream> itr = datastreams.iterator();
		while (itr.hasNext()) {
			Datastream ds = itr.next();
			falkonry.deleteDatastream(ds.getId());
		}
	}
}
