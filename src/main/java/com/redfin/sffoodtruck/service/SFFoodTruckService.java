package com.redfin.sffoodtruck.service;

import com.redfin.sffoodtruck.model.FoodTruck;
import com.redfin.sffoodtruck.util.SoQLQueryBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;

import java.time.LocalTime;
import java.util.ArrayList;

public class SFFoodTruckService {

	private static final String APP_TOKEN = "KwbxXfkQ1jqnNc0elmfONlkCU";

	/**
	 * Extract food trucks out of their Json format and construct a list of FoodTruck DTO's
	 * @return List of all currently open food trucks
	 * @throws IOException
	 */
	public ArrayList<FoodTruck> getOpenFoodTrucks() throws IOException {

		//Storage for food trucks that are open
		ArrayList<FoodTruck> foodTruckList = new ArrayList<FoodTruck>();

		JSONArray foodTrucksOpenTodayJsonArray = new JSONArray(getSortedFoodTrucksOpenTodayFromSocrataAPI());

		for (int i = 0; i < foodTrucksOpenTodayJsonArray.length(); i++)
		{
			JSONObject foodTruckJsonObject = (JSONObject) foodTrucksOpenTodayJsonArray.get(i);

			if (determineIfFoodTruckIsOpenRightNow(foodTruckJsonObject))
			{
				FoodTruck foodTruck = new FoodTruck();
				foodTruck.setTruckName(foodTruckJsonObject.getString("applicant"));
				foodTruck.setTruckAddress(foodTruckJsonObject.getString("location"));
				foodTruckList.add(foodTruck);
			}
		}
		return foodTruckList;
	}

	/**
	 * Call external Socrata API to get food trucks that are open at some point today.  Add app token
	 * and only pull back fields that we need.
	 * @return Json String with all food trucks that are open at some point today.
	 * @throws IOException
	 */
	public String getSortedFoodTrucksOpenTodayFromSocrataAPI() throws IOException {
		StringBuilder result = new StringBuilder();

		try {
			URL url = new URL("http://data.sfgov.org/resource/bbb8-hzi6.json" + "?$$app_token="
					+ APP_TOKEN + SoQLQueryBuilder.getFoodTrucksOpenTodayQuery());
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;

			while ((line = rd.readLine()) != null) {
				result.append(line);
			}
			rd.close();
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return result.toString();
	}

	/**
	 * Determine whether or not a food truck is open right now.  I believe it's possible to do this filtering within
	 * the SoQL query itself, but I ran out of time while trying to figure out the necessary URL encoding.  This code
	 * could be avoided if this logic was added to the query
	 * @param foodTruckJsonObject JsonObject representation of a food truck
	 * @return whether or not food truck is currently open right now
	 */
	public boolean determineIfFoodTruckIsOpenRightNow(JSONObject foodTruckJsonObject)
	{
		LocalTime startTime;
		LocalTime closeTime;

		//LocalTime does not support 24:00 as it's viewed as "the next day".  Again here there
		//is probably a more graceful way of doing this, but I was running short on allotted time.
		if (foodTruckJsonObject.getString("start24").equals("24:00"))
			startTime = LocalTime.parse("23:59");
		else
			startTime = LocalTime.parse(foodTruckJsonObject.getString("start24"));

		if (foodTruckJsonObject.getString("end24").equals("24:00"))
			closeTime = LocalTime.parse("23:59");
		else
			closeTime = LocalTime.parse(foodTruckJsonObject.getString("end24"));

		if (LocalTime.now().compareTo(startTime) >= 0 &&
				LocalTime.now().compareTo(closeTime) < 0)
			return true;
		else
			return false;
	}
}
