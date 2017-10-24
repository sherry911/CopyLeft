import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
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

public class Start extends JFrame {
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
	public Start() {
		getContentPane().setBackground(Color.WHITE); 
		getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Music Play"); //if user click Music play button, execute event
		//Music play button 
		btnNewButton.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				Login lg = new Login(); //create login object
				lg.setSize(450,260); //set login page size
				lg.setVisible(true); //set login page visible
				
			}
		});
		btnNewButton.setFont(new Font("Adobe Caslon Pro Bold", Font.PLAIN, 20));//Music play button
		btnNewButton.setBackground(Color.BLACK);//Music play button background 
		btnNewButton.setForeground(Color.WHITE);//Music play button foreground
		btnNewButton.setBounds(67, 210, 158, 50); //Music play button bound 
		getContentPane().add(btnNewButton);//Music play button
		
		JButton btnNewButton_1 = new JButton("Music Register");//Music Register button
		//Music Register button 
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MusicRegister mr = new MusicRegister(); //Music Register 
				mr.setSize(800,1000); //Music Register 
				mr.setVisible(true);//Music Register 
			}
		});
		btnNewButton_1.setFont(new Font("Adobe Caslon Pro Bold", Font.PLAIN, 20));//set Music Register font
		btnNewButton_1.setForeground(Color.WHITE);//set Music Register foreground color
		btnNewButton_1.setBackground(Color.BLACK);//set Music Register background color
		btnNewButton_1.setBounds(298, 211, 180, 49);
		getContentPane().add(btnNewButton_1);
		
		JLabel lblCopyleftMusic = new JLabel("Copy Left  Music");//Copy Left  Music label
		lblCopyleftMusic.setFont(new Font("Adobe Garamond Pro Bold", Font.PLAIN, 30)); //Copy Left  Music label 
		lblCopyleftMusic.setBounds(169, 3, 224, 62); //Copy Left  Music label bound 
		getContentPane().add(lblCopyleftMusic);//Copy Left  Music label 
		
		JPanel panel = new JPanel(){
			public void paintComponent(Graphics g){
				ImageIcon ig = new ImageIcon("image.png");
			}
		};
		panel.setBounds(95, 58, 340, 127);
		getContentPane().add(panel);
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
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		Start st = new Start(); //start object create
        st.setSize(555,331); //set start page size
        st.setVisible(true);//start page is visible
        
    
 
   }
}