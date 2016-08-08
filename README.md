[![Falkonry Logo](http://static1.squarespace.com/static/55a7df64e4b09f03368a7a78/t/569c6441ab281050fe32c18a/1453089858079/15-logo-transparent-h.png?format=500w)](http://falkonry.com/)

Falkonry Java Client to access [Falkonry Condition Prediction](falkonry.com) APIs

[Releases](https://github.com/Falkonry/falkonry-java-client/releases)

## Installation

Maven install
```
<dependency>
  <groupId>com.falkonry</groupId>
  <artifactId>client</artifactId>
  <version>0.1.3</version>
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
    * Create/delete subscription for Eventbuffer
    * Create/delete publication for Pipeline

## Format of Data 

    * Narrow Format [Historian] JSON 
```
    {"time" :"2016-03-01 01:01:01", "tag" : "signal1_thing1", "value" : 3.4}
    {"time" :"2016-04-01 05:04:01", "tag" : "signal1_thing2", "value" : 9.3}
```    
 
    * Narrow Format [Historian] CSV 
```
    time, tag, value
    2016-03-01 01:01:01, signal1_thing1, 3.4
    2016-03-01 01:01:01, signal1_thing1, 3.4
```
   
    * Wide Format JSON
```
    {"time":1467729675422,"thing":"Thing1","signal1":41.11,"signal2":82.34,"signal3":74.63,"signal4":4.8,"signal5":72.01}
    {"time":1467729668919,"thing":"Thing2","signal1":78.11,"signal2":2.33,"signal3":4.6,"signal4":9.8,"signal5":99.09}
```

    * Wide Format CSV
```
    time,thing,signal1,signal2,signal3,signal4,signal5
    1467729675422,thing1,41.11,62.34,77.63,4.8,72.01
    1467729675445,thing1,43.91,82.64,73.63,3.8,2.01
```    
 
## Quick Start

    * To create an Eventbuffer for narrow format data
    
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
```
    * To create an Eventbuffer for wide format data

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
```
    * To get an Eventbuffer

```java
import com.falkonry.client.Falkonry
import com.falkonry.schemas

Falkonry falkonry = new Falkonry("https://service.falkonry.io", "auth-token");

eventbuffer = falkonry.getUpdatedEventbuffer(eventbuffer.getId());
```
    * To create a Pipeline with narrow format csv data
    
```java
import com.falkonry.client.Falkonry
import com.falkonry.schemas

Falkonry falkonry = new Falkonry("https://service.falkonry.io", "auth-token");

Eventbuffer eb = new schemas.Eventbuffer();
    eb.setName("Eventbuffer_name");
    eb.setTimeIdentifier("time");
    eb.setTimeFormat("iso_8601");

Eventbuffer eventbuffer = falkonry.createEventbuffer(eb);

String data = "time, tag, value\n" + "2016-03-01 01:01:01, signal1_thing1, 3.4";
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
    * To create a Pipeline with wide format json data
    
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
    * To add json data in narrow formatted Eventbuffer
    
```java
import com.falkonry.client.Falkonry

Falkonry falkonry = new Falkonry("https://service.falkonry.io", "auth-token");
Map<String, String> options = new HashMap<String, String>();

String data = "{\"time\" : \"2016-03-01 01:01:01\", \"tag\" : \"signal1_thing1\", \"value\" : 3.4}";

InputStatus inputStatus = falkonry.addInput(eventbuffer.getId(),data,options);
```
    * To add json data in narrow formatted Eventbuffer for multiple things
    
```java
import com.falkonry.client.Falkonry

Falkonry falkonry = new Falkonry("https://service.falkonry.io", "auth-token");
Map<String, String> options = new HashMap<String, String>();

String data = "{\"time\" : \"2016-03-01 01:01:01\", \"tag\" : \"signal1_thing1\", \"value\" : 3.4}\n"
                + "{\"time\" : \"2016-04-01 02:04:01\", \"tag\" : \"signal1_thing2\", \"value\" : 9.8}\n"
                + "{\"time\" : \"2016-09-07 07:03:01\", \"tag\" : \"signal1_thing3\", \"value\" : 7.4}";

InputStatus inputStatus = falkonry.addInput(eventbuffer.getId(),data,options);
```
    * To add csv data in narrow formatted Eventbuffer
    
```java
import com.falkonry.client.Falkonry

Falkonry falkonry = new Falkonry("https://service.falkonry.io", "auth-token");
Map<String, String> options = new HashMap<String, String>();

String data = "time, tag, value\n"+
            + "2016-03-01 01:01:01, signal1_thing1, 3.4"
            
InputStatus inputStatus = falkonry.addInput(eventbuffer.getId(),data,options);
```
    * To add csv data in narrow formatted Eventbuffer for multiple things
      
```java
import com.falkonry.client.Falkonry

Falkonry falkonry = new Falkonry("https://service.falkonry.io", "auth-token");
Map<String, String> options = new HashMap<String, String>();

String data = "time, tag, value\n"+
            + "2016-03-01 01:01:01, signal1_thing1, 3.4\n"
            + "2016-04-01 02:03:01, signal1_thing2, 8.4\n"
            + "2016-07-01 01:07:09, signal1_thing3, 3.9";

InputStatus inputStatus = falkonry.addInput(eventbuffer.getId(),data,options);
```
    * To add json data in wide format Eventbuffer with multiple things

```java
import org.json.simple.JSONObject
import com.falkonry.client.Falkonry

Falkonry falkonry = new Falkonry("https://service.falkonry.io", "auth-token");
Map<String, String> options = new HashMap<String, String>();

String data = "{\"time\":1467729675422,\"thing\":\"thing1\",\"signal1\":41.11,\"signal2\":82.34,\"signal3\":74.63,\"signal4\":4.8,\"signal5\":72.01}\n"
                + "{\"time\":1467729675188,\"thing\":\"thing2\",\"signal1\":77.81,\"signal2\":78.34,\"signal3\":77.63,\"signal4\":6.8,\"signal5\":72.89}\n"
                + "{\"time\":1467729675422,\"thing\":\"thing3\",\"signal1\":11.11,\"signal2\":2.39,\"signal3\":70.63,\"signal4\":40,\"signal5\":12.01}";

InputStatus inputStatus = falkonry.addInput(eventbuffer.getId(),data,options);
```
    * To add json data in wide format Eventbuffer for multiple things

```java
import org.json.simple.JSONObject
import com.falkonry.client.Falkonry

Falkonry falkonry = new Falkonry("https://service.falkonry.io", "auth-token");
Map<String, String> options = new HashMap<String, String>();

String data = "{\"time\":1467729675422,\"thing\":\"thing1\",\"signal1\":41.11,\"signal2\":82.34,\"signal3\":74.63,\"signal4\":4.8,\"signal5\":72.01}";

InputStatus inputStatus = falkonry.addInput(eventbuffer.getId(),data,options);
```
    * To add csv data in a wide format Eventbuffer
    
```java
import com.falkonry.client.Falkonry

Falkonry falkonry = new Falkonry("https://service.falkonry.io", "auth-token");
Map<String, String> options = new HashMap<String, String>();

String data = "time,thing,signal1,signal2,signal3,signal4,signal5\n"
                + "1467729675422,thing1,41.11,62.34,77.63,4.8,72.01";

InputStatus inputStatus = falkonry.addInput(eventbuffer.getId(),data,options);
```
    * To add csv data in a wide format Eventbuffer for multiple things
    
```java
import com.falkonry.client.Falkonry

Falkonry falkonry = new Falkonry("https://service.falkonry.io", "auth-token");
Map<String, String> options = new HashMap<String, String>();

String data = "time,thing,signal1,signal2,signal3,signal4,signal5\n"
                + "1467729655422,thing1,71.11,62.34,77.66,4.8,72.1\n"
                + "1467729666782,thing2,40.1,29.34,7.43,3.8,2.01\n"
                + "1467729674890,thing3,21.55,89.34,90.63,9.8,8.01";

InputStatus inputStatus = falkonry.addInput(eventbuffer.getId(),data,options);
```
    * To add json data from a stream to an Eventbuffer
    
```java
import com.falkonry.client.Falkonry
import org.apache.commons.io.FileUtils;

Falkonry falkonry   = new Falkonry("https://service.falkonry.io", "auth-token");
Map<String, String> options = new HashMap<String, String>();

File file = new File("tmp/data.json");      
ByteArrayInputStream istream = new ByteArrayInputStream(FileUtils.readFileToByteArray(file));

InputStatus inputStatus = falkonry.addInputStream(eventbuffer.getId(),byteArrayInputStream,options);
```
    * To add csv data from a stream to an Eventbuffer
    
```java
import com.falkonry.client.Falkonry
import org.apache.commons.io.FileUtils;

Falkonry falkonry   = new Falkonry("https://service.falkonry.io", "auth-token");
Map<String, String> options = new HashMap<String, String>();

File file = new File("tmp/data.csv");     
ByteArrayInputStream istream = new ByteArrayInputStream(FileUtils.readFileToByteArray(file));

InputStatus inputStatus = falkonry.addInputStream(eventbuffer.getId(),byteArrayInputStream,options);
```
    * To add verification json data to a Pipeline

```java
import com.falkonry.client.Falkonry

Falkonry falkonry = new Falkonry("https://service.falkonry.io", "auth-token");

String data = "{\"time\" : \"2011-03-26T12:00:00Z\", \"thing\" : \"thing1\", \"end\" : \"2012-06-01T00:00:00Z\", \"Health\" : \"Normal\"}";
String response = falkonry.addVerification(pipeline.getId(),data, options);
```
    * To add verification csv data to a Pipeline

```java
import com.falkonry.client.Falkonry

Falkonry falkonry = new Falkonry("https://service.falkonry.io", "auth-token");

String data = "time,end,car,Health\n2011-03-31T00:00:00Z,2011-04-01T00:00:00Z,IL9753,Normal\n2011-03-31T00:00:00Z,2011-04-01T00:00:00Z,HI3821,Normal";
String response = falkonry.addVerification(pipeline.getId(),data, options);
```
    * To add json verification data from a stream to a Pipeline
    
```java
import com.falkonry.client.Falkonry
import org.apache.commons.io.FileUtils;

Falkonry falkonry   = new Falkonry("https://service.falkonry.io", "auth-token");
File file = new File("res/verificationData.json");      
ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(FileUtils.readFileToByteArray(file));
String response = falkonry.addVerificationStream(pipeline.getId(),byteArrayInputStream, options);
```
    * To add csv verification data from a stream to a Pipeline
    
```java
import com.falkonry.client.Falkonry
import org.apache.commons.io.FileUtils;

Falkonry falkonry   = new Falkonry("https://service.falkonry.io", "auth-token");
File file = new File("res/verificationData.csv");      
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
    * To create/delete a subscription for an Eventbuffer

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

    * To create/delete a publication for a Pipeline

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

Publication pb = falkonry.createPublication(pipeline.getId(),publication);
falkonry.deletePublication(pipeline.getId(),publication.getKey());
```
## Docs

    [Falkonry APIs](https://service.falkonry.io/api)
     
## Tests

  To run the test suite, first install the dependencies, then run tests from the test folder.
  

## License

  Available under [MIT License](LICENSE)
