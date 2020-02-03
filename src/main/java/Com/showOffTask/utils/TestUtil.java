package Com.showOffTask.utils;

import org.json.JSONArray;
import org.json.JSONObject;

public class TestUtil {
	//public static JSONObject responseJson;

	public TestUtil() {
		// TODO Auto-generated constructor stub
	}
		public static String getValueByJpath(JSONObject responseJson,String jpath){
			 Object obj =responseJson;
			for(String sJson:jpath.split("/"))
			if (!sJson.isEmpty())
			if (!(sJson.contains("[")||sJson.contains("[")))
			obj=((JSONObject) obj).get(sJson);
			else if((sJson.contains("[")|| sJson.contains("[")))
			obj=((JSONArray)((JSONObject)obj).get(sJson.split("\\[")[0])).get(Integer.parseInt(sJson.split("\\[")[1].replace("]","")));
			return obj.toString();

	}

}
