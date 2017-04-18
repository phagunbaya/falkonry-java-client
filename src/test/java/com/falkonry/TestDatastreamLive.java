package com.falkonry;

import com.falkonry.client.Falkonry;
import com.falkonry.helper.models.Datasource;
import com.falkonry.helper.models.Datastream;
import com.falkonry.helper.models.Assessment;
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

public class TestDatastreamLive {

    Falkonry falkonry = null;
    String host = "https://localhost:8080";
    String token = "8g462njx92e1yc0fxzrbdxqtx90hsr1s";
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
    public void datastreamLiveOn() throws Exception {
    	// Id of datastream which has assessment with active model available
    	String datastreamId = "hk7cgt56r3yln0";
        
        List<Assessment> assessments = falkonry.datastreamLiveOn(datastreamId);
        Assert.assertEquals(true,assessments.size() != 0);
    }
    
    /**
    *
    * @throws Exception
    */
   @Test
   public void datastreamLiveOff() throws Exception {
	   // Id of datastream which has assessment with active model available
	   String datastreamId = "hk7cgt56r3yln0";
       
       List<Assessment> assessments1 = falkonry.datastreamLiveOn(datastreamId);
       Assert.assertEquals(true,assessments1.size() != 0);
       
       List<Assessment> assessments2 = falkonry.datastreamLiveOff(datastreamId);
       Assert.assertEquals(true,assessments2.size() != 0);
       
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
