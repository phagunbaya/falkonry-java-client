[![Falkonry Logo](http://static1.squarespace.com/static/55a7df64e4b09f03368a7a78/t/569c6441ab281050fe32c18a/1453089858079/15-logo-transparent-h.png?format=500w)](http://falkonry.com/)

Falkonry Java Client to access [Falkonry Condition Prediction](falkonry.com) APIs

## Installation

Maven install
```
<dependency>
  <groupId>com.falkonry</groupId>
  <artifactId>client</artifactId>
  <version>0.1.2</version>
</dependency>
```

## Features

    * Create Eventbuffer
    * Retrieve Eventbuffers
    * Create Pipeline
    * Retrieve Pipelines
    * Add data to Eventbuffer (csv/json, stream)
    * Add verification to Pipeline (csv/json, stream)
    * Retrieve output of Pipeline
    * Create/delete Subscription for Eventbuffer
    * Create/delete Publication for Pipeline


## Quick Start

    * To create an Eventbuffer in narrow format

```java
import com.falkonry.client.Falkonry
import com.falkonry.schemas

Falkonry falkonry = new Falkonry("https://service.falkonry.io", "auth-token");

Eventbuffer eb = new schemas.Eventbuffer();
    eb.setName("Eventbuffer_name");
    eb.setTimeIdentifier("time");
    eb.setTimeFormat("iso_8601");
    eb.setValueColumn("value");
    eb.setSignalsDelimiter("_");
    eb.setSignalsLocation("prefix");
    eb.setSignalsTagField("tag");

Eventbuffer eventbuffer = falkonry.createEventbuffer(eb);
eventbuffers.add(eventbuffer);
```
    * To create an Eventbuffer in wide format

```java
import com.falkonry.client.Falkonry
import com.falkonry.schemas

Falkonry falkonry = new Falkonry("https://service.falkonry.io", "auth-token");

Eventbuffer eb = new Eventbuffer();
    eb.setName("Eventbuffer_name");
    eb.setTimeIdentifier("time");
    eb.setTimeFormat("iso_8601");
    eb.setThingIdentifier("thing");

Eventbuffer eventbuffer = falkonry.createEventbuffer(eb);
eventbuffers.add(eventbuffer);
```

    * To retrieve an updated Eventbuffer

```java
import com.falkonry.client.Falkonry
import com.falkonry.schemas

Falkonry falkonry = new Falkonry("https://service.falkonry.io", "auth-token");

String data = "{\"time\" : \"2016-03-01 01:01:01\", \"tag\" : \"signal1_thing1\", \"value\" : 3.4}";
Map<String, String> options = new HashMap<String, String>();

falkonry.addInput(eventbuffer.getId(), data, options);

eventbuffer = falkonry.getUpdatedEventbuffer(eventbuffer.getId());
```

    * To create Pipeline
    
```java
import com.falkonry.client.Falkonry
import com.falkonry.schemas

Falkonry falkonry = new Falkonry("https://service.falkonry.io", "auth-token");

Eventbuffer eb = new schemas.Eventbuffer();
    eb.setName("Eventbuffer_name");
    eb.setTimeIdentifier("time");
    eb.setTimeFormat("iso_8601");
    eb.setValueColumn("value");
    eb.setSignalsDelimiter("_");
    eb.setSignalsLocation("prefix");
    eb.setSignalsTagField("tag");

Eventbuffer eventbuffer = falkonry.createEventbuffer(eb);
eventbuffers.add(eventbuffer);

String data = "{\"time\" : \"2016-03-01 01:01:01\", \"tag\" : \"signal1_thing1\", \"value\" : 3.4}";
Map<String, String> options = new HashMap<String, String>();
falkonry.addInput(eventbuffer.getId(), data, options);

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

    * To add json/csv data in narrow format
    
```java
import org.json.simple.JSONObject
import com.falkonry.client.Falkonry

Falkonry falkonry = new Falkonry("https://service.falkonry.io", "auth-token");
Map<String, String> options = new HashMap<String, String>();

String data = "{\"time\" : \"2016-03-01 01:01:01\", \"tag\" : \"signal1_thing1\", \"value\" : 3.4}";

InputStatus inputStatus = falkonry.addInput(eventbuffer.getId(),data,options);
```

    * To add json/csv data in wide format

```java
import org.json.simple.JSONObject
import com.falkonry.client.Falkonry

Falkonry falkonry = new Falkonry("https://service.falkonry.io", "auth-token");
Map<String, String> options = new HashMap<String, String>();

String data = "{\"time\":1467729675422,\"thing\":\"thing1\",\"signal1\":41.11,\"signal2\":82.34,\"signal3\":74.63,\"signal4\":4.8,\"signal5\":72.01}";

InputStatus inputStatus = falkonry.addInput(eventbuffer.getId(),data,options);
```
    * To add json/csv data from a stream
    
```java
import com.falkonry.client.Falkonry
import org.apache.commons.io.FileUtils;

Falkonry falkonry   = new Falkonry("https://service.falkonry.io", "auth-token");
Map<String, String> options = new HashMap<String, String>();

File file = new File("tmp/data.json");      //use '*.csv' for csv file formats
ByteArrayInputStream istream = new ByteArrayInputStream(FileUtils.readFileToByteArray(file));

InputStatus inputStatus = falkonry.addInputStream(eventbuffer.getId(),byteArrayInputStream,options);
```

    * To add verification data to a pipeline

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
File file = new File("res/verificationData.json");      //use '*.csv' for csv file format
ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(FileUtils.readFileToByteArray(file));
String response = falkonry.addVerificationStream(pipeline.getId(),byteArrayInputStream, options);
```

    * To get output of a Pipeline

```java
import com.falkonry.client.Falkonry

Falkonry falkonry = new Falkonry("https://service.falkonry.io", "auth-token");
OutputStream os   = new FileOutputStream("/tmp/sample.json");
Long startTime    = "1457018017000"; //milliseconds since unix epoch
Long endTime      = "1457028017000"; //milliseconds since unix epoch

BufferedReader br = falkonry.getOutput("pipeline_id", startTime, endTime);
```

       * To create/delete a Subscription for an Eventbuffer

```java
import com.falkonry.client.Falkonry
import com.falkonry.schemas

Subscription sub = new Subscription();
sub.setType("MQTT")
      .setPath("mqtt://test.mosquito.com")
      .setTopic("falkonry-eb-1-test")
      .setUsername("test-user")
      .setPassword("test");

Subscription subscription = falkonry.createSubscription(eventbuffer.getId(), sub);
falkonry.deleteSubscription(eventbuffer.getId(),subscription.getKey());
```

       * To create/delete a Publication for a Pipeline

```java
import com.falkonry.client.Falkonry
import com.falkonry.schemas

Publication publication = new Publication();
publication.setType("MQTT")
    .setPath("mqtt://test.mosquito.com")
    .setTopic("falkonry-eb-1-test")
    .setUsername("test-user")
    .setPassword("test")
    .setContentType("application/json");

Publication pb = falkonry.createPublication(pl.getId(),publication);
falkonry.deletePublication(pl.getId(),pb.getKey());
```
## Docs

    * [Falkonry APIs](https://service.falkonry.io/api)
     
## Tests

  To run the test suite, first install the dependencies, then run tests from the test folder.
  

## License

  Available under [MIT License](LICENSE)
