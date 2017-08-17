package com.falkonry.client;

/*!
 * falkonry-java-client
 * Copyright(c) 2017 Falkonry Inc
 * MIT Licensed
 */

import com.falkonry.client.service.FalkonryService;
import com.falkonry.helper.models.*;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Map;

/**
 * Falkonry client class to access APIs
 */
public class Falkonry {

	private FalkonryService falkonryService;

	/**
	 * Falkonry client class to access APIs
	 * @param host
	 * @param token
	 * @throws Exception
	 */
	public Falkonry(String host, String token) throws Exception {
		this.falkonryService = new FalkonryService(host, token);
	}

	/**
	 * createDatastream
	 * @param datastream
	 * @throws Exception
	 * @return Datastream
	 */
	public Datastream createDatastream(Datastream datastream) throws Exception {
		return falkonryService.createDatastream(datastream);
	}

	/**
	 * getDatastreams
	 * @throws Exception
	 * @return List
	 */
	public List<Datastream> getDatastreams() throws Exception {
		return falkonryService.getDatastreams();
	}

	/**
	 * getDatastream
	 * @param id DataStream id
	 * @throws Exception
	 * @return Datastream
	 */
	public Datastream getDatastream(String id) throws Exception {
		return falkonryService.getDatastream(id);
	}

	/**
	 * updateDatastream
	 * @param datastream
	 * @throws Exception
	 * @return Datastream
	 */
	public Datastream updateDatastream(Datastream datastream) throws Exception {
		return falkonryService.updateDatastream(datastream);
	}

	/**
	 * deleteDatastream
	 * @param id DataStream id
	 * @throws Exception
	 */
	public void deleteDatastream(String id) throws Exception {
		falkonryService.deleteDatastream(id);
	}

	/**
	 * createAssessment
	 * @param assessmentRequest
	 * @throws Exception
	 * @return Assessment
	 */
	public Assessment createAssessment(AssessmentRequest assessmentRequest) throws Exception {
		return falkonryService.createAssessment(assessmentRequest);
	}

	/**
	 * getAssessment
	 * @param id Assessment id
	 * @throws Exception
	 * @return Assessment
	 */
	public Assessment getAssessment(String id) throws Exception {
		return falkonryService.getAssessment(id);
	}

	/**
	 * updateAssessment
	 * @param assessment
	 * @throws Exception
	 * @return Assessment
	 */
	public Assessment updateAssessment(Assessment assessment) throws Exception {
		return falkonryService.updateAssessment(assessment);
	}

	/**
	 * getAssessments
	 * @throws Exception
	 * @return List
	 */
	public List<Assessment> getAssessments() throws Exception {
		return falkonryService.getAssessments();
	}

	/**
	 * deleteAssessment
	 * @param id Assessment id
	 * @throws Exception
	 */
	public void deleteAssessment(String id) throws Exception {
		this.falkonryService.deleteAssessment(id);
	}

	/**
	 * addInput
	 * @param id DataStream id
	 * @param data
	 * @param options
	 * @throws Exception
	 * @return InputStatus
	 */
	public InputStatus addInput(String id, String data, Map<String, String> options) throws Exception {
		return this.falkonryService.addInputData(id, data, options);
	}

	/**
	 * addFacts
	 * @param id Assessment id
	 * @param data
	 * @param options
	 * @throws Exception
	 * @return InputStatus
	 */
	public InputStatus addFacts(String id, String data, Map<String, String> options) throws Exception {
		return this.falkonryService.addFacts(id, data, options);
	}

	/**
	 * addInputStream
	 * @param id DataStream id
	 * @param stream
	 * @param options
	 * @throws Exception
	 * @return InputStatus
	 */
	public InputStatus addInputStream(String id, ByteArrayInputStream stream, Map<String, String> options)
			throws Exception {
		return this.falkonryService.addInputFromStream(id, stream, options);
	}

	/**
	 * addFactsStream
	 * @param id Assessment id
	 * @param stream
	 * @param options
	 * @throws Exception
	 * @return InputStatus
	 */
	public InputStatus addFactsStream(String id, ByteArrayInputStream stream, Map<String, String> options)
			throws Exception {
		return this.falkonryService.addFactsStream(id, stream, options);
	}

	/**
	 * getOutput
	 * @param id Assessment id
	 * @throws Exception
	 * @return BufferedReader
	 */
	public BufferedReader getOutput(String id) throws Exception {
		return this.falkonryService.getOutput(id);
	}

	/**
	 * getHistoricalOutput
	 * @param assessment
	 * @param options
	 * @throws Exception
	 * @return HttpResponseFormat
	 */
	public HttpResponseFormat getHistoricalOutput(Assessment assessment, Map<String, String> options) throws Exception {
		return this.falkonryService.getHistoricalOutput(assessment, options);
	}

	/**
	 * onDatastream
	 * @param id DataStream id
	 * @throws Exception
	 * @return List
	 */
	public List<Assessment> onDatastream(String id) throws Exception {
		return this.falkonryService.onDatastream(id);
	}

	/**
	 * offDatastream
	 * @param id DataStream id
	 * @throws Exception
	 * @return List
	 */
	public List<Assessment> offDatastream(String id) throws Exception {
		return this.falkonryService.offDatastream(id);
	}

	/**
	 * getEntityMeta
	 * @param datastreamId
	 * @throws Exception
	 * @return List
	 */
	public List<EntityMeta> getEntityMeta(String datastreamId) throws Exception {
		return this.falkonryService.getEntityMeta(datastreamId);
	}

	/**
	 * postEntityMeta
	 * @param entityMetaRequest
	 * @param datastreamId
	 * @throws Exception
	 * @return List
	 */
	public List<EntityMeta> postEntityMeta(List<EntityMetaRequest> entityMetaRequest, String datastreamId)
			throws Exception {
		return this.falkonryService.postEntityMeta(entityMetaRequest, datastreamId);
	}
	
	/**
	 * getFactsData
	 * @param assessment
	 * @param options
	 * @throws Exception
	 * @return HttpResponseFormat
	 */
	public HttpResponseFormat getFactsData(String assessmentId, Map<String, String> options) throws Exception {
		return this.falkonryService.getFactsData(assessmentId, options);
	}
	
	/**
	 * getDatastreamInputData
	 * @param datastream
	 * @param options
	 * @throws Exception
	 * @return HttpResponseFormat
	 */
	public HttpResponseFormat getInputData(String datastreamId, Map<String, String> options) throws Exception {
		return this.falkonryService.getInputData(datastreamId, options);
	}

}
