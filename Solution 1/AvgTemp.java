package DEWJavaTest;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class AvgTemp {
	
	public static void main(String args[]) throws FileNotFoundException
	{
		String url = "http://www.bom.gov.au/fwo/IDS60901/IDS60901.94672.json";
		JSONParser parser = new JSONParser();
		ArrayList tempList = new ArrayList();
		double totalTemperature = 0;
		
		try
		{
			//Object obj = parser.parse(new FileReader("DEW.json.txt"));
			
			/* Reading data from remote JSON file */
			InputStream is = new URL(url).openStream();
		    BufferedReader rd = new BufferedReader(new InputStreamReader(is));
			
			String inputLine;
			StringBuilder sb = new StringBuilder();
	        while ((inputLine = rd.readLine()) != null)
	        {
	            sb.append(inputLine);
	        }

	        /* Parsing JSON data */
	        Object obj = parser.parse(sb.toString());
			
			JSONObject jsonObject = (JSONObject)obj;
			JSONObject observations = (JSONObject)jsonObject.get("observations");
			JSONArray dataArray = (JSONArray)observations.get("data");
			Iterator dataIterator = dataArray.iterator();
			while(dataIterator.hasNext())
			{
				Object data = dataIterator.next();
				JSONObject dataJsonObject = (JSONObject) data;
				Double temperature = (Double) dataJsonObject.get("air_temp");
				tempList.add(temperature); 
			}
			/* Calculating average temperature */
			for(int i=0;i<tempList.size();i++)
			{
				totalTemperature = totalTemperature + (Double)tempList.get(i);
				
			}
			
			double averageTemperature = totalTemperature/tempList.size();
			System.out.println("averageTemperature : "+averageTemperature);
			
		}
		catch(FileNotFoundException fe)
		{
			fe.printStackTrace();
		}
		catch(IOException ie)
		{
			ie.printStackTrace();
		}
		catch(ParseException pe)
		{
			pe.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
