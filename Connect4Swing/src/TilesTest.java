import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Test;

public class TilesTest {

	@Test
	public void RedTileTest() {
		Tiles tile  = new Tiles(1, 1, 1);
	    tile.turnRed();
		assertEquals(tile.getColor(), Color.red);
	}
	
	@Test
	public void YellowTileTest() {
		Tiles tile  = new Tiles(1, 1, 1);
	    tile.turnYellow();
		assertEquals(tile.getColor(), Color.yellow);
	}
	
	@Test
	public void WhiteTileTest() {
		Tiles tile  = new Tiles(1, 1, 1);
	    tile.turnYellow();
	    tile.turnRed();
	    tile.turnWhite();
		assertEquals(tile.getColor(), Color.white);
	}
	
	@Test
	public void GetTileTest1() {
		Tiles tile  = new Tiles(1, 1, 1);
	    tile.turnYellow();
	    tile.turnRed();
		assertEquals(tile.getTile(), 1);
	}
	
	@Test
	public void GetTileTest2() {
		Tiles tile  = new Tiles(1, 1, 1);
	    tile.turnYellow();
	    tile.turnRed();
	    tile.turnYellow();
		assertEquals(tile.getTile(), 2);
	}


}
