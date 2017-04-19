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
import java.util.List;
import java.util.Map;

/**
 *
 */
public class Falkonry {

	/**
	 * 
	 */
	private FalkonryService falkonryService;

	/**
	 * @param host
	 * @param token
	 * @throws Exception
	 */
	public Falkonry(String host, String token) throws Exception {
		this.falkonryService = new FalkonryService(host, token);
	}

	/**
	 * @param datastream
	 * @throws Exception
	 * @return Datastream
	 */
	public Datastream createDatastream(Datastream datastream) throws Exception {
		return falkonryService.createDatastream(datastream);
	}

	/**
	 * @throws Exception
	 * @return List<Datastream>
	 */
	public List<Datastream> getDatastreams() throws Exception {
		return falkonryService.getDatastreams();
	}

	/**
	 * @param id
	 * @throws Exception
	 * @return Datastream
	 */
	public Datastream getDatastream(String id) throws Exception {
		return falkonryService.getDatastream(id);
	}

	/**
	 * @param datastream
	 * @throws Exception
	 * @return Datastream
	 */
	public Datastream updateDatastream(Datastream datastream) throws Exception {
		return falkonryService.updateDatastream(datastream);
	}

	/**
	 * @param id
	 * @throws Exception
	 * @return void
	 */
	public void deleteDatastream(String id) throws Exception {
		falkonryService.deleteDatastream(id);
	}

	/**
	 * @param assessmentRequest
	 * @throws Exception
	 * @return Assessment
	 */
	public Assessment createAssessment(AssessmentRequest assessmentRequest) throws Exception {
		return falkonryService.createAssessment(assessmentRequest);
	}

	/**
	 * @param id
	 * @throws Exception
	 * @return Assessment
	 */
	public Assessment getAssessment(String id) throws Exception {
		return falkonryService.getAssessment(id);
	}

	/**
	 * @param assessment
	 * @throws Exception
	 * @return Assessment
	 */
	public Assessment updateAssessment(Assessment assessment) throws Exception {
		return falkonryService.updateAssessment(assessment);
	}

	/**
	 * 
	 * @throws Exception
	 * @return List<Assessment>
	 */
	public List<Assessment> getAssessments() throws Exception {
		return falkonryService.getAssessments();
	}

	/**
	 * @param id
	 * @throws Exception
	 * @return void
	 */
	public void deleteAssessment(String id) throws Exception {
		this.falkonryService.deleteAssessment(id);
	}

	/**
	 * @param id
	 * @param data
	 * @param options
	 * @throws Exception
	 * @return InputStatus
	 */
	public InputStatus addInput(String id, String data, Map<String, String> options) throws Exception {
		return this.falkonryService.addInputData(id, data, options);
	}

	/**
	 * @param id
	 * @param data
	 * @param options
	 * @throws Exception
	 * @return InputStatus
	 */
	public InputStatus addFacts(String id, String data, Map<String, String> options) throws Exception {
		return this.falkonryService.addFacts(id, data, options);
	}

	/**
	 * @param id
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
	 * @param id
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
	 * @param id
	 * @param start
	 * @param end
	 * @throws Exception
	 * @return BufferedReader
	 */
	public BufferedReader getOutput(String id, Long start, Long end) throws Exception {
		return this.falkonryService.getOutput(id, start, end);
	}

	/**
	 * @param assessment
	 * @param options
	 * @throws Exception
	 * @return HttpResponseFormat
	 */
	public HttpResponseFormat getHistoricalOutput(Assessment assessment, Map<String, String> options) throws Exception {
		return this.falkonryService.getHistoricalOutput(assessment, options);
	}

	/**
	 * @param id
	 * @throws Exception
	 * @return List<Assessment>
	 */
	public List<Assessment> onDatastream(String id) throws Exception {
		return this.falkonryService.onDatastream(id);
	}

	/**
	 * @param id
	 * @throws Exception
	 * @return List<Assessment>
	 */
	public List<Assessment> offDatastream(String id) throws Exception {
		return this.falkonryService.offDatastream(id);
	}

	/**
	 * @param datastreamId
	 * @throws Exception
	 * @return List<EntityMeta>
	 */
	public List<EntityMeta> getEntityMeta(String datastreamId) throws Exception {
		return this.falkonryService.getEntityMeta(datastreamId);
	}

	/**
	 * @param entityMetaRequest
	 * @param datastreamId
	 * @throws Exception
	 * @return List<EntityMeta>
	 */
	public List<EntityMeta> postEntityMeta(List<EntityMetaRequest> entityMetaRequest, String datastreamId)
			throws Exception {
		return this.falkonryService.postEntityMeta(entityMetaRequest, datastreamId);
	}

}
