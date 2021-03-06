package com.http.test;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Com.http.client.RestClient;
import Com.showOffTask.utils.TestUtil;
import ShowOffTask.TestBase;
import junit.framework.Assert;

public class GetAPITest extends TestBase {
	
	public GetAPITest() throws Exception {
		super();
		// TODO Auto-generated constructor stub
	}


	TestBase testBase;
	String apiurl;
	String serviceUrl;
	String uri;
	RestClient restClient;
	 CloseableHttpResponse  closeableHttpResponse;
@BeforeMethod
	public void setUp() throws Exception {
		testBase=new TestBase();
		apiurl=prop.getProperty("URL");
		serviceUrl= prop.getProperty("showServiceURL");
		
		 uri =apiurl+serviceUrl;
		}
	
	
	@Test(enabled=false) 
   public void getAPITest()throws ClientProtocolException, IOException {
	    restClient= new RestClient();
	    closeableHttpResponse = restClient.getAPIMethod(uri);
	    
	    int statusCode= closeableHttpResponse.getStatusLine().getStatusCode();
		  System.out.println("StatusCode.....>"+statusCode);
		//  Assert.assertEquals("status code is not 200",RESPONSE_STATUS_CODE_200, statusCode);
		
		
		//below code gives Json Response converted to String and stores in the variable.
		  String responseString=EntityUtils.toString(closeableHttpResponse.getEntity(),"UTF-8");//UTF-8 standard HttpEntity entity,String defaultCharset
		
		//string converted to json by using bellow class
		  JSONObject responsejson = new JSONObject(responseString);
		  
		  System.out.println("Response JSON from API.....>"+responsejson);
		  
		  String perResponseMessage= TestUtil.getValueByJpath(responsejson,"message");
		  System.out.println("value of per message"+perResponseMessage);
		  Assert.assertEquals(perResponseMessage,"Success");
		  
		 Header[] headerArray=closeableHttpResponse.getAllHeaders();
		  HashMap<String, String> allHeaders= new HashMap<String,String>();
		  
		 for(Header header:headerArray) {
			allHeaders.put(header.getName(),header.getValue());}
		  
		    System.out.println("Headers Array.....>"+allHeaders);
	     
		     }
	   // String s = res.get("code").toString();
	    
	   // assertTrue(s.equals("10"));
	     	 	
	//System.out.println("code "+s);
	@Test	
	   public void getRequestAPIWithHeaders()throws ClientProtocolException, IOException {
		    restClient= new RestClient();
		    
		    HashMap<String,String> headerMap = new HashMap<String,String>();
		    
		   // headerMap.put("Content-Type","application/json");
		    headerMap.put("Authorization","Bearer f6e012c1b6cda653f51c48215bcdf2a2bdd23cf63eebebb3397274cb4bd759cb");
		    
		    //response
		    closeableHttpResponse = restClient.getRequestAPIWithHeaders(uri, headerMap);//sending URI to restclient and taking response to closeableHttpResponse
		    
			 int statusCode= closeableHttpResponse.getStatusLine().getStatusCode();
			  System.out.println("StatusCode.....>"+statusCode);
			  Assert.assertEquals("status code is not 200",RESPONSE_STATUS_CODE_200, statusCode);
			
			
			//below code gives Json Response converted to String and stores in the variable.
			  String responseString=EntityUtils.toString(closeableHttpResponse.getEntity(),"UTF-8");//UTF-8 standard HttpEntity entity,String defaultCharset
			
			//string converted to json by using bellow class
			  JSONObject responsejson = new JSONObject(responseString);
			  
			  System.out.println("Response JSON from API.....>"+responsejson);
			  
			  String perResponseMessage= TestUtil.getValueByJpath(responsejson,"message");
			  System.out.println("value of per message"+perResponseMessage);
			  Assert.assertEquals(perResponseMessage,"Success");
			  
			 Header[] headerArray=closeableHttpResponse.getAllHeaders();
			  HashMap<String, String> allHeaders= new HashMap<String,String>();
			  
			 for(Header header:headerArray) {
				allHeaders.put(header.getName(),header.getValue());}
			  
			    System.out.println("Headers Array.....>"+allHeaders);
		     
			     }
}

