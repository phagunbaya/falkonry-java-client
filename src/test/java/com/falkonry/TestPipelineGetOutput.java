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

public class TestPipelineGetOutput {
  Falkonry falkonry = null;
  String host = "https://dev.falkonry.io";
  String token = "6vhoa94dnndb299ulaj4a51hq9ppa88y";

  @Before
  public void setUp() throws Exception {
    falkonry = new Falkonry(host, token);
  }

  @Test
  public void getOutput() throws Exception{
    try {
      String pipeline = "zmusfprsf7zspf";
      BufferedReader br = falkonry.getOutput(pipeline, null, null);
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
