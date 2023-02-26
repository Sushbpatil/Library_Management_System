package Library_01;

import java.sql.*;
import java.util.*;

public class User_Information extends Library {
	static String name;
	static String choice_4;
	static String fqcn = "com.mysql.cj.jdbc.Driver";
	static String url = "jdbc:mysql://localhost:3306?user=root&password=Sushant@123";
	static PreparedStatement pstm = null;
	static Connection con = null;
	static ResultSet rs = null;
	static String qry_verify_user = "SELECT * FROM library.userinformation WHERE  UMOBILE= ?";
	static String qry_verify_pass = "SELECT * FROM library.userinformation WHERE UMOBILE=? AND UPASS=?";
	static String qry_insert_info = "insert into library.userinformation (UNAME,UMOBILE,Uemail,UPASS) values (?,?,?,?) ";
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		System.out.println();
		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("                                                    <-- USER PAGE -->       ");
		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------------");

		choice_3();
	}

	public static void choice_3() {

		System.out.println("                                ");
		System.out.println("IF YOU ARE NEW USER THEN PLEASE FIRST SIGN UP -->  ");

		System.out.println("ENTER 1 -->  TO SIGN-UP  ");
		System.out.println("ENTER 2 -->  TO LOGIN ");

		System.out.print("ENTER YOUR CHOICE -->");

		String choice_3 = sc.next();
		switch (choice_3) {

		case "1":
			signUp();
			break;

		case "2":
			logiN();
			break;

		default:
			System.out.println("ENTER VALID INPUT");
			System.out.println();
			System.out.print("<--ENTER VALID CHOICE -->");
			System.out.println();
			choice_3();
		}

	}

	public static void User_info_choice() {
		String choice_1 = null;
		do {
			System.out.println("                                ");
			System.out.println("ENTER 1 --> SIGNUP ");
			System.out.println("ENTER 2 --> LOGIN ");
			System.out.println("ENTER 3 --> TO EXIT");
			System.out.print("ENTER YOUR CHOICE -->");

			choice_1 = sc.next();

			switch (choice_1) {

			case "1":
				signUp();
				break;
			case "2":
				logiN();
				break;
			case "3":
				System.out.println(
						"------------------------------------------------------------------------------------------------------------------------------------");
				System.out.println("                                                   THANK YOU    ");
				System.out.println("                                                HAVE A GREAT DAY    ");

				System.out.println(
						"------------------------------------------------------------------------------------------------------------------------------------");

				break;
			default:
				System.out.println("ENTER VALID INPUT");
				System.out.println();
				System.out.print("<--ENTER VALID CHOICE -->");
				System.out.println();
				finalChoice();
			}
		} while (Integer.parseInt(choice_1) != 4);

	}

	public static void signUp() {

		System.out.println();
		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("                                                    <-- SIGN-UP PAGE -->       ");
		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------------");
		choiceSignUp();
	}

	public static void choiceSignUp() {

		System.out.println("Enter First Name --> ");
		String fname = sc.next();

		System.out.println("Enter Middle Name --> ");
		String mname = sc.next();

		System.out.println("Enter Last Name --> ");
		String lname = sc.next();

		name = fname + " " + mname + " " + lname;

		System.out.print("Enter MobileNO --> ");
		String mobileno = sc.next();

		try {
			Class.forName(fqcn);
			con = DriverManager.getConnection(url);
			pstm = con.prepareStatement(qry_verify_user);

			pstm.setString(1, mobileno);

			rs = pstm.executeQuery();

			if (rs.next()) {
				System.out.println("Entered Mobile No is Already Exist ");
				System.out.print("Enter Valid Mobile No --> ");
				mobileno = sc.next();
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {

			if (rs == null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstm == null) {
				try {
					pstm.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con == null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		char[] ch = mobileno.toCharArray();
		for (int i = 0; i < ch.length; i++) {

			if (mobileno.charAt(0) >= '0' || mobileno.charAt(0) <= '9') {
				if (ch.length != 10) {
					System.out.print("Enter Valid Mobile No --> ");
					mobileno = sc.next();
				}
			} else {
				System.out.print("Enter Valid Mobile No --> ");
				mobileno = sc.next();
			}
		}
		System.out.print("Enter Email --> ");
		String email = sc.next();
		if (email.endsWith("@gmail.com")) {

		} else {
			System.out.print("Enter Valid Email --> ");
			email = sc.next();
		}
		System.out.print("Enter Password -->");
		String pass = sc.next();
		if (pass.length() <= 8) {
			System.out.print("Enter Strong Password -->");
			pass = sc.next();
		}

		try {

			pstm = con.prepareStatement(qry_insert_info);
			pstm.setString(1, name);
			pstm.setString(2, mobileno);
			pstm.setString(3, email);
			pstm.setString(4, pass);
			pstm.executeUpdate();
			System.out.println("SIGN UP SUCCESFULL");

//			login();
			User_info_choice();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			if (rs == null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstm == null) {
				try {
					pstm.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con == null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void logiN() {

		System.out.println();
		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("                                                    <-- LOGIN PAGE -->       ");
		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("                                ");

		try {
			Class.forName(fqcn);
			con = DriverManager.getConnection(url);
			System.out.print("Enter Mobile Number -->");
			String i_mobile = sc.next();
			System.out.print("Enter Password -->");
			String i_pass = sc.next();
			pstm = con.prepareStatement(qry_verify_pass);
			pstm.setString(1, i_mobile);
			pstm.setString(2, i_pass);
			rs = pstm.executeQuery();
			boolean b = false;
			if (rs.next()) {
				System.out.println("                                ");
				System.out.println("You have been Succesfully Logged");
				System.out.println("                                ");

				b = true;
			} else {
				System.out.println("Invalid MobileNo or Password ");
				System.out.print("Enter valid mobile number  -->");
				i_mobile = sc.next();
				System.out.print("Enter valid password -->");
				i_pass = sc.next();
				pstm = con.prepareStatement(qry_verify_pass);
				pstm.setString(1, i_mobile);
				pstm.setString(2, i_pass);
				rs = pstm.executeQuery();
				if (rs.next()) {
					System.out.println("                                ");
					System.out.println("You have been Succesfully Logged");
					System.out.println("                                ");
					b = true;
				}
			}
			if (b == false) {
				System.out.println("Please SignUp first");
				signUp();
			}
			rs = pstm.executeQuery();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			if (rs == null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstm == null) {
				try {
					pstm.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con == null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		loginINFO();
	}

	public static void loginChoice() {
		do {
			System.out.println("                                              ");
			System.out.println("ENTER 1 -->  TO LOGIN MENU  ");
			System.out.println("ENTER 2 -->  TO MAIN MENU ");
			System.out.println("ENTER  -->  TO EXIT");
			System.out.println("                                              ");
			System.out.print("ENTER YOUR CHOICE -->");
			choice_4 = sc.next();
			switch (choice_4) {
			case "1":
				issueBook();
				break;
			case "2":
				break;
			case "3":
				System.out.println("THANK YOU !!!");
				break;

			default:
				System.out.println("ENTER VALID INPUT");
				System.out.println();
				System.out.print("<--ENTER VALID CHOICE -->");
				System.out.println();
				loginChoice();
			}
		} while (Integer.parseInt(choice_4) != 3);

	}

	public static void loginINFO() {

		do {
			System.out.println("                                              ");
			System.out.println("ENTER 1 -->  TO ISSUE A BOOK  ");
			System.out.println("ENTER 2 -->  TO SEE THE LIBRARY BOOKS ");
			System.out.println("ENTER 3 -->  TO RETURN A BOOK ");
			System.out.println("ENTER 4 -->  TO EXIT");
			System.out.println("                                              ");
			System.out.print("ENTER YOUR CHOICE -->");
			choice_4 = sc.next();
			switch (choice_4) {
			case "1":
				issueBook();
				break;
			case "2":
				viewLibrary();
				break;
			case "3":
				returnBook();
				break;
			case "4":
				System.out.println("THANK YOU !!!");
				break;

			default:
				System.out.println("ENTER VALID INPUT");
				System.out.println();
				System.out.print("<--ENTER VALID CHOICE -->");
				System.out.println();
				loginINFO();
			}
		} while (Integer.parseInt(choice_4) != 4);

	}

	public static void viewLibrary() {

		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("                         <-- BOOKS INFORMATION --> ");
		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------------");
		boolean b = false;
		try {
			Class.forName(fqcn);
			con = DriverManager.getConnection(url);
			pstm = con.prepareStatement("Select * from library.library");
			rs = pstm.executeQuery();
			b = true;
			while (rs.next()) {
				System.out.print(rs.getInt("BOOK_ID") + " ");
				System.out.print(rs.getString("BOOK_NAME") + "  ");
				System.out.print(rs.getString("BOOK_AUTHOR") + "  ");
				System.out.print(rs.getString("NO_OF_BOOKS") + "  ");
				System.out.print(rs.getString("BOOK_PRICE") + "  ");
				System.out.println("");
				System.out.println(
						"------------------------------------------------------------------------------------------------------------------------------------");
				b = false;
			}
			if (b == true) {
				System.out.println("Table is empty");
				loginINFO();
			}

			loginINFO();

		} catch (Exception e) {

			e.getMessage();
		} finally {

			if (rs == null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstm == null) {
				try {
					pstm.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con == null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void returnBook() {
		System.out.println();
		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("                                                    <-- BOOK RETURN PAGE -->       ");
		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("                                ");
		String return_book = "UPDATE library.library  SET NO_of_Books = NO_of_Books + 1 WHERE Book_id = ?";

		String insert_tr = "insert into library.lib_transactions (book_id,User_id,Type) values (?,?,'Return')";
		try {
			Class.forName(fqcn);
			con = DriverManager.getConnection(url);
			// RETURN A BOOK
			pstm = con.prepareStatement(return_book);
			System.out.println("Enter Book_id");
			int Book_id = sc.nextInt();
			pstm.setInt(1, Book_id);
			System.out.println("Enter C_id");
			int cid = sc.nextInt();
			pstm.executeUpdate();
			pstm = con.prepareStatement(insert_tr);
			pstm.setInt(1, Book_id);
			pstm.setInt(2, cid);
			pstm.executeUpdate();
			System.out.println("Book REturned Succesfully !!!");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			if (pstm == null) {
				try {
					pstm.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con == null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void issueBook() {
		System.out.println();
		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("                                                    <-- BOOK ISSUE PAGE -->       ");
		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("                                ");

		String issue_book = "UPDATE library.library  SET NO_of_Books = NO_of_Books - 1 WHERE Book_id = ?";

		String insert_tr = "insert into library.lib_transactions (book_id,User_id,Type) values (?,?,'ISSUE')";

		try {
			Class.forName(fqcn);
			con = DriverManager.getConnection(url);
			// RETURN A BOOK
			pstm = con.prepareStatement(issue_book);
			System.out.println("Enter Book_id");
			int Book_id = sc.nextInt();
			pstm.setInt(1, Book_id);
			System.out.println("Enter C_id");
			int cid = sc.nextInt();
			pstm.executeUpdate();
			pstm = con.prepareStatement(insert_tr);
			pstm.setInt(1, Book_id);
			pstm.setInt(2, cid);
			pstm.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
