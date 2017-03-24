package com.falkonry;

import com.falkonry.client.Falkonry;
import com.falkonry.helper.models.Datasource;
import com.falkonry.helper.models.Datastream;
import com.falkonry.helper.models.Field;
import com.falkonry.helper.models.InputStatus;
import com.falkonry.helper.models.Signal;
import com.falkonry.helper.models.TimeObject;
import org.apache.commons.io.FileUtils;
import org.junit.*;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.*;

/*!
 * falkonry-java-client
 * Copyright(c) 2016 Falkonry Inc
 * MIT Licensed
 */
public class TestAddDataStream {

    Falkonry falkonry = null;
    String host = "https://localhost:8080";
    String token = "yf15jw8igeppzqba86essum3ycdeqi9u";
    List<Datastream> datastreams = new ArrayList<Datastream>();

    @Before
    public void setUp() throws Exception {
        falkonry = new Falkonry(host, token);
    }

    @Test
    public void addDataJsonStream() throws Exception {

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

        ds.setDatasource(dataSource);
        ds.setField(field);

        Datastream datastream = falkonry.createDatastream(ds);
        datastreams.add(datastream);

        Map<String, String> options = new HashMap<String, String>();
        
        File file = new File("res/data.json");
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(FileUtils.readFileToByteArray(file));
        InputStatus inputStatus = falkonry.addInputStream(datastream.getId(), byteArrayInputStream, options);
        datastream = falkonry.getUpdatedDatastream(datastream.getId());
    }

    @Test
    public void addWideDataJsonStream() throws Exception {

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

        ds.setDatasource(dataSource);
        ds.setField(field);

        Datastream datastream = falkonry.createDatastream(ds);
        datastreams.add(datastream);

        Map<String, String> options = new HashMap<String, String>();

        File file = new File("res/data_wide.json");
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(FileUtils.readFileToByteArray(file));
        InputStatus inputStatus = falkonry.addInputStream(datastream.getId(), byteArrayInputStream, options);
        datastream = falkonry.getUpdatedDatastream(datastream.getId());
    }

    @Test
    public void addDataCsvStream() throws Exception {

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

        ds.setDatasource(dataSource);
        ds.setField(field);

        Datastream datastream = falkonry.createDatastream(ds);
        datastreams.add(datastream);

        Map<String, String> options = new HashMap<String, String>();

        File file = new File("res/data.csv");
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(FileUtils.readFileToByteArray(file));
        InputStatus inputStatus = falkonry.addInputStream(datastream.getId(), byteArrayInputStream, options);
        datastream = falkonry.getUpdatedDatastream(datastream.getId());
    }

    @Test
    public void addWideDataCsvStream() throws Exception {

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

        ds.setDatasource(dataSource);
        ds.setField(field);

        Datastream datastream = falkonry.createDatastream(ds);
        datastreams.add(datastream);

        Map<String, String> options = new HashMap<String, String>();
        File file = new File("res/data_wide.csv");
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(FileUtils.readFileToByteArray(file));
        InputStatus inputStatus = falkonry.addInputStream(datastream.getId(), byteArrayInputStream, options);
        datastream = falkonry.getUpdatedDatastream(datastream.getId());
    }

    @After
    public void cleanUp() throws Exception {
        Iterator<Datastream> itr = datastreams.iterator();
        while (itr.hasNext()) {
            Datastream ds = itr.next();
            falkonry.deleteDatastream(ds.getId());
        }
    }

}
