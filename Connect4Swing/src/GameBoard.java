import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.util.ArrayList;

public class GameBoard extends JPanel{

	private int boardWidth; //number of tiles
	private int boardHeight;
	private Tiles[][] board; 
	private int width; //actual pixel count
	private int height; //actual pixel count
	private int turn;
	private int winner;
	private JLabel status; // Current status text, i.e. "Running..."
	private ArrayList<Integer> list = new ArrayList<>();

	public GameBoard(int w, int h, int bW, int bH, JLabel status) {
		boardWidth = bW;
		boardHeight = bH;
		width = w;
		height = h;
		this.setSize(width, height);
		board = new Tiles[boardWidth][boardHeight];
		int xSize = width / boardWidth; //how many pixels across one tile?
		int ySize = height / boardHeight;
		turn = 1;
		winner = 0;
		this.status = status;

		for (int i = 0; i < boardWidth ; i++) {
			for (int j = 0; j < boardHeight ; j++) {
				board[i][j] = new Tiles(i * xSize, j * ySize, (int)(xSize * 0.75));
			}
		}

		//mouse listener
		addMouseListener(new MouseAdapter() { 
			public void mousePressed(MouseEvent e) {
				int clickX = e.getX() / (width / boardWidth);
				int clickY = e.getY() / (height / boardHeight);
				//	board[clickX][clickY].turnRed();
				//	System.out.println(e);
				takeTurn(clickX);
				repaint();
			}
		});
	}
    //Player 1's turn is signified by 1, Player 2's turn is signified by 2
	public void takeTurn(int column) {

		if (!isFull() && winner == 0) {
			int emptySpot = columnFull(column);
			if (emptySpot < 0) { //if there aren't open spots
				return;
			}
			list.add(column);

			if (turn == 1) {
				board[column][emptySpot].turnRed();
			}
			else {
				board[column][emptySpot].turnYellow();
			}
			winner = checkWinner();

			repaint();

			if (turn == 1) {
				turn = 2;
			}
			else {
				turn = 1;
			}
		}
	}

	public void undoTurn() {
		if (list.size() == 0) {
			return;
		}

		int column = list.get(list.size() - 1);
		list.remove(list.size() - 1);

		int row = columnFull(column); //lowest white spot
		row++;

		board[column][row].turnWhite();

		if (turn == 1) {
			turn = 2;
		}
		else {
			turn = 1;
		}

		winner = 0;
		repaint();

		if (status != null) {
			status.setText("");
		}
	}

	public int checkWinner() {
		int row = checkRow();
		int column = checkCol();
		int major = checkMajorDiagonal();
		int minor = checkMinorDiagonal();
		if (row != 0) {
			return row;
		}
		else if (column != 0) {
			return column;
		}
		else if (major != 0) {
			return major;
		}
		else if (minor != 0) {
			return minor;
		}

		if (isFull()) {
			return -1;
		}
		return 0;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.blue);
		g.fillRect(0, 0, width, height);
		for (int i = 0; i < boardWidth ; i++) {
			for (int j = 0; j < boardHeight ; j++) {
				board[i][j].draw(g);
			}
		}
		if (winner != 0 && status != null) {
			if (winner == 1) {
				status.setText("RED HAS WON!");
			}
			else if (winner == 2) {
				status.setText("YELLOW HAS WON!");
			}
			else if (winner == -1) {
				status.setText("IT'S A TIE!");
			}
		}
	}

	public void reset() {
		for (int i = 0; i < boardHeight; i++) {
			for (int j = 0; j < boardWidth; j++) {
				board[j][i].turnWhite();
			}
		}
		winner = 0;
		turn = 1;
		repaint();
		list.clear();
		if (status != null) {
			status.setText("");
		}
	}

	public boolean isFull() {
		for (int i = 0; i < boardHeight; i++) {
			for (int j = 0; j < boardWidth; j++) {
				if (board[j][i].getTile() == 0)  {
					return false;
				}
			}
		}
		return true;
	}

	public boolean isEmpty() {
		for (int i = 0; i < boardHeight; i++) {
			for (int j = 0; j < boardWidth; j++) {
				if (board[j][i].getTile() != 0) {
					return false;
				}
			}
		}
		return true;  
	}

	public int columnFull(int column) {

		int row = 5;

		while(row >= 0 && board[column][row].getTile() != 0) {
			row--;
		}
		return row;
	}

	public int checkCol() {
		for (int r = 0; r < boardWidth; r++ ) {
			for (int c = 0; c <  boardHeight - 3; c++) {
				if (board[r][c].getTile() != 0 && board[r][c].getTile() == 
						board[r][c+1].getTile() && board[r][c + 1].getTile() == 
						board[r][c+2].getTile() && board[r][c + 2].getTile() ==
						board[r][c+3].getTile()) {
					return board[r][c].getTile();
				}
			}
		}
		return 0; //no one has won
	}

	public int checkRow() {
		for (int r = 0; r < boardWidth - 3; r++ ) {
			for (int c = 0; c < boardHeight; c++) {
				if (board[r][c].getTile() != 0 && board[r][c].getTile() == 
						board[r+1][c].getTile() && board[r][c].getTile() == 
						board[r+2][c].getTile() && board[r][c].getTile() == 
						board[r+3][c].getTile()) {
					return board[r][c].getTile();
				}
			}
		}
		return 0; //no one has won
	}


	public int checkMajorDiagonal() {
		for (int r = 0; r < boardWidth - 3; r++ ) {
			for (int c = 0; c < boardHeight - 3; c++) {
				if (board[r][c].getTile() != 0 && board[r][c].getTile() == 
						board[r+1][c+1].getTile() && board[r][c].getTile() == 
						board[r+2][c+2].getTile() && board[r][c].getTile() == 
						board[r+3][c+3].getTile()) {
					return board[r][c].getTile();
				}
			}
		}
		return 0; //no winner
	}

	public int checkMinorDiagonal() {

		for (int r = boardWidth - 1; r >= 3; r--) {
			for (int c = 0; c < boardHeight - 3; c++ ) {
				if (board[r][c].getTile() != 0 && board[r][c].getTile() == 
						board[r-1][c+1].getTile() && board[r][c].getTile() == 
						board[r - 2][c +2].getTile() && board [r][c].getTile() ==
						board[r-3][c+3].getTile()) {
					return board[r][c].getTile();
				}
			}
		}
		return 0;
	}

	public void save(String fileName) {
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(fileName);
			if (list.size() != 0) {
				for(Integer i : list) {
					out.write(i + 66);
				}
			}
			if (out != null) {
				out.close();
			}
		}
		catch (Exception e){
			System.out.println(e);
		}
	}

	public void load(String fileName) {
		FileInputStream in = null;
		try {
			in = new FileInputStream(fileName);
			reset();
			int i;
			while ((i = in.read()) != -1) {
				takeTurn(i - 66);
			}
			if (in != null) {
				in.close();
			}
		}
		catch (Exception e){
			System.out.println(e);
		}
	}
}
