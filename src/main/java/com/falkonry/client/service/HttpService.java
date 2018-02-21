package com.falkonry.client.service;

/*!
 * falkonry-java-client
 * Copyright(c) 2017 Falkonry Inc
 * MIT Licensed
 */

import com.falkonry.helper.models.HttpResponseFormat;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.conn.ssl.SSLSocketFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509ExtendedTrustManager;

/**
 *
 */
public class HttpService {

	private String host;
	private String token;
	private String user_agent = "falkonry/java-client";

	/**
	 *
	 * @param host
	 * @param token
	 * @throws Exception
	 */
	public HttpService(String host, String token) throws Exception {
		TrustManager[] trustAllCerts = new TrustManager[]{
      new X509ExtendedTrustManager() {
        @Override
        public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
        }
        @Override
        public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
        }
        @Override
        public X509Certificate[] getAcceptedIssuers() {
          return null;
        }
        @Override
        public void checkClientTrusted(X509Certificate[] x509Certificates, String s, Socket socket) throws CertificateException {
        }
        @Override
        public void checkServerTrusted(X509Certificate[] x509Certificates, String s, Socket socket) throws CertificateException {
        }
        @Override
        public void checkClientTrusted(X509Certificate[] x509Certificates, String s, SSLEngine sslEngine) throws CertificateException {
        }
        @Override
        public void checkServerTrusted(X509Certificate[] x509Certificates, String s, SSLEngine sslEngine) throws CertificateException {
        }
      }
		};

		SSLContext sslContext = SSLContext.getInstance("SSL");
		sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
		javax.net.ssl.HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());

		this.host = (host == null) ? "https://sandbox.falkonry.ai" : host;
		try {
			this.token = token;
		} catch (Exception e) {
			throw new Exception("Invalid token");
		}
	}

	/**
	 *
	 * @param path
	 * @return
	 * @throws Exception
	 */
	public String get(String path) throws Exception {
		String url = this.host + path;
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", this.user_agent);
		con.setRequestProperty("Authorization", "Bearer " + this.token);
		con.setRequestProperty("x-falkonry-source", "falkonry-java-client");
		int responseCode = con.getResponseCode();
		if (responseCode >= 400) {
			InputStream errorStream = con.getErrorStream();
			BufferedReader in = new BufferedReader(new InputStreamReader(errorStream));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			throw new FalkonryException(response.toString());
		}

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}

		in.close();

		return response.toString();
	}

	/**
	 *
	 * @param path
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public String post(String path, String data) throws Exception {
		String url = this.host + path;

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", this.user_agent);
		con.setRequestProperty("Content-Type", "application/json");
		con.setRequestProperty("Authorization", "Bearer " + this.token);
		con.setRequestProperty("x-falkonry-source", "falkonry-java-client");
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(data);
		wr.flush();
		wr.close();
		int responseCode = con.getResponseCode();
		if (responseCode >= 400) {
			InputStream errorStream = con.getErrorStream();
			BufferedReader in = new BufferedReader(new InputStreamReader(errorStream));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			throw new FalkonryException(response.toString());
		}
		InputStream is = con.getInputStream();
		BufferedReader in = new BufferedReader(new InputStreamReader(is));

		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}

		in.close();

		return response.toString();
	}

	/**
	 *
	 * @param path
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public String postData(String path, String data) throws Exception {
		String url = this.host + path;
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", this.user_agent);
		con.setRequestProperty("Content-Type", "text/plain");
		con.setRequestProperty("Authorization", "Bearer " + this.token);
		con.setRequestProperty("x-falkonry-source", "falkonry-java-client");
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(data);
		wr.flush();
		wr.close();
		int responseCode = con.getResponseCode();
		if (responseCode >= 400) {
			InputStream errorStream = con.getErrorStream();
			BufferedReader in = new BufferedReader(new InputStreamReader(errorStream));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			throw new FalkonryException(response.toString());
		}
		InputStream is = con.getInputStream();
		BufferedReader in = new BufferedReader(new InputStreamReader(is));

		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}

		in.close();

		return response.toString();
	}

	/**
	 *
	 * @param path
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public String put(String path, String data) throws Exception {
		String url = this.host + path;
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("PUT");
		con.setRequestProperty("User-Agent", this.user_agent);
		con.setRequestProperty("Content-Type", "application/json");
		con.setRequestProperty("Authorization", "Bearer " + this.token);
		con.setRequestProperty("x-falkonry-source", "falkonry-java-client");
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(data);
		wr.flush();
		wr.close();

		int responseCode = con.getResponseCode();
		if (responseCode >= 400) {
			InputStream errorStream = con.getErrorStream();
			BufferedReader in = new BufferedReader(new InputStreamReader(errorStream));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			throw new FalkonryException(response.toString());
		}
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}

		in.close();

		return response.toString();
	
	}

	/**
	 *
	 * @param path
	 * @return
	 * @throws Exception
	 */
	public String delete(String path) throws Exception {
		String url = this.host + path;
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("DELETE");
		con.setRequestProperty("User-Agent", this.user_agent);
		con.setRequestProperty("Authorization", "Bearer " + this.token);
		con.setRequestProperty("x-falkonry-source", "falkonry-java-client");
		int responseCode = con.getResponseCode();
		if (responseCode >= 400) {
			InputStream errorStream = con.getErrorStream();
			BufferedReader in = new BufferedReader(new InputStreamReader(errorStream));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			throw new FalkonryException(response.toString());
		}
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}

		in.close();

		return response.toString();
	}

	/**
	 *
	 * @param path
	 * @param params
	 * @param stream
	 * @return
	 * @throws Exception
	 */
	public String sfpost(String path, Map<String, String> params, InputStream stream) throws Exception {
		String url = this.host + path;
		String tempFileName;
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		httpPost.addHeader("User-Agent", this.user_agent);
		httpPost.addHeader("Authorization", "Bearer " + this.token);
		httpPost.addHeader("x-falkonry-source", "falkonry-java-client");

		MultipartEntityBuilder builder = MultipartEntityBuilder.create();
		builder.addTextBody("name", params.get("name"));
		builder.addTextBody("timeIdentifier", params.get("timeIdentifier"));
		builder.addTextBody("timeFormat", params.get("timeFormat"));

		if (stream != null) {
			tempFileName = "input-" + Math.random() + "." + params.get("fileFormat");
			String content_type = "text/" + params.get("fileFormat");
			builder.addBinaryBody("data", stream, ContentType.create(content_type), tempFileName);
		}

		HttpEntity multipart = builder.build();
		httpPost.setEntity(multipart);

		CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpPost);
		int responseCode = closeableHttpResponse.getStatusLine().getStatusCode();
		if (responseCode >= 400) {
			String responseMessage = closeableHttpResponse.getStatusLine().getReasonPhrase();
			throw new FalkonryException(responseMessage);
		}
		HttpEntity responseEntity = closeableHttpResponse.getEntity();

		InputStream is = responseEntity.getContent();
		BufferedReader in = new BufferedReader(new InputStreamReader(is));

		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}

		in.close();

		return response.toString();
	}

	/**
	 *
	 * @param path
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public String upstream(String path, byte[] data) throws Exception {
		String url = this.host + path;
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", this.user_agent);
		con.setRequestProperty("Content-Type", "text/plain");
		con.setRequestProperty("Authorization", "Bearer " + this.token);
		con.setRequestProperty("x-falkonry-source", "falkonry-java-client");
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.write(data);
		wr.flush();
		wr.close();

		int responseCode = con.getResponseCode();
		if (responseCode >= 400) {
			InputStream errorStream = con.getErrorStream();
			BufferedReader in = new BufferedReader(new InputStreamReader(errorStream));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			throw new FalkonryException(response.toString());
		}
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}

		in.close();

		return response.toString();
	}

	/**
	 *
	 * @param path
	 * @return
	 * @throws Exception
	 */
	public BufferedReader downstream(String path) throws Exception {
		String url = this.host + path;
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", this.user_agent);
		con.setRequestProperty("Authorization", "Bearer " + this.token);
		con.setRequestProperty("x-falkonry-source", "falkonry-java-client");
		int responseCode = con.getResponseCode();
		if (responseCode >= 400) {
			InputStream errorStream = con.getErrorStream();
			BufferedReader in = new BufferedReader(new InputStreamReader(errorStream));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			throw new FalkonryException(response.toString());
		}
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

		return in;
	}

	/**
	 *
	 * @param path
	 * @param responseFormat
	 * @return
	 */
	public HttpResponseFormat getOutput(String path, String responseFormat) throws Exception {

		HttpResponseFormat httpResponseFormat = new HttpResponseFormat();
		String url = this.host + path;
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", this.user_agent);
		con.setRequestProperty("Authorization", "Bearer " + this.token);
		con.setRequestProperty("Content-Type", "application/json");
		con.setRequestProperty("Accept", responseFormat);
		con.setRequestProperty("x-falkonry-source", "falkonry-java-client");
		int responseCode = con.getResponseCode();
		if (responseCode >= 400) {
			InputStream errorStream = con.getErrorStream();
			BufferedReader in = new BufferedReader(new InputStreamReader(errorStream));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			throw new FalkonryException(response.toString());
		}
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}

		in.close();

		httpResponseFormat.setResponse(response.toString());
		httpResponseFormat.setStatusCode(responseCode);
		return httpResponseFormat;
	}
}
