package com.dhl.chatbot.util;

import java.awt.Graphics;

import javax.swing.JLabel;

/**
 * 
 * @author Prashant Padmanabhan
 *
 */
public class UnderLinedLabel extends JLabel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int x1;
	private int y1;
	private int x2;
	private int y2;
	
	

	/**
	 * This id constructor for UnderLinedLabel.java
	 * @param x1
	 * @param x2
	 * @param y1
	 * @param y2
	 */
	public UnderLinedLabel(int x1, int x2, int y1, int y2) {
		super();
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
	}



	public void paint(Graphics g) {
		super.paint(g);
		g.drawLine(x1, y1, x2, y2);
	}
}