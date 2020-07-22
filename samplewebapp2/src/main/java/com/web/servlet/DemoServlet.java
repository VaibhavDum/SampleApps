package com.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class DemoServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        doGet(request,response);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            //ServletContext ctx = servletContextEvent.getServletContext();

            //initialize DB Connection
            String dbDriver = "com.mysql.jdbc.Driver";
            String dbName = getServletContext().getInitParameter("dbName");
            String dbURL = getServletContext().getInitParameter("dbURL");
            String user = getServletContext().getInitParameter("dbUser");
            String pwd = getServletContext().getInitParameter("dbPassword");

            // Initialize the database
            Connection con = DatabaseConnection.initializeDatabase(dbDriver,dbURL, dbName, user, pwd);

            PreparedStatement st = con
                    .prepareStatement("insert into demo values(?, ?, ?)");

            st.setString(1, request.getParameter("fname"));

            st.setString(2, request.getParameter("lname"));

            st.setInt(3, Integer.parseInt(request.getParameter("age")));

            // Execute the insert command using executeUpdate()
            // to make changes in database
            st.executeUpdate();

            // Close all the connections
            st.close();
            con.close();

            // Get a writer pointer
            // to display the successful result
            PrintWriter out = response.getWriter();
            out.println("<html><body><b>Successfully Inserted"
                    + "</b></body></html>");
            out.println("<a href="+getServletContext().getContextPath()+"/index.html>Go Back!</a>");
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

}
