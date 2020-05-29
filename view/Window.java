package Maze.view;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
public class Window {
	/*
	 * A class to easily use JOptionPanes to display messages
	 */
	private static JFrame j;
	public Window() {
		j = new JFrame();
	}
	
	//Displays a JOptionPane with a message
	public static void msg(String msg) {
		JOptionPane.showMessageDialog(null, msg);
	}
	 
	//Displays a message and options for user to choose from
	public static int option(String[] options, String msg) {
		return JOptionPane.showOptionDialog(
				j, 
				msg, // my message
                "Click a button", // dialog box title
                JOptionPane.DEFAULT_OPTION, 
                JOptionPane.INFORMATION_MESSAGE, 
                null, 
                options, // possible options
                options[0]); // default option
	}
	
	//Displays a message, options for user to choose from, and an Image to go along with it
	public static int option1(String[] options, String msg, Icon i) {
        return JOptionPane.showOptionDialog(
                j, 
                msg, // my message
                null, // dialog box title
                JOptionPane.DEFAULT_OPTION, 
                JOptionPane.PLAIN_MESSAGE, 
                i, 
                options, // possible options
                options[0]); // default option
    }
	
	//Prints a message and skips a line
	public static void println(String msg) {
		System.out.println(msg);
	}
	
	//Prints a message
	public static void print(String msg) {
		System.out.println(msg);
	}

	//Returns the string of the user's input
	public static String in(Object msg) {
		return JOptionPane.showInputDialog(msg);
	}
	
	//Displays a message with an icon
	public static void msg2(String msg, Icon i){
        JOptionPane.showMessageDialog(
                j, 
                msg, 
                null, 
                JOptionPane.DEFAULT_OPTION, 
                i); 
    }
	
	/*
	 * Displays options with an image and returns the option chosen
	 * This is unique method for each question: Koopaling Question
	 */
	public static String showInputDialogling(String string, ImageIcon i) {
		// TODO Auto-generated method stub
		String[] s = {"Koopalings","Koopakids","IDK"};
		return (String)JOptionPane.showInputDialog(
				null,string,"stuff",JOptionPane.PLAIN_MESSAGE,i,s,s[0]
				);
	}
	
	/*
	 * Displays options with an image and returns the option chosen
	 * This is unique method for each question: Wario Question
	 */
	public static String showInputDialogwar(String string, ImageIcon i) {
		// TODO Auto-generated method stub
		String[] s = {"Waluigi","Wario","Wabowser"};
		return (String)JOptionPane.showInputDialog(
				null,string,"stuff",JOptionPane.PLAIN_MESSAGE,i,s,s[0]
				);
	}
	
	/*
	 * Displays options with an image and returns the option chosen
	 * This is unique method for each question: Goomba Question
	 */
	public static String showInputDialoggoomba(String string, ImageIcon i) {
		// TODO Auto-generated method stub
		String[] s = {"Koopa Troopa","Bowser Jr.","Goomba"};
		return (String)JOptionPane.showInputDialog(
				null,string,"stuff",JOptionPane.PLAIN_MESSAGE,i,s,s[0]
				);
	}
	/*
	 * Displays options with an image and returns the option chosen
	 * This is unique method for each question: Birdo Question
	 */
	public static String showInputDialogbird(String string, ImageIcon i) {
		// TODO Auto-generated method stub
		String[] s = {"Yoshi's Girl","Birdo","Dinosaur Girl"};
		return (String)JOptionPane.showInputDialog(
				null,string,"stuff",JOptionPane.PLAIN_MESSAGE,i,s,s[0]
				);	}

}

