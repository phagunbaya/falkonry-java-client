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
	 * @param host         Falkonry service url
	 * @param token        Integration token
	 * @throws Exception
	 */
	public Falkonry(String host, String token) throws Exception {
		this.falkonryService = new FalkonryService(host, token);
	}

	/**
	 * Create new datastream
	 * @param datastream   Datastream object
	 * @throws Exception
	 * @return             The newly created datastream
	 */
	public Datastream createDatastream(Datastream datastream) throws Exception {
		return falkonryService.createDatastream(datastream);
	}

	/**
	 * Get list of datastreams
	 * @throws Exception
	 * @return             List of datastreams
	 */
	
	public List<Datastream> getDatastreams() throws Exception {
		return falkonryService.getDatastreams();
	}

	/**
	 * Get datastream by id
	 * @param id           Datastream id
	 * @throws Exception
	 * @return             Datastream object
	 */
	public Datastream getDatastream(String id) throws Exception {
		return falkonryService.getDatastream(id);
	}

	/**
	 * Update the Datastream
	 * @param datastream   Datastream object
	 * @throws Exception
	 * @return             Updated Datastream object
	 */
	public Datastream updateDatastream(Datastream datastream) throws Exception {
		return falkonryService.updateDatastream(datastream);
	}

	/**
	 * Delete Datastream using id
	 * @param id           Datastream id
	 * @throws Exception
	 */
	public void deleteDatastream(String id) throws Exception {
		falkonryService.deleteDatastream(id);
	}

	/**
	 * Create new Assessment
	 * @param assessmentRequest Request object for creating new assessment
	 * @throws Exception
	 * @return                  The newly created Assessment
	 */
	public Assessment createAssessment(AssessmentRequest assessmentRequest) throws Exception {
		return falkonryService.createAssessment(assessmentRequest);
	}

	/**
	 * Get assessment by id
	 * @param id           Assessment id
	 * @throws Exception
	 * @return             Assessment object
	 */
	public Assessment getAssessment(String id) throws Exception {
		return falkonryService.getAssessment(id);
	}

	/**
	 * Update the Assessment
	 * @param assessment   Assessment object
	 * @throws Exception 
	 * @return             Updated Assessment object
	 */
	public Assessment updateAssessment(Assessment assessment) throws Exception {
		return falkonryService.updateAssessment(assessment);
	}

	/**
	 * Get list of assessments
	 * @throws Exception
	 * @return            List of Assessments
	 */
	public List<Assessment> getAssessments() throws Exception {
		return falkonryService.getAssessments();
	}

	/**
	 * Delete Assessment using id
	 * @param id           Assessment id
	 * @throws Exception
	 */
	public void deleteAssessment(String id) throws Exception {
		this.falkonryService.deleteAssessment(id);
	}

	/**
	 * Add input data as string to the datastream  
	 * @param id           Datastream id
	 * @param data         Input data as string in csv/json format
	 * @param options      Map of parameters like hasMoreData, steaming and various field identifiers in data
	 * @throws Exception
	 * @return             Status of data ingestion
	 */
	public InputStatus addInput(String id, String data, Map<String, String> options) throws Exception {
		return this.falkonryService.addInputData(id, data, options);
	}

	/**
	 * Add fact data as string to the assessment
	 * @param id           Assessment id
	 * @param data         Fact data as string in csv/json format
	 * @param options      Map of parameters for various field identifiers in data
	 * @throws Exception
	 * @return             Status of data ingestion
	 */
	public InputStatus addFacts(String id, String data, Map<String, String> options) throws Exception {
		return this.falkonryService.addFacts(id, data, options);
	}

	/**
	 * Add input data as ByteArrayInputStream to the datastream
	 * @param id           Datastream id
	 * @param stream       Input data as ByteArrayInputStream in csv/json format
	 * @param options      Map of parameters like hasMoreData, steaming and various field identifiers in data
	 * @throws Exception
	 * @return             Status of data ingestion
	 */
	public InputStatus addInputStream(String id, ByteArrayInputStream stream, Map<String, String> options)
			throws Exception {
		return this.falkonryService.addInputFromStream(id, stream, options);
	}

	/**
	 * Add fact data as ByteArrayInputStream to the assessment
	 * @param id           Assessment id
	 * @param stream       Fact data as ByteArrayInputStream in csv/json format
	 * @param options      Map of parameters for various field identifiers in data
	 * @throws Exception
	 * @return             Status of data ingestion
	 */
	public InputStatus addFactsStream(String id, ByteArrayInputStream stream, Map<String, String> options)
			throws Exception {
		return this.falkonryService.addFactsStream(id, stream, options);
	}

	/**
	 * Get live streaming assessment output
	 * @param id              Assessment id
	 * @throws Exception
	 * @return                BufferedReader for consuming assessment output
	 */
	public BufferedReader getOutput(String id) throws Exception {
		return this.falkonryService.getOutput(id);
	}

	/**
	 * Get assessment output for historical input data
	 * @param assessment          Assessment object
	 * @param options             Map of parameters like startTime, endTime, responseFormat, modelIndex
	 * @throws Exception
	 * @return                    Assessment output as HttpResponseFormat for historical input data
	 */
	public HttpResponseFormat getHistoricalOutput(Assessment assessment, Map<String, String> options) throws Exception {
		return this.falkonryService.getHistoricalOutput(assessment, options);
	}

	/**
	 * Turn live monitoring on for a datastream. Each assessment associated with the datastream will be turned on for live monitoring.
	 * @param id                 Datastream id
	 * @throws Exception
	 * @return                   List of assessments for which live monitoring is turned on
	 */
	public List<Assessment> onDatastream(String id) throws Exception {
		return this.falkonryService.onDatastream(id);
	}

	/**
	 * Turn live monitoring off for a datastream. Each assessment associated with the datastream will be turned off for live monitoring.
	 * @param id                Datastream id
	 * @throws Exception
	 * @return                  List of assessments for which live monitoring is turned off
	 */
	public List<Assessment> offDatastream(String id) throws Exception {
		return this.falkonryService.offDatastream(id);
	}

	/**
	 * Turn live monitoring on for an assessment
	 * @param id           Assessment id
	 * @return             Assessment object that turned on
	 * @throws Exception
	 */
	public Assessment onAssessment(String id) throws Exception {
		return this.falkonryService.onAssessment(id);
	}

	/**
	 * Turn live monitoring off for an assessment
	 * @param id           Assessment id
	 * @return Assessment  Assessment object that turned off
	 * @throws Exception
	 */
	public Assessment offAssessment(String id) throws Exception {
		return this.falkonryService.offAssessment(id);
	}

	/**
	 * Get list of EntityMeta of a datastream
	 * @param datastreamId      Datastream id
	 * @throws Exception
	 * @return                  List of EntityMeta of a datastream
	 */
	public List<EntityMeta> getEntityMeta(String datastreamId) throws Exception {
		return this.falkonryService.getEntityMeta(datastreamId);
	}

	/**
	 * Add EntityMeta to a datastream
	 * @param entityMetaRequest  List of EntityMetaRequest 
	 * @param datastreamId       Datastream id
	 * @throws Exception
	 * @return                   List of newly added EntityMeta
	 */
	public List<EntityMeta> postEntityMeta(List<EntityMetaRequest> entityMetaRequest, String datastreamId)
			throws Exception {
		return this.falkonryService.postEntityMeta(entityMetaRequest, datastreamId);
	}
	
	/**
	 * Extract fact data from an assessment
	 * @param assessmentId         Assessment id
	 * @param options              Map of parameters like startTime, endTime, responseFormat, modelIndex
	 * @throws Exception
	 * @return                     Fact data as HttpResponseFormat for an assessment
	 */
	public HttpResponseFormat getFactsData(String assessmentId, Map<String, String> options) throws Exception {
		return this.falkonryService.getFactsData(assessmentId, options);
	}
	
	/**
	 * Extract input data from a datastream
	 * @param datastreamId         Datastream id
	 * @param options              Map of parameters like responseFormat
	 * @throws Exception
	 * @return                     Input data as HttpResponseFormat for a datastream
	 */
	public HttpResponseFormat getInputData(String datastreamId, Map<String, String> options) throws Exception {
		return this.falkonryService.getInputData(datastreamId, options);
	}

	/**
	 * Get status of the tracker
	 * @param id           Tracker id
	 * @throws Exception
	 * @return             Tracker object
	 */
	public Tracker getStatus(String id) throws Exception {
		return this.falkonryService.getStatus(id);
	}

}
