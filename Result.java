import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Result extends JFrame implements ActionListener 
{
	    JLabel l1;
	    JButton btn1,btn2;
		JTextArea area;
		private String uname;
	Result(String stat)
	{
		this.uname=stat;
		
		setVisible(true);
        setSize(700, 700);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Employee Information");
		
		l1 = new JLabel("Employee Details:");
        l1.setForeground(Color.blue);
        l1.setFont(new Font("Serif", Font.BOLD, 20));
		
		btn1 = new JButton("Resign");
		btn2 = new JButton("Exit");
		
		btn1.addActionListener(this);
		btn2.addActionListener(this);
		
		l1.setBounds(100, 30, 400, 30);
        btn1.setBounds(50, 350, 100, 30);
		btn2.setBounds(170, 350, 100, 30);
		
		area=new JTextArea();  
        area.setBounds(50,80, 200,200);  
        add(area);  
		
		add(l1);
		add(btn1);
		add(btn2);
		try{
		Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "root");
					Statement st=con.createStatement();
					ResultSet rs=st.executeQuery("select * from reg where name='"+uname+"'");
					while(rs.next()){
						String name = rs.getString("name");
						String email = rs.getString("EmailID");
						String phone = rs.getString("PhoneNo");
						String post = rs.getString("post");
						String salary = rs.getString("Salary");
						String address = rs.getString("address");
						area.setText("Name - "+name+"\n\n"+"EmailID - "+email+"\n\n"+"PhoneNo - "+phone+"\n\n"+"Post - "+post+"\n\n"+"Salary - "+salary+"\n\n"+"Address - "+address);
					}
		}
		catch(Exception e){}	
	}
	public void actionPerformed(ActionEvent ae) 
     { 
		if (ae.getSource() == btn1)
		{
			int x=0;
			try{
			Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "root");
					PreparedStatement ps = con.prepareStatement("delete from reg where name=?");
					ps.setString(1,uname);
					ps.executeUpdate();
					x++;
                    if (x > 0) 
                    {
                        JOptionPane.showMessageDialog(btn1, "Data Deleted Successfully");
                    }
			}
			catch(Exception e){}
		}
		if(ae.getSource() == btn2)
		{
			System.exit(0);
		}
		
	 }
	
  }