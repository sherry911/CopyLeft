import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.awt.event.ActionEvent;


/**************************
*date:2015-11-22
*Class Name : Login class
*Procedure : when user login, execute.
*author : Jeong hee jin
***************************/

public class Login extends JFrame {
	private JTextField textField; 
	private JPasswordField passwordField; 
	private JPasswordField passwordField_1;
	private JTextField textField_1;
	private JTextField login_id;
	private JPasswordField login_pw;
	
	/**************************
	*date:2015-11-26
	*Function : insertDB
	*input  : char[] x
	*Output : String temp
	*Procedure : array of char convert string
	*author : Jeong hee jin
	***************************/
	
	 public String charToString(char[] x){
		 
		  String temp;
		  temp = x[0]+"";
		  for(int i=0;i<x.length-1;i++){
		   temp+=x[i+1];
		  }
		  return temp;
		 }
	 
	   /**************************
		*date:2015-11-26
		*Function : checkDB
		*input  para1: String id
		*		para2: String pw
		*Output : boolean login
		*Procedure : when user logins, DB check id,password.
		*author : Jeong hee jin
		***************************/
	 
	 public boolean checkDB(String id,String pw){ 
		 
		 Connection con = null;
		 String url = "jdbc:mysql://localhost:3306/copyleft"; //db address
		 String dbUser = "root"; //db user name
		 String dbPw = "sherry";  //db user password
		 boolean login = false; //login flag
		 
		 Statement stmt = null; 
		 String query = "select * from user_info"; //query statement
		 ResultSet rs = null;
		 System.out.println(id+pw);
					 
				 
				 try{
					 Class.forName("org.git.mm.mysql.Driver");
					 System.out.println("");
				 }catch(ClassNotFoundException e){
					 System.out.println("");
				 }
				 try{
					 
					
					 con = DriverManager.getConnection(url,dbUser,dbPw);
					 stmt = con.createStatement(); 
					
					 rs = stmt.executeQuery(query); 
					 
					 while(rs.next()){
						// id,pw are correct
						 if((rs.getString(1).equals(id))&&((rs.getString(2).equals(pw)))){

							 login = true;
							 JOptionPane.showMessageDialog(null, "ID : "+rs.getString(1)+" login success");
							 setVisible(false); //this page is invisible
							 
							 /*show music play page*/
							 MusicPlay mp = new MusicPlay(); //create music play page 
							 mp.setSize(837,468);  //set music play page size
							 mp.setVisible(true); //set music play page visible
							 break;
						 }
						 else{//id,pw are incorrect
						
							 login = false;
						 }
						 
					 }
				
			 }catch(SQLException e){ 
				 e.printStackTrace();
			 }
			//login is false	 
			if(login == false) JOptionPane.showMessageDialog(null, "login failde");
			System.out.println(login);
			return login;
		 } 
	 
	public Login() {
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		
		JLabel lblId = new JLabel("ID");
		lblId.setFont(new Font("Adobe Garamond Pro Bold", Font.PLAIN, 20));
		lblId.setBounds(99, 40, 80, 29);
		getContentPane().add(lblId);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Adobe Garamond Pro Bold", Font.PLAIN, 20));
		lblPassword.setBounds(99, 79, 80, 27);
		getContentPane().add(lblPassword);
		
		login_id = new JTextField();
		login_id.setBounds(191, 40, 144, 25);
		getContentPane().add(login_id);
		login_id.setColumns(10);
	
		
		login_pw = new JPasswordField();
		login_pw.setBounds(191, 82, 150, 25);
		getContentPane().add(login_pw);
		login_pw.setColumns(10);

		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				//id or password is blank, inform it.
				String id = login_id.getText();  //get id from login_id text field
				int password = login_pw.getPassword().length; //get length of password from textfield
				char[] TempPw = login_pw.getPassword(); //get password from text field
				
				//user input no id
				 if( id.length() == 0 ){
					 JOptionPane.showMessageDialog(null, "id is blank!");
				 }
				 
				//user input no password
				 if(password == 0){
					 JOptionPane.showMessageDialog(null, "pass word is blank!");
				 }
				//user input any id,password
				 if(password != 0 && id.length() != 0 )
				  {
					 String pw = charToString(TempPw);
					 //check id,password from db
				     checkDB(id,pw);
				  }
				}
			
		});
		btnLogin.setBackground(Color.BLACK);
		btnLogin.setForeground(Color.WHITE);
		btnLogin.setFont(new Font("Adobe Garamond Pro Bold", Font.PLAIN, 20));
		btnLogin.setBounds(125, 168, 95, 37);
		getContentPane().add(btnLogin);
		
		
		//execute event if user click "sign"button
		JButton btnCancel = new JButton("Sign");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Sign s = new Sign();//create new sign object
				s.setSize(500,470); //set sign page size
				s.setVisible(true); //set sign page visible
				
			}
		});
		btnCancel.setBackground(Color.BLACK);
		btnCancel.setForeground(Color.WHITE);
		btnCancel.setFont(new Font("Adobe Garamond Pro Bold", Font.PLAIN, 20));
		btnCancel.setBounds(232, 168, 114, 37);
		getContentPane().add(btnCancel);
		
	
	}
}
