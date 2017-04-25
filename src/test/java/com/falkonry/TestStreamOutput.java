package com.falkonry;

/*!
 * falkonry-java-client
 * Copyright(c) 2016 Falkonry Inc
 * MIT Licensed
 */
import com.falkonry.client.Falkonry;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.Observer;

/**
 *
 */
public class TestStreamOutput {

	Falkonry falkonry = null;
	String host = "https://dev.falkonry.ai";
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
	// @Test

	/**
	 * Should get output data
	 * @throws Exception
	 */
	public void getOutput() throws Exception {
		try {
			String assessment = "hq1b0xg2f2a4hh";
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
