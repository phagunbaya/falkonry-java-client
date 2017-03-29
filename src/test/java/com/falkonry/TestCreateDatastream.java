package com.falkonry;

import com.falkonry.client.Falkonry;
import com.falkonry.helper.models.Datasource;
import com.falkonry.helper.models.Datastream;
import com.falkonry.helper.models.Field;
import com.falkonry.helper.models.TimeObject;
import com.falkonry.helper.models.Signal;
import org.junit.*;

import java.util.*;

/*!
 * falkonry-java-client
 * Copyright(c) 2016 Falkonry Inc
 * MIT Licensed
 */

/**
 *
 * @author dev-falkonry-10
 */

public class TestCreateDatastream {

    Falkonry falkonry = null;
    String host = "https://localhost:8080";
    String token = "yf15jw8igeppzqba86essum3ycdeqi9u";
    List<Datastream> datastreams = new ArrayList<Datastream>();

    /**
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        falkonry = new Falkonry(host, token);
    }

    /**
     *
     * @throws Exception
     */
    @Test
    public void createDatastream() throws Exception {
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
//        field.setEntityIdentifier("unit");

        ds.setDatasource(dataSource);
        ds.setField(field);

        Datastream datastream = falkonry.createDatastream(ds);
        datastreams.add(datastream);

        Assert.assertEquals(ds.getName(), datastream.getName());
        Assert.assertNotEquals(null, datastream.getId());

        Assert.assertEquals(datastream.getField().getTime().getFormat(), ds.getField().getTime().getFormat());
        Assert.assertEquals(datastream.getField().getTime().getIdentifier(), ds.getField().getTime().getIdentifier());
        Assert.assertEquals(datastream.getField().getTime().getZone(), ds.getField().getTime().getZone());

        Assert.assertEquals(datastream.getDatasource().getType(), ds.getDatasource().getType());

        Assert.assertEquals(datastream.getField().getSignal().getDelimiter(), ds.getField().getSignal().getDelimiter());
        Assert.assertEquals(datastream.getField().getSignal().getIsSignalPrefix(), ds.getField().getSignal().getIsSignalPrefix());
        Assert.assertEquals(datastream.getField().getSignal().getTagIdentifier(), ds.getField().getSignal().getTagIdentifier());
        Assert.assertEquals(datastream.getField().getSignal().getValueIdentifier(), ds.getField().getSignal().getValueIdentifier());
    }
    
    /**
     *
     * @throws Exception
     */
    @Test
    public void getDatastreamList() throws Exception {
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
//        field.setEntityIdentifier("unit");

        ds.setDatasource(dataSource);
        ds.setField(field);

        Datastream datastream = falkonry.createDatastream(ds);
        datastreams.add(datastream);

        Assert.assertEquals(ds.getName(), datastream.getName());
        Assert.assertNotEquals(null, datastream.getId());

        Assert.assertEquals(datastream.getField().getTime().getFormat(), ds.getField().getTime().getFormat());
        Assert.assertEquals(datastream.getField().getTime().getIdentifier(), ds.getField().getTime().getIdentifier());
        Assert.assertEquals(datastream.getField().getTime().getZone(), ds.getField().getTime().getZone());

        Assert.assertEquals(datastream.getDatasource().getType(), ds.getDatasource().getType());

        Assert.assertEquals(datastream.getField().getSignal().getDelimiter(), ds.getField().getSignal().getDelimiter());
        Assert.assertEquals(datastream.getField().getSignal().getIsSignalPrefix(), ds.getField().getSignal().getIsSignalPrefix());
        Assert.assertEquals(datastream.getField().getSignal().getTagIdentifier(), ds.getField().getSignal().getTagIdentifier());
        Assert.assertEquals(datastream.getField().getSignal().getValueIdentifier(), ds.getField().getSignal().getValueIdentifier());
    
        List<Datastream> datastream1 = falkonry.getDatastreams();
        Assert.assertEquals(true,datastream1.size()>1);
        
    }
    
    /**
     *
     * @throws Exception
     */
    @Test
    public void getDatastreamById() throws Exception {
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
//        field.setEntityIdentifier("unit");

        ds.setDatasource(dataSource);
        ds.setField(field);

        Datastream datastream = falkonry.createDatastream(ds);
        datastreams.add(datastream);

        Assert.assertEquals(ds.getName(), datastream.getName());
        Assert.assertNotEquals(null, datastream.getId());

        Assert.assertEquals(datastream.getField().getTime().getFormat(), ds.getField().getTime().getFormat());
        Assert.assertEquals(datastream.getField().getTime().getIdentifier(), ds.getField().getTime().getIdentifier());
        Assert.assertEquals(datastream.getField().getTime().getZone(), ds.getField().getTime().getZone());

        Assert.assertEquals(datastream.getDatasource().getType(), ds.getDatasource().getType());

        Assert.assertEquals(datastream.getField().getSignal().getDelimiter(), ds.getField().getSignal().getDelimiter());
        Assert.assertEquals(datastream.getField().getSignal().getIsSignalPrefix(), ds.getField().getSignal().getIsSignalPrefix());
        Assert.assertEquals(datastream.getField().getSignal().getTagIdentifier(), ds.getField().getSignal().getTagIdentifier());
        Assert.assertEquals(datastream.getField().getSignal().getValueIdentifier(), ds.getField().getSignal().getValueIdentifier());
    
        Datastream datastream1 = falkonry.getDatastream(datastream.getId());
        Assert.assertEquals(datastream1.getName(),datastream.getName());
        Assert.assertEquals(datastream1.getId(),datastream.getId());
        
        Assert.assertEquals(datastream.getField().getTime().getFormat(), datastream1.getField().getTime().getFormat());
        Assert.assertEquals(datastream.getField().getTime().getIdentifier(), datastream1.getField().getTime().getIdentifier());
        Assert.assertEquals(datastream.getField().getTime().getZone(), datastream1.getField().getTime().getZone());

        Assert.assertEquals(datastream.getDatasource().getType(), datastream1.getDatasource().getType());

        Assert.assertEquals(datastream.getField().getSignal().getDelimiter(), datastream1.getField().getSignal().getDelimiter());
        Assert.assertEquals(datastream.getField().getSignal().getIsSignalPrefix(), datastream1.getField().getSignal().getIsSignalPrefix());
        Assert.assertEquals(datastream.getField().getSignal().getTagIdentifier(), datastream1.getField().getSignal().getTagIdentifier());
        Assert.assertEquals(datastream.getField().getSignal().getValueIdentifier(), datastream1.getField().getSignal().getValueIdentifier());
        
    }

    
    //@Test

    /**
     *
     * @throws Exception
     */
    public void updateDatastream() throws Exception {
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
//        field.setEntityIdentifier("unit");

        ds.setDatasource(dataSource);
        ds.setField(field);

        Datastream datastream = falkonry.createDatastream(ds);
        datastreams.add(datastream);

        Assert.assertEquals(ds.getName(), datastream.getName());
        Assert.assertNotEquals(null, datastream.getId());

        Assert.assertEquals(datastream.getField().getTime().getFormat(), ds.getField().getTime().getFormat());
        Assert.assertEquals(datastream.getField().getTime().getIdentifier(), ds.getField().getTime().getIdentifier());
        Assert.assertEquals(datastream.getField().getTime().getZone(), ds.getField().getTime().getZone());

        Assert.assertEquals(datastream.getDatasource().getType(), ds.getDatasource().getType());

        Assert.assertEquals(datastream.getField().getSignal().getDelimiter(), ds.getField().getSignal().getDelimiter());
        Assert.assertEquals(datastream.getField().getSignal().getIsSignalPrefix(), ds.getField().getSignal().getIsSignalPrefix());
        Assert.assertEquals(datastream.getField().getSignal().getTagIdentifier(), ds.getField().getSignal().getTagIdentifier());
        Assert.assertEquals(datastream.getField().getSignal().getValueIdentifier(), ds.getField().getSignal().getValueIdentifier());
    
        datastream.setName("Test-DS-" + Math.random());
        Datastream datastream1 = falkonry.updateDatastream(datastream);
        Assert.assertEquals(datastream1.getName(),datastream.getName());
        Assert.assertEquals(datastream1.getId(),datastream.getId());
        
        Assert.assertEquals(datastream.getField().getTime().getFormat(), datastream1.getField().getTime().getFormat());
        Assert.assertEquals(datastream.getField().getTime().getIdentifier(), datastream1.getField().getTime().getIdentifier());
        Assert.assertEquals(datastream.getField().getTime().getZone(), datastream1.getField().getTime().getZone());

        Assert.assertEquals(datastream.getDatasource().getType(), datastream1.getDatasource().getType());

        Assert.assertEquals(datastream.getField().getSignal().getDelimiter(), datastream1.getField().getSignal().getDelimiter());
        Assert.assertEquals(datastream.getField().getSignal().getIsSignalPrefix(), datastream1.getField().getSignal().getIsSignalPrefix());
        Assert.assertEquals(datastream.getField().getSignal().getTagIdentifier(), datastream1.getField().getSignal().getTagIdentifier());
        Assert.assertEquals(datastream.getField().getSignal().getValueIdentifier(), datastream1.getField().getSignal().getValueIdentifier());
        
    }
    
    /**
     *
     * @throws Exception
     */
    @Test
    public void createWideDatastream() throws Exception {

        Datastream ds = new Datastream();
        ds.setName("Test-DS-" + Math.random());
        TimeObject time = new TimeObject();
        time.setIdentifier("time");
        time.setFormat("iso_8601");
        time.setZone("GMT");

        Datasource dataSource = new Datasource();
        dataSource.setType("STANDALONE");

        Field field = new Field();
        field.setTime(time);
        field.setEntityIdentifier("unit");

        ds.setDatasource(dataSource);
        ds.setField(field);

        Datastream datastream = falkonry.createDatastream(ds);
        datastreams.add(datastream);

        Assert.assertEquals(ds.getName(), datastream.getName());
        Assert.assertNotEquals(null, datastream.getId());

        Assert.assertEquals(datastream.getField().getTime().getFormat(), ds.getField().getTime().getFormat());
        Assert.assertEquals(datastream.getField().getTime().getIdentifier(), ds.getField().getTime().getIdentifier());
        Assert.assertEquals(datastream.getField().getTime().getZone(), ds.getField().getTime().getZone());

        Assert.assertEquals(datastream.getDatasource().getType(), ds.getDatasource().getType());
    }

    /**
     *
     * @throws Exception
     */
    @Test
    public void createDatastreamWithJsonData() throws Exception {

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
//        field.setEntityIdentifier("unit");

        ds.setDatasource(dataSource);
        ds.setField(field);

        Datastream datastream = falkonry.createDatastream(ds);
        datastreams.add(datastream);

        String data = "{\"time\" : \"2016-03-01 01:01:01\", \"tag\" : \"signal1_entity1\", \"value\" : 3.4}";
        Map<String, String> options = new HashMap<String, String>();
        falkonry.addInput(datastream.getId(), data, options);

        Assert.assertEquals(ds.getName(), datastream.getName());
        Assert.assertNotEquals(null, datastream.getId());

        Assert.assertEquals(datastream.getField().getTime().getFormat(), ds.getField().getTime().getFormat());
        Assert.assertEquals(datastream.getField().getTime().getIdentifier(), ds.getField().getTime().getIdentifier());
        Assert.assertEquals(datastream.getField().getTime().getZone(), ds.getField().getTime().getZone());

        Assert.assertEquals(datastream.getDatasource().getType(), ds.getDatasource().getType());

        Assert.assertEquals(datastream.getField().getSignal().getDelimiter(), ds.getField().getSignal().getDelimiter());
        Assert.assertEquals(datastream.getField().getSignal().getIsSignalPrefix(), ds.getField().getSignal().getIsSignalPrefix());
        Assert.assertEquals(datastream.getField().getSignal().getTagIdentifier(), ds.getField().getSignal().getTagIdentifier());
        Assert.assertEquals(datastream.getField().getSignal().getValueIdentifier(), ds.getField().getSignal().getValueIdentifier());

    }

    /**
     *
     * @throws Exception
     */
    @Test
    public void createDatastreamWithWideJsonData() throws Exception {

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
//        field.setEntityIdentifier("entity");

        ds.setDatasource(dataSource);
        ds.setField(field);

        Datastream datastream = falkonry.createDatastream(ds);
        datastreams.add(datastream);

        String data = "{\"time\":1467729675422,\"entity\":\"entity1\",\"signal1\":41.11,\"signal2\":82.34,\"signal3\":74.63,\"signal4\":4.8,\"signal5\":72.01}";
        Map<String, String> options = new HashMap<String, String>();
        falkonry.addInput(datastream.getId(), data, options);

        Assert.assertEquals(ds.getName(), datastream.getName());
        Assert.assertNotEquals(null, datastream.getId());

        Assert.assertEquals(datastream.getField().getTime().getFormat(), ds.getField().getTime().getFormat());
        Assert.assertEquals(datastream.getField().getTime().getIdentifier(), ds.getField().getTime().getIdentifier());
        Assert.assertEquals(datastream.getField().getTime().getZone(), ds.getField().getTime().getZone());

        Assert.assertEquals(datastream.getDatasource().getType(), ds.getDatasource().getType());

        Assert.assertEquals(datastream.getField().getSignal().getDelimiter(), ds.getField().getSignal().getDelimiter());
        Assert.assertEquals(datastream.getField().getSignal().getIsSignalPrefix(), ds.getField().getSignal().getIsSignalPrefix());
        Assert.assertEquals(datastream.getField().getSignal().getTagIdentifier(), ds.getField().getSignal().getTagIdentifier());
        Assert.assertEquals(datastream.getField().getSignal().getValueIdentifier(), ds.getField().getSignal().getValueIdentifier());

    }

    /**
     *
     * @throws Exception
     */
    @Test
    public void createDatastreamWithCsvData() throws Exception {

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

        String data = "time, tag, value\n2016-03-01 01:01:01, signal1_entity1, 3.4";
        Map<String, String> options = new HashMap<String, String>();
        falkonry.addInput(datastream.getId(), data, options);

        Assert.assertEquals(ds.getName(), datastream.getName());
        Assert.assertNotEquals(null, datastream.getId());

        Assert.assertEquals(datastream.getField().getTime().getFormat(), ds.getField().getTime().getFormat());
        Assert.assertEquals(datastream.getField().getTime().getIdentifier(), ds.getField().getTime().getIdentifier());
        Assert.assertEquals(datastream.getField().getTime().getZone(), ds.getField().getTime().getZone());

        Assert.assertEquals(datastream.getDatasource().getType(), ds.getDatasource().getType());

        Assert.assertEquals(datastream.getField().getSignal().getDelimiter(), ds.getField().getSignal().getDelimiter());
        Assert.assertEquals(datastream.getField().getSignal().getIsSignalPrefix(), ds.getField().getSignal().getIsSignalPrefix());
        Assert.assertEquals(datastream.getField().getSignal().getTagIdentifier(), ds.getField().getSignal().getTagIdentifier());
        Assert.assertEquals(datastream.getField().getSignal().getValueIdentifier(), ds.getField().getSignal().getValueIdentifier());

    }

    /**
     *
     * @throws Exception
     */
    @Test
    public void createDatastreamWithWideCsvData() throws Exception {

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
//        field.setEntityIdentifier("unit");

        ds.setDatasource(dataSource);
        ds.setField(field);

        Datastream datastream = falkonry.createDatastream(ds);
        datastreams.add(datastream);

        String data = "time,entity,signal1,signal2,signal3,signal4,signal5\n"
                + "1467729675422,entity1,41.11,82.34,74.63,4.8,72.01";
        Map<String, String> options = new HashMap<String, String>();
        falkonry.addInput(datastream.getId(), data, options);

        Assert.assertEquals(ds.getName(), datastream.getName());
        Assert.assertNotEquals(null, datastream.getId());

        Assert.assertEquals(datastream.getField().getTime().getFormat(), ds.getField().getTime().getFormat());
        Assert.assertEquals(datastream.getField().getTime().getIdentifier(), ds.getField().getTime().getIdentifier());
        Assert.assertEquals(datastream.getField().getTime().getZone(), ds.getField().getTime().getZone());

        Assert.assertEquals(datastream.getDatasource().getType(), ds.getDatasource().getType());

        Assert.assertEquals(datastream.getField().getSignal().getDelimiter(), ds.getField().getSignal().getDelimiter());
        Assert.assertEquals(datastream.getField().getSignal().getIsSignalPrefix(), ds.getField().getSignal().getIsSignalPrefix());
        Assert.assertEquals(datastream.getField().getSignal().getTagIdentifier(), ds.getField().getSignal().getTagIdentifier());
        Assert.assertEquals(datastream.getField().getSignal().getValueIdentifier(), ds.getField().getSignal().getValueIdentifier());

    }

    /**
     *
     * @throws Exception
     */
    @After
    public void cleanUp() throws Exception {
        Iterator<Datastream> itr = datastreams.iterator();
        while (itr.hasNext()) {
            Datastream ds = itr.next();
            falkonry.deleteDatastream(ds.getId());
        }
    }
}
