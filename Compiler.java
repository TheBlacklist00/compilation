package essaye;

import java.awt.EventQueue;



import javax.swing.JFrame;

//import javax.swing.JScrollBar;

//import javax.swing.JScrollPane;

//import javax.swing.BoundedRangeModel;

import javax.swing.JButton;

import java.awt.event.ActionListener;

import java.io.IOException;

import java.awt.event.ActionEvent;

//import javax.swing.JTextPane;

import java.awt.Font;

//import java.awt.Scrollbar;

import java.awt.TextArea;

import javax.swing.JLabel;

import javax.swing.JScrollPane;



import java.awt.Window.Type;

import java.awt.Color;

import javax.swing.UIManager;

import java.awt.SystemColor;

import javax.swing.JTextPane;

import javax.swing.Icon;

import javax.swing.ImageIcon;

import javax.swing.JTextArea;

import java.awt.ScrollPane;

import javax.swing.SwingConstants;

import java.awt.Toolkit;
import java.awt.Window;
import java.awt.Dialog.ModalExclusionType;



public class Compiler {



	private JFrame Compilateure;

	private int nbr_erreur;

	private String code;

	private boolean run = false;

	private TextArea textArea;

	private TextArea textArea_1;

	private JLabel lblNewLabel;

	//Semantique semantique;





	/**

	 * Launch the application.

	 */

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {

			public void run() {

				try {

					

					Compiler window = new Compiler();

					window.Compilateure.setVisible(true);

				} catch (Exception e) {

					e.printStackTrace();

				}

			}

		});

	}



	/**

	 * Create the application.

	 */

	public Compiler() {

		initialize();

	}



	/**

	 * Initialize the contents of the frame.

	 */

	private void initialize() {

		Compilateure = new JFrame();

		Compilateure.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);

		Compilateure.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Asusu\\Downloads\\snail.png"));

		Compilateure.setForeground(SystemColor.desktop);

		Compilateure.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Compilateure.setTitle("Snail COMPILATEUR");

		Compilateure.setBounds(100, 100, 783, 715);

		Compilateure.getContentPane().setLayout(null);

		

		

		

	

		 textArea = new TextArea();

		 textArea.setForeground(SystemColor.control);

		 textArea.setBackground(Color.BLACK);

		 textArea.setFont(new Font("Source Code Pro", Font.PLAIN, 18));

			textArea.setBounds(10, 184, 743, 233);

			Compilateure.getContentPane().add(textArea);

	

		

		

		

		JButton btnNewButton = new JButton("BROWSER");

	//	btnNewButton.setEnabled(false);

		btnNewButton.setHorizontalAlignment(SwingConstants.CENTER);

		btnNewButton.setBackground(Color.GREEN);

		btnNewButton.setIcon(new ImageIcon("C:\\Users\\Asusu\\Downloads\\add-button-inside-black-circle.png"));

		//btnNewButton.setFont(new Font("Yu Gothic", Font.PLAIN, 10));

		btnNewButton.addActionListener(new ActionListener() {

			

			public void actionPerformed(ActionEvent arg0) {

				

				FileChooser filechooser = new FileChooser();

				try {

					filechooser.PickMe();

				} catch (Exception e) {

					// TODO Auto-generated catch block

					e.printStackTrace();

				}

				lblNewLabel.setText(filechooser.filePath());

				code = filechooser.readFile();

				textArea.setText(code);

			}

		});

		btnNewButton.setBounds(10, 10, 98, 50);

		Compilateure.getContentPane().add(btnNewButton);
		Icon icone=new ImageIcon("C:\\Users\\Asusu\\Downloads\\analyze.png");

		JButton btnLexique = new JButton(icone);
		btnLexique.setText("LEXICAL");
		btnLexique.setForeground(Color.WHITE);
		btnLexique.setHorizontalAlignment(SwingConstants.CENTER);

		//btnLexique.setIcon(new ImageIcon("C:\\Users\\Asusu\\Downloads\\analyze.png"));

		btnLexique.setBackground(Color.BLUE);

		btnLexique.setBorderPainted(false);

		

		btnLexique.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				Lexical lexical = new Lexical();

				String analyse = (String) lexical.analyse_lex(code);

				textArea_1.setText(analyse);

				

				

			}

		});

		btnLexique.setBounds(10, 96, 120, 73);

		Compilateure.getContentPane().add(btnLexique);

		

		JButton btnSyntaxique = new JButton("SYNTAXIQUE");

		btnSyntaxique.setHorizontalAlignment(SwingConstants.CENTER);
		
		btnSyntaxique.setForeground(Color.WHITE);
		
		btnSyntaxique.setBackground(Color.BLUE);

		btnSyntaxique.setBorderPainted(false);

		btnSyntaxique.setIcon(new ImageIcon("C:\\Users\\Asusu\\Downloads\\puzzle.png"));

		btnSyntaxique.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				Syntaxique syntaxique = new Syntaxique();

				String analyse = (String) syntaxique.analyse_syn(code);

				nbr_erreur = syntaxique.erreur();

				textArea_1.setText(analyse);

			}

		});

		btnSyntaxique.setBounds(140, 96, 120, 73);

		Compilateure.getContentPane().add(btnSyntaxique);

		

		JLabel lblPath = new JLabel("PATH: ");

		lblPath.setFont(new Font("Source Sans Pro Light", Font.BOLD, 13));

		lblPath.setForeground(Color.BLACK);

		lblPath.setBounds(154, 25, 45, 23);

		Compilateure.getContentPane().add(lblPath);

		

	lblNewLabel = new JLabel("");

	lblNewLabel.setForeground(Color.BLACK);

	lblNewLabel.setFont(new Font("Source Code Pro", Font.PLAIN, 13));

		lblNewLabel.setBounds(188, 25, 375, 23);

		Compilateure.getContentPane().add(lblNewLabel);

		

		JButton btnSemantique = new JButton("SEMANTIQUE");

		btnSemantique.setHorizontalAlignment(SwingConstants.CENTER);

		btnSemantique.setBackground(Color.BLUE);
		
		btnSemantique.setForeground(Color.WHITE);

		btnSemantique.setBorderPainted(false);

		btnSemantique.setIcon(new ImageIcon("C:\\Users\\Asusu\\Downloads\\gear.png"));

		btnSemantique.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				 Semantique semantique = new Semantique(nbr_erreur,code);

				String resultat = null;

				try {

					resultat = (String)semantique.analyse_sem();

				} catch (IOException e) {

					// TODO Auto-generated catch block

					e.printStackTrace();

				}

				

				textArea.setText(resultat);

				run = true;

			}

		});

		btnSemantique.setBounds(282, 96, 120, 73);

		Compilateure.getContentPane().add(btnSemantique);

		

		

		

		textArea_1 = new TextArea();

		textArea_1.setFont(new Font("Source Code Pro", Font.PLAIN, 18));

		textArea_1.setBounds(12, 440, 741, 216);

		textArea_1.setForeground(SystemColor.control);

		 textArea_1.setBackground(new Color(17, 17, 17));

		Compilateure.getContentPane().add(textArea_1);

		

		JLabel lblNewLabel_1 = new JLabel("New label");

		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\Asusu\\Downloads\\windows_7_matrix-wallpaper-1440x900.jpg"));

		lblNewLabel_1.setBounds(-35, 0, 833, 680);

		Compilateure.getContentPane().add(lblNewLabel_1);

		

	

	}

}
