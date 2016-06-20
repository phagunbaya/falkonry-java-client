package com.falkonry;

/*!
 * falkonry-java-client
 * Copyright(c) 2016 Falkonry Inc
 * MIT Licensed
 */

import com.falkonry.client.Falkonry;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class TestPipelineGetOutput {
  Falkonry falkonry = null;
  String host = "http://localhost:8080";
  String token = "";

  @Before
  @Ignore
  public void setUp() throws Exception {
    falkonry = new Falkonry(host, token);
  }

  @Test
  @Ignore
  public void getOutput() throws Exception{
    try {

      BufferedReader br = falkonry.getOutput("e9wxrrh4yvwv4p", null, null);
      BufferedWriter bw = new BufferedWriter(new FileWriter("res/output.txt"));
      String data;
      while ((data = br.readLine()) != null)
        bw.write(data+"\n");
      if (br != null)
        br.close();
      if (bw != null)
        bw.close();
    }
    catch (Exception e){
      System.out.println(e.toString()+"\nError in getting output");
      Assert.assertEquals(0,1);
    }
  }
}
