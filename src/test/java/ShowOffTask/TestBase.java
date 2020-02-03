package ShowOffTask;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.testng.annotations.BeforeClass;



public class TestBase {
 
	public Properties prop;
	
	public int RESPONSE_STATUS_CODE_200=200;
	public int RESPONSE_STATUS_CODE_500=500;
	public int RESPONSE_STATUS_CODE_400=400;
	public int RESPONSE_STATUS_CODE_401=401;
	public int RESPONSE_STATUS_CODE_201=201;
	 
	public TestBase()throws Exception{
	
	{
		try{
			  prop=new Properties();
			  String projectPath= System.getProperty("user.dir");//user.dir is the constant for everyone
	   
		 //("user.dir")+"//src//config//config.properties");
	      FileInputStream fip=new FileInputStream(projectPath+"/src/main/resources/Config.Properties");
			
		      prop.load(fip);
	      } catch(FileNotFoundException e){
	      System.out.println("FileNotFoundException caught in intialconfig:"+e.getMessage());
	      }
	      catch(IOException e){
	      e.printStackTrace();}}
		

	}

}
