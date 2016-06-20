package com.falkonry;

/*!
 * falkonry-java-client
 * Copyright(c) 2016 Falkonry Inc
 * MIT Licensed
 */

import com.falkonry.helper.models.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestEntityModel {

  @Test
  public void eventbufferModel()throws Exception{
    Eventbuffer eb = new Eventbuffer();
    eb.setName("Test-EB");
    Assert.assertEquals(eb.getName(),"Test-EB");
  }

  @Test
  public void pipelineModelWithSingleThingWithDefaults(){
    List<Signal> signals = new ArrayList<Signal>();
    signals.add(new Signal().setName("current").setValueType(new ValueType().setType("Numeric"))
        .setEventType(new EventType().setType("Samples")));
    signals.add(new Signal().setName("vibration").setValueType(new ValueType().setType("Numeric"))
        .setEventType(new EventType().setType("Samples")));
    signals.add(new Signal().setName("state").setValueType(new ValueType().setType("Categorical"))
        .setEventType(new EventType().setType("Samples")));

    List<String> inputList = new ArrayList<String>();
    inputList.add("current");
    inputList.add("vibration");
    inputList.add("state");

    List<Assessment> assessments = new ArrayList<Assessment>();
    Assessment assessment = new Assessment();
    assessment.setName("Health");
    assessment.setInputList(inputList);
    assessments.add(assessment);

    Interval interval = new Interval();
    interval.setDuration("PT1S");

    Pipeline pipeline = new Pipeline();
    pipeline.setName("Motor Health");
    pipeline.setEventbuffer("eventbuffer-id");
    pipeline.setInputList(signals);
    pipeline.setThingName("Motor");
    pipeline.setThingIdentifier("thing");
    pipeline.setAssessmentList(assessments);
    pipeline.setInterval(interval);

    Assert.assertEquals(pipeline.getName(),"Motor Health");
    Assert.assertEquals(pipeline.getEventbuffer(),"eventbuffer-id");
    Assert.assertEquals(pipeline.getThingName(),"Motor");
    Assert.assertEquals(pipeline.getInputList().size(),3);
    Assert.assertEquals(pipeline.getAssessmentList().size(),1);
    Assert.assertEquals(pipeline.getInterval().getDuration(),"PT1S");

  }

  @Test
  public void pipelineModelWithMultipleThingsWithOverrides(){
    List<Signal> signals = new ArrayList<Signal>();
    signals.add(new Signal().setName("current").setValueType(new ValueType().setType("Numeric"))
        .setEventType(new EventType().setType("Samples")));
    signals.add(new Signal().setName("vibration").setValueType(new ValueType().setType("Numeric"))
        .setEventType(new EventType().setType("Samples")));
    signals.add(new Signal().setName("state").setValueType(new ValueType().setType("Categorical"))
        .setEventType(new EventType().setType("Samples")));

    List<String> inputList = new ArrayList<String>();
    inputList.add("current");
    inputList.add("vibration");
    inputList.add("state");

    List<Assessment> assessments = new ArrayList<Assessment>();
    Assessment assessment = new Assessment();
    assessment.setName("Health");
    assessment.setInputList(inputList);
    assessments.add(assessment);

    Interval interval = new Interval();
    interval.setDuration("PT1S");

    Pipeline pipeline = new Pipeline();
    pipeline.setName("Motor Health");
    pipeline.setEventbuffer("eventbuffer-id");
    pipeline.setInputList(signals);
    pipeline.setThingName("Motor");
    pipeline.setThingIdentifier("thing");
    pipeline.setAssessmentList(assessments);
    pipeline.setInterval(interval);

    Assert.assertEquals(pipeline.getName(),"Motor Health");
    Assert.assertEquals(pipeline.getEventbuffer(),"eventbuffer-id");
    Assert.assertEquals(pipeline.getThingName(),"Motor");
    Assert.assertEquals(pipeline.getInputList().size(),3);
    Assert.assertEquals(pipeline.getAssessmentList().size(),1);
    Assert.assertEquals(pipeline.getInterval().getDuration(),"PT1S");
  }

  @Test
  public void signalModel(){
    Signal signal = new Signal();
    signal.setName("current").setValueType(new ValueType().setType("Numeric"))
        .setEventType(new EventType().setType("Samples"));

    Assert.assertEquals(signal.getName(),"current");
    Assert.assertEquals(signal.getValueType().getType(),"Numeric");
    Assert.assertEquals(signal.getEventType().getType(),"Samples");
  }

  @Test
  public void assessmentModel(){
    List<String> inputList = new ArrayList<String>();
    inputList.add("current");
    inputList.add("vibration");
    inputList.add("state");

    Assessment assessment = new Assessment();
    assessment.setName("Health");
    assessment.setInputList(inputList);

    Assert.assertEquals(assessment.getName(),"Health");
    Assert.assertEquals(assessment.getInputList().size(),3);

  }

  @Test
  public void subscriptionModel()
  {
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

    Assert.assertEquals(sub.getType(),"MQTT");
    Assert.assertEquals(sub.getTopic(),"falkonry-eb-1-test");
    Assert.assertEquals(sub.getPath(),"mqtt://test.mosquito.com");
    Assert.assertEquals(sub.getUsername(),"test-user");
    Assert.assertEquals(sub.getValueColumn(),"value");
    Assert.assertEquals(sub.getTimeFormat(),"YYYY-MM-DD HH:mm:ss");
    Assert.assertEquals(sub.getTimeIdentifier(),"time");
    Assert.assertEquals(sub.getSignalsDelimiter(),"_");
    Assert.assertEquals(sub.getSignalsTagField(),"tag");
    Assert.assertEquals(sub.getSignalsLocation(),"prefix");

  }

  @Test
  public void publicationModel(){
    Publication publication = new Publication();
    publication.setType("MQTT")
        .setPath("mqtt://test.mosquito.com")
        .setTopic("falkonry-eb-1-test")
        .setUsername("test-user")
        .setPassword("test")
        .setContentType("application/json");

    Assert.assertEquals(publication.getType(),"MQTT");
    Assert.assertEquals(publication.getPath(),"mqtt://test.mosquito.com");
    Assert.assertEquals(publication.getTopic(),"falkonry-eb-1-test");
    Assert.assertEquals(publication.getUsername(),"test-user");
    Assert.assertEquals(publication.getContentType(),"application/json");
  }
}
