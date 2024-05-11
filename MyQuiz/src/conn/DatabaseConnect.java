package conn;
import java.sql.*;
import javax.swing.JOptionPane;

public class DatabaseConnect {
	Connection con;
	public Connection Connect(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://sql12.freemysqlhosting.net:3306/sql12292767","sql12292767","qFjs4WpNbw");  
			
		}
		catch(SQLException e){
			JOptionPane.showMessageDialog(null,"Check Connection"+e.toString());
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null,"There are Some issue....try again");
		}
		return con;
	}
}
