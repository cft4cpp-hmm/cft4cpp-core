package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextPane;

<<<<<<< HEAD
import com.thoughtworks.xstream.core.util.FastField;

import Khamd.Main;
=======
import org.apache.poi.ss.formula.ptg.Deleted3DPxg;

import com.thoughtworks.xstream.core.util.FastField;

import Khamd.CFT4CPP;
import Khamd.FullBoundedTestGen;
import Khamd.Main;
import config.AbstractSetting;
import config.Settingv2;
import console.Console;
>>>>>>> 1107e24

import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButtonMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.SystemColor;
<<<<<<< HEAD

public class Frame {

	private JFrame frame;
	private final ButtonGroup buttonGroup1 = new ButtonGroup();
	private JTextField depth;
=======
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.JComboBox;

public class Frame {

	private JFrame frmWcftcpp;
	private final ButtonGroup buttonGroup1 = new ButtonGroup();
>>>>>>> 1107e24
	private JTextField funcName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame window = new Frame();
<<<<<<< HEAD
					window.frame.setVisible(true);
=======
					window.frmWcftcpp.setVisible(true);
>>>>>>> 1107e24
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Frame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
<<<<<<< HEAD
		frame = new JFrame();
		frame.setBounds(100, 100, 747, 431);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblPathToZ = new JLabel("Path to Z3");
		lblPathToZ.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPathToZ.setBounds(10, 10, 86, 27);
		frame.getContentPane().add(lblPathToZ);
		
		JTextPane Z3 = new JTextPane();
		Z3.setBounds(178, 10, 461, 27);
		frame.getContentPane().add(Z3);
		
		JLabel lblPathToZ_1_1_1 = new JLabel("Path to Mingw32");
		lblPathToZ_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPathToZ_1_1_1.setBounds(10, 47, 146, 27);
		frame.getContentPane().add(lblPathToZ_1_1_1);
		
		JLabel lblPathToGcc = new JLabel("Path to GCC");
		lblPathToGcc.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPathToGcc.setBounds(10, 84, 86, 27);
		frame.getContentPane().add(lblPathToGcc);
		
		JLabel lblPathToG = new JLabel("Path to g++");
		lblPathToG.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPathToG.setBounds(10, 119, 117, 27);
		frame.getContentPane().add(lblPathToG);
		
		JTextPane mingw = new JTextPane();
		mingw.setBounds(178, 47, 461, 27);
		frame.getContentPane().add(mingw);
		
		JTextPane gcc = new JTextPane();
		gcc.setBounds(178, 84, 461, 27);
		frame.getContentPane().add(gcc);
		
		JTextPane gplus = new JTextPane();
		gplus.setBounds(178, 119, 461, 27);
		frame.getContentPane().add(gplus);
		
		JRadioButton rdbtnGenerateForC = new JRadioButton("Generate for C2");
		rdbtnGenerateForC.setSelected(true);
		JRadioButton rdbtnGenerateForBoudary = new JRadioButton("Generate for boudary");
		JRadioButton rdbtnGenerateForC_1 = new JRadioButton("Generate for C2 & boudary");
		JButton btnStart = new JButton("Start");
		JTextPane txtpnP = new JTextPane();
		JLabel lblFunctionNamw = new JLabel("Function Name");
		rdbtnGenerateForC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnGenerateForC.isSelected()) {
					rdbtnGenerateForBoudary.setSelected(false);
					rdbtnGenerateForC_1.setSelected(false);
				}
			}
		});
		buttonGroup1.add(rdbtnGenerateForC);
		buttonGroup1.add(rdbtnGenerateForBoudary);
		buttonGroup1.add(rdbtnGenerateForC_1);
		rdbtnGenerateForC.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdbtnGenerateForC.setBounds(61, 173, 137, 21);
		frame.getContentPane().add(rdbtnGenerateForC);
		
		rdbtnGenerateForBoudary.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnGenerateForBoudary.isSelected()) {
					rdbtnGenerateForC.setSelected(false);
					rdbtnGenerateForC_1.setSelected(false);
				}
			}
		});
		rdbtnGenerateForBoudary.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdbtnGenerateForBoudary.setBounds(241, 173, 159, 21);
		frame.getContentPane().add(rdbtnGenerateForBoudary);
		
		
		rdbtnGenerateForC_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnGenerateForC_1.isSelected()) {
					rdbtnGenerateForBoudary.setSelected(false);
					rdbtnGenerateForC.setSelected(false);
				}
			}
		});
		rdbtnGenerateForC_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdbtnGenerateForC_1.setBounds(423, 173, 249, 21);
		frame.getContentPane().add(rdbtnGenerateForC_1);
		
		JLabel depthLabel = new JLabel("depth");
		depthLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		depthLabel.setBounds(10, 216, 49, 27);
		frame.getContentPane().add(depthLabel);
		
		depth = new JTextField();
		depth.setBounds(61, 221, 71, 22);
		frame.getContentPane().add(depth);
		depth.setColumns(10);
=======
		frmWcftcpp = new JFrame();
		frmWcftcpp.setTitle("WCFT4Cpp");
		frmWcftcpp.setBounds(100, 100, 701, 431);
		frmWcftcpp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmWcftcpp.getContentPane().setLayout(null);
		
		JRadioButton WCFT4Cpp = new JRadioButton("WCFT");
		WCFT4Cpp.setSelected(true);
		JRadioButton BCFT4Cpp = new JRadioButton("BVTG");
		JRadioButton STCFG = new JRadioButton("STCFG");
		JRadioButton CTCFG = new JRadioButton("Concolic");
		JTextPane txtpnP = new JTextPane();
		JComboBox comboBox = new JComboBox();
		comboBox.addItem(0);
		comboBox.addItem(1);
		comboBox.addItem(2);
		comboBox.addItem(3);
		comboBox.addItem(4);
		comboBox.setSelectedIndex(0);
		comboBox.setBounds(196, 132, 56, 21);
		frmWcftcpp.getContentPane().add(comboBox);
		
		
		
		STCFG.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtpnP.setText("Please fill out required field and wait until every thing done");
				txtpnP.setForeground(Color.black);
				if(STCFG.isSelected()) {
					BCFT4Cpp.setSelected(false);
					WCFT4Cpp.setSelected(false);
					CTCFG.setSelected(false);
				}
			}
		});
		
		
		JButton btnStart = new JButton("Generate test data");
		btnStart.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mousePressed(MouseEvent e) {
				btnStart.setText("wait");
				txtpnP.setText("Please fill out required field and wait until every thing done");
				txtpnP.setForeground(Color.black);
			}
		});
		
		
		JLabel lblFunctionNamw = new JLabel("Function Name");
		WCFT4Cpp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtpnP.setText("Please fill out required field and wait until every thing done");
				txtpnP.setForeground(Color.black);
				if(WCFT4Cpp.isSelected()) {
					BCFT4Cpp.setSelected(false);
					CTCFG.setSelected(false);
					STCFG.setSelected(false);
				}
			}
		});
		buttonGroup1.add(WCFT4Cpp);
		buttonGroup1.add(BCFT4Cpp);
		buttonGroup1.add(CTCFG);
		buttonGroup1.add(STCFG);
		WCFT4Cpp.setFont(new Font("Tahoma", Font.PLAIN, 14));
		WCFT4Cpp.setBounds(61, 40, 137, 21);
		frmWcftcpp.getContentPane().add(WCFT4Cpp);
		
		BCFT4Cpp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtpnP.setText("Please fill out required field and wait until every thing done");
				txtpnP.setForeground(Color.black);
				if(BCFT4Cpp.isSelected()) {
					WCFT4Cpp.setSelected(false);
					CTCFG.setSelected(false);
					STCFG.setSelected(false);
				}
			}
		});
		BCFT4Cpp.setFont(new Font("Tahoma", Font.PLAIN, 14));
		BCFT4Cpp.setBounds(61, 90, 131, 21);
		frmWcftcpp.getContentPane().add(BCFT4Cpp);
		CTCFG.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtpnP.setText("Please fill out required field and wait until every thing done");
				txtpnP.setForeground(Color.black);
				if(CTCFG.isSelected()) {
					BCFT4Cpp.setSelected(false);
					WCFT4Cpp.setSelected(false);
					STCFG.setSelected(false);
				}
			}
		});
		STCFG.setFont(new Font("Tahoma", Font.PLAIN, 14));
		STCFG.setBounds(212, 40, 249, 21);
		frmWcftcpp.getContentPane().add(STCFG);

		CTCFG.setFont(new Font("Tahoma", Font.PLAIN, 14));
		CTCFG.setBounds(214, 90, 94, 21);
		frmWcftcpp.getContentPane().add(CTCFG);
		
		JLabel depthLabel = new JLabel("Depth");
		depthLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		depthLabel.setBounds(61, 127, 49, 27);
		frmWcftcpp.getContentPane().add(depthLabel);
>>>>>>> 1107e24
		

		

		funcName = new JTextField();
<<<<<<< HEAD
		funcName.setColumns(10);
		funcName.setBounds(137, 259, 137, 22);
		frame.getContentPane().add(funcName);
		
		txtpnP.setFont(new Font("Times New Roman", Font.BOLD, 14));
		txtpnP.setBackground(SystemColor.control);
		txtpnP.setForeground(Color.BLACK);
		txtpnP.setText("Please fill out required field");
		txtpnP.setBounds(37, 357, 426, 27);
		txtpnP.setEditable(false);
		frame.getContentPane().add(txtpnP);
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtpnP.setForeground(Color.blue);	
				txtpnP.setText("Waiting....");
				int iterations = Integer.parseInt(depth.getText());
				String functionName = funcName.getText();
				String pathToz3 = Z3.getText();
				String pathToMingw32 = mingw.getText();
				String pathToGcc = gcc.getText();
				String pathTogPlus = gplus.getText();
				Main.pathToZ3 = pathToz3;
				Main.pathToGCC = pathToGcc;
				Main.pathToMingw32 = pathToMingw32;
				Main.pathToGPlus = pathTogPlus;
				
				Main main = new Main(functionName, iterations);
				try {
					main.run();
					txtpnP.setText("open report.html to view the result!");
				}catch (Exception ex) {
					// TODO: handle exception
					txtpnP.setText("Error while running tool. Please reivse your inputs.");
				}
			}
		});
		
		btnStart.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnStart.setBounds(444, 221, 123, 27);
		frame.getContentPane().add(btnStart);
		
		
		lblFunctionNamw.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblFunctionNamw.setBounds(10, 253, 117, 27);
		frame.getContentPane().add(lblFunctionNamw);
=======
		funcName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		funcName.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtpnP.setText("Please fill out required field and wait until every thing done");
				txtpnP.setForeground(Color.black);
				txtpnP.setForeground(Color.black);
			}
		});
		
		funcName.setColumns(10);
		funcName.setBounds(196, 164, 187, 22);
		frmWcftcpp.getContentPane().add(funcName);
		
		txtpnP.setFont(new Font("Verdana", Font.BOLD, 14));
		txtpnP.setBackground(SystemColor.control);
		txtpnP.setForeground(Color.BLACK);
		txtpnP.setText("Please wait until every thing done");
		txtpnP.setBounds(35, 305, 554, 27);
		txtpnP.setEditable(false);
		frmWcftcpp.getContentPane().add(txtpnP);
		
		
		btnStart.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
//				.setForeground(Color.blue);	
//				txtpnP.setText("Waiting....");
				
				
//				String pathToz3 = Z3.getText();
//				String pathToMingw32 = mingw.getText();
//				String pathToGcc = gcc.getText();
//				String pathTogPlus = gplus.getText();
//				
//				Main.pathToZ3 = pathToz3;
//				Main.pathToGCC = pathToGcc;
//				Main.pathToMingw32 = pathToMingw32;
//				Main.pathToGPlus = pathTogPlus;
				
				try {
					int iterations = (int) comboBox.getSelectedItem();
					String functionName = funcName.getText();
					Main.depth = iterations;
					CFT4CPP cft4cpp = new CFT4CPP(null,iterations, functionName);
					Main main = new Main(functionName, iterations);
					FullBoundedTestGen bGen = new FullBoundedTestGen(null, iterations, functionName);
					if(STCFG.isSelected()) {
						Main.nameOfMethod=STCFG.getText();
						cft4cpp.run();
						
					}
					else if(WCFT4Cpp.isSelected()) {
						Main.nameOfMethod=WCFT4Cpp.getText();
						main.run();
						
					}
					else if(BCFT4Cpp.isSelected()) {
						Main.nameOfMethod=BCFT4Cpp.getText();
						bGen.toHtml();
						
					}
					else if(CTCFG.isSelected()) {
						Main.nameOfMethod=CTCFG.getText();
						Console console = new Console(functionName);
						console.exportToHtml(new File("TEST_REPORT.html"), functionName);
						console = null; 
					}
					
					txtpnP.setText("open TEST_REPORT.html to view the result!");
					txtpnP.setForeground(Color.blue);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					txtpnP.setText("Error while running tool. Please reivse your inputs.");
					txtpnP.setForeground(Color.red);
					e1.printStackTrace();
				}
				btnStart.setText("Generate test data");
				
//				try {
//					main.run();
//					txtpnP.setText("open report.html to view the result!");
//				}catch (Exception ex) {
//					// TODO: handle exception
//					txtpnP.setText("Error while running tool. Please reivse your inputs.");
//				}
			}
		});
		
		btnStart.setFont(new Font("Verdana", Font.PLAIN, 13));
		btnStart.setBounds(212, 203, 170, 27);
		frmWcftcpp.getContentPane().add(btnStart);
		
		
		lblFunctionNamw.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblFunctionNamw.setBounds(61, 164, 117, 27);
		frmWcftcpp.getContentPane().add(lblFunctionNamw);
		
		
>>>>>>> 1107e24
		
		
	}
}
