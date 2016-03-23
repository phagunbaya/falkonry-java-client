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

Map<String, String> signals = new HashMap<String, String>();
signals.put("current", "Numeric");
signals.put("vibration", "Numeric");

ArrayList<String> assessment_signals = new ArrayList<String>();
assessment_signals.add("current");
assessment_signals.add("vibration");

Assessment assessment = new schemas.Assessment()
                .setName("Health")
                .addSignals(assessment_signals);
                        
Pipeline pipeline = new schemas.Pipeline()
                .setName("Motor Health")
                .setThingName("Motor")
                .setTimeIdentifier("time")
                .setTimeFormat("YYYY-MM-DD HH:MM:SS")
                .setInputSignals(signals)
                .addAssessment(assessment);
        
Pipeline createdPipeline = falkonry.createPipeline(pipeline);
```

    * To get all Pipelines
    
```java
import com.falkonry.client.Falkonry
import com.falkonry.schemas

Falkonry falkonry = new Falkonry("https://service.falkonry.io", "auth-token")

List<Pipeline> pipelines = falkonry.getPipelines()
```

    * To add data
    
```java
import org.json.simple.JSONObject
import com.falkonry.client.Falkonry

List<JSONObject> data = new ArrayList<JSONObject>();
data.add(
    new JSONObject()
        .put("time", 1456528122024L)
        .put("current", new Double(3.86))
        .put("vibration", new Double(4.2))
);
data.add(
    new JSONObject()
        .put("time", 1456528132024L)
        .put("current", new Double(4.456))
        .put("vibration", new Double(6.8))
);
data.add(
    new JSONObject()
        .put("time", 1456528142024L)
        .put("current", new Double(2.4690))
        .put("vibration", new Double(9.3))
);

Falkonry falkonry = new Falkonry("https://service.falkonry.io", "auth-token")

inputResponse = falkonry.addInput("pipeline_id", data)
```

    * To add data from a stream
    
```java
import com.falkonry.client.Falkonry

Falkonry falkonry   = new Falkonry("https://service.falkonry.io", "auth-token")
InputStream istream = new FileInputStream(new File("/tmp/data.json"));

inputResponse = falkonry.addInputFromStream("pipeline_id", istream);
```

    * To get output of a Pipeline
    
```java
import com.falkonry.client.Falkonry

Falkonry falkonry = new Falkonry("https://service.falkonry.io", "auth-token")
OutputStream os   = new FileOutputStream("/tmp/sample.json");
Long startTime    = "1457018017"; //seconds since unix epoch 
Long endTime      = "1457028017"; //seconds since unix epoch

OutputStream outputStream = falkonry.getOutput("pipeline_id", startTime, endTime);
```

## Docs

    * [Falkonry APIs](https://service.falkonry.io/api)
     
## Tests

  To run the test suite, first install the dependencies, then run `Test.sh`:
  
```
$ ./Test.sh
```

## License

  Available under [MIT License](LICENSE)
