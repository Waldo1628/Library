package Main;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ManageWindow extends JFrame implements ActionListener {
    
	JFrame frame = new JFrame("Manage");
    JTextField first_name = new JTextField(15);
    JTextField last_name = new JTextField(15);
    JTextField ssn = new JTextField(15);
    JTextField address = new JTextField(15);
    JTextField city = new JTextField(15);
    JTextField state = new JTextField(15);
    JTextField phone = new JTextField(15);
    JLabel first_name_text = new JLabel("First Name");
    JLabel last_name_text = new JLabel("Last Name");    
    JLabel ssn_text = new JLabel("SSN");
    JLabel address_text = new JLabel("Street Address");
    JLabel city_text = new JLabel("City");
    JLabel state_text = new JLabel("State");
    JLabel phone_text = new JLabel("Phone number");
    JButton submit = new JButton("Add");
    String ssn_collected;
    String fname_collected;
    String lname_collected;
    String address_collected;
    String city_collected;
    String state_collected;
    String phone_collected;
    GridBagLayout bagLayout = new GridBagLayout();
	GridBagConstraints bagConstraints = new GridBagConstraints();



    ManageWindow(){
    	
    	frame.setLayout(bagLayout);
		bagConstraints.insets = new Insets(15, 40, 0, 0);
		
		//******************************************//
		//			Creating Frame					//
		//******************************************//
		bagConstraints.gridx = 0;
		bagConstraints.gridy = 0;
		frame.add(first_name_text, bagConstraints);
		
		bagConstraints.gridx = 1;
		bagConstraints.gridy = 0;
		frame.add(first_name, bagConstraints);
		
		bagConstraints.gridx = 0;
		bagConstraints.gridy = 1;
		frame.add(last_name_text, bagConstraints);
		
		bagConstraints.gridx = 1;
		bagConstraints.gridy = 1;
		frame.add(last_name, bagConstraints);
		
		bagConstraints.gridx = 0;
		bagConstraints.gridy = 2;
		frame.add(ssn_text, bagConstraints);
		
		bagConstraints.gridx = 1;
		bagConstraints.gridy = 2;
		frame.add(ssn, bagConstraints);
		
		bagConstraints.gridx = 0;
		bagConstraints.gridy = 3;
		frame.add(address_text, bagConstraints);
		
		bagConstraints.gridx = 1;
		bagConstraints.gridy = 3;
		frame.add(address, bagConstraints);
		
		bagConstraints.gridx = 0;
		bagConstraints.gridy = 4;
		frame.add(city_text, bagConstraints);
		
		bagConstraints.gridx = 1;
		bagConstraints.gridy = 4;
		frame.add(city, bagConstraints);
		
		bagConstraints.gridx = 0;
		bagConstraints.gridy = 5;
		frame.add(state_text, bagConstraints);
		
		bagConstraints.gridx = 1;
		bagConstraints.gridy = 5;
		frame.add(state, bagConstraints);
		
		bagConstraints.gridx = 0;
		bagConstraints.gridy = 6;
		frame.add(phone_text, bagConstraints);
		
		bagConstraints.gridx = 1;
		bagConstraints.gridy = 6;
		frame.add(phone, bagConstraints);
		
		bagConstraints.gridx = 2;
		bagConstraints.gridy = 3;
		frame.add(submit, bagConstraints);
		
		//******************************************//
		//			Creating Frame					//
		//******************************************//
		
		submit.addActionListener(this);

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(true);
        frame.setSize(1000,1000);
        frame.setVisible(true);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
    	//Getting all of the users inputs and inserting them into strings
    	if(e.getSource() == submit) {
    		fname_collected = first_name.getText();
    		lname_collected = last_name.getText();
    		ssn_collected = ssn.getText();
    		address_collected = address.getText();
    		city_collected = city.getText();
    		state_collected = state.getText();
    		phone_collected = phone.getText();
    		
    		try {
        		Class.forName("com.mysql.jdbc.Driver");
        		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library?autoReconnect=true", "root", "password");
        		Statement s = conn.createStatement();
        		//Getting the last entry in our borrower table
        		String max = "SELECT MAX(card_id) from BORROWER";
        		String result_sql = "SELECT EXISTS(SELECT Ssn FROM BORROWER WHERE BORROWER.ssn = '" + ssn_collected + "')";
        		
        		ResultSet rs;
        		ResultSet result;
        		int insert;
        		
        		rs = s.executeQuery(max); // Pulls the last entrie in borrower for card id
        		rs.next();
        		String temp = rs.getString(1);
        		int new_cardid = Integer.parseInt(temp);
        		new_cardid += 1; //Max + 1
        		result = s.executeQuery(result_sql);
        		result.next();
        		boolean test = result.getBoolean(1);
        		
        		if(test == true) {
                    JOptionPane.showMessageDialog(null, "Borrower Already exists");
                }
        		//Inserting into borrowers table
                else {
                	address_collected += ", " + city_collected + ", " + state_collected;
                	String bname = fname_collected +" "+ lname_collected; 
                    String insert_stmt = "Insert into BORROWER (card_id, ssn, bname, address, phone) Values ('" + new_cardid + "', '" + ssn_collected + "', '" + bname + "', '"  + address_collected + "', '" + phone_collected + "')";
                    insert = s.executeUpdate(insert_stmt);
                    
                }
        	} catch(Exception e1) {
        		JOptionPane.showMessageDialog(null, e1);
        	}
    	}
    }


}