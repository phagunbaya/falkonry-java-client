//package com.falkonry;
//
//import com.falkonry.client.Falkonry;
//import com.falkonry.helper.models.Datastream;
//import org.junit.*;
//
//import java.util.*;
//
///*!
// * falkonry-java-client
// * Copyright(c) 2016 Falkonry Inc
// * MIT Licensed
// */
//
//public class TestCreateDatastream {
//  Falkonry falkonry = null;
//  String host = "http://localhost:8080";
//  String token = "";
//  List<Datastream> datastreams = new ArrayList<Datastream>();
//
//  @Before
//  public void setUp() throws Exception {
//    falkonry = new Falkonry(host, token);
//  }
//
//  //@Test
//  public void createDatastream() throws Exception {
//    Timezone timezone = new Timezone();
//    timezone.setZone("GMT");
//    timezone.setOffset(0);
//
//    Datastream eb = new Datastream();
//    eb.setName("Test-EB-"+Math.random());
//    eb.setTimeIdentifier("time");
//    eb.setTimeFormat("iso_8601");
//    eb.setValueColumn("value");
//    eb.setSignalsDelimiter("_");
//    eb.setSignalsLocation("prefix");
//    eb.setSignalsTagField("tag");
//    eb.setTimezone(timezone);
//
//    Datastream eventbuffer = falkonry.createDatastream(eb);
//    datastreams.add(eventbuffer);
//
//    Assert.assertEquals(eb.getName(), eventbuffer.getName());
//    Assert.assertNotEquals(null, eventbuffer.getId());
//    Assert.assertEquals(1, eventbuffer.getSchemaList().size());
//    Assert.assertEquals(1, eventbuffer.getSubscriptionList().size());
//
//    Assert.assertEquals(eventbuffer.getTimeIdentifier(), eb.getTimeIdentifier());
//    Assert.assertEquals(eventbuffer.getTimeFormat(), eb.getTimeFormat());
//
//    Assert.assertEquals(eventbuffer.getValueColumn(), eb.getValueColumn());
//    Assert.assertEquals(eventbuffer.getSignalsDelimiter(), eb.getSignalsDelimiter());
//    Assert.assertEquals(eventbuffer.getSignalsTagField(), eb.getSignalsTagField());
//    Assert.assertEquals(eventbuffer.getSignalsLocation(), eb.getSignalsLocation());
//  }
//
//  //@Test
//  public void createWideDatastream() throws Exception {
//    Datastream eb = new Datastream();
//    eb.setName("Test-EB-"+Math.random());
//    eb.setTimeIdentifier("time");
//    eb.setTimeFormat("iso_8601");
//    eb.setEntityIdentifier("entity");
//
//    Datastream eventbuffer = falkonry.createDatastream(eb);
//    datastreams.add(eventbuffer);
//
//    Assert.assertEquals(eb.getName(), eventbuffer.getName());
//    Assert.assertNotEquals(null, eventbuffer.getId());
//    Assert.assertEquals(1, eventbuffer.getSchemaList().size());
//    Assert.assertEquals(1, eventbuffer.getSubscriptionList().size());
//
//    Assert.assertEquals(eventbuffer.getTimeIdentifier(), eb.getTimeIdentifier());
//    Assert.assertEquals(eventbuffer.getTimeFormat(), eb.getTimeFormat());
//  }
//
//  //@Test
//  public void createDatastreamWithJsonData() throws Exception {
//    Datastream eb = new Datastream();
//    eb.setName("Test-EB-"+Math.random());
//    eb.setTimeIdentifier("time");
//    eb.setTimeFormat("iso_8601");
//    eb.setValueColumn("value");
//    eb.setSignalsDelimiter("_");
//    eb.setSignalsLocation("prefix");
//    eb.setSignalsTagField("tag");
//
//    Datastream eventbuffer = falkonry.createDatastream(eb);
//    datastreams.add(eventbuffer);
//
//    String data = "{\"time\" : \"2016-03-01 01:01:01\", \"tag\" : \"signal1_entity1\", \"value\" : 3.4}";
//    Map<String, String> options = new HashMap<String, String>();
//    falkonry.addInput(eventbuffer.getId(), data, options);
//
//    Assert.assertEquals(eb.getName(), eventbuffer.getName());
//    Assert.assertNotEquals(null, eventbuffer.getId());
//    Assert.assertEquals(1, eventbuffer.getSchemaList().size());
//    Assert.assertEquals(1, eventbuffer.getSubscriptionList().size());
//
//    Assert.assertEquals(eventbuffer.getTimeFormat(), eb.getTimeFormat());
//    Assert.assertEquals(eventbuffer.getTimeIdentifier(), eb.getTimeIdentifier());
//
//    Assert.assertEquals(eventbuffer.getValueColumn(), eb.getValueColumn());
//    Assert.assertEquals(eventbuffer.getSignalsDelimiter(), eb.getSignalsDelimiter());
//    Assert.assertEquals(eventbuffer.getSignalsTagField(), eb.getSignalsTagField());
//    Assert.assertEquals(eventbuffer.getSignalsLocation(), eb.getSignalsLocation());
//  }
//
//  //@Test
//  public void createDatastreamWithWideJsonData() throws Exception {
//    Datastream eb = new Datastream();
//    eb.setName("Test-EB-"+Math.random());
//    eb.setEntityIdentifier("entity");
//    eb.setTimeIdentifier("time");
//    eb.setTimeFormat("millis");
//
//    Datastream eventbuffer = falkonry.createDatastream(eb);
//    datastreams.add(eventbuffer);
//
//    String data = "{\"time\":1467729675422,\"entity\":\"entity1\",\"signal1\":41.11,\"signal2\":82.34,\"signal3\":74.63,\"signal4\":4.8,\"signal5\":72.01}";
//    Map<String, String> options = new HashMap<String, String>();
//    falkonry.addInput(eventbuffer.getId(), data, options);
//
//    Assert.assertEquals(eb.getName(), eventbuffer.getName());
//    Assert.assertNotEquals(null, eventbuffer.getId());
//    Assert.assertEquals(1, eventbuffer.getSchemaList().size());
//    Assert.assertEquals(1, eventbuffer.getSubscriptionList().size());
//
//    Assert.assertEquals(eventbuffer.getTimeFormat(), eb.getTimeFormat());
//    Assert.assertEquals(eventbuffer.getTimeIdentifier(), eb.getTimeIdentifier());
//
//    Assert.assertEquals(eventbuffer.getValueColumn(), eb.getValueColumn());
//    Assert.assertEquals(eventbuffer.getSignalsDelimiter(), eb.getSignalsDelimiter());
//    Assert.assertEquals(eventbuffer.getSignalsTagField(), eb.getSignalsTagField());
//    Assert.assertEquals(eventbuffer.getSignalsLocation(), eb.getSignalsLocation());
//  }
//
//  //@Test
//  public void createDatastreamWithCsvData() throws Exception {
//    Datastream eb = new Datastream();
//    eb.setName("Test-EB-"+Math.random());
//    eb.setTimeIdentifier("time");
//    eb.setTimeFormat("iso_8601");
//    eb.setValueColumn("value");
//    eb.setSignalsDelimiter("_");
//    eb.setSignalsLocation("prefix");
//    eb.setSignalsTagField("tag");
//
//    Datastream eventbuffer = falkonry.createDatastream(eb);
//    datastreams.add(eventbuffer);
//
//    String data = "time, tag, value\n2016-03-01 01:01:01, signal1_entity1, 3.4";
//    Map<String, String> options = new HashMap<String, String>();
//    falkonry.addInput(eventbuffer.getId(), data, options);
//
//    Assert.assertEquals(eb.getName(), eventbuffer.getName());
//    Assert.assertNotEquals(null, eventbuffer.getId());
//    Assert.assertEquals(1, eventbuffer.getSchemaList().size());
//    Assert.assertEquals(1, eventbuffer.getSubscriptionList().size());
//
//    Assert.assertEquals(eventbuffer.getValueColumn(), eb.getValueColumn());
//    Assert.assertEquals(eventbuffer.getSignalsDelimiter(), eb.getSignalsDelimiter());
//    Assert.assertEquals(eventbuffer.getSignalsTagField(), eb.getSignalsTagField());
//    Assert.assertEquals(eventbuffer.getSignalsLocation(), eb.getSignalsLocation());
//  }
//
//  //@Test
//  public void createDatastreamWithWideCsvData() throws Exception {
//    Datastream eb = new Datastream();
//    eb.setName("Test-EB-"+Math.random());
//    eb.setEntityIdentifier("entity");
//    eb.setTimeIdentifier("time");
//    eb.setTimeFormat("millis");
//
//    Datastream eventbuffer = falkonry.createDatastream(eb);
//    datastreams.add(eventbuffer);
//    String data = "time,entity,signal1,signal2,signal3,signal4,signal5\n" +
//            "1467729675422,entity1,41.11,82.34,74.63,4.8,72.01";
//    Map<String, String> options = new HashMap<String, String>();
//    falkonry.addInput(eventbuffer.getId(), data, options);
//
//    Assert.assertEquals(eb.getName(), eventbuffer.getName());
//    Assert.assertNotEquals(null, eventbuffer.getId());
//    Assert.assertEquals(1, eventbuffer.getSchemaList().size());
//    Assert.assertEquals(1, eventbuffer.getSubscriptionList().size());
//
//    Assert.assertEquals(eventbuffer.getValueColumn(), eb.getValueColumn());
//    Assert.assertEquals(eventbuffer.getSignalsDelimiter(), eb.getSignalsDelimiter());
//    Assert.assertEquals(eventbuffer.getSignalsTagField(), eb.getSignalsTagField());
//    Assert.assertEquals(eventbuffer.getSignalsLocation(), eb.getSignalsLocation());
//  }
//
//  //@Test
//  public void createDatastreamWithMqttSubscription() throws Exception {
//    Datastream eb = new Datastream();
//    eb.setName("Test-EB-"+Math.random());
//    eb.setTimeIdentifier("time");
//    eb.setTimeFormat("iso_8601");
//    eb.setValueColumn("value");
//    eb.setSignalsDelimiter("_");
//    eb.setSignalsLocation("prefix");
//    eb.setSignalsTagField("tag");
//
//    Subscription sub = new Subscription();
//    sub.setType("MQTT")
//            .setPath("mqtt://test.mosquito.com")
//            .setTopic("falkonry-eb-1-test")
//            .setUsername("test-user")
//            .setPassword("test");
//
//    Datastream eventbuffer = falkonry.createDatastream(eb);
//
//    String data = "time,entity,signal1,signal2,signal3,signal4,signal5\n" +
//            "1467729675422,entity1,41.11,82.34,74.63,4.8,72.01";
//    Map<String, String> options = new HashMap<String, String>();
//    falkonry.addInput(eventbuffer.getId(), data, options);
//
//    datastreams.add(eventbuffer);
//    Assert.assertEquals(1, eventbuffer.getSubscriptionList().size());
//
//    Subscription subscription = falkonry.createSubscription(eventbuffer.getId(), sub);
//
//    Assert.assertNotEquals(null, subscription.getKey());
//    Assert.assertEquals(sub.getType(), subscription.getType());
//    Assert.assertEquals(sub.getPath(), subscription.getPath());
//    Assert.assertEquals(sub.getTopic(), subscription.getTopic());
//    Assert.assertEquals(sub.getUsername(), subscription.getUsername());
//    Assert.assertEquals(sub.getPassword(), subscription.getPassword());
//
//    Assert.assertEquals(eventbuffer.getTimeIdentifier(), eb.getTimeIdentifier());
//    Assert.assertEquals(eventbuffer.getTimeFormat(), eb.getTimeFormat());
//
//    Assert.assertEquals(eventbuffer.getValueColumn(), eb.getValueColumn());
//    Assert.assertEquals(eventbuffer.getSignalsDelimiter(), eb.getSignalsDelimiter());
//    Assert.assertEquals(eventbuffer.getSignalsTagField(), eb.getSignalsTagField());
//    Assert.assertEquals(eventbuffer.getSignalsLocation(), eb.getSignalsLocation());
//
//    falkonry.deleteSubscription(eventbuffer.getId(),subscription.getKey());
//  }
//
//  //@Test
//  public void createDatastreamWithOutflowSubscription() throws Exception {
//    Datastream eb = new Datastream();
//    eb.setName("Test-EB-"+Math.random());
//    eb.setTimeIdentifier("time");
//    eb.setTimeFormat("iso_8601");
//    eb.setValueColumn("value");
//    eb.setSignalsDelimiter("_");
//    eb.setSignalsLocation("prefix");
//    eb.setSignalsTagField("tag");
//
//    Subscription sub = new Subscription();
//    sub.setType("PIPELINEOUTFLOW")
//            .setPath("urn:falkonry:pipeline:zmusfprsf7zspf");
//
//    Datastream eventbuffer = falkonry.createDatastream(eb);
//    String data = "time,entity,signal1,signal2,signal3,signal4,signal5\n"
//            + "1467729675422,entity1,41.11,82.34,74.63,4.8,72.01";
//    Map<String, String> options = new HashMap<String, String>();
//    falkonry.addInput(eventbuffer.getId(), data, options);
//
//    datastreams.add(eventbuffer);
//
//    Assert.assertEquals(1, eventbuffer.getSubscriptionList().size());
//
//    Subscription subscription = falkonry.createSubscription(eventbuffer.getId(), sub);
//
//    Assert.assertNotEquals(null, subscription.getKey());
//    Assert.assertEquals(sub.getType(), subscription.getType());
//    Assert.assertEquals(sub.getPath(), subscription.getPath());
//
//    Assert.assertEquals(eventbuffer.getTimeIdentifier(), eb.getTimeIdentifier());
//    Assert.assertEquals(eventbuffer.getTimeFormat(), eb.getTimeFormat());
//
//    Assert.assertEquals(eventbuffer.getValueColumn(), eb.getValueColumn());
//    Assert.assertEquals(eventbuffer.getSignalsDelimiter(), eb.getSignalsDelimiter());
//    Assert.assertEquals(eventbuffer.getSignalsTagField(), eb.getSignalsTagField());
//    Assert.assertEquals(eventbuffer.getSignalsLocation(), eb.getSignalsLocation());
//
//    falkonry.deleteSubscription(eventbuffer.getId(),subscription.getKey());
//  }
//
//  //@Test
//  public void createDatastreamWithMqttSubscriptionForNarrowFormatData() throws Exception {
//    Datastream eb = new Datastream();
//    eb.setName("Test-EB-"+Math.random());
//    eb.setTimeIdentifier("time");
//    eb.setTimeFormat("iso_8601");
//    eb.setValueColumn("value");
//    eb.setSignalsDelimiter("_");
//    eb.setSignalsLocation("prefix");
//    eb.setSignalsTagField("tag");
//
//    Subscription sub = new Subscription();
//    sub.setType("MQTT")
//            .setPath("mqtt://test.mosquito.com")
//            .setTopic("falkonry-eb-1-test")
//            .setUsername("test-user")
//            .setPassword("test");
//
//    Datastream eventbuffer = falkonry.createDatastream(eb);
//    String data = "time,entity,signal1,signal2,signal3,signal4,signal5\n" +
//            "1467729675422,entity1,41.11,82.34,74.63,4.8,72.01";
//    Map<String, String> options = new HashMap<String, String>();
//    falkonry.addInput(eventbuffer.getId(), data, options);
//
//    datastreams.add(eventbuffer);
//    Assert.assertEquals(1, eventbuffer.getSubscriptionList().size());
//
//    Subscription subscription = falkonry.createSubscription(eventbuffer.getId(), sub);
//
//    Assert.assertNotEquals(null, subscription.getKey());
//    Assert.assertEquals(sub.getType(), subscription.getType());
//    Assert.assertEquals(sub.getPath(), subscription.getPath());
//    Assert.assertEquals(sub.getTopic(), subscription.getTopic());
//    Assert.assertEquals(sub.getUsername(), subscription.getUsername());
//    Assert.assertEquals(sub.getPassword(), subscription.getPassword());
//
//    Assert.assertEquals(eventbuffer.getTimeIdentifier(), eb.getTimeIdentifier());
//    Assert.assertEquals(eventbuffer.getTimeFormat(), eb.getTimeFormat());
//
//    Assert.assertEquals(eventbuffer.getValueColumn(), eb.getValueColumn());
//    Assert.assertEquals(eventbuffer.getSignalsDelimiter(), eb.getSignalsDelimiter());
//    Assert.assertEquals(eventbuffer.getSignalsTagField(), eb.getSignalsTagField());
//    Assert.assertEquals(eventbuffer.getSignalsLocation(), eb.getSignalsLocation());
//  }
//
//  @After
//  public void cleanUp() throws Exception {
//    Iterator<Datastream> itr = datastreams.iterator();
//    while(itr.hasNext()) {
//      Datastream eb = itr.next();
//      falkonry.deleteDatastream(eb.getId());
//    }
//  }
//}
