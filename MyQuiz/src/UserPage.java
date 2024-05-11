import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import conn.DatabaseConnect;
import java.sql.*;

public class UserPage {
	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	public JFrame frame;
	private JComboBox comboBox;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserPage window = new UserPage("harshit");
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	String id;
	public UserPage(String ID) {
		id=ID;
		DatabaseConnect obj=new DatabaseConnect();
		con=obj.Connect();
		initialize();
	}
	
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		frame.setBounds(-7,0, 1400, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnEditProfile = new JButton("Change Password");
		btnEditProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UserPassChange window = new UserPassChange(id);
				window.frame.setVisible(true);
			}
		});
		btnEditProfile.setForeground(new Color(250, 250, 210));
		btnEditProfile.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 25));
		btnEditProfile.setBackground(new Color(128, 0, 0));
		btnEditProfile.setBounds(10, 11, 311, 34);
		frame.getContentPane().add(btnEditProfile);
		
		JLabel lblWelcome = new JLabel("WELCOME");
		lblWelcome.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 25));
		lblWelcome.setForeground(new Color(255, 255, 255));
		lblWelcome.setBounds(943, 15, 153, 30);
		frame.getContentPane().add(lblWelcome);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 25));
		lblNewLabel.setBounds(1106, 15, 235, 30);
		frame.getContentPane().add(lblNewLabel);
		
		try{
			String str="select name from student where studentid=?";
			ps=con.prepareStatement(str);
			ps.setString(1,id);
			rs=ps.executeQuery();
			if(rs.next())
			{
				lblNewLabel.setText(rs.getString(1).toUpperCase());
			}
			con.close();
		}
		catch(SQLException e){
			JOptionPane.showMessageDialog(this.frame,"Check Internet Connection");
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(this.frame,"There are Some issue....try again");
		}
		
		JLabel lblSelectCourse = new JLabel("SELECT COURSE");
		lblSelectCourse.setForeground(new Color(255, 99, 71));
		lblSelectCourse.setFont(new Font("Algerian", Font.BOLD | Font.ITALIC, 45));
		lblSelectCourse.setBounds(480, 152, 377, 41);
		frame.getContentPane().add(lblSelectCourse);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(255, 0, 0));
		separator.setBounds(461, 199, 410, 9);
		frame.getContentPane().add(separator);
		
		comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 20));
		comboBox.setBounds(549, 256, 241, 30);
		frame.getContentPane().add(comboBox);
		fillid();
		
		JButton btnStartTest = new JButton("Start Test");
		btnStartTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new TestPage(comboBox.getSelectedItem().toString(),id);
				frame.hide();
			}
		});
		btnStartTest.setFont(new Font("Tahoma", Font.BOLD, 23));
		btnStartTest.setBackground(new Color(128, 0, 0));
		btnStartTest.setForeground(new Color(253, 245, 230));
		btnStartTest.setBounds(593, 335, 174, 41);
		frame.getContentPane().add(btnStartTest);
		
		JLabel lblInstruction = new JLabel("Instruction :-");
		lblInstruction.setForeground(Color.YELLOW);
		lblInstruction.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 25));
		lblInstruction.setBounds(137, 445, 215, 30);
		frame.getContentPane().add(lblInstruction);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(new Color(255, 0, 0));
		separator_1.setBounds(114, 486, 1200, 11);
		frame.getContentPane().add(separator_1);
		
		JLabel lblthereAre = new JLabel("1.There are 10  Question in each Course.");
		lblthereAre.setForeground(new Color(0, 255, 127));
		lblthereAre.setFont(new Font("Tahoma", Font.ITALIC, 18));
		lblthereAre.setBounds(137, 518, 653, 30);
		frame.getContentPane().add(lblthereAre);
		
		JLabel lblmaximumTime = new JLabel("2.Maximum Time 5 minute.");
		lblmaximumTime.setForeground(new Color(0, 255, 127));
		lblmaximumTime.setFont(new Font("Tahoma", Font.ITALIC, 18));
		lblmaximumTime.setBounds(137, 567, 667, 34);
		frame.getContentPane().add(lblmaximumTime);
		
		JLabel lblafterTimeupYou = new JLabel("3.After TimeUp You will automatically Logged Out");
		lblafterTimeupYou.setForeground(new Color(0, 255, 127));
		lblafterTimeupYou.setFont(new Font("Tahoma", Font.ITALIC, 18));
		lblafterTimeupYou.setBounds(137, 619, 653, 30);
		frame.getContentPane().add(lblafterTimeupYou);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBackground(Color.RED);
		separator_2.setBounds(937, 56, 377, 2);
		frame.getContentPane().add(separator_2);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setForeground(Color.RED);
		separator_3.setBounds(114, 675, 1017, 9);
		frame.getContentPane().add(separator_3);
		
		JButton btnLogout = new JButton("LogOut");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainPage window = new MainPage();
				window.frame.setVisible(true);
				frame.hide();
			}
		});
		btnLogout.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnLogout.setBackground(Color.GRAY);
		btnLogout.setForeground(Color.GREEN);
		btnLogout.setBounds(1141, 652, 173, 34);
		frame.getContentPane().add(btnLogout);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon(UserPage.class.getResource("/images/wallpaper2you_94367.jpg")));
		lblNewLabel_1.setBounds(0, 0, 1381, 766);
		frame.getContentPane().add(lblNewLabel_1);
		
		
	}
	public void fillid()
	{
		try
		{
			DatabaseConnect obj=new DatabaseConnect();
			con=obj.Connect();
			String str="select topic from topic";
			ps=con.prepareStatement(str);
			rs=ps.executeQuery();
			while(rs.next())
			{
				comboBox.addItem(rs.getString(1).toString().toUpperCase());
			}
			con.close();
		}
		catch(SQLException e){
			JOptionPane.showMessageDialog(this.frame,"Check Internet Connection");
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(this.frame,"There are Some issue....try again");
		}
	}
	
	
}

