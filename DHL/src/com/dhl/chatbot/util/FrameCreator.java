package com.dhl.chatbot.util;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * 
 * @author Prashant Padmanabhan
 *
 */
public class FrameCreator {
	
	public final static int STANDARD_COMPONENT_DIFFERENCE = 40;
	
	public final static int STANDARD_HEIGHT = 25;
	
	public final static int STANDARD_WIDTH = 25;
	
	public final static int STANDARD_CHECK_BOX_WIDTH = 25;
	
	public final static int STANDARD_DATE_SELECTOR_WIDTH = 20;
	
	public final static int STANDARD_TEXT_FIELD_WIDTH = 190;
	
	public final static int STANDARD_BUTTON_WIDTH = 90;
	
	public final static int START_MENU_MARGIN = 60;
	
	public final static int STANDARD_SMALL_LABEL_WIDTH = 75;
	
	public final static int STANDARD_LONG_LABEL_WIDTH = 505;
	
	public final static int STANDARD_MEDIUM_LABEL_WIDTH = 125;
	
	public final static int STANDARD_DATE_FIELD_WIDTH = 100;
	
	public final static int STANDARD_DATE_TIME_FIELD_WIDTH = 120;
	
	public final static int STANDARD_DATE_INDICATOR_LABEL_DIFF_WIDTH = 15;
	
	public final static int STANDARD_TEXT_LABEL_DIFF = 15;
	
	public final static int STANDARD_RADIO_DIFF = 75;
	
	public final static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
	public final static Dimension area = new Dimension(0,0);
	
	public static JFrame createFrame() {
		JFrame frame = new JFrame();
		Dimension frameSize = frame.getSize();
		frameSize.height = screenSize.height;
		frameSize.width = screenSize.width;
		
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(frameSize.width, frameSize.height - START_MENU_MARGIN);
		
		return frame;
	}
	
	public static void main(String[] args) {
		JFrame frame = createFrame();
		frame.setVisible(true);
	}
	
	public static JFrame addComponents(final JFrame frame, List<Component> componenetList) {
		JPanel jPanel = null;
		int width = (new Double(frame.getSize().getWidth())).intValue(); 
		int height = (new Double(frame.getSize().getHeight())).intValue();
			boolean isScrollPaneRequired = false;
		for (Component component : componenetList) {
			Rectangle rectangle = component.getBounds();
			if (rectangle != null) {
				if (width < rectangle.getX() + rectangle.getWidth()) {
					width = new Double(rectangle.getX() + rectangle.getWidth()
							).intValue() + STANDARD_HEIGHT;
					isScrollPaneRequired = true;
				}
				
				if (height < rectangle.getY() + rectangle.getHeight()) {
					height = new Double(rectangle.getY() + rectangle.getHeight()
							).intValue() + STANDARD_HEIGHT;
					isScrollPaneRequired = true;
				}
			}
			if (jPanel == null) {
				if (! isScrollPaneRequired) {
//					jPanel = new JPanel();
					jPanel = new JPanel();
					jPanel.setLayout(new BorderLayout());
				} else {
//					jPanel = new JPanel();
					jPanel = new JPanel();
					jPanel.setLayout(new BorderLayout());
				}
			}
			jPanel.add(component, BorderLayout.CENTER);
			
		}
		
		if (isScrollPaneRequired) {
			final JScrollPane scroller = new JScrollPane(jPanel);
//			scroller.setOpaque(true); //content panes must be opaque
//			scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
//			scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			mouseReleased1(null, jPanel, width, height);
			
			final int width1 = width;
			final int height1 = height;
			final JPanel jPanel1 = jPanel; 
			

//			frame.addMouseListener(new MouseListener() {
//				
////	        	public void mouseReleased(MouseEvent e){mouseReleased1(e, jPanel1, width1, height1);}
//				public void mouseExited(MouseEvent e){
//					System.out.println("inside frame.mouseExited");
////				mouseReleased1(e, jPanel1, width1, height1);
////				frame.remove(scroller);
////				frame.setContentPane(scroller);
////				frame.repaint();
//				}
//				
//	        	public void mouseReleased(MouseEvent e){
//	        		System.out.println("inside frame.mouseReleased");
////	        		mouseReleased1(e, jPanel1, width1, height1);
////	        		frame.remove(scroller);
////					frame.setContentPane(scroller);
////					frame.repaint();
////	        		mouseEntered(e);
//	        	}
//	            public void mouseEntered(MouseEvent e){
//	            	System.out.println("inside frame.mouseEntered");
//	            	mouseReleased1(e, jPanel1, width1, height1);
//	            	frame.repaint();
////	            	frame.remove(scroller);
////					frame.setContentPane(scroller);
////					mouseExited(e);
//	            }
//	            public void mouseClicked(MouseEvent e){
//	            	System.out.println("inside frame.mouseClicked");
////	            	frame.repaint();
////	            	frame.remove(scroller);
////					frame.setContentPane(scroller);
////					mouseExited(e);
//	            	}
//	            public void mousePressed(MouseEvent e){
//	            	System.out.println("inside frame.mousePressed");
////	            	frame.repaint();
////	            	frame.remove(scroller);
////					frame.setContentPane(scroller);
////					mouseExited(e);
//	            	}
//	        });
//			scroller.addMouseMotionListener(new MouseMotionListener() {
//
//				@Override
//				public void mouseDragged(MouseEvent e) {
//					System.out.println("inside scroller.mouseDragged");
//					mouseReleased1(e, jPanel1, width1, height1);
//					frame.repaint();
//					
//				}
//
//				@Override
//				public void mouseMoved(MouseEvent e) {
//					System.out.println("inside scroller.mouseMoved");
//					mouseReleased1(e, jPanel1, width1, height1);
//					frame.repaint();
//					
//				}
//				
//			});
//			scroller.setIgnoreRepaint(false);
//			scroller.addMouseMotionListener(new MouseMotionListener() {
//
//				@Override
//				public void mouseDragged(MouseEvent e) {
//					System.out.println("inside scroller.mouseDragged");
//					
//				}
//
//				@Override
//				public void mouseMoved(MouseEvent e) {
//					System.out.println("inside scroller.mouseMoved");
//					mouseReleased1(e, jPanel1, width1, height1);
//					
//				}
//				
//			});
			scroller.getHorizontalScrollBar().addAdjustmentListener(new AdjustmentListener() {

				@Override
				public void adjustmentValueChanged(AdjustmentEvent e) {
//					System.out.println("inside scroller.adjustmentValueChanged");
					mouseReleased1(null, jPanel1, width1, height1);
					frame.repaint();
				}
				
			});
			
			scroller.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {

				@Override
				public void adjustmentValueChanged(AdjustmentEvent e) {
//					System.out.println("inside scroller.adjustmentValueChanged");
					mouseReleased1(null, jPanel1, width1, height1);
					frame.repaint();
				}
				
			});
//			scroller.getHorizontalScrollBar().addMouseListener(new MouseListener() {
//				
////	        	public void mouseReleased(MouseEvent e){mouseReleased1(e, jPanel1, width1, height1);}
//				public void mouseExited(MouseEvent e){
////					System.out.println("inside scroller.mouseExited");
//////					jPanel1.revalidate();
//////					jPanel1.repaint();
////				mouseReleased1(e, jPanel1, width1, height1);
////				jPanel1.revalidate();
////        		jPanel1.scrollRectToVisible(new Rectangle(0, 0, width1, height1));
////					frame.repaint();
////				frame.remove(scroller);
////				frame.setContentPane(scroller);
////				frame.repaint();
//				}
//				
//	        	public void mouseReleased(MouseEvent e){
//	        		System.out.println("inside scroller.mouseReleased");
//	        		mouseReleased1(e, jPanel1, width1, height1);
////	        		jPanel1.revalidate();
////	        		jPanel1.scrollRectToVisible(new Rectangle(0, 0, width1, height1));
////	        		frame.repaint();
////	        		frame.remove(scroller);
////					frame.setContentPane(scroller);
//					frame.repaint();
////					mouseEntered(e);
//	        		}
//	            public void mouseEntered(MouseEvent e){
////	            	System.out.println("inside scroller.mouseEntered");
//////	            	jPanel1.revalidate();
//////	            	jPanel1.repaint();
////	            	mouseReleased1(e, jPanel1, width1, height1);
////	            	jPanel1.scrollRectToVisible(new Rectangle(0, 0, width1, height1));
////	            	
//////	            	frame.remove(scroller);
//////					frame.setContentPane(scroller);
////					frame.repaint();
////					mouseExited(e);
//	            }
//	            public void mouseClicked(MouseEvent e){
//	            	mouseReleased1(e, jPanel1, area.width, area.height);
//	            	frame.repaint();
////	            	frame.remove(scroller);
////					frame.setContentPane(scroller);
//				}
//	            public void mousePressed(MouseEvent e){
//	            	System.out.println("inside scroller.mousePressed");
////	            	jPanel1.revalidate();
////	        		jPanel1.scrollRectToVisible(new Rectangle(0, 0, width1, height1));
//	            	mouseReleased1(e, jPanel1, area.width, area.height);
//	            	frame.repaint();
////	            	frame.remove(scroller);
////					frame.setContentPane(scroller);
////					frame.repaint();
//	            }
//	        });
//			scroller.getVerticalScrollBar().addMouseListener(new MouseListener() {
//				
////	        	public void mouseReleased(MouseEvent e){mouseReleased1(e, jPanel1, width1, height1);}
//				public void mouseExited(MouseEvent e){
//					System.out.println("inside scroller.mouseExited");
////					jPanel1.revalidate();
////					jPanel1.repaint();
//				mouseReleased1(e, jPanel1, width1, height1);
//				jPanel1.revalidate();
//        		jPanel1.scrollRectToVisible(new Rectangle(0, 0, width1, height1));
//					frame.repaint();
////				frame.remove(scroller);
////				frame.setContentPane(scroller);
////				frame.repaint();
//				}
//				
//	        	public void mouseReleased(MouseEvent e){
//	        		System.out.println("inside scroller.mouseReleased");
//	        		mouseReleased1(e, jPanel1, width1, height1);
//	        		jPanel1.revalidate();
//	        		jPanel1.scrollRectToVisible(new Rectangle(0, 0, width1, height1));
////	        		frame.repaint();
////	        		frame.remove(scroller);
////					frame.setContentPane(scroller);
//					frame.repaint();
////					mouseEntered(e);
//	        		}
//	            public void mouseEntered(MouseEvent e){
//	            	System.out.println("inside scroller.mouseEntered");
////	            	jPanel1.revalidate();
////	            	jPanel1.repaint();
//	            	mouseReleased1(e, jPanel1, width1, height1);
//	            	jPanel1.scrollRectToVisible(new Rectangle(0, 0, width1, height1));
//	            	
////	            	frame.remove(scroller);
////					frame.setContentPane(scroller);
//					frame.repaint();
////					mouseExited(e);
//	            }
//	            public void mouseClicked(MouseEvent e){
//	            	
////	            	frame.remove(scroller);
////					frame.setContentPane(scroller);
//				}
//	            public void mousePressed(MouseEvent e){
//	            	System.out.println("inside scroller.mousePressed");
////	            	frame.remove(scroller);
////					frame.setContentPane(scroller);
////					frame.repaint();
//	            }
//	        });
//			scroller.addComponentListener(new ComponentListener() {
//
//				@Override
//				public void componentHidden(ComponentEvent e) {
//					System.out.println("inside scroller.componentHidden");
//					mouseReleased1(null, jPanel1, width1, height1);
//					
//				}
//
//				@Override
//				public void componentMoved(ComponentEvent e) {
//					System.out.println("inside scroller.componentMoved");
//					mouseReleased1(null, jPanel1, width1, height1);
//					jPanel1.revalidate();
//	        		jPanel1.scrollRectToVisible(new Rectangle(0, 0, width1, height1));
//						frame.repaint();
//				}
//
//				@Override
//				public void componentResized(ComponentEvent e) {
//					System.out.println("inside scroller.componentResized");
//					mouseReleased1(null, jPanel1, width1, height1);
//					
//				}
//
//				@Override
//				public void componentShown(ComponentEvent e) {
//					System.out.println("inside scroller.componentShown");
//					mouseReleased1(null, jPanel1, width1, height1);
//					
//				}
//				
//			});
			
//			scroller.addMouseListener(new MouseListener() {
//				
////	        	public void mouseReleased(MouseEvent e){mouseReleased1(e, jPanel1, width1, height1);}
//				public void mouseExited(MouseEvent e){
//					System.out.println("inside scroller.mouseExited");
////					jPanel1.revalidate();
////					jPanel1.repaint();
//				mouseReleased1(e, jPanel1, width1, height1);
//				jPanel1.revalidate();
//        		jPanel1.scrollRectToVisible(new Rectangle(0, 0, width1, height1));
//					frame.repaint();
////				frame.remove(scroller);
////				frame.setContentPane(scroller);
////				frame.repaint();
//				}
//				
//	        	public void mouseReleased(MouseEvent e){
//	        		System.out.println("inside scroller.mouseReleased");
//	        		mouseReleased1(e, jPanel1, width1, height1);
//	        		jPanel1.revalidate();
//	        		jPanel1.scrollRectToVisible(new Rectangle(0, 0, width1, height1));
////	        		frame.repaint();
////	        		frame.remove(scroller);
////					frame.setContentPane(scroller);
//					frame.repaint();
////					mouseEntered(e);
//	        		}
//	            public void mouseEntered(MouseEvent e){
//	            	System.out.println("inside scroller.mouseEntered");
////	            	jPanel1.revalidate();
////	            	jPanel1.repaint();
//	            	mouseReleased1(e, jPanel1, width1, height1);
//	            	jPanel1.scrollRectToVisible(new Rectangle(0, 0, width1, height1));
//	            	
////	            	frame.remove(scroller);
////					frame.setContentPane(scroller);
//					frame.repaint();
////					mouseExited(e);
//	            }
//	            public void mouseClicked(MouseEvent e){
//	            	
////	            	frame.remove(scroller);
////					frame.setContentPane(scroller);
//				}
//	            public void mousePressed(MouseEvent e){
//	            	System.out.println("inside scroller.mousePressed");
////	            	frame.remove(scroller);
////					frame.setContentPane(scroller);
////					frame.repaint();
//	            }
//	        });
			scroller.setPreferredSize(new Dimension(width, height));
//			JPanel panelMaster = new ScrollDemoMain(jPanel, width, height);
			
//			JPanel panelMaster = new JPanel();
//			panelMaster.add(scroller);
//			mouseReleased1(null, panelMaster, width, height);
//			panelMaster.setOpaque(true);
			frame.setContentPane(scroller);
//			frame.setContentPane(scroller);
//			frame.repaint();

		} else {
			frame.getContentPane().add(jPanel, null);
			frame.setVisible(true);
		}
//		ScrollDemon.createAndShowGUI(frame, componenetList);
		return frame;
	}
		
	public static void mouseReleased1(MouseEvent e, JPanel panel, int W, int H) {
//      final int W = (new Double(area2.getWidth())).intValue(); 
//      final int H = (new Double(area2.getHeight())).intValue();
      
//      W = 100; 
//      H = 100;
		
      boolean changed = false;
      int x = 0;
      int y = 0;
      if (e != null) {
    	  x = e.getX() - W/2;
//    	  System.out.println("e.getX() - W/2 :" + x);
          y = e.getY() - H/2;
//          System.out.println("e.getY() - H/2 :" + y);
      }
      
//         
    
          if (x < 0) x = 0;
          if (y < 0) y = 0;
          Rectangle rect = new Rectangle(x, y, W, H);
          panel.scrollRectToVisible(rect);
//          panel.repaint();
          
          int this_width = (x + W);
         
//          System.out.println("x :" + x);
//          System.out.println("y :" + y);
//          System.out.println("W :" + W);
//          System.out.println("H :" + H);
//          System.out.println("area.width :" + area.width);
//          System.out.println("area.height :" + area.height);
//          System.out.println("this_width :" + this_width);
          
          if (this_width > area.width) {
              area.width = this_width; 
              changed=true;
          }

         int this_height = (y + H);
//         System.out.println("this_height :" + this_height);
          if (this_height > area.height) {
              area.height = this_height; 
              changed=true;
          }
          
      if (changed) {
          //Update client's preferred size because
          //the area taken up by the graphics has
          //gotten larger or smaller (if cleared).
    	  panel.setPreferredSize(area);

          //Let the scroll pane know to update itself
          //and its scrollbars.
    	  panel.revalidate();
      }
  }
	
}