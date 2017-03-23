package com.falkonry.client.service;

/*!
 * falkonry-java-client
 * Copyright(c) 2016 Falkonry Inc
 * MIT Licensed
 */
import com.falkonry.helper.models.*;
import com.sun.org.apache.xpath.internal.operations.Bool;
import javafx.beans.*;
import org.apache.commons.io.IOUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.util.JSONPObject;
import org.codehaus.jackson.type.TypeReference;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.*;
import java.util.Observable;
import java.util.concurrent.*;
import java.util.stream.Stream;
import org.json.JSONObject;

public class FalkonryService {

    private HttpService httpService;

    public FalkonryService(String host, String token) throws Exception {
        this.httpService = new HttpService(host, token);
    }

    public Datastream createDatastream(Datastream datastream) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Datastream ds = new Datastream();

        ds.setName(datastream.getName());
        if (datastream.getDatasource() != null) {
            ds.setDatasource(datastream.getDatasource());
        }
        if (datastream.getField() != null) {
            ds.setField(datastream.getField());
        }

        String datastream_json = httpService.post("/datastream", mapper.writeValueAsString(ds));
        return mapper.readValue(datastream_json, Datastream.class);
    }

    public List<Datastream> getDatastreams() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String datastream_json = httpService.get("/datastream");
        return mapper.readValue(datastream_json, new TypeReference<List<Datastream>>() {
        });
    }

    public Datastream getUpdatedDatastream(String id) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String url = "/datastream/" + id;
        String datastream_json = httpService.get(url);
        return mapper.readValue(datastream_json, Datastream.class);
    }

    public void deleteDatastream(String datastream) throws Exception {
        httpService.delete("/datastream/" + datastream);
    }

    public Assessment createAssessment(Assessment assessment) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        AssessmentRequest assessmentRequest = new AssessmentRequest();

        assessmentRequest.setName(assessment.getName());
        assessmentRequest.setAssessmentRate(assessmentRequest.getAssessmentRate());
        assessmentRequest.setDatastream(assessmentRequest.getDatastream());

        String assessment_json = httpService.post("/assessment", mapper.writeValueAsString(assessmentRequest));
        return mapper.readValue(assessment_json, Assessment.class);
    }

    public List<Assessment> getAssessments() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String assessment_json = httpService.get("/assessment");
        return mapper.readValue(assessment_json, new TypeReference<List<Assessment>>() {
        });
    }

    public void deleteAssessment(String assessment) throws Exception {
        httpService.delete("/assessment/" + assessment);
    }

    public InputStatus addInputData(String datastream, String data, Map<String, String> options) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> ops = new HashMap<String, String>();
        String url = "/datastream/" + datastream;
        if (options.containsKey("subscription")) {
            url += "?subscriptionKey=" + options.get("subscription");
        }
        byte[] data_bytes = data.getBytes(Charset.forName("UTF-8"));
        InputStream stream = new ByteArrayInputStream(data_bytes);
        String status = this.httpService.postData(url, data);
        return mapper.readValue(status, InputStatus.class);
    }

    public String addFacts(String assessment, String data, Map<String, String> options) throws Exception {
        String url = "/assessment/" + assessment + "/facts";
        return this.httpService.postData(url, data);
    }

    public InputStatus addInputFromStream(String datastream, ByteArrayInputStream stream, Map<String, String> options) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String url = "/datastream/" + datastream;
        if (options.containsKey("subscription")) {
            url += "?subscriptionKey=" + options.get("subscription");
        }
        byte[] data_bytes = IOUtils.toByteArray(stream);
        String status = this.httpService.upstream(url, data_bytes);
        return mapper.readValue(status, InputStatus.class);
    }

    public String addFactsStream(String assessment, ByteArrayInputStream stream, Map<String, String> options) throws Exception {
        String url = "/assessment/" + assessment + "/facts";
        byte[] data_bytes = IOUtils.toByteArray(stream);
        return this.httpService.upstream(url, data_bytes);
    }

    public BufferedReader getOutput(String assessment, Long start, Long end) throws Exception {
        String url = "/assessment/" + assessment + "/output?";
        if (end != null) {
            url += "lastTime=" + end;
            if (start != null) {
                url += "&startTime=" + start;
            }
        } else {
            if (start != null) {
                url += "startTime=" + start;
            }
        }
        return this.httpService.downstream(url);
    }

    public HttpResponseFormat GetHistoricalOutput(Assessment assessment, Map<String, String> options) {
        String url = "/assessment/" + assessment.getId() + "/output?";
        String trackerId;
        String modelIndex;
        String startTime;
        String endTime;
        Boolean firstReqParam = true;

        if (options.containsKey("trackerId")) {

            firstReqParam = false;
            url += "trackerId=" + options.get("trackerId");
        }

        if (options.containsKey("modelIndex")) {

            if (firstReqParam) {
                firstReqParam = false;
                url += "model=" + options.get("modelIndex");
            } else {
                url += "&model=" + options.get("modelIndex");
            }
        }

        if (options.containsKey("startTime")) {

            if (firstReqParam) {
                firstReqParam = false;
                url += "startTime=" + options.get("startTime");
            } else {
                url += "&startTime=" + options.get("startTime");
            }
        }

        if (options.containsKey("endTime")) {

            if (firstReqParam) {
                url += "endTime=" + options.get("endTime");
            } else {
                url += "&endTime=" + options.get("endTime");
            }
        }

        String format;
        String responseFromat = "application/json";
        if (options.containsKey("responseFromat")) {

            format = options.get("responseFromat");
            if (format.equals("text/csv")) {
                responseFromat = format;
            }
        }

        HttpResponseFormat outputData = httpService.GetOutput(url, responseFromat);
        return outputData;
    }

    // Post EntityMeta
    public List<EntityMeta> PostEntityMeta(List<EntityMetaRequest> entityMetaRequest, Datastream datastream) throws Exception{
//        var data = JsonConvert.SerializeObject(entityMetaRequest, Formatting.Indented,
//                new JsonSerializerSettings() {
//            ContractResolver  = new CamelCasePropertyNamesContractResolver() });
//        String response = HttpService.Post("/datastream/" + datastream.getId() + "/entityMeta", data);
//        return JsonConvert.DeserializeObject < List < EntityMeta >> (response);
//
//        ObjectMapper mapper = new ObjectMapper();
//        EntityMetaRequest emr = new EntityMetaRequest();
//
//        emr.setLabel(entityMetaRequest.g());
//        if (datastream.getDatasource() != null) {
//            ds.setDatasource(datastream.getDatasource());
//        }
//        if (datastream.getField() != null) {
//            ds.setField(datastream.getField());
//        }
        ObjectMapper mapper = new ObjectMapper();
        String entityMetaRequest_json = httpService.post("/datastream", mapper.writeValueAsString(entityMetaRequest));
        return mapper.readValue(entityMetaRequest_json, List.class);
    }

    // Get EntityMeta
    public List<EntityMeta> GetEntityMeta(Datastream datastream) throws Exception{
        
        
        ObjectMapper mapper = new ObjectMapper();
        String entityMeta_json = httpService.get("/datastream/" + datastream.getId() + "/entityMeta");
        return mapper.readValue(entityMeta_json, new TypeReference<List<EntityMeta>>() {
        });
    }

    /*private class StreamingThread extends Observable implements Runnable {
    String assessment = "";
    Long start = 0l;
    Boolean awaitingResponse = false;
    private StreamingThread (String assessment, Long start) throws Exception {
      assessment = this.assessment;
      start = this.start;
    }
    public void run() {
      BufferedReader data = null;
      if (!awaitingResponse) {
        data = outflowData(assessment);
      }
      if (data != null) {
        setChanged();
        notifyObservers(data);
      }
    }
    private BufferedReader outflowData (String assessment) {
      //BufferedReader assessmentOutflowData = null;
      try {
        if(assessmentOpen()) {
          System.out.println("Start : " + start);
          String url = "/assessment/" + assessment + "/output?startTime=" + start;
          //assessmentOutflowData =
          return httpService.downstream(url);
        }
        else {
          return null;
        }
      } catch (Exception e) {
        System.out.println("Error : " + e);
      }
      return null;
    }

    private boolean assessmentOpen() throws Exception {
      String url = "/Assessment/" + assessment;
      String assessment_json = httpService.get("/assessment");
      //JSONPObject assessment_jsonpobject = new JSONPObject(, assessment_json);
      //ObjectMapper mapper = new ObjectMapper();
      //if(mapper.readValue(assessment_json, Object[].class) == "CLOSED") {}
      JSONObject outflowStatus = new JSONObject(assessment_json);
      return (outflowStatus.get("outflowStatus") == "OPEN");
    }
  }

  private class StreamObserver implements Observer {
    private String outflowData = "";

    @Override
    public void update(Observable o, Object arg) {
      //System.out.println("Data received  : " + arg);
      outflowData = arg.toString();
    }
    public String getData () {
      return  outflowData;
    }
  }

  public Observable streamOutput(String assessment, Long start) {
    String data;
    //StreamObserver outflowObserver = new StreamObserver();
    try {
      //StreamingThread streamingThread = new StreamingThread(assessment, start);
      //streamingThread.addObserver(outflowObserver);
      return (new StreamingThread(assessment, start));
    } catch (Exception e) {
      System.out.println("Error instantiating streamingThread : " + e);
    }
    return null;
  }

  private class StreamingThread {     // implements Runnable {
    ScheduledExecutorService scheduledExecutorService =
            Executors.newScheduledThreadPool(3);
    ScheduledFuture scheduledFuture =
            scheduledExecutorService.schedule(new Callable() {
              public Object call() throws Exception {
                System.out.println("Executed!");
                return "Called!";
              }
            }, 5, TimeUnit.SECONDS);

    String assessment = "";
    Long start = 0l;
    Boolean awaitingResponse = false;
    private StreamingThread (String assessment, Long start) throws Exception {
      assessment = this.assessment;
      start = this.start;
    }
    public void run() {
      BufferedReader data = null;
      if (!awaitingResponse) {
        data = outflowData(assessment);
      }
      if (data != null) {
        //setChanged();
        //notifyObservers(data);
      }
    }
    private BufferedReader outflowData (String assessment) {
      //BufferedReader assessmentOutflowData = null;
      try {
        if(assessmentOpen()) {
          System.out.println("Start : " + start);
          String url = "/assessment/" + assessment + "/output?startTime=" + start;
          //assessmentOutflowData =
          return httpService.downstream(url);
        }
        else {
          return null;
        }
      } catch (Exception e) {
        System.out.println("Error : " + e);
      }
      return null;
    }

    private boolean assessmentOpen() throws Exception {
      String url = "/Assessment/" + assessment;
      String assessment_json = httpService.get("/assessment");
      //JSONPObject assessment_jsonpobject = new JSONPObject(, assessment_json);
      //ObjectMapper mapper = new ObjectMapper();
      //if(mapper.readValue(assessment_json, Object[].class) == "CLOSED") {}
      JSONObject outflowStatus = new JSONObject(assessment_json);
      return (outflowStatus.get("outflowStatus") == "OPEN");
    }
  }
     */
    // public Observer streamOutput(String assessment, Long start) {
    //   String data;
    //     try {
    //     } catch (Exception e) {
    //       System.out.println("Error instantiating streamingThread : " + e);
    //     }
    //   return null;
    // }
}
