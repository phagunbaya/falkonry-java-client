package com.falkonry.client.service;

/*!
 * falkonry-java-client
 * Copyright(c) 2016 Falkonry Inc
 * MIT Licensed
 */

import com.falkonry.helper.models.*;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import java.io.*;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FalkonryService {
  private HttpService httpService;

  public FalkonryService (String host, String token) throws Exception {
    this.httpService = new HttpService(host, token);
  }

  public Eventbuffer createEventbuffer(Eventbuffer eventbuffer, Map<String, String> options) throws Exception {
    ObjectMapper mapper = new ObjectMapper();
    if(options.containsKey("data")){
      Map<String, String> ops = new HashMap<String, String>();
      if(options.containsKey("timeIdentifier"))
        ops.put("timeIdentifier", options.get("timeIdentifier"));
      else
        ops.put("timeIdentifier", "time");

      if(options.containsKey("timeFormat"))
        ops.put("timeFormat", options.get("timeFormat"));
      else
        ops.put("timeFormat", "iso_8601");
      String eventbuffer_json = httpService.fpost("/eventbuffer", ops, new ByteArrayInputStream(options.get("data").getBytes(Charset.forName("UTF-8"))));
      return mapper.readValue(eventbuffer_json, Eventbuffer.class);
    }
    else {
      String data = "name=" + eventbuffer.getName();
      String eventbuffer_json = httpService.sfpost("/eventbuffer", data);
      return mapper.readValue(eventbuffer_json, Eventbuffer.class);
    }
  }

  public List<Eventbuffer> getEventbuffers() throws Exception {
    ObjectMapper mapper = new ObjectMapper();
    String eventbuffer_json = httpService.get("/eventbuffer");
    return mapper.readValue(eventbuffer_json, new TypeReference<List<Eventbuffer>>(){});
  }

  public void deleteEventbuffer(String eventbuffer) throws Exception {
    httpService.delete("/eventbuffer/"+eventbuffer);
  }

  public Pipeline createPipeline(Pipeline pipeline) throws Exception {
    ObjectMapper mapper = new ObjectMapper();
    String pipeline_json = httpService.post("/pipeline", mapper.writeValueAsString(pipeline));
    return mapper.readValue(pipeline_json, Pipeline.class);
  }

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
    String url = "/eventbuffer/"+eventbuffer;
    if(options.containsKey("subscription")){
      url += "?subscriptionKey="+options.get("subscription");
    }
    byte[] data_bytes = data.getBytes(Charset.forName("UTF-8"));
    InputStream stream = new ByteArrayInputStream(data_bytes);
    String status = this.httpService.fpost(url, null, stream);
    return mapper.readValue(status, InputStatus.class);
  }

  public InputStatus addInputFromStream(String eventbuffer, ByteArrayOutputStream stream, Map<String, String> options) throws Exception {
    ObjectMapper mapper = new ObjectMapper();
    String url = "/eventbuffer/"+eventbuffer;
    if(options.containsKey("subscription")){
      url += "?subscriptionKey="+options.get("subscription");
    }
    String status = this.httpService.upstream(url, stream.toByteArray());
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
    String subscription_json = httpService.post(eventbuffer, mapper.writeValueAsString(subscription));
    return mapper.readValue(subscription_json, Subscription.class);
  }

  public Subscription updateSubscription(String eventbuffer, Subscription subscription) throws Exception {
    ObjectMapper mapper = new ObjectMapper();
    String subscription_json = httpService.put(eventbuffer, mapper.writeValueAsString(subscription));
    return mapper.readValue(subscription_json, Subscription.class);
  }

  public void deleteEventbuffer(String eventbuffer, String subscription) throws Exception {
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
