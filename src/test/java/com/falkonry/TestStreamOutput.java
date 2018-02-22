package com.falkonry;

/*!
 * falkonry-java-client
 * Copyright(c) 2017 Falkonry Inc
 * MIT Licensed
 */

import com.falkonry.client.Falkonry;
import org.junit.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.Observer;

@Ignore
public class TestStreamOutput {

	Falkonry falkonry = null;
	String host = System.getenv("FALKONRY_HOST_URL");
	String token = System.getenv("FALKONRY_TOKEN");
	String assessment = System.getenv("FALKONRY_ASSESSMENT_SLIDING_ID");
	

	/**
	 *
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		falkonry = new Falkonry(host, token);
	}

	// To run this test case, data should be continuously streamed

	/**
	 * Should get output data
	 * 
	 * @throws Exception
	 */
	@Test
	public void getOutput() throws Exception {
		try {
			BufferedReader outputBuffer;
			outputBuffer = falkonry.getOutput(assessment);
			String inputLine;
			StringBuffer response = new StringBuffer();
			inputLine = outputBuffer.readLine();
			while (inputLine != null) {
				try {
					inputLine = outputBuffer.readLine();
					response.append(inputLine);
				} catch (Exception e) {
					break;
				}
			}
			outputBuffer.close();

		} catch (Exception e) {
			System.out.println(e.toString() + "\nError in getting output");
			Assert.assertEquals(0, 1);
		}
	}
}
