package telephone;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class JDBClogin
 */
@WebServlet("/JDBClogin")
public class JDBClogin extends HttpServlet {
		
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static Connection getConnection() throws Exception {
	        
	        String driver = "com.mysql.jdbc.Driver";
	        String url = "jdbc:mysql://localhost:3306/OnlineDirectory";
	        String username = "root";
	        String password = "root";
	        Class.forName(driver);
	        Connection conn = DriverManager.getConnection(url, username, password);
	        return conn;
	    }
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
        //out.print("Working");
        
        Connection conn = null;
        Statement stmt = null;
        java.sql.ResultSet rs = null;               
    try {
            conn = getConnection();
            stmt = conn.createStatement();           
                int inp;
                
            try
            {
             inp =Integer.parseInt(request.getParameter("phone"));
            out.println(inp);
             rs = stmt.executeQuery("SELECT * FROM Telephone_Directory where Phone_Number="+inp);
            }
            catch(Exception e)
            {
                String name=request.getParameter("phone");
                // out.println(""+name);
                rs = stmt.executeQuery("SELECT * FROM Telephone_Directory where Name='"+name+"'");
                
            }
            if(rs.next()) {
                int contact = rs.getInt(1);
                String name = rs.getString(1);
                String address = rs.getString(3);
                String company = rs.getString(4);
                int pin =rs.getInt(5);
                out.println("name"+name);
                out.println("contact:"+contact);
                out.println("address:"+address);
                out.println("company:"+company);
                out.println("pin:"+pin);
                
                
                } 
            else
            {
                out.println("no contact found");
            } 
            
            
        } catch (ClassNotFoundException e) {
            System.out.println("Error: failed to load MySQL driver.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Error: failed to create a connection object.");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Error: unknown");
            e.printStackTrace();
        } 
    
    finally {
        try {
            stmt.close();
            conn.close();        
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}