package tictactoe;

import static org.junit.Assert.assertNotEquals;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

//import java.lang.ArrayIndexOutOfBoundsException;

class TicTacToeJUnitTest {
	
	private static TicTacToeMain obj;// = new TicTacToeMain();

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		obj = new TicTacToeMain(); 
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		obj.playButton.doClick();
	}

	@AfterEach
	void tearDown() throws Exception {
		obj.isPlay = false;
		obj.setStatus("Click 'Play' To Start");
	}

	@Test
	void setStatusTest1() {
		obj.setStatus("TestStatus");
		assertEquals("TestStatus", obj.statusLabel.getText());
	}
	
	@Test
	void setButtonsEnabledTest1() {
		obj.setButtonsEnabled(true);
		assertEquals(true, obj.buttons[0][0].isEnabled());
	}
	
	@Test
	void playTest1() {
		obj.isPlay = false;
		obj.setStatus("Click 'Play' To Start");
		assertEquals(false, obj.isPlay);
		obj.playButton.doClick();
		assertEquals(true, obj.isPlay);
	}
	
	@Test
	void yourTurnTest() {
		//obj.actionPerformed(new ActionEvent(, 10, ""))
		//obj.playButton.doClick();
		obj.click(0, 0);
		obj.click(1, 0);
		assertEquals("Your Turn", obj.statusLabel.getText());
	}
	
	@Test
	void earlyLoseTest() {
		obj.click(0, 0);
		obj.click(1, 0);
		assertNotEquals("Sorry, You Lose!", obj.statusLabel.getText());
	}
	
	@Test
	void drawTest() {
		obj.click(0, 0);
		obj.click(1, 0);
		assertNotEquals("Draw, Click 'Play' For Rematch!", obj.statusLabel.getText());
	}

	@Test
	void wrongNegativeIndexesTest() {
		Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
			obj.click(-1, -1);
			obj.click(-2, -2);
			obj.click(0, -1);
			obj.click(-1, 0);
		});
	}
	
	@Test
	void wrongPositiveIndexesTest() {
		Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
			obj.click(20, 20);
			obj.click(30, 30);
			obj.click(0, 40);
			obj.click(40, 0);
		});
	}
	
	@Test
	void multipleClicksTest1() {
		obj.click(0, 0);
		obj.click(0, 1);
		obj.click(0, 2);
		obj.click(1, 0);
		obj.click(1, 1);
		obj.click(1, 2);
		obj.click(0, 1);
		obj.click(2, 0);
		obj.click(2, 1);
		obj.click(2, 2);
		assertNotEquals("Your Turn", obj.statusLabel.getText());
	}
	
	@Test
	void multipleButtonsTest1() {
		obj.buttons[0][0].doClick();
		obj.buttons[0][1].doClick();
		obj.buttons[0][2].doClick();
		obj.buttons[1][0].doClick();
		obj.buttons[1][1].doClick();
		obj.buttons[1][2].doClick();
		obj.buttons[2][0].doClick();
		obj.buttons[2][1].doClick();
		obj.buttons[2][2].doClick();
		assertNotEquals("Your Turn", obj.statusLabel.getText());
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "test1.csv", numLinesToSkip = 0)
	void samePositionAfterRestart(int input, String expected) {
		obj.click(input, input);
		obj.playButton.doClick();
		assertEquals(TicTacToeAI.EMPTY ,obj.game.getBoardValue(input,input));
		obj.click(input, input);
		assertEquals(expected, obj.statusLabel.getText());
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "test2.csv", numLinesToSkip = 0)
	void gameWonTest(int arg1, int arg2, String arg3, String expected) {
		obj.click(arg1, arg1);
		obj.click(arg2, arg2);
		obj.gameOver(arg3);
		assertNotEquals(expected, obj.statusLabel.getText());
	}
	
	
}



















