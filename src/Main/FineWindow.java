package Main;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;


public class FineWindow extends JFrame implements ActionListener {
	private static final DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    public static String error="",due_date,Loan_id1,amount_status;
    static Date due_datefinal,date_today,date_in;
    public static ArrayList<Float> fine =new ArrayList<Float>();
    public static ArrayList<Float> estimated_fine =new ArrayList<Float>();
    public static ArrayList<String> fine_data =new ArrayList<String>();
    static float fine1,estimated_fine1;
    static HashMap hm=new HashMap();
    public static float fine_amount;
    private static float local_amount;
    float amt;
    String id_text;

    
	JFrame frame;
	JButton fine_button;
	JButton all_fine;
	JButton paid_fines;
	JButton unpaid_fines;
	static JTable table;
	static DefaultTableModel defaultTableModel;
	
    //Creating the initial frame window for the fines
	
	FineWindow(){
		
		frame = new JFrame();
        frame.setTitle("Fine Window");
		calculate_fines();
        //creating object for gridbag
        GridBagLayout bagLayout = new GridBagLayout();
        GridBagConstraints bagConstraints = new GridBagConstraints();
        frame.setSize(500, 300);
        frame.setLayout(bagLayout);
        
        bagConstraints.insets = new Insets(15, 0, 0, 0);//Setting the padding between the components and neighboring components
 
        //Setting the properties for each button needed for the assignment
        fine_button = new JButton("Pay Fine");
        bagConstraints.gridx = 0;
        bagConstraints.gridy = 0;
        frame.add(fine_button, bagConstraints);
        
        all_fine = new JButton("View All Fines");
        paid_fines = new JButton("View paid fines");
        unpaid_fines = new JButton("View unpaid fines");
        
        //Inserting our JFrame objects into the actual frame
        bagConstraints.gridx = 0;
        bagConstraints.gridy = 1;
        frame.add(all_fine, bagConstraints);
        
        bagConstraints.gridx = 0;
        bagConstraints.gridy = 2;
        frame.add(paid_fines, bagConstraints);
        
        bagConstraints.gridx = 0;
        bagConstraints.gridy = 3;
        frame.add(unpaid_fines, bagConstraints);

        
        
        fine_button.addActionListener(this);
        all_fine.addActionListener(this);
        paid_fines.addActionListener(this);
        unpaid_fines.addActionListener(this);
        
        //Making the frame visible
        frame.setVisible(true);
        frame.validate();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(true);
        
        setLayout(new FlowLayout());
	}
	
	@Override
	//This allows for actions to occurred
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == fine_button) {
			JFrame frame = new JFrame("Pay Fines");
			JButton pay = new JButton("Pay");
			JButton updt = new JButton("Update");
			JLabel loan_id = new JLabel("Loan ID:");
			JLabel amount_due = new JLabel("Amount:");
			JTextField text = new JTextField(15);
			JTextField amount = new JTextField(15);
			//Creating the frame for Paying fines
			
			GridBagLayout bagLayout = new GridBagLayout();
	        GridBagConstraints bagConstraints = new GridBagConstraints();
	        frame.setSize(500, 300);
	        frame.setLayout(bagLayout);
	        
	        bagConstraints.gridx = 0;
	        bagConstraints.gridy = 0;
	        frame.add(loan_id, bagConstraints);
	        
	        bagConstraints.gridx = 1;
	        bagConstraints.gridy = 0;
	        frame.add(text, bagConstraints);
	        
	        bagConstraints.gridx = 0;
	        bagConstraints.gridy = 1;
	        frame.add(amount_due, bagConstraints);
	        
	        bagConstraints.gridx = 1;
	        bagConstraints.gridy = 1;
	        frame.add(amount, bagConstraints);
	        
	        bagConstraints.gridx = 0;
	        bagConstraints.gridy = 2;
	        
	        frame.add(pay, bagConstraints);
	        
	        bagConstraints.gridx = 1;
	        bagConstraints.gridy = 2;
	        
	        frame.add(updt, bagConstraints);
	        
	        //Button action listener to pay fines
	        pay.addActionListener(new ActionListener() {

	            @Override
	            public void actionPerformed(ActionEvent e) {
	                // TODO Auto-generated method stub
	            	//Once the user clicks the pay method it will get
	            	//The card_id that the user inputed and the amount to be paid
	            	//The information gotten is then fed into the payment method function
	                if(e.getSource() == pay) {
	                    id_text = text.getText();
	                    amt = Float.parseFloat(amount.getText());
	                    payment_method(id_text, amt);
	                    
	                }
	            }
	            
	        });
	        //User clicks update fines button
	        //Card id and amount are gotten from user
	        //Information gathered is used to update the paid status and update the fine amount
	        
	        updt.addActionListener(new ActionListener() {

	            @Override
	            public void actionPerformed(ActionEvent e) {
	                // TODO Auto-generated method stub
	                if(e.getSource() == pay) {
	                    id_text = text.getText();
	                    amt = Float.parseFloat(amount.getText());
	                    update_paid(id_text, false);
	                    update_fine(id_text, amt);
	                    
	                }
	            }
	            
	        });
	        //Making the frame visible
	        frame.setVisible(true);
	        frame.validate();
	        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	        frame.setResizable(true);
		}
		//Button to view all fines is called
		else if(e.getSource() == all_fine) {
			JFrame frame = new JFrame("All");
			JLabel id = new JLabel("Card ID:");
			JTextField text = new JTextField(15);
			JButton view = new JButton("View");
			GridBagLayout bagLayout = new GridBagLayout();
	        GridBagConstraints bagConstraints = new GridBagConstraints();
	        frame.setSize(500, 300);
	        frame.setLayout(bagLayout);
	        
	        bagConstraints.gridx = 0;
	        bagConstraints.gridy = 0;
	        frame.add(id, bagConstraints);
	        
	        bagConstraints.gridx = 1;
	        bagConstraints.gridy = 0;
	        frame.add(text, bagConstraints);
	        
	        bagConstraints.gridx = 0;
	        bagConstraints.gridy = 1;
	        bagConstraints.gridwidth = 5;
	        frame.add(view, bagConstraints);
	        
	        frame.setVisible(true);
	        frame.validate();
	        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	        frame.setResizable(true);
	        
	        view.addActionListener(new ActionListener() {

	            @Override
	            public void actionPerformed(ActionEvent e) {
	                // TODO Auto-generated method stub
	                if(e.getSource() == view) {
	                    id_text = text.getText();
	                    String i = "all";
	                    display_fine(id_text,i);
	                    
	                }
	            }
	            
	        });
	      //Button for view paid fines is clicked 
		}else if(e.getSource() == paid_fines) {
			JFrame frame = new JFrame("Paid");
			JLabel id = new JLabel("Card ID:");
			JTextField text = new JTextField(15);
			JButton view = new JButton("View");
			GridBagLayout bagLayout = new GridBagLayout();
	        GridBagConstraints bagConstraints = new GridBagConstraints();
	        frame.setSize(500, 300);
	        frame.setLayout(bagLayout);
	        
	        bagConstraints.gridx = 0;
	        bagConstraints.gridy = 0;
	        frame.add(id, bagConstraints);
	        
	        bagConstraints.gridx = 1;
	        bagConstraints.gridy = 0;
	        frame.add(text, bagConstraints);
	        
	        bagConstraints.gridx = 0;
	        bagConstraints.gridy = 1;
	        bagConstraints.gridwidth = 5;
	        frame.add(view, bagConstraints);
	        
	        frame.setVisible(true);
	        frame.validate();
	        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	        frame.setResizable(true);
	        //Pulls up only paid fines
	        view.addActionListener(new ActionListener() {

	            @Override
	            public void actionPerformed(ActionEvent e) {
	                // TODO Auto-generated method stub
	                if(e.getSource() == view) {
	                    id_text = text.getText();
	                    String i = "paid";
	                    display_fine(id_text,i);
	                    
	                }
	            }
	            
	        });
	        //Button for unpaid fines is clicked
		}else if(e.getSource() == unpaid_fines) {
			JFrame frame = new JFrame("Paid");
			JLabel id = new JLabel("Card ID:");
			JTextField text = new JTextField(15);
			JButton view = new JButton("View");
			GridBagLayout bagLayout = new GridBagLayout();
	        GridBagConstraints bagConstraints = new GridBagConstraints();
	        frame.setSize(500, 300);
	        frame.setLayout(bagLayout);
	        
	        bagConstraints.gridx = 0;
	        bagConstraints.gridy = 0;
	        frame.add(id, bagConstraints);
	        
	        bagConstraints.gridx = 1;
	        bagConstraints.gridy = 0;
	        frame.add(text, bagConstraints);
	        
	        bagConstraints.gridx = 0;
	        bagConstraints.gridy = 1;
	        bagConstraints.gridwidth = 5;
	        frame.add(view, bagConstraints);
	        
	        frame.setVisible(true);
	        frame.validate();
	        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	        frame.setResizable(true);
	        //Only uploads the unpaid fines
	        view.addActionListener(new ActionListener() {

	            @Override
	            public void actionPerformed(ActionEvent e) {
	                // TODO Auto-generated method stub
	                if(e.getSource() == view) {
	                    id_text = text.getText();
	                    String i = "unpaid";
	                    display_fine(id_text,i);
	                    
	                }
	            }
	            
	        });
		}
	}
	
	//This function is to calculate the fine amount
    public static void calculate_fines (){

    	Calendar cal = Calendar.getInstance();
    	try {
			 Class.forName("com.mysql.jdbc.Driver");
			 Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library?autoReconnect=true", "root", "password");
			 Statement s = conn.createStatement();
			 String sql=" Select * from book_loans;"  ;
			 ResultSet rs = s.executeQuery(sql);
             
		     while (rs.next()){
		    	 String Isbn=rs.getString("isbn");
                 String Loan_id=rs.getString("loan_id");
                 String card_id=rs.getString("card_id");
                 String date_out=rs.getString("date_out");
                 due_date= rs.getString("due_date");
                 //Calculating fines
                 try{
                	 String date_infake = rs.getString("date_in");
                     fine1=0;estimated_fine1=0;
                     Date today= new Date();
                     cal.setTime(today);
                     date_today=cal.getTime();
                     Date date_in1=sdf.parse(date_infake);
                     Date date_due1=sdf.parse(due_date);                  
                     cal.setTime(date_in1);
                     date_in = cal.getTime();
                     cal.setTime(date_due1);
                     due_datefinal= cal.getTime();
                     
                     if(date_today.after(date_due1)) {
                    	 if(date_in.after(due_datefinal)){
	                    	 long days_case2=daysBetween(date_due1,date_in);
	                    	 estimated_fine1=(float) (days_case2 * 0.25);
	                    	 hm.put(Loan_id, estimated_fine1);
	                    	 estimated_fine.add(estimated_fine1);
	                    	 
	                    	 if(search_fine(Loan_id, card_id)==false){
	                        	 update_fine(Loan_id,(float)hm.get(Loan_id));
	                        	 update_paid(Loan_id,false);    
	                 		 }
	                     }
                     }
                 }
                 catch(NullPointerException e){
                	 Date today= new Date();
                     cal.setTime(today);
                     date_today=cal.getTime();
                     Date date_due1=sdf.parse(due_date);
                     cal.setTime(date_due1);
                     due_datefinal= cal.getTime();
              
                     if(date_today.after(date_due1)) {
                		 long days_case2=daysBetween(date_due1,date_today);
	                     estimated_fine1=(float) (days_case2*0.25);
	                     hm.put(Loan_id, estimated_fine1);
	                     estimated_fine.add(estimated_fine1);
	                     
	                     if(search_fine(Loan_id, card_id)==false){
	                    	 update_fine(Loan_id,(float)hm.get(Loan_id));
	                    	 update_paid(Loan_id,false);    
	             		 }
                	 }
                     
                 }
		     }
    	}catch(Exception e1) {
     		JOptionPane.showMessageDialog(null, e1);
	 	}
                   //pay button   
                   //update button
    }
    //Payment function
    public static void payment_method(String Loan_id,float amount){

    	Calendar cal = Calendar.getInstance();
    	try {
			 Class.forName("com.mysql.jdbc.Driver");
			 Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library?autoReconnect=true", "root", "password");
			 Statement s = conn.createStatement();
			 String sql=" select * from book_loans a INNER JOIN fines b ON a.Loan_id=b.Loan_id where b.Loan_id='"+Loan_id+"';"  ;
			 ResultSet rs = s.executeQuery(sql);
             while (rs.next()){
            	 String Isbn=rs.getString("isbn");
                 String Loan_id1=rs.getString("Loan_id");
                 String card_id=rs.getString("card_id");
                 String date_out=rs.getString("date_out");
                 due_date= rs.getString("due_date");
                 String date_in=rs.getString("date_in");
                 float amt=rs.getFloat("fine_amt");
                          
                 if(!date_in.isEmpty()){
                	 if(amount == amt || amount>=amt){
                		 update_paid(Loan_id,true);
                  
                		 JOptionPane.showMessageDialog(null, "The amount has been paid");
                		 amount_status="The amount has been paid";
                     	}
                	 else if(amount != amt || amount <=amt){
                		 amount_status="The full amount has not  been paid";
                     	}
                  }           
               }
    	}catch(Exception e1) {
     		JOptionPane.showMessageDialog(null, e1);
	 	}
    }
    //Can insert fines here

    public static void insert_fines(String Loan_id,float fine_amt, String card_id){

    	Calendar cal = Calendar.getInstance();
    	try {
			 Class.forName("com.mysql.jdbc.Driver");
			 Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library?autoReconnect=true", "root", "password");
			 Statement s = conn.createStatement();
			 String sql=" insert into fines(loan_id, fine_amt, card_id, paid) values('"+ Loan_id+"' , '"+fine_amt+"' , '" + card_id + "', FALSE );";
             boolean rs=s.execute(sql);
             error="data inserted";
              
    	}catch(Exception e1) {
     		JOptionPane.showMessageDialog(null, e1);
	 	}
    }
    //Update a fine
    public static void update_fine(String Loan_id,float fine_amt){
    	try {
			 Class.forName("com.mysql.jdbc.Driver");
			 Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library?autoReconnect=true", "root", "password");
			 Statement s = conn.createStatement();
			 String sql=" Update fines SET fine_amt='"+fine_amt+"' where loan_id='"+Loan_id+"' ;"  ;

             int rs=s.executeUpdate(sql);
             error="data updated";
    	}catch(Exception e1) {
     		JOptionPane.showMessageDialog(null, e1);
	 	}
    }
    //Update paid status
    public static void update_paid(String Loan_id,Boolean Paid){
    	int paid=1;
     
    	if(Paid == true){
    		paid=1;
    	}
    	else if(Paid== false){
    		paid=0;
    	}
    	try {
			 Class.forName("com.mysql.jdbc.Driver");
			 Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library?autoReconnect=true", "root", "password");
			 Statement s = conn.createStatement();
			 String sql=" Update fines SET Paid= '"+paid+"' where Loan_id='"+Loan_id+"' ;"  ;

			 int rs=s.executeUpdate(sql);
			 error="data updated";
            
    	}catch(Exception e1) {
	     		JOptionPane.showMessageDialog(null, e1);
		 	} 
    }   
    //To search for a fine
    public static boolean search_fine(String Loan_id, String card_id){
        boolean output= false;
        Calendar cal = Calendar.getInstance();
        
		 try {
 			 Class.forName("com.mysql.jdbc.Driver");
 			 Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library?autoReconnect=true", "root", "password");
 			 Statement s = conn.createStatement();
 			 String sql=" Select * from fines where loan_id= '"+Loan_id+"';"  ;
 			 ResultSet rs;
 			 rs = s.executeQuery(sql); 

             boolean  val=(rs.next());
             
             if (val==false){
            	 insert_fines(Loan_id,(float)hm.get(Loan_id), card_id);
            	 output = true;
             }
             else if (val==true){
            	 while(val){
            		 local_amount=rs.getFloat("fine_amt");
            		 error="found";
            		 boolean paid=rs.getBoolean("Paid");
            		 output=paid;
                  
            		 val=rs.next();
                  }
             }
		 	}catch(Exception e1) {
	     		JOptionPane.showMessageDialog(null, e1);
		 	}
           return output;
    }    
	
	//To display fines
	public static void display_fine(String card_num, String input) {
		
		defaultTableModel = new DefaultTableModel();
    	table = new JTable(defaultTableModel);
    	table.setPreferredScrollableViewportSize(new Dimension(1000,1000));
    	table.setFillsViewportHeight(true);
    	JFrame frame_two = new JFrame();
    	frame_two.setLayout(new FlowLayout());
    	frame_two.setSize(1000,100);
    	frame_two.add(new JScrollPane(table));
    	defaultTableModel.addColumn("Loan ID");
    	defaultTableModel.addColumn("Fine Amount");
    	defaultTableModel.addColumn("Card ID");
    	defaultTableModel.addColumn("Paid");
		 try {
 			 Class.forName("com.mysql.jdbc.Driver");
 			 Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library?autoReconnect=true", "root", "password");
 			 Statement s = conn.createStatement();
 			 ResultSet rs;
 			 String sql;
 			 //Depending on the button selected
 			 //SQL statement will change
 			 if(input == "all") {
 				 sql = "SELECT * FROM fines WHERE card_id='" + card_num + "';";
 			 }else if(input == "paid"){
 				 sql = "SELECT * FROM fines WHERE card_id='" + card_num + "' AND paid=TRUE;";
 			 }else {
 				sql = "SELECT * FROM fines WHERE card_id='" + card_num + "' AND paid=FALSE;";
 			 };
 			 
 			 rs = s.executeQuery(sql);
 			 while(rs.next()) {
 				String loan_id = rs.getString("loan_id");
                String fine_amt = rs.getString("fine_amt");
                String card_id = rs.getString("card_id");
                String paid = rs.getString("paid");
                defaultTableModel.addRow(new Object[] {loan_id,fine_amt,card_id,paid});
                DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
                centerRenderer.setHorizontalAlignment( JLabel.CENTER );
                table.setDefaultRenderer(String.class, centerRenderer);
                frame_two.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame_two.setVisible(true);
                frame_two.validate();
 			 }
 			 
 			 
	}catch(Exception e1) {
 		JOptionPane.showMessageDialog(null, e1);
 	}
	}
	//To calc the days in between check out and check in
	public static int daysBetween(Date d1, Date d2){
        return (int)((d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
	}
}