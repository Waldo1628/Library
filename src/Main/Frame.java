package Main;

import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Frame extends JFrame implements ActionListener{
    JButton search = new JButton("Search");
    JButton loan = new JButton("Loan");
    JButton fine = new JButton("Fine");
    JButton manage = new JButton("Manage");
    JButton exit = new JButton("Exit");
    JLabel label = new JLabel("Welcome to the Library");
    
    Frame(){        
        JPanel panel = new JPanel(new GridLayout(6,1,50,50));
        
        label.setVerticalAlignment(JLabel.TOP);
        label.setHorizontalAlignment(JLabel.CENTER);
        
        panel.add(label);
        panel.add(search);
        panel.add(loan);
        panel.add(fine);
        panel.add(manage);
        panel.add(exit);
        
        search.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                if(e.getSource() == search) {
                    SearchWindow search_window = new SearchWindow();
                }
            }
        });
        
        loan.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                if(e.getSource()== loan ) {
                    LoanWindow loan_window = new LoanWindow();
                }
            }
        });
        manage.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                if(e.getSource() == manage ) {
                    ManageWindow mgmt_window = new ManageWindow();
                }
                
            }
        });
        fine.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                if(e.getSource()== fine ) {
                	FineWindow fine_window = new FineWindow();
                }
            }
        });
        exit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                JFrame frame = new JFrame();
                if(JOptionPane.showConfirmDialog(frame, "Confirm if you want to exit","EXIT", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION) {
                    System.exit(0);
                }
                
            }
        });
        
        loan.addActionListener(this);
        this.setTitle("Main");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setSize(1000,1000);
        
        this.add(panel);
        //this.add(label);
        
        this.pack();
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        
    }
    
    
    
    
}