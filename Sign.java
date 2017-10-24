import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.ListSelectionModel;
import javax.swing.JRadioButton;

/**************************
*date:2015-11-22
*Class Name : Sign class
*Procedure : when user sign, execute.
*author : Jeong hee jin
***************************/

public class Sign extends JFrame {
	private JTextField text_id;
	private JPasswordField text_pw;
	private ButtonGroup genre;
	
	/**************************
	*date:2015-12-01
	*Function : charToString  
	*input   :  Char[] x
	*Output   : temp
	*Procedure : array of char convert to string
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
		*date:2015-11-28
		*Function : getGenre  
		*input   :  ButtonGroup gr
		*Output   : temp_genre
		*Procedure : selected genre convert string value
		*author : Jeong hee jin
		***************************/
	 public String getGenre(ButtonGroup gr){
		 
		 String temp_genre ="\0"; 
		 Enumeration<AbstractButton> enums = gr.getElements();
		 
		 while(enums.hasMoreElements()){
			 
			 AbstractButton ab = enums.nextElement();
			 
			 JRadioButton jb = (JRadioButton)ab;  
			 
			 if(jb.isSelected()){ 

				 temp_genre = jb.getText(); 
			 }	 
		 }
		 return temp_genre; 

	 }
	 
	 /**************************
		*date:2015-12-01
		*Function : insertDB
		*input   para1: String id
		*        para2: String pw
		*        para3: String genre
		*Output   : void
		*Procedure : User information update in User DB
		*author : Jeong hee jin
		***************************/
	 public void insertDB(String id,String pw,String genre){ 
		 Connection con = null;
		 String url = "jdbc:mysql://localhost:3306/copyleft"; 
		 String dbUser = "root"; 
		 String dbPw = "sherry"; 
	
		 
		 Statement stmt = null; 
		 String query = "select * from user_info"; 
		 ResultSet rs = null;
		 boolean check = true;

			 
			 if(check == true){

				 try{
					 Class.forName("org.git.mm.mysql.Driver");
					 System.out.println("");
				 }catch(ClassNotFoundException e){
					 System.out.println("");
				 }
				 
	
				 
				 try{
					 
					 boolean duplicateId = false; 
					 con = DriverManager.getConnection(url,dbUser,dbPw);
					 stmt = con.createStatement(); //for send query
					 String updateValue = "insert into user_info values('"+id+"',"+"'"+pw+"','"+genre+"')";
					 rs = stmt.executeQuery(query);
					 
					 while(rs.next()){
						 
						 if(rs.getString(1).equals(id)){

							 JOptionPane.showMessageDialog(null, "Id duplication!");
							 duplicateId = true;
						 }
						 
					 }
					 
					 if(duplicateId == false){
						 int n = stmt.executeUpdate(updateValue);
					 
						 if(n>0){
							 JOptionPane.showMessageDialog(null, "register success!");
							
							 Login l = new Login(); 
							 setVisible(false); 
							 l.setSize(450,260); 
							 l.setVisible(true); 
						 }
						 else{
							 System.out.println("register failed!");
						 }
					 }
				 
				 
			 }catch(SQLException e){ 
				 e.printStackTrace();
			 }
		
		 } 
	 }
	  
	 /**************************
		*date:2015-12-01
		*Function : insertDB
		*input   para1: String id
		*        para2: String pw
		*        para3: String genre
		*Output   : void
		*Procedure : User information update in User DB
		*author : Jeong hee jin
		***************************/
	public Sign() {
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setForeground(new Color(0, 0, 0));
		getContentPane().setLayout(null);
		
		/*媛곸쥌 label�뱾*/
		JLabel lblSign = new JLabel("Sign");
		lblSign.setFont(new Font("Adobe Garamond Pro Bold", Font.PLAIN, 25));
		lblSign.setBounds(254, 21, 112, 35);
		getContentPane().add(lblSign);
		
		
		JLabel lblId = new JLabel("ID:");
		lblId.setFont(new Font("Adobe Garamond Pro Bold", Font.PLAIN, 20));
		lblId.setBounds(69, 90, 68, 21);
		getContentPane().add(lblId);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Adobe Garamond Pro Bold", Font.PLAIN, 20));
		lblPassword.setBounds(65, 130, 112, 33);
		getContentPane().add(lblPassword);
		
	
		JButton btnSign = new JButton("Sign");
		btnSign.setFont(new Font("Adobe Garamond Pro Bold", Font.PLAIN, 20));
		btnSign.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
			
				
				String id = text_id.getText(); //�궗�슜�옄 id 
				char[] temp_pw = text_pw.getPassword(); 
				int password = text_pw.getPassword().length;
				String temp_genre = getGenre(genre);
				
				if( id.length() == 0 ){
					 JOptionPane.showMessageDialog(null, "id is blank!");
				 }

				 if(password == 0){
					 JOptionPane.showMessageDialog(null, "pass word is blank!");
				 }
				
				 if(temp_genre.length() == 0){
					 JOptionPane.showMessageDialog(null, "genre word is blank!");
				 }
				 
				 if(password != 0 && id.length() != 0 && temp_genre.length() != 0)
				  {		String pw = charToString(temp_pw); 
					
					System.out.println(id+" "+pw+" "+temp_genre);
					insertDB(id,pw,temp_genre);
				  }
		
				
				
			}
		});
		btnSign.setBackground(Color.BLACK);
		btnSign.setForeground(Color.WHITE);
		btnSign.setBounds(69, 363, 124, 35);
		getContentPane().add(btnSign);
		
		JButton btnNewButton = new JButton("Cancel");
		btnNewButton.setFont(new Font("Adobe Garamond Pro Bold", Font.PLAIN, 20));
		btnNewButton.setBackground(Color.BLACK);
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBounds(266, 363, 124, 35);
		getContentPane().add(btnNewButton);
		
		JLabel label = new JLabel("");
		label.setFont(new Font("Adobe Garamond Pro Bold", Font.PLAIN, 14));
		label.setBounds(38, 194, 57, 15);
		getContentPane().add(label);
		
		JLabel lblGenre = new JLabel("Favorite Genre:");
		lblGenre.setFont(new Font("Adobe Garamond Pro Bold", Font.PLAIN, 19));
		lblGenre.setBounds(69, 173, 130, 36);
		getContentPane().add(lblGenre);
		
		text_id = new JTextField();
		text_id.setBounds(175, 79, 285, 32);
		getContentPane().add(text_id);
		text_id.setColumns(10);
		
		
		//Genre->None
		JRadioButton rd_None = new JRadioButton("None");
		rd_None.setForeground(Color.BLACK);
		rd_None.setBackground(Color.PINK);
		rd_None.setFont(new Font("Adobe Garamond Pro Bold", Font.PLAIN, 15));
		rd_None.setBounds(209, 179, 121, 23);
		getContentPane().add(rd_None);
		
		//Genre->HIPHOP
		JRadioButton rd_HIPHOP = new JRadioButton("HIPHOP");
		rd_HIPHOP.setBackground(Color.PINK);
		rd_HIPHOP.setFont(new Font("Adobe Garamond Pro Bold", Font.PLAIN, 15));
		rd_HIPHOP.setBounds(209, 213, 121, 23);
		getContentPane().add(rd_HIPHOP);
		
		//Genre->ROCK
		JRadioButton rd_ROCK = new JRadioButton("ROCK");
		rd_ROCK.setBackground(Color.PINK);
		rd_ROCK.setFont(new Font("Adobe Garamond Pro Bold", Font.PLAIN, 15));
		rd_ROCK.setBounds(209, 248, 121, 23);
		getContentPane().add(rd_ROCK);
		
		//Genre->BALLAD
		JRadioButton rd_BALLAD = new JRadioButton("BALLAD");
		rd_BALLAD.setBackground(Color.PINK);
		rd_BALLAD.setFont(new Font("Adobe Garamond Pro Bold", Font.PLAIN, 15));
		rd_BALLAD.setBounds(209, 284, 121, 23);
		getContentPane().add(rd_BALLAD);
		
		//Genre->DANCE
		JRadioButton rd_DANCE = new JRadioButton("DANCE");
		rd_DANCE.setBackground(Color.PINK);
		rd_DANCE.setFont(new Font("Adobe Garamond Pro Bold", Font.PLAIN, 15));
		rd_DANCE.setBounds(339, 179, 121, 23);
		getContentPane().add(rd_DANCE);
		
		//Genre->RnB
		JRadioButton rd_RnB = new JRadioButton("RnB");
		rd_RnB.setBackground(Color.PINK);
		rd_RnB.setFont(new Font("Adobe Garamond Pro Bold", Font.PLAIN, 15));
		rd_RnB.setBounds(339, 213, 121, 23);
		getContentPane().add(rd_RnB);
		
		//Genre->POP
		JRadioButton rd_POP = new JRadioButton("POP");
		rd_POP.setBackground(Color.PINK);
		rd_POP.setFont(new Font("Adobe Garamond Pro Bold", Font.PLAIN, 15));
		rd_POP.setBounds(339, 248, 121, 23);
		getContentPane().add(rd_POP);
		
		//Genre->ELECTIRC
		JRadioButton rd_ELECTRIC = new JRadioButton("ELECTRIC");
		rd_ELECTRIC.setBackground(Color.PINK);
		rd_ELECTRIC.setFont(new Font("Adobe Garamond Pro Bold", Font.PLAIN, 15));
		rd_ELECTRIC.setBounds(339, 282, 121, 23);
		getContentPane().add(rd_ELECTRIC);
		
		
		/*Genre radio button�뱾 grouping*/
		genre = new ButtonGroup(); 
		genre.add(rd_None);
		genre.add(rd_HIPHOP);
		genre.add(rd_ROCK);
		genre.add(rd_BALLAD);
		genre.add(rd_DANCE);
		genre.add(rd_RnB);
		genre.add(rd_POP);
		genre.add(rd_ELECTRIC);
		

		JLabel lblEveryListMust = new JLabel("every list must be filled in!!");
		lblEveryListMust.setForeground(Color.RED);
		lblEveryListMust.setFont(new Font("Adobe Garamond Pro Bold", Font.PLAIN, 14));
		lblEveryListMust.setBounds(219, 324, 201, 15);
		getContentPane().add(lblEveryListMust);

		text_pw = new JPasswordField();
		text_pw.setBounds(175, 129, 285, 32);
		getContentPane().add(text_pw);
	}
	
}



