package com.falkonry;

import com.falkonry.client.Falkonry;
import com.falkonry.helper.models.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

/**
 * Created by phagunbaya on 23/03/16.
 */
public class TestCreatePipeline {
  Falkonry falkonry = null;
  String host = "http://192.168.2.238:8080";
  String token = "14m9d8tv5pk96lku7u972d2qznn0weok";
  List<Eventbuffer> eventbuffers = new ArrayList<Eventbuffer>();
  List<Pipeline> pipelines = new ArrayList<Pipeline>();

  @Before
  public void setUp() throws Exception {
    falkonry = new Falkonry(host, token);
  }

  @Test
  public void createPipeline() throws Exception {
    Eventbuffer eb = new Eventbuffer();
    eb.setName("Test-EB-" + Math.random());

    List<Signal> signals = new ArrayList<Signal>();
    signals.add(new Signal().setName("current").setValueType(new ValueType().setType("Numeric")).setEventType(new EventType().setType("Samples")));
    signals.add(new Signal().setName("vibration").setValueType(new ValueType().setType("Numeric")).setEventType(new EventType().setType("Samples")));
    signals.add(new Signal().setName("state").setValueType(new ValueType().setType("Categorical")).setEventType(new EventType().setType("Samples")));

    List<String> inputList = new ArrayList<String>();
    inputList.add("current");
    inputList.add("vibration");
    inputList.add("state");

    List<Assessment> assessments = new ArrayList<Assessment>();
    Assessment assessment = new Assessment();
    assessment.setName("Health");
    assessment.setInputList(inputList);
    assessments.add(assessment);

    Map<String, String> options = new HashMap<String, String>();
    options.put("timeIdentifier", "time");
    options.put("timeFormat", "iso_8601");
    Eventbuffer eventbuffer = falkonry.createEventbuffer(eb, options);
    eventbuffers.add(eventbuffer);

    Interval interval = new Interval();
    interval.setDuration("PT1S");

    Pipeline pipeline = new Pipeline();
    String name = "Test-PL-" + Math.random();
    pipeline.setName(name);
    pipeline.setEventbuffer(eventbuffer.getId());
    pipeline.setInputList(signals);
    pipeline.setThingName(name);
    pipeline.setThingIdentifier("thing");
    pipeline.setAssessmentList(assessments);
    pipeline.setInterval(interval);

    Pipeline pl = falkonry.createPipeline(pipeline);
    Assert.assertEquals(pl.getName(),pipeline.getName());
    falkonry.deletePipeline(pl.getId());
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

