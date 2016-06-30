[![Falkonry Logo](http://static1.squarespace.com/static/55a7df64e4b09f03368a7a78/t/569c6441ab281050fe32c18a/1453089858079/15-logo-transparent-h.png?format=500w)](http://falkonry.com/)

[![Build status](https://img.shields.io/travis/Falkonry/falkonry-java-client.svg?style=flat-square)](https://travis-ci.org/Falkonry/falkonry-java-client)

Falkonry Java Client to access [Falkonry Condition Prediction](falkonry.com) APIs

## Installation

Maven install
```
<dependency>
  <groupId>com.falkonry</groupId>
  <artifactId>client</artifactId>
  <version>0.1.1</version>
</dependency>
```

## Features

    * Create Pipeline
    * Retrieve Pipelines
    * Add data to Pipeline (jsonarray, stream)
    * Retrieve output of Pipeline
    
## Quick Start

    * To create Pipeline
    
```java
import com.falkonry.client.Falkonry
import com.falkonry.schemas

Falkonry falkonry = new Falkonry("https://service.falkonry.io", "auth-token");

List<Signal> signals = new ArrayList<Signal>();
    signals.add(new schemas.Signal().setName("current").setValueType(new ValueType().setType("Numeric"))
        .setEventType(new EventType().setType("Samples")));
    signals.add(new schemas.Signal().setName("vibration").setValueType(new ValueType().setType("Numeric"))
        .setEventType(new EventType().setType("Samples")));
    signals.add(new schemas.Signal().setName("state").setValueType(new ValueType().setType("Categorical"))
        .setEventType(new EventType().setType("Samples")));

List<String> inputList = new ArrayList<String>();
    inputList.add("current");
    inputList.add("vibration");
    inputList.add("state");

List<Assessment> assessmentList = new ArrayList<Assessment>();
Assessment assessment = new schemas.Assessment()
                .setName("Health")
                .addSignals(assessment_signals);
assessmentList.add(assessment);

Map<String, String> options = new HashMap<String, String>();
Eventbuffer eb = new schemas.Eventbuffer();
    eb.setName("Test-EB-" + Math.random());
    options.put("timeIdentifier", "time");
    options.put("timeFormat", "iso_8601");
Eventbuffer eventbuffer = falkonry.createEventbuffer(eb, options);

Interval interval = new schemas.Interval();
    interval.setDuration("PT1S");
                        
Pipeline pipeline = new schemas.Pipeline()
                .setName("Motor Health")
                .setEventbuffer(eventbuffer.getId())
                .setThingName("Motor")
                .setInputSignals(signals)
                .setAssessmentList(assessments)
                .setInterval(interval);
        
Pipeline createdPipeline = falkonry.createPipeline(pipeline);
```

    * To get all Pipelines
    
```java
import com.falkonry.client.Falkonry
import com.falkonry.schemas

Falkonry falkonry = new Falkonry("https://service.falkonry.io", "auth-token");

List<Pipeline> pipelines = falkonry.getPipelines();
```

    * To add data
    
```java
import org.json.simple.JSONObject
import com.falkonry.client.Falkonry

Map<String, String> options = new HashMap<String, String>();
        options.put("timeIdentifier", "time");
        options.put("timeFormat", "iso_8601");
String data = "{\"time\" :\"2016-03-01 01:01:01\", \"current\" : 12.4, \"vibration\" : 3.4, \"state\" : \"On\"}";
        options.put("fileFormat","json");

Falkonry falkonry = new Falkonry("https://service.falkonry.io", "auth-token");

InputStatus inputStatus = falkonry.addInput(eventbuffer.getId(),data,options);
```

    * To add data from a stream
    
```java
import com.falkonry.client.Falkonry
import org.apache.commons.io.FileUtils;

Falkonry falkonry   = new Falkonry("https://service.falkonry.io", "auth-token");
Map<String, String> options = new HashMap<String, String>();
    options.put("timeIdentifier", "time");
    options.put("timeFormat", "iso_8601");
File file = new File("tmp/data.json");
ByteArrayInputStream istream = new ByteArrayInputStream(FileUtils.readFileToByteArray(file));

InputStatus inputStatus = falkonry.addInputStream(eventbuffer.getId(),byteArrayInputStream,options);
```

    * To add verification data

```java
import com.falkonry.client.Falkonry

Falkonry falkonry = new Falkonry("https://service.falkonry.io", "auth-token");

String data = "time,end,car,Health\n2011-03-31T00:00:00Z,2011-04-01T00:00:00Z,IL9753,Normal\n2011-03-31T00:00:00Z,2011-04-01T00:00:00Z,HI3821,Normal";
String response = falkonry.addVerification(pipeline.getId(),data, options);
```

    * To add verification data from a stream

```java
import com.falkonry.client.Falkonry
import org.apache.commons.io.FileUtils;

Falkonry falkonry   = new Falkonry("https://service.falkonry.io", "auth-token");
File file = new File("res/verificationData.json");
ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(FileUtils.readFileToByteArray(file));
String response = falkonry.addVerificationStream(pipeline.getId(),byteArrayInputStream, options);
```

    * To get output of a Pipeline

```java
import com.falkonry.client.Falkonry

Falkonry falkonry = new Falkonry("https://service.falkonry.io", "auth-token");
OutputStream os   = new FileOutputStream("/tmp/sample.json");
Long startTime    = "1457018017000"; //miliseconds since unix epoch
Long endTime      = "1457028017000"; //miliseconds since unix epoch

BufferedReader br = falkonry.getOutput("pipeline_id", startTime, endTime);
```

## Docs

    * [Falkonry APIs](https://service.falkonry.io/api)
     
## Tests

  To run the test suite, first install the dependencies, then run tests from the test folder.
  

## License

  Available under [MIT License](LICENSE)
