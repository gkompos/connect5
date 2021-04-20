import java.awt.event.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class connect5 extends JFrame implements ActionListener {
	JFrame f;			//The Frame
	JPanel grid;		//The panel
	JButton cells[][];// cells of the grid(buttons)
	JButton drop[];	  // Buttons to select where to drop
	JButton computer; // Button to start the Computer First
	JButton reset;
	JButton pause;
	JButton pchangeColor; //button for chose players colour
	JButton read;
	JLabel label; // select who starts
	JLabel playerl; // record moves
	JLabel coml; // record moves
	String playert = "Player's Turn: ";
	String comt = "Computer's Turn: ";
	
	int count[] = new int[] { 6, 6, 6, 6, 6, 6, 6, 6 }; // 6 represents rows(because we start from 0) and 8 sixes because we have 8 columns
	int check[][] = new int[8][7]; 						//it prints in the console and use it to read in the file.
	int counterpl = 0; //count of turns
	int countercom = 0;// counts of turns
	Color playerCol = Color.red; // Default Color Player
	Color comCol = Color.yellow; // Default Color Computer
	JPanel colors; // the panel for chose the colour
	JButton red,yellow,blue,green,black,cyan,gray,pink;
	
	
	GridBagConstraints s = new GridBagConstraints();// help with the coordinates

	public connect5() {   // constructor

		grid = new JPanel();// creating the frame
		add(grid); // add the grid to the frame
		grid.setLayout(new GridBagLayout());
		s.insets = new Insets(1,1,1,1); // space in between each button
		s.fill = GridBagConstraints.HORIZONTAL; // define the grids

		label = new JLabel("Select Who's First");
		s.gridx = 9;
		s.gridy = 0;
		label.setPreferredSize(new Dimension(150, 40));
		grid.add(label, s);

		computer = new JButton("Computer First");
		s.gridx = 9;
		s.gridy = 1;
		computer.setPreferredSize(new Dimension(150, 40));
		grid.add(computer, s);
		computer.addActionListener(this);

		playerl = new JLabel(playert + counterpl);
		s.gridx = 9;
		s.gridy = 2;
		playerl.setPreferredSize(new Dimension(150, 40));
		grid.add(playerl, s);

		coml = new JLabel(comt + countercom);
		s.gridx = 9;
		s.gridy = 3;
		coml.setPreferredSize(new Dimension(150, 40));
		grid.add(coml, s);

		pchangeColor = new JButton("Choose Color");
		s.gridx = 9;
		s.gridy = 4;
		pchangeColor.setPreferredSize(new Dimension(150, 40));
		grid.add(pchangeColor, s);
		pchangeColor.addActionListener(this);


		pause = new JButton("Pause & Save");
		s.gridx = 9;
		s.gridy = 5;
		pause.setPreferredSize(new Dimension(150, 40));
		grid.add(pause, s);
		pause.addActionListener(this);

		read = new JButton("Load Saved Game");
		s.gridx = 9;
		s.gridy = 6;
		read.setPreferredSize(new Dimension(150, 40));
		grid.add(read, s);
		read.addActionListener(this);

		reset = new JButton("Reset");
		s.gridx = 9;
		s.gridy = 7;
		reset.setPreferredSize(new Dimension(150, 40));
		grid.add(reset, s);
		reset.addActionListener(this);

		cells = new JButton[8][7];
		drop = new JButton[8];
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 7; y++) {
				cells[x][y] = new JButton("");
				cells[x][y].setPreferredSize(new Dimension(80, 80));
				cells[x][y].setAction(null);
				cells[x][y].setBackground(Color.white);
				cells[x][y].setBorder(BorderFactory.createLineBorder(Color.blue, 3));
				s.gridx = x;
				s.gridy = y+1;
				grid.add(cells[x][y], s);//add the buttons to the board
			}
		}
		for (int x = 0; x < 8; x++) {
			drop[x] = new JButton("DROP");
			drop[x].setFont(new Font("Arial Bold", Font.PLAIN, 20));
			drop[x].setForeground(Color.white);
			drop[x].setPreferredSize(new Dimension(90, 80));
			drop[x].setBackground(Color.black);
			drop[x].setBorder(BorderFactory.createLineBorder(Color.black, 3));
			drop[x].addActionListener(this);
			s.gridx = x;
			s.gridy = 0;
			grid.add(drop[x], s);//add the the buttons to the grid
		}
		f= new JFrame();
		colors = new JPanel();
		
		red=new JButton("RED");
		red.setBackground(Color.red);
		red.setBorder(BorderFactory.createLineBorder(Color.red, 3));
		red.setForeground(Color.white);
		colors.add(red);
		red.addActionListener(this);

		yellow=new JButton("YELLOW");
		yellow.setBackground(Color.yellow);
		yellow.setBorder(BorderFactory.createLineBorder(Color.yellow, 3));
		yellow.setForeground(Color.white);
		colors.add(yellow);
		yellow.addActionListener(this);

		blue=new JButton("BLUE");
		blue.setBackground(Color.blue);
		blue.setBorder(BorderFactory.createLineBorder(Color.blue, 3));
		blue.setForeground(Color.white);
		colors.add(blue);
		blue.addActionListener(this);

		green=new JButton("GREEN");
		green.setBackground(Color.green);
		green.setBorder(BorderFactory.createLineBorder(Color.green, 3));
		green.setForeground(Color.white);
		colors.add(green);
		green.addActionListener(this);

		black=new JButton("BLACK");
		black.setBackground(Color.black);
		black.setBorder(BorderFactory.createLineBorder(Color.black, 3));
		black.setForeground(Color.white);
		colors.add(black);
		black.addActionListener(this);

		cyan=new JButton("CYAN");
		cyan.setBackground(Color.cyan);
		cyan.setBorder(BorderFactory.createLineBorder(Color.cyan, 3));
		cyan.setForeground(Color.white);
		colors.add(cyan);
		cyan.addActionListener(this);

		gray=new JButton("GRAY");
		gray.setBackground(Color.gray);
		gray.setBorder(BorderFactory.createLineBorder(Color.gray, 3));
		gray.setForeground(Color.white);
		colors.add(gray);
		gray.addActionListener(this);

		pink=new JButton("PINK");
		pink.setBackground(Color.pink);
		pink.setBorder(BorderFactory.createLineBorder(Color.pink, 3));
		pink.setForeground(Color.white);
		colors.add(pink);
		pink.addActionListener(this);

		f.add(colors);
		f.setSize(200, 200);
		f.setLocation(400, 150);
		f.setVisible(false);

	}



	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == drop[0]) {	//Check which buttons is pressed

				cells[0][count[0]].setBackground(playerCol);	//Change color background for button at the bottom
				check[0][count[0]] = 1; 	//popAlert in the console 1 for player move and 2 for computer move
				count[0]--; 				// popAlert which block you are in for each column
				counterpl++; 				// count the players moves
				label.setText(comt);
				playerl.setText(playert + counterpl);	//Show turns for Player 		
				aimove();
				checkCondition(); 			//Method to check condition for what message to give
		}
		if (e.getSource() == drop[1]) {

				cells[1][count[1]].setBackground(playerCol);
				check[1][count[1]] = 1;
				count[1]--;
				counterpl++;
				label.setText(comt);
				playerl.setText(playert + counterpl);
				aimove();
				checkCondition();
		}
		if (e.getSource() == drop[2]) {

				cells[2][count[2]].setBackground(playerCol);
				check[2][count[2]] = 1;
				count[2]--;
				counterpl++;
				label.setText(comt);
				playerl.setText(playert + counterpl);
				aimove();
				checkCondition();
		}
		if (e.getSource() == drop[3]) {

				cells[3][count[3]].setBackground(playerCol);
				check[3][count[3]] = 1;
				count[3]--;
				counterpl++;
				label.setText(comt);
				playerl.setText(playert + counterpl);
				aimove();
				checkCondition();
		}
		if (e.getSource() == drop[4]) {

				cells[4][count[4]].setBackground(playerCol);
				check[4][count[4]] = 1;
				count[4]--;
				counterpl++;
				label.setText(comt);
				playerl.setText(playert + counterpl);
				aimove();
				checkCondition();
		}
		if (e.getSource() == drop[5]) {

				cells[5][count[5]].setBackground(playerCol);
				check[5][count[5]] = 1;
				count[5]--;
				counterpl++;
				label.setText(comt);
				playerl.setText(playert + counterpl);
				aimove();
				checkCondition();
		}
		if (e.getSource() == drop[6]) {

				cells[6][count[6]].setBackground(playerCol);
				check[6][count[6]] = 1;
				count[6]--;
				counterpl++;
				label.setText(comt);
				playerl.setText(playert + counterpl);
				aimove();
				checkCondition();
		}
		if (e.getSource() == drop[7]) {
			
				cells[7][count[7]].setBackground(playerCol);
				check[7][count[7]] = 1;
				count[7]--;
				counterpl++;
				label.setText(comt);
				playerl.setText(playert + counterpl);
				aimove();
				checkCondition();
		}
		if (e.getSource() == computer) {
			label.setText("Computer Starts First!");
			aimove();
		}
		if (e.getSource() == reset) {	
			reset();					//Calls the method to reset everything
		}
		if (e.getSource() == pchangeColor) { //Make window visible for when user clicks button.

			f.setVisible(true);
		}
		
		if (e.getSource() == pause) {
			label.setText(save());	//Message will appear in Label
		}
		if (e.getSource() == read) {
			label.setText(reload()); //Message will appear in Label
		}
		//Color buttons to change color. When player changes color, automatically change for the computer and make window disappear.
		if(e.getSource()==red) {
			playerCol = Color.red;
			comCol=Color.yellow;
			f.setVisible(false);
		}
		if(e.getSource()==blue) {
			playerCol = Color.blue;
			comCol=Color.green;
			f.setVisible(false);
		}
		if(e.getSource()==black) {
			playerCol = Color.black;
			comCol=Color.pink;
			f.setVisible(false);
		}
		if(e.getSource()==cyan) {
			playerCol = Color.cyan;
			comCol=Color.gray;
			f.setVisible(false);
		}
		if(e.getSource()==yellow) {
			playerCol = Color.yellow;
			comCol=Color.red;
			f.setVisible(false);
		}
		if(e.getSource()==green) {
			playerCol = Color.green;
			comCol=Color.blue;
			f.setVisible(false);
		}
		if(e.getSource()==pink) {
			playerCol = Color.pink;
			comCol=Color.black;
			f.setVisible(false);
		}
		if(e.getSource()==gray) {
			playerCol = Color.gray;
			comCol=Color.cyan;
			f.setVisible(false);
		}

	}
	public void checkCondition() {
		if (draw() == true) {
			popAlert(0);
		}
		if (win() == 1) {
			popAlert(1);
		}
		if (win() == 2) {
			popAlert(2);
		}
	}
	
	//Alert for the each case (draw, win player, win computer)
	public void popAlert(int x) {
		JFrame alert = new JFrame();
		if(x==0) {
			JOptionPane.showMessageDialog(alert, "It's a DRAW!");
			reset();
		}
		if(x==1) {
			JOptionPane.showMessageDialog(alert, "Player Wins!");
			reset();
		}
		if(x==2) {
			JOptionPane.showMessageDialog(alert, "Computer Wins!");
			reset();
		}
	}
	//Reset Method (sets color back to white and restored all values back to default)
	public void reset() {
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 7; y++) {
				cells[x][y].setBackground(Color.white);
			}
		}
		count = null;
		count = new int[] { 6, 6, 6, 6, 6, 6, 6, 6 };
		check = null;
		check = new int[8][7];
		counterpl = 0;
		countercom = 0;
	}
	//Save Method (Stores 2D Array in a file along with the counters)
	public String save() {
		try {
			FileWriter writehandle = new FileWriter("C:\\Users\\NoHumExi\\Desktop\\connect5\\game.txt");
			BufferedWriter bw = new BufferedWriter(writehandle);
			for (int y = 0; y <= 6; y++) {
				for (int x = 0; x <= 7; x++) {
					bw.write(check[x][y]);			//Write the number which are stored in the array in the file
					System.out.print(check[x][y]);	//Print them in console
				}
				bw.newLine();
				System.out.println();
			}
			bw.write(counterpl);	//Write the counter for the player
			bw.newLine();
			bw.write(countercom);	//Write the counter for the player
			bw.newLine();
			System.out.println("saved");

			bw.close();
			writehandle.close();
			return "GAME SAVED";	// Return this String to The label to inform user that it's saved
		} catch (IOException ioe) {
			System.out.println("error.");
			return "ERROR";			// Return this String to The label to inform user that an error occurred
		}

	}
	//Reload Saved Game Method (Reads the 2D Array and the counters and based on that restores the background color of the buttons)
	public String reload() {
		try {
			FileReader readhandle = new FileReader("C:\\Users\\NoHumExi\\Desktop\\connect5\\game.txt");
			BufferedReader br = new BufferedReader(readhandle);

			for (int y = 0; y <= 6; y++) {
				for (int x = 0; x <= 7; x++) {
					check[x][y] = br.read();
					if (check[x][y] == 1) {						// If it is 1 means it was the player
						cells[x][y].setBackground(playerCol);	//Set Color for Player
						count[x]--;								// Remove zeroes that are used
					}
					if (check[x][y] == 2) {						// If it is 2 means it was the computer
						cells[x][y].setBackground(comCol);		//Set Color for Computer
						count[x]--;								// Remove zeroes that are used
					}
				}
				br.readLine();
			}
			counterpl = br.read();	//Read the counter for the Player
			br.readLine();
			countercom = br.read();	//Read the counter for the Computer

			br.close();
			readhandle.close();
			return "GAME RELOADED";
		} catch (IOException ioe) {
			System.out.println("error");
			return "ERROR";
		}

	}

	public int win() {
		for (int y = 0; y <= 6; y++) {
			for (int x = 0; x <= 7; x++) {
				System.out.print(check[x][y]);
			}
			System.out.println();
		}
		int res = 0;	//This result is set to determine which player won.
		//Five Conditions in each if statement to check for 5 same color in horizontal or vertical or diagonal
		//CHECK HORIZONTAL
		for (int x = 0; x < 4; x++) {
			for (int y = 0; y < 7; y++) {

				if (check[x][y] == 1 && check[x + 1][y] == 1 && check[x + 2][y] == 1 && check[x + 3][y] == 1 && check[x + 4][y] == 1) {
					res = 1;
				}
				if (check[x][y] == 2 && check[x + 1][y] == 2 && check[x + 2][y] == 2 && check[x + 3][y] == 2 && check[x + 4][y] == 2) {
					res = 2;
				}
			}
		}
		//CHECK VERTICAL
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 3; y++) {
				if (check[x][y] == 1 && check[x][y + 1] == 1 && check[x][y + 2] == 1 && check[x][y + 3] == 1 && check[x][y + 4] == 1) {
					res = 1;
				}
				if (check[x][y] == 2 && check[x][y + 1] == 2 && check[x][y + 2] == 2 && check[x][y + 3] == 2 && check[x][y + 4] == 2) {
					res = 2;
				}
			}
		}
		//CHECK DIAGONAL WITH POSITIVE SLOPE
		for (int x = 0; x < 4; x++) {
			for (int y = 0; y < 3; y++) {
				if (check[x][y] == 1 && check[x + 1][y + 1] == 1 && check[x + 2][y + 2] == 1 && check[x + 3][y + 3] == 1 && check[x + 4][y + 4] == 1) {
					res = 1;
				}
				if (check[x][y] == 2 && check[x + 1][y + 1] == 2 && check[x + 2][y + 2] == 2 && check[x + 3][y + 3] == 2 && check[x + 4][y + 4] == 2) {
					res = 2;
				} 
			}
		}
		//CHECK DIAGONAL WITH NEGATIVE SLOPE
		for (int x = 7; x > 3; x--) {
			for (int y = 0; y < 3; y++) {
				if (check[x - 4][y + 4] == 1 && check[x - 3][y + 3] == 1 && check[x - 2][y + 2] == 1 && check[x - 1][y + 1] == 1 && check[x][y] == 1) {
					res = 1;
				}
				if (check[x - 4][y + 4] == 2 && check[x - 3][y + 3] == 2 && check[x - 2][y + 2] == 2 && check[x - 1][y + 1] == 2 && check[x][y] == 2) {
					res = 2;
				} 

			}
		}
		return res;
	}
	//CHECK FOR DRAW
	public boolean draw() {
		for (int x = 0; x < 8; x++) { //If the 8 slots are full and no win found then it's draw.
			if (count[x] >= 0) {
				return false;
			}
		}
		return true;
	}

	public void aimove() {
		Random num = new Random();
		int x = num.nextInt(8);
		if (count[x] != -1) {
			System.out.println(count[x]);
			cells[x][count[x]].setBackground(comCol);
			check[x][count[x]] = 2;
			count[x]--;
			countercom++;
			label.setText(playert);
			coml.setText(comt + countercom);
		} else {
			aimove();
		}
		if (win() == 2)
			popAlert(2);
		else if (draw() == true)
			popAlert(0);
	}
	public static void main(String[] args) {
		JFrame game = new connect5();
		game.setTitle("Connect FIVE!");
		game.setSize(1150, 950);
		game.setLocation(100, 0);
		game.setVisible(true);
		game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}