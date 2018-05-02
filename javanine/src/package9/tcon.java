package package9;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/tcon")
public class tcon extends HttpServlet {
private static final long serialVersionUID = 1L;
   
public tcon() {
    super();   
}

protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String[] Accessories={};
		Accessories=request.getParameterValues("access");
		String tshirtAccessories="";
		String tshirtTagline=request.getParameter("tagline");
		String tshirtOption=request.getParameter("pocket");
		String tcolor=request.getParameter("Tshirtcolor");
		out.println("<html>");
		out.println("<head><title>T-shirt</title></head>");
		out.println("<body>");
		try {
            Statement stmt;
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tshirt", "root", "root");
            if (conn != null) {
            	stmt= conn.createStatement();
            	String qu;
            	if(tshirtAccessories!=null && tshirtTagline!=null && tshirtOption!=null && tcolor!=null){
            		for(String option:Accessories){
            			tshirtAccessories=tshirtAccessories+option;
            		}
            		qu="insert into Tshirt(tshirtTagline,tshirtAccessories,tcolor,tshirtOption) values('"+tshirtTagline+"','"+tshirtAccessories+"','"+tcolor+"','"+tshirtOption+"');";
            		stmt.executeUpdate(qu);
            	}
            	qu="select * from Tshirt;";
            	ResultSet rs =stmt.executeQuery(qu);
            	out.println("<table border=2>");
        		out.println("<tr>");
        		out.print("<td>OrderNo</td>");
        		out.print("<td>T-shirt tag-line</td>");
        		out.print("<td>T-shirt Accessories</td>");
        		out.print("<td>T-shirt type</td>");
        		out.print("<td>T-shirt color</td>");
        		out.println("</tr>");
        		
        		while(rs.next()){
        		out.println("<tr>");
            		out.print("<td>"+(Integer.parseInt(rs.getString("OrderNo")))+"</td>");
            		out.print("<td>"+rs.getString("tshirtTagline")+"</td>");
            		out.print("<td>"+rs.getString("tshirtAccessories")+"</td>");
            		out.print("<td>"+rs.getString("tcolor")+"</td>");
            		out.print("<td>"+rs.getString("tshirtOption")+"</td>");
            		out.println("</tr>");
        		}
        		out.println("</table>");
        		out.println("<a href=\"tshirt.jsp\">click here</a>");
        		out.println("</body></html>");
            }
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
}
