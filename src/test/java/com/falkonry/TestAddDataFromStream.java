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

public class TestAddDataFromStream {

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
	 * Should add narrow data to datastream in stream JSON format
	 * 
	 * @throws Exception
	 */
	@Test
	public void addDataJsonStream() throws Exception {

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

		Map<String, String> options = new HashMap<String, String>();

		File file = new File(this.getClass().getClassLoader().getResource("data.json").getFile());

		options.put("timeIdentifier", "time");
		options.put("timeFormat", "YYYY-MM-DD HH:mm:ss");
		options.put("timeZone", time.getZone());
		options.put("signalIdentifier", "signal");
		options.put("entityIdentifier", "entity");
		options.put("valueIdentifier", "value");
		options.put("fileFormat", "csv");
		options.put("streaming", "false");
		options.put("hasMoreData", "false");
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(FileUtils.readFileToByteArray(file));
		InputStatus inputStatus = falkonry.addInputStream(datastream.getId(), byteArrayInputStream, options);
		Assert.assertEquals(inputStatus.getAction(), "ADD_DATA_DATASTREAM");
		Assert.assertEquals(inputStatus.getStatus(), "PENDING");

		// checking ingestion status
		checkStatus(inputStatus.getId());

	}
	
	/**
	 * Should add narrow data to batch datastream in stream JSON format
	 * 
	 * @throws Exception
	 */
	@Test
	public void addDataJsonStreamBatchDatastream() throws Exception {

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
		field.setBatchIdentifier("entity");

		field.setSignal(signal);
		field.setTime(time);
		ds.setField(field);
		Datasource dataSource = new Datasource();
		dataSource.setType("STANDALONE");
		ds.setDatasource(dataSource);
		Datastream datastream = falkonry.createDatastream(ds);
		datastreams.add(datastream);

		Map<String, String> options = new HashMap<String, String>();

		File file = new File("res/dataBatch.json");

		options.put("timeIdentifier", "time");
		options.put("timeFormat", "YYYY-MM-DD HH:mm:ss");
		options.put("timeZone", time.getZone());
		options.put("signalIdentifier", "signal");
		options.put("entityIdentifier", "entity");
		options.put("valueIdentifier", "value");
		options.put("batchIdentifier", "batch");
		options.put("fileFormat", "csv");
		options.put("streaming", "false");
		options.put("hasMoreData", "false");
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(FileUtils.readFileToByteArray(file));
		InputStatus inputStatus = falkonry.addInputStream(datastream.getId(), byteArrayInputStream, options);
		Assert.assertEquals(inputStatus.getAction(), "ADD_DATA_DATASTREAM");
		Assert.assertEquals(inputStatus.getStatus(), "PENDING");
		Datastream datastream1 = falkonry.getDatastream(datastream.getId());
		Assert.assertEquals(datastream1.getId(), datastream.getId());
		Assert.assertEquals(datastream1.getName(), datastream.getName());
		Field field1 = datastream1.getField();
		Signal signal1 = field1.getSignal();

		Assert.assertEquals(signal1.getValueIdentifier(), signal.getValueIdentifier());

	}

	
	/**
	 * Should add wide data to datastream in stream JSON format
	 * 
	 * @throws Exception
	 */
	@Test
	public void addWideDataJsonStream() throws Exception {

		Datastream ds = new Datastream();
		ds.setName("Test-DS2-" + Math.random());
		TimeObject time = new TimeObject();
		time.setIdentifier("time");
		time.setFormat("millis");
		time.setZone("GMT");

		Datasource dataSource = new Datasource();
		dataSource.setType("STANDALONE");

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

		ds.setDatasource(dataSource);
		ds.setField(field);

		Datastream datastream = falkonry.createDatastream(ds);
		datastreams.add(datastream);

		Map<String, String> options = new HashMap<String, String>();

		File file = new File(this.getClass().getClassLoader().getResource("data_wide.json").getFile());
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(FileUtils.readFileToByteArray(file));
		options.put("fileFormat", "json");
		options.put("streaming", "false");
		options.put("hasMoreData", "false");
		InputStatus inputStatus = falkonry.addInputStream(datastream.getId(), byteArrayInputStream, options);
		Assert.assertEquals(inputStatus.getAction(), "ADD_DATA_DATASTREAM");
		Assert.assertEquals(inputStatus.getStatus(), "PENDING");

		// checking ingestion status
		checkStatus(inputStatus.getId());
	}

	/**
	 * Should add narrow data to datastream in stream CSV format
	 * 
	 * @throws Exception
	 */
	@Test
	public void addDataCsvStream() throws Exception {

		Datastream ds = new Datastream();
		ds.setName("Test-DS-" + Math.random());
		TimeObject time = new TimeObject();
		time.setIdentifier("time");
		time.setFormat("YYYY-MM-DD HH:mm:ss");
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

		Map<String, String> options = new HashMap<String, String>();

		File file = new File(this.getClass().getClassLoader().getResource("data.csv").getFile());
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(FileUtils.readFileToByteArray(file));
		options.put("fileFormat", "csv");
		options.put("streaming", "false");
		options.put("hasMoreData", "false");
		options.put("timeIdentifier", "time");
		options.put("timeFormat", "YYYY-MM-DD HH:mm:ss");
		options.put("timeZone", time.getZone());
		options.put("signalIdentifier", "signal");
		options.put("entityIdentifier", "entity");
		options.put("valueIdentifier", "value");
		
		InputStatus inputStatus = falkonry.addInputStream(datastream.getId(), byteArrayInputStream, options);
		Assert.assertEquals(inputStatus.getAction(), "ADD_DATA_DATASTREAM");
		Assert.assertEquals(inputStatus.getStatus(), "PENDING");

		// checking ingestion status
		checkStatus(inputStatus.getId());

	}

	/**
	 * Should add wide data to datastream in stream CSV format
	 * 
	 * @throws Exception
	 */
	@Test
	public void addWideDataCsvStream() throws Exception {

		Datastream ds = new Datastream();
		ds.setName("Test-DS4-" + Math.random());

		TimeObject time = new TimeObject();
		time.setIdentifier("time");
		time.setFormat("millis");
		time.setZone("GMT");

		Datasource dataSource = new Datasource();
		dataSource.setType("STANDALONE");

		List<Input> inputList = new ArrayList<Input>();
		;
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

		ds.setDatasource(dataSource);
		ds.setField(field);

		Datastream datastream = falkonry.createDatastream(ds);
		datastreams.add(datastream);

		Map<String, String> options = new HashMap<String, String>();
		File file = new File(this.getClass().getClassLoader().getResource("data_wide.csv").getFile());
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(FileUtils.readFileToByteArray(file));
		options.put("fileFormat", "csv");
		options.put("streaming", "false");
		options.put("hasMoreData", "false");
		InputStatus inputStatus = falkonry.addInputStream(datastream.getId(), byteArrayInputStream, options);
		Assert.assertEquals(inputStatus.getAction(), "ADD_DATA_DATASTREAM");
		Assert.assertEquals(inputStatus.getStatus(), "PENDING");

		// checking ingestion status
		checkStatus(inputStatus.getId());
	}

	/**
	 * Should add datastream with microseconds precision
	 * 
	 * @throws Exception
	 */
	@Test
	public void addDataWithMicrosecondsPrecision() throws Exception {

		Datastream ds = new Datastream();
		ds.setName("Test-DS-" + Math.random());
		ds.setTimePrecision("micro");
		TimeObject time = new TimeObject();
		time.setIdentifier("time");
		time.setFormat("YYYY-MM-DD HH:mm:ss");
		time.setZone("GMT");

		Signal signal = new Signal();
		signal.setSignalIdentifier("signal");
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

		Map<String, String> options = new HashMap<String, String>();

		File file = new File(this.getClass().getClassLoader().getResource("data.json").getFile());
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(FileUtils.readFileToByteArray(file));
		options.put("fileFormat", "json");
		options.put("streaming", "false");
		options.put("hasMoreData", "false");
		options.put("timeIdentifier", "time");
		options.put("timeFormat", "YYYY-MM-DD HH:mm:ss");
		options.put("timeZone", time.getZone());
		options.put("signalIdentifier", "signal");
		options.put("entityIdentifier", "entity");
		options.put("valueIdentifier", "value");
		InputStatus inputStatus = falkonry.addInputStream(datastream.getId(), byteArrayInputStream, options);
		Assert.assertEquals(inputStatus.getAction(), "ADD_DATA_DATASTREAM");
		Assert.assertEquals(inputStatus.getStatus(), "PENDING");

		// checking ingestion status
		checkStatus(inputStatus.getId());

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
