package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextPane;

import com.thoughtworks.xstream.core.util.FastField;

import Khamd.Main;

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

public class Frame {

	private JFrame frame;
	private final ButtonGroup buttonGroup1 = new ButtonGroup();
	private JTextField depth;
	private JTextField funcName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame window = new Frame();
					window.frame.setVisible(true);
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
		

		

		funcName = new JTextField();
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
		
		
	}
}
