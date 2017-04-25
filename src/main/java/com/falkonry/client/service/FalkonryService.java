package com.falkonry.client.service;

/*!
 * falkonry-java-client
 * Copyright(c) 2016 Falkonry Inc
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
	 *
	 * @param host
	 * @param token
	 * @throws Exception
	 */
	public FalkonryService(String host, String token) throws Exception {
		this.httpService = new HttpService(host, token);
	}

	/**
	 *
	 * @param datastream
	 * @return
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
		if(datastream.getInputList() != null) {
			ds.setInputList(datastream.getInputList());
		}
		String datastream_json = httpService.post("/datastream", mapper.writeValueAsString(ds));
		return mapper.readValue(datastream_json, Datastream.class);
	}

	/**
	 *
	 * @return
	 * @throws Exception
	 */
	public List<Datastream> getDatastreams() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		String datastream_json = httpService.get("/datastream");
		return mapper.readValue(datastream_json, new TypeReference<List<Datastream>>() {
		});
	}

	/**
	 *
	 * @param id
	 *            is datastream id
	 * @return
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
	 *
	 * @param datastream
	 * @return
	 * @throws Exception
	 */
	public Datastream updateDatastream(Datastream datastream) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		String url = "/datastream/" + datastream.getId();
		String datastream_json = httpService.put(url, mapper.writeValueAsString(datastream));
		return mapper.readValue(datastream_json, Datastream.class);
	}

	/**
	 *
	 * @param id
	 *            is datastream id
	 * @throws Exception
	 */
	public void deleteDatastream(String id) throws Exception {
		httpService.delete("/datastream/" + id);
	}

	/**
	 *
	 * @param assessmentRequest
	 * @return
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
	 *
	 * @return
	 * @throws Exception
	 */
	public List<Assessment> getAssessments() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		String assessment_json = httpService.get("/assessment");
		return mapper.readValue(assessment_json, new TypeReference<List<Assessment>>() {
		});
	}

	/**
	 *
	 * @param id
	 *            is assessment.id
	 * @return
	 * @throws Exception
	 */
	public Assessment getAssessment(String id) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		String url = "/assessment/" + id;
		String assessment_json = httpService.get(url);
		return mapper.readValue(assessment_json, Assessment.class);
	}

	/**
	 *
	 * @param assessment
	 * @return
	 * @throws Exception
	 */
	public Assessment updateAssessment(Assessment assessment) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		String url = "/assessment/" + assessment.getId();
		String assessment_json = httpService.put(url, mapper.writeValueAsString(assessment));
		return mapper.readValue(assessment_json, Assessment.class);
	}

	/**
	 *
	 * @param id
	 *            is assessment.id
	 * @throws Exception
	 */
	public void deleteAssessment(String id) throws Exception {
		httpService.delete("/assessment/" + id);
	}

	/**
	 *
	 * @param id
	 *            is datastream.id
	 * @param data
	 * @param options
	 * @return
	 * @throws Exception
	 */
	public InputStatus addInputData(String id, String data, Map<String, String> options) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		Map<String, String> ops = new HashMap<String, String>();
		String url = "/datastream/" + id;
		if (options.containsKey("streaming")) {
			url += "?streaming=" + URLEncoder.encode(options.get("streaming"), "UTF-8");
		}
		if (options.containsKey("hasMoreData")) {
			url += "?hasMoreData=" + URLEncoder.encode(options.get("hasMoreData"), "UTF-8");
		}
		String status = this.httpService.postData(url, data);
		return mapper.readValue(status, InputStatus.class);
	}

	/**
	 *
	 * @param id
	 *            is assessment.id
	 * @param data
	 * @param options
	 * @return
	 * @throws Exception
	 */
	public InputStatus addFacts(String id, String data, Map<String, String> options) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		String url = "/assessment/" + id + "/facts";
		String status = this.httpService.postData(url, data);
		return mapper.readValue(status, InputStatus.class);
	}

	/**
	 *
	 * @param id
	 *            is datastream.id
	 * @param stream
	 * @param options
	 * @return
	 * @throws Exception
	 */
	public InputStatus addInputFromStream(String id, ByteArrayInputStream stream, Map<String, String> options)
			throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		String url = "/datastream/" + id;

		if (options.containsKey("streaming")) {
			url += "?streaming=" + URLEncoder.encode(options.get("streaming"), "UTF-8");
		}
		if (options.containsKey("hasMoreData")) {
			url += "?hasMoreData=" + URLEncoder.encode(options.get("hasMoreData"), "UTF-8");
		}

		byte[] data_bytes = IOUtils.toByteArray(stream);
		String status = this.httpService.upstream(url, data_bytes);
		return mapper.readValue(status, InputStatus.class);
	}

	/**
	 *
	 * @param id
	 *            is assessment.id
	 * @param stream
	 * @param options
	 * @return
	 * @throws Exception
	 */
	public InputStatus addFactsStream(String id, ByteArrayInputStream stream, Map<String, String> options)
			throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		String url = "/assessment/" + id + "/facts";
		byte[] data_bytes = IOUtils.toByteArray(stream);
		String status = this.httpService.upstream(url, data_bytes);
		return mapper.readValue(status, InputStatus.class);
	}

	/**
	 *
	 * @param id
	 *            is assessment.id
	 * @param start
	 * @param end
	 * @return
	 * @throws Exception
	 */
	public BufferedReader getOutput(String id) throws Exception {
		String url = "/assessment/" + id + "/output";
		return this.httpService.downstream(url);
	}

	/**
	 *
	 * @param id
	 *            is datastream.id
	 * @return
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
	 *
	 * @param id
	 *            is datastream.id
	 * @return
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
	 *
	 * @param assessment
	 * @param options
	 * @return
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
		String responseFromat = "application/json";
		if (options.containsKey("responseFromat")) {

			format = options.get("responseFromat");
			if (format.equals("text/csv")) {
				responseFromat = format;
			}
		}

		HttpResponseFormat outputData = httpService.getOutput(url, responseFromat);
		return outputData;
	}

	// Post EntityMeta

	/**
	 * @param entityMetaRequest
	 * @param id
	 * @throws Exception
	 * @return List<EntityMeta>
	 */
	public List<EntityMeta> postEntityMeta(List<EntityMetaRequest> entityMetaRequest, String id) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		String entityMetaRequest_json = httpService.post("/datastream/" + id + "/entityMeta",
				mapper.writeValueAsString(entityMetaRequest));
		return mapper.readValue(entityMetaRequest_json, new TypeReference<List<EntityMeta>>() {
		});
	}

	/**
	 * @param id
	 * @throws Exception
	 * @return List<EntityMeta>
	 */
	public List<EntityMeta> getEntityMeta(String id) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		String entityMeta_json = httpService.get("/datastream/" + id + "/entityMeta");
		return mapper.readValue(entityMeta_json, new TypeReference<List<EntityMeta>>() {
		});
	}
}
