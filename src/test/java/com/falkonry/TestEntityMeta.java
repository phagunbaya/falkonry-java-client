package com.falkonry;

import com.falkonry.client.Falkonry;
import com.falkonry.helper.models.Datasource;
import com.falkonry.helper.models.Datastream;
import com.falkonry.helper.models.EntityMeta;
import com.falkonry.helper.models.EntityMetaRequest;
import com.falkonry.helper.models.Field;
import com.falkonry.helper.models.Signal;
import com.falkonry.helper.models.TimeObject;

import org.junit.*;

import java.util.*;

public class TestEntityMeta {
	
	Falkonry falkonry = null;
	String host = "https://localhost:8080";
    String token = "8g462njx92e1yc0fxzrbdxqtx90hsr1s";
    List<Datastream> datastreams = new ArrayList<Datastream>();
	List<EntityMetaRequest> entityMetaRequests = new ArrayList<EntityMetaRequest>();

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
   public void createEntityMeta() throws Exception {

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

       Field field = new Field();
       field.setSiganl(signal);
       field.setTime(time);
       ds.setField(field);
       Datasource dataSource = new Datasource();
       dataSource.setType("STANDALONE");
       ds.setDatasource(dataSource);

       Datastream datastream = falkonry.createDatastream(ds);
       datastreams.add(datastream);
       String data = "time, tag, value\n";
       for(int i = 0; i < 10; i++) {
    	   data += "2016-03-01 01:01:0"+i+", entity1_signal1, 3.4\n";   
       }
       Map<String, String> options = new HashMap<String, String>();
       options.put("timeIdentifier", "time");
       options.put("timeFormat", "iso_8601");
       options.put("fileFormat", "csv");
       options.put("streaming", "false");
       options.put("hasMoreData", "false");
       falkonry.addInput(datastream.getId(), data, options);

       datastream = falkonry.getDatastream(datastream.getId());
       EntityMetaRequest entityMetaRequest = new EntityMetaRequest();
       entityMetaRequest.setSourceId("entity1");
       entityMetaRequest.setLabel("UNIT1");
       entityMetaRequest.setPath("entity1/UNIT1");
       entityMetaRequests.add(entityMetaRequest);
       List<EntityMeta> entityMetas = falkonry.postEntityMeta(entityMetaRequests, datastream.getId());
       Assert.assertEquals(true, entityMetas.size() == 1);
       
       List<EntityMeta> entityMetas1 = falkonry.getEntityMeta(datastream.getId());
       Assert.assertEquals(true, entityMetas1.size() == 1);
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
