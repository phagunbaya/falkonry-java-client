package com.falkonry;

import com.falkonry.client.Falkonry;
import com.falkonry.helper.models.Assessment;
import com.falkonry.helper.models.Datastream;

import org.junit.*;

import java.util.*;

public class TestFalkonryException {
	
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
    public void exceptionFetchingDatastream() throws Exception {
    	try {
    		Datastream ds = falkonry.getDatastream("123");
    	} catch (Exception e) {
    		String msg = e.getMessage();
    		Assert.assertEquals(msg, "Not Found");
    	}
    }
    
    /**
    *
    * @throws Exception
    */
   @Test
   public void exceptionFetchingAssessment() throws Exception {
   	try {
   		Assessment asmt = falkonry.getAssessment("123");
   	} catch (Exception e) {
   		String msg = e.getMessage();
   		Assert.assertEquals(msg, "Not Found");
   	}
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
