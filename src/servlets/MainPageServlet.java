package servlets;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.Path;


@WebServlet("/MainPage")
public class MainPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public MainPageServlet() {
    	
    }
    
	public void init(ServletConfig config) throws ServletException {
		
		try {
			Class.forName("org.firebirdsql.jdbc.FBDriver");
			Connection connection = DriverManager.getConnection(
					 Path.getDatabaseURL(), "sysdba", "masterkey"
			);
			 
			ResultSet rs = connection.prepareStatement("select * from galaxies").executeQuery();
			ResultSetMetaData rd = rs.getMetaData();
			while(rs.next()){
				String name = rs.getString(2);
				System.out.println("Length of row is: " + rd.getColumnCount());
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/MainPage.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

