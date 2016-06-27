package com.falkonry;

import com.falkonry.client.Falkonry;
import com.falkonry.helper.models.Eventbuffer;
import com.falkonry.helper.models.InputStatus;
import org.junit.*;
import org.apache.commons.io.FileUtils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.*;

/*!
 * falkonry-java-client
 * Copyright(c) 2016 Falkonry Inc
 * MIT Licensed
 */


public class TestAddDataStream {
  Falkonry falkonry = null;
  String host = "http://localhost:8080";
  String token = "";
  List<Eventbuffer> eventbuffers = new ArrayList<Eventbuffer>();

  @Before
  @Ignore
  public void setUp() throws Exception {
    falkonry = new Falkonry(host, token);
  }

  @Test
  @Ignore
  public void addDataJsonStream() throws Exception {
    Eventbuffer eb = new Eventbuffer();
    eb.setName("Test-EB-" + Math.random());
    Map<String, String> options = new HashMap<String, String>();
    options.put("timeIdentifier", "time");
    options.put("timeFormat", "iso_8601");
    Eventbuffer eventbuffer = falkonry.createEventbuffer(eb, options);
    eventbuffers.add(eventbuffer);
    File file = new File("res/data.json");
    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(FileUtils.readFileToByteArray(file));
    InputStatus inputStatus = falkonry.addInputStream(eventbuffer.getId(),byteArrayInputStream,options);
    eventbuffer = falkonry.getUpdatedEventbuffer(eventbuffer.getId());
    Assert.assertEquals(1,eventbuffer.getSchemaList().size());
  }

  @Test
  @Ignore
  public void addDataCsvStream() throws Exception {
    Eventbuffer eb = new Eventbuffer();
    eb.setName("Test-EB-" + Math.random());
    Map<String, String> options = new HashMap<String, String>();
    options.put("timeIdentifier", "time");
    options.put("timeFormat", "iso_8601");
    Eventbuffer eventbuffer = falkonry.createEventbuffer(eb, options);
    eventbuffers.add(eventbuffer);
    File file = new File("res/data.csv");
    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(FileUtils.readFileToByteArray(file));
    InputStatus inputStatus = falkonry.addInputStream(eventbuffer.getId(),byteArrayInputStream,options);
    eventbuffer = falkonry.getUpdatedEventbuffer(eventbuffer.getId());
    Assert.assertEquals(1,eventbuffer.getSchemaList().size());
  }

  @After
  @Ignore
  public void cleanUp() throws Exception {
    Iterator<Eventbuffer> itr = eventbuffers.iterator();
    while(itr.hasNext()) {
      Eventbuffer eb = itr.next();
      falkonry.deleteEventbuffer(eb.getId());
    }
  }

}
