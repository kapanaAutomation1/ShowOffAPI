package Com.http.client;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import Com.showOffTask.utils.TestUtil;

import java.util.Map;

import javax.imageio.IIOException;

import junit.framework.Assert;
public class RestClient {

	/*public RestClient() {
		// TODO Auto-generated constructor stub
	}*/
	
	//GET method
	public CloseableHttpResponse getAPIMethod(String uri) throws ClientProtocolException, IOException {
		
	   CloseableHttpClient httpClient= HttpClients.createDefault();// this create Default return 
	   
	   HttpGet httpGet = new HttpGet(uri);//http get request
	   
	   
	  //CloseableHttpResponse  
       CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpGet);//hit the GET URL

	     return closeableHttpResponse;
	
     }
	
	public CloseableHttpResponse getRequestAPIWithHeaders(String uri,HashMap<String,String> headerMap) throws ClientProtocolException,IOException{

		CloseableHttpClient httpClient=HttpClients.createDefault();

		  HttpGet httpGet = new HttpGet(uri);

		for(Map.Entry<String,String> entry:headerMap.entrySet()){
		
		httpGet.addHeader(entry.getKey(),entry.getValue());
		}
		CloseableHttpResponse  closeableHttpResponse = httpClient.execute(httpGet);//hit the GetURL
		return closeableHttpResponse;
		}

	
	// POST METHOD
	public CloseableHttpResponse createPOSTAPI(String uri,String entityString,HashMap<String,String> headerMap) throws ClientProtocolException,IOException{

		CloseableHttpClient httpClient=HttpClients.createDefault();

		  HttpPost httpPost = new HttpPost(uri);//HTTP POST REQUEST
		httpPost.setEntity(new StringEntity(entityString));//for payLoad

		//for headers:
		for(Map.Entry<String,String>entry:headerMap.entrySet()){
		httpPost.addHeader(entry.getKey(),entry.getValue());
		}
		CloseableHttpResponse  closeableHttpResponse = httpClient.execute(httpPost);//hit the POST URL
		return closeableHttpResponse;
		}
	
	
	
	//***PUT Method****
	public CloseableHttpResponse createPutAPI(String uri,String entityString,HashMap<String,String> headerMap) throws ClientProtocolException,IOException{

		CloseableHttpClient httpClient=HttpClients.createDefault();

		  HttpPut httpPut = new HttpPut(uri);//HTTP PuT REQUEST
		httpPut.setEntity(new StringEntity(entityString));//for payLoad

		//for headers:
		for(Map.Entry<String,String>entry:headerMap.entrySet()){
			
		httpPut.addHeader(entry.getKey(),entry.getValue());
		}
		CloseableHttpResponse  closeableHttpResponse = httpClient.execute(httpPut);//hit the PUT URL
		return closeableHttpResponse;
		}
	
	//Delete Method
	
	public CloseableHttpResponse deleteResourceAPI(String uri,HashMap<String,String> headerMap) throws ClientProtocolException,IOException{

		CloseableHttpClient httpClient=HttpClients.createDefault();
		  HttpDelete httpDelete = new HttpDelete(uri);

		//for headers:
		for(Map.Entry<String,String>entry:headerMap.entrySet()){
			
		httpDelete.addHeader(entry.getKey(),entry.getValue());
		}
		CloseableHttpResponse  closeableHttpResponse = httpClient.execute(httpDelete);//hit the delete URL
		return closeableHttpResponse;
		} 
	}