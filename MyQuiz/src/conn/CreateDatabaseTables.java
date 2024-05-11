package conn;
import java.sql.*;

import javax.swing.JOptionPane;
public class CreateDatabaseTables {
	Connection cn;
	PreparedStatement ps;
	CreateDatabaseTables(){
		try{
			DatabaseConnect db=new DatabaseConnect();
			cn=db.Connect();
			
			String admin="CREATE TABLE `admin` ("+
						"`adminid` varchar(50) NOT NULL,"+
						"`passwrd` varchar(50) DEFAULT NULL,"+
						"`name` varchar(50) DEFAULT NULL,"+
						"`remarks` varchar(100) DEFAULT NULL,"+
						"PRIMARY KEY (`adminid`)"+
						") ENGINE=InnoDB DEFAULT CHARSET=utf8;";
			ps=cn.prepareStatement(admin);
			ps.executeUpdate();
			
			String str="insert into admin values('admin','admin123','Admin','admin')";
			ps=cn.prepareStatement(str);
			ps.executeUpdate();
			
			String student="CREATE TABLE `student` ("+
						  "`studentid` varchar(50) NOT NULL,"+
						  "`passwrd` varchar(50) DEFAULT NULL,"+
						  "`name` varchar(50) DEFAULT NULL,"+
						  "`email` varchar(50) DEFAULT NULL,"+
						  "`phone` varchar(20) DEFAULT NULL,"+
						  "`dob` varchar(50) DEFAULT NULL,"+
						  "`gender` varchar(20) DEFAULT NULL,"+
						  "`remarks` varchar(100) DEFAULT NULL,"+
						  "PRIMARY KEY (`studentid`)"+
						") ENGINE=InnoDB DEFAULT CHARSET=utf8;";
			ps=cn.prepareStatement(student);
			ps.executeUpdate();
			
			String topic="CREATE TABLE `topic` ("+
						  "`topicid` varchar(50) NOT NULL,"+
						  "`topic` varchar(50) DEFAULT NULL,"+
						  "`remarks` varchar(100) DEFAULT NULL,"+
						  "PRIMARY KEY (`topicid`)"+
						  ") ENGINE=InnoDB DEFAULT CHARSET=utf8;";
			ps=cn.prepareStatement(topic);
			ps.executeUpdate();
			
			String result="CREATE TABLE `result` ("+
						  "`resultid` int(50) NOT NULL,"+
						  "`studentid` varchar(50) DEFAULT NULL,"+
						  "`topicid` varchar(50) DEFAULT NULL,"+
						  "`TotalMarks` int(11) DEFAULT NULL,"+
						  "`obtainedmarks` int(11) DEFAULT NULL,"+
						  "`Date` date DEFAULT NULL,"+
						  "`Result` varchar(100) DEFAULT NULL,"+
						  "PRIMARY KEY (`resultid`),"+
						  "KEY `result_ibfk_2` (`topicid`),"+
						  "KEY `result_ibfk_1` (`studentid`),"+
						  "CONSTRAINT `result_ibfk_1` FOREIGN KEY (`studentid`) REFERENCES `student` (`studentid`)"+
						  ") ENGINE=InnoDB DEFAULT CHARSET=utf8;";
			ps=cn.prepareStatement(result);
			ps.executeUpdate();
			
			String question="CREATE TABLE `question` ("+
							  "`questionId` varchar(20) NOT NULL,"+
							  "`topicid` varchar(20) DEFAULT NULL,"+
							  "`question` varchar(200) DEFAULT NULL,"+
							  "`OptionA` varchar(200) DEFAULT NULL,"+
							  "`OptionB` varchar(200) DEFAULT NULL,"+
							  "`OptionC` varchar(200) DEFAULT NULL,"+
							  "`OptionD` varchar(200) DEFAULT NULL,"+
							  "`correct` varchar(50) DEFAULT NULL,"+
							  "`remarks` varchar(100) DEFAULT NULL,"+
							"  PRIMARY KEY (`questionId`)"+
							") ENGINE=InnoDB DEFAULT CHARSET=utf8;";
			ps=cn.prepareStatement(question);
			ps.executeUpdate();

			String checkques="CREATE TABLE `checkquestion` ("+
							 " `resultid` int(11) DEFAULT NULL,"+
							 " `studentid` varchar(50) DEFAULT NULL,"+
							 " `questionid` varchar(50) DEFAULT NULL,"+
							 " `topicid` varchar(50) DEFAULT NULL,"+
							 " `pickoption` varchar(50) DEFAULT NULL,"+
							  "`remarks` varchar(100) DEFAULT NULL,"+
							  "KEY `checkquestion_ibfk_1` (`studentid`),"+
							  "KEY `checkquestion_ibfk_2` (`questionid`),"+
							  "KEY `checkquestion_ibfk_3` (`topicid`),"+
							 " KEY `checkquestion_ibfk_4` (`resultid`),"+
							 " CONSTRAINT `checkquestion_ibfk_1` FOREIGN KEY (`studentid`) REFERENCES `student` (`studentid`),"+
							  "CONSTRAINT `checkquestion_ibfk_2` FOREIGN KEY (`questionid`) REFERENCES `question` (`questionId`),"+
							  "CONSTRAINT `checkquestion_ibfk_3` FOREIGN KEY (`topicid`) REFERENCES `topic` (`topicid`),"+
							  "CONSTRAINT `checkquestion_ibfk_4` FOREIGN KEY (`resultid`) REFERENCES `result` (`resultid`)"+
							") ENGINE=InnoDB DEFAULT CHARSET=utf8;";
			ps=cn.prepareStatement(checkques);
			ps.executeUpdate();
			JOptionPane.showMessageDialog(null, "Table Created..");
		
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, e.toString());
		}
	}
	
	public static void main(String[] args) {
		new CreateDatabaseTables();
		
	}

}
