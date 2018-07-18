package com.falkonry.client.service;

/*!
 * falkonry-java-client
 * Copyright(c) 2017 Falkonry Inc
 * MIT Licensed
 */

import com.falkonry.helper.models.*;
import sun.security.util.PropertyExpander.ExpandException;
import org.apache.commons.io.IOUtils;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

/**
 *
 */
public class FalkonryService {

	private HttpService httpService;

	/**
	 * Sets httpService with host and token
	 * @param host         Host of the falkonry
	 * @param token        Integration token
	 * @throws Exception
	 */
	public FalkonryService(String host, String token) throws Exception {
		this.httpService = new HttpService(host, token);
	}

	/**
	 * Create new datastream
	 * @param datastream   Datastream object
	 * @return             The newly created datastream
	 * @throws Exception
	 */
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
		if (datastream.getInputList() != null) {
			ds.setInputList(datastream.getInputList());
		}
		if (datastream.getTimePrecision() != null) {
			ds.setTimePrecision(datastream.getTimePrecision());
		}
		String datastream_json = httpService.post("/datastream", mapper.writeValueAsString(ds));
		return mapper.readValue(datastream_json, Datastream.class);
	}

	/**
	 * Get list of datastreams
	 * @return             List of datastreams
	 * @throws Exception
	 */
	public List<Datastream> getDatastreams() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		String datastream_json = httpService.get("/datastream");
		return mapper.readValue(datastream_json, new TypeReference<List<Datastream>>() {
		});
	}

	/**
	 * Get datastream by id
	 * @param id                    DataStream id
	 * @return                      Datastream object
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 * @throws Exception
	 */
	public Datastream getDatastream(String id) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		String url = "/datastream/" + id;
		String datastream_json = httpService.get(url);
		return mapper.readValue(datastream_json, Datastream.class);

	}

	/**
	 * Update the Datastream
	 * @param datastream   Datastream object
	 * @return             Updated Datastream object
	 * @throws Exception
	 */
	public Datastream updateDatastream(Datastream datastream) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		String url = "/datastream/" + datastream.getId();
		String datastream_json = httpService.put(url, mapper.writeValueAsString(datastream));
		return mapper.readValue(datastream_json, Datastream.class);
	}

	/**
	 * Delete Datastream using id
	 * @param id           DataStream id
	 * @throws Exception
	 */
	public void deleteDatastream(String id) throws Exception {
		httpService.delete("/datastream/" + id);
	}

	/**
	 * Create new Assessment
	 * @param assessmentRequest Request object for creating new assessment
	 * @return                  The newly created Assessment
	 * @throws Exception
	 */
	public Assessment createAssessment(AssessmentRequest assessmentRequest) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		AssessmentRequest as = new AssessmentRequest();

		as.setName(assessmentRequest.getName());
		as.setAssessmentRate(assessmentRequest.getAssessmentRate());
		as.setDatastream(assessmentRequest.getDatastream());

		String assessment_json = httpService.post("/assessment", mapper.writeValueAsString(as));
		return mapper.readValue(assessment_json, Assessment.class);
	}

	/**
	 * Get list of assessments
	 * @return             List of assessments
	 * @throws Exception
	 */
	public List<Assessment> getAssessments() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		String assessment_json = httpService.get("/assessment");
		return mapper.readValue(assessment_json, new TypeReference<List<Assessment>>() {
		});
	}

	/**
	 * Get assessment by id
	 * @param id           Assessment id
	 * @return             Assessment object
	 * @throws Exception
	 */
	public Assessment getAssessment(String id) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		String url = "/assessment/" + id;
		String assessment_json = httpService.get(url);
		return mapper.readValue(assessment_json, Assessment.class);
	}

	/**
	 * Update the Assessment
	 * @param assessment   Assessment object
	 * @return             Updated Assessment object
	 * @throws Exception
	 */
	public Assessment updateAssessment(Assessment assessment) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		String url = "/assessment/" + assessment.getId();
		String assessment_json = httpService.put(url, mapper.writeValueAsString(assessment));
		return mapper.readValue(assessment_json, Assessment.class);
	}

	/**
	 * Delete Assessment using id
	 * @param id           Assessment id
	 * @throws Exception
	 */
	public void deleteAssessment(String id) throws Exception {
		httpService.delete("/assessment/" + id);
	}

	/**
	 * Add String of input data to the datastream
	 * @param id           DataStream id
	 * @param data         String of input data in csv/json format
	 * @param options      Options for hasMoreData, steaming and various field identifiers in data
	 * @return             Status of data ingestion
	 * @throws Exception
	 */
	public InputStatus addInputData(String id, String data, Map<String, String> options) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		String url = getInputIngestionUrl(id, options);
		String status = this.httpService.postData(url, data);
		return mapper.readValue(status, InputStatus.class);
	}

	/**
	 * Add String of facts data to the assessment
	 * @param id           Assessment id
	 * @param data         String of fact data in csv/json format
	 * @param options      Options for various field identifiers in data
	 * @return
	 * @throws Exception   Status of data ingestion
	 */
	public InputStatus addFacts(String id, String data, Map<String, String> options) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		String url = getAddFactsUrl(id, options);
		String status = this.httpService.postData(url, data);
		return mapper.readValue(status, InputStatus.class);
	}

	/**
	 * Add ByteArrayInputStream of input data to the datastream
	 * @param id           Datastream id
	 * @param stream       ByteArrayInputStream of input data in csv/json format
	 * @param options      Options for hasMoreData, steaming and various field identifiers in data
	 * @return             Status of data ingestion
	 * @throws Exception
	 */
	public InputStatus addInputFromStream(String id, ByteArrayInputStream stream, Map<String, String> options)
			throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		String url = getInputIngestionUrl(id, options);
		byte[] data_bytes = IOUtils.toByteArray(stream);
		String status = this.httpService.upstream(url, data_bytes);
		return mapper.readValue(status, InputStatus.class);
	}

	/**
	 * Add ByteArrayInputStream of facts data to the assessment
	 * @param id           Assessment id
	 * @param stream       ByteArrayInputStream of fact data in csv/json format
	 * @param options      Options for various field identifiers in data
	 * @return             Status of data ingestion
	 * @throws Exception
	 */
	public InputStatus addFactsStream(String id, ByteArrayInputStream stream, Map<String, String> options)
			throws Exception {

		ObjectMapper mapper = new ObjectMapper();
		String url = getAddFactsUrl(id, options);
		byte[] data_bytes = IOUtils.toByteArray(stream);
		String status = this.httpService.upstream(url, data_bytes);
		return mapper.readValue(status, InputStatus.class);
	}

	/**
	 * Get url for adding facts data to the assessment
	 * @param assessmentId Assessment id
	 * @param options      Options for various field identifiers in data
	 * @return             Url for adding facts data to the assessment
	 * @throws Exception
	 */
	private String getAddFactsUrl(String assessmentId, Map<String, String> options) throws Exception {

		String url = "/assessment/" + assessmentId + "/facts?";

		Boolean firstReqParam = true;

		if (options.containsKey("startTimeIdentifier")) {
			if (firstReqParam)
				firstReqParam = false;
			else
				url += "&";
			url += "startTimeIdentifier=" + URLEncoder.encode(options.get("startTimeIdentifier"), "UTF-8");
		}
		if (options.containsKey("endTimeIdentifier")) {
			if (firstReqParam)
				firstReqParam = false;
			else
				url += "&";
			url += "endTimeIdentifier=" + URLEncoder.encode(options.get("endTimeIdentifier"), "UTF-8");
		}
		if (options.containsKey("timeFormat")) {
			if (firstReqParam)
				firstReqParam = false;
			else
				url += "&";
			url += "timeFormat=" + URLEncoder.encode(options.get("timeFormat"), "UTF-8");
		}
		if (options.containsKey("timeZone")) {
			if (firstReqParam)
				firstReqParam = false;
			else
				url += "&";
			url += "timeZone=" + URLEncoder.encode(options.get("timeZone"), "UTF-8");
		}
		if (options.containsKey("entityIdentifier")) {
			if (firstReqParam)
				firstReqParam = false;
			else
				url += "&";
			url += "entityIdentifier=" + URLEncoder.encode(options.get("entityIdentifier"), "UTF-8");
		}
		if (options.containsKey("valueIdentifier")) {
			if (firstReqParam)
				firstReqParam = false;
			else
				url += "&";
			url += "valueIdentifier=" + URLEncoder.encode(options.get("valueIdentifier"), "UTF-8");
		}
		if (options.containsKey("additionalKeyword")) {
			if (firstReqParam)
				firstReqParam = false;
			else
				url += "&";
			url += "additionalKeyword=" + URLEncoder.encode(options.get("additionalKeyword"), "UTF-8");
		}
		if (options.containsKey("keywordIdentifier")) {
			if (firstReqParam)
				firstReqParam = false;
			else
				url += "&";
			url += "keywordIdentifier=" + URLEncoder.encode(options.get("keywordIdentifier"), "UTF-8");
		}
		if (options.containsKey("batchIdentifier")) {
			if (firstReqParam)
				firstReqParam = false;
			else
				url += "&";
			url += "batchIdentifier=" + URLEncoder.encode(options.get("batchIdentifier"), "UTF-8");
		}

		return url;
	}

	/**
	 * Get url for adding input data to the datastream
	 * @param datstreamId  Datastream id
	 * @param options      Options for hasMoreData, steaming and various field identifiers in data
	 * @return             Url for adding input data to the datastream
	 * @throws Exception
	 */
	private String getInputIngestionUrl(String datstreamId, Map<String, String> options) throws Exception {

		String url = "/datastream/" + datstreamId + "?";

		if (options.containsKey("streaming")) {
			url += "streaming=" + URLEncoder.encode(options.get("streaming"), "UTF-8");
		} else {
			url += "streaming=true";
		}

		if (options.containsKey("hasMoreData")) {
			url += "&hasMoreData=" + URLEncoder.encode(options.get("hasMoreData"), "UTF-8");
		} else {
			url += "&hasMoreData=true";
		}

		if (options.containsKey("timeFormat")) {
			url += "&timeFormat=" + URLEncoder.encode(options.get("timeFormat"), "UTF-8");
		}

		if (options.containsKey("timeZone")) {
			url += "&timeZone=" + URLEncoder.encode(options.get("timeZone"), "UTF-8");
		}

		if (options.containsKey("timeIdentifier")) {
			url += "&timeIdentifier=" + URLEncoder.encode(options.get("timeIdentifier"), "UTF-8");
		}

		if (options.containsKey("entityIdentifier")) {
			url += "&entityIdentifier=" + URLEncoder.encode(options.get("entityIdentifier"), "UTF-8");
		}

		if (options.containsKey("valueIdentifier")) {
			url += "&valueIdentifier=" + URLEncoder.encode(options.get("valueIdentifier"), "UTF-8");
		}

		if (options.containsKey("signalIdentifier")) {
			url += "&signalIdentifier=" + URLEncoder.encode(options.get("signalIdentifier"), "UTF-8");
		}

		if (options.containsKey("batchIdentifier")) {
			url += "&batchIdentifier=" + URLEncoder.encode(options.get("batchIdentifier"), "UTF-8");
		}

		return url;
	}

	/**
	 * Get live streaming assessment output
	 * @param id           Assessment id
	 * @return             BufferedReader of live streaming assessment output
	 * @throws Exception
	 */
	public BufferedReader getOutput(String id) throws Exception {
		String url = "/assessment/" + id + "/output";
		return this.httpService.downstream(url);
	}

	/**
	 * Turn live monitoring on for a datastream. Each assessment associated with the datastream will be turned on for live monitoring.
	 * @param id           DataStream id
	 * @return             List of assessments for which live monitoring is turned on
	 * @throws Exception
	 */
	public List<Assessment> onDatastream(String id) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		String url = "/datastream/" + id + "/on";
		String assessment_json = httpService.post(url, "");
		return mapper.readValue(assessment_json, new TypeReference<List<Assessment>>() {
		});
	}

	/**
	 * Turn live monitoring off for a datastream. Each assessment associated with the datastream will be turned off for live monitoring.
	 * @param id           DataStream id
	 * @return             List of assessments for which live monitoring is turned off
	 * @throws Exception
	 */
	public List<Assessment> offDatastream(String id) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		String url = "/datastream/" + id + "/off";
		String assessment_json = httpService.post(url, "");
		return mapper.readValue(assessment_json, new TypeReference<List<Assessment>>() {
		});
	}

	/**
	 * Turn live monitoring on for an assessment
	 * @param id            Assessment id
	 * @return              Assessment object that turned on
	 * @throws Exception
	 */
	public Assessment onAssessment(String id) throws Exception {
		Assessment assessmentObj = getAssessment(id);
		ObjectMapper mapper = new ObjectMapper();
		String url = "/datastream/" + assessmentObj.getDatastream() + "/on?assessment=" + id;
		String assessment_json = httpService.post(url, "");
		List<Assessment> assessmentList = mapper.readValue(assessment_json, new TypeReference<List<Assessment>>() {});
		return assessmentList.get(0);
	}

	/**
	 * Turn live monitoring off for an assessment
	 * @param id            Assessment id
	 * @return              Assessment object that turned off
	 * @throws Exception
	 */
	public Assessment offAssessment(String id) throws Exception {
		Assessment assessmentObj = getAssessment(id);
		ObjectMapper mapper = new ObjectMapper();
		String url = "/datastream/" + assessmentObj.getDatastream() + "/off?assessment=" + id;
		String assessment_json = httpService.post(url, "");
		List<Assessment> assessmentList = mapper.readValue(assessment_json, new TypeReference<List<Assessment>>() {});
		return assessmentList.get(0);
	}

	/**
	 * Get historical output generated by an assessment from the historian
	 * @param assessment   Assessment object
	 * @param options      Options for startTime, endTime, responseFormat, modelIndex
	 * @return             HttpResponseFormat for historical output generated by an assessment from the historian
	 * @throws Exception
	 */
	public HttpResponseFormat getHistoricalOutput(Assessment assessment, Map<String, String> options) throws Exception {
		String url = "/assessment/" + assessment.getId() + "/output?";
		String trackerId;
		String modelIndex;
		String startTime;
		String endTime;
		Boolean firstReqParam = true;

		if (options.containsKey("trackerId")) {

			firstReqParam = false;
			url += "trackerId=" + URLEncoder.encode(options.get("trackerId"), "UTF-8");
		}

		if (options.containsKey("modelIndex")) {

			if (firstReqParam) {
				firstReqParam = false;
				url += "model=" + URLEncoder.encode(options.get("modelIndex"), "UTF-8");
			} else {
				url += "&model=" + URLEncoder.encode(options.get("modelIndex"), "UTF-8");
			}
		}

		if (options.containsKey("startTime")) {

			if (firstReqParam) {
				firstReqParam = false;
				url += "startTime=" + URLEncoder.encode(options.get("startTime"), "UTF-8");
			} else {
				url += "&startTime=" + URLEncoder.encode(options.get("startTime"), "UTF-8");
			}
		}

		if (options.containsKey("endTime")) {

			if (firstReqParam) {
				url += "endTime=" + URLEncoder.encode(options.get("endTime"), "UTF-8");
			} else {
				url += "&endTime=" + URLEncoder.encode(options.get("endTime"), "UTF-8");
			}
		}

		String format;
		String responseFormat = "application/json";
		if (options.containsKey("responseFormat")) {

			format = options.get("responseFormat");
			if (format.equals("text/csv")) {
				responseFormat = format;
			}
		}

		HttpResponseFormat outputData = httpService.getOutput(url, responseFormat);
		return outputData;
	}

	// Post EntityMeta

	/**
	 * Add EntityMeta to a datastream
	 * @param entityMetaRequest   List of EntityMetaRequest
	 * @param id                  Datastream id
	 * @throws Exception
	 * @return                    List of newly added EntityMeta
	 */
	public List<EntityMeta> postEntityMeta(List<EntityMetaRequest> entityMetaRequest, String id) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		String entityMetaRequest_json = httpService.post("/datastream/" + id + "/entityMeta",
				mapper.writeValueAsString(entityMetaRequest));
		return mapper.readValue(entityMetaRequest_json, new TypeReference<List<EntityMeta>>() {
		});
	}

	/**
	 * Get list of EntityMeta of a datastream
	 * @param id           Datastream id
	 * @throws Exception
	 * @return             List of EntityMeta of a datastream
	 */
	public List<EntityMeta> getEntityMeta(String id) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		String entityMeta_json = httpService.get("/datastream/" + id + "/entityMeta");
		return mapper.readValue(entityMeta_json, new TypeReference<List<EntityMeta>>() {
		});
	}

	/**
	 * Extract facts data from an assessment
	 * @param assessment   Assessment id
	 * @param options      Options for startTime, endTime, responseFormat, modelIndex
	 * @throws Exception
	 * @return             HttpResponseFormat of facts data of an assessment
	 */
	public HttpResponseFormat getFactsData(String assessment, Map<String, String> options) throws Exception {
		String url = "/assessment/" + assessment + "/facts?";
		Boolean firstReqParam = true;

		if (options.containsKey("modelIndex")) {

			if (firstReqParam) {
				firstReqParam = false;
				url += "model=" + URLEncoder.encode(options.get("modelIndex"), "UTF-8");
			} else {
				url += "&model=" + URLEncoder.encode(options.get("modelIndex"), "UTF-8");
			}
		}

		if (options.containsKey("startTime")) {

			if (firstReqParam) {
				firstReqParam = false;
				url += "startTime=" + URLEncoder.encode(options.get("startTime"), "UTF-8");
			} else {
				url += "&startTime=" + URLEncoder.encode(options.get("startTime"), "UTF-8");
			}
		}

		if (options.containsKey("endTime")) {

			if (firstReqParam) {
				url += "endTime=" + URLEncoder.encode(options.get("endTime"), "UTF-8");
			} else {
				url += "&endTime=" + URLEncoder.encode(options.get("endTime"), "UTF-8");
			}
		}

		String format;
		String responseFormat = "application/json";
		if (options.containsKey("responseFormat")) {

			format = options.get("responseFormat");
			if (format.equals("text/csv")) {
				responseFormat = format;
			}
		}
		HttpResponseFormat factsData = httpService.getOutput(url, responseFormat);
		return factsData;
	}

	/**
	 * Extract input data from a datastream
	 * @param datastream   Datastream id
	 * @param options      Options for responseFormat
	 * @throws Exception
	 * @return             HttpResponseFormat of input data of a datastream
	 */
	public HttpResponseFormat getInputData(String datastream, Map<String, String> options) throws Exception {
		String url = "/datastream/" + datastream + "/data";
		String format;
		String responseFormat = "application/json";
		if (options.containsKey("responseFormat")) {

			format = options.get("responseFormat");
			if (format.equals("text/csv")) {
				responseFormat = format;
			}
		}
		HttpResponseFormat factsData = httpService.getOutput(url, responseFormat);
		return factsData;
	}

	/**
	 * Get status of the tracker
	 * @param id           Tracker id
	 * @throws Exception
	 * @return             Tracker object
	 */
	public Tracker getStatus(String id) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		String entityMeta_json = httpService.get("/app/track/" + id);
		return mapper.readValue(entityMeta_json, new TypeReference<Tracker>() {});
	}
}
