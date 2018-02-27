package assignment.java;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Deck {

	private static String[][] cards = new String[4][13];

	public int printCards(String players) throws Exception {
	
		initializeCards();
		Map<String, String> mapOfCards = new HashMap<>();
		try {
			int numberOfPlayers = Integer.parseInt(players);

			if (numberOfPlayers < 1 || numberOfPlayers > 52) {
				System.out.println("The number of players should be between 1 and 52");
				return -1;
			} else {
				int numberOfCards = 52 / numberOfPlayers;

				Random rand = new Random();
				int k = 0, l = 0;
				for (int i = 0; i < 4; i++) {
					for (int j = 0; j < 13; j++) {
						while (l < numberOfPlayers) {
							System.out.print("user-" + (l + 1) + " ");
							while (k < numberOfCards) {
								int r = i + rand.nextInt(4);
								int s = j + rand.nextInt(13);
								String key = r + "" + s;
								if (mapOfCards.get(key) == null) {
									mapOfCards.put(key, cards[r][s]);
									System.out.print(cards[r][s] + " ");
									k++;
								}
							}
							System.out.println();
							k = 0;
							l++;
						}
					}
				}
				System.out.println("Remaining number of cards = "+52%numberOfPlayers);
			}
		} catch (Exception e) {
			System.out.println("Invalid entry!! The number of players should be a number between 1 and 52");
			return -2;
		}
		return mapOfCards.size();
	}
	
	
	private void initializeCards() {
		StringBuilder cardName = new StringBuilder("");

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 13; j++) {
				switch (i) {
				case 0:
					cardName = new StringBuilder("spades:");
					break;
				case 1:
					cardName = new StringBuilder("clubs:");
					break;
				case 2:
					cardName = new StringBuilder("diamonds:");
					break;
				case 3:
					cardName = new StringBuilder("hearts:");
					break;
				}

				switch (j) {
				case 9:
					cardName.append("Jack");
					break;
				case 10:
					cardName.append("Queen");
					break;
				case 11:
					cardName.append("King");
					break;
				case 12:
					cardName.append("A");
					break;
				default:
					cardName.append(String.valueOf(j + 2));
					break;
				}
				cards[i][j] = cardName.toString();
			}
		}
	}


}
