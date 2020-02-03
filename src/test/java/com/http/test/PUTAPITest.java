package com.http.test;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import Com.http.client.RestClient;
import Com.showOffTask.utils.TestUtil;
import ShowOffTask.TestBase;
import junit.framework.Assert;

public class PUTAPITest extends TestBase {
	TestBase testBase;
	String apiurl;
	String serviceUrl;
	String uri;
	RestClient restClient;
	CloseableHttpResponse  closeableHttpResponse;

public PUTAPITest() throws Exception {
	super();
	// TODO Auto-generated constructor stub
}

@BeforeMethod
public void setUp() throws Exception {
	testBase=new TestBase();
	apiurl=prop.getProperty("URL");
	serviceUrl= prop.getProperty("putUpdateURI");
	
	 uri =apiurl+serviceUrl;
	}

@Test
 public void putAPIMethodTest()throws ClientProtocolException, IOException, ParseException {
      restClient= new RestClient();
    
    HashMap<String,String> headerMap = new HashMap<String,String>();
    
 
    headerMap.put("Authorization","Bearer f6e012c1b6cda653f51c48215bcdf2a2bdd23cf63eebebb3397274cb4bd759cb");
    headerMap.put("Content-Type","application/json");
    
    //Jackson API
    ObjectMapper mapper=new ObjectMapper();
    
    JSONParser parser = new JSONParser();

    Object obj = parser.parse(new FileReader("src/main/resources/Payloads/UpdateWidgetPayload.json"));
    	
  //java object to json in string 
    String usersJsonString=mapper.writeValueAsString(obj);
    System.out.println("g usersJsonString"+usersJsonString);

    	    closeableHttpResponse= restClient.createPutAPI(uri, usersJsonString, headerMap);//call the API
    	    
    
      //1.status code:
    	    int statusCode= closeableHttpResponse.getStatusLine().getStatusCode();
			  System.out.println("StatusCode.....>"+statusCode);
			 Assert.assertEquals("status code is not 200",RESPONSE_STATUS_CODE_200, statusCode);
			  //2.responseString:
			  
	 String responsePutString=EntityUtils.toString(closeableHttpResponse.getEntity(),"UTF-8");
			
	  //3.converted response sting to put string
	 JSONObject responsePutjson = new JSONObject(responsePutString);
	 String perResponseMessage= TestUtil.getValueByJpath(responsePutjson,"message");
	 
	 JSONObject data=  responsePutjson.getJSONObject("data");
	 JSONObject widget= data.getJSONObject("widget");
	 
	
	 String owner= TestUtil.getValueByJpath(widget,"owner");
	 
	 System.out.println("owner"+owner);
	 Assert.assertEquals(perResponseMessage,"Success");
	 Assert.assertNotNull(owner);
	 
	 System.out.println("Response JSON from API.....>"+responsePutjson);
 }

}
