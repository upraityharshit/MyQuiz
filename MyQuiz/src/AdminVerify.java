import java.awt.*;
import javax.swing.*;
import conn.DatabaseConnect;
import net.proteanit.sql.DbUtils;
import java.sql.*;
import java.awt.event.*;

public class AdminVerify {

	public JFrame frame;
	private JTable table;
	Connection con;
	PreparedStatement ps,ps1,ps2;
	ResultSet rs,rs1,rs2;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminVerify window = new AdminVerify();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public AdminVerify() {
		initialize();
	}

	
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.GRAY);
		frame.setBounds(-8,0, 1400, 850);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		JLabel lblStudentVerification = new JLabel("Student Verification");
		lblStudentVerification.setForeground(Color.ORANGE);
		lblStudentVerification.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 45));
		lblStudentVerification.setBounds(452, 11, 509, 51);
		frame.getContentPane().add(lblStudentVerification);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 60, 1350, 12);
		frame.getContentPane().add(separator);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(45, 174, 1293, 550);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblChooseStudentId = new JLabel("Choose Student Id :");
		lblChooseStudentId.setForeground(Color.ORANGE);
		lblChooseStudentId.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblChooseStudentId.setBounds(45, 99, 205, 30);
		frame.getContentPane().add(lblChooseStudentId);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(309, 97, 214, 32);
		frame.getContentPane().add(comboBox);
		
		DatabaseConnect obj=new DatabaseConnect();
		con=obj.Connect();
		try{
			String a="SELECT checkquestion.resultid,checkquestion.questionid, question.topicid, pickoption ,question.correct FROM quiz.checkquestion,question where checkquestion.questionid = question.questionid and checkquestion.topicid = question.topicid ";
			ps=con.prepareStatement(a);
			rs=ps.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			
			String b="select studentid from student";
			ps1=con.prepareStatement(b);
			rs1=ps1.executeQuery();
			while(rs1.next()){
				comboBox.addItem(rs1.getString(1));
			}
		}
		catch(SQLException e){
			JOptionPane.showMessageDialog(this.frame,"Check Internet Connection");
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(this.frame,"There are Some issue....try again");
		}

		
		JButton btnShow = new JButton("Show");
		btnShow.addActionListener(new ActionListener() {
			private Component frame;

			public void actionPerformed(ActionEvent arg0) {
				try{
					String stl="SELECT checkquestion.resultid,checkquestion.questionid, question.topicid, pickoption ,question.correct FROM quiz.checkquestion,question where checkquestion.questionid = question.questionId and checkquestion.topicid = question.topicid and checkquestion.studentid=?";
					ps2=con.prepareStatement(stl);
					ps2.setString(1,comboBox.getSelectedItem().toString());
					rs2=ps2.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs2));
						
				}
				catch(SQLException e){
					JOptionPane.showMessageDialog(this.frame ,"Check Internet Connection");
				}
				catch(Exception e){
					JOptionPane.showMessageDialog(this.frame,"There are Some issue....try again");
				}
			}
		});
		btnShow.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnShow.setBounds(622, 99, 152, 31);
		frame.getContentPane().add(btnShow);
		
		table.setFont(new Font("Arial", Font.BOLD, 15));
		table.setRowHeight(40);
		table.setShowGrid(true);
		table.setGridColor(Color.red);
		table.setBackground(Color.black);
		table.setForeground(Color.YELLOW);
		table.setSelectionBackground(Color.yellow);
		table.setSelectionForeground(Color.black);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AdminPage window = new AdminPage();
				window.frame.setVisible(true);
				frame.hide();
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnBack.setBounds(853, 99, 141, 31);
		frame.getContentPane().add(btnBack);
		
	}
}
