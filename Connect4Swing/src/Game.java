// Set up the GUI to get everything ready 
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;

public class Game {

	public static JFrame frame; 


	public static void main(String[] args) {

		frame = new JFrame("Connect Four Game");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(700, 650);
		
		JOptionPane.showMessageDialog(null, "Welcome to Connect4! Player 1 is Red "
				+ "and Player 2 is Yellow."
				+ "\nThe goal of the game is for a player to get 4 tiles in a row, 4 "
				+ "tiles in a column, or 4 tiles diagonally." 
		        + "\nRed will always go first. The game is played with each player"
		        + " taking turns clicking on the board to select the column for their tile."
		        + "\nClick OK to proceed");
		
		JFileChooser chooser = new JFileChooser();
		

		// Status panel
		final JPanel status_panel = new JPanel();
		final JLabel status = new JLabel("");
		
		JMenuBar menu = new JMenuBar();
		JMenu file = new JMenu("File");
		JMenuItem load = new JMenuItem("Load");
		JMenuItem save = new JMenuItem("Save");
		JMenuItem undo = new JMenuItem("Undo");
		JMenuItem reset = new JMenuItem("Reset");
		
		GameBoard game = new GameBoard(700, 600, 7, 6, status);
		
		reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.reset();
			}
		});
		
		undo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.undoTurn();
			}
		});
		
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = chooser.showSaveDialog(null);
				if (i == JFileChooser.APPROVE_OPTION) {
					File f = chooser.getSelectedFile();
				   game.save(f.getAbsolutePath());
				}
			}
		});
		
		load.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = chooser.showOpenDialog(null);
				if (i == JFileChooser.APPROVE_OPTION) {
					File f = chooser.getSelectedFile();
				   game.load(f.getAbsolutePath());
				}
			}
		});
		
		
		file.add(load);
		file.add(save);
		menu.add(file);
		menu.add(undo);
		menu.add(reset);
		
		frame.setJMenuBar(menu);
		status_panel.add(status);
		frame.add(status_panel, BorderLayout.SOUTH);
		frame.add(game, BorderLayout.CENTER);
		frame.setVisible(true);
	}
}
