package servlets;

import java.io.IOException;
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
import models.BlackHole;
import models.Galaxy;
import models.Moon;
import models.Planet;
import models.SmallSolarSystemBody;
import models.SolarSystem;
import models.Star;

@WebServlet("/SearchResults")
public class SearchResultsServlet extends HttpServlet {
	public void init(ServletConfig config) throws ServletException {
		try {
			Class.forName("org.firebirdsql.jdbc.FBDriver");
			Connection connection = DriverManager.getConnection(
					 Path.getDatabaseURL(), "sysdba", "masterkey"
			);

			ResultSet rs = connection
					.prepareStatement("select * from galaxies").executeQuery();
			while (rs.next()) {
				String name = rs.getString(2);
				System.out.println(name);
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if (request.getSession().getAttribute("select").equals("galaxies")) {
			request.getRequestDispatcher("/WEB-INF/GalaxyResults.jsp").forward(
					request, response);
		} else if (request.getSession().getAttribute("select")
				.equals("solar_systems")) {
			request.getRequestDispatcher("/WEB-INF/SolarSystemResults.jsp")
					.forward(request, response);
		} else if (request.getSession().getAttribute("select").equals("stars")) {
			request.getRequestDispatcher("/WEB-INF/StarResults.jsp").forward(
					request, response);
		} else if (request.getSession().getAttribute("select")
				.equals("Small_Solar_System_Bodies")) {
			request.getRequestDispatcher(
					"/WEB-INF/SmallSolarSystemBodyResults.jsp").forward(
					request, response);
		} else if (request.getSession().getAttribute("select")
				.equals("planets")) {
			request.getRequestDispatcher("/WEB-INF/PlanetResults.jsp").forward(
					request, response);
		} else if (request.getSession().getAttribute("select")
				.equals("blackholes")) {
			request.getRequestDispatcher("/WEB-INF/BlackHoleResults.jsp")
					.forward(request, response);
		} else if (request.getSession().getAttribute("select").equals("moons")) {
			request.getRequestDispatcher("/WEB-INF/MoonResults.jsp").forward(
					request, response);
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("got here with" + request.getParameter("select")
				+ " " + request.getParameter("query"));
		String select = request.getParameter("select");
		request.getSession().setAttribute("select", select);
		String query = request.getParameter("query");
		Object object = getQuery(select, query);
		request.getSession().setAttribute("object",
				object.getClass().cast(object));
		doGet(request, response);
	}

	public Object getQuery(String search, String query) {
		try {
			Class.forName("org.firebirdsql.jdbc.FBDriver");
			Connection connection = DriverManager.getConnection(
					 Path.getDatabaseURL(), "sysdba", "masterkey"
			);
			System.out.println(Path.getDatabaseURL());
			System.out.println("Search is set to " + search);
			ResultSet rs = connection.prepareStatement(
					"select * from " + search + " where name = " + "\'" + query
							+ "\'").executeQuery();
			while (rs.next()) {
				if (search.equals("galaxies")) {
					Galaxy galaxy = new Galaxy();
					galaxy.setId(rs.getInt(1));
					galaxy.setName(rs.getString(2));
					galaxy.setUrl(rs.getString(3));
					galaxy.setType(rs.getString(4));
					return galaxy;
				} else if (search.equals("solar_systems")) {
					SolarSystem ss = new SolarSystem();
					ss.setId(rs.getInt(1));
					ss.setName(rs.getString(2));
					ss.setUrl(rs.getString(4));
					ResultSet orbitSet = connection.prepareStatement(
							"select g.name from galaxies g"
									+ "											inner join solar_systems s"
									+ "											on(g.id = s.galaxy_id)"
									+ "											where s.id = " + ss.getId())
							.executeQuery();
					orbitSet.next();
					ss.setGalaxyName(orbitSet.getString(1));
					return ss;
				} else if (search.equals("stars")) {
					Star star = new Star();
					star.setId(rs.getInt(1));
					star.setName(rs.getString(2));
					star.setType(rs.getString(3));
					star.setUrl(rs.getString(4));
					star.setAge(rs.getInt(5));
					star.setMass(rs.getInt(6));
					star.setLumionsity(rs.getInt(7));
					star.setTemperature(rs.getInt(8));
					ResultSet orbitSet = connection
							.prepareStatement(
									"select s.name from solar_systems s"
											+ "											inner join stars ss"
											+ "											on(s.id = ss.solar_system_id)"
											+ "											where ss.id = "
											+ star.getId()).executeQuery();
					orbitSet.next();
					star.setSolarSystemName(orbitSet.getString(1));
					return star;
				} else if (search.equals("Small_Solar_System_Bodies")) {
					SmallSolarSystemBody s = new SmallSolarSystemBody();
					s.setId(rs.getInt(1));
					s.setName(rs.getString(2));
					s.setType(rs.getString(3));
					s.setUrl(rs.getString(4));
					ResultSet orbitSet = connection
							.prepareStatement(
									"select s.name from stars s"
											+ "											inner join small_solar_system_bodies ss"
											+ "											on(s.id = ss.star_id)"
											+ "											where ss.id = "
											+ s.getId()).executeQuery();
					orbitSet.next();
					s.setStarName(orbitSet.getString(1));
					return s;
				} else if (search.equals("planets")) {
					Planet planet = new Planet();
					planet.setId(rs.getInt(1));
					planet.setName(rs.getString(2));
					planet.setUrl(rs.getString(3));
					planet.setType(rs.getString(4));
					ResultSet habitableSet = connection.prepareStatement(
							"select habitable from planets_habitable"
									+ "											where type = \'"
									+ planet.getType() + "\'").executeQuery();
					habitableSet.next();
					planet.setHabitable(habitableSet.getString(1));
					ResultSet orbitSet = connection.prepareStatement(
							"select s.name from stars s"
									+ "											inner join planets p"
									+ "											on(s.id = p.star_id)"
									+ "											where p.id = "
									+ planet.getId()).executeQuery();
					orbitSet.next();
					planet.setStarName(orbitSet.getString(1));
					return planet;
				} else if (search.equals("blackholes")) {
					BlackHole bl = new BlackHole();
					bl.setId(rs.getInt(1));
					bl.setName(rs.getString(2));
					bl.setUrl(rs.getString(3));
					bl.setType(rs.getString(4));
					bl.setAngularMomentum(rs.getString(5));
					bl.setCharge(rs.getString(6));
					bl.setMass(rs.getString(7));
					bl.setAge(rs.getString(8));
					bl.setStarId(rs.getString(9));
					return bl;
				} else if (search.equals("moons")) {
					Moon moon = new Moon();
					moon.setId(rs.getInt(1));
					moon.setName(rs.getString(2));
					moon.setUrl(rs.getString(3));
					ResultSet orbitSet = connection
							.prepareStatement(
									"select p.name from planets p"
											+ "											inner join moons m"
											+ "											on(p.id = m.planet_id)"
											+ "											where m.id = "
											+ moon.getId()).executeQuery();
					orbitSet.next();
					moon.setPlanetName(orbitSet.getString(1));
					return moon;
				}
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new Object();
	}

}
