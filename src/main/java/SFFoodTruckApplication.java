import com.redfin.sffoodtruck.model.FoodTruck;
import com.redfin.sffoodtruck.service.SFFoodTruckService;

import java.util.ArrayList;
import java.util.Scanner;

public class SFFoodTruckApplication {

	/**
	 * Main method class to handle outputting information to screen and user input
	 * @param args
	 */
	public static void main(String[] args) {
		ArrayList<FoodTruck> openFoodTrucks = new ArrayList<>();
		SFFoodTruckService sfFoodTruckService = new SFFoodTruckService();
		try {
			openFoodTrucks = sfFoodTruckService.getOpenFoodTrucks();
		}
		catch (Exception ex)
		{
			System.out.println("ERROR " + ex.getMessage());
		}

		//If there are no open food trucks then display message stating this.  Otherwise print out food trucks!
		if (openFoodTrucks == null || openFoodTrucks.size() == 0)
		{
			System.out.println("There are no open food trucks right now!");
		}
		else
		{
			System.out.println("FOOD TRUCKS CURRENTLY OPEN IN SF");
			System.out.println("---------------------------------");
			System.out.println("NAME\tADDRESS");

			for (int i = 0; i < openFoodTrucks.size(); i++)
			{
				String userInput = "";

				//If we have more than 10 results then paginate through them on user input
				if (i % 10 == 0 && i != 0)
				{
					System.out.println("---------------------------------");
					System.out.println("Showing 10 Food Trucks...");
					System.out.println("Press 0 (plus the enter key) to exit");
					System.out.println("Press any other alphanumeric key (plus the enter key) to see the next page");

					Scanner scanner = new Scanner(System.in);
					userInput = scanner.next();

					if (userInput.equals("0"))
						break;
				}

				System.out.println(openFoodTrucks.get(i).getTruckName()
						+ "\t"  + openFoodTrucks.get(i).getTruckAddress());
			}
		}
	}
}
