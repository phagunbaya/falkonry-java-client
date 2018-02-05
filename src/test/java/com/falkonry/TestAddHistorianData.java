package com.falkonry;

/*!
 * falkonry-java-client
 * Copyright(c) 2017 Falkonry Inc
 * MIT Licensed
 */

import com.falkonry.client.Falkonry;
import com.falkonry.helper.models.*;
import org.junit.*;
import java.util.*;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.json.JSONObject;

public class TestAddHistorianData {

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
	 * Should create narrow format datastream and add data in CSV format
	 * 
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
		String data = "time,entity,signal,value\n" +
				"2016-05-05T12:00:00Z,Unit1,current,12.4\n" +
				"2016-03-01T01:01:01,Unit1,vibration,20.4";

		Map<String, String> options = new HashMap<String, String>();
		options.put("timeIdentifier", "time");
		options.put("timeFormat", "iso_8601");
		options.put("timeZone", time.getZone());
		options.put("signalIdentifier", "signal");
		options.put("entityIdentifier", "entity");
		options.put("valueIdentifier", "value");
		options.put("fileFormat", "csv");
		options.put("streaming", "false");
		options.put("hasMoreData", "false");

		InputStatus inputStatus = falkonry.addInput(datastream.getId(), data, options);
		Assert.assertEquals(inputStatus.getAction(), "ADD_DATA_DATASTREAM");
		Assert.assertEquals(inputStatus.getStatus(), "PENDING");

		// checking ingestion status
		checkStatus(inputStatus.getId());

	}

	/**
	 * Should create narrow format datastream and add stream data in CSV format
	 * 
	 * @throws Exception
	 */
	@Ignore
	@Test
	public void AddDataNarrowFormatCsvForStreaming() throws Exception {

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
		String data = "time,entity,signal,value\n" +
				"2016-05-05 12:00:00Z,Unit1,current,12.4\n" +
				"2016-03-01 01:01:01,Unit1,vibration,20.4";

		Map<String, String> options = new HashMap<String, String>();

		options.put("timeIdentifier", "time");
		options.put("timeFormat", "YYYY-MM-DD HH:mm:ss");
		options.put("timeZone", time.getZone());
		options.put("signalIdentifier", "signal");
		options.put("entityIdentifier", "entity");
		options.put("valueIdentifier", "value");
		options.put("fileFormat", "csv");
		options.put("streaming", "true");
		options.put("hasMoreData", "false");

		InputStatus inputStatus = falkonry.addInput(datastream.getId(), data, options);
		Assert.assertEquals(inputStatus.getAction(), "ADD_DATA_DATASTREAM");
		Assert.assertEquals(inputStatus.getStatus(), "PENDING");

		// checking ingestion status
		checkStatus(inputStatus.getId());

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
