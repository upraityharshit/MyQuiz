import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JRadioButton;
import javax.swing.ImageIcon;
import javax.swing.JScrollBar;
import javax.swing.JEditorPane;

import java.awt.Scrollbar;
import java.sql.*;

import javax.swing.JList;
import javax.swing.JButton;

import conn.DatabaseConnect;


public class Question extends JFrame implements ActionListener,FocusListener{

	JFrame frame;
	private ButtonGroup bg;
	private JRadioButton A,B,C,D;
	private JTextArea textArea,textArea_1,textArea_2,textArea_3,textArea_4;
	private JTextField textField,topic;
	private JScrollPane jsp,jsp1,jsp2,jsp3,jsp4;
	private JLabel msg,lblAddQuestions;
	private JComboBox jcb,comboBox;
	private JButton find,b1;
	Connection con;
	PreparedStatement st;
	ResultSet rs;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Question window = new Question("insert");
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	String operation;
	
	public Question(String ope) {
		operation=ope;
		DatabaseConnect obj=new DatabaseConnect();
		con=obj.Connect();
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(-5,00, 1400,800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);	
		if(operation=="insert")
			lblAddQuestions = new JLabel("ADD QUESTION's");
		else if(operation=="delete")
			lblAddQuestions = new JLabel("Delete QUESTION's");
		else 
			lblAddQuestions = new JLabel("Update QUESTION's");
		lblAddQuestions.setForeground(new Color(178, 34, 34));
		lblAddQuestions.setFont(new Font("Algerian", Font.BOLD | Font.ITALIC, 52));
		lblAddQuestions.setBounds(458, 11, 800, 47);
		frame.getContentPane().add(lblAddQuestions);
		
		JSeparator separator = new JSeparator();
		separator.setBackground(new Color(0, 255, 0));
		separator.setBounds(394, 56, 706, 2);
		frame.getContentPane().add(separator);
		
		JLabel lblQuestionId = new JLabel("Question ID :");
		lblQuestionId.setForeground(Color.ORANGE);
		lblQuestionId.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblQuestionId.setBounds(40, 96, 164, 33);
		frame.getContentPane().add(lblQuestionId);
		
		if(operation=="insert"){
			textField = new JTextField();
			textField.setBounds(224, 100, 212, 33);
			frame.getContentPane().add(textField);
			textField.addFocusListener(this);
			textField.setColumns(10);
			msg=new JLabel("");
			msg.setBounds(450,100,200,34);
			msg.setFont(new Font("Tahoma", Font.BOLD, 15));
			msg.setForeground(Color.ORANGE);
			frame.getContentPane().add(msg);

			comboBox = new JComboBox();
			comboBox.setBounds(223, 148, 206, 33);
			frame.getContentPane().add(comboBox);
			fillcombobox();
		}
		else{
			jcb=new JComboBox();
			jcb.setBounds(224, 100, 210, 34);
			frame.getContentPane().add(jcb);
			find=new JButton("Find Data");
			find.setBounds(450,100,100,30);
			find.addActionListener(this);
			frame.getContentPane().add(find);
			fillid();
			topic=new JTextField();
			topic.setBounds(223,148,206,33);
			frame.getContentPane().add(topic);
		}
			
		
		JLabel lblQuestion = new JLabel("Question :");
		lblQuestion.setForeground(Color.ORANGE);
		lblQuestion.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 25));
		lblQuestion.setBounds(40, 211, 164, 40);
		frame.getContentPane().add(lblQuestion);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(231, 221, 1101, 180);
		frame.getContentPane().add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 20));
		textArea.setForeground(Color.BLUE);
		
		
		JLabel lblOptions = new JLabel("OPTION'S :");
		lblOptions.setForeground(Color.ORANGE);
		lblOptions.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblOptions.setBounds(40, 436, 143, 26);
		frame.getContentPane().add(lblOptions);
		
		JLabel lblA = new JLabel("A.");
		lblA.setForeground(Color.GREEN);
		lblA.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblA.setBounds(223, 436, 27, 33);
		frame.getContentPane().add(lblA);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(272, 436, 467, 83);
		frame.getContentPane().add(scrollPane_1);
		
		textArea_1 = new JTextArea();
		scrollPane_1.setViewportView(textArea_1);
		textArea_1.setFont(new Font("Monospaced", Font.PLAIN, 20));
		
		JLabel lblB = new JLabel("B.");
		lblB.setForeground(Color.GREEN);
		lblB.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblB.setBounds(223, 549, 27, 26);
		frame.getContentPane().add(lblB);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(272, 549, 467, 93);
		frame.getContentPane().add(scrollPane_2);
		
		textArea_2 = new JTextArea();
		scrollPane_2.setViewportView(textArea_2);
		textArea_2.setFont(new Font("Monospaced", Font.PLAIN, 20));
		
		JLabel lblC = new JLabel("C.");
		lblC.setForeground(Color.GREEN);
		lblC.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblC.setBounds(798, 434, 33, 30);
		frame.getContentPane().add(lblC);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(854, 436, 467, 83);
		frame.getContentPane().add(scrollPane_3);
		
		textArea_3 = new JTextArea();
		scrollPane_3.setViewportView(textArea_3);
		textArea_3.setFont(new Font("Monospaced", Font.PLAIN, 20));
		
		JLabel lblD = new JLabel("D.");
		lblD.setForeground(Color.GREEN);
		lblD.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblD.setBounds(798, 546, 33, 33);
		frame.getContentPane().add(lblD);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(854, 549, 467, 93);
		frame.getContentPane().add(scrollPane_4);
		
		textArea_4 = new JTextArea();
		scrollPane_4.setViewportView(textArea_4);
		textArea_4.setFont(new Font("Monospaced", Font.PLAIN, 20));
		
		JLabel lblCorrectOption = new JLabel("Correct Option :");
		lblCorrectOption.setForeground(Color.ORANGE);
		lblCorrectOption.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblCorrectOption.setBounds(853, 95, 206, 33);
		frame.getContentPane().add(lblCorrectOption);
		
		bg=new ButtonGroup();
		A = new JRadioButton("A");
		A.setFont(new Font("Tahoma", Font.BOLD, 20));
		A.setBounds(1107, 101, 52, 23);
		B = new JRadioButton("B");
		B.setFont(new Font("Tahoma", Font.BOLD, 20));
		B.setBounds(1161, 101, 52, 23);
		C = new JRadioButton("C");
		C.setFont(new Font("Tahoma", Font.BOLD, 20));
		C.setBounds(1215, 101, 52, 23);
		D = new JRadioButton("D");
		D.setFont(new Font("Tahoma", Font.BOLD, 20));
		D.setBounds(1269, 101, 52, 23);
		bg.add(A);
		bg.add(B);
		bg.add(C);
		bg.add(D);
		frame.getContentPane().add(A);
		frame.getContentPane().add(B);
		frame.getContentPane().add(C);
		frame.getContentPane().add(D);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBackground(Color.RED);
		separator_1.setBounds(40, 412, 1291, 13);
		frame.getContentPane().add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBackground(Color.RED);
		separator_2.setBounds(40, 192, 1292, 8);
		frame.getContentPane().add(separator_2);
		
		if(operation=="insert")
			b1 = new JButton("SUBMIT");
		else if(operation=="delete")
			b1 = new JButton("DELETE");
		else if(operation=="update")
			b1 = new JButton("UPDATE");
		b1.setFont(new Font("Tahoma", Font.BOLD, 25));
		b1.setBounds(298, 676, 181, 51);
		b1.addActionListener(this);
		frame.getContentPane().add(b1);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ar) {
				System.exit(1);
			}
		});
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnCancel.setBounds(672, 676, 182, 51);
		frame.getContentPane().add(btnCancel);
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AdminPage window = new AdminPage();
				window.frame.setVisible(true);
				frame.hide();
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnBack.setBounds(10, 10, 108, 23);
		frame.getContentPane().add(btnBack);
		
		JLabel lblTopicId = new JLabel("Topic ID :");
		lblTopicId.setForeground(Color.ORANGE);
		lblTopicId.setBackground(new Color(255, 215, 0));
		lblTopicId.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTopicId.setBounds(40, 147, 132, 23);
		frame.getContentPane().add(lblTopicId);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(Question.class.getResource("/images/wallpaper2you_94367.jpg")));
		lblNewLabel.setBounds(0, 0, 1416, 762);
		frame.getContentPane().add(lblNewLabel);
		
	}

	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void focusLost(FocusEvent arg0) {
			boolean ret=getUserID(textField.getText());
			if (ret==true||textField.getText().length()==0)
			{
				if(textField.getText().length()==0)
					msg.setText("Empty Field...");
				else
					msg.setText("This ID iS Alredy Exits..Try another ID...");
				b1.setEnabled(false);
			}
			else
			{
				msg.setText("");
				b1.setEnabled(true);
			}
	}

	public void actionPerformed(ActionEvent ar) {
		if(ar.getSource()==b1)
		{
				try
				{
					if(operation=="insert")
					{
						String correct=coption();
						if(correct.length()==0||textField.getText().length()==0||textArea.getText().length()==0||textArea_1.getText().length()==0||textArea_2.getText().length()==0||textArea_3.getText().length()==0||textArea_4.getText().length()==0)
						{
							JOptionPane.showMessageDialog(this, "Please Enter all Fields..");
						}
						else
						{ 
							String studentid=textField.getText();
							String ques=textArea.getText();
							String topic=comboBox.getSelectedItem().toString();
							String op1=textArea_1.getText();
							String op2=textArea_2.getText();
							String op3=textArea_3.getText();
							String op4=textArea_4.getText();	
								String str="insert into question values(?,?,?,?,?,?,?,?,?)";
								st=con.prepareStatement(str);
								st.setString(1,studentid);
								st.setString(2,topic);
								st.setString(3,ques);
								st.setString(4,op1);
								st.setString(5,op2);
								st.setString(6,op3);
								st.setString(7,op4);
								st.setString(8,correct);
								st.setString(9,studentid);
								int q=st.executeUpdate();
								JOptionPane.showMessageDialog(this, "Data Saved...");
								textField.setText("");
								textArea.setText("");
								textArea_1.setText("");
								textArea_2.setText("");
								textArea_3.setText("");
								textArea_4.setText("");
								bg.clearSelection();
						}
					}
					else if(operation=="delete")
					{
						String id=jcb.getSelectedItem().toString();
						String str="delete from question where questionId=?";
						st=con.prepareStatement(str);
						st.setString(1,id);
						int q=st.executeUpdate();
						JOptionPane.showMessageDialog(this, "Data Deleted...");
						textArea.setText("");
						textArea_1.setText("");
						textArea_2.setText("");
						textArea_3.setText("");
						textArea_4.setText("");
					}
					else if(operation=="update")
					{
						String correct=coption();
						if(correct.length()==0||topic.getText().length()==0||textArea.getText().length()==0||textArea_1.getText().length()==0||textArea_2.getText().length()==0||textArea_3.getText().length()==0||textArea_4.getText().length()==0)
						{
							JOptionPane.showMessageDialog(this, "Please Enter all Fields..");
						}
						else
						{ 
							String studentid=jcb.getSelectedItem().toString();
							String ques=textArea.getText();
							String topic1=topic.getText();
							String op1=textArea_1.getText();
							String op2=textArea_2.getText();
							String op3=textArea_3.getText();
							String op4=textArea_4.getText();	
							String str="update question set question=?,optionA=?,optionB=?,optionC=?,optionD=?,correct=?,topicid=? where questionid=?";
							st=con.prepareStatement(str);
							st.setString(1,ques);
							st.setString(2,op1);
							st.setString(3,op2);
							st.setString(4,op3);
							st.setString(5,op4);
							st.setString(6,correct);
							st.setString(8,studentid);
							st.setString(7, topic1);
							int q=st.executeUpdate();
							JOptionPane.showMessageDialog(this, "Data Updated...");
							textArea.setText("");
							textArea_1.setText("");
							textArea_2.setText("");
							textArea_3.setText("");
							textArea_4.setText("");
						}
					}
				}
				catch(SQLException e){
					JOptionPane.showMessageDialog(this,"Check Internet Connection");
				}
				catch(Exception e){
					JOptionPane.showMessageDialog(this,"There are Some issue....try again");
				}
		}	
		else if(ar.getSource()==find)
		{
			if(jcb.getSelectedItem().toString().length()!=0){
			try {
				String sql="select question,optionA,optionB,optionC,optionD,topicid from question where questionId=?";
				st=con.prepareStatement(sql);
				st.setString(1,jcb.getSelectedItem().toString());
				rs=st.executeQuery();
				if(rs.next())
				{
					textArea.setText(rs.getString(1));
					textArea_1.setText(rs.getString(2));
					textArea_2.setText(rs.getString(3));
					textArea_3.setText(rs.getString(4));
					textArea_4.setText(rs.getString(5));
					topic.setText(rs.getString(6));
				}
			}
			catch(SQLException e){
				JOptionPane.showMessageDialog(this,"Check Internet Connection");
			}
			catch(Exception e){
				JOptionPane.showMessageDialog(this,"There are Some issue....try again");
			}
		}
		else
			JOptionPane.showMessageDialog(this.frame,"Empty Id..");
		}
	}
	public void fillid()
	{
		try
		{
			String str="select questionid from question";
			st=con.prepareStatement(str);
			rs=st.executeQuery();
			while(rs.next())
			{
				jcb.addItem(rs.getString(1).toString());
			}
		}
		catch(SQLException e){
			JOptionPane.showMessageDialog(this,"Check Internet Connection");
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(this,"There are Some issue....try again");
		}
	}
	public void fillcombobox()
	{
		try
		{
			String str="select topicid from topic";
			st=con.prepareStatement(str);
			rs=st.executeQuery();
			while(rs.next())
			{
				comboBox.addItem(rs.getString(1).toString());
			}
		}
		catch(SQLException e){
			JOptionPane.showMessageDialog(this,"Check Internet Connection");
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(this,"There are Some issue....try again");
		}
	}
	public String coption()
	{
		if(A.isSelected())
		{
			return "A";
		}
		else if(B.isSelected())
		{
			return "B";
		}
		else if(C.isSelected())
		{
			return "C";
		}
		else if(D.isSelected())
		{
			return "D";
		}
		else
			return "";
			
	}
	public boolean getUserID(String Id)
	{
		try
		{
			String str="select * from question where questionId=?";
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
}
