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

/**
 *
 * @author dev-falkonry-10
 */
public class Falkonry {

    private FalkonryService falkonryService;

    /**
     *
     * @param host
     * @param token
     * @throws Exception
     */
    public Falkonry(String host, String token) throws Exception {
        this.falkonryService = new FalkonryService(host, token);
    }

    /**
     *
     * @param datastream
     * @return
     * @throws Exception
     */
    public Datastream createDatastream(Datastream datastream) throws Exception {
        return falkonryService.createDatastream(datastream);
    }

    /**
     *
     * @return
     * @throws Exception
     */
    public List<Datastream> getDatastreams() throws Exception {
        return falkonryService.getDatastreams();
    }

    /**
     *
     * @param id is datastream.id
     * @return
     * @throws Exception
     */
    public Datastream getDatastream(String id) throws Exception {
        return falkonryService.getDatastream(id);
    }
    
    /**
     *
     * @param datastream
     * @return
     * @throws Exception
     */
    public Datastream updateDatastream(Datastream datastream) throws Exception {
        return falkonryService.updateDatastream(datastream);
    }

    /**
     *
     * @param id is datastream.id
     * @throws Exception
     */
    public void deleteDatastream(String id) throws Exception {
        falkonryService.deleteDatastream(id);
    }

    /**
     *
     * @param assessmentRequest
     * @return
     * @throws Exception
     */
    public Assessment createAssessment(AssessmentRequest assessmentRequest) throws Exception {
        return falkonryService.createAssessment(assessmentRequest);
    }

    /**
     *
     * @param id is assessment.id
     * @return
     * @throws Exception
     */
    public Assessment getAssessment(String id) throws Exception {
        return falkonryService.getAssessment(id);
    }
    
    /**
     *
     * @param assessment
     * @return
     * @throws Exception
     */
    public Assessment updateAssessment(Assessment assessment) throws Exception {
        return falkonryService.updateAssessment(assessment);
    }

    /**
     *
     * @return
     * @throws Exception
     */
    public List<Assessment> getAssessments() throws Exception {
        return falkonryService.getAssessments();
    }

    /**
     *
     * @param id is assessment.id
     * @throws Exception
     */
    public void deleteAssessment(String id) throws Exception {
        this.falkonryService.deleteAssessment(id);
    }

    /**
     *
     * @param id is datastream.id
     * @param data
     * @param options
     * @return
     * @throws Exception
     */
    public InputStatus addInput(String id, String data, Map<String, String> options) throws Exception {
        return this.falkonryService.addInputData(id, data, options);
    }

    /**
     *
     * @param id is assessment.id
     * @param data
     * @param options
     * @return
     * @throws Exception
     */
    public FalkonryClientReponse addFacts(String id, String data, Map<String, String> options) throws Exception {
        return this.falkonryService.addFacts(id, data, options);
    }

    /**
     *
     * @param id is datastream.id
     * @param stream
     * @param options
     * @return
     * @throws Exception
     */
    public InputStatus addInputStream(String id, ByteArrayInputStream stream, Map<String, String> options) throws Exception {
        return this.falkonryService.addInputFromStream(id, stream, options);
    }

    /**
     *
     * @param id is assessment.id
     * @param stream
     * @param options
     * @return
     * @throws Exception
     */
    public FalkonryClientReponse addFactsStream(String id, ByteArrayInputStream stream, Map<String, String> options) throws Exception {
        return this.falkonryService.addFactsStream(id, stream, options);
    }

    /**
     *
     * @param id is assessment.id
     * @param start
     * @param end
     * @return
     * @throws Exception
     */
    public BufferedReader getOutput(String id, Long start, Long end) throws Exception {
        return this.falkonryService.getOutput(id, start, end);
    }

    /**
     *
     * @param assessment
     * @param options
     * @return
     * @throws UnsupportedEncodingException
     */
    public HttpResponseFormat GetHistoricalOutput(Assessment assessment, Map<String, String> options) throws UnsupportedEncodingException {
        return this.falkonryService.GetHistoricalOutput(assessment, options);
    }

//   public Observer streamOutput(String assessment, Long start) throws Exception {
//     return this.falkonryService.streamOutput(assessment, start);
//   }

   
}
