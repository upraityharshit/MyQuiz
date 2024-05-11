import java.awt.*;
import javax.swing.*;
import net.proteanit.sql.DbUtils;
import java.sql.*;
import conn.DatabaseConnect;
import java.awt.event.*;

public class StudentReport {

	public JFrame frame;
	private JTable table;
	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentReport window = new StudentReport();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public StudentReport() {
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
		
		JLabel lblStudentsReport = new JLabel("Student's Report");
		lblStudentsReport.setForeground(Color.ORANGE);
		lblStudentsReport.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 50));
		lblStudentsReport.setBounds(504, 11, 478, 57);
		frame.getContentPane().add(lblStudentsReport);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.RED);
		separator.setBounds(0, 79, 1370, 8);
		frame.getContentPane().add(separator);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 98, 1350, 601);
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
		try{
			String str="select STUDENTID,NAME,EMAIL,PHONE,DOB,GENDER from student "; 
			ps=con.prepareStatement(str);
			rs=ps.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			
			JButton btnBack = new JButton("BACK");
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
			btnBack.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));
			btnBack.setBackground(new Color(255, 218, 185));
			btnBack.setForeground(new Color(178, 34, 34));
			btnBack.setBounds(1209, 11, 151, 39);
			frame.getContentPane().add(btnBack);
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
