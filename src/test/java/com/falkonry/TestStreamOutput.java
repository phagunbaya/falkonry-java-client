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
 * @author dev-falkonry-10
 */
public class TestStreamOutput {

    Falkonry falkonry = null;
    String host = "https://localhost:8080";
    String token = "8g462njx92e1yc0fxzrbdxqtx90hsr1s";

    /**
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        falkonry = new Falkonry(host, token);
    }

    //To run this test case, data should be continuously streamed
    //@Test

    /**
     *
     * @throws Exception
     */
    public void getOutput() throws Exception {
        try {
            String assessment = "suix1o014tyj97";
            BufferedReader outputBuffer;
            outputBuffer = falkonry.getOutput(assessment,null,null);
            String inputLine;
            StringBuffer response = new StringBuffer();
            inputLine = outputBuffer.readLine();
            while (inputLine != null) {
            	try {
            		inputLine = outputBuffer.readLine();
                    response.append(inputLine);
            	} catch(Exception e) {
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
