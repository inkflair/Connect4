import java.awt.Color;
import java.awt.Graphics;

public class Tiles {

	private int xcenter;
	private int ycenter;
	private Color c;

	private int halfRadius;

	public Tiles(int x, int y, int r) {
		xcenter = (int) (x + (r * 0.1));
		ycenter = (int) (y + (r * 0.1));
		c = Color.white;
		halfRadius = r;
	}

	public int getX() {
		return xcenter;
	}

	public int getY() {
		return ycenter;
	}

	public void draw(Graphics g) {
		g.setColor(c);
		g.fillOval(getX(), getY(), halfRadius, halfRadius);
	}

	public void turnRed() {
		c = Color.red;
	}

	public void turnYellow() {
		c = Color.yellow;
	}

	public void turnWhite() {
		c = Color.white;
	}

	public Color getColor() {
		return c;
	}

	public int getTile() {
		if (c == Color.red) {
			return 1;
		}
		else if (c == Color.yellow) {
			return 2;
		}
		return 0;
	}
}

