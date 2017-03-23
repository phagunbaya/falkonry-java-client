package com.falkonry;

import com.falkonry.client.Falkonry;
import com.falkonry.helper.models.Datastream;
import com.falkonry.helper.models.TimeObject;
import com.falkonry.helper.models.Field;
import com.falkonry.helper.models.Input;
import com.falkonry.helper.models.ValueType;
import com.falkonry.helper.models.EventType;
import com.falkonry.helper.models.Datasource;
import org.junit.*;

import java.util.*;

/*!
 * falkonry-java-client
 * Copyright(c) 2016 Falkonry Inc
 * MIT Licensed
 */
public class TestAddData {

    Falkonry falkonry = null;

//    String host = "https://dev.falkonry.ai";
//    String token = "6bvyjtb2mlh4f3v93stdoe6ai5ea6d3t";
    String host = "https://localhost:8080";
    String token = "yf15jw8igeppzqba86essum3ycdeqi9u";
    List<Datastream> datastreams = new ArrayList<Datastream>();

    @Before
    public void setUp() throws Exception {
        falkonry = new Falkonry(host, token);

    }

    @Test
    public void addDataJson() throws Exception {
        Datastream ds = new Datastream();
        ds.setName("Test-DS-" + Math.random());
        TimeObject time = new TimeObject();
        time.setIdentifier("time");
        time.setFormat("iso_8601");
        time.setZone("GMT");
//        Signal signal = new Signal();
//        signal.setTagIdentifier("tag");
//        signal.setValueIdentifier("value");
//        signal.setDelimiter("_");
//        signal.setIsSignalPrefix(false);

        Field field = new Field();
//        field.setSiganl(signal);
        field.setTime(time);
        field.setEntityIdentifier("unit");
        ds.setField(field);
        Datasource dataSource = new Datasource();
        dataSource.setType("PI");
        dataSource.sethost("https://test.piserver.com/piwebapi");
        dataSource.setElementTemplateName("SampleElementTempalte");
        ds.setDatasource(dataSource);

        // Input List
//        Input inputList = new TypeReference<List<Input>>(){};
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
        datastreams.add(datastream);
//        String data1 = "{\"time\" : \"2016-03-01 01:01:01\", \"tag\" : \"signal1_entity1\", \"value\" : 3.4}";
        String data = "{\"time\" :\"2016-03-01 01:01:01\",\"Unit\":\"Unit1\", \"current\" : 12.4, \"vibration\" : 3.4, \"state\" : \"On\"}";

        Map<String, String> options = new HashMap<String, String>();

        falkonry.addInput(datastream.getId(), data, options);

        datastream = falkonry.getUpdatedDatastream(datastream.getId());
        falkonry.deleteDatastream(datastream.getId());
//        Assert.assertEquals(1, datastream.getSchemaList().size());
    }

    /*
    //@Test
    public void addWideDataJson() throws Exception {
        Datastream ds = new Datastream();
        ds.setName("Test-EB-" + Math.random());
        ds.setEntityIdentifier("entity");
        ds.setTimeIdentifier("time");
        ds.setTimeFormat("millis");

        Datastream eventbuffer = falkonry.createDatastream(ds);
        datastreams.add(eventbuffer);
        String data = "{\"time\":1467729675422,\"entity\":\"entity1\",\"signal1\":41.11,\"signal2\":82.34,\"signal3\":74.63,\"signal4\":4.8,\"signal5\":72.01}";
        Map<String, String> options = new HashMap<String, String>();
        falkonry.addInput(eventbuffer.getId(), data, options);

        eventbuffer = falkonry.getUpdatedDatastream(eventbuffer.getId());
        Assert.assertEquals(1, eventbuffer.getSchemaList().size());
    }

    //@Test
    public void addDataCsv() throws Exception {
        Datastream ds = new Datastream();
        ds.setName("Test-EB-" + Math.random());
        ds.setTimeIdentifier("time");
        ds.setTimeFormat("iso_8601");
        ds.setValueColumn("value");
        ds.setSignalsDelimiter("_");
        ds.setSignalsLocation("prefix");
        ds.setSignalsTagField("tag");
        Datastream eventbuffer = falkonry.createDatastream(ds);
        datastreams.add(eventbuffer);
        String data = "time, tag, value\n" + "2016-03-01 01:01:01, signal1_entity1, 3.4";
        Map<String, String> options = new HashMap<String, String>();
        falkonry.addInput(eventbuffer.getId(), data, options);
        eventbuffer = falkonry.getUpdatedDatastream(eventbuffer.getId());
        Assert.assertEquals(1, eventbuffer.getSchemaList().size());
    }

    //@Test
    public void addWideDataCsv() throws Exception {
        Datastream ds = new Datastream();
        ds.setName("Test-EB-" + Math.random());
        ds.setEntityIdentifier("entity");
        ds.setTimeIdentifier("time");
        ds.setTimeFormat("millis");

        Datastream eventbuffer = falkonry.createDatastream(ds);
        datastreams.add(eventbuffer);
        String data = "time,entity,signal1,signal2,signal3,signal4,signal5\n"
                + "1467729675422,entity1,41.11,82.34,74.63,4.8,72.01";
        Map<String, String> options = new HashMap<String, String>();
        falkonry.addInput(eventbuffer.getId(), data, options);

        eventbuffer = falkonry.getUpdatedDatastream(eventbuffer.getId());
        Assert.assertEquals(1, eventbuffer.getSchemaList().size());
    }

    @After
    public void cleanUp() throws Exception {
        Iterator<Datastream> itr = datastreams.iterator();
        while (itr.hasNext()) {
            Datastream ds = itr.next();
            falkonry.deleteDatastream(ds.getId());
        }
    }
     */
}
