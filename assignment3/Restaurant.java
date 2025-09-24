package assignment3;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/*this code represents the UI of an online restaurant system
 *  where orders are added to a collection and passed
 *   to a separate class for handling.
 * */



public class Restaurant extends JFrame implements ActionListener{
	

	
	 ArrayList<String>orderItem =new ArrayList<String>();// arrayList for meal orders
	private JTextArea textArea; 
	
	
	
	public void UI() {
		//Frame setup 
		JFrame frame = new JFrame("Tender Hooks");
		
	frame.setSize(700,700);
		 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	       frame.getContentPane().setBackground(Color.BLACK);
	        
	       //main panel for JFrame
	      JPanel mainPanel = new JPanel(new BorderLayout());
	      mainPanel.setBackground(Color.BLACK);
		
	      
	      
	      JPanel menuBar = new JPanel(new FlowLayout(FlowLayout.CENTER,20,10));
	      menuBar.setBackground(Color.BLACK);
	      JButton tenders = editButton("TENDERS");
	      JButton sandwiches = editButton("SANDWICHES");
	      menuBar.add(tenders);
	      menuBar.add(sandwiches);
	      
	      JPanel contentPanel = new JPanel(new CardLayout());
	      contentPanel.setBackground(Color.BLACK);
	      JPanel tendersPanel = EditTendersPanel();	//method that allows freedom to edit the tenders panel
	      JPanel sandwichPanel = editSandwichPanel();//method to edit the sandwich panel
	      contentPanel.add(tendersPanel,"TENDERS");
	      contentPanel.add(sandwichPanel,"SANDWICHES");
	      
	      
	      
	      
	      JPanel OrderPanel = new JPanel(new BorderLayout());
	      OrderPanel.setBackground(Color.BLACK);
	      textArea = new JTextArea(5,30);
	      textArea.setEditable(false);
	      textArea.setFont(new Font("calibri",Font.PLAIN,14));
        textArea.setForeground(Color.WHITE);
	      textArea.setBackground(Color.BLACK);
	      
	      
	      JScrollPane scrollPane = new JScrollPane(textArea);    
	      JLabel orderlabel = new JLabel("ORDERS",SwingConstants.CENTER);
	      
	      orderlabel.setForeground(Color.YELLOW);
	      orderlabel.setFont(new Font("Arial",Font.BOLD,16));
	      
	      OrderPanel.add(orderlabel,BorderLayout.NORTH);
	      OrderPanel.add(scrollPane,BorderLayout.CENTER);
	      
	      //Menu Button Function
	      tenders.addActionListener((ActionEvent e)->{
	    	  setBold(tenders,sandwiches);                                   //Method that sets one button bold and the other unselected plain
	    	  ((CardLayout) contentPanel.getLayout()).show(contentPanel,"TENDERS");
	      }); 
	      
	      sandwiches.addActionListener((ActionEvent e)->{
	    	  setBold(sandwiches,tenders);
	    	  ((CardLayout) contentPanel.getLayout()).show(contentPanel,"SANDWICHES");
	      }); 
		
	      frame.add(menuBar,BorderLayout.NORTH);
	      frame.add(contentPanel,BorderLayout.CENTER);
	      frame.add(OrderPanel,BorderLayout.SOUTH);
		frame.setVisible(true);
		
		

	}
	
	
	private JPanel EditTendersPanel() { //Private method that edits a panel and returns it to be used in UI
		// TODO Auto-generated method stub
			JPanel panel = editVerticalPanel();
			
			panel.add(makeMiddleLabel("TENDERS & DIPS", 28, Color.YELLOW, true));
		panel.add(Box.createVerticalStrut(10));
			panel.add(makeMiddleLabel("our original crispy chicken tenders are hand breaded & cooked to order,", 14, Color.WHITE, false));
			panel.add(Box.createVerticalStrut(10));
			
			 panel.add(makeItemPanel("3 CRISPY CHICKEN TENDER MEAL & 1 DIP"));
		        panel.add(makeItemPanel("6 CRISPY CHICKEN TENDERS MEAL & 2 DIPS"));
		        return panel;		
		}
		
		private JPanel editSandwichPanel() {
		// TODO Auto-generated method stub
			
			JPanel panel = editVerticalPanel();
			
			panel.add(makeMiddleLabel("SANDWICHES", 28, Color.YELLOW, true));
		panel.add(Box.createVerticalStrut(10));
			panel.add(makeMiddleLabel("our original crispy chicken sandwiches are cooked to order,", 14, Color.WHITE, false));
			panel.add(Box.createVerticalStrut(10));
			
			   panel.add(makeItemPanel("CHIPUFFALO SANDWICH MEAL"));
		        return panel;				
		
	}

		private JButton editButton(String text) { //private method that edits the button layout
		// TODO Auto-generated method stub
			
			JButton button = new JButton(text);
			button.setFocusPainted(false);
			button.setBackground(Color.BLACK);
			button.setForeground(Color.YELLOW);
			button.setFont(new Font("Arial",Font.PLAIN,14) );
			
			return button;
		}
		
		
		
		
		private void setBold(JButton selected, JButton unselected){ //sets button text bold or plain
		// TODO Auto-generated method stub
			
			selected.setFont(new Font("Arial",Font.BOLD,14));
			unselected.setFont(new Font("Arial",Font.PLAIN,14));
			
		}
		
		private JPanel editVerticalPanel() { //constraints the area where text can be used on the panel
		// TODO Auto-generated method stub
			
			JPanel panel = new JPanel();
			panel.setBackground(Color.BLACK);
			panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
			panel.setBorder(BorderFactory.createEmptyBorder(30,30,30,30));
			return panel;
			
			
		}
		
		private JLabel makeMiddleLabel(String text, int size, Color color, boolean bold) { //labels that only display text in the middle of the panel, returns a label 
		// TODO Auto-generated method stub
			
			JLabel label = new JLabel(text, SwingConstants.CENTER);
			label.setFont(new Font("Arial",bold ?/*if true it is made bold if false it is made normal*/ Font.BOLD : Font.PLAIN,size));
			label.setForeground(color);
			label.setAlignmentX(Component.CENTER_ALIGNMENT);
			
			return label;
			
			
		}
		
		private void addToOrder(String name) { // allows the waiter to pass the order to the chef while updating
		// TODO Auto-generated method stub
			synchronized(this){
			orderItem.add(name);
			update();
			notify();		}
		}
		private void update() { //updates the text area whenever a new order is put into the queue
		// TODO Auto-generated method stub
			synchronized(orderItem) {//protected 
			StringBuilder sb = new StringBuilder();
			for (String item : orderItem) {
				sb.append("Order: ").append(item).append("\n");
			}
				textArea.setText(sb.toString());
			}
		}
		
		
		
		private JPanel makeItemPanel(String name) { //method to add button text and layout to the center of the panel 
		// TODO Auto-generated method stub
			
			JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		
		JLabel nameLabel = makeMiddleLabel(name, 18, Color.WHITE, true); //calls method on nameLabel
		
		JButton add = new JButton("add to Order queue");
		 add.setAlignmentX(Component.CENTER_ALIGNMENT);
	        add.setBackground(Color.YELLOW);
	        add.setForeground(Color.BLACK);
	        add.setFocusPainted(false);
	        add.setFont(new Font("Arial", Font.BOLD, 14));
 
	        add.addActionListener(e -> addToOrder(name));
	        
	        panel.add(nameLabel);
	        panel.add(add);
	        
	        return panel;
			
		}
		
		
		public void foodPrepared(String order, int table) {
		    SwingUtilities.invokeLater(() -> {
		        textArea.append("\n Prepared: " + order + " for Table " + table + "\n");
		    });
		}
		
		
	

	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(() -> {
			Restaurant thread = new Restaurant();
					thread.UI(); //makes a thread and calls the restaurant class and allows updates to be shown in realtime
		new Waiter(thread).start(); //starts waiter thread
	});
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	

}
