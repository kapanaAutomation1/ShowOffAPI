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
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.databind.ObjectMapper;

import Com.http.client.RestClient;
import Com.showOffTask.utils.TestUtil;
import ShowOffTask.TestBase;
import junit.framework.Assert;

public class POSTAPITest extends TestBase {
	TestBase testBase;
	String apiUrl;
	String serviceUrl;
	String uri;
	RestClient restClient;
	CloseableHttpResponse  closeableHttpResponse;

	public POSTAPITest() throws Exception {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@BeforeMethod
	public void setUp() throws Exception {
		testBase=new TestBase();
		apiUrl=prop.getProperty("URL");
		serviceUrl= prop.getProperty("createUserURL");
		
		 uri =apiUrl+serviceUrl;
		}
	
	@Test
	 public void postAPIMethodTest()throws ClientProtocolException, IOException, ParseException {
	      restClient= new RestClient();
	    
	    HashMap<String,String> headerMap = new HashMap<String,String>();
	    
	    headerMap.put("Content-Type","application/json");
	    
	    //Jackson API
	    ObjectMapper mapper=new ObjectMapper();
	    
	    JSONParser parser = new JSONParser();

	    Object obj = parser.parse(new FileReader("src/main/resources/Payloads/CreateUserPayload.json"));
	    	
	     //java object to json in string 
	    String usersJsonString=mapper.writeValueAsString(obj);
	    System.out.println("g usersJsonString"+usersJsonString);

	    	    closeableHttpResponse= restClient.createPOSTAPI(uri, usersJsonString, headerMap);//call the API
	    
	      //1.status code:
	     int statusCode= closeableHttpResponse.getStatusLine().getStatusCode();
		 System.out.println("StatusCode.....>"+statusCode);
		 Assert.assertEquals("status code is not 200",RESPONSE_STATUS_CODE_200, statusCode);
		  
		  //2.responseString:
		 String responsePostString=EntityUtils.toString(closeableHttpResponse.getEntity(),"UTF-8");
		  
		  //3.converted response sting to post string
		 JSONObject responsePostjson = new JSONObject(responsePostString);
		 String perResponseMessage= TestUtil.getValueByJpath(responsePostjson,"message");
		 
		 JSONObject data= responsePostjson.getJSONObject("data");
		 JSONObject token= data.getJSONObject("token");
		
		 String access_token= TestUtil.getValueByJpath(token,"access_token");
		 
		 System.out.println("access_token  "+access_token);
		 Assert.assertEquals(perResponseMessage,"Success");
		 Assert.assertNotNull(access_token);
		 
		 System.out.println("Response JSON from API.....>"+responsePostjson);
	 }

}
