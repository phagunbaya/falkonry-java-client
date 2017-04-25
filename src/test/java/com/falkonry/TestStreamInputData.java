package com.falkonry;

/*!
 * falkonry-java-client
 * Copyright(c) 2016 Falkonry Inc
 * MIT Licensed
 */

import com.falkonry.client.Falkonry;
import com.falkonry.helper.models.Datastream;
import com.falkonry.helper.models.InputStatus;
import org.junit.*;

import java.io.ByteArrayInputStream;
import java.util.*;

public class TestStreamInputData {

	Falkonry falkonry = null;
	String host = "https://dev.falkonry.ai";
	String token = "267ummc4hjyywop631wfogkwhb6t95wr";
	String datastreamId = "nb37s0ll4itfo7";
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
	 * Should add CSV format data stream
	 * @throws Exception
	 */
	@Test
	public void addWideDataCsvStream() throws Exception {
		try {
			for (int i = 0; i < 10; i++) {
				Map<String, String> options = new HashMap<String, String>();
				options.put("streaming", "false");
				options.put("hasMoreData", "true");
				Date d = new Date();
				Random r = new Random();
				int n = r.nextInt(100);
				String data = "{\"time\":" + d.getTime() + ",\"tag\":\"entity1_signal1\",\"value\":" + n + "}";
				ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data.getBytes());
				InputStatus inputStatus = falkonry.addInputStream(datastreamId, byteArrayInputStream, options);
				Thread.sleep(1000);
			}
		} catch (Exception e) {
			Exception error = e;
		}
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
			// falkonry.deleteDatastream(ds.getId());
		}
	}

}
