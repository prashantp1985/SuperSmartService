package com.dhl.chatbot.util;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import com.dhl.chatbot.logging.TraceLogger;
@SuppressWarnings("serial")
/**
 * 
 * @author Prashant Padmanabhan
 *
 */
public class ImageCreator extends JPanel {
	
	private TraceLogger logger = new TraceLogger(ImageCreator.class);
	
    BufferedImage image;
    
    int imageAdjustment = 0;
    
    
  public ImageCreator() {
	  this(new FileFinder().getDCCImagePath(), 75);
//	  this("ganapathy.jpg", 75);
  }
  
  public ImageCreator(String filename, int imageAdjustment) {
	  try {
//	    	String path = System.getProperty("user.dir");
//		    File input = new File(path + filename);
	    	
//		    String path = new PathFinder().getStringPath(filename);
//		   	ExceptionLogger.log(path);
//	    	File input = new File(path);
//	    	image = ImageIO.read(input);
	    	super.setLayout(new BorderLayout());
		  	image = ImageIO.read(new File(filename));
		  	
	    	this.imageAdjustment = imageAdjustment;
	    } catch (IOException ie) {
	    	logger.logError(ie);
	    }
	  
  }
  
  public ImageCreator(BufferedImage image, int imageAdjustment) {
	  try {
	    	this.image = image;
	    	this.imageAdjustment = imageAdjustment;
	    } catch (Exception ie) {
	    	logger.logError(ie);
	    }
	  
  }
  
  public ImageCreator(InputStream filename, int imageAdjustment) {
	  try {
	    	image = ImageIO.read(filename);
	    	this.imageAdjustment = imageAdjustment;
	    } catch (IOException ie) {
	    	logger.logError(ie);
	    }
	  
  }
  

  /**
 * @return the image
 */
public BufferedImage getImage() {
	return image;
}

public void paint(Graphics g) {
	Dimension screenSize = FrameCreator.screenSize;
    g.drawImage( image, (screenSize.width) / 2 - imageAdjustment, FrameCreator.STANDARD_HEIGHT, null);
  }

}