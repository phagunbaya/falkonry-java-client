package com.falkonry;

import com.falkonry.client.Falkonry;
import com.falkonry.helper.models.Eventbuffer;
import com.falkonry.helper.models.Subscription;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

/*!
 * falkonry-java-client
 * Copyright(c) 2016 Falkonry Inc
 * MIT Licensed
 */

public class TestCreateEventbuffer {
  Falkonry falkonry = null;
  String host = "https://dev.falkonry.io";
  String token = "f8xew97kv5rc1qdgps0hn5qi77om014q";
  List<Eventbuffer> eventbuffers = new ArrayList<Eventbuffer>();

  @Before
  public void setUp() throws Exception {
    falkonry = new Falkonry(host, token);
  }

  @Test
  public void createEventbuffer() throws Exception {
    Eventbuffer eb = new Eventbuffer();
    eb.setName("Test-EB-"+Math.random());
    Map<String, String> options = new HashMap<String, String>();
    options.put("timeIdentifier", "time");
    options.put("timeFormat", "iso_8601");
    Eventbuffer eventbuffer = falkonry.createEventbuffer(eb, options);
    eventbuffers.add(eventbuffer);
    Assert.assertEquals(eb.getName(), eventbuffer.getName());
    Assert.assertNotEquals(null, eventbuffer.getId());
    Assert.assertEquals(0, eventbuffer.getSchemaList().size());
    Assert.assertEquals(1, eventbuffer.getSubscriptionList().size());
  }

  @Test
  public void createEventbufferWithJsonData() throws Exception {
    Eventbuffer eb = new Eventbuffer();
    eb.setName("Test-EB-"+Math.random());
    Map<String, String> options = new HashMap<String, String>();
    options.put("timeIdentifier", "time");
    options.put("timeFormat", "iso_8601");
    options.put("fileFormat","json");
    options.put("data", "{\"time\" :\"2016-03-01 01:01:01\", \"current\" : 12.4, \"vibration\" : 3.4, \"state\" : \"On\"}");
    Eventbuffer eventbuffer = falkonry.createEventbuffer(eb, options);
    eventbuffers.add(eventbuffer);

    Assert.assertEquals(eb.getName(), eventbuffer.getName());
    Assert.assertNotEquals(null, eventbuffer.getId());
    Assert.assertEquals(1, eventbuffer.getSchemaList().size());
    Assert.assertEquals(1, eventbuffer.getSubscriptionList().size());
  }

  @Test
  public void createEventbufferWithCsvData() throws Exception {
    Eventbuffer eb = new Eventbuffer();
    eb.setName("Test-EB-"+Math.random());
    Map<String, String> options = new HashMap<String, String>();
    options.put("timeIdentifier", "time");
    options.put("timeFormat", "iso_8601");
    options.put("fileFormat", "csv");
    options.put("data", "time, current, vibration, state\n" + "2016-03-01 01:01:01, 12.4, 3.4, On");
    Eventbuffer eventbuffer = falkonry.createEventbuffer(eb, options);
    eventbuffers.add(eventbuffer);

    Assert.assertEquals(eb.getName(), eventbuffer.getName());
    Assert.assertNotEquals(null, eventbuffer.getId());
    Assert.assertEquals(1, eventbuffer.getSchemaList().size());
    Assert.assertEquals(1, eventbuffer.getSubscriptionList().size());
  }

  @Test
  public void createEventbufferWithMqttSubscription() throws Exception {
    Eventbuffer eb = new Eventbuffer();
    eb.setName("Test-EB-"+Math.random());
    Map<String, String> options = new HashMap<String, String>();
    options.put("timeIdentifier", "time");
    options.put("timeFormat", "iso_8601");
    Subscription sub = new Subscription();
    sub.setType("MQTT")
            .setPath("mqtt://test.mosquito.com")
            .setTopic("falkonry-eb-1-test")
            .setUsername("test-user")
            .setPassword("test")
            .setTimeFormat("YYYY-MM-DD HH:mm:ss")
            .setTimeIdentifier("time");

    Eventbuffer eventbuffer = falkonry.createEventbuffer(eb, options);
    eventbuffers.add(eventbuffer);
    Assert.assertEquals(1, eventbuffer.getSubscriptionList().size());

    Subscription subscription = falkonry.createSubscription(eventbuffer.getId(), sub);
    Assert.assertNotEquals(null, subscription.getKey());
    Assert.assertEquals(sub.getType(), subscription.getType());
    Assert.assertEquals(sub.getPath(), subscription.getPath());
    Assert.assertEquals(sub.getTopic(), subscription.getTopic());
    Assert.assertEquals(sub.getPath(), subscription.getPath());
    Assert.assertEquals(sub.getUsername(), subscription.getUsername());
    Assert.assertEquals(sub.getPassword(), subscription.getPassword());
    Assert.assertEquals(sub.getTimeIdentifier(), subscription.getTimeIdentifier());
    Assert.assertEquals(sub.getTimeFormat(), subscription.getTimeFormat());
  }

  @Test
  public void createEventbufferWithOutflowSubscription() throws Exception {
    Eventbuffer eb = new Eventbuffer();
    eb.setName("Test-EB-"+Math.random());
    Map<String, String> options = new HashMap<String, String>();
    options.put("timeIdentifier", "time");
    options.put("timeFormat", "iso_8601");
    Subscription sub = new Subscription();
    sub.setType("PIPELINEOUTFLOW")
            .setPath("urn:falkonry:pipeline:qaerscdtxh7rc3");

    Eventbuffer eventbuffer = falkonry.createEventbuffer(eb, options);
    eventbuffers.add(eventbuffer);
    Assert.assertEquals(1, eventbuffer.getSubscriptionList().size());

    Subscription subscription = falkonry.createSubscription(eventbuffer.getId(), sub);
    Assert.assertNotEquals(null, subscription.getKey());
    Assert.assertEquals(sub.getType(), subscription.getType());
    Assert.assertEquals(sub.getPath(), subscription.getPath());
  }

  @Test
  public void createEventbufferWithMqttSubscriptionForHistorianData() throws Exception {
    Eventbuffer eb = new Eventbuffer();
    eb.setName("Test-EB-"+Math.random());
    Map<String, String> options = new HashMap<String, String>();
    options.put("timeIdentifier", "time");
    options.put("timeFormat", "iso_8601");
    Subscription sub = new Subscription();
    sub.setType("MQTT")
            .setPath("mqtt://test.mosquito.com")
            .setTopic("falkonry-eb-1-test")
            .setUsername("test-user")
            .setPassword("test")
            .setTimeFormat("YYYY-MM-DD HH:mm:ss")
            .setTimeIdentifier("time")
            .setHistorian(true)
            .setValueColumn("value")
            .setSignalsDelimiter("_")
            .setSignalsTagField("tag")
            .setSignalsLocation("prefix");

    Eventbuffer eventbuffer = falkonry.createEventbuffer(eb, options);
    eventbuffers.add(eventbuffer);
    Assert.assertEquals(1, eventbuffer.getSubscriptionList().size());

    Subscription subscription = falkonry.createSubscription(eventbuffer.getId(), sub);
    Assert.assertNotEquals(null, subscription.getKey());
    Assert.assertEquals(sub.getType(), subscription.getType());
    Assert.assertEquals(sub.getPath(), subscription.getPath());
    Assert.assertEquals(sub.getTopic(), subscription.getTopic());
    Assert.assertEquals(sub.getPath(), subscription.getPath());
    Assert.assertEquals(sub.getUsername(), subscription.getUsername());
    Assert.assertEquals(sub.getPassword(), subscription.getPassword());
    Assert.assertEquals(sub.getTimeIdentifier(), subscription.getTimeIdentifier());
    Assert.assertEquals(sub.getTimeFormat(), subscription.getTimeFormat());
    Assert.assertEquals(sub.getValueColumn(), subscription.getValueColumn());
    Assert.assertEquals(sub.getSignalsDelimiter(), subscription.getSignalsDelimiter());
    Assert.assertEquals(sub.getSignalsTagField(), subscription.getSignalsTagField());
    Assert.assertEquals(sub.getSignalsLocation(), subscription.getSignalsLocation());
  }

  @After
  public void cleanUp() throws Exception {
    Iterator<Eventbuffer> itr = eventbuffers.iterator();
    while(itr.hasNext()) {
      Eventbuffer eb = itr.next();
      falkonry.deleteEventbuffer(eb.getId());
    }
  }
}
