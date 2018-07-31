package com.falkonry;

/*!
 * falkonry-java-client
 * Copyright(c) 2017-2018 Falkonry Inc
 * MIT Licensed
 */

import com.falkonry.client.Falkonry;
import com.falkonry.helper.models.Assessment;
import com.falkonry.helper.models.Datastream;
import org.junit.*;
import java.util.*;

public class TestFalkonryException {

	Falkonry falkonry = null;
	String host = System.getenv("FALKONRY_HOST_URL");
	String token = System.getenv("FALKONRY_TOKEN");


	/**
	 *
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		falkonry = new Falkonry(host, token);
	}

	/**
	 * Should test falkonry exception when fetching incorrect datastream
	 * 
	 * @throws Exception
	 */
	@Test
	public void exceptionFetchingDatastream() throws Exception {
		try {
			Datastream ds = falkonry.getDatastream("123");
		} catch (Exception e) {
			String msg = e.getMessage();
			Assert.assertEquals(msg, "{\"message\":\"No such Datastream available\"}");
		}
	}

	/**
	 * Should test falkonry exception when fetching incorrect assessment
	 * 
	 * @throws Exception
	 */
	@Test
	public void exceptionFetchingAssessment() throws Exception {
		try {
			Assessment asmt = falkonry.getAssessment("123");
		} catch (Exception e) {
			String msg = e.getMessage();
			Assert.assertEquals(msg, "{\"message\":\"No such assessment available\"}");
		}
	}

}
