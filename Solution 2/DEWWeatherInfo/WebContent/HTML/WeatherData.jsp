<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "com.DEW.WeatherData.WeatherDataService" %>
<%@ page import = "com.DEW.WeatherData.WeatherDataBean" %>
<%@ page import = "java.util.*" %>

    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>DEW Weather Information Page</title>
</head>
<body>
<%! private int stationID; 
Object obj;
ArrayList<WeatherDataBean> beanAL = new ArrayList<WeatherDataBean>();
WeatherDataBean beanDetails = new WeatherDataBean();
%>

<% 
String station_id = request.getParameter("station_id");

if(station_id != null)
	stationID = Integer.parseInt(station_id.trim());

WeatherDataService service =  new WeatherDataService();
// obj = service.getWeatherDataService(temp);
beanAL = (ArrayList<WeatherDataBean>)service.getWeatherDataService(stationID);
%>
<table border = 1 cellspacing="6" cellpadding="2">
<tr><th>sort_order</th><th>wmo</th><th>name</th><th>history_product</th><th>local_date_time</th><th>local_date_time_full</th><th>aifstime_utc</th><th>lat</th><th>lon</th><th>apparent_t</th><th>cloud</th><th>cloud_base_m</th><th>cloud_oktas</th><th>cloud_type_id</th><th>cloud_type</th><th>delta_t</th><th>gust_kmh</th><th>gust_kt</th><th>air_temp</th><th>dewpt</th><th>press</th><th>press_qnh</th><th>press_msl</th><th>press_tend</th><th>rain_trace</th><th>rel_hum</th><th>sea_state</th><th>swell_dir_worded</th><th>swell_height</th><th>swell_period</th><th>vis_km</th><th>weather</th><th>wind_dir</th><th>wind_spd_kmh</th><th>wind_spd_kt</th></tr>
<%
// Iterating through beanList
if(beanAL.size() > 0)
{
Iterator<WeatherDataBean> iterator = beanAL.iterator();  // Iterator interface
while(iterator.hasNext())  // iterate through all the data until the last record
{
	beanDetails = iterator.next(); //assign individual details to the bean class object
%>
<tr>
<td><%=beanDetails.getSort_order()%></td>
<td><%=beanDetails.getWmo()%></td>
<td><%=beanDetails.getName()%></td>
<td><%=beanDetails.getHistory_product()%></td>
<td><%=beanDetails.getLocal_date_time()%></td>
<td><%=beanDetails.getLocal_date_time_full()%></td>
<td><%=beanDetails.getAifstime_utc()%></td>
<td><%=beanDetails.getLat()%></td>
<td><%=beanDetails.getLon()%></td>
<td><%=beanDetails.getApparent_t()%></td>
<td><%=beanDetails.getCloud()%></td>
<td><%=beanDetails.getCloudBase_m()%></td>
<td><%=beanDetails.getCloud_oktas()%></td>
<td><%=beanDetails.getCloud_type_id()%></td>
<td><%=beanDetails.getCloud_type()%></td>
<td><%=beanDetails.getDelta_t()%></td>
<td><%=beanDetails.getGust_kmh()%></td>
<td><%=beanDetails.getGust_kt()%></td>
<td><%=beanDetails.getAir_temp()%></td>
<td><%=beanDetails.getDewpt()%></td>
<td><%=beanDetails.getPress()%></td>
<td><%=beanDetails.getPress_qnh()%></td>
<td><%=beanDetails.getPress_msl()%></td>
<td><%=beanDetails.getPress_tend()%></td>
<td><%=beanDetails.getRain_trace()%></td>
<td><%=beanDetails.getRel_hum()%></td>
<td><%=beanDetails.getSea_state()%></td>
<td><%=beanDetails.getSwell_dir_worded()%></td>
<td><%=beanDetails.getSwell_height()%></td>
<td><%=beanDetails.getSwell_period()%></td>
<td><%=beanDetails.getVis_km()%></td>
<td><%=beanDetails.getWeather()%></td>
<td><%=beanDetails.getWind_dir()%></td>
<td><%=beanDetails.getWind_spd_kmh()%></td>
<td><%=beanDetails.getWind_spd_kt()%></td>
</tr>
<%
}
}
%>
</table>
</body>
</html>