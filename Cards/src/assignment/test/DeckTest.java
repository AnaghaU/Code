package assignment.test;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import assignment.java.Deck;
import jxl.CellView;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class DeckTest {
	
	
	public Deck deck;
	public int checkDistinctElements;
	String[] testDataArray;
	String[] outputArray;
	File file;
	String filePath = System.getProperty("user.dir")+"\\src\\resources\\CardsTestCases.xls";

	public DeckTest() throws Exception {
		file = new File(filePath);
		FileInputStream fileInputStream = new FileInputStream(file);
		
		//Reading from excel sheet
		Workbook workbook = Workbook.getWorkbook(fileInputStream);
		Sheet sheet = workbook.getSheet(0);
		
		int totalRows = sheet.getRows();
		
		testDataArray = new String[totalRows - 1];
		outputArray = new String[totalRows - 1];
		
		for(int row = 1; row < totalRows; row++) {
			testDataArray[row-1] = sheet.getCell(5,row).getContents();
		}
		deck = new Deck();
		workbook.close();
		
	}
	
	@Test
	public void testEqualCards() throws Exception {
		String numberOfPlayers = testDataArray[0];
		checkDistinctElements = deck.printCards(numberOfPlayers);
		if(checkDistinctElements == 52) { //as the elements in a hashmap will be always unique
			outputArray[0] = "Pass";
		}
		else {
			outputArray[0] = "Fail";
		}
	}
	
	@Test
	public void testUnequalCards() throws Exception {
		String numberOfPlayers = testDataArray[1];
		checkDistinctElements = deck.printCards(numberOfPlayers);
		if(checkDistinctElements == 52-(52%Integer.parseInt(numberOfPlayers)) ){
			outputArray[1] = "Pass";
			outputArray[7] = "Pass";
		}
		else {
			outputArray[1] = "Fail";
			outputArray[7] = "Fail";
		}
	}
	
	@Test
	public void testInvalidNumber() throws Exception {
		String numberOfPlayers = testDataArray[2];
		checkDistinctElements = deck.printCards(numberOfPlayers);
		if(checkDistinctElements == -1) {
			outputArray[2] = "Pass";
		}
		else {
			outputArray[2] = "Fail";
		}
	}
	
	@Test
	public void testNegativeNumber() throws Exception {
		String numberOfPlayers = testDataArray[3];
		checkDistinctElements = deck.printCards(numberOfPlayers);
		if(checkDistinctElements == -1) {
			outputArray[3] = "Pass";
		}else {
			outputArray[3] = "Fail";
		}
	}	
	
	@Test
	public void testString() throws Exception {
		String numberOfPlayers = testDataArray[4];
		checkDistinctElements = deck.printCards(numberOfPlayers);
		if(checkDistinctElements == -2) {
			outputArray[4] = "Pass";
		}
		else {
			outputArray[4] = "Fail";
		}
	}
	
	@Test
	public void testSpecialCharacters() throws Exception {
		String numberOfPlayers = testDataArray[5];
		checkDistinctElements = deck.printCards(numberOfPlayers);
		if(checkDistinctElements == -2) {
			outputArray[5] = "Pass";
		}
		else {
			outputArray[5] = "Fail";
		}
	}
		
	
	@Test
	public void testDecimalNumber() throws Exception {
		String numberOfPlayers = testDataArray[6];
		checkDistinctElements = deck.printCards(numberOfPlayers);
		if(checkDistinctElements == -2) {
			outputArray[6] = "Pass";
		}
		else {
			outputArray[6] = "Fail";
		}
	}
	
	@Test
	public void test52Cards() throws Exception {
		String numberOfPlayers = testDataArray[8];
		checkDistinctElements = deck.printCards(numberOfPlayers);
		System.out.println(checkDistinctElements);
		if(checkDistinctElements == 52) { //as the elements in a hashmap will be always unique
			outputArray[8] = "Pass";
		}
		else {
			outputArray[8] = "Fail";
		}
	}
	
	public void writeResultsToExcel() throws Exception {
		WorkbookSettings workbookSettings;
		WritableWorkbook writableWorkbook;
		
		workbookSettings = new WorkbookSettings();
		workbookSettings.setLocale(new Locale("en", "EN"));
		Workbook workbook = Workbook.getWorkbook(new FileInputStream(file));
		writableWorkbook = Workbook.createWorkbook(file, workbook, workbookSettings);
		WritableSheet writableSheet = writableWorkbook.getSheet(0);
		
		WritableCellFormat cellFormat = new WritableCellFormat();
		cellFormat.setWrap(true);
		
		CellView cv = new CellView();
		cv.setFormat(cellFormat);
		cv.setAutosize(true);

		int totalWritableRows = writableSheet.getRows();
		
		for(int row = 1; row < totalWritableRows ; row++) {
			addLabel(writableSheet, 6, row, outputArray[row-1], cellFormat);
			//System.out.println(outputArray[row-1]);
		}
		
		writableWorkbook.write();
		writableWorkbook.close();
	}

	private static void addLabel(WritableSheet excelSheet, int column, int row, String value, WritableCellFormat cellFormat) throws WriteException {
		Label label = new Label(column, row, value, cellFormat);
		excelSheet.addCell(label);
	}
	
}
