package assignment.test;

public class TestRunner {

	public static void main(String[] args) throws Exception {
		DeckTest deckTest = new DeckTest();
		deckTest.testEqualCards();
		deckTest.testDecimalNumber();
		deckTest.testInvalidNumber();
		deckTest.testNegativeNumber();
		deckTest.testSpecialCharacters();
		deckTest.testUnequalCards();
		deckTest.testString();
		deckTest.test52Cards();
		deckTest.writeResultsToExcel();
	}
}
