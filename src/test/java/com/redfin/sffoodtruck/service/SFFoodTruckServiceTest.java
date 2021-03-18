package com.redfin.sffoodtruck.service;

import org.json.JSONObject;
import org.junit.Test;
import org.junit.Assert;


public class SFFoodTruckServiceTest {

	@Test
	public void testDetermineIfFoodTruckIsOpenRightNow() {
		JSONObject foodTruckJsonObject = new JSONObject("{'start24':'09:00','end24':'18:00'}");

		SFFoodTruckService service = new SFFoodTruckService();

		boolean open = service.determineIfFoodTruckIsOpenRightNow(foodTruckJsonObject);

		Assert.assertEquals(open, true);
	}

}
