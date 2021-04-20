
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager; 

public class connect5mainmenu extends JFrame {
	public connect5mainmenu() {
		//Remove window border
		this.setUndecorated(true);
		this.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
		//set size
		this.setSize(400, 300);
		//set layout
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);	//set close operation
		this.setVisible(true);								//set visibility

		/*
		 * Centering the current window
		 */
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
		this.setLocation(x, y);


		// Constructing title bar
		// Declare and initialize title label
		JLabel titleLabel = new JLabel("Connect FIVE!");
		// Set font
		titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 55));

		//Removes the window border
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setVerticalAlignment(SwingConstants.CENTER);

		this.add(titleLabel, BorderLayout.NORTH);

		JPanel controlPanel = new JPanel();
		//set layout of control panel
		controlPanel.setLayout(new GridLayout(5, 1));

		//The buttons on the main panel

		JButton singlePlayer = new JButton("Single Player");
		JButton about = new JButton("About");
		JButton exit = new JButton("Exit");

		//set font for the buttons
		singlePlayer.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		about.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		exit.setFont(new Font("Times New Roman", Font.PLAIN, 20));

		// ActionListeners

		//Single Player, where a player is playing the AI.
		
		singlePlayer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {  
				connect5.main(null); //run main method of connect 4 single player
			}

		});

		// About button

		about.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
			
				String rule="                                                           Rule\n\n"
						+ "The player in his/her turn drops one of his/her pieces "
						+ "down any of the slots in the top of the grid. \n\n"
						+ "The play alternates until the player or the computer "
						+ "gets five checkers of his/her colour in a row. \n\n"
						+ "The five in a row can be horizontal, vertical,"
						+ "or diagonal. \n\n"
						+ "The first player to get five in a row wins. Either it's a draw!";
				JOptionPane.showMessageDialog(null, rule);

			}

		});	
		//Exit button
		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}

		});

		// add buttons to the control Panel
		controlPanel.add(singlePlayer);
		controlPanel.add(about);
		controlPanel.add(exit);

		// set layout for main menu
		this.add(controlPanel, BorderLayout.CENTER);

		this.repaint();
		this.revalidate();

	}
		// main method
	public static void main(String[] args) {
		new connect5mainmenu();

	}
}