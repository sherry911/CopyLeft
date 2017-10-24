import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.JList;
/**************************
*date:2015-11-23
*Class Name : Recommend
*Procedure : Recommend music by genre,artist,time,copyRight.
*author : Hong ji young
***************************/
public class Recommend extends JFrame {

	ArrayList <Music> arrlist;
	JFrame newFrame = new JFrame("Genre Recommend"); //create window when click genre button
	JFrame newFrame1 = new JFrame("Artist Recommend");//create window when click artist button
	JFrame newFrame3 = new JFrame("CopyRight Recommend");//create window when click copyRight button
	
	/**************************
	*date:2015-12-05
	*Function : Recommend
	*input   :  ArrayList<Music> m
	*Output   : none
	*Procedure : make recommend window and when click button create new instance window.
	*author : Hong ji young
	***************************/
	public Recommend(ArrayList<Music> m) {
		
		arrlist = m; // get arrlist form music play array list 
		getContentPane().setLayout(null); //set window pane layout
		
		JLabel lblRecommend = new JLabel("Recommend"); //make label named Recommned
		lblRecommend.setBounds(12, 58, 118, 26); //set label bound
		getContentPane().add(lblRecommend);//add label to pane window
		
		JButton btnGenre = new JButton("Genre");//make button named genre
		//genre button click handler
		btnGenre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				newFrame.pack();//search genre newframe window size to be fitted
				newFrame.setVisible(true);//set genre newframe window visible
				newFrame.getContentPane().setLayout(null);//add genre newframe window to window pane
				newFrame.setSize(1100,500);//set genre newframe window size
				
				JButton balladButton = new JButton("Ballad");//set button named Ballad
				balladButton.setBounds(12, 28, 121, 23); //set button bound
				newFrame.getContentPane().add(balladButton);//add button to genre newframe window
				//Ballad button click event handler
				balladButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						viewGenre("BALLAD"); 
					}
				});
				
				
				JButton danceButton = new JButton("Dance");//set button named Dance
				danceButton.setBounds(210, 28, 121, 23);//set button bound
				newFrame.getContentPane().add(danceButton);//add button to genre newframe window
				//Dance button click event handler
				danceButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						viewGenre("DANCE");
					}
				});
				
				JButton rnbButton = new JButton("RnB");//set button named RnB
				rnbButton.setBounds(410, 28, 121, 23);//set button bound
				newFrame.getContentPane().add(rnbButton);//add button to genre newframe window
				//RnB button click event handler
				rnbButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						viewGenre("RnB");
					}
				});
				
				JButton hiphopButton = new JButton("HipHop");//set button named HipHop
				hiphopButton.setBounds(610, 28, 121, 23);//set button bound
				newFrame.getContentPane().add(hiphopButton);//add button to genre newframe window
				//HipHop button click event handler
				hiphopButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						viewGenre("HIPHOP");
					}
				});
				
				JButton rockButton = new JButton("Rock");//set button named Rock
				rockButton.setBounds(810, 28, 121, 23);//set button bound
				newFrame.getContentPane().add(rockButton);//add button to genre newframe window
				//Rock button click event handler
				rockButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						viewGenre("ROCK");
					}
				});
				
				JButton elecButton = new JButton("Electric");//set button named Electric
				elecButton.setBounds(1010, 28, 121, 23);//set button bound
				newFrame.getContentPane().add(elecButton);//add button to genre newframe window
				//Electric button click event handler
				elecButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						viewGenre("ELECTRIC");
					}
				});
			 
			
		
			
			}
		});
		btnGenre.setBounds(12, 115, 97, 23);//set genre button bound
		getContentPane().add(btnGenre);//add genre button to window pane
		
		JButton btnArtist = new JButton("Artist");//make button named artist
		//artist button click handler
		btnArtist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				newFrame1.pack();//search artist newframe window size to be fitted
				newFrame1.setVisible(true);//set artist newframe window visible
				newFrame1.getContentPane().setLayout(null);//add artist newframe window to window pane
				newFrame1.setSize(1100,500);//set artist newframe window size
				DefaultListModel DLM = new DefaultListModel(); 
				final JList list = new JList(); //make new list instance
				list.setBounds(12, 30, 146, 210);//set list bound
				
				for (int i = 0; i <arrlist.size(); i++) // loop all list
		        {
					DLM.addElement(arrlist.get(i).getArtist());//add artist from arrlist in list    
		        }
				list.setModel(DLM);  //set list model
				JScrollPane scroll = new JScrollPane(list); //add list to scroll pane
				scroll.setSize(300, 198);//set scroll size
				scroll.setLocation(39, 50);//set scroll location
				list.setVisible(true);//set scroll visible
				newFrame1.getContentPane().add(scroll);//add scroll to artist newframe window
				//artist button click handler
				list.addListSelectionListener(new ListSelectionListener() {
					//Returns whether or not this is one in a series of multiple events, where changes are still being made.
				      public void valueChanged(ListSelectionEvent evt) {
				    	//Returns whether or not this is one in a series of multiple events, where changes are still being made. 
				        if (evt.getValueIsAdjusting())
				          return;
				        System.out.println("Selected from " + arrlist.get(list.getSelectedIndex()).getArtist());
				        viewArtist(arrlist.get(list.getSelectedIndex()).getArtist()); //view artist table
				      }
				    });
	
			}
		});
		btnArtist.setBounds(121, 115, 97, 23); //set genre button bound
		getContentPane().add(btnArtist); //add genre button to window pane

		
		JButton btnTime = new JButton("Time");//make button named time
		//time button click handler
		btnTime.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame newFrame = new JFrame("Time Recommend");
				newFrame.pack();//search time newframe window size to be fitted
				newFrame.setVisible(true);//set time newframe window visible
				newFrame.getContentPane().setLayout(null);//add time newframe window to window pane
				newFrame.setSize(1100,500);//set time newframe window size
			}
		});
		btnTime.setBounds(230, 115, 97, 23);//set Time button bound
		getContentPane().add(btnTime);//add Time button to window pane
		
		JButton btnCopyright = new JButton("CopyRight");//make button named CopyRight
		//CopyRight button click handler
		btnCopyright.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newFrame3.pack();//search copyRight newframe window size to be fitted
				newFrame3.setVisible(true);//set copyRight newframe window visible
				newFrame3.getContentPane().setLayout(null);//add copyRight newframe window to window pane
				newFrame3.setSize(1100,500);//set copyRight newframe window size

				JButton attributeButton = new JButton("Attribution");//make button named Attribution
				attributeButton.setBounds(12, 28, 121, 23); //set Attribution button bound
				newFrame3.getContentPane().add(attributeButton);//add Attribution button to window pane
				//Attribution button click handler
				attributeButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						viewCopyRight("attribution"); //view CopyRight table
					}
				});
				
				
				JButton notButton = new JButton("Not Commercial");//make button named Not Commercial
				notButton.setBounds(210, 28, 121, 23);//set Not Commercial button bound
				newFrame3.getContentPane().add(notButton);//add Not Commercial button to window pane
				//Not Commercial button click handler
				notButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						viewCopyRight("not commercial");//view CopyRight table
					}
				});
			}
		});
		btnCopyright.setBounds(339, 115, 97, 23);//set Copyright button bound
		getContentPane().add(btnCopyright);//add Copyright button to window pane
		

		
		
	}
	/**************************
	*date:2015-12-05
	*Function : viewGenre
	*input   : String genre
	*Output   : none
	*Procedure : view music table which is equal to String genre 
	*author : Hong ji young
	***************************/
    void viewGenre(String genre)
	{
		
		String[] index = {"Title","Artist","Genre","ReleaseDate","CopyRight","tag1","tag2","tag3"};//table index
  		Object[][] data = new Object[arrlist.size()][8];//table contents
  		int n=0; //variable for storing next data when search word found
  		for(int i=0;i<arrlist.size();i++)//loop all arrlist
  		{
  			if(arrlist.get(i).getGenre().contains(genre))//only when arrlist genre contatin genre
  			{
  				
  				data[n][0] = arrlist.get(i).getTitle();//store title
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
		
		JTable table = new JTable(data,index);//make table instance
		table.setBorder(new LineBorder(new Color(0, 0, 0))); //set table border line
  		table.setBounds(39, 113, 388, 195);//set table bound
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);//set table size auto resize off 
		JScrollPane scroll = new JScrollPane(table);//make scroll pane instance including table
		scroll.setSize(600, 198);//set scroll pane instance  size
		scroll.setLocation(39, 117);//set scroll pane location
		scroll.setViewportBorder(new LineBorder(new Color(0, 0, 0)));//set scroll border line
		newFrame.getContentPane().add(scroll);//set genre window size

	}

	/**************************
	*date:2015-12-05
	*Function : viewArtist
	*input   : String artist
	*Output   : none
	*Procedure : view music table which is equal to String artist
	*author : Hong ji young
	***************************/
	void viewArtist(String artist)
	{
		String[] index = {"Title","Artist","Genre","ReleaseDate","CopyRight","tag1","tag2","tag3"};//table index
  		Object[][] data = new Object[arrlist.size()][8];//table contents
  		int n=0;//variable for storing next data when search word found
  		for(int i=0;i<arrlist.size();i++)//loop all arrlist
  		{
  			if(arrlist.get(i).getArtist().contains(artist))//only when arrlist artist contatin artist
  			{
  				System.out.println("list "+arrlist.get(i).getArtist());
  				System.out.println("true");
  				data[n][0] = arrlist.get(i).getTitle();//store title
  			    data[n][1] = arrlist.get(i).getArtist();//store artist
  			    data[n][2] = arrlist.get(i).getGenre();//store genre
  			    data[n][3] = arrlist.get(i).getDate();//store date
  			    data[n][4] = arrlist.get(i).getcopyRight();//store copyRight
  			    data[n][5] = arrlist.get(i).getTag1();//store tag1
  			    data[n][6] = arrlist.get(i).getTag2();//store tag2
  			    data[n][7] = arrlist.get(i).getTag3();//store tag3
  			    
  			    n++;//next object
  			}
  			
  		}
		
		JTable table = new JTable(data,index);//make table instance
		table.setBorder(new LineBorder(new Color(0, 0, 0)));//set table border line
  		table.setBounds(39, 113, 388, 195);//set table bound
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);//set table size auto resize off 
		JScrollPane scroll = new JScrollPane(table);//make scroll pane instance including table
		scroll.setSize(600, 198);//set scroll pane instance  size
		scroll.setLocation(400, 50);//set scroll pane location
		scroll.setViewportBorder(new LineBorder(new Color(0, 0, 0)));//set scroll border line
		newFrame1.getContentPane().add(scroll);//set genre window size
	}
	/**************************
	*date:2015-12-05
	*Function : viewTime
	*input   : String time
	*Output   : none
	*Procedure : view music table which is equal to String time
	*author : Hong ji young
	***************************/
	void viewTime(String time)
	{}
	/**************************
	*date:2015-12-05
	*Function : CopyRight
	*input   : String copy
	*Output   : none
	*Procedure : view music table which is equal to String copy
	*author : Hong ji young
	***************************/
	void viewCopyRight(String copy)
	{
		String[] index = {"Title","Artist","Genre","ReleaseDate","CopyRight","tag1","tag2","tag3"};//table index
  		Object[][] data = new Object[arrlist.size()][8];//table contents
  		int n=0;//variable for storing next data when search word found
  		for(int i=0;i<arrlist.size();i++)//loop all arrlist
  		{
  			if(arrlist.get(i).getcopyRight().contains(copy))//only when arrlist genre contatin copy
  			{

  				data[n][0] = arrlist.get(i).getTitle();//store title
  			    data[n][1] = arrlist.get(i).getArtist();//store artist
  			    data[n][2] = arrlist.get(i).getGenre();//store genre
  			    data[n][3] = arrlist.get(i).getDate();//store date
  			    data[n][4] = arrlist.get(i).getcopyRight();//store copyRight
  			    data[n][5] = arrlist.get(i).getTag1();//store tag1
  			    data[n][6] = arrlist.get(i).getTag2();//store tag2
  			    data[n][7] = arrlist.get(i).getTag3();//store tag3
  			    
  			    n++;//next object
  			}
  			
  		}
		
		JTable table = new JTable(data,index);//make table instance
		table.setBorder(new LineBorder(new Color(0, 0, 0)));//set table border line
  		table.setBounds(39, 113, 388, 195);//set table bound
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);//set table size auto resize off 
		JScrollPane scroll = new JScrollPane(table);//make scroll pane instance including table
		scroll.setSize(600, 198);//set scroll pane instance  size
		scroll.setLocation(39, 117);//set scroll pane location
		scroll.setViewportBorder(new LineBorder(new Color(0, 0, 0)));//set scroll border line
		newFrame3.getContentPane().add(scroll);//set genre window size

		
	}
	
}
