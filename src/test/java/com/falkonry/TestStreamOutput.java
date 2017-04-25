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
	String host = "https://localhost:8080";
	String token = "267ummc4hjyywop631wfogkwhb6t95wr";

	/**
	 *
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		falkonry = new Falkonry(host, token);
	}

	// To run this test case, data should be continuously streamed
	@Test

	/**
	 * Should get output data
	 * @throws Exception
	 */
	public void getOutput() throws Exception {
		try {
			String assessment = "suix1o014tyj97";
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
