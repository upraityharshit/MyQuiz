


import java.awt.EventQueue;
import java.awt.Font;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;

import net.proteanit.sql.DbUtils;

import javax.swing.*;

import conn.DatabaseConnect;

import java.sql.*;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Verify extends JFrame {

	public JFrame frame;
	private JTable table;
	JLabel l1;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Verify window = new Verify("harshit123","JAVA",1);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	String arg,sbj;
	int curid;
	public Verify(String ar,String sb,int a)
	{
		arg=ar;
		sbj=sb;
		curid=a;
		DatabaseConnect obj=new DatabaseConnect();
		con=obj.Connect();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(-8, 0, 1400, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnLogout = new JButton("LogOut");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainPage window = new MainPage();
				window.frame.setVisible(true);
				frame.hide();
			}
		});
		btnLogout.setForeground(Color.BLUE);
		btnLogout.setBackground(Color.ORANGE);
		btnLogout.setFont(new Font("Arial", Font.BOLD, 18));
		btnLogout.setBounds(1198, 30, 150, 32);
		frame.getContentPane().add(btnLogout);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(104, 185, 1118, 500);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setFont(new Font("Arial", Font.BOLD, 15));
		table.setRowHeight(40);
		table.setShowGrid(true);
		table.setGridColor(Color.red);
		table.setBackground(Color.black);
		table.setForeground(Color.YELLOW);
		table.setSelectionBackground(Color.yellow);
		table.setSelectionForeground(Color.black);
		
		l1=new JLabel("Verification");
		l1.setForeground(Color.YELLOW);
		l1.setBounds(400,50,366,100);
		frame.getContentPane().add(l1);
		l1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 50));
		try{
			String stl="SELECT checkquestion.questionid, question.topicid, pickoption ,question.correct FROM quiz.checkquestion,question where checkquestion.questionid = question.questionId and checkquestion.topicid = question.topicid and checkquestion.studentid=? and checkquestion.topicid=? and checkquestion.resultid=?";
			ps=con.prepareStatement(stl);
			ps.setString(1,arg);
			ps.setString(2,sbj);
			ps.setInt(3,curid);
			rs=ps.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			con.close();	
		}
		catch(SQLException e){
			JOptionPane.showMessageDialog(this.frame,"Check Internet Connection");
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(this.frame,"There are Some issue....try again");
		}
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.RED);
		separator.setBounds(10, 156, 1350, 7);
		frame.getContentPane().add(separator);
		
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(Verify.class.getResource("/images/Success-Wallpaper-hd.jpg")));
		lblNewLabel.setBounds(0, 0, 1412, 780);
		frame.getContentPane().add(lblNewLabel);
	
	}
}