package Main;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
//import java.sql.Date;
import java.sql.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class SearchWindow extends JFrame implements ActionListener {
    JFrame frame = new JFrame("Search");
    JFrame frame_two = new JFrame("Results");
    JTextField search = new JTextField();
    JLabel search_label = new JLabel("Enter a book or author to search for");
    JButton submit = new JButton();
    JLabel results = new JLabel();
    String submit_collected;
    JTable table;
    DefaultTableModel defaultTableModel;
    //Creating the frame for the search window
    SearchWindow(){
        JPanel panel = new JPanel(new GridLayout(2,2,50,50));
        panel.add(search_label);
        panel.add(search);
        panel.add(submit);
        
        setLayout(new FlowLayout());
        
        submit.addActionListener(new ActionListener() {

            @Override
            //Performing an action whenever the client chooses search
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
            	
                if(e.getSource() == submit) {
                	//Getting user input
                	submit_collected = search.getText();
                	
                	frame_two.setLayout(new FlowLayout());
                	frame_two.setSize(1000,1000);
                	
                	//output instructions message above table
                	JLabel instructions = new JLabel("Click a row to check out that book.");
                	frame_two.add(instructions);
                	//setting the properties of jtable and defaulttablemodel
                	defaultTableModel = new DefaultTableModel();
                	table = new JTable(defaultTableModel);
                	table.setPreferredScrollableViewportSize(new Dimension(1000,100));
                	table.setFillsViewportHeight(true);
                	frame_two.add(new JScrollPane(table));
                	defaultTableModel.addColumn("ISBN10");
                	defaultTableModel.addColumn("ISBN13");
                	defaultTableModel.addColumn("TITLE");
                	//SQL statement to search for input
                	try {
                		Class.forName("com.mysql.cj.jdbc.Driver");
                		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library?autoReconnect=true", "root", "password");
                		//String sql = "SELECT book.isbn10,  authors.name FROM book NATURAL JOIN authors";
                		//String sql = "SELECT book.title, book.isbn10, authors.author_name FROM book natural join authors WHERE (title LIKE '%" + submit_collected +"%') OR isbn10 LIKE '%" + submit_collected + "%' OR author_name LIKE '%" + submit_collected + "%'";
                		String sql = "SELECT * FROM book WHERE title LIKE '%" + submit_collected + " %';";
                		PreparedStatement pstmt = conn.prepareStatement(sql);            		
                		ResultSet rs = pstmt.executeQuery();

                		while(rs.next()) {
                			String isbn10 = rs.getString("isbn10");
                            String isbn13 = rs.getString("isbn13");
                            String title = rs.getString("title");
                            defaultTableModel.addRow(new Object[] {isbn10,isbn13,title});
                            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
                            centerRenderer.setHorizontalAlignment( JLabel.CENTER );
                            table.setDefaultRenderer(String.class, centerRenderer);
                            frame_two.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                            frame_two.setVisible(true);
                            frame_two.validate();
                		}                		
                		
                		//add click functionality to search results table
                		table.addMouseListener(new MouseAdapter() 
                 		{
                 	         public void mouseClicked(MouseEvent m) 
                 	         {
                 	            if (m.getClickCount() == 1) 
                 	            {
                 	            	//get info from row user clicked
                 	                JTable target = (JTable)m.getSource();
                 	                int row = target.getSelectedRow();
                 	                Object isbn = table.getValueAt(row, 0);
                 	                
                 	                //confirm that user wants to check out book first 
                 	                JFrame frame = new JFrame();
                 	                int result = JOptionPane.showConfirmDialog(frame,"Are you sure you want to check out this book?", "", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                 	                if(result == JOptionPane.YES_OPTION)
            						{
                 	                	//create frame to prompt user for card info
	                 	                JFrame check_out_frame = new JFrame("Check Out");
	                 	       			JLabel id = new JLabel("Card ID: ");
	                 	       			JTextField id_text = new JTextField(15);
	                 	       			JButton check_out = new JButton("Check Out");
	                 	       			GridBagLayout bagLayout = new GridBagLayout();
	                 	       	        GridBagConstraints bagConstraints = new GridBagConstraints();
	                 	       	        check_out_frame.setSize(500, 300);
	                 	       	        check_out_frame.setLayout(bagLayout);
	                 	       	        
	                 	       	        bagConstraints.gridx = 0;
	                 	       	        bagConstraints.gridy = 0;
	                 	       	        check_out_frame.add(id, bagConstraints);
	                 	       	        
	                 	       	        bagConstraints.gridx = 1;
	                 	       	        bagConstraints.gridy = 0;
	                 	       	        check_out_frame.add(id_text, bagConstraints);
	                 	       	        
	                 	       	        bagConstraints.gridx = 0;
	                 	       	        bagConstraints.gridy = 2;
	                 	       	        bagConstraints.gridwidth = 5;
	                 	       	        check_out_frame.add(check_out, bagConstraints);
	                 	       	        
	                 	       	    	check_out_frame.setVisible(true);
	                 	       	    	check_out_frame.validate();
	                 	       	    	check_out_frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	                 	       	    	check_out_frame.setResizable(true);	        
	                 	       	        
	                 	       	    	//check out button for check out frame
	                 	       	        check_out.addActionListener(new ActionListener() {
	                 	       	            @Override
	                 	       	            public void actionPerformed(ActionEvent e) {
	                 	       	                // TODO Auto-generated method stub
	                 	       	                if(e.getSource() == check_out) {
		                 	       	                try {
			                	     	                	//connect to database
			                	     	                	Class.forName("com.mysql.jdbc.Driver");
			                	     	                	Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library?autoReconnect=true", "root", "password");
			                	     	           			Statement s = conn.createStatement();
			                	     	           			ResultSet rs;
			                	     	           			String card_id_input = id_text.getText();
			                	     	           			
			                	     	           			//check card_id
			                								rs = s.executeQuery("SELECT bname FROM borrower WHERE card_id='" + card_id_input + "';");
			                								boolean val = rs.next();
			                								
			                								//if there is no matching card id in the database output error message
			                								if(val == false)
			                								{
			                									JFrame frame = new JFrame();
			                					                if(JOptionPane.showConfirmDialog(frame, "Card Id does not exist.","ERROR", JOptionPane.DEFAULT_OPTION)==JOptionPane.DEFAULT_OPTION)
			                					                	frame.dispose();
			                								}
			                								else
			                								{	
			                									//otherwise check out the book with the isbn from the row that was clicked
			                									boolean checkout = LoanWindow.book_check_out(card_id_input, isbn.toString());
			                								}
			                								
		                 	       	                	}catch (Exception e2) 
		                								{
		                 	       	                		e2.printStackTrace();
		                								}
	                 	       	                }
	                 	       	            }
	                 	       	        });
            						}
                 	                else if(result == JOptionPane.NO_OPTION)
                 	                	frame.dispose();
                 	            }
                 	         }
                 	      });
                	} catch(Exception e1) {
                		JOptionPane.showMessageDialog(null, e1);
                	}	
                }
            }
        });

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(panel);
        frame.setResizable(true);
        frame.setSize(1000,1000);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

    }
    
	

    
    

}