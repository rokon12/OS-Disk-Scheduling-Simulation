package com.diskscheduling;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class MainFrame extends JFrame implements ActionListener {
	private JPanel inputPanel_;
	private JPanel graphPanel_;
	private JPanel buttonPanel_;
	private JComboBox algorithmCombo_;

	int startingPoint = 53;

	String[] alogorithmNames = { "FCFS", "SSTF", "SCAN", "CSCAN", "LOOK",
			"CLOOK" };
	String[] nameOfTheButtons = { "input", "Randoms", "submit", "Draw Graph",
			"clear" };
	// private JButton button;
	private MyButton button;

	private JLabel head;
	int[] diskPosstion;
	int selected;

	private JTextField[] inputField_;
	private JScrollPane jScrollPane;

	public int numOfCyn = 38;

	public MainFrame() {
		super("Disk Scheduling Graph");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setSize(new Dimension(600, 650));
		setLocationRelativeTo(null);
		setVisible(true);
		addWindowListener(new ExitListener());
		setLayout(new FlowLayout());
		initComponents();
	}

	public void initComponents() {
		graphPanel_ = new JPanel();
		// graphPanel_.setBorder(new TitledBorder("Graph"));
		graphPanel_.setPreferredSize(new Dimension(600, 400));

		inputPanel_ = new JPanel();
		inputPanel_.setBorder(new TitledBorder("Input"));
		inputPanel_.setPreferredSize(new Dimension(250, 200));
		// inputPanel_.setBackground(Color.yellow);

		buttonPanel_ = new JPanel();
		buttonPanel_.setPreferredSize(new Dimension(280, 200));
		buttonPanel_.setBorder(new TitledBorder("Buttons"));

		head = new JLabel("Head Possition: ");

		add(graphPanel_);
		add(inputPanel_);
		add(buttonPanel_);

		setButtonPanel();
	}

	public void setInputPanel() {
		inputPanel_.removeAll();

		JPanel valuePanel = new JPanel();

		numOfCyn = Integer.parseInt(JOptionPane.showInputDialog(this,
				"Enter the number of Cylinder: "));
		valuePanel.setLayout(new GridLayout(numOfCyn, 2, 10, 10));

		inputField_ = new JTextField[numOfCyn];

		for (int i = 0; i < inputField_.length; i++) {
			JLabel label = new JLabel("Cyldr: " + (i + 1));
			label.setFont(new Font("Vardana", Font.BOLD, 14));

			inputField_[i] = new JTextField(2);
			inputField_[i].setHorizontalAlignment(JTextField.CENTER);

			valuePanel.add(label);
			valuePanel.add(inputField_[i]);
		}

		jScrollPane = new JScrollPane();
		jScrollPane.setPreferredSize(new java.awt.Dimension(200, 150));

		jScrollPane.setViewportView(valuePanel);
		jScrollPane.validate();
		inputPanel_.add(jScrollPane);
		inputPanel_.revalidate();
	}

	public void setButtonPanel() {
		buttonPanel_.setLayout(new FlowLayout());

		for (int i = 0; i < nameOfTheButtons.length; i++) {
			button = new MyButton(nameOfTheButtons[i], 50);
			button.addActionListener(this);
			// button.setName(nameOfTheButtons[i]);
			buttonPanel_.add(button);
		}

		algorithmCombo_ = new JComboBox(alogorithmNames);
		buttonPanel_.add(algorithmCombo_);
		buttonPanel_.revalidate();
	}

	public void setGraphPanel(JPanel panel) {
		graphPanel_.setLayout(new FlowLayout());
		graphPanel_.removeAll();
		graphPanel_.add(panel);

		head.setFont(new Font("Vardana", Font.BOLD, 14));
		head.setForeground(Color.BLUE);
		head.setText("Algorithm: " + algorithmCombo_.getSelectedItem()
				+ " ^_^ Head Starting point: " + startingPoint);
		graphPanel_.add(head);
		graphPanel_.repaint();
		graphPanel_.revalidate();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("input")) {
			setInputPanel();
		} else if (e.getActionCommand().equals("Randoms")) {
			for (int i = 0; i < numOfCyn; i++) {
				long random = (long) (Math.random() * 200 + 1);
				inputField_[i].setText("" + random);
			}
		} else if (e.getActionCommand().equals("Draw Graph")) {
			selected = algorithmCombo_.getSelectedIndex();
			diskPosstion = new int[numOfCyn + 1];

			for (int i = 0; i < numOfCyn; i++) {
				diskPosstion[i + 1] = Integer
						.parseInt(inputField_[i].getText());
			}
			startingPoint = Integer.parseInt(JOptionPane.showInputDialog(this,
					"Enter starting point: "));
			diskPosstion[0] = startingPoint;

			if (selected == 0) {
				setGraphPanel(new DrawGraph(diskPosstion));
			} else if (selected == 1) {

				int[] ssft = new SSTF().getSftf(diskPosstion, startingPoint);
				int[] temp = new int[ssft.length - 1];

				for (int i = 0; i < ssft.length - 1; i++) {
					temp[i] = ssft[i];
				}
				setGraphPanel(new DrawGraph(temp));
			} else if (selected == 2) {
				ScanDisk scan = new ScanDisk(diskPosstion, startingPoint);
				setGraphPanel(new DrawGraph(scan.calculateScanDisk()));
			} else if (selected == 3) {
				CScanDisk cscan = new CScanDisk(diskPosstion, startingPoint);
				setGraphPanel(new DrawGraph(cscan.calculateScanDisk()));
			} else if (selected == 4) {
				LookDisk look = new LookDisk(diskPosstion, startingPoint);
				setGraphPanel(new DrawGraph(look.calculate()));
			} else if (selected == 5) {
				ClookDisk clook = new ClookDisk(diskPosstion, startingPoint);
				setGraphPanel(new DrawGraph(clook.calculate()));
			}
		}
	}
}
