import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Date;
import java.util.Calendar;

import javax.swing.*;

import conn.DatabaseConnect;
import conn.SendResult;

public class TestPage extends JFrame implements ActionListener,Runnable{
	
	Connection con;
	PreparedStatement ps,ps1,ps2,ps3,ps4,ps5;
	ResultSet rs,rs1,rs2,rst,rs3;
	JLabel jl1,A,B,C,D,head,l1,LastMsg,option;
	JTextArea ques;
	ButtonGroup bg;
	JRadioButton r1,r2,r3,r4;
	JButton next,verify;
	int q=1,marks=0,i=0,min=1,sec=59;
	int resultid=0;
	String subject,topic_id,student_id;
	int total;
	Thread t=new Thread(this);
	
	Date d=new Date(Calendar.getInstance().getTime().getTime());
	
	public TestPage(String topic,String id)
	{
		student_id=id;
		subject=topic;
		t.start();
		setLayout(null);
		
		head=new JLabel("MY EXAM SHEET");
		head.setForeground(Color.GREEN);
		head.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 55));
		head.setBounds(280,30,700,50);
		add(head);
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(255, 0, 0));
		separator.setBounds(250,90,600,30);
		add(separator);
		
		
		jl1=new JLabel("Q"+q+".");
		jl1.setBounds(50,130,100,50);
		jl1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));
		add(jl1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(130,130,1000,119);
		add(scrollPane);
		
		ques=new JTextArea("");
		ques.setForeground(Color.BLACK);
		ques.setFont(new Font("Tahoma", Font.BOLD, 20));
		scrollPane.setViewportView(ques);
		ques.setEditable(false);
		
		LastMsg=new JLabel("");
		LastMsg.setBounds(350,235,1000,50);
		LastMsg.setForeground(Color.PINK);
		LastMsg.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));
		add(LastMsg);
		
		option=new JLabel("Options:");
		option.setBounds(10,250,100,50);
		add(option);
		option.setForeground(Color.BLACK);
		option.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		
		A=new JLabel("A.");
		A.setBounds(100,250,50,50);
		A.setForeground(Color.orange);
		A.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		add(A);
		B=new JLabel("B.");
		B.setBounds(100,300,50,50);
		B.setForeground(Color.orange);
		B.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		add(B);
		C=new JLabel("C.");
		C.setBounds(100,350,50,50);
		C.setForeground(Color.orange);
		C.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		add(C);
		D=new JLabel("D.");
		D.setBounds(100,400,50,50);
		D.setForeground(Color.orange);
		D.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		add(D);
		bg=new ButtonGroup();
		r1=new JRadioButton("");
		r1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		r2=new JRadioButton("");
		r2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		r3=new JRadioButton("");
		r3.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		r4=new JRadioButton("");
		r4.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		r1.setBounds(130,250,1000,50);
		r2.setBounds(130,300,1000,50);
		r3.setBounds(130,350,1000,50);
		r4.setBounds(130,400,1000,50);
		r1.addActionListener(this);
		r2.addActionListener(this);
		r3.addActionListener(this);
		r4.addActionListener(this);
		bg.add(r1);
		bg.add(r2);
		bg.add(r3);
		bg.add(r4);
		add(r1);add(r2);add(r3);add(r4);
		
		DatabaseConnect obj=new DatabaseConnect();
		con=obj.Connect();
		try{
			String stl="select question,optionA,optionB,optionC,optionD,questionId,topicid from question where topicid=?"; 
			ps1=con.prepareStatement(stl);
			ps1.setString(1,subject);
			rs1=ps1.executeQuery();
			if(rs1.next())
			{
				topic_id=rs1.getString(7);
				ques.setText(rs1.getString(1));
				r1.setText(rs1.getString(2));
				r2.setText(rs1.getString(3));
				r3.setText(rs1.getString(4));
				r4.setText(rs1.getString(5));
			}
			else
			{
				JOptionPane.showMessageDialog(this,"No Question To Show in the Subject");
			}
			String str1="select count(resultid) from result";
			ps3=con.prepareStatement(str1);
			rs2=ps3.executeQuery();
			while(rs2.next())
			{
				resultid=rs2.getInt(1);
			}
		}
		catch(SQLException e){
			JOptionPane.showMessageDialog(this,"Check Internet Connection");
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(this,"There are Some issue....try again");
		}
		
		next=new JButton("Next");
		next.setBounds(300,500,200,50);
		next.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));
		next.addActionListener(this);
		add(next);
		
		l1=new JLabel("Time Left:-"+min+":"+sec);
		l1.setBounds(1000,10,500,50);
		l1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 25));
		l1.setForeground(Color.yellow);
		add(l1);
		getContentPane().setBackground(Color.gray);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(UserPage.class.getResource("/images/background_20.wide.jpeg")));
		lblNewLabel_1.setBounds(0, 0, 1400, 800);
		add(lblNewLabel_1);
		
		setSize(1400, 800);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public static void main(String arg[])
	{
		new TestPage("java","test");
	}
	
	public void run(){
		do{
			try{
				Thread.sleep(1000);
				if(min==0&&sec==0)
				{
					min=-1;
					sec=-1;
					JOptionPane.showMessageDialog(this, "Time's Up...");
					submitResult();
				}
				else{
					if(sec==0){
						min--;
						sec=59;
					}
					else
						sec--;
				}
				l1.setText("Time Left::["+min+":"+sec+"]");
			}
			catch(Exception e){}
		}while(min!=-1&&sec!=-1);
	}
	
	String opt="";
	public void actionPerformed(ActionEvent a) {
		if(a.getSource()==r1){
			opt="A";
		}
		else if(a.getSource()==r2){
			opt="B";
		}
		else if(a.getSource()==r3){
			opt="C";
		}
		else if(a.getSource()==r4){
			opt="D";
		}
		else if(a.getSource()==next){				
			try{
				String str="select correct from question where questionid=?";
				ps=con.prepareStatement(str);
				ps.setString(1,rs1.getString(6));
				rs=ps.executeQuery();
				if(rs.next()){
					//i++; 
					if(opt.equals(rs.getString(1)))
						++marks;
				}
				
				String cq="insert into checkquestion values(?,?,?,?,?,?)";
				ps4=con.prepareStatement(cq);
				ps4.setString(1,null);
				ps4.setString(2,student_id);
				ps4.setString(3,rs1.getString(6));
				ps4.setString(4,topic_id);
				ps4.setString(5,opt);
				ps4.setString(6,"Check");
				ps4.executeUpdate();
				
				if(rs1.next())
				{
					jl1.setText("Q"+(++q)+"->");
					ques.setText(rs1.getString(1));
					r1.setText(rs1.getString(2));
					r2.setText(rs1.getString(3));
					r3.setText(rs1.getString(4));
					r4.setText(rs1.getString(5));
					repaint();
				}
				else{
					submitResult();
				}
			}
			catch(Exception e){
				JOptionPane.showMessageDialog(this, e.toString());
				System.exit(1);
			}
			opt="";
			bg.clearSelection();
		}
	}
	public void submitResult() throws Exception
	{
		String st1="select count(question) from question where topicid=?"; 
		ps5=con.prepareStatement(st1);
		ps5.setString(1, subject);
		rs3=ps5.executeQuery();
		while(rs3.next()){
			i=rs3.getInt(1);
		}
		
		String sql="insert into result values(?,?,?,?,?,?,?)";
		ps2=con.prepareStatement(sql);
		ps2.setInt(1, resultid+1);
		ps2.setString(2, student_id);
		ps2.setString(3, topic_id);
		ps2.setInt(4, i);
		ps2.setInt(5, marks);
		ps2.setDate(6, d);
		if(i/2>=marks)
			ps2.setString(7, "Pass");
		else
			ps2.setString(7, "Fail");
		ps2.executeUpdate();
		JOptionPane.showMessageDialog(this,"Completed...");
		
		String cq1="update checkquestion set resultid=? where resultid is null";
		ps4=con.prepareStatement(cq1);
		ps4.setInt(1,resultid+1);
		ps4.executeUpdate();
		con.close();
		
		LastMsg.setText("Thank You For appearing this test");
		JSeparator separator1 = new JSeparator();
		separator1.setBackground(Color.RED);
		separator1.setBounds(350,290,520,50);
		add(separator1);
		
		new SendResult(student_id,resultid+1);
		
		option.setText("");
		bg.clearSelection();
		jl1.setText("");
		ques.setVisible(false);
		A.setVisible(false);
		B.setVisible(false);
		C.setVisible(false);
		D.setVisible(false);
		r1.setVisible(false);
		r2.setVisible(false);
		r3.setVisible(false);
		r4.setVisible(false);
		l1.setVisible(false);
		next.setVisible(false);
		t.stop();
		
	
	}
}
