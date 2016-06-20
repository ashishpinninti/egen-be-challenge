package com.ashishpinninti;

import static org.junit.Assert.assertEquals;
import groovyx.net.http.HTTPBuilder;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NoHttpResponseException;
import org.apache.http.conn.HttpHostConnectException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MicroserviceDemoApplication.class)
@WebAppConfiguration
public class MicroserviceDemoApplicationTests {
	
	@Test
	public void endTOEndTest() throws Exception{
		
		// Assuming 160 as base_weight
		//Create a metric with value 300; This should create alert with over weight;
		Object res = post("http://localhost:8080/metric/create",300);
		Gson gson = new Gson();
	    String json = gson.toJson(res);
	    Metric originalMetric = gson.fromJson(json, Metric.class);
	    
		Object res1 = get("http://localhost:8080/alert/read");
		Type type = new TypeToken<List<Alert>>() {
		}.getType();
		String json1 = gson.toJson(res1);
		List<Alert> alerts = gson.fromJson(json1,type);
		Alert alert = alerts.get(alerts.size()-1);
		Metric relatedMetric = alert.getMetric();
		
		// Check if the created Metric and Metric referred in alert are equal
		assertEquals(originalMetric, relatedMetric);
		
	}
	
	private Object post(String url, int value) throws Exception {
		HTTPBuilder http = new HTTPBuilder(url);
		
		Map<String, Object> map = new HashMap<>();
		String json = "{\"timeStamp\": \"" + String.valueOf(System.currentTimeMillis()) + "\", \"value\": \"" + value + "\"}";
		map.put("body", json);
		
		Map<String, String> headers = new HashMap<>();
		headers.put("content-type", "application/json");
		map.put("headers", headers);
		
		try {
			return http.post(map);
		} catch(HttpHostConnectException | NoHttpResponseException e) {
			System.out.println("API [" + url + "] not reachable. Error - " + e.getMessage());
		}
		return null;
	}
	
	private Object get(String url) throws Exception {
		HTTPBuilder http = new HTTPBuilder(url);
		
		Map<String, Object> map = new HashMap<>();
		Map<String, String> headers = new HashMap<>();
		headers.put("accept", "application/json");
		map.put("headers", headers);
		
		try {
			return http.get(map);
		} catch(Exception e) {
			System.out.println("API [" + url + "] not reachable. Error - " + e.getMessage());
		}
		return null;
	}

}
