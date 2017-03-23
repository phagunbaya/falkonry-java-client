//package com.falkonry;
//
//import com.falkonry.client.Falkonry;
//import com.falkonry.helper.models.Datasource;
//import com.falkonry.helper.models.Datastream;
//import com.falkonry.helper.models.Field;
//import com.falkonry.helper.models.InputStatus;
//import com.falkonry.helper.models.Signal;
//import com.falkonry.helper.models.TimeObject;
//import org.apache.commons.io.FileUtils;
//import org.junit.*;
//
//import java.io.ByteArrayInputStream;
//import java.io.File;
//import java.util.*;
//
///*!
// * falkonry-java-client
// * Copyright(c) 2016 Falkonry Inc
// * MIT Licensed
// */
//public class TestAddDataStream {
//
//    Falkonry falkonry = null;
//    String host = "http://localhost:8080";
//    String token = "";
//    List<Datastream> eventbuffers = new ArrayList<Datastream>();
//
//    @Before
//    public void setUp() throws Exception {
//        falkonry = new Falkonry(host, token);
//    }
//
//    //@Test
//    public void addDataJsonStream() throws Exception {
//
//        Datastream ds = new Datastream();
//        ds.setName("Test-DS-" + Math.random());
//
//        TimeObject time = new TimeObject();
//        time.setIdentifier("time");
//        time.setFormat("iso_8601");
//        time.setZone("GMT");
//
//        Signal signal = new Signal();
//        signal.setTagIdentifier("tag");
//        signal.setValueIdentifier("value");
//        signal.setDelimiter("_");
//        signal.setIsSignalPrefix(false);
//
//        Datasource dataSource = new Datasource();
//        dataSource.setType("STANDALONE");
//
//        Field field = new Field();
//        field.setSiganl(signal);
//        field.setTime(time);
//
//        ds.setDatasource(dataSource);
//        ds.setField(field);
//
//        Datastream datastream = falkonry.createDatastream(ds);
//        datastreams.add(datastream);
//        
//       
//       
//        Map<String, String> options = new HashMap<String, String>();
//        Datastream eventbuffer = falkonry.createDatastream(ds);
//        eventbuffers.add(eventbuffer);
//        File file = new File("res/data.json");
//        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(FileUtils.readFileToByteArray(file));
//        InputStatus inputStatus = falkonry.addInputStream(eventbuffer.getId(), byteArrayInputStream, options);
//        eventbuffer = falkonry.getUpdatedDatastream(eventbuffer.getId());
//        Assert.assertEquals(1, eventbuffer.getSchemaList().size());
//    }
//
//    //@Test
//    public void addWideDataJsonStream() throws Exception {
//        Datastream ds = new Datastream();
//        ds.setName("Test-EB-" + Math.random());
//        ds.setTimeIdentifier("time");
//        ds.setTimeFormat("millis");
//        ds.setEntityIdentifier("entity");
//        Map<String, String> options = new HashMap<String, String>();
//        Datastream eventbuffer = falkonry.createDatastream(ds);
//        eventbuffers.add(eventbuffer);
//        File file = new File("res/data_wide.json");
//        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(FileUtils.readFileToByteArray(file));
//        InputStatus inputStatus = falkonry.addInputStream(eventbuffer.getId(), byteArrayInputStream, options);
//        eventbuffer = falkonry.getUpdatedDatastream(eventbuffer.getId());
//        Assert.assertEquals(1, eventbuffer.getSchemaList().size());
//    }
//
//    //@Test
//    public void addDataCsvStream() throws Exception {
//        Datastream ds = new Datastream();
//        ds.setName("Test-EB-" + Math.random());
//        ds.setTimeIdentifier("time");
//        ds.setTimeFormat("iso_8601");
//        ds.setValueColumn("value");
//        ds.setSignalsDelimiter("_");
//        ds.setSignalsLocation("prefix");
//        ds.setSignalsTagField("tag");
//        Map<String, String> options = new HashMap<String, String>();
//        Datastream eventbuffer = falkonry.createDatastream(ds);
//        eventbuffers.add(eventbuffer);
//        File file = new File("res/data.csv");
//        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(FileUtils.readFileToByteArray(file));
//        InputStatus inputStatus = falkonry.addInputStream(eventbuffer.getId(), byteArrayInputStream, options);
//        eventbuffer = falkonry.getUpdatedDatastream(eventbuffer.getId());
//        Assert.assertEquals(1, eventbuffer.getSchemaList().size());
//    }
//
//    //@Test
//    public void addWideDataCsvStream() throws Exception {
//        Datastream ds = new Datastream();
//        ds.setName("Test-EB-" + Math.random());
//        ds.setTimeIdentifier("time");
//        ds.setTimeFormat("millis");
//        ds.setEntityIdentifier("entity");
//        Map<String, String> options = new HashMap<String, String>();
//        Datastream datastream = falkonry.createDatastream(ds);
//        eventbuffers.add(eventbuffer);
//        File file = new File("res/data_wide.csv");
//        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(FileUtils.readFileToByteArray(file));
//        InputStatus inputStatus = falkonry.addInputStream(eventbuffer.getId(), byteArrayInputStream, options);
//        eventbuffer = falkonry.getUpdatedDatastream(eventbuffer.getId());
//        Assert.assertEquals(1, eventbuffer.getSchemaList().size());
//    }
//
//    @After
//    public void cleanUp() throws Exception {
//        Iterator<Datastream> itr = eventbuffers.iterator();
//        while (itr.hasNext()) {
//            Datastream ds = itr.next();
//            falkonry.deleteDatastream(ds.getId());
//        }
//    }
//
//}
