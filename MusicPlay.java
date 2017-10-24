import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.*;
import com.mysql.jdbc.ResultSetMetaData;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.EtchedBorder;
import javax.swing.UIManager;
import java.awt.Font;
import javax.swing.ListSelectionModel;
/**************************
*date:2015-11-23
*Class Name : MusicPlay class
*Procedure : User can view music all music list, or by rank 
*            and can click viewing recommend music window 
*            And user can add play list music and view it. 
*author : Hong ji young
***************************/
public class MusicPlay extends JFrame {
	private JTextField textField;
	private JTable table;
	private JTable table_1;
	/**************************
	*date:2015-12-05
	*Function : MusicPlay 
	*input   :  none
	*Output   : none
	*Procedure : User can view music all music list, or by rank 
*                and can click viewing recommend music window 
	*author : Hong ji young
	***************************/
	public MusicPlay() {
		getContentPane().setBackground(Color.WHITE); //set pane window color white
		getContentPane().setLayout(null);//set pane layout null
		textField = new JTextField(); //text field for writring search word
		textField.setBounds(249, 39, 274, 21); //set text field bound
		getContentPane().add(textField); //add text field to pane window
		textField.setColumns(10);

		final String [] item = {  //combo box items
				"None",
				"1. BALLAD",
				"2. DANCE",
				"3. RnB",
				"4. HIPHOP",
				"5. ROCK",
				"6. ELECTRIC"};
		final JComboBox comboBox = new JComboBox(item); //make combobox
		comboBox.setBounds(39, 39, 106, 21);//set combobox bound
		getContentPane().add(comboBox); //add combobox to pane window

		//link to mysql 
		try {
			  Class.forName("com.mysql.jdbc.Driver").newInstance(); //link to mysql driver
        	} 
		catch (Exception ex) 
		    {}
        	try{
        	//get connection to jdbc:mysql://localhost:3306/copyleft
        	Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/copyleft" ,"root" ,"sherry");
        	System.out.println("mysql ����"); //check connection 
        	Statement stmt = conn.createStatement(); 
        	//for returning writing result. by executing static sql statement, 
        	 try
        	 {

        		 //get DB contents from data base
        		ResultSet rs = stmt.executeQuery("SELECT * FROM music");
        		
        		final ArrayList<Music> arrlist = new ArrayList<Music>(); //array list for storing db contents

        		while(rs.next()) //storing all db countens in arrlist
        		{
        			Music music = new Music(); //make new Music instance
        			
        			String title =rs.getString("title"); //get title field form db
        			music.setTitle(title); //storing title field form db in music instance
        		
        			String artist =rs.getString("artist");//get artist field form db
        			music.setArtist(artist);//storing artist field form db in music instance

        			String genre =rs.getString("genre");//get genre field form db
        			music.setGenre(genre);//storing genre field form db in music instance

        			String date =rs.getString("releasedate");//get releasedate field form db
        			music.setDate(date);//storing releasedate field form db in music instance
        			
        			String copyRight =rs.getString("copyRight");//get copyright field form db
        			music.setCopy(copyRight);//storing copyright field form db in music instance
        			
        			String tag1 =rs.getString("tag1");//get title tag1 form db
        			music.setTag1(tag1);//storing tag1 field form db in music instance

        			String tag2 =rs.getString("tag2");//get title tag2 form db
        			music.setTag2(tag2);//storing tag2 field form db in music instance
        			
        			String tag3 =rs.getString("tag3");//get title tag3 form db
        			music.setTag3(tag3);//storing tag3 field form db in music instance
        			
        			int count = rs.getInt("count");//get title count form db
        			music.setCount(count);//storing count field form db in music instance

        			arrlist.add(music); //store music instance in arrlist
        			
        		}
        	
        		makeTable(arrlist);  //make all music list table
        		
          		makePopularTable(arrlist); //make top 10 popular music list table
          		
                JButton btnSearch = new JButton("search");  //make button instance named search
                btnSearch.setBounds(557, 38, 106, 22); //set search button bound
        		getContentPane().add(btnSearch); //add search button to window
        	
        		
              //click search button handler
        		btnSearch.addActionListener(new ActionListener() { 
        			public void actionPerformed(ActionEvent e) {
        				String searchWord = textField.getText(); //get text from search textField
        				String genre = item[comboBox.getSelectedIndex()];  //get item from genre combobox
        			
        				if(searchWord.length()>0) //if you search textField length is longer than 0, write searchword
        				{
        					Object[][] data = search(searchWord,genre,arrlist);//get data from search method 
        		
        					String[] index = {"Title","Artist","Genre","ReleaseDate","CopyRight"
        							,"tag1","tag2","tag3"}; //index for table
        					JFrame newFrame = new JFrame("search window"); //new window named search window
        					newFrame.pack();//search window size to be fitted
        					newFrame.setVisible(true);//make search  window visible
        					newFrame.getContentPane().setLayout(null); //set search window pane layout null
        					
        					JLabel lblSearchResult = new JLabel("Search Result"); //add label to search window
        					lblSearchResult.setBounds(32, 70, 101, 15);//set label bound
        					newFrame.getContentPane().add(lblSearchResult);//add label to search window
        					
        					JButton btnAddPlaylist = new JButton("add PlayList"); //make play list button
        					btnAddPlaylist.setBounds(500, 350, 133, 23);//set  add play list button bound
        					newFrame.getContentPane().add(btnAddPlaylist);//add play list button to search window
        					 
                     		JTable table = new JTable(data,index); //make table instance
        					table.setBorder(new LineBorder(new Color(0, 0, 0))); //set table border line
        			  		table.setBounds(39, 113, 388, 195);//set table bound
        					table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); //set table size auto resize off 
        					JScrollPane scroll = new JScrollPane(table); //make scroll pane instance including table
        					scroll.setSize(600, 198);//set scroll pane instance
        					scroll.setLocation(39, 117); //set scroll pane location
        					scroll.setViewportBorder(new LineBorder(new Color(0, 0, 0)));//set scroll border line
        					newFrame.getContentPane().add(scroll); //add scroll to search window
        					newFrame.setSize(800,1000); //set search window size
        					
        				}       				
        			}
        		});
        		
        		
        		JLabel lblTop = new JLabel("Top 10"); //add label named top 10
        		lblTop.setBounds(590, 92, 57, 15); //set label bound
        		getContentPane().add(lblTop); //add label to window pane
        		
        		JLabel lblMusicList = new JLabel("Music List"); //add label named Music List
        		lblMusicList.setBounds(39, 85, 106, 15); //set label bound
        		getContentPane().add(lblMusicList); //add label to window pane
        		
        		
        		JButton btnAddPlayList = new JButton("Add Play List"); //add button named Add Play List
        		btnAddPlayList.setBounds(674, 338, 144, 23); //set button bound
        		getContentPane().add(btnAddPlayList);//add button to window pane
        		
        		JButton btnOpenPlayList = new JButton("Open Play List"); //add button named Open Play List
        		btnOpenPlayList.setBounds(674, 371, 144, 23); //set button bound
        		getContentPane().add(btnOpenPlayList); //add button to window pane
        		

        		JButton btnRecommend = new JButton("Recommend"); //add button named Recommend
        		//add click Recommend button handler
        		btnRecommend.addActionListener(new ActionListener() {
        			public void actionPerformed(ActionEvent e) {
        				Recommend r = new Recommend(arrlist);//make new Recommend instance 
        				r.setSize(800,500);//set Recommend  instance size
        				r.setVisible(true);//set Recommend instance visible
        			}
        		});
        		btnRecommend.setBounds(43, 338, 127, 23); //set Recommend bound
        		getContentPane().add(btnRecommend);//add Recommend button to window pane
     
        	 }
        	finally
        	{
        		//do nothing
        	}
    	} 
        	// handle exception
        	catch (SQLException ex) 
        	{
        		System.out.println("SQLException: " + ex.getMessage());
        	    System.out.println("SQLState: " + ex.getSQLState());
        	    System.out.println("VendorError: " + ex.getErrorCode());
        	}

	}
	/**************************
	*date:2015-12-05
	*Function : search  
	*input   :  String find,String genre,ArrayList<Music> arrlist
	*Output   : Object[][]
	*Procedure : search item in arrlist and insert them in object[][] date and return object[][] date
	*author : Hong ji young
	***************************/
	Object[][] search(String find,String genre,ArrayList<Music> arrlist)
	{
		//make new object[][] date instance for storing data and return it.
  		Object[][] data = new Object[arrlist.size()][8]; 
  		int n=0; //variable for storing next data when search word found
		if(genre == "None") //when you choose genre none,
		{

			for(int i=0;i<arrlist.size();i++) //loop all arrlist
			{
				if(arrlist.get(i).getTitle().contains(find) //when arrlist title contatin find word
				   ||arrlist.get(i).getArtist().contains(find)//when arrlist artist contatin find word
				   ||arrlist.get(i).getGenre().contains(find)//when arrlist genre contatin find word
				   ||arrlist.get(i).getcopyRight().contains(find)//when copyRight title contatin find word
				   ||arrlist.get(i).getDate().contains(find)//when arrlist date contatin find word
				   ||arrlist.get(i).getTag1().contains(find)//when arrlist tag1 contatin find word
				   ||arrlist.get(i).getTag2().contains(find)//when arrlist tag2 contatin find word
				   ||arrlist.get(i).getTag2().contains(find))//when arrlist tag3 contatin find word
				{
					//insert findings in Object[][] data
					data[n][0] = arrlist.get(i).getTitle(); //store title
		  			data[n][1] = arrlist.get(i).getArtist();//store artist
		  			data[n][2] = arrlist.get(i).getGenre();//store genre
		  			data[n][3] = arrlist.get(i).getDate();//store date
		  			data[n][4] = arrlist.get(i).getcopyRight();//store copyRight
		  			data[n][5] = arrlist.get(i).getTag1();//store tag1
		  			data[n][6] = arrlist.get(i).getTag2();//store tag2
		  			data[n][7] = arrlist.get(i).getTag3();//store tag3
					n++; //next object
				}
			}
		}
		
		else//when you choose genre,
		{
			genre = genre.substring(3, 7); 
			//substring genre because it is stored with index number, so cut index number.
			for(int i=0;i<arrlist.size();i++) //loop all arrlist
			{
				if(arrlist.get(i).getGenre().contains(genre))//only when arrlist genre contatin find word
				{
					if(arrlist.get(i).getTitle().contains(find)//when arrlist title contatin find word
					   ||arrlist.get(i).getArtist().contains(find)//when arrlist artist contatin find word
					   ||arrlist.get(i).getcopyRight().contains(find)//when copyRight title contatin find word
					   ||arrlist.get(i).getDate().contains(find)//when arrlist date contatin find word
					   ||arrlist.get(i).getTag1().contains(find)//when arrlist tag1 contatin find word
					   ||arrlist.get(i).getTag2().contains(find)//when arrlist tag2 contatin find word
					   ||arrlist.get(i).getTag2().contains(find))//when arrlist tag3 contatin find word
					{
						data[n][0] = arrlist.get(i).getTitle();//store title
			  			data[n][1] = arrlist.get(i).getArtist();//store artist
			  			data[n][2] = arrlist.get(i).getGenre();//store genre
			  			data[n][3] = arrlist.get(i).getDate();//store date
			  			data[n][4] = arrlist.get(i).getcopyRight();//store copyRight
			  			data[n][5] = arrlist.get(i).getTag1();//store tag1
			  			data[n][6] = arrlist.get(i).getTag2();//store tag2
			  			data[n][7] = arrlist.get(i).getTag3();//store tag3
						n++;	//next object
					}
					
				}
			}
			
		}

	
		return data; //return Object[][] data
	}

	/**************************
	*date:2015-12-05
	*Function : makePopularTable 
	*input   :  ArrayList<Music> arrlist
	*Output   : none
	*Procedure : make top 10 popular music table 
	*author : Hong ji young
	***************************/
	void makePopularTable(ArrayList<Music> arrlist)
	{
		//delete dupliction if exists in array list 
		ArrayList<Music> temp = new ArrayList<Music>(new HashSet<Music>(arrlist)); 
		CountCompare compare = new CountCompare(); //make new compare instance for sorting 
		Collections.sort(temp,compare); //sorting array list by count field by ascending order

		String[] index = {"Rank","Title","Artist"}; //table index
  		Object[][] data = new Object[arrlist.size()][3]; //table contents 

  		//get top 10 popular music from arrlist
  		for(int i=0;i<10;i++)
  		{
  			data[i][0] = i+1; //rank variable
  			data[i][1] = temp.get(i).getTitle(); //get title from arrlist
  			data[i][2] = temp.get(i).getArtist();//get artist from arrlist
  		}
  		
  		table_1 = new JTable(data,index);//make table
		table_1.setBorder(new LineBorder(new Color(0, 0, 0)));//set table border line
		table_1.setBounds(449, 124, 183, 160);//set table bound
		getContentPane().add(table_1);//add table to pane
		table_1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);//set table size AUTO_RESIZE_OFF
		JScrollPane scroll_1 = new JScrollPane(table_1); //make scroll including table
		scroll_1.setSize(238, 172);//set scroll size
		scroll_1.setLocation(580, 117);//set scroll location
		scroll_1.setViewportBorder(new LineBorder(new Color(0, 0, 0)));//set scroll line border
		getContentPane().add(scroll_1);//add scroll to pane
		
	}
	
	
	
	/**************************
	*date:2015-12-05
	*Function : makeTable
	*input   :  ArrayList<Music> arrlist
	*Output   : none
	*Procedure : make all music table 
	*author : Hong ji young
	***************************/
	void makeTable(ArrayList<Music> arrlist)
	{
		String[] index = {"Title","Artist","Genre","ReleaseDate","CopyRight" 
				,"tag1","tag2","tag3"}; //table index
	
  		Object[][] data = new Object[arrlist.size()][8]; //table contents

  	    //get all music from arrlist
  		for(int i=0;i<arrlist.size();i++)
  		{
  			data[i][0] = arrlist.get(i).getTitle();//get title from arrlist
  			data[i][1] = arrlist.get(i).getArtist();//get artist from arrlist
  			data[i][2] = arrlist.get(i).getGenre();//get genre from arrlist
  			data[i][3] = arrlist.get(i).getDate();//get date from arrlist
  			data[i][4] = arrlist.get(i).getcopyRight();//get copyRight from arrlist
  			data[i][5] = arrlist.get(i).getTag1();//get tag1 from arrlist
  			data[i][6] = arrlist.get(i).getTag2();//get tag2 from arrlist
  			data[i][7] = arrlist.get(i).getTag3();//get tag3 from arrlist
  			
  		}

  		JTable table = new JTable(data, index);//make table
  		table.setBorder(new LineBorder(new Color(0, 0, 0)));//set table border line
  		table.setBounds(39, 113, 388, 195);//set table bound
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);//set table size AUTO_RESIZE_OFF
		JScrollPane scroll = new JScrollPane(table);//make scroll including table
		scroll.setSize(529, 198);//set scroll size
		scroll.setLocation(39, 117);//set scroll location
		scroll.setViewportBorder(new LineBorder(new Color(0, 0, 0)));//set scroll line border
		getContentPane().add(scroll);//add scroll to pane
        
	}
	
}



/**************************
*date:2015-11-23
*Class Name : CountCompare
*Procedure : for sorting Music arrlist
*author : Hong ji young
***************************/
class CountCompare implements Comparator<Music>
{
	/**************************
	*date:2015-12-05
	*Function :  compare
	*input   : Music m1,Music m2
	*Output   : int 
	*Procedure : compare by integer magnitude
	*author : Hong ji young
	***************************/
public int compare(Music m1,Music m2)
	{
		if(m1.getCount()>m2.getCount()) //if m1.count is bigger than m2.count
			return -1;//return -1
		else if(m1.getCount()==m2.getCount())//if m1.count is equal to m2.count
			return 0;
		else//if m1.count is smaller than m2.count
			return 1;
	}

}

