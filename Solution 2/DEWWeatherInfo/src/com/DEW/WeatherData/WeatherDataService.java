package com.DEW.WeatherData;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

@WebService
public class WeatherDataService {

@WebMethod
	public ArrayList<WeatherDataBean> getWeatherDataService(int station)
	{
		String JSONURL = "http://www.bom.gov.au/fwo/IDS60901/IDS60901." + station + ".json";
		
		JSONParser parser = new JSONParser();
		ArrayList<WeatherDataBean> beanAL = new ArrayList<WeatherDataBean>();
		
		try
		{
			/* Reading data from remote JSON file */
			InputStream is = new URL(JSONURL).openStream();
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
				WeatherDataBean wd = new WeatherDataBean();
				wd = populateDataBean(dataJsonObject, wd);
				beanAL.add(wd); 
			}
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
			e.getCause().getCause();
			e.printStackTrace();
		}
		return beanAL;
	}

	public WeatherDataBean populateDataBean(JSONObject dataJsonObject, WeatherDataBean wd)
	{
		if(dataJsonObject.get("sort_order") != null)
			wd.setSort_order(Integer.valueOf(dataJsonObject.get("sort_order").toString()));
		if(dataJsonObject.get("wmo") != null)
			wd.setWmo(Integer.valueOf(dataJsonObject.get("wmo").toString()));
		if(dataJsonObject.get("name") != null)
			wd.setName(dataJsonObject.get("name").toString());
		if(dataJsonObject.get("history_product") != null)
			wd.setHistory_product(dataJsonObject.get("history_product").toString());
		if(dataJsonObject.get("local_date_time") != null)
			wd.setLocal_date_time(dataJsonObject.get("local_date_time").toString());
		if(dataJsonObject.get("local_date_time_full") != null)
			wd.setLocal_date_time_full(dataJsonObject.get("local_date_time_full").toString());
		if(dataJsonObject.get("aifstime_utc") != null)
			wd.setAifstime_utc(dataJsonObject.get("aifstime_utc").toString());
		if(dataJsonObject.get("lat") != null)
			wd.setLat((Double) dataJsonObject.get("lat"));
		if(dataJsonObject.get("lon") != null)
			wd.setLon((Double) dataJsonObject.get("lon"));
		if(dataJsonObject.get("apparent_t") != null)
			wd.setApparent_t((Double) dataJsonObject.get("apparent_t"));
		if(dataJsonObject.get("cloud") != null)
			wd.setCloud(dataJsonObject.get("cloud").toString());
		if(dataJsonObject.get("cloud_base_m") != null)
			wd.setCloudBase_m(dataJsonObject.get("cloud_base_m").toString());
		if(dataJsonObject.get("cloud_oktas") != null)
			wd.setCloud_oktas(Integer.valueOf(dataJsonObject.get("cloud_oktas").toString()));
		if(dataJsonObject.get("cloud_type_id") != null)
			wd.setCloud_type_id(Integer.valueOf(dataJsonObject.get("cloud_type_id").toString()));
		if(dataJsonObject.get("cloud_type") != null)
			wd.setCloud_type(dataJsonObject.get("cloud_type").toString());
		if(dataJsonObject.get("delta_t") != null)
			wd.setDelta_t((Double) dataJsonObject.get("delta_t"));
		if(dataJsonObject.get("gust_kmh") != null)
			wd.setGust_kmh((Integer.valueOf(dataJsonObject.get("gust_kmh").toString())));
		if(dataJsonObject.get("gust_kt") != null)
			wd.setGust_kt(Integer.valueOf(dataJsonObject.get("gust_kt").toString()));
		if(dataJsonObject.get("air_temp") != null)
			wd.setAir_temp((Double)dataJsonObject.get("air_temp"));
		if(dataJsonObject.get("dewpt") != null)
			wd.setDewpt((Double) dataJsonObject.get("dewpt"));
		if(dataJsonObject.get("press") != null)
			wd.setPress((Double)dataJsonObject.get("press"));
		if(dataJsonObject.get("press_qnh") != null)
			wd.setPress_qnh((Double) dataJsonObject.get("press_qnh"));
		if(dataJsonObject.get("press_msl") != null)
			wd.setPress_msl((Double) dataJsonObject.get("press_msl"));
		if(dataJsonObject.get("press_tend") != null)
			wd.setPress_tend(dataJsonObject.get("press_tend").toString());
		if(dataJsonObject.get("rain_trace") != null)
			wd.setRain_trace(dataJsonObject.get("rain_trace").toString());
		if(dataJsonObject.get("rel_hum") != null)
			wd.setRel_hum(Integer.valueOf(dataJsonObject.get("rel_hum").toString()));
		if(dataJsonObject.get("sea_state") != null)
			wd.setSea_state(dataJsonObject.get("sea_state").toString());
		if(dataJsonObject.get("swell_dir_worded") != null)
			wd.setSwell_dir_worded(dataJsonObject.get("swell_dir_worded").toString());
		if(dataJsonObject.get("swell_height") != null)
			wd.setSwell_height(dataJsonObject.get("swell_height").toString());
		if(dataJsonObject.get("swell_period") != null)
			wd.setSwell_period(dataJsonObject.get("swell_period").toString());
		if(dataJsonObject.get("vis_km") != null)
			wd.setVis_km(dataJsonObject.get("vis_km").toString());
		if(dataJsonObject.get("weather") != null)
			wd.setWeather(dataJsonObject.get("weather").toString());
		if(dataJsonObject.get("wind_dir") != null)
			wd.setWind_dir(dataJsonObject.get("wind_dir").toString());
		if(dataJsonObject.get("wind_spd_kmh") != null)
			wd.setWind_spd_kmh(Integer.valueOf(dataJsonObject.get("wind_spd_kmh").toString()));
		if(dataJsonObject.get("wind_spd_kt") != null)
			wd.setWind_spd_kt(Integer.valueOf(dataJsonObject.get("wind_spd_kt").toString()));return wd; 
	}
}
