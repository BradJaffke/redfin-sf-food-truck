package com.redfin.sffoodtruck.util;

import java.time.LocalDateTime;

public class SoQLQueryBuilder {

	/**
	 * Builds a simple SoQL query to use against the Socrata API.  This query only returns four fields (applicant,
	 * location, start time, and end time), and only selects food trucks that are open at some point today.
	 * Results are also sorted alphabetically.
	 * @return query to use against Socrata API.
	 */
	public static String getFoodTrucksOpenTodayQuery() {

		StringBuilder query = new StringBuilder();

		query.append("&$select=applicant,location,start24,end24");
		query.append("&$where=dayorder=" + getDayOfWeek());
		query.append("&$order=applicant");

		return query.toString();
	}

	public static int getDayOfWeek()
	{
		return LocalDateTime.now().getDayOfWeek().getValue();
	}
}
