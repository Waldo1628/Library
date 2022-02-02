package Main;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class LoanWindow extends JFrame implements ActionListener {
    JFrame loan_frame = new JFrame("Loan");
    JButton check_out = new JButton("Check out");
    JButton check_in = new JButton("Check in");
    JButton view_loans = new JButton("View Loans");
    GridBagLayout bagLayout = new GridBagLayout();
	GridBagConstraints bagConstraints = new GridBagConstraints();
	String id_input;
	String isbn_input;
	String name_input;
    
    LoanWindow(){ 
    	loan_frame.setLayout(bagLayout);
		bagConstraints.insets = new Insets(15, 40, 0, 0);
		
		bagConstraints.gridx = 0;
		bagConstraints.gridy = 0;
		loan_frame.add(check_out, bagConstraints);
		check_out.addActionListener(this);
		
		bagConstraints.gridx = 0;
		bagConstraints.gridy = 1;
		loan_frame.add(check_in, bagConstraints);
		check_in.addActionListener(this);
		
		bagConstraints.gridx = 0;
		bagConstraints.gridy = 2;
		loan_frame.add(view_loans, bagConstraints);
		view_loans.addActionListener(this);

        loan_frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        loan_frame.setResizable(true);
        loan_frame.setSize(500, 300);
        loan_frame.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
    	if(e.getSource() == check_in) 
    	{
    		loan_frame.dispose();
    		JFrame frame = new JFrame("Check In");
			JLabel id = new JLabel("Card ID: ");
			JLabel isbn = new JLabel("ISBN: ");
			JLabel name = new JLabel("Name: ");
			JTextField id_text = new JTextField(15);
			JTextField isbn_text = new JTextField(15);
			JTextField name_text = new JTextField(15);
			JButton check_in = new JButton("Check In");
			GridBagLayout bagLayout = new GridBagLayout();
	        GridBagConstraints bagConstraints = new GridBagConstraints();
	        frame.setSize(500, 300);
	        frame.setLayout(bagLayout);
	        
	        bagConstraints.gridx = 0;
	        bagConstraints.gridy = 0;
	        frame.add(id, bagConstraints);
	        
	        bagConstraints.gridx = 0;
	        bagConstraints.gridy = 1;
	        frame.add(isbn, bagConstraints);
	        
	        bagConstraints.gridx = 0;
	        bagConstraints.gridy = 2;
	        frame.add(name, bagConstraints);
	        
	        bagConstraints.gridx = 1;
	        bagConstraints.gridy = 0;
	        frame.add(id_text, bagConstraints);
	        
	        bagConstraints.gridx = 1;
	        bagConstraints.gridy = 1;
	        frame.add(isbn_text, bagConstraints);
	        
	        bagConstraints.gridx = 1;
	        bagConstraints.gridy = 2;
	        frame.add(name_text, bagConstraints);
	        
	        bagConstraints.gridx = 0;
	        bagConstraints.gridy = 3;
	        bagConstraints.gridwidth = 5;
	        frame.add(check_in, bagConstraints);
	        
	        frame.setVisible(true);
	        frame.validate();
	        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	        frame.setResizable(true);
	        
	        check_in.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                // TODO Auto-generated method stub
	                if(e.getSource() == check_in) {
	                    id_input = id_text.getText();
	                    isbn_input = isbn_text.getText();
	                    name_input = name_text.getText();
	                    book_check_in(id_input, isbn_input, name_input);
	                }
	            }
	        });   		    		
    	}
    	else if(e.getSource() == check_out)
    	{
    		loan_frame.dispose();
    		JFrame frame = new JFrame("Check Out");
			JLabel id = new JLabel("Card ID: ");
			JLabel isbn = new JLabel("ISBN: ");
			JTextField id_text = new JTextField(15);
			JTextField isbn_text = new JTextField(15);
			JButton check_out = new JButton("Check Out");
			GridBagLayout bagLayout = new GridBagLayout();
	        GridBagConstraints bagConstraints = new GridBagConstraints();
	        frame.setSize(500, 300);
	        frame.setLayout(bagLayout);
	        
	        bagConstraints.gridx = 0;
	        bagConstraints.gridy = 0;
	        frame.add(id, bagConstraints);
	        
	        bagConstraints.gridx = 0;
	        bagConstraints.gridy = 1;
	        frame.add(isbn, bagConstraints);
	        
	        bagConstraints.gridx = 1;
	        bagConstraints.gridy = 0;
	        frame.add(id_text, bagConstraints);
	        
	        bagConstraints.gridx = 1;
	        bagConstraints.gridy = 1;
	        frame.add(isbn_text, bagConstraints);
	        
	        bagConstraints.gridx = 0;
	        bagConstraints.gridy = 2;
	        bagConstraints.gridwidth = 5;
	        frame.add(check_out, bagConstraints);
	        
	        frame.setVisible(true);
	        frame.validate();
	        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	        frame.setResizable(true);	        
	        
	        check_out.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                // TODO Auto-generated method stub
	                if(e.getSource() == check_out) {
	                    id_input = id_text.getText();
	                    isbn_input = isbn_text.getText();
	                    book_check_out(id_input, isbn_input);
	                }
	            }
	        });
    	}
    	else if(e.getSource() == view_loans) 
    	{
    		loan_frame.dispose();
    		JFrame frame = new JFrame("View Loans");
			JLabel id = new JLabel("Card ID: ");
			JLabel isbn = new JLabel("ISBN: ");
			JLabel name = new JLabel("Name: ");
			JTextField id_text = new JTextField(15);
			JTextField isbn_text = new JTextField(15);
			JTextField name_text = new JTextField(15);
			JButton view_loans = new JButton("View Loans");
			GridBagLayout bagLayout = new GridBagLayout();
	        GridBagConstraints bagConstraints = new GridBagConstraints();
	        frame.setSize(500, 300);
	        frame.setLayout(bagLayout);
	        
	        bagConstraints.gridx = 0;
	        bagConstraints.gridy = 0;
	        frame.add(id, bagConstraints);
	        
	        bagConstraints.gridx = 0;
	        bagConstraints.gridy = 1;
	        frame.add(isbn, bagConstraints);
	        
	        bagConstraints.gridx = 0;
	        bagConstraints.gridy = 2;
	        frame.add(name, bagConstraints);
	        
	        bagConstraints.gridx = 1;
	        bagConstraints.gridy = 0;
	        frame.add(id_text, bagConstraints);
	        
	        bagConstraints.gridx = 1;
	        bagConstraints.gridy = 1;
	        frame.add(isbn_text, bagConstraints);
	        
	        bagConstraints.gridx = 1;
	        bagConstraints.gridy = 2;
	        frame.add(name_text, bagConstraints);
	        
	        bagConstraints.gridx = 0;
	        bagConstraints.gridy = 3;
	        bagConstraints.gridwidth = 5;
	        frame.add(view_loans, bagConstraints);
	        
	        frame.setVisible(true);
	        frame.validate();
	        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	        frame.setResizable(true);
	        
	        view_loans.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                // TODO Auto-generated method stub
	                if(e.getSource() == view_loans) {
	                    id_input = id_text.getText();
	                    isbn_input = isbn_text.getText();
	                    name_input = name_text.getText();
	                    view_book_loans(id_input, isbn_input, name_input);
	                }
	            }
	        });   		    		
    	}
    }
    
    //Function to check the loans of a borrower
    //If borrower has more than 3 books checked out do not allow
    public static boolean check_loan(String card_id)
    {
    	boolean output = true;
	    try 
	    {
	    	//connect to database
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library?autoReconnect=true", "root", "password");
			Statement s = conn.createStatement();
			String sql = "SELECT num_loans FROM borrower WHERE card_id='" + card_id + "';";
			ResultSet rs;
			rs = s.executeQuery(sql);
			boolean val = rs.next();
			int loans_amount;
			
			if(val == false)
			{
				JFrame frame = new JFrame();
                if(JOptionPane.showConfirmDialog(frame, "Card Id does not exist.","ERROR", JOptionPane.DEFAULT_OPTION)==JOptionPane.DEFAULT_OPTION)
                	frame.dispose();
			}
			else
			{
				while(val) 
				{
					loans_amount = Integer.parseInt(rs.getString("num_loans"));
					if(loans_amount == 3)
					{
						output = false;
						//output message to say borrower has checked out 3 books already
						JFrame frame = new JFrame();
		                if(JOptionPane.showConfirmDialog(frame, "Borrower has 3 books checked out.","ERROR", JOptionPane.DEFAULT_OPTION)==JOptionPane.DEFAULT_OPTION)
		                	frame.dispose();
					}
					val = rs.next();
				}
			}
	    }catch(Exception e1) 
	    {
	    	JOptionPane.showMessageDialog(null, e1);
	    }
	    
    	return output;
    }
    
    //checks if a book is in the library and if it is checked out 
    public static boolean check_availability(String Isbn){
        Boolean output = false;
        String check;
	   
        try {		
        	//connect to database
    		Class.forName("com.mysql.jdbc.Driver");
    		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library?autoReconnect=true", "root", "password");
    		Statement s = conn.createStatement();
    		
    		String sql="SELECT * FROM book WHERE isbn10 = '"+Isbn+"' ;";
    		ResultSet rs = s.executeQuery(sql);
    		boolean val = rs.next();
    			
    		//No rows for rs.next() to iterate through
    			if(val == false) {
    				System.out.println("Set is empty");
    				output = false;
					//output message to say book was not found in search
					JFrame frame = new JFrame();
	                if(JOptionPane.showConfirmDialog(frame, "Book does not exist in this library.","ERROR", JOptionPane.DEFAULT_OPTION)==JOptionPane.DEFAULT_OPTION)
	                	frame.dispose();
    			}
    			else {
    				while(val) {
    					check = rs.getString("isbn10");
    					//verify ISBN matches
    					if(check.equals(Isbn))
    					{
    						//check if book is checked out
    						if(rs.getInt("book_status") == 0)
    							return true;
    						else
    						{
    							//output message to say book is checked out
    							JFrame frame = new JFrame();
    			                if(JOptionPane.showConfirmDialog(frame, "This book is checked out already","ERROR", JOptionPane.DEFAULT_OPTION)==JOptionPane.DEFAULT_OPTION)
    			                	frame.dispose();
    						}
    						break;
    					}
    					
    					val= rs.next();
    				}
    			}
        }
		catch(Exception e1) {
    		JOptionPane.showMessageDialog(null, e1);
		}
        return output;
    }
    
    public static boolean book_check_out(String card_id, String isbn) 
    {   	
    	boolean output = true;
    	//get current date
    	LocalDate date_out = LocalDate.now();
    	LocalDate due_date = date_out.plusDays(14);
        
    	
        try 
        {
        	//check that borrower does not have 3 books checked out already
    		boolean cond1 = check_loan(card_id);
    		//check that book is in the system and also not check out already
    		boolean cond2 = check_availability(isbn);
    		
    		//connect to database
    		Class.forName("com.mysql.jdbc.Driver");
    		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library?autoReconnect=true", "root", "password");
    		Statement s = conn.createStatement();
    		String sql = "INSERT INTO book_loans(isbn, card_id, date_out, due_date) VALUES('" + isbn + "', " + card_id + ", '" + date_out +"', '" + due_date + "');";
    		
    		//check out book to user
    		if(cond1 && cond2) 
    		{
    			//insert info into loans table
    			s.executeUpdate(sql);
    			output = true;
    			//update borrower's loan amount
				s.executeUpdate("UPDATE borrower SET num_loans = num_loans + 1 WHERE card_id = " + card_id + ";");
				//update book status to true (checked out)
				s.executeUpdate("UPDATE book SET book_status = true WHERE isbn10 = '" + isbn + "';");
				//output message to say book was check out successfully
				JFrame frame = new JFrame();
	            if(JOptionPane.showConfirmDialog(frame, "Book checked out successfully!","ERROR", JOptionPane.DEFAULT_OPTION)==JOptionPane.DEFAULT_OPTION)
	            	frame.dispose();   
    		}
    		else 
    		{
    			output = false;
    			System.out.println("Error: Cannot insert data");
    		}
        }catch(Exception e1) 
        {
        		JOptionPane.showMessageDialog(null, e1);
        }
    	return output;
    }
      
    //book check in
    public static void book_check_in(String card_id, String isbn, String name)
    {
        JFrame check_in_results = new JFrame("Check In Results");
    	JTable table;
        DefaultTableModel defaultTableModel;
        LocalDate date_in = LocalDate.now();
        
        //set layout of frame
        check_in_results.setLayout(new FlowLayout());
        check_in_results.setSize(1000,1000);
        
        //add instructions above table
        JLabel instructions = new JLabel("Click a row to check in that book.");
    	check_in_results.add(instructions);
        //set the properties of jtable and defaulttablemodel
    	defaultTableModel = new DefaultTableModel();
    	table = new JTable(defaultTableModel);
    	table.setPreferredScrollableViewportSize(new Dimension(1000,100));
    	table.setFillsViewportHeight(true);
    	check_in_results.add(new JScrollPane(table));
    	defaultTableModel.addColumn("CARD ID");
    	defaultTableModel.addColumn("ISBN");
    	defaultTableModel.addColumn("DATE OUT");
    	defaultTableModel.addColumn("DUE DATE");
    	
        try {
        	//connect to database
     		Class.forName("com.mysql.jdbc.Driver");
     		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library?autoReconnect=true", "root", "password");
     		Statement s = conn.createStatement();
     		ResultSet rs;
     		String sql;
     		
     		//do not search for ISBN if user input is empty for it
     		if(name.length() > 0)
     		{
     		//execute query
	     		sql = "SELECT * FROM book_loans WHERE card_id = '" + card_id + "'AND date_in IS NULL UNION SELECT * FROM book_loans WHERE isbn LIKE '" + isbn + "'AND date_in IS NULL "
	     				+ "UNION SELECT DISTINCT book_loans.loan_id, book_loans.isbn, book_loans.card_id, book_loans.date_out, book_loans.due_date, book_loans.date_in FROM book_loans "
	     				+ "INNER JOIN borrower ON borrower.card_id=book_loans.card_id WHERE borrower.bname LIKE '%" + name + "%' AND book_loans.date_in IS NULL ;";  
     		}
     		else
     			sql = "SELECT * FROM book_loans WHERE card_id = '" + card_id + "'AND date_in IS NULL UNION SELECT * FROM book_loans WHERE isbn LIKE '" + isbn + "'AND date_in IS NULL;";
     		rs = s.executeQuery(sql);
     		boolean val = rs.next();
			
			if(val == false)
			{
				JFrame frame = new JFrame();
                if(JOptionPane.showConfirmDialog(frame, "No Active Loans Exist for that Search","ERROR", JOptionPane.DEFAULT_OPTION)==JOptionPane.DEFAULT_OPTION)
                	frame.dispose();
			}
			else
			{
	     		//output set in table
	     		while(val) 
	     		{
	     			defaultTableModel.addRow(new Object[] {rs.getString("card_id"), rs.getString("isbn"), rs.getString("date_out"), rs.getString("due_date")});
	     			DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
	                centerRenderer.setHorizontalAlignment( JLabel.CENTER );
	                table.setDefaultRenderer(String.class, centerRenderer);
	                check_in_results.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	                check_in_results.setVisible(true);
	                check_in_results.validate();
	                val = rs.next();
	     		}
	     		//check if user clicked element in table
	     		table.addMouseListener(new MouseAdapter() 
	     		{
	     	         public void mouseClicked(MouseEvent m) 
	     	         {
	     	            if (m.getClickCount() == 1) 
	     	            {
	     	            	//get info from row user clicked
	     	                JTable target = (JTable)m.getSource();
	     	                int row = target.getSelectedRow();
	     	                Object card_id = table.getValueAt(row, 0);
	     	                Object isbn = table.getValueAt(row, 1);
	     	                
	     	                JFrame frame = new JFrame();
	     	                int result = JOptionPane.showConfirmDialog(frame,"Are you sure you want to check in this book?", "", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
	     	                if(result == JOptionPane.YES_OPTION)
							{
	     	                	try {
		     	                	//connect to database
		     	                	Class.forName("com.mysql.jdbc.Driver");
		     	                	Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library?autoReconnect=true", "root", "password");
		     	           			Statement s = conn.createStatement();
		     	           			
		     	           			//update date_ in loans table
									s.executeUpdate("UPDATE book_loans SET date_in = '" + date_in + "' WHERE card_id = '" + card_id.toString() + "' AND isbn = '" + isbn.toString() + "';");
									//update borrower's number of loans
									s.executeUpdate("UPDATE borrower SET num_loans = num_loans - 1 WHERE card_id = " + card_id.toString() + ";");
									//update book's status to checked in
									s.executeUpdate("UPDATE book SET book_status = false WHERE isbn10 = '" + isbn.toString() + "';");
									//close loans table once book is checked out
									check_in_results.dispose();
									JFrame frame2 = new JFrame();
									//output message to say book was checked out
									if(JOptionPane.showConfirmDialog(frame2, "Book checked in successfully!","", JOptionPane.DEFAULT_OPTION)==JOptionPane.DEFAULT_OPTION)
						            	frame2.dispose();
		     	                }catch (Exception e) 
		     	                {
									e.printStackTrace();
								}
							}
	     	                else if(result == JOptionPane.NO_OPTION)
	     	                	frame.dispose();
	     	            }
	     	         }
	     	      });
			}
	    }catch(Exception e1) {
	 		JOptionPane.showMessageDialog(null, e1);
	 	}
    }
    
    //shows all loans for a user including non active loans
    public static void view_book_loans(String card_id, String isbn, String name)
    {
        JFrame view_loans_frame = new JFrame("Check In Results");
    	JTable table;
        DefaultTableModel defaultTableModel;
        
        //set layout of frame
        view_loans_frame.setLayout(new FlowLayout());
        view_loans_frame.setSize(1000,1000);
        
        //set the properties of jtable and defaulttablemodel
    	defaultTableModel = new DefaultTableModel();
    	table = new JTable(defaultTableModel);
    	table.setPreferredScrollableViewportSize(new Dimension(1000,100));
    	table.setFillsViewportHeight(true);
    	view_loans_frame.add(new JScrollPane(table));
    	defaultTableModel.addColumn("CARD ID");
    	defaultTableModel.addColumn("ISBN");
    	defaultTableModel.addColumn("DATE OUT");
    	defaultTableModel.addColumn("DUE DATE");
    	defaultTableModel.addColumn("DATE IN");
    	
        try {
        	//connect to database
     		Class.forName("com.mysql.jdbc.Driver");
     		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library?autoReconnect=true", "root", "password");
     		Statement s = conn.createStatement();
     		ResultSet rs;
     		String sql;
     		
     		//do not search for ISBN if user input is empty for it
     		if(name.length() > 0)
     		{
     		//execute query
	     		sql = "SELECT * FROM book_loans WHERE card_id = '" + card_id + "' UNION SELECT * FROM book_loans WHERE isbn LIKE '" + isbn + "' "
	     				+ "UNION SELECT DISTINCT book_loans.loan_id, book_loans.isbn, book_loans.card_id, book_loans.date_out, book_loans.due_date, book_loans.date_in FROM book_loans "
	     				+ "INNER JOIN borrower ON borrower.card_id=book_loans.card_id WHERE borrower.bname LIKE '%" + name + "%';";  
     		}
     		else
     			sql = "SELECT * FROM book_loans WHERE card_id = '" + card_id + "' UNION SELECT * FROM book_loans WHERE isbn LIKE '" + isbn + "';";
     		rs = s.executeQuery(sql);
     		boolean val = rs.next();
			
			if(val == false)
			{
				JFrame frame = new JFrame();
                if(JOptionPane.showConfirmDialog(frame, "No Active Loans Exist for that Search","ERROR", JOptionPane.DEFAULT_OPTION)==JOptionPane.DEFAULT_OPTION)
                	frame.dispose();
			}
			else
			{
	     		//output set in table
	     		while(val) 
	     		{
	     			defaultTableModel.addRow(new Object[] {rs.getString("card_id"), rs.getString("isbn"), rs.getString("date_out"), rs.getString("due_date"), rs.getString("date_in")});
	     			DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
	                centerRenderer.setHorizontalAlignment( JLabel.CENTER );
	                table.setDefaultRenderer(String.class, centerRenderer);
	                view_loans_frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	                view_loans_frame.setVisible(true);
	                view_loans_frame.validate();
	                val = rs.next();
	     		}
			}
	    }catch(Exception e1) {
	 		JOptionPane.showMessageDialog(null, e1);
	 	}
    }
}