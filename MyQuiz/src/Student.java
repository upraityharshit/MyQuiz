import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import conn.DatabaseConnect;
import conn.OTPGenerate;
import conn.OtpMail;
import java.sql.*;
import java.util.regex.*;

public class Student extends JFrame implements ActionListener,FocusListener {

	JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JLabel lblNewStudentRegisteration,msg;
	private JButton b1,cancel,find;
	private JComboBox jcb;
	
	JTextArea textArea;
	JComboBox comboBox,comboBox_1,comboBox_2,Gender;
	JLabel lblNewLabel_7,lblNewLabel_6,lblNewLabel_5,lblNewLabel_4;
	String dob1;
	
	Connection con;
	PreparedStatement st;
	ResultSet rs;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Student window = new Student("insert","admin");
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	String operation,root;
	public Student(String ope,String rot) {
		operation=ope;
		root=rot;
		DatabaseConnect obj=new DatabaseConnect();
		con=obj.Connect();
		initialize();
		try{
			con.close();
		}
		catch(Exception e){}
	}

	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(-8,0, 1400, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		if(operation=="insert")
			lblNewStudentRegisteration = new JLabel("Student Registeration");
		else if(operation=="delete")
			lblNewStudentRegisteration = new JLabel("Student Delete ");
		else if(operation=="update")
			lblNewStudentRegisteration = new JLabel("Student Updation");
		lblNewStudentRegisteration.setForeground(Color.MAGENTA);
		lblNewStudentRegisteration.setFont(new Font("Algerian", Font.BOLD | Font.ITALIC, 50));
		lblNewStudentRegisteration.setBounds(211, 36, 792, 47);
		frame.getContentPane().add(lblNewStudentRegisteration);
		
		JSeparator separator = new JSeparator();
		separator.setBackground(Color.RED);
		separator.setBounds(70, 94, 1099, 9);
		frame.getContentPane().add(separator);
		
		JLabel lblStudentId = new JLabel("Student ID :");
		lblStudentId.setForeground(Color.BLACK);
		lblStudentId.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblStudentId.setBounds(70, 143, 165, 23);
		frame.getContentPane().add(lblStudentId);
		
		if(operation=="insert"){
			textField = new JTextField();
			textField.setBounds(341, 142, 188, 34);
			textField.addFocusListener(this);
			frame.getContentPane().add(textField);
			textField.addFocusListener(this);
			textField.setColumns(10);
			msg=new JLabel("");
			msg.setBounds(540,142,300,34);
			msg.setFont(new Font("Tahoma", Font.BOLD, 15));
			msg.setForeground(Color.ORANGE);
			frame.getContentPane().add(msg);
		}
		else{
			jcb=new JComboBox();
			jcb.setBounds(341, 142, 220, 34);
			frame.getContentPane().add(jcb);
			find=new JButton("Find Data");
			find.setBounds(580,145,200,30);
			find.addActionListener(this);
			frame.getContentPane().add(find);
			fillid();
		}
		
		JLabel lblNewLabel = new JLabel("Name :");
		lblNewLabel.setForeground(Color.ORANGE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNewLabel.setBounds(70, 208, 165, 31);
		frame.getContentPane().add(lblNewLabel);
		
		lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setBounds(345, 245, 200, 15);
		frame.getContentPane().add(lblNewLabel_4);
		lblNewLabel_4.setForeground(Color.red);
		
		textField_1 = new JTextField();
		textField_1.setBounds(341, 208, 220, 34);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		textField_1.addFocusListener(this);
		
		JLabel lblNewLabel_1 = new JLabel("Create Password :");
		lblNewLabel_1.setForeground(Color.ORANGE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNewLabel_1.setBounds(70, 284, 201, 23);
		frame.getContentPane().add(lblNewLabel_1);
		
		textField_2 = new JTextField();
		textField_2.setBounds(341, 273, 220, 34);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblPhone = new JLabel("Email ID :");
		lblPhone.setForeground(Color.ORANGE);
		lblPhone.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblPhone.setBounds(70, 355, 157, 23);
		frame.getContentPane().add(lblPhone);
		
		lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.setBounds(345, 385, 200, 15);
		frame.getContentPane().add(lblNewLabel_6);
		lblNewLabel_6.setForeground(Color.red);
		textField_3 = new JTextField();
		textField_3.setBounds(341, 344, 220, 34);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		textField_3.addFocusListener(this);
		
		JLabel lblConfirmPassword = new JLabel("Confirm Password :");
		lblConfirmPassword.setForeground(Color.ORANGE);
		lblConfirmPassword.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblConfirmPassword.setBounds(623, 273, 236, 34);
		frame.getContentPane().add(lblConfirmPassword);
		
		textField_4 = new JTextField();
		textField_4.setBounds(900, 272, 285, 37);
		frame.getContentPane().add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblPhone_1 = new JLabel("Mobile No. :");
		lblPhone_1.setForeground(Color.ORANGE);
		lblPhone_1.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblPhone_1.setBounds(70, 421, 157, 23);
		frame.getContentPane().add(lblPhone_1);
		
		lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setBounds(345, 452, 200, 15);
		frame.getContentPane().add(lblNewLabel_5);
		lblNewLabel_5.setForeground(Color.red);
		textField_5 = new JTextField();
		textField_5.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent arg0) {
				
			}
		});
		textField_5.setBounds(341, 410, 220, 34);
		frame.getContentPane().add(textField_5);
		textField_5.setColumns(10);
		textField_5.addFocusListener(this);
		
		JLabel lblNewLabel_2 = new JLabel("Date Of Birth :");
		lblNewLabel_2.setForeground(Color.ORANGE);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNewLabel_2.setBounds(70, 486, 165, 23);
		frame.getContentPane().add(lblNewLabel_2);
		
		lblNewLabel_7 = new JLabel("");
		lblNewLabel_7.setBounds(345, 522, 200, 15);
		frame.getContentPane().add(lblNewLabel_7);
		lblNewLabel_7.setForeground(Color.red);
		
		comboBox = new JComboBox();
		comboBox.setBounds(341, 478, 65, 31);
		frame.getContentPane().add(comboBox);
		comboBox.addItem("Date");
		for(int a=1;a<=31;a++)
		{
			comboBox.addItem(a);
		}
		
		comboBox_1 = new JComboBox();
		comboBox_1.setBounds(405, 478, 65, 31);
		frame.getContentPane().add(comboBox_1);
		comboBox_1.addItem("Month");
		for(int a=1;a<=12;a++)
		{
			comboBox_1.addItem(a);
		}
		
		comboBox_2 = new JComboBox();
		comboBox_2.setBounds(468, 478, 93, 31);
		frame.getContentPane().add(comboBox_2);
		comboBox_2.addItem("Year");
		for(int a=1980;a<=2018;a++)
		{
			comboBox_2.addItem(a);
		}
		
		JLabel lblGender = new JLabel("Gender :");
		lblGender.setForeground(Color.ORANGE);
		lblGender.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblGender.setBounds(623, 349, 123, 34);
		frame.getContentPane().add(lblGender);
	
		Gender = new JComboBox();
		Gender.setBounds(900, 354, 220, 34);
		frame.getContentPane().add(Gender);
		Gender.addItem("Select");
		Gender.addItem("Male");
		Gender.addItem("Female");
		Gender.addItem("Other");
		
		JLabel lblRemarks = new JLabel("Remarks :");
		lblRemarks.setForeground(Color.ORANGE);
		lblRemarks.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblRemarks.setBounds(623, 417, 123, 31);
		frame.getContentPane().add(lblRemarks);
		
		textArea = new JTextArea();
		textArea.setBounds(900, 421, 285, 102);
		frame.getContentPane().add(textArea);
		
		if(operation=="insert")
			b1 = new JButton("SUBMIT");
		else if(operation=="delete")
			b1 = new JButton("DELETE");
		else if(operation=="update")
			b1 = new JButton("UPDATE");
		b1.setBackground(Color.GREEN);
		b1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 25));
		b1.setBounds(303, 645, 188, 37);
		b1.addActionListener(this);
		frame.getContentPane().add(b1);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBackground(Color.GREEN);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ar) {
				if(ar.getSource()==btnCancel){
					System.exit(1);
				}
			}
		});
		btnCancel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 25));
		btnCancel.setBounds(604, 645, 201, 36);
		frame.getContentPane().add(btnCancel);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBackground(Color.RED);
		separator_1.setBounds(70, 625, 1115, 9);
		frame.getContentPane().add(separator_1);
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(root=="admin"){
					AdminPage window = new AdminPage();
					window.frame.setVisible(true);
					frame.hide();
				}
				else{
					MainPage window = new MainPage();
					window.frame.setVisible(true);
					frame.hide();
				}
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnBack.setBounds(10, 10, 108, 23);
		frame.getContentPane().add(btnBack);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setIcon(new ImageIcon(Student.class.getResource("/images/technology-wallpape.jpg")));
		lblNewLabel_3.setBounds(0, 0, 1413, 746);
		frame.getContentPane().add(lblNewLabel_3);		
	
		
		
	}

	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void focusLost(FocusEvent arg0) {
		if(operation=="insert")
		{
			boolean ret=getUserID(textField.getText());
			if (ret==true||textField.getText().length()==0)
			{
				msg.setText("*This ID iS Alredy Exits,Try another ID...");
				b1.setEnabled(false);
			}
			else
			{
				msg.setText("");
				b1.setEnabled(true);
			}
		}
		else 
			b1.setEnabled(true);
		
		
	}

	public void actionPerformed(ActionEvent ar) {
		if(ar.getSource()==b1)
		{
			DatabaseConnect obj=new DatabaseConnect();
			con=obj.Connect();
				try
				{
					boolean vname=validName();
					boolean vemail=validEmail();
					boolean vnum=validNumber();
					boolean vdate=validDate();
					if(operation=="insert")
					{
						String gender=gender();
						if(vdate||vname||vnum||vemail || gender.equals("Select")||textField.getText().length()==0||textField_1.getText().length()==0||textField_2.getText().length()==0||textField_4.getText().length()==0||textField_5.getText().length()==0||textArea.getText().length()==0)
						{
							JOptionPane.showMessageDialog(this, "Please Fill all Fields Correctly");
						}
						else
						{ 
							String studentid=textField.getText();
							String pass=textField_2.getText();
							String cpass=textField_4.getText();
							String name=textField_1.getText();
							String email=textField_3.getText();
							String phone=textField_5.getText();	
							String dob=dob1;
							String Remarks=textArea.getText();
							if(pass.equals(cpass)){
								OTPGenerate ot=new OTPGenerate();
								int otp= ot.OTP();
								
								new OtpMail(email,otp,"Student");
									
								OTPCheck window = new OTPCheck(otp,studentid,pass,name,email,phone,dob,gender,Remarks);
								window.frame.setVisible(true);
								frame.hide();		
								
							}
							else
								JOptionPane.showMessageDialog(this, "Password Is not Matched");
						}
					}
					else if(operation=="delete")
					{
						String id=jcb.getSelectedItem().toString();
						String str="delete from student where studentId=?";
						st=con.prepareStatement(str);
						st.setString(1,id);
						int q=st.executeUpdate();
						JOptionPane.showMessageDialog(this, "Data Deleted...");
					}
					else if(operation=="update")
					{
						String gender=gender();
						if(vdate||vname||vnum||vemail||gender.equals("Select")||textField_1.getText().length()==0||textField_2.getText().length()==0||textField_4.getText().length()==0||textField_5.getText().length()==0||textArea.getText().length()==0)
						{
							JOptionPane.showMessageDialog(this, "Please Enter all Fields..");
						}
						else
						{
							String studentid=jcb.getSelectedItem().toString();
							String pass=textField_2.getText();
							String cpass=textField_4.getText();
							String name=textField_1.getText();
							String email=textField_3.getText();
							String phone=textField_5.getText();	
							String dob=dob1;
							String Remarks=textArea.getText();
						if(pass.equals(cpass)){
							
							String str="update student set passwrd=?,name=?,email=?,phone=?,dob=?,gender=?,remarks=? where studentid=?";
							st=con.prepareStatement(str);
							st.setString(1,pass);
							st.setString(2,name);
							st.setString(3,email);
							st.setString(4,phone);
							st.setString(5,dob);
							st.setString(6,gender);
							st.setString(7,Remarks);
							st.setString(8,studentid);
							int q=st.executeUpdate();
							JOptionPane.showMessageDialog(this, "Data Updated...");
							con.close();
							textArea.setText("");
							textField_1.setText("");
							textField_2.setText("");
							textField_3.setText("");
							textField_4.setText("");
							textField_5.setText("");
							comboBox.setSelectedIndex(0);
							comboBox_1.setSelectedIndex(0);
							comboBox_2.setSelectedIndex(0);
						}
						else
							JOptionPane.showMessageDialog(this, "Password Is not Matched");
					}
				}
				}
				catch(SQLException e){
					JOptionPane.showMessageDialog(this.frame,"Check Internet Connection");
					e.printStackTrace();
				}
				catch(Exception e){
					JOptionPane.showMessageDialog(this.frame,"There are Some issue....try again");
					e.printStackTrace();
				}
		}
		else if(ar.getSource()==find)
			{
			DatabaseConnect obj=new DatabaseConnect();
			con=obj.Connect();
				if(jcb.getSelectedItem().toString().length()!=0){
				try {
					String sql="select passwrd,name,email,phone,dob,gender,remarks from student where studentId=?";
					st=con.prepareStatement(sql);
					st.setString(1,jcb.getSelectedItem().toString());
					rs=st.executeQuery();
					if(rs.next())
					{
						textField_2.setText(rs.getString(1));
						textField_4.setText(rs.getString(1));
						textField_1.setText(rs.getString(2));
						textField_3.setText(rs.getString(3));
						textField_5.setText(rs.getString(4));
						Gender.setSelectedItem(rs.getString(6));
						textArea.setText(rs.getString(7));
					}
				}
				catch(SQLException e){
					JOptionPane.showMessageDialog(this.frame,"Check Internet Connection");
					e.printStackTrace();
				}
				catch(Exception e){
					JOptionPane.showMessageDialog(this.frame,"There are Some issue....try again");
					e.printStackTrace();
				}
			}
			else
				JOptionPane.showMessageDialog(this,"Empty Id..");
		}

	}
	public String gender()
	{
		return Gender.getSelectedItem().toString();		
	}
	public void fillid()
	{
		DatabaseConnect obj=new DatabaseConnect();
		con=obj.Connect();
		try
		{
			String str="select studentid from student";
			st=con.prepareStatement(str);
			rs=st.executeQuery();
			while(rs.next())
			{
				jcb.addItem(rs.getString(1).toString());
			}
		}
		catch(SQLException e){
			JOptionPane.showMessageDialog(this.frame,"Check Internet Connection");
			e.printStackTrace();
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(this.frame,"There are Some issue....try again");
			e.printStackTrace();
		}
	}
	public boolean getUserID(String Id)
	{
		DatabaseConnect obj=new DatabaseConnect();
		con=obj.Connect();
		ResultSet rs;
		try
		{
			String str="select * from student where studentId=?";
			st=con.prepareStatement(str);
			st.setString(1, Id);
			rs=st.executeQuery();
			if(rs.next())
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		catch(Exception e)
		{
			return true;
		}
	}
	public boolean validDate(){
		boolean vdate=false;
		if((comboBox.getSelectedIndex()>28 && comboBox_1.getSelectedIndex()==2) ||
			(comboBox.getSelectedIndex()==0 ||comboBox_1.getSelectedIndex()==0 ||
			  comboBox_2.getSelectedIndex()==0)){
			String leapyear=comboBox_2.getSelectedItem().toString();
			if(comboBox.getSelectedIndex()==29 && (leapyear=="1980"||leapyear=="1984"||leapyear=="1988"||
					leapyear=="1992"||leapyear=="1996"||leapyear=="2000"||leapyear=="2004"||leapyear=="2008"||
					leapyear=="2012"||leapyear=="2016"||leapyear=="2020")){
				lblNewLabel_7.setText("");
				vdate=false;
			}
			else{
				lblNewLabel_7.setText("*Date is Not Valid");
				vdate=true;
			}
		}
		else
			lblNewLabel_7.setText("");
		if(comboBox_1.getSelectedIndex()<=7){
		if((comboBox.getSelectedIndex()==31 &&
				comboBox_1.getSelectedIndex()%2==0)){
			lblNewLabel_7.setText("*Date is Not Valid");
			vdate=true;
		}}
		else{
			if((comboBox.getSelectedIndex()==31 &&
					comboBox_1.getSelectedIndex()%2==1)){
				lblNewLabel_7.setText("*Date is Not Valid");
				vdate=true;
			}
			else
				lblNewLabel_7.setText("");
		}
		if(!vdate){
			dob1=comboBox.getSelectedItem().toString()+"/"+comboBox_1.getSelectedItem().toString()+"/"+comboBox_2.getSelectedItem().toString();
		}
		return vdate;
	}
	
	public boolean validName(){
		boolean vname=false;
		String name="^[a-z A-Z]{3,30}$";
		Pattern pat=Pattern.compile(name);
		Matcher mat=pat.matcher(textField_1.getText());
		if(!mat.matches()){
			lblNewLabel_4.setText("*Only Character Allowed");
			vname=true;
		}
		else{
			lblNewLabel_4.setText("");
			vname=false;
		}
		return vname;
	}
	
	public boolean validEmail(){
		boolean vemail=false;
		String emailformat="^[a-zA-Z0-9]{1,20}@[a-zA-Z]{4,10}.[a-zA-Z]{2,3}$";
		Pattern pat=Pattern.compile(emailformat);
		Matcher mat=pat.matcher(textField_3.getText());
		if(!mat.matches()){
			lblNewLabel_6.setText("*Ex::abc123@gmail.com");
			vemail=true;
		}
		else{
			lblNewLabel_6.setText("");
			vemail=false;
		}
		return vemail;
	}
	
	public boolean validNumber(){
		boolean vnum=false;
		
		Pattern pat=Pattern.compile("[7-9][0-9]{9}");
		Matcher mat=pat.matcher(textField_5.getText());
		if(!mat.matches()){
			lblNewLabel_5.setText("*Wrong Number Input");
			vnum=true;
		}
		else{
			lblNewLabel_5.setText("");
			vnum=false;
		}
		return vnum;
	}
}
