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
    String token = "yf15jw8igeppzqba86essum3ycdeqi9u";

    /**
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        falkonry = new Falkonry(host, token);
    }

    //To run this test case, data should be continously streamed
    //@Test

    /**
     *
     * @throws Exception
     */
    public void getOutput() throws Exception {
        try {
            String assessment = "wpyred1glh6c5r";
            BufferedReader outputBuffer;
            outputBuffer = falkonry.getOutput(assessment,null,null);
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = outputBuffer.readLine()) != null) {
                response.append(inputLine);
            }
            outputBuffer.close();

        } catch (Exception e) {
            System.out.println(e.toString() + "\nError in getting output");
            Assert.assertEquals(0, 1);
        }
    }
}
