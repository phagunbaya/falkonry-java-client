[![Falkonry Logo](http://static1.squarespace.com/static/55a7df64e4b09f03368a7a78/t/569c6441ab281050fe32c18a/1453089858079/15-logo-transparent-h.png?format=500w)](http://falkonry.com/)

Falkonry Java Client to access [Falkonry Condition Prediction](falkonry.com) APIs

[Releases](https://github.com/Falkonry/falkonry-java-client/releases)

## Installation

Maven install
```
<dependency>
  <groupId>com.falkonry</groupId>
  <artifactId>client</artifactId>
  <version>0.1.8</version>
</dependency>
```

## Features

    * Create Datastream
    * Retrieve Datastreams
    * Create Assessment
    * Retrieve Assessments
    * Add data to Datastream (csv/json, stream)
    * Add facts to Assessment (csv/json, stream)
    * Add data in wide format
    * Add historian data to datastream
    * Add streaming data to datastream
    * Get HistorianOutput from assessment
    * Get Streaming Output from assessment
    * Delete datastream
    * Delete assessment

## Quick Start

    * Get auth token from the Falkonry Service UI.
    * Read below examples for integration with various data formats.

## Examples

### Create datastream
Usage:
```java
    import com.falkonry.client.Falkonry;
    import com.falkonry.helper.models.Datasource;
    import com.falkonry.helper.models.Datastream;
    import com.falkonry.helper.models.Field;
    import com.falkonry.helper.models.TimeObject;
    import com.falkonry.helper.models.Signal;

    //instantiate Falkonry
    Falkonry falkonry = new Falkonry("https://sandbox.falkonry.ai", "auth-token");
    Datastream ds = new Datastream();
    ds.setName("Test-DS-" + Math.random());

    TimeObject time = new TimeObject();
    time.setIdentifier("time");
    time.setFormat("iso_8601");
    time.setZone("GMT");

    Signal signal = new Signal();
    signal.setTagIdentifier("tag");
    signal.setValueIdentifier("value");
    signal.setDelimiter("_");
    signal.setIsSignalPrefix(false);

    Datasource dataSource = new Datasource();
    dataSource.setType("STANDALONE");

    Field field = new Field();
    field.setSiganl(signal);
    field.setTime(time);
    //field.setEntityIdentifier("unit");

    ds.setDatasource(dataSource);
    ds.setField(field);

    Datastream datastream = falkonry.createDatastream(ds);

```


#### Setup Datastream for narrow/historian style data from a single entity

Data:
```
    {"time" :"2016-03-01 01:01:01", "tag" : "signal1", "value" : 3.4}
    {"time" :"2016-03-01 01:01:02", "tag" : "signal2", "value" : 9.3}

    or

    time, tag, value
    2016-03-01 01:01:01, signal1, 3.4
    2016-03-01 01:01:02, signal2, 9.3

```

Usage:
```java
    import com.falkonry.client.Falkonry;
    import com.falkonry.helper.models.Datasource;
    import com.falkonry.helper.models.Datastream;
    import com.falkonry.helper.models.Field;
    import com.falkonry.helper.models.TimeObject;
    import com.falkonry.helper.models.Signal;

    //instantiate Falkonry
    Falkonry falkonry = new Falkonry("https://sandbox.falkonry.ai", "auth-token");
    Datastream ds = new Datastream();
    ds.setName("Test-DS-" + Math.random());

    TimeObject time = new TimeObject();
    time.setIdentifier("time");
    time.setFormat("iso_8601");
    time.setZone("GMT");

    Signal signal = new Signal();
    signal.setTagIdentifier("tag");
    signal.setValueIdentifier("value");
    signal.setDelimiter("_");
    signal.setIsSignalPrefix(false);

    Datasource dataSource = new Datasource();
    dataSource.setType("STANDALONE");

    Field field = new Field();
    field.setSiganl(signal);
    field.setTime(time);
    //field.setEntityIdentifier("unit");

    ds.setDatasource(dataSource);
    ds.setField(field);

    Datastream datastream = falkonry.createDatastream(ds);

    //Add data to datastream
    String data = "{\"time\" : \"2016-03-01 01:01:01\", \"tag\" : \"signal1\", \"value\" : 3.4}" + "\n"
        + "{\"time\" : \"2016-03-01 01:01:02\", \"tag\" : \"signal2\", \"value\" : 9.3}";

    Map<String, String> options = new HashMap<String, String>();
    options.put("timeIdentifier", "time");
    options.put("timeFormat", "iso_8601");
    options.put("fileFormat", "csv");
    falkonry.addInput(datastream.getId(), data, options);

```

#### Setup Datastream for narrow/historian style data from multiple entities

Data:
```
    {"time" :"2016-03-01 01:01:01", "tag" : "signal1_entity1", "value" : 3.4}
    {"time" :"2016-03-01 01:01:01", "tag" : "signal2_entity1", "value" : 1.4}
    {"time" :"2016-03-01 01:01:02", "tag" : "signal1_entity2", "value" : 9.3}
    {"time" :"2016-03-01 01:01:02", "tag" : "signal2_entity2", "value" : 4.3}

    or

    time, tag, value
    2016-03-01 01:01:01, signal1_entity1, 3.4
    2016-03-01 01:01:01, signal2_entity1, 1.4
    2016-03-01 01:01:02, signal1_entity2, 9.3
    2016-03-01 01:01:02, signal2_entity2, 4.3
```

Usage:
```java
    import com.falkonry.client.Falkonry;
    import com.falkonry.helper.models.Datasource;
    import com.falkonry.helper.models.Datastream;
    import com.falkonry.helper.models.Field;
    import com.falkonry.helper.models.TimeObject;
    import com.falkonry.helper.models.Signal;

    //instantiate Falkonry
    Falkonry falkonry = new Falkonry("https://sandbox.falkonry.ai", "auth-token");


    Datastream ds = new Datastream();
    ds.setName("Test-DS-" + Math.random());

    TimeObject time = new TimeObject();
    time.setIdentifier("time");
    time.setFormat("iso_8601");
    time.setZone("GMT");

    Signal signal = new Signal();
    signal.setTagIdentifier("tag");
    signal.setValueIdentifier("value");
    signal.setDelimiter("_");
    signal.setIsSignalPrefix(false);

    Datasource dataSource = new Datasource();
    dataSource.setType("STANDALONE");

    Field field = new Field();
    field.setSiganl(signal);
    field.setTime(time);
    //field.setEntityIdentifier("unit");

    ds.setDatasource(dataSource);
    ds.setField(field);

    Datastream datastream = falkonry.createDatastream(ds);
    

    //Add data to datastream
    String data = "{\"time\" : \"2016-03-01 01:01:01\", \"tag\" : \"signal1_entity1\", \"value\" : 3.4}" + "\n"
        + "{\"time\" : \"2016-03-01 01:01:01\", \"tag\" : \"signal2_entity1\", \"value\" : 1.4}" + "\n"
        + "{\"time\" : \"2016-03-01 01:01:02\", \"tag\" : \"signal1_entity1\", \"value\" : 9.3}" + "\n"
        + "{\"time\" : \"2016-03-01 01:01:02\", \"tag\" : \"signal2_entity2\", \"value\" : 4.3}";
    Map<String, String> options = new HashMap<String, String>();
    options.put("timeIdentifier", "time");
    options.put("timeFormat", "iso_8601");
    options.put("fileFormat", "csv");
    falkonry.addInput(datastream.getId(), data, options);

```

#### Setup Datastream for wide style data from a single entity

Data:
```
    {"time":1467729675422, "signal1":41.11, "signal2":82.34, "signal3":74.63, "signal4":4.8}
    {"time":1467729668919, "signal1":78.11, "signal2":2.33, "signal3":4.6, "signal4":9.8}

    or

    time, signal1, signal2, signal3, signal4
    1467729675422, 41.11, 62.34, 77.63, 4.8
    1467729675445, 43.91, 82.64, 73.63, 3.8
```

Usage:
```java
    import com.falkonry.client.Falkonry;
    import com.falkonry.helper.models.Datasource;
    import com.falkonry.helper.models.Datastream;
    import com.falkonry.helper.models.Field;
    import com.falkonry.helper.models.TimeObject;
    import com.falkonry.helper.models.Signal;

    //instantiate Falkonry
    Falkonry falkonry = new Falkonry("https://sandbox.falkonry.ai", "auth-token");

    Datastream ds = new Datastream();
    ds.setName("Test-DS-" + Math.random());

    TimeObject time = new TimeObject();
    time.setIdentifier("time");
    time.setFormat("millis");
    time.setZone("GMT");

    Signal signal = new Signal();
    signal.setTagIdentifier("tag");
    signal.setValueIdentifier("value");
    signal.setDelimiter("_");
    signal.setIsSignalPrefix(false);

    Datasource dataSource = new Datasource();
    dataSource.setType("STANDALONE");

    Field field = new Field();
    field.setSiganl(signal);
    field.setTime(time);
    //field.setEntityIdentifier("unit");

    ds.setDatasource(dataSource);
    ds.setField(field);

    Datastream datastream = falkonry.createDatastream(ds);
    

    //Add data to datastream
    String data = "{\"time\":1467729675422,\"signal1\":41.11,\"signal2\":82.34,\"signal3\":74.63,\"signal4\":4.8}" + "\n"
        + "{\"time\":1467729668919,\"signal1\":78.11,\"signal2\":2.33,\"signal3\":4.6,\"signal4\":9.8}";
    Map<String, String> options = new HashMap<String, String>();
    options.put("timeIdentifier", "time");
    options.put("timeFormat", "millis");
    options.put("fileFormat", "csv");
    falkonry.addInput(datastream.getId(), data, options);

```

#### Setup Datastream for wide style data from multiple entities

Data:
```
    {"time":1467729675422, "entities": "entity1", "signal1":41.11, "signal2":82.34, "signal3":74.63, "signal4":4.8}
    {"time":1467729668919, "entities": "entity2", "signal1":78.11, "signal2":2.33, "signal3":4.6, "signal4":9.8}

    or

    time, entities, signal1, signal2, signal3, signal4
    1467729675422, entity1, 41.11, 62.34, 77.63, 4.8
    1467729675445, entity1, 43.91, 82.64, 73.63, 3.8
```

Usage:
```java
    import com.falkonry.client.Falkonry;
    import com.falkonry.helper.models.Datasource;
    import com.falkonry.helper.models.Datastream;
    import com.falkonry.helper.models.Field;
    import com.falkonry.helper.models.TimeObject;
    import com.falkonry.helper.models.Signal;

    //instantiate Falkonry
    Falkonry falkonry = new Falkonry("https://sandbox.falkonry.ai", "auth-token");

    Datastream ds = new Datastream();
    ds.setName("Test-DS-" + Math.random());

    TimeObject time = new TimeObject();
    time.setIdentifier("time");
    time.setFormat("millis");
    time.setZone("GMT");

    Signal signal = new Signal();
    signal.setTagIdentifier("tag");
    signal.setValueIdentifier("value");
    signal.setDelimiter("_");
    signal.setIsSignalPrefix(false);

    Datasource dataSource = new Datasource();
    dataSource.setType("STANDALONE");

    Field field = new Field();
    field.setSiganl(signal);
    field.setTime(time);
    field.setEntityIdentifier("entities");

    ds.setDatasource(dataSource);
    ds.setField(field);

    Datastream datastream = falkonry.createDatastream(ds);


    //Add data to datastream
    String data = "time, entities, signal1, signal2, signal3, signal4" + "\n"
        + "1467729675422, entity1, 41.11, 62.34, 77.63, 4.8" + "\n"
        + "1467729675445, entity1, 43.91, 82.64, 73.63, 3.8";
    Map<String, String> options = new HashMap<String, String>();
    options.put("timeIdentifier", "time");
    options.put("timeFormat", "millis");
    options.put("fileFormat", "csv");
    falkonry.addInput(datastream.getId(), data, options);
```

#### Get a Datastream

```java
    import com.falkonry.client.Falkonry;

    Falkonry falkonry = new Falkonry("https://sandbox.falkonry.ai", "auth-token");

    datastream = falkonry.getDatastream("datastream_id"); //datastream's id
```

#### Delete a Datastream

```java
    import com.falkonry.client.Falkonry;

    Falkonry falkonry = new Falkonry("https://sandbox.falkonry.ai", "auth-token");

    datastream = falkonry.deleteDatastream("datastream_id"); //datastream's id
```

#### Add json data from a stream to an Datastream
    
```java
    import com.falkonry.client.Falkonry
    import org.apache.commons.io.FileUtils;

    Falkonry falkonry   = new Falkonry("https://sandbox.falkonry.ai", "auth-token");
    Map<String, String> options = new HashMap<String, String>();

    File file = new File("tmp/data.json");      
    ByteArrayInputStream istream = new ByteArrayInputStream(FileUtils.readFileToByteArray(file));

    InputStatus inputStatus = falkonry.addInputStream(datastream.getId(),byteArrayInputStream,options);
```

#### Add csv data from a stream to an Datastream
    
```java
    import com.falkonry.client.Falkonry
    import org.apache.commons.io.FileUtils;

    Falkonry falkonry   = new Falkonry("https://sandbox.falkonry.ai", "auth-token");
    Map<String, String> options = new HashMap<String, String>();

    File file = new File("tmp/data.csv");     
    ByteArrayInputStream istream = new ByteArrayInputStream(FileUtils.readFileToByteArray(file));

    InputStatus inputStatus = falkonry.addInputStream(datastream.getId(),byteArrayInputStream,options);
```

#### Create Assessment from Datastream
    
```java
    import com.falkonry.client.Falkonry;
    import com.falkonry.helper.models.*;

    //instantiate Falkonry
    Falkonry falkonry = new Falkonry("https://sandbox.falkonry.ai", "auth-token");

    Datastream ds = new Datastream();
    ds.setName("Test-DS-" + Math.random());
    TimeObject time = new TimeObject();
    time.setIdentifier("time");
    time.setFormat("iso_8601");
    time.setZone("GMT");

    Field field = new Field();
    field.setTime(time);
    ds.setField(field);
    Datasource dataSource = new Datasource();
    dataSource.setType("PI");
    dataSource.sethost("https://test.piserver.com/piwebapi");
    dataSource.setElementTemplateName("SampleElementTempalte");
    ds.setDatasource(dataSource);

    // Input List
    List<Input> inputList = new ArrayList<Input>();
    Input currents = new Input();
    ValueType valueType = new ValueType();
    EventType eventType = new EventType();
    currents.setName("current");
    valueType.setType("Numeric");
    eventType.setType("Samples");
    currents.setValueType(valueType);
    currents.setEventType(eventType);
    inputList.add(currents);

    Input vibration = new Input();
    vibration.setName("vibration");
    valueType.setType("Numeric");
    eventType.setType("Samples");
    vibration.setValueType(valueType);
    vibration.setEventType(eventType);
    inputList.add(vibration);

    Input state = new Input();
    state.setName("state");
    valueType.setType("Categorical");
    eventType.setType("Samples");
    state.setValueType(valueType);
    state.setEventType(eventType);
    inputList.add(state);

    ds.setInputList(inputList);

    Datastream datastream = falkonry.createDatastream(ds);

    AssessmentRequest assessmentRequest = new AssessmentRequest();
    assessmentRequest.setName("Health");
    assessmentRequest.setDatastream(datastream.getId());
    assessmentRequest.setAssessmentRate("PT1S");
    Assessment assessment = falkonry.createAssessment(assessmentRequest);
    
```

#### To get all Assessments
    
```java
    import com.falkonry.client.Falkonry;

    Falkonry falkonry = new Falkonry("https://sandbox.falkonry.ai", "auth-token");

    List<Assessment> datastreams = falkonry.getAssessments();
```

#### To get individual assessment
    
```java
    import com.falkonry.client.Falkonry;

    Falkonry falkonry = new Falkonry("https://sandbox.falkonry.ai", "auth-token");

    Assessment assesssment = falkonry.getAssessment('assessment_id');
```


#### To Delete an assessment
    
```java
    import com.falkonry.client.Falkonry;

    Falkonry falkonry = new Falkonry("https://sandbox.falkonry.ai", "auth-token");

    Assessment assesssment = falkonry.deleteAssessment('assessment_id');
```

#### Add facts data (json format) to a Assessment

```java
    import com.falkonry.client.Falkonry;

    Falkonry falkonry = new Falkonry("https://sandbox.falkonry.ai", "auth-token");

    String data = "{\"time\" : \"2011-03-26T12:00:00Z\", \"entities\" : \"entity1\", \"end\" : \"2012-06-01T00:00:00Z\", \"Health\" : \"Normal\"}";
    String response = falkonry.addfacts(assessment.getId(),data, options);
```

#### To add facts data (csv format) to a Assessment

```java
    import com.falkonry.client.Falkonry;

    Falkonry falkonry = new Falkonry("https://sandbox.falkonry.ai", "auth-token");

    String data = "time,end,car,Health\n2011-03-31T00:00:00Z,2011-04-01T00:00:00Z,IL9753,Normal\n2011-03-31T00:00:00Z,2011-04-01T00:00:00Z,HI3821,Normal";
    String response = falkonry.addFacts(assessment.getId(),data, options);
```

#### Add facts data (json format) from a stream to a Assessment
    
```java
    import com.falkonry.client.Falkonry;
    import org.apache.commons.io.FileUtils;

    Falkonry falkonry   = new Falkonry("https://sandbox.falkonry.ai", "auth-token");
    File file = new File("res/factsData.json");      
    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(FileUtils.readFileToByteArray(file));
    String response = falkonry.addFactsStream(assessment.getId(),byteArrayInputStream, options);
```

#### Add facts data (csv format) from a stream to a Assessment
    
```java
    import com.falkonry.client.Falkonry;
    import org.apache.commons.io.FileUtils;

    Falkonry falkonry   = new Falkonry("https://sandbox.falkonry.ai", "auth-token");
    File file = new File("res/factsData.csv");      
    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(FileUtils.readFileToByteArray(file));
    String response = falkonry.addFactsStream(assessment.getId(),byteArrayInputStream, options);
```


#### Get Historical Output

````

    Map<String, String> options = new HashMap<String, String>();
    options.put("startTime", "2011-07-17T01:00:00.000Z"); // in the format YYYY-MM-DDTHH:mm:ss.SSSZ
    options.put("endTime", "2011-08-18T01:00:00.000Z");  // in the format YYYY-MM-DDTHH:mm:ss.SSSZ
    options.put("responseFormat", "application/json");  // also avaibale options 1. text/csv 2. application/json

    assessment.setId("wpyred1glh6c5r");
    HttpResponseFormat httpResponse = falkonry.GetHistoricalOutput(assessment_id, options);

````

#### Get Streaming Output

````

    String assessment = "wpyred1glh6c5r";
    BufferedReader outputBuffer;
    outputBuffer = falkonry.getOutput(assessment,null,null);

````

## Docs

    [Falkonry APIs](https://sandbox.falkonry.ai/api)
     
## Tests

  To run the test suite, first install the dependencies, then run tests from the test folder.
  

## License

  Available under [MIT License](LICENSE)
