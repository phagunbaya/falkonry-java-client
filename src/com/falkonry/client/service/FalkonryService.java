package com.falkonry.client.service;

/*!
 * falkonry-java-client
 * Copyright(c) 2016 Falkonry Inc
 * MIT Licensed
 */

import com.falkonry.helper.models.*;
import org.apache.commons.io.IOUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FalkonryService {
  private HttpService httpService;

  public FalkonryService (String host, String token) throws Exception {
    this.httpService = new HttpService(host, token);
  }//working

  public Eventbuffer createEventbuffer(Eventbuffer eventbuffer, Map<String, String> options) throws Exception {
    ObjectMapper mapper = new ObjectMapper();
    Map<String, String> ops = new HashMap<String, String>();
    ops.put("name",eventbuffer.getName());
    if(options.containsKey("fileFormat"))
      ops.put("fileFormat", options.get("fileFormat"));
    if(options.containsKey("timeIdentifier"))
      ops.put("timeIdentifier", options.get("timeIdentifier"));
    else
      ops.put("timeIdentifier", "time");

    if(options.containsKey("timeFormat"))
      ops.put("timeFormat", options.get("timeFormat"));
    else
      ops.put("timeFormat", "iso_8601");
    if(options.containsKey("data")){
      String eventbuffer_json = httpService.sfpost("/eventbuffer", ops,
              new ByteArrayInputStream(options.get("data").getBytes(Charset.forName("UTF-8"))));
      return mapper.readValue(eventbuffer_json, Eventbuffer.class);
    }
    else {
      System.out.println(ops);
      String eventbuffer_json = httpService.sfpost("/eventbuffer", ops, null);
      return mapper.readValue(eventbuffer_json, Eventbuffer.class);
    }
  }//working

  public List<Eventbuffer> getEventbuffers() throws Exception {
    ObjectMapper mapper = new ObjectMapper();
    String eventbuffer_json = httpService.get("/eventbuffer");
    return mapper.readValue(eventbuffer_json, new TypeReference<List<Eventbuffer>>(){});
  }

  public Eventbuffer getUpdatedEventbuffer(String id) throws Exception {
    ObjectMapper mapper = new ObjectMapper();
    String url = "/eventbuffer/" + id;
    String eventbuffer_json = httpService.get(url);
    return mapper.readValue(eventbuffer_json,Eventbuffer.class);
  }//working

  public void deleteEventbuffer(String eventbuffer) throws Exception {
    httpService.delete("/eventbuffer/" + eventbuffer);
  }//working

  public Pipeline createPipeline(Pipeline pipeline) throws Exception {
    ObjectMapper mapper = new ObjectMapper();
    PipelineRequest pipelineRequest = new PipelineRequest();
    List<Signal> signalList;
    List<SignalRequest> signalRequestList = new ArrayList<SignalRequest>();
    System.out.println(mapper.writeValueAsString(pipeline));
    int len_input_list = pipeline.getInputList().size();
    signalList = pipeline.getInputList();
    for(int i = 0; i < len_input_list; i++)
    {
      SignalRequest signalRequest = new SignalRequest();
      signalRequest.setName(signalList.get(i).getName());
      signalRequest.setEventType(signalList.get(i).getEventType());
      signalRequest.setValueType(signalList.get(i).getValueType());
      signalRequestList.add(signalRequest);
    }
    int len_assessment_list = pipeline.getAssessmentList().size();
    List<Assessment> assessmentList = pipeline.getAssessmentList();
    List<AssessmentRequest> assessmentRequestList = new ArrayList<AssessmentRequest>();
    for(int i = 0; i < len_assessment_list; i++)
    {
      AssessmentRequest assessmentRequest = new AssessmentRequest();
      assessmentRequest.setName(assessmentList.get(i).getName());
      assessmentRequest.setInputList(assessmentList.get(i).getInputList());
      assessmentRequest.setAprioriConditionList(assessmentList.get(i).getAprioriConditionList());
      assessmentRequestList.add(assessmentRequest);
    }
    pipelineRequest.setName(pipeline.getName())
        .setThingIdentifier(pipeline.getThingIdentifier())
        .setInterval(pipeline.getInterval())
        .setEventbuffer(pipeline.getEventbuffer())
        .setInputList(signalRequestList)
        .setAssessmentList(assessmentRequestList)
        .setThingName(pipeline.getThingName());
    System.out.println(mapper.writeValueAsString(pipelineRequest));
    String pipeline_json = httpService.post("/pipeline", mapper.writeValueAsString(pipelineRequest));
    return mapper.readValue(pipeline_json, Pipeline.class);
  }//working

  public List<Pipeline> getPipelines() throws Exception {
    ObjectMapper mapper = new ObjectMapper();
    String pipeline_json = httpService.get("/pipeline");
    return mapper.readValue(pipeline_json, new TypeReference<List<Pipeline>>(){});
  }

  public void deletePipeline(String pipeline) throws Exception {
    httpService.delete("/pipeline/"+pipeline);
  }

  public InputStatus addInputData(String eventbuffer, String data, Map<String, String> options) throws Exception {
    ObjectMapper mapper = new ObjectMapper();
    Map<String, String> ops = new HashMap<String, String>();
    /*if (options.containsKey("fileFormat")){
      ops.put("fileFormat",options.get("fileFormat"));
    }*/
    String url = "/eventbuffer/"+eventbuffer;
    if(options.containsKey("subscription")){
      url += "?subscriptionKey="+options.get("subscription");
    }
    byte[] data_bytes = data.getBytes(Charset.forName("UTF-8"));
    InputStream stream = new ByteArrayInputStream(data_bytes);
    String status = this.httpService.postData(url, data);
    return mapper.readValue(status, InputStatus.class);
  }//working

  public InputStatus addInputFromStream(String eventbuffer, ByteArrayInputStream stream, Map<String, String> options) throws Exception {
    ObjectMapper mapper = new ObjectMapper();
    String url = "/eventbuffer/"+eventbuffer;
    if(options.containsKey("subscription")){
      url += "?subscriptionKey="+options.get("subscription");
    }
    byte[] data_bytes = IOUtils.toByteArray(stream);
    String status = this.httpService.upstream(url, data_bytes);
    return mapper.readValue(status, InputStatus.class);
  }

  public BufferedReader getOutput(String pipeline, Long start, Long end) throws Exception {
    String url = "/pipeline/"+pipeline+"/output?";
    if(end != null) {
      url += "lastTime=" + end;
      if(start != null)
        url += "&startTime="+start;
    }
    else {
      if(start != null)
        url += "startTime="+start;
    }
    return this.httpService.downstream(url);
  }

  public Subscription createSubscription(String eventbuffer, Subscription subscription) throws Exception {
    ObjectMapper mapper = new ObjectMapper();
    System.out.println(mapper.writeValueAsString(subscription));
    String subscription_json = httpService.post("/eventbuffer/"+eventbuffer+"/subscription", mapper.writeValueAsString(subscription));
    return mapper.readValue(subscription_json, Subscription.class);
  }//working

  public Subscription updateSubscription(String eventbuffer, Subscription subscription) throws Exception {
    ObjectMapper mapper = new ObjectMapper();
    String subscription_json = httpService.put("/eventbuffer/"+eventbuffer+"/subscription/"+subscription.getKey(), mapper.writeValueAsString(subscription));
    return mapper.readValue(subscription_json, Subscription.class);
  }

  public void deleteSubscription(String eventbuffer, String subscription) throws Exception {
    httpService.delete("/eventbuffer/"+eventbuffer+"/subscription/"+subscription);
  }

  public Publication createPublication(String pipeline, Publication publication) throws Exception {
    ObjectMapper mapper = new ObjectMapper();
    String publication_json = httpService.post(pipeline, mapper.writeValueAsString(publication));
    return mapper.readValue(publication_json, Publication.class);
  }

  public Publication updatePublication(String pipeline, Publication publication) throws Exception {
    ObjectMapper mapper = new ObjectMapper();
    String publication_json = httpService.put(pipeline, mapper.writeValueAsString(publication));
    return mapper.readValue(publication_json, Publication.class);
  }

  public void deletePublication(String pipeline, String publication) throws Exception {
    httpService.delete("/pipeline/"+pipeline+"/publication/"+publication);
  }
}
