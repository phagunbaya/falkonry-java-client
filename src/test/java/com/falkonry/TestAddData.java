package com.falkonry;

import com.falkonry.client.Falkonry;
import com.falkonry.helper.models.Eventbuffer;
import org.junit.*;

import java.util.*;

/*!
 * falkonry-java-client
 * Copyright(c) 2016 Falkonry Inc
 * MIT Licensed
 */

public class TestAddData {
    Falkonry falkonry = null;
    String host = "https://dev.falkonry.io";
    String token = "6vhoa94dnndb299ulaj4a51hq9ppa88y";
    List<Eventbuffer> eventbuffers = new ArrayList<Eventbuffer>();

    @Before
    public void setUp() throws Exception {
        falkonry = new Falkonry(host, token);
    }

    @Test
    public void addDataJson() throws Exception {
        Eventbuffer eb = new Eventbuffer();
        eb.setName("Test-EB-"+Math.random());
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

        eventbuffer = falkonry.getUpdatedEventbuffer(eventbuffer.getId());
        Assert.assertEquals(1,eventbuffer.getSchemaList().size());
    }

    @Test
    public void addDataCsv() throws Exception {
        Eventbuffer eb = new Eventbuffer();
        eb.setName("Test-EB-"+Math.random());
        eb.setTimeIdentifier("time");
        eb.setTimeFormat("iso_8601");
        eb.setValueColumn("value");
        eb.setSignalsDelimiter("_");
        eb.setSignalsLocation("prefix");
        eb.setSignalsTagField("tag");
        Eventbuffer eventbuffer = falkonry.createEventbuffer(eb);
        eventbuffers.add(eventbuffer);
        String data = "time, tag, value\n" + "2016-03-01 01:01:01, signal1_thing1, 3.4";
        Map<String, String> options = new HashMap<String, String>();
        falkonry.addInput(eventbuffer.getId(), data, options);
        eventbuffer = falkonry.getUpdatedEventbuffer(eventbuffer.getId());
        Assert.assertEquals(1,eventbuffer.getSchemaList().size());
    }

    @After
    public void cleanUp() throws Exception {
        Iterator<Eventbuffer> itr = eventbuffers.iterator();
        while(itr.hasNext()) {
            Eventbuffer eb = itr.next();
            falkonry.deleteEventbuffer(eb.getId());
        }
    }
}
