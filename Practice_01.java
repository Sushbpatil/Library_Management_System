package Library_01;

import java.sql.*;
import java.util.*;

//Insert data into table(CRUD)operation
//Library & Member Table
public class Practice_01 {
	static Scanner sc = new Scanner(System.in);
	static String fqcl = "com.mysql.cj.jdbc.Driver";
	static String url = "jdbc:mysql://localhost:3306?user=root&password=Sushant@123";
	static String q_login = "insert into library.login values(?,?,?)";
	static String q_insert_book = "insert into library.login values (?,?,?,?,?)";
	static String r_retriev="Select * from library.library ";
	static String see="SELECT * FROM library.library";
	static PreparedStatement pstmt = null;
	static Connection con = null;
	static ResultSet rs=null;
	
	public static void main(String[] args) {

		System.out.println("Welcome to Library");
		System.out.println("Enter 1 for Signup");
		System.out.println("Enter 2 for Issue Book");
		System.out.println("Enter 3 for To See Library Books");
		System.out.println("Enter 4 for to Return book");
		System.out.println("Enter 5 for to Add book into Library");
		System.out.println("Enter Number: ");
		try {
			// Creating a platform or Registering a Driver class
			Class.forName(fqcl);

			// Connect with the DataBase
			con = DriverManager.getConnection(url);
			int n=sc.nextInt();
			switch (n) {
			case 1:
			   loginn();
			   break;
			   
			case 2: 
				retrieve();
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void loginn() {
		// Create a Statement or Platform to Execute the Sql Statements
		try {
				System.out.println("Sign Up Page");
				System.out.println("------------------------------------------------------------");
				System.out.print("Enter Id : ");
				int M_id = sc.nextInt();
				System.out.print("Enter Name : ");
				String M_name = sc.next();	
				System.out.print("Enter Password : ");
				String pass = sc.next();
				pstmt = con.prepareStatement(q_login);
				pstmt.setInt(1, M_id);
				pstmt.setString(2, M_name);
				pstmt.setString(3, pass);
				pstmt.executeUpdate();
				System.out.println("Data Inserted");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Want to Perform Other Operations");
		System.out.println("Enter y for other operations");
		System.out.println("Enter any other key to exit");
		String pole = sc.next();
		if (pole == "y") {
			loginn();
		} else {
			System.out.println("Thank You !!!!");
		}

	}
	
	public static void retrieve()
	{
		try {
		System.out.println(" Library ");
		System.out.println("------------------------------------------------------------");
		pstmt=con.prepareStatement(see);
//		int id=rs.getInt();
		ResultSet rs=pstmt.executeQuery();
		while(rs.next())
		{
			
			System.out.print(rs.getInt("Book_id")+" ");
			System.out.print(rs.getString("Book_Name")+ " ");
			System.out.print(rs.getString("Author")+"  ");
			System.out.println(rs.getInt("Price"));
		}
	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		

//		//GET NAME WITH THE HELP OF ID
//		String bName="select Book_name from library.library where Book_id=? ";
//		String uName="select Uname from library.userinformation where Uid=?";
//	   	pstm.setInt(1, Book_id);
//		pstm=con.prepareStatement(bName);
//		rs=pstm.executeQuery();
//		while(rs.next())
//		{
//			BookName= rs.getString("Book_Name");
//		}
//		pstm.setInt(1, cid);
//		pstm=con.prepareStatement(uName);
//		rs=pstm.executeQuery();
//		while(rs.next())
//		{
//			UserName=rs.getString("Uname");
//			
//		}
//		
		//INSERT INTO TRANSACTION TABLE
	}
}

