import java.sql.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

class Login extends JFrame{
	JButton SUBMIT;
	JLabel label1,label2,click;
	JTextField  text1,text2;
 
	Login(){
		setTitle("Login Form");
		setLayout(null);
		label1 = new JLabel();
		label1.setText("Username:");
		text1 = new JTextField(15);

		label2 = new JLabel();
		label2.setText("Password:");
		text2 = new JPasswordField(15);
	
		SUBMIT=new JButton("Login");
		click = new JLabel("Register here!");
		label1.setBounds(350,100,100,20);
		text1.setBounds(450,100,200,20);
		label2.setBounds(350,130,100,20);
		text2.setBounds(450,130,200,20);
		SUBMIT.setBounds(450,160,100,20);
		click.setBounds(450,180,100,30);
		add(label1);
		add(text1);
		add(label2);
		add(text2);
		add(SUBMIT);
		add(click);

		setVisible(true);
		setSize(1024,768);

		SUBMIT.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				String value1=text1.getText();
				String value2=text2.getText();
				try{
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "root");
					String query="select name,Pass from reg where name=? and Pass=?";
					PreparedStatement st=con.prepareStatement(query);
					st.setString(1, value1);
					st.setString(2, value2);
					
					ResultSet rs=st.executeQuery();
					
					if(value1.equals("") && value2.equals("")) {
						JOptionPane.showMessageDialog(null,"Enter login name or password","Error",JOptionPane.ERROR_MESSAGE);
					}
					
					else if(rs.next()){
						Result page=new Result(value1);
						page.setVisible(true);
					}
					
					else{
						JOptionPane.showMessageDialog(null,"Invalid login name or password","Error",JOptionPane.ERROR_MESSAGE);
						text1.setText("");
						text2.setText("");
					}	
					
				}
				catch(Exception e){}
			}
		});
 
		click.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
					Registration page=new Registration();
					page.setVisible(true);
			}
		});
	}
	public static void main(String arg[]){
		new Login();
	}
}