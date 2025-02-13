package dynamic;
import java.util.*;
import java.sql.*;
public class sqldynamic {
	int id;
	String name,pass;
	String firpass,secpass;
public static Connection connect() {
		try {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3309/Registration","root","dbms");
		if(con!=null) {
			return con;
		}
		}
		catch(Exception e){
			System.out.println(e);
		}
		return null;
}
	public static void createTable() throws SQLException{
		Connection con=connect();
		String qry="create table signup(id int(10),username varchar(20),pass varchar(20))";
		PreparedStatement pre=con.prepareStatement(qry);
		System.out.println(pre);
		pre.executeUpdate();
		
	}
	
	
	static void insert()  throws SQLException {
		Scanner scan=new Scanner(System.in);
		System.out.print("ID :");
		int id=scan.nextInt();
		System.out.print("Name :");
		String name=scan.next();
		System.out.print("Pass :");
		String pass=scan.next();
		System.out.println("Reset pass 1:");
		int n=scan.nextInt();
		 if (n == 1) {
	            System.out.print("Enter new password: ");
	            String firpass = scan.next();
	            System.out.print("Confirm new password: ");
	            String secpass = scan.next();

	            if (firpass.equals(secpass)) {
	            String qry = "update signup set username=?,pass=? where id=? ";
	            try (Connection con = connect(); PreparedStatement pre = con.prepareStatement(qry)) {
	                pre.setInt(3, id);
	                pre.setString(1, name);
	                pre.setString(2, firpass);
	                pre.executeUpdate();
	                System.out.println("Data inserted successfully.");
	            } catch (SQLException e) {
	                System.out.println("SQL Error during insert: " + e.getMessage());
	            }
		}
	            else {
	            	System.out.print("Password is Incorrect");
	            }
		 }
		else {
		String qry="insert into signup(id,username,pass)values(?,?,?)";
		Connection con=connect();
		PreparedStatement pre=con.prepareStatement(qry);
		pre.setInt(1,id);
		pre.setString(2,name);
		pre.setString(3,pass);
		System.out.print(pre);
		pre.executeUpdate();
		}
	
	}
	static void delete()  throws SQLException {
		Scanner scan=new Scanner(System.in);
		System.out.print("ID :");
		int id=scan.nextInt();
		String qry="delete from signup where id=?";
		Connection con=connect();
		PreparedStatement pre=con.prepareStatement(qry);
		pre.setInt(1,id);
		pre.executeUpdate();
	}
	static void update()  throws SQLException {
		Scanner scan=new Scanner(System.in);
		System.out.print("ID :");
		int id=scan.nextInt();
		System.out.print("Name :");
		String name=scan.next();
		System.out.print("Pass :");
		String pass=scan.next();
		
		String qry="update signup set username=?,pass=? where id=? ";
		Connection con=connect();
		PreparedStatement pre=con.prepareStatement(qry);
		pre.setInt(3,id);
		pre.setString(1,name);
		pre.setString(2,pass);
		pre.executeUpdate();
	}
	static void view() throws SQLException 
	{
		String qry="select*from signup";
		Connection con=connect();
		PreparedStatement pre=con.prepareStatement(qry);
		ResultSet rs=pre.executeQuery();
		while(rs.next()) {
			System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3));
		}
	}

	public static void main(String[] args) throws SQLException{
		sqldynamic.insert();
		sqldynamic.update();
}
}
