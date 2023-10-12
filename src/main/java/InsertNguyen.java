
/**
 * @file SimpleFormInsert.java
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/InsertNguyen")
public class InsertNguyen extends HttpServlet {
   private static final long serialVersionUID = 1L;

   public InsertNguyen() {
      super();
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String name = request.getParameter("name");
      String age = request.getParameter("age");
      String year = request.getParameter("class");
      String email = request.getParameter("email");

      Connection connection = null;
      String insertSql = " INSERT INTO tech_exercise_table (id, name, age, class, email) values (default, ?, ?, ?, ?)";

      try {
         DBConnectionNguyen.getDBConnection();
         connection = DBConnectionNguyen.connection;
         PreparedStatement preparedStmt = connection.prepareStatement(insertSql);
         preparedStmt.setString(1, name);
         preparedStmt.setString(2, age);
         preparedStmt.setString(3, year);
         preparedStmt.setString(4, email);
         preparedStmt.execute();
         connection.close();
      } catch (Exception e) {
         e.printStackTrace();
      }

      // Set response content type
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      String title = "Insert Data to DB table";
      String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
      out.println(docType + //
            "<html>\n" + //
            "<head><title>" + title + "</title></head>\n" + //
            "<body bgcolor=\"#f0f0f0\">\n" + //
            "<h2 align=\"center\">" + title + "</h2>\n" + //
            "<ul>\n" + //

            "  <li><b>Name</b>: " + name + "\n" + //
            "  <li><b>Age</b>: " + age + "\n" + //
            "  <li><b>Class</b>: " + year + "\n" + //
            "  <li><b>Email</b>: " + email + "\n" + //

            "</ul>\n");

      out.println("<a href=/webproject/search_nguyen.html>Search Data</a> <br>");
      out.println("</body></html>");
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }

}
