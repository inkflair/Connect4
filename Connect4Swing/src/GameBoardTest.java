import static org.junit.Assert.*;

import org.junit.Test;

public class GameBoardTest {

	@Test
	public void RedWinnerTest() {
		GameBoard game = new GameBoard(700, 600, 7, 6, null);
		game.takeTurn(5);
		game.takeTurn(4);
		game.takeTurn(5);
		game.takeTurn(4);
		game.takeTurn(5);
		game.takeTurn(4);
		game.takeTurn(5);
		int winner = game.checkCol();
		assertEquals(winner, 1);
	}
	
	@Test
	public void NoWinnerTest() {
		GameBoard game = new GameBoard(700, 600, 7, 6, null);
		game.takeTurn(5);
		int winner = game.checkCol();
		assertEquals(winner, 0);
	}
	
	@Test
	public void YellowWinnerTest() {
		GameBoard game = new GameBoard(700, 600, 7, 6, null);
		game.takeTurn(5);
		game.takeTurn(4);
		game.takeTurn(5);
		game.takeTurn(4);
		game.takeTurn(5);
		game.takeTurn(4);
		game.takeTurn(2);
		game.takeTurn(4);
		int winner = game.checkCol();
		assertEquals(winner, 2);
	}

	@Test
	public void ResetBoardTest() {
		GameBoard game = new GameBoard(700, 600, 7, 6, null);
		game.takeTurn(5);
		game.takeTurn(4);
		game.takeTurn(5);
		game.takeTurn(4);
		game.takeTurn(5);
		game.takeTurn(4);
		game.takeTurn(2);
		game.takeTurn(4);
		game.reset();
		assertTrue(game.isEmpty());
	}
	
	@Test
	public void FullBoardTest() {
		GameBoard game = new GameBoard(700, 600, 7, 6, null);
		game.takeTurn(0);
		game.takeTurn(0);
		game.takeTurn(0);
		game.takeTurn(0);
		game.takeTurn(0);
		game.takeTurn(0);
		game.takeTurn(6);
		game.takeTurn(6);
		game.takeTurn(6);
		game.takeTurn(6);
		game.takeTurn(6);
		game.takeTurn(6);
		game.takeTurn(5);
		game.takeTurn(5);
		game.takeTurn(5);
		game.takeTurn(4);
		game.takeTurn(4);
		game.takeTurn(4);
		game.takeTurn(5);
		game.takeTurn(5);
		game.takeTurn(5);
		game.takeTurn(4);
		game.takeTurn(4);
		game.takeTurn(4);
		game.takeTurn(3);
		game.takeTurn(3);
		game.takeTurn(3);
		game.takeTurn(1);
		game.takeTurn(1);
		game.takeTurn(1);
		game.takeTurn(2);
		game.takeTurn(2);
		game.takeTurn(2);
		game.takeTurn(2);
		game.takeTurn(2);
		game.takeTurn(2);
		game.takeTurn(1);
		game.takeTurn(1);
		game.takeTurn(1);
		game.takeTurn(3);
		game.takeTurn(3);
		game.takeTurn(3);
		assertTrue(game.isFull());
	}
	
	@Test
	public void FullColumnTest() {
		GameBoard game = new GameBoard(700, 600, 7, 6, null);
		game.takeTurn(5);
		game.takeTurn(5);
		game.takeTurn(5);
		game.takeTurn(5);
		game.takeTurn(5);
		game.takeTurn(5);
		assertEquals(game.columnFull(5), -1);
	}
	
	@Test
	public void CheckRowTest() {
		GameBoard game = new GameBoard(700, 600, 7, 6, null);
		game.takeTurn(0);
		game.takeTurn(0);
		game.takeTurn(1);
		game.takeTurn(1);
		game.takeTurn(2);
		game.takeTurn(2);
		game.takeTurn(3);
		game.takeTurn(3);
		assertEquals(game.checkRow(), 1); //red has won
	}
	
	@Test
	public void CheckColTest() {
		GameBoard game = new GameBoard(700, 600, 7, 6, null);
		game.takeTurn(0);
		game.takeTurn(1);
		game.takeTurn(6);
		game.takeTurn(1);
		game.takeTurn(0);
		game.takeTurn(1);
		game.takeTurn(0);
		game.takeTurn(1);
		assertEquals(game.checkCol(), 2); //yellow has won
	}
	
	@Test
	public void CheckMinorDiagTest() {
		GameBoard game = new GameBoard(700, 600, 7, 6, null);
		game.takeTurn(0); //red
		game.takeTurn(1); //yellow
		game.takeTurn(1); //red
		game.takeTurn(6); //yellow
		game.takeTurn(1); //red
		game.takeTurn(2); //yellow
		game.takeTurn(2); //red
		game.takeTurn(3); //yellow
		game.takeTurn(2); //red
		game.takeTurn(3); //yellow
		game.takeTurn(2); //red
		game.takeTurn(3); //yellow
		game.takeTurn(3); //red
		assertEquals(game.checkMinorDiagonal(), 1); //red has won
	}
	
	@Test
	public void CheckMajorDiagTest() {
		GameBoard game = new GameBoard(700, 600, 7, 6, null);
		game.takeTurn(0); //red
		game.takeTurn(0); //yellow
		game.takeTurn(0); //red
		game.takeTurn(0); //yellow
		game.takeTurn(1); //red
		game.takeTurn(1); //yellow
		game.takeTurn(2); //red
		game.takeTurn(2); //yellow
		game.takeTurn(6); //red
		game.takeTurn(3); //yellow
		game.takeTurn(3); //red
		game.takeTurn(1); //yellow
		game.takeTurn(3); //red
		assertEquals(game.checkMajorDiagonal(), 2); //yellow has won
	}
}
