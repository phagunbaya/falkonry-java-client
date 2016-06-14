package com.falkonry.client.service;

/*!
 * falkonry-java-client
 * Copyright(c) 2016 Falkonry Inc
 * MIT Licensed
 */

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;

class MultipartUtility {
  private final String boundary;
  private static final String LINE_FEED = "\r\n";
  private HttpURLConnection httpConn;
  private String charset;
  private OutputStream outputStream;
  private PrintWriter writer;

  /**
   * This constructor initializes a new HTTP POST request with content type
   * is set to multipart/form-data
   * @param requestURL
   * @param charset
   * @throws IOException
   */
  public MultipartUtility(String requestURL, String charset)
      throws IOException {
    this.charset = charset;

    // creates a unique boundary based on time stamp
    boundary = "===" + System.currentTimeMillis() + "===";

    URL url = new URL(requestURL);
    httpConn = (HttpURLConnection) url.openConnection();
    httpConn.setUseCaches(false);
    httpConn.setDoOutput(true); // indicates POST method
    httpConn.setDoInput(true);
    httpConn.setRequestProperty("Content-Type",
        "multipart/form-data; boundary=" + boundary);
    httpConn.setRequestProperty("User-Agent", "CodeJava Agent");
    httpConn.setRequestProperty("Test", "Bonjour");
    outputStream = httpConn.getOutputStream();
    writer = new PrintWriter(new OutputStreamWriter(outputStream, charset),
        true);
  }

  /**
   * Adds a form field to the request
   * @param name field name
   * @param value field value
   */
  public void addFormField(String name, String value) {
    writer.append("--" + boundary).append(LINE_FEED);
    writer.append("Content-Disposition: form-data; name=\"" + name + "\"")
        .append(LINE_FEED);
    writer.append("Content-Type: text/plain; charset=" + charset).append(
        LINE_FEED);
    writer.append(LINE_FEED);
    writer.append(value).append(LINE_FEED);
    writer.flush();
  }

  /**
   * Adds a upload file section to the request
   * @param fieldName name attribute in <input type="file" name="..." />
   * @param inputStream FileInputStream
   * @throws IOException
   */
  public void addFilePart(String fieldName, InputStream inputStream)
      throws IOException {

    writer.append("--" + boundary).append(LINE_FEED);
    writer.append(
        "Content-Disposition: form-data; name=\"" + fieldName
            + "\"; filename=\"input-"+Math.random()+"\"")
        .append(LINE_FEED);
    writer.append("Content-Transfer-Encoding: binary").append(LINE_FEED);
    writer.append(LINE_FEED);
    writer.flush();

    byte[] buffer = new byte[4096];
    int bytesRead = -1;
    while ((bytesRead = inputStream.read(buffer)) != -1) {
      outputStream.write(buffer, 0, bytesRead);
    }
    outputStream.flush();
    inputStream.close();

    writer.append(LINE_FEED);
    writer.flush();
  }

  /**
   * Adds a header field to the request.
   * @param name - name of the header field
   * @param value - value of the header field
   */
  public void addHeaderField(String name, String value) {
    writer.append(name + ": " + value).append(LINE_FEED);
    writer.flush();
  }

  /**
   * Completes the request and receives response from the server.
   * @return a list of Strings as response in case the server returned
   * status OK, otherwise an exception is thrown.
   * @throws IOException
   */
  public List<String> finish() throws IOException {
    List<String> response = new ArrayList<String>();

    writer.append(LINE_FEED).flush();
    writer.append("--" + boundary + "--").append(LINE_FEED);
    writer.close();

    // checks server's status code first
    int status = httpConn.getResponseCode();
    if (status == HttpURLConnection.HTTP_OK) {
      BufferedReader reader = new BufferedReader(new InputStreamReader(
          httpConn.getInputStream()));
      String line = null;
      while ((line = reader.readLine()) != null) {
        response.add(line);
      }
      reader.close();
      httpConn.disconnect();
    } else {
      throw new IOException("Server returned non-OK status: " + status);
    }

    return response;
  }
}

public class HttpService {

  private String host;
  private String token;
  private String user_agent = "falkonry/java-client";

  public HttpService(String host, String token) throws Exception {
    this.host = (host == null) ? "https://service.falkonry.io" : host;
    try {
      this.token = Base64.getEncoder().encodeToString(token.getBytes("UTF-8"));
    } catch (Exception e) {
      throw new Exception("Invalid token");
    }
  }

  public String get(String path) throws Exception {
    String url = this.host + path;
    URL obj = new URL(url);
    HttpURLConnection con = (HttpURLConnection) obj.openConnection();
    con.setRequestMethod("GET");
    con.setRequestProperty("User-Agent", this.user_agent);
    con.setRequestProperty("Authorization", "Token "+this.token);
    int responseCode = con.getResponseCode();
    BufferedReader in = new BufferedReader(
        new InputStreamReader(con.getInputStream()));

    String inputLine;
    StringBuffer response = new StringBuffer();

    while ((inputLine = in.readLine()) != null) {
      response.append(inputLine);
    }

    in.close();

    if(responseCode == 401)
      throw new Exception("Unauthorized : Invalid token");
    else if(responseCode >= 400)
      throw new Exception(response.toString());
    else
      return response.toString();
  }

  public String post(String path, String data) throws Exception {
    String url = this.host + path;
    URL obj = new URL(url);
    HttpURLConnection con = (HttpURLConnection) obj.openConnection();
    con.setRequestMethod("POST");
    con.setRequestProperty("User-Agent", this.user_agent);
    con.setRequestProperty("Content-Type", "application/json");
    con.setRequestProperty("Authorization", "Token "+this.token);
    con.setDoOutput(true);
    DataOutputStream wr = new DataOutputStream(con.getOutputStream());
    wr.writeBytes(data);
    wr.flush();
    wr.close();

    int responseCode = con.getResponseCode();
    BufferedReader in = new BufferedReader(
        new InputStreamReader(con.getInputStream()));

    String inputLine;
    StringBuffer response = new StringBuffer();

    while ((inputLine = in.readLine()) != null) {
      response.append(inputLine);
    }

    in.close();

    if(responseCode == 401)
      throw new Exception("Unauthorized : Invalid token");
    else if(responseCode >= 400)
      throw new Exception(response.toString());
    else
      return response.toString();
  }

  public String put(String path, String data) throws Exception {
    String url = this.host + path;
    URL obj = new URL(url);
    HttpURLConnection con = (HttpURLConnection) obj.openConnection();
    con.setRequestMethod("PUT");
    con.setRequestProperty("User-Agent", this.user_agent);
    con.setRequestProperty("Content-Type", "application/json");
    con.setRequestProperty("Authorization", "Token "+this.token);
    con.setDoOutput(true);
    DataOutputStream wr = new DataOutputStream(con.getOutputStream());
    wr.writeBytes(data);
    wr.flush();
    wr.close();

    int responseCode = con.getResponseCode();
    BufferedReader in = new BufferedReader(
        new InputStreamReader(con.getInputStream()));

    String inputLine;
    StringBuffer response = new StringBuffer();

    while ((inputLine = in.readLine()) != null) {
      response.append(inputLine);
    }

    in.close();

    if(responseCode == 401)
      throw new Exception("Unauthorized : Invalid token");
    else if(responseCode >= 400)
      throw new Exception(response.toString());
    else
      return response.toString();
  }

  public String delete(String path) throws Exception {
    String url = this.host + path;
    URL obj = new URL(url);
    HttpURLConnection con = (HttpURLConnection) obj.openConnection();
    con.setRequestMethod("DELETE");
    con.setRequestProperty("User-Agent", this.user_agent);
    con.setRequestProperty("Content-Type", "application/json");
    con.setRequestProperty("Authorization", "Token "+this.token);
    con.setDoOutput(true);
    DataOutputStream wr = new DataOutputStream(con.getOutputStream());
    wr.writeBytes("");
    wr.flush();
    wr.close();

    int responseCode = con.getResponseCode();
    BufferedReader in = new BufferedReader(
        new InputStreamReader(con.getInputStream()));

    String inputLine;
    StringBuffer response = new StringBuffer();

    while ((inputLine = in.readLine()) != null) {
      response.append(inputLine);
    }

    in.close();

    if(responseCode == 401)
      throw new Exception("Unauthorized : Invalid token");
    else if(responseCode >= 400)
      throw new Exception(response.toString());
    else
      return response.toString();
  }

  public String sfpost(String path, String data) throws Exception {
    String url = this.host + path;
    URL obj = new URL(url);
    HttpURLConnection con = (HttpURLConnection) obj.openConnection();
    con.setRequestMethod("DELETE");
    con.setRequestProperty("User-Agent", this.user_agent);
    con.setRequestProperty("Content-Type", "application/x-www- form-urlencoded");
    con.setRequestProperty("Authorization", "Token "+this.token);
    con.setDoOutput(true);
    DataOutputStream wr = new DataOutputStream(con.getOutputStream());
    wr.writeBytes(data);
    wr.flush();
    wr.close();

    int responseCode = con.getResponseCode();
    BufferedReader in = new BufferedReader(
        new InputStreamReader(con.getInputStream()));

    String inputLine;
    StringBuffer response = new StringBuffer();

    while ((inputLine = in.readLine()) != null) {
      response.append(inputLine);
    }

    in.close();

    if(responseCode == 401)
      throw new Exception("Unauthorized : Invalid token");
    else if(responseCode >= 400)
      throw new Exception(response.toString());
    else
      return response.toString();
  }

  public String fpost(String path, Map<String, String> params, InputStream stream) throws Exception {
    String url = this.host + path;
    MultipartUtility multipart = new MultipartUtility(url, "UTF-8");

    multipart.addHeaderField("User-Agent", this.user_agent);
    multipart.addHeaderField("Content-Type", "multipart/form-data");
    multipart.addHeaderField("Authorization", "Token "+this.token);

    if(params != null) {
      for (Map.Entry<String, String> entry : params.entrySet()) {
        multipart.addFormField(entry.getKey(), entry.getValue());
      }
    }

    if(stream != null)
      multipart.addFilePart("input-"+Math.random(), stream);

    List<String> response = multipart.finish();
    return response.get(0);
  }

  public String upstream(String path, byte[] data) throws Exception {
    String url = this.host + path;
    URL obj = new URL(url);
    HttpURLConnection con = (HttpURLConnection) obj.openConnection();
    con.setRequestMethod("POST");
    con.setRequestProperty("User-Agent", this.user_agent);
    con.setRequestProperty("Content-Type", "text/plain");
    con.setRequestProperty("Authorization", "Token "+this.token);
    con.setDoOutput(true);
    DataOutputStream wr = new DataOutputStream(con.getOutputStream());
    wr.write(data);
    wr.flush();
    wr.close();

    int responseCode = con.getResponseCode();
    BufferedReader in = new BufferedReader(
        new InputStreamReader(con.getInputStream()));

    String inputLine;
    StringBuffer response = new StringBuffer();

    while ((inputLine = in.readLine()) != null) {
      response.append(inputLine);
    }

    in.close();

    if(responseCode == 401)
      throw new Exception("Unauthorized : Invalid token");
    else if(responseCode >= 400)
      throw new Exception(response.toString());
    else
      return response.toString();
  }

  public BufferedReader downstream(String path) throws Exception {
    String url = this.host + path;
    URL obj = new URL(url);
    HttpURLConnection con = (HttpURLConnection) obj.openConnection();
    con.setRequestMethod("GET");
    con.setRequestProperty("User-Agent", this.user_agent);
    con.setRequestProperty("Authorization", "Token "+this.token);
    int responseCode = con.getResponseCode();
    BufferedReader in = new BufferedReader(
        new InputStreamReader(con.getInputStream()));

    if(responseCode == 401)
      throw new Exception("Unauthorized : Invalid token");
    else if(responseCode >= 400) {
      String inputLine;
      StringBuffer response = new StringBuffer();

      while ((inputLine = in.readLine()) != null) {
        response.append(inputLine);
      }
      in.close();
      throw new Exception(response.toString());
    }
    else
      return in;
  }
}
