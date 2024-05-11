import java.awt.*;
import javax.swing.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;
import java.awt.event.*;
import conn.DatabaseConnect;

public class ResultReport {

	public JFrame frame;
	Connection con;
	PreparedStatement ps,ps1;
	ResultSet rs,rs1;
	private JTable table;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ResultReport window = new ResultReport();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ResultReport() {
		DatabaseConnect obj=new DatabaseConnect();
		con=obj.Connect();
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(-8, 0, 1400, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().setBackground(Color.GRAY);
		
		JLabel lblStudentsReport = new JLabel("Student's Result");
		lblStudentsReport.setForeground(Color.GREEN);
		lblStudentsReport.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 50));
		lblStudentsReport.setBounds(504, 11, 478, 57);
		frame.getContentPane().add(lblStudentsReport);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.RED);
		separator.setBounds(0, 79, 1370, 8);
		frame.getContentPane().add(separator);
		
		JLabel lblChooseStudentId = new JLabel("Choose Student ID : ");
		lblChooseStudentId.setForeground(Color.RED);
		lblChooseStudentId.setFont(new Font("Tahoma", Font.ITALIC, 25));
		lblChooseStudentId.setBounds(38, 112, 249, 33);
		frame.getContentPane().add(lblChooseStudentId);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(319, 109, 255, 36);
		frame.getContentPane().add(comboBox);
		
		try{
			String str="select studentid from student";
			ps1=con.prepareStatement(str);
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
				
		JButton btnBack = new JButton("Back");
		btnBack.setBackground(new Color(255, 218, 185));
		btnBack.setForeground(new Color(178, 34, 34));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				AdminPage window = new AdminPage();
				window.frame.setVisible(true);
				frame.hide();
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnBack.setBounds(905, 109, 164, 36);
		frame.getContentPane().add(btnBack);
		
		
		JLabel lblNewLabel = new JLabel("Student ID                   :");
		lblNewLabel.setForeground(Color.CYAN);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel.setBounds(203, 212, 283, 36);
		frame.getContentPane().add(lblNewLabel);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.RED);
		separator_1.setBounds(0, 177, 1370, 8);
		frame.getContentPane().add(separator_1);
		
		JLabel lblName = new JLabel("Name                            :");
		lblName.setForeground(Color.CYAN);
		lblName.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblName.setBounds(203, 259, 283, 36);
		frame.getContentPane().add(lblName);
		
		JLabel lblEmail = new JLabel("E-Mail                           :");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblEmail.setForeground(Color.CYAN);
		lblEmail.setBounds(203, 306, 283, 36);
		frame.getContentPane().add(lblEmail);
		
		JLabel lblNewLabel_1 = new JLabel("Date of Birth                :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel_1.setForeground(Color.CYAN);
		lblNewLabel_1.setBounds(203, 353, 283, 36);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblPhoneNumber = new JLabel("Phone Number            :");
		lblPhoneNumber.setForeground(Color.CYAN);
		lblPhoneNumber.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblPhoneNumber.setBounds(203, 400, 283, 36);
		frame.getContentPane().add(lblPhoneNumber);
		
		JLabel lblGender = new JLabel("Gender                         :");
		lblGender.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblGender.setForeground(Color.CYAN);
		lblGender.setBounds(203, 447, 276, 33);
		frame.getContentPane().add(lblGender);
		
		JLabel lblExamAttempts = new JLabel("Exam Attempt's          :");
		lblExamAttempts.setForeground(Color.CYAN);
		lblExamAttempts.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblExamAttempts.setBounds(203, 491, 283, 33);
		frame.getContentPane().add(lblExamAttempts);
		
		JLabel lblGrandTotal = new JLabel("Grand Total                 :");
		lblGrandTotal.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblGrandTotal.setForeground(Color.CYAN);
		lblGrandTotal.setBounds(203, 535, 276, 36);
		frame.getContentPane().add(lblGrandTotal);
		
		JLabel lblPercentage = new JLabel("Percentage                  :");
		lblPercentage.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblPercentage.setForeground(Color.CYAN);
		lblPercentage.setBounds(203, 624, 283, 36);
		frame.getContentPane().add(lblPercentage);
		
		JLabel lblMarksObtained = new JLabel("Marks Obtained          :");
		lblMarksObtained.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblMarksObtained.setForeground(Color.CYAN);
		lblMarksObtained.setBounds(203, 582, 283, 33);
		frame.getContentPane().add(lblMarksObtained);
		
		JLabel lblResult = new JLabel("Result                           :");
		lblResult.setForeground(Color.CYAN);
		lblResult.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblResult.setBounds(203, 673, 283, 33);
		frame.getContentPane().add(lblResult);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(Color.RED);
		separator_2.setBounds(0, 725, 1370, 2);
		frame.getContentPane().add(separator_2);
		
		JLabel lblNull = new JLabel("");
		lblNull.setFont(new Font("Tahoma", Font.ITALIC, 25));
		lblNull.setForeground(Color.ORANGE);
		lblNull.setBounds(528, 212, 418, 33);
		frame.getContentPane().add(lblNull);
		
		JLabel lblNull_1 = new JLabel("");
		lblNull_1.setForeground(Color.ORANGE);
		lblNull_1.setFont(new Font("Tahoma", Font.ITALIC, 25));
		lblNull_1.setBounds(528, 258, 418, 37);
		frame.getContentPane().add(lblNull_1);
		
		JLabel lblNull_2 = new JLabel("");
		lblNull_2.setFont(new Font("Tahoma", Font.ITALIC, 25));
		lblNull_2.setForeground(Color.ORANGE);
		lblNull_2.setBounds(528, 306, 418, 36);
		frame.getContentPane().add(lblNull_2);
		
		JLabel lblNull_3 = new JLabel("");
		lblNull_3.setForeground(Color.ORANGE);
		lblNull_3.setFont(new Font("Tahoma", Font.ITALIC, 25));
		lblNull_3.setBounds(528, 353, 418, 36);
		frame.getContentPane().add(lblNull_3);
		
		JLabel lblNull_4 = new JLabel("");
		lblNull_4.setFont(new Font("Tahoma", Font.ITALIC, 25));
		lblNull_4.setForeground(Color.ORANGE);
		lblNull_4.setBounds(528, 400, 418, 33);
		frame.getContentPane().add(lblNull_4);
		
		JLabel lblNull_5 = new JLabel("");
		lblNull_5.setForeground(Color.ORANGE);
		lblNull_5.setFont(new Font("Tahoma", Font.ITALIC, 25));
		lblNull_5.setBounds(528, 449, 418, 33);
		frame.getContentPane().add(lblNull_5);
		
		JLabel lblNull_6 = new JLabel("");
		lblNull_6.setFont(new Font("Tahoma", Font.ITALIC, 25));
		lblNull_6.setForeground(Color.ORANGE);
		lblNull_6.setBounds(528, 491, 418, 36);
		frame.getContentPane().add(lblNull_6);
		
		JLabel lblNull_7 = new JLabel("");
		lblNull_7.setForeground(Color.ORANGE);
		lblNull_7.setFont(new Font("Tahoma", Font.ITALIC, 25));
		lblNull_7.setBounds(528, 537, 424, 33);
		frame.getContentPane().add(lblNull_7);
		
		JLabel lblNull_8 = new JLabel("");
		lblNull_8.setFont(new Font("Tahoma", Font.ITALIC, 25));
		lblNull_8.setForeground(Color.ORANGE);
		lblNull_8.setBounds(528, 582, 305, 33);
		frame.getContentPane().add(lblNull_8);
		
		JLabel lblNull_9 = new JLabel("");
		lblNull_9.setForeground(Color.ORANGE);
		lblNull_9.setFont(new Font("Tahoma", Font.ITALIC, 25));
		lblNull_9.setBounds(528, 629, 305, 31);
		frame.getContentPane().add(lblNull_9);
		
		JLabel lblNull_10 = new JLabel("");
		lblNull_10.setFont(new Font("Tahoma", Font.ITALIC, 25));
		lblNull_10.setForeground(Color.ORANGE);
		lblNull_10.setBounds(528, 677, 305, 29);
		frame.getContentPane().add(lblNull_10);
		
		JButton btnNewButton = new JButton("Show");
		btnNewButton.setForeground(new Color(178, 34, 34));
		btnNewButton.setBackground(new Color(255, 222, 173));
		btnNewButton.addActionListener(new ActionListener() {
			private Component frame;

			public void actionPerformed(ActionEvent arg0) {
				try{
					String str="select result.studentid,name,email,dob,phone,gender,count(result.studentid),sum(totalmarks),sum(obtainedmarks) from quiz.student,result where result.studentid=student.studentid and result.studentid=?";
					ps=con.prepareStatement(str);
					ps.setString(1,comboBox.getSelectedItem().toString());
					rs=ps.executeQuery();
					if(rs.next()){
						lblNull.setText(rs.getString(1));
						lblNull_1.setText(rs.getString(2));
						lblNull_2.setText(rs.getString(3));
						lblNull_3.setText(rs.getString(4));
						lblNull_4.setText(rs.getString(5));
						lblNull_5.setText(rs.getString(6));
						lblNull_6.setText(rs.getString(7));
						lblNull_7.setText(rs.getString(8));
						lblNull_8.setText(rs.getString(9));
						float i= (float)(rs.getInt(9)*100)/(float)rs.getInt(8);
						lblNull_9.setText(""+i);
						if(i>=40)
							lblNull_10.setText("Pass");
						else
							lblNull_10.setText("Fail");
					}
					
				}
				catch(SQLException e){
					JOptionPane.showMessageDialog(this.frame,"Check Internet Connection");
				}
				catch(Exception e){
					JOptionPane.showMessageDialog(this.frame,"There are Some issue....try again");
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnNewButton.setBounds(660, 109, 173, 36);
		frame.getContentPane().add(btnNewButton);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(ResultReport.class.getResource("/images/motivational-wallpaper-8.jpg")));
		label.setBounds(0, 0, 1424, 789);
		frame.getContentPane().add(label);
	
	}
}
