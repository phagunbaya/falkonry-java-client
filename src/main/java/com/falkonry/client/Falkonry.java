package com.falkonry.client;

/*!
 * falkonry-java-client
 * Copyright(c) 2016 Falkonry Inc
 * MIT Licensed
 */
import com.falkonry.client.service.FalkonryService;
import com.falkonry.helper.models.*;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import java.util.Observer;

public class Falkonry {

    private FalkonryService falkonryService;

    public Falkonry(String host, String token) throws Exception {
        this.falkonryService = new FalkonryService(host, token);
    }

    public Datastream createDatastream(Datastream datastream) throws Exception {
        return falkonryService.createDatastream(datastream);
    }

    public List<Datastream> getDatastreams() throws Exception {
        return falkonryService.getDatastreams();
    }

    public Datastream getUpdatedDatastream(String id) throws Exception {
        return falkonryService.getUpdatedDatastream(id);
    }
    
    public Datastream updateDatastream(Datastream datastream) throws Exception {
        return falkonryService.updateDatastream(datastream);
    }

    public void deleteDatastream(String datastream) throws Exception {
        falkonryService.deleteDatastream(datastream);
    }

    public Assessment createAssessment(AssessmentRequest assessmentRequest) throws Exception {
        return falkonryService.createAssessment(assessmentRequest);
    }

    public Assessment getAssessment(String assessment_id) throws Exception {
        return falkonryService.getAssessment(assessment_id);
    }
    
    public Assessment updateAssessment(Assessment assessment) throws Exception {
        return falkonryService.updateAssessment(assessment);
    }

    public List<Assessment> getAssessments() throws Exception {
        return falkonryService.getAssessments();
    }

    public void deleteAssessment(String assessment) throws Exception {
        this.falkonryService.deleteAssessment(assessment);
    }

    public InputStatus addInput(String datastream, String data, Map<String, String> options) throws Exception {
        return this.falkonryService.addInputData(datastream, data, options);
    }

    public String addFacts(String assessment, String data, Map<String, String> options) throws Exception {
        return this.falkonryService.addFacts(assessment, data, options);
    }

    public InputStatus addInputStream(String datastream, ByteArrayInputStream stream, Map<String, String> options) throws Exception {
        return this.falkonryService.addInputFromStream(datastream, stream, options);
    }

    public String addFactsStream(String assessment, ByteArrayInputStream stream, Map<String, String> options) throws Exception {
        return this.falkonryService.addFactsStream(assessment, stream, options);
    }

    public BufferedReader getOutput(String assessment, Long start, Long end) throws Exception {
        return this.falkonryService.getOutput(assessment, start, end);
    }

    public HttpResponseFormat GetHistoricalOutput(Assessment assessment, Map<String, String> options) throws UnsupportedEncodingException {
        return this.falkonryService.GetHistoricalOutput(assessment, options);
    }

  // public Observer streamOutput(String assessment, Long start) throws Exception {
  //   return this.falkonryService.streamOutput(assessment, start);
  // }

   
}
