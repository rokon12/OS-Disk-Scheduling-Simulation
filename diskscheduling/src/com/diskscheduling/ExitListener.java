package com.diskscheduling;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

public class ExitListener extends WindowAdapter {
	public void windowClosing(WindowEvent event) {
		int a = JOptionPane.showConfirmDialog(null, "are you sure to exit?",
				null, JOptionPane.OK_CANCEL_OPTION);
		System.out.println(a);
		if (a == JOptionPane.OK_OPTION) {
			System.exit(0);
		}
	}
}
