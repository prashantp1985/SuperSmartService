package com.dhl.chatbot.screens;
 
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileWriter;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.dhl.chatbot.controller.ChatResponseController;
import com.dhl.chatbot.enumeration.ClassifierType;
import com.dhl.chatbot.exception.DHLException;
import com.dhl.chatbot.util.ImageCreator;
/**
 * 
 * @author Prashant Padmanabhan
 *
 */
public class DHLChatScreen {
    final static boolean isToBeFilled = true;
    final static boolean isWeightX = true;
    final static boolean rightToLeft = false;
    private static String applicationName = "DHL Prash chatbot 1.0 - Developed by Prashant Padmanabhan";
    private static JFrame      chatScreen    = new JFrame(applicationName);
    private static JButton     saveButton;
    private static JButton     micButton;
    private static JButton     imageButton;
    private static JButton     sendButton;
    private static JButton     clearButton;
    private static JTextField  messageBox;
    private static JTextArea   chatArea;
    private JTextField  userNameTextField;
    private JFrame      loginScreen;
    private static String  userName;
    private JPanel panel = new JPanel();
    private int lastStartHeight = 0;
    private int lastEndHeight = 0;
    private int lastHeight = 0;
    private final boolean isCaseSensitive = false;
    private final int precision = 20;
    private final ClassifierType classifierType = ClassifierType.PRASH;
    private GridBagConstraints constraints = new GridBagConstraints();
    private JScrollPane scrollPane = new JScrollPane(panel);
    private StringBuilder chatLog = new StringBuilder();
    private JFileChooser chooser = new JFileChooser();
   
    
    public void showLogin() {
        chatScreen.setVisible(false);
        loginScreen = new JFrame(applicationName);
        userNameTextField = new JTextField(15);
        JLabel chooseUsernameLabel = new JLabel("User Name : ");
        JButton startChatButton = new JButton("Start Chat");
        userNameTextField.addKeyListener(new LoginButtonKeyListener());
        startChatButton.addActionListener(new StartChatButtonListener());
        JPanel imagePanel = new ImageCreator();
        JPanel loginPanel = new JPanel(new GridBagLayout());

        GridBagConstraints rightGrid = new GridBagConstraints();
        rightGrid.insets = new Insets(0, 0, 0, 10);
        rightGrid.anchor = GridBagConstraints.EAST;
        GridBagConstraints leftGrid = new GridBagConstraints();
        leftGrid.anchor = GridBagConstraints.WEST;
        leftGrid.insets = new Insets(0, 10, 0, 10);
        // preRight.weightx = 2.0;
        rightGrid.fill = GridBagConstraints.HORIZONTAL;
        rightGrid.gridwidth = GridBagConstraints.REMAINDER;
//        loginPanel.add(imagePanel, leftGrid);
        loginPanel.add(userNameTextField, rightGrid);
        loginPanel.add(chooseUsernameLabel, leftGrid);
        loginPanel.add(userNameTextField, rightGrid);
        
        rightGrid.insets = new Insets(10, 0, 0, 10);
        rightGrid.anchor = GridBagConstraints.EAST;
        rightGrid.gridx = 1;       //aligned with button 2
        rightGrid.gridwidth = 2;   //2 columns wide
        loginPanel.add(startChatButton, rightGrid);
        loginScreen.add(imagePanel);
        loginScreen.add(BorderLayout.CENTER, loginPanel);
        //loginScreen.add(BorderLayout.SOUTH, startChatButton);
        
        loginScreen.setSize(600, 310);
        loginScreen.setVisible(true);

    }
    
    
    public void addComponentsToPane(Container pane1) throws DHLException {
        if (rightToLeft) {
            pane1.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        }
        
        chooser.setCurrentDirectory(new File("C:/"));
        scrollPane.setPreferredSize(chatScreen.getSize());
        chatScreen.getContentPane().add(scrollPane);
        GridBagLayout g = new GridBagLayout();
	    panel.setLayout(g);
	    panel.setAutoscrolls(true);
	    
	    messageBox = new JTextField(30);
	    messageBox.setText("");
	    messageBox.requestFocusInWindow();
	
	    sendButton = new JButton("Send");
	    sendButton.addActionListener(new SendButtonListener());
	    messageBox.addKeyListener(new SendButtonKeyListener());
	    clearButton = new JButton("Clear");
	    clearButton.addActionListener(new ClearButtonListener());
	    
	    constraints.fill = GridBagConstraints.HORIZONTAL;
	    constraints.weighty = 0.5;
	    constraints.gridx = 0;
	    constraints.gridy = 0;
	    //c.gridwidth = 2;
	   constraints.anchor = GridBagConstraints.PAGE_START; //bottom of space
	   constraints.insets = new Insets(10, 10, 0, 10);
	   chatArea = new JTextArea();
	   ChatResponseController chatResponseController = new ChatResponseController();
	     String response = chatResponseController.getResponse(classifierType.toString(), "hello", precision, isCaseSensitive);
	   chatArea.setText("Prash: " + response);
	   chatLog.append("Prash: " + response + "/n");
	   chatArea.setEditable(false);
	   chatArea.setFont(new Font("Serif", Font.PLAIN, 14));
	   chatArea.setForeground(Color.BLACK);
	   chatArea.setLineWrap(true);
	   
	   chatArea.setPreferredSize(new Dimension(120,40));
	   
	   lastHeight = 40;
	   lastEndHeight = 40;
	   lastStartHeight = 40;
	   chatArea.setBackground(new Color(175, 255, 175));
	    panel.add(chatArea, constraints);
	    
	    if (isWeightX) {
	    constraints.weightx = 0.5;
	    }
	    constraints.fill = GridBagConstraints.HORIZONTAL;
	    constraints.insets = new Insets(10, 10, 10, 0);
	    constraints.gridx = 0;
	    constraints.gridy = 2;
	   constraints.anchor = GridBagConstraints.PAGE_END; //bottom of space
	    panel.add(messageBox, constraints);
	 
	    constraints.fill = GridBagConstraints.HORIZONTAL;
	    constraints.insets = new Insets(10, 10, 10, 0);
	    constraints.weightx = 0.5;
	    constraints.gridx = 1;
	    constraints.gridy = 2;
	    constraints.anchor = GridBagConstraints.PAGE_END; //bottom of space
	    panel.add(sendButton, constraints);
	 
	    constraints.fill = GridBagConstraints.HORIZONTAL;
	    constraints.insets = new Insets(10, 10, 10, 0);
	    constraints.weightx = 0.5;
	    constraints.gridx = 2;
	    constraints.gridy = 2;
	    constraints.anchor = GridBagConstraints.PAGE_END; //bottom of space
	    panel.add(clearButton, constraints);
	    
	    saveButton = new JButton("Save Chat");
	    saveButton.addActionListener(new SaveButtonListener());
	    constraints.fill = GridBagConstraints.HORIZONTAL;
	    constraints.insets = new Insets(10, 10, 10, 0);
	    constraints.weightx = 0.5;
	    constraints.gridx = 3;
	    constraints.gridy = 2;
	    constraints.anchor = GridBagConstraints.PAGE_END; //bottom of space
	    panel.add(saveButton, constraints);
	    
	    micButton = new JButton("Voice query");
	    micButton.addActionListener(new FutureButtonListener());
	    constraints.fill = GridBagConstraints.HORIZONTAL;
	    constraints.insets = new Insets(10, 10, 10, 0);
	    constraints.weightx = 0.5;
	    constraints.gridx = 4;
	    constraints.gridy = 2;
	    constraints.anchor = GridBagConstraints.PAGE_END; //bottom of space
	    panel.add(micButton, constraints);
	    
	    imageButton = new JButton("Image upload");
	    imageButton.addActionListener(new FutureButtonListener());
	    constraints.fill = GridBagConstraints.HORIZONTAL;
	    constraints.insets = new Insets(10, 10, 10, 10);
	    constraints.weightx = 0.5;
	    constraints.gridx = 5;
	    constraints.gridy = 2;
	    constraints.anchor = GridBagConstraints.PAGE_END; //bottom of space
	    panel.add(imageButton, constraints);
    }
 
    private void showScreen() throws DHLException {
    	chatScreen = new JFrame(applicationName);
    	chatScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	chatScreen.setBackground(Color.WHITE);
    	 
        addComponentsToPane(chatScreen.getContentPane());
 
        chatScreen.pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = chatScreen.getSize();
		frameSize.height = screenSize.height;
		frameSize.width = screenSize.width;
		
		chatScreen.setSize(frameSize.width, frameSize.height - 30);
        chatScreen.setSize(900, 310);
        chatScreen.setVisible(true);
    }
 
    public static void main(String[] args) {
    	DHLChatScreen finalOne = new DHLChatScreen();
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                finalOne.showLogin();
            }
        });
    }
    
    public void sendMessage() throws DHLException {
    	String textQuery = null;
   	 	if (messageBox.getText().length() > 0) {
   		 
   		 JTextArea chatArea = new JTextArea();
   	        chatArea.setEditable(false);
   	        chatArea.setFont(new Font("Serif", Font.PLAIN, 14));
   	        chatArea.setLineWrap(true);
   	        int value = messageSplitter( userName + " : " + messageBox.getText());
   	        chatLog.append(userName + " : " + messageBox.getText()+ "/n");
   	        textQuery = messageBox.getText(); 
   	        chatArea.setPreferredSize(new Dimension(150,(25 * value)));
   	        chatArea.setBorder(null);
            chatArea.append( userName + " : " + messageBox.getText() + "\n");
            chatArea.setForeground(Color.BLACK);
            chatArea.setFont(new Font("Serif", Font.PLAIN, 14));
            chatArea.setBackground(Color.WHITE);
            constraints.fill = GridBagConstraints.HORIZONTAL;
            constraints.weighty = lastHeight + (25* value);
            constraints.gridx = 2;       //aligned with button 2
            constraints.gridwidth = 5;   //2 columns wide
            constraints.gridy = 0;
            lastHeight = lastHeight + (25* value);
            lastStartHeight = lastEndHeight + 20;
            lastEndHeight = lastStartHeight + (25 * value);
            constraints.insets = new Insets(lastStartHeight, 10, 20, 10);
           constraints.anchor = GridBagConstraints.PAGE_START; //bottom of space
         
          panel.add(chatArea, constraints);
           
            chatScreen.repaint();
            chatScreen.revalidate();
            
            messageBox.setText("");
        }
        messageBox.requestFocusInWindow();
        sendButton.setEnabled(false);
        getResponse(textQuery);
        sendButton.setEnabled(true);
        JScrollBar sb = scrollPane.getVerticalScrollBar();
        sb.setValue( sb.getMaximum() );
   }
    
    public void getResponse(String testQuery) throws DHLException {
      		 
      		 JTextArea chatArea = new JTextArea();
      	        chatArea.setEditable(false);
      	        chatArea.setFont(new Font("Serif", Font.PLAIN, 14));
      	        chatArea.setLineWrap(true);
      	        ChatResponseController chatResponseController = new ChatResponseController();
      	      String response = chatResponseController.getResponse(classifierType.toString(), testQuery, precision, isCaseSensitive);
      	     int value = messageSplitter(response);
      	     
      	        chatArea.setPreferredSize(new Dimension(120,(25 * value)));
      	        chatArea.setBorder(null);
               chatArea.append( "Prash: " + response);
               chatLog.append("Prash: " + response+ "/n");
               chatArea.setForeground(Color.BLACK);
               chatArea.setFont(new Font("Serif", Font.PLAIN, 14));
               chatArea.setBackground(new Color(175, 255, 175));
               constraints.fill = GridBagConstraints.HORIZONTAL;
               constraints.weighty = lastHeight + (25* value);
               constraints.gridx = 0;       //aligned with button 2
               constraints.gridy = 0;
               constraints.gridwidth = 2;
               lastHeight = lastHeight + (25* value);
               lastStartHeight = lastEndHeight + 20;
               lastEndHeight = lastStartHeight + (25 * value);
               constraints.insets = new Insets(lastStartHeight, 10, 20, 10);
              constraints.anchor = GridBagConstraints.PAGE_START; //bottom of space
            
             panel.add(chatArea, constraints);
              
               chatScreen.repaint();
               chatScreen.revalidate();
               
           messageBox.requestFocusInWindow();
      } 
   
   public int messageSplitter(String message) {
	   int x = 60;
   	if (message != null && message.length() > x) {
   		if (message.length() % x == 0) {
   			return message.length() / x;
   		} else {
   			return message.length() / x + 1 ;
   		}
   		
   	} else {
   		if (message != null) {
   			return message.length() / x == 0 ? 1 : message.length() / x;
   		} else {
   			return 1;
   		}
   	}
   }
   
   class SendButtonListener implements ActionListener {
       public void actionPerformed(ActionEvent event) {
          try {
			sendMessage();
		} catch (DHLException e) {
			e.printStackTrace();
		}
       }
   }
   
   class LoginButtonListener implements ActionListener {
       public void actionPerformed(ActionEvent event) {
    	   try {
			showScreen();
		} catch (DHLException e) {
			e.printStackTrace();
		}
       }
   }

   class ClearButtonListener implements ActionListener {
       public void actionPerformed(ActionEvent event) {
    	   chatLog = new StringBuilder();
    	   chatScreen.setVisible(false);
           chatScreen.dispose();
    	   DHLChatScreen finalOne = new DHLChatScreen();
    	   try {
			finalOne.showScreen();
		} catch (DHLException e) {
			e.printStackTrace();
		}
           messageBox.setText("");
           messageBox.requestFocusInWindow();
       }
   }

   class StartChatButtonListener implements ActionListener {
       public void actionPerformed(ActionEvent event) {
       	startChat();
       }

   }
   
   public void startChat() {
   	userName = userNameTextField.getText();
       if (userName.length() < 1) {
       	 JOptionPane.showMessageDialog(null,
					"Please enter your name", "Invalid input",
					JOptionPane.ERROR_MESSAGE);
       } else {
           loginScreen.setVisible(false);
           chatScreen.setVisible(false);
           chatScreen.dispose();
           DHLChatScreen finalOne = new DHLChatScreen();
           try {
			finalOne.showScreen();
		} catch (DHLException e) {
			e.printStackTrace();
		}
          
       }
   }
   
   class SendButtonKeyListener implements KeyListener {
   
   	public void keyPressed(KeyEvent event) {
   	    if (event.getKeyCode() == KeyEvent.VK_ENTER) {
   	    	 try {
				sendMessage();
			} catch (DHLException e) {
				e.printStackTrace();
			}
   	    }

   	}

		@Override
		public void keyReleased(KeyEvent e) {
			
		}

		@Override
		public void keyTyped(KeyEvent e) {
			
		}
   }
	
   class LoginButtonKeyListener implements KeyListener {
       
   	public void keyPressed(KeyEvent event) {
   	    if (event.getKeyCode() == KeyEvent.VK_ENTER) {
   	    	startChat();
   	    }

   	}

		@Override
		public void keyReleased(KeyEvent e) {
			
		}

		@Override
		public void keyTyped(KeyEvent e) {
			
		}
   }
   
   class FutureButtonListener implements ActionListener {
       public void actionPerformed(ActionEvent event) {
    	   JOptionPane.showMessageDialog(null,
    	   			"This feature will be available in future", "Search not available",
    						JOptionPane.INFORMATION_MESSAGE);
       }
   }
   
   class SaveButtonListener implements ActionListener {
	  
       public void actionPerformed(ActionEvent event) {
    	   try {
    		   int returnVal = chooser.showSaveDialog(null);
    		   if (returnVal == JFileChooser.APPROVE_OPTION) {
    			   
    			   FileWriter filewriter = new FileWriter(chooser.getSelectedFile() +".txt");
	    		   filewriter.write(chatLog.toString());
	    		   filewriter.close();
    		   }
    		} catch ( Exception e) {
    			e.printStackTrace();
    		}
    	   
    	   JOptionPane.showMessageDialog(null,
    	   			"File Saved", "Operation Successful",
    						JOptionPane.INFORMATION_MESSAGE);
       }
   }
   
   
}