import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.channels.FileChannel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.AbstractButton;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.AbstractListModel;
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;
import javax.swing.JMenuItem;

import java.awt.Choice;

/**************************
*date:2015-11-23
*Class Name : MusicRegister class
*Procedure : when user register music, execute.
*author :  Jeong hee jin, Hong ji young
***************************/

public class MusicRegister extends JFrame {
	
	private JTextField text_time;
	private JTextField text_tag1;
	private JTextField text_tag2;
	private JTextField text_tag3;
	private JTextField text_title;
	private JTextField text_Artist;
	
	/*radio button group*/
private ButtonGroup  genre;
private ButtonGroup  copyRight;

/*ReleaseDate choice*/
private Choice choice_year;
private Choice choice_day;
private Choice choice_month;

/**************************
*date:2015-12-05
*Function : getRadio  
*input   :  ButtonGroup bg
*Output   : temp_genre
*Procedure : Selected genre radio button group convert String value
*author : Jeong hee jin
***************************/
 public String getRadio(ButtonGroup bg){
	 
	 String temp_genre ="\0"; 
 Enumeration<AbstractButton> enums = bg.getElements();//Get genre radio button value
 
// hasMoreElements() Check for Enum object
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
 *date:2015-12-05
 *Function : insertDB
 *input  para1 :String title
 *       para2 :String artist
 *       para3 :String genre
 *       para4 :String time
 *       para5 :String copyright
 *       para6 :String tag1
 *       para7 :String tag2
 *       para8 :String tag3
 *       para9 :String releaseDate
 *Output   : void
 *Procedure : Music information input Music DB
 *author : Jeong hee jin,Hong ji young
 ***************************/
 public void insertDB(String title,String artist,String genre,String releaseDate,String copyright,String tag1,String tag2,String tag3){ 
	 
	 Connection con = null;
	 String url = "jdbc:mysql://localhost:3306/copyleft"; 
 String dbUser = "root"; 
 String dbPw = "sherry";

 
 Statement stmt = null; 
 String query = "select * from music"; 
 ResultSet rs = null;
 boolean check = true;
 
/*if user register music information, handle exception*/
	 
 if(title.length() == 0){ //case[1]: no input title
  JOptionPane.showMessageDialog(null, "fill in the blank : [title]");
	  check = false;
 }
 if(artist.length() == 0){//case[2]: no input artist
 JOptionPane.showMessageDialog(null, "fill in the blank : [artist]");
	 check = false;
 }
 if(genre.length() == 0){ //case[3]: no input genre
 JOptionPane.showMessageDialog(null, "fill in the blank : [genre]");
	 check = false;
 }

 if(copyright.length() == 0){//case[4]: no input copyright
 JOptionPane.showMessageDialog(null, "fill in the blank : [copyRight]");
	 check = false;
 }
 if(tag1.length() == 0){ //case[5]: no input tag1
 JOptionPane.showMessageDialog(null, "fill in the blank : [tag1]");
	 check = false;
 }
 if(tag2.length() == 0){ //case[6]: no input tag2
 JOptionPane.showMessageDialog(null, "fill in the blank : [tag2]");
	 check = false;
 }
 if(tag3.length() == 0){ //case[7]: no input tag3
 JOptionPane.showMessageDialog(null, "fill in the blank : [tag3]");
	 check = false;
 }
 if(releaseDate.length() == 0){  //case[8]: no input release date
 JOptionPane.showMessageDialog(null, "");
	 check = false;
 }

//if no exception detect, insert music information into db 
 if(check == true){
	 try{
		 Class.forName("org.git.mm.mysql.Driver");
	 System.out.println("");
 }catch(ClassNotFoundException e){
	 System.out.println("exception found");
 }

 
 try{
	 
	 con = DriverManager.getConnection(url,dbUser,dbPw);
	 stmt = con.createStatement(); //for send query
	PreparedStatement pstmt = con.prepareStatement("INSERT INTO music (title, artist, genre, releasedate, copyRight, tag1, tag2, tag3,count) values(?,?,?,?,?,?,?,?,?)" );  
	pstmt.setString(1, title);
	pstmt.setString(2, artist);
	pstmt.setString(3, genre);
	pstmt.setString(4, releaseDate);
	pstmt.setString(5, copyright);
	pstmt.setString(6, tag1);
	pstmt.setString(7, tag2);
	pstmt.setString(8, tag3);
	pstmt.setInt(9,0);
	int n = pstmt.executeUpdate(); 
	
	 rs = stmt.executeQuery(query); 
	 
	 
	if(n>0)
	{
			 JOptionPane.showMessageDialog(null, "register success!");
			
			 Login l = new Login(); 
			 setVisible(false); 
			 l.setSize(450,260); 
			 l.setVisible(true); 
	}
		 else{
			 System.out.println("register fail!");
			 }
	 }
 catch(SQLException e){ //�삁�쇅泥섎━
		 e.printStackTrace();
	 }

 } 
}
 
 /**************************
 *date:2015-12-05
 *Function : MusicRegister  
 *input   :  none
 *Output   : none
 *Procedure : register music
 *author : Jeong hee jin,Hong ji young
 ***************************/
	
public MusicRegister() {
	
	
	getContentPane().setBackground(Color.WHITE);
	getContentPane().setLayout(null);
	
    JLabel lblMusicGenre = new JLabel("Music Genre:");
lblMusicGenre.setFont(new Font("Adobe Garamond Pro Bold", Font.PLAIN, 20));
    lblMusicGenre.setBounds(41, 156, 111, 21);
    getContentPane().add(lblMusicGenre);
 
    JLabel lblArtist = new JLabel("Artist:");
lblArtist.setFont(new Font("Adobe Garamond Pro Bold", Font.PLAIN, 20));
lblArtist.setBounds(41, 108, 57, 25);
getContentPane().add(lblArtist);

JLabel lblReleaseDate = new JLabel("Release Date\r\n");
lblReleaseDate.setFont(new Font("Adobe Garamond Pro Bold", Font.PLAIN, 20));
lblReleaseDate.setBounds(41, 298, 122, 26);
getContentPane().add(lblReleaseDate);

JLabel lblCopyright = new JLabel("CopyRight");
lblCopyright.setFont(new Font("Adobe Garamond Pro Bold", Font.PLAIN, 18));
lblCopyright.setBounds(32, 393, 111, 21);
getContentPane().add(lblCopyright);

JLabel lblInformation = new JLabel("Information:");
lblInformation.setFont(new Font("Adobe Garamond Pro Bold", Font.PLAIN, 20));
lblInformation.setBounds(30, 424, 111, 21);
getContentPane().add(lblInformation);

JButton btnRegister = new JButton("Register");
btnRegister.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent e) {
	
	String title = text_title.getText();
    String artist = text_Artist.getText();
    String Genre = getRadio(genre);
    String CopyRight = getRadio(copyRight);
    String tag1 = text_tag1.getText();
    String tag2 = text_tag2.getText();
    String tag3 = text_tag3.getText();
    String year = choice_year.getItem(choice_year.getSelectedIndex());
    String month = choice_month.getItem(choice_month.getSelectedIndex());
    String day = choice_day.getItem(choice_day.getSelectedIndex());
    String date = year+"."+month+"."+day; //�쓬�븙 諛쒕ℓ�씪

    insertDB(title,artist,Genre,date,CopyRight,tag1,tag2,tag3);
    try 
    {
    	fileLoad(title);
    } 
    catch (IOException e1) 
    {
    	e1.printStackTrace();
	}
    }
});

btnRegister.setBackground(Color.BLACK);
btnRegister.setForeground(Color.WHITE);
btnRegister.setFont(new Font("Adobe Garamond Pro Bold", Font.PLAIN, 20));
btnRegister.setBounds(131, 588, 140, 40);
getContentPane().add(btnRegister);

JButton btnCancel = new JButton("Cancel");
    btnCancel.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) 
    	{
    		setVisible(false);
	    }
});

   btnCancel.setBackground(Color.BLACK);
   btnCancel.setForeground(Color.WHITE);
   btnCancel.setFont(new Font("Adobe Garamond Pro Bold", Font.PLAIN, 20));
   btnCancel.setBounds(312, 588, 122, 41);
   getContentPane().add(btnCancel);

   JLabel lblTag = new JLabel("tag1");
   lblTag.setFont(new Font("Adobe Garamond Pro Bold", Font.BOLD, 20));
   lblTag.setBounds(41, 455, 57, 15);
   getContentPane().add(lblTag);

   JLabel lblTag_1 = new JLabel("tag2");
   lblTag_1.setFont(new Font("Adobe Garamond Pro Bold", Font.PLAIN, 20));
   lblTag_1.setBounds(41, 497, 57, 15);
   getContentPane().add(lblTag_1);

   JLabel lblTag_2 = new JLabel("tag3");
   lblTag_2.setFont(new Font("Adobe Garamond Pro Bold", Font.PLAIN, 20));
   lblTag_2.setBounds(41, 543, 57, 15);
   getContentPane().add(lblTag_2);

   JLabel lblMusicRegister = new JLabel("Music Register");
   lblMusicRegister.setFont(new Font("Adobe Garamond Pro Bold", Font.PLAIN, 20));
   lblMusicRegister.setBounds(222, 23, 164, 21);
   getContentPane().add(lblMusicRegister);

   JLabel lblTitie = new JLabel("Titie:");
   lblTitie.setFont(new Font("Adobe Garamond Pro Bold", Font.PLAIN, 20));
   lblTitie.setBounds(41, 68, 57, 21);
   getContentPane().add(lblTitie);

   choice_year = new Choice();
   choice_year.setBounds(224, 303, 60, 21);
   getContentPane().add(choice_year);

   for(int i=1900;i<2016;i++)
   {
	   String year = String.valueOf(i);
       choice_year.add(year);
    }
		
   JLabel lblYear = new JLabel("Year");
   lblYear.setFont(new Font("Adobe Garamond Pro Bold", Font.PLAIN, 15));
   lblYear.setBounds(188, 306, 57, 15);
   getContentPane().add(lblYear);

   JLabel lblMonth = new JLabel("Month");
   lblMonth.setFont(new Font("Adobe Garamond Pro Bold", Font.PLAIN, 15));
   lblMonth.setBounds(169, 331, 44, 14);
   getContentPane().add(lblMonth);

   choice_month = new Choice();
   choice_month.setBounds(224, 330, 44, 21);
   getContentPane().add(choice_month);

    for(int i=1;i<13;i++)
    {
    	
    	String month = String.valueOf(i);
	    choice_month.add(month);
	 }


   choice_day = new Choice();
   choice_day.setBounds(224, 362, 44, 21);
   getContentPane().add(choice_day);

    for(int i=1;i<32;i++)
    {
    	
    	String day = String.valueOf(i);
	    choice_day.add(day);
	}


   JLabel lblDay = new JLabel("Day");
   lblDay.setFont(new Font("Adobe Garamond Pro Bold", Font.PLAIN, 15));
   lblDay.setBounds(169, 362, 44, 14);
   getContentPane().add(lblDay);

   JRadioButton rdbtnAttribute = new JRadioButton("Attribute");
   rdbtnAttribute.setBounds(165, 403, 121, 23);
   getContentPane().add(rdbtnAttribute);

   JRadioButton rdbtnNotCommercial = new JRadioButton("Not commercial");   
   rdbtnNotCommercial.setBounds(312, 403, 121, 23);
   getContentPane().add(rdbtnNotCommercial);


   copyRight = new ButtonGroup(); 
   copyRight.add(rdbtnAttribute); 
   copyRight.add(rdbtnNotCommercial); 

   text_tag1 = new JTextField();
   text_tag1.setBounds(131, 452, 171, 21);
   getContentPane().add(text_tag1);
   text_tag1.setColumns(10);

   text_tag2 = new JTextField();
   text_tag2.setColumns(10);
   text_tag2.setBounds(131, 494, 171, 21);
   getContentPane().add(text_tag2);

   text_tag3 = new JTextField();
   text_tag3.setColumns(10);
   text_tag3.setBounds(131, 540, 171, 21);
   getContentPane().add(text_tag3);

   JRadioButton genre_None = new JRadioButton("genre_None");
   genre_None.setBounds(169, 155, 121, 23);
   getContentPane().add(genre_None);

   JRadioButton genre_hiphop = new JRadioButton("HIPHOP");
   genre_hiphop.setBounds(313, 155, 121, 23);
   getContentPane().add(genre_hiphop);

   JRadioButton genre_DANCE = new JRadioButton("DANCE");
   genre_DANCE.setBounds(169, 196, 121, 23);
   getContentPane().add(genre_DANCE);

   JRadioButton genre_BALLAD = new JRadioButton("BALLAD");
   genre_BALLAD.setBounds(313, 196, 121, 23);
   getContentPane().add(genre_BALLAD);

   JRadioButton genre_ROCK = new JRadioButton("ROCK");
   genre_ROCK.setBounds(169, 236, 121, 23);
   getContentPane().add(genre_ROCK);

   JRadioButton genre_POP = new JRadioButton("POP");
   genre_POP.setBounds(313, 236, 121, 23);
   getContentPane().add(genre_POP);

   JRadioButton genre_RnB = new JRadioButton("RnB");
   genre_RnB.setBounds(169, 274, 121, 23);
   getContentPane().add(genre_RnB);

    JRadioButton genre_ELECTRIC = new JRadioButton("ELECTRIC");
	genre_ELECTRIC.setBounds(312, 274, 148, 23);
	getContentPane().add(genre_ELECTRIC);
	
	
	genre = new ButtonGroup(); 
	genre.add(genre_None);  
	genre.add(genre_DANCE); 
	genre.add(genre_hiphop); 
	genre.add(genre_RnB); 
	genre.add(genre_ROCK); 
	genre.add(genre_POP); 
	genre.add(genre_BALLAD); 
	genre.add(genre_ELECTRIC); 
	
	text_title = new JTextField();
	text_title.setBounds(155, 68, 263, 21);
	getContentPane().add(text_title);
	text_title.setColumns(10);
	
	text_Artist = new JTextField();
	text_Artist.setBounds(155, 110, 263, 21);
	getContentPane().add(text_Artist);
	text_Artist.setColumns(10);
	
}

/**************************
*date:2015-12-05
*Function : fileLoad
*input   :  String title
*Output   : none
*Procedure : copy client file  
*author : Hong ji young
***************************/
void fileLoad(String title) throws IOException
{

	 FileInputStream inputStream = new FileInputStream("c:\\client\\"+title+".mp3");  //client file inputstream       
 FileOutputStream outputStream = new FileOutputStream("c:\\music\\"+title+".mp3"); //file path for storing client file
   
 FileChannel fcin =  inputStream.getChannel(); //get inputstream channel
 FileChannel fcout = outputStream.getChannel();//get outputstream channel
   
 long size = fcin.size(); //get fcin size
 fcin.transferTo(0, size, fcout); //transfer fcin to fcout
   
 fcout.close(); //close filechannel channel
 fcin.close();//close filechannel channel
   
 outputStream.close(); //close outputStream
 inputStream.close();//close inputStream

}

}


	

