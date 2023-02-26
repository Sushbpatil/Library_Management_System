package Library_01;

import java.sql.*;
import java.util.*;

public class Library {

	static String fqcn = "com.mysql.cj.jdbc.Driver";
	static String url = "jdbc:mysql://localhost:3306?user=root&password=Sushant@123";
	static String qry_insert = "insert into  library.library values (?,?,?,?,?)";
	static Scanner sc = new Scanner(System.in);
	static String library_user = "Library01";
	static String lib_pass = "Lib@123";
	static PreparedStatement pstm = null;
	static Connection con = null;
	static ResultSet rs = null;

	public static void main(String[] args) {

		System.out.println();
		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("                                                    <-- WELCOME TO LIBRARY  -->       ");
		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------------");

		System.out.println("                                ");
		System.out.println("ENTER 1 --> IF YOU ARE ADMIN ");
		System.out.println("ENTER 2 --> IF YOU ARE CUSTOMER");
		System.out.print("ENTER YOUR CHOICE -->");
		String choice_2 = sc.next();
		switch (choice_2) {

		case "1":
			LibraryLogin();
			;
			break;

		case "2":
			User_Information.main(null);
			break;
		default:
			System.out.println("ENTER VALID INPUT");
			System.out.println();
			System.out.print("<--ENTER VALID CHOICE -->");
			System.out.println();
			main(null);
		}

	}

	public static void LibraryLogin() {

		System.out.print("Enter username -->");
		String b_user = sc.next();

		System.out.print("Enter Password -->");
		String b_pass = sc.next();

		if (b_user.equals(library_user) && b_pass.equals(lib_pass)) {
			System.out.println("You have been SuccesFully logged");

		} else {
			System.out.println("Enter valid Username & Password");
			LibraryLogin();
		}
		choice_01();

	}

	public static void choice_01() {
		System.out.println("                                ");
		System.out.println("ENTER 1 --> TO INSERT BOOK INTO LIBRARY ");
		System.out.println("ENTER 2 --> TO VIEW BOOKS FROM THE LIBRARY");
		System.out.println("ENTER 3 --> TO SEE TRANSACTIONS");
		System.out.println("ENTER 4 --> TO SEE USER INFORMATION");
		System.out.print("ENTER YOUR CHOICE -->");
		String choice_2 = sc.next();
		switch (choice_2) {

		case "1":
			InsertBook();
			;
			break;

		case "2":
			viewBook();
			break;
		case "3":
			viewTransactions();
			break;

		case "4":
			userInformation();
			break;

		default:
			System.out.println("ENTER VALID INPUT");
			System.out.println();
			System.out.print("<--ENTER VALID CHOICE -->");
			System.out.println();
			choice_01();
		}

	}

	public static void InsertBook() {
		System.out.print("Enter Book_Id --> ");
		int id = sc.nextInt();
		System.out.println("Enter Book_Name -->");
		String name = sc.next();
		System.out.println("Enter Author Name -->");
		String A_name = sc.next();
		System.out.println("Enter No_of_Books -->");
		int no = sc.nextInt();
		System.out.println("Enter Price of One book -->");
		int price = sc.nextInt();
		try {
			Class.forName(fqcn);
			con = DriverManager.getConnection(url);
			pstm = con.prepareStatement(qry_insert);
			pstm.setInt(1, id);
			pstm.setString(2, name);
			pstm.setString(3, A_name);
			pstm.setInt(4, no);
			pstm.setInt(5, price);
			pstm.executeUpdate();
			System.out.println("Data Inserted");

			finalChoice();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void viewBook() {

		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("                         <-- BOOKS INFORMATION --> ");
		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------------");

		try {

			Class.forName(fqcn);
			con = DriverManager.getConnection(url);
			pstm = con.prepareStatement("Select * from library.library");
			rs = pstm.executeQuery();
			boolean b = true;
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
			}
			choice_01();

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
		finalChoice();
	}

	public static void viewTransactions() {
		try {
			Class.forName(fqcn);
			con = DriverManager.getConnection(url);
			pstm = con.prepareStatement("Select * from library.lib_transactions");
			rs = pstm.executeQuery();
			boolean b = true;
			while (rs.next()) {
				System.out.print(rs.getInt("TRANSACTION_ID") + " ");
				System.out.print(rs.getString("BOOK_ID") + "  ");
				System.out.print(rs.getString("USER_ID") + "  ");
				System.out.print(rs.getString("Type") + " ");
				System.out.println("");
				System.out.println(
						"------------------------------------------------------------------------------------------------------------------------------------");
				b = false;

			}
			if (b == true) {
				System.out.println("TABLE IS EMPTY");
			}

			choice_01();
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
		finalChoice();
	}

	public static void userInformation() {
		try {
			Class.forName(fqcn);
			con = DriverManager.getConnection(url);
			pstm = con.prepareStatement("Select * from library.userinformation");
			rs = pstm.executeQuery();
			boolean b = true;
			while (rs.next()) {
				System.out.print(rs.getInt("UID") + " ");
				System.out.print(rs.getString("Uname") + "  ");
				System.out.print(rs.getString("Umobile") + "  ");
				System.out.print(rs.getString("Uemail") + " ");
				System.out.print(rs.getString("Upass") + " ");
				System.out.println("");
				System.out.println(
						"------------------------------------------------------------------------------------------------------------------------------------");
				b = false;
			}
			if (b == true) {
				System.out.println("TABLE IS EMPTY");
			}
			choice_01();

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
		finalChoice();

	}

	public static void finalChoice() {
		String choice_1 = null;
		do {
			System.out.println("                                ");
			System.out.println("ENTER 1 --> LIBRARY MENU ");
			System.out.println("ENTER 2 --> USER PAGE ");
			System.out.println("ENTER 3 --> TO EXIT");
			System.out.print("ENTER YOUR CHOICE -->");

			choice_1 = sc.next();

			switch (choice_1) {

			case "1":
				choice_01();
				break;
			case "2":
				User_Information.choice_3();
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
		} while (Integer.parseInt(choice_1) != 3);
	}

}
