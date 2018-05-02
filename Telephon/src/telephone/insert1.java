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
 * Servlet implementation class insert1
 */
@WebServlet("/insert1")
public class insert1 extends HttpServlet {

	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Connection conn1 = null;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
        Statement stmt = null;
        //@SuppressWarnings("unused")
		//java.sql.ResultSet rs = null;

        try {
            String driver = "com.mysql.jdbc.Driver";
            String url = "jdbc:mysql://localhost:3306/OnlineDirectory";
            String username = "root";
            String password = "root";
            Class.forName(driver);
            conn1 = DriverManager.getConnection(url, username , password);
            if (conn1 != null)
                System.out.println("Successful");
            stmt = conn1.createStatement();


            String name = request.getParameter("nam");
            int contact = Integer.parseInt(request.getParameter("cnt"));
            String address = request.getParameter("address");
            String company = request.getParameter("company");
            int pin = Integer.parseInt(request.getParameter("pin"));

            out.println("name" + name);
            out.println("contact:" + contact);
            out.println("address:" + address);
            out.println("company:" + company);
            out.println("pin:" + pin);

            stmt.executeUpdate("insert into Telephone_Directory values('" + contact + "','" + name + "','" + address + "','" + company + "','" + pin +"');");
            out.println("updated the records");

        } catch (ClassNotFoundException e) {
            System.out.println("Error: failed to load MySQL driver.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Error: failed to create a connection object.");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Error: unknown");
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
                conn1.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}