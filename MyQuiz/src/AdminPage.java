import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import conn.DatabaseConnect;
import conn.SendMailMessage;
import net.proteanit.sql.DbUtils;
import java.sql.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AdminPage extends JFrame implements ActionListener{

	private JScrollPane jp,jp1;
	private JTable jt;
	public JFrame frame;
	private JTable table;
	private JTable table_1;
	private JButton ShowResult;
	JLayeredPane layeredPane;
	private JPanel panel,panel_1,panel_2;
	private JComboBox jcb,comboBox;
	Connection con;
	ResultSet rs,rs1;
	PreparedStatement ps,ps1;
	private JTextField txtOespapergmailcom;
	private JTextField textField_2;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminPage window = new AdminPage();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public AdminPage() {
		initialize();
	}
	
	public void switchpanel(JPanel panel)
	{
		layeredPane.removeAll();
		layeredPane.add(panel);
		layeredPane.repaint();
		layeredPane.revalidate();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(-5, 0, 1400, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnStudent = new JMenu("Student");
		menuBar.add(mnStudent);
		
		JMenuItem mntmAddStudent = new JMenuItem("Add Student");
		mnStudent.add(mntmAddStudent);
		mntmAddStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ar) {
				Student window = new Student("insert","admin");
				window.frame.setVisible(true);
				frame.hide();
			}
		});
		
		JMenuItem mntmUpdateStudent = new JMenuItem("Update Student");
		mnStudent.add(mntmUpdateStudent);
		mntmUpdateStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ar) {
				Student window = new Student("update","admin");
				window.frame.setVisible(true);
				frame.hide();
			}
		});
		
		JMenu mnQuestions = new JMenu("Question's");
		menuBar.add(mnQuestions);
		
		JMenuItem mntmAddQuestion = new JMenuItem("Add Question");
		mnQuestions.add(mntmAddQuestion);
		mntmAddQuestion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ar) {
				Question window = new Question("insert");
				window.frame.setVisible(true);
				frame.hide();
			}
		});
		
		JMenuItem mntmUpdateQuestion = new JMenuItem("Update Question");
		mnQuestions.add(mntmUpdateQuestion);
		mntmUpdateQuestion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ar) {
				Question window = new Question("update");
				window.frame.setVisible(true);
				frame.hide();
			}
		});
		
		JMenuItem mntmDeleteQuestion = new JMenuItem("Delete Question");
		mnQuestions.add(mntmDeleteQuestion);
		mntmDeleteQuestion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ar) {
				Question window = new Question("delete");
				window.frame.setVisible(true);
				frame.hide();
			}
		});
		
		JMenu mnTopic = new JMenu("Topic");
		menuBar.add(mnTopic);
		
		JMenuItem mntmAddTopic = new JMenuItem("Add Topic");
		mnTopic.add(mntmAddTopic);
		mntmAddTopic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ar) {
				topic window = new topic("insert");
				window.frame.setVisible(true);
				frame.hide();
			}
		});
		
		JMenuItem mntmUpdateTopic = new JMenuItem("Update Topic");
		mnTopic.add(mntmUpdateTopic);
		mntmUpdateTopic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ar) {
				topic window = new topic("update");
				window.frame.setVisible(true);
				frame.hide();
			}
		});
		
		JMenuItem mntmDeleteTopic = new JMenuItem("Delete Topic");
		mnTopic.add(mntmDeleteTopic);
		mntmDeleteTopic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ar) {
				topic window = new topic("delete");
				window.frame.setVisible(true);
				frame.hide();
			}
		});
		
		JMenu mnProfile = new JMenu("Profile");
		menuBar.add(mnProfile);
		
		JMenuItem mntmAddAdmin = new JMenuItem("Add Admin");
		mnProfile.add(mntmAddAdmin);
		mntmAddAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ar) {
				Admin window = new Admin("insert");
				window.frame.setVisible(true);
				frame.hide();
			}
		});
		
		JMenuItem mntmUpdateAdmin = new JMenuItem("Update Admin");
		mnProfile.add(mntmUpdateAdmin);
		mntmUpdateAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ar) {
				Admin window = new Admin("update");
				window.frame.setVisible(true);
				frame.hide();
			}
		});
		
		JMenuItem mntmDeleteAdmin = new JMenuItem("Delete Admin");
		mnProfile.add(mntmDeleteAdmin);
		mntmDeleteAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ar) {
				Admin window = new Admin("delete");
				window.frame.setVisible(true);
				frame.hide();
			}
		});
		
		JMenu mnRsult = new JMenu("Result");
		menuBar.add(mnRsult);
		
		JMenu mnShow = new JMenu("Show");
		mnRsult.add(mnShow);
		mnShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		
		JMenuItem mntmSingleStudent = new JMenuItem("Single Student");
		mnShow.add(mntmSingleStudent);
		mntmSingleStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				switchpanel(panel);
			}
		});
		
		
		JMenuItem mntmAllStudent = new JMenuItem("All Student");
		mnShow.add(mntmAllStudent);
		mntmAllStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				switchpanel(panel_1);
			}
		});
		
		JMenu mnReport = new JMenu("Report");
		menuBar.add(mnReport);
		
		JMenuItem mntmStudentReport = new JMenuItem("Student Report");
		mntmStudentReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				StudentReport window = new StudentReport();
				window.frame.setVisible(true);
			}
		});
		mnReport.add(mntmStudentReport);
		
		JMenuItem mntmResultReport = new JMenuItem("Result Report");
		mntmResultReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ResultReport window = new ResultReport();
				window.frame.setVisible(true);
			}
		});
		mnReport.add(mntmResultReport);
		
		JMenu mnVerify = new JMenu("Verify");
		menuBar.add(mnVerify);
		
		JMenuItem mntmVerification = new JMenuItem("Verification");
		mntmVerification.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AdminVerify window = new AdminVerify();
				window.frame.setVisible(true);
			}
		});
		mnVerify.add(mntmVerification);
		
		JMenu mnEmail = new JMenu("E-Mail");
		menuBar.add(mnEmail);
		
		JMenuItem mntmSendMail = new JMenuItem("Send Mail");
		mntmSendMail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				switchpanel(panel_2);
			}
		});
		mnEmail.add(mntmSendMail);
		
		JMenu mnHelp = new JMenu("About");
		menuBar.add(mnHelp);
		
		JMenuItem mntmHelp = new JMenuItem("Help");
		mnHelp.add(mntmHelp);
		
		JMenu menu = new JMenu("");
		menuBar.add(menu);
		frame.getContentPane().setLayout(null);
		
		JLabel lblExaminationManagementSystem = new JLabel("Online Examination System");
		lblExaminationManagementSystem.setForeground(Color.MAGENTA);
		lblExaminationManagementSystem.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 35));
		lblExaminationManagementSystem.setBounds(367, 30, 627, 48);
		frame.getContentPane().add(lblExaminationManagementSystem);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.RED);
		separator.setBounds(367, 87, 521, 9);
		frame.getContentPane().add(separator);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(AdminPage.class.getResource("/images/EXAM.jpg")));
		lblNewLabel.setBounds(0, 0, 351, 128);
		frame.getContentPane().add(lblNewLabel);
		
		layeredPane = new JLayeredPane();
		layeredPane.setBounds(21, 171, 1316, 522);
		frame.getContentPane().add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));
		
		JButton btnLogout = new JButton("LOGOUT");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainPage window = new MainPage();
				window.frame.setVisible(true);
				frame.hide();
			}
		});
		btnLogout.setBackground(Color.ORANGE);
		btnLogout.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnLogout.setBounds(1197, 11, 140, 33);
		frame.getContentPane().add(btnLogout);
		
		JLabel lblNewLabel_2 = new JLabel("LOGO");
		lblNewLabel_2.setIcon(new ImageIcon(AdminPage.class.getResource("/images/mainlogo (1).png")));
		lblNewLabel_2.setBounds(600, 241, 209, 260);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_1 = new JLabel("Background");
		lblNewLabel_1.setIcon(new ImageIcon(AdminPage.class.getResource("/images/background_20.wide.jpeg")));
		lblNewLabel_1.setBounds(0, 0, 1390, 728);
		frame.getContentPane().add(lblNewLabel_1);
		
		
		panel = new JPanel();
		panel.setBackground(new Color(51, 102, 204));
		panel.setLayout(null);
		
		JLabel lblStudentId = new JLabel("Student ID :");
		lblStudentId.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblStudentId.setBounds(49, 44, 153, 33);
		panel.add(lblStudentId);
		
		jcb = new JComboBox();
		jcb.setBounds(254, 44, 206, 33);
		panel.add(jcb);
		DatabaseConnect obj=new DatabaseConnect();
		con=obj.Connect();
		try{
			String stl="select studentid from student";
			ps=con.prepareStatement(stl);
			rs=ps.executeQuery();
			while(rs.next()){
				jcb.addItem(rs.getString(1));
			}
		}
		catch(SQLException e){
			JOptionPane.showMessageDialog(this.frame,"Check Internet Connection");
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(this.frame,"There are Some issue....try again");
		}
		
		ShowResult = new JButton("Show Result");
		ShowResult.setBackground(Color.ORANGE);
		ShowResult.setFont(new Font("Tahoma", Font.BOLD, 15));
		ShowResult.setBounds(537, 48, 153, 32);
		ShowResult.addActionListener(this);
		panel.add(ShowResult);
		
		panel_1 = new JPanel();
		panel_1.setBackground(new Color(51, 102, 204));
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(70, 100, 1200, 400);
		panel.add(scrollPane);
		
		table_1 = new JTable();
		scrollPane.setViewportView(table_1);
		table_1.setFont(new Font("Arial", Font.BOLD, 15));
		table_1.setRowHeight(40);
		table_1.setShowGrid(true);
		table_1.setGridColor(Color.red);
		table_1.setBackground(Color.black);
		table_1.setForeground(Color.YELLOW);
		table_1.setSelectionBackground(Color.yellow);
		table_1.setSelectionForeground(Color.black);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(49, 50, 1208, 388);
		panel_1.add(scrollPane_1);
		
		table = new JTable();
		scrollPane_1.setViewportView(table);
		table.setFont(new Font("Arial", Font.BOLD, 15));
		table.setRowHeight(40);
		table.setShowGrid(true);
		table.setGridColor(Color.red);
		table.setBackground(Color.black);
		table.setForeground(Color.YELLOW);
		table.setSelectionBackground(Color.yellow);
		table.setSelectionForeground(Color.black);
		try{
			String str="select * from result";
			ps1=con.prepareStatement(str);
			rs=ps1.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
		}
		catch(SQLException e){
			JOptionPane.showMessageDialog(this.frame,"Check Internet Connection");
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(this.frame,"There are Some issue....try again");
		}

		panel_2 = new JPanel();
		panel_2.setBackground(new Color(51, 102, 204));
		//layeredPane.add(panel_2, "name_469312899732");
		panel_2.setLayout(null);
		
		JLabel lblEmailsendImportant = new JLabel("E-MAIL (Send Important Information to Student)");
		lblEmailsendImportant.setForeground(Color.GREEN);
		lblEmailsendImportant.setFont(new Font("Algerian", Font.BOLD | Font.ITALIC, 30));
		lblEmailsendImportant.setBounds(157, 31, 856, 59);
		panel_2.add(lblEmailsendImportant);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.GREEN);
		separator_1.setBounds(157, 83, 853, 7);
		panel_2.add(separator_1);
		
		JLabel lblFrom = new JLabel("From :");
		lblFrom.setFont(new Font("Tahoma", Font.ITALIC, 25));
		lblFrom.setBounds(155, 138, 97, 31);
		panel_2.add(lblFrom);
		
		JLabel lblTo = new JLabel("To :");
		lblTo.setFont(new Font("Tahoma", Font.ITALIC, 25));
		lblTo.setBounds(155, 197, 102, 31);
		panel_2.add(lblTo);
		
		JLabel lblSubject = new JLabel("Subject :");
		lblSubject.setFont(new Font("Tahoma", Font.ITALIC, 25));
		lblSubject.setBounds(155, 260, 116, 31);
		panel_2.add(lblSubject);
		
		JLabel lblMessage = new JLabel("Message :");
		lblMessage.setFont(new Font("Tahoma", Font.ITALIC, 25));
		lblMessage.setBounds(155, 320, 116, 43);
		panel_2.add(lblMessage);
		
		txtOespapergmailcom = new JTextField();
		txtOespapergmailcom.setFont(new Font("Tahoma", Font.ITALIC, 18));
		txtOespapergmailcom.setEditable(false);
		txtOespapergmailcom.setText("oespaper9760@gmail.com");
		txtOespapergmailcom.setBounds(353, 133, 324, 36);
		panel_2.add(txtOespapergmailcom);
		txtOespapergmailcom.setColumns(10);
		
		comboBox = new JComboBox();
		comboBox.setBounds(353, 192, 324, 36);
		panel_2.add(comboBox);
		comboBox.addItem("Select ID");
		additem();
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Tahoma", Font.ITALIC, 18));
		textField_2.setBounds(353, 248, 350, 43);
		panel_2.add(textField_2);
		textField_2.setColumns(10);
		
		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 15));
		textArea.setBounds(353, 322, 350, 151);
		panel_2.add(textArea);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setBounds(119, 106, 8, 384);
		panel_2.add(separator_2);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(119, 106, 965, 14);
		panel_2.add(separator_3);
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setBounds(119, 490, 965, 7);
		panel_2.add(separator_4);
		
		JSeparator separator_5 = new JSeparator();
		separator_5.setOrientation(SwingConstants.VERTICAL);
		separator_5.setBounds(1083, 106, 8, 384);
		panel_2.add(separator_5);
		
		JSeparator separator_6 = new JSeparator();
		separator_6.setOrientation(SwingConstants.VERTICAL);
		separator_6.setBounds(729, 106, 8, 384);
		panel_2.add(separator_6);
		
		JButton btnSend = new JButton("Send");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(textField_2.getText().toString().length()!=0&&textArea.getText().toString().length()!=0){
					new SendMailMessage(comboBox.getSelectedItem().toString(),textField_2.getText().toString(),textArea.getText().toString());
					
				}
				else{
					JOptionPane.showMessageDialog(frame, "Please Fill All the Coloum");
				}
			}
		});
		btnSend.setBackground(Color.PINK);
		btnSend.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 22));
		btnSend.setBounds(805, 207, 197, 43);
		panel_2.add(btnSend);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				layeredPane.removeAll();
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});
		btnCancel.setBackground(Color.PINK);
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnCancel.setBounds(807, 283, 195, 43);
		panel_2.add(btnCancel);
		
	}
	
	private void additem() {
		try{
			DatabaseConnect obj=new DatabaseConnect();
			con=obj.Connect();
			String str="select studentid from student";
			ps=con.prepareStatement(str);
			rs=ps.executeQuery();
			while(rs.next()){
				comboBox.addItem(rs.getString(1).toUpperCase());
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}

	public void actionPerformed(ActionEvent a) {
		if(a.getSource()==ShowResult){
			try{
				String str="SELECT * FROM quiz.result where studentid=?";
				ps1=con.prepareStatement(str);
				ps1.setString(1, jcb.getSelectedItem().toString());
				rs=ps1.executeQuery();
				table_1.setModel(DbUtils.resultSetToTableModel(rs));
			}
			catch(SQLException e){
				JOptionPane.showMessageDialog(this.frame,"Check Internet Connection");
			}
			catch(Exception e){
				JOptionPane.showMessageDialog(this.frame,"There are Some issue....try again");
			}
		}
		
	}
}
