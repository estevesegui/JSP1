
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 * Servlet implementation class Inicio
 */
public class Inicio extends HttpServlet {
	private String userName;
	private String pass;
	// private PrintStream out;
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Inicio() {
		super();
		// TODO Auto-generated constructor stub
		System.out.println("<p>Hola estas en Inicio!!</p>");
		// out.println("<p>Hola estas en Inicio!!</p>");
	}

	/**
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(false); // agafa sessi� si n'hi ha. Si no n'hi ha, �s NULL

		if (session == null) {
			// NO EXISTE LA SESI�N
			// out.println("<p>No existe la sessi�n</p>");

			// COMPROBAR PAR�METROS DEL FORMULARIO
			String usuario = request.getParameter("usuario");
			String password = request.getParameter("password");

			String usuarioCorrecto = getServletConfig().getInitParameter("usuario");
			String passwordCorrecta = getServletConfig().getInitParameter("password");

			// EL USUARIO ES EL CORRECTO (root) Y LA CONTRASE�A TAMBI�N (232323). TAMPOCO
			// PUEDEN ENVIAR PAR�METROS NULL
			if (usuario != null && password != null && usuario.equals(usuarioCorrecto)
					&& password.equals(passwordCorrecta)) {
				session = request.getSession(true);

				session.setAttribute("user", usuario);
				RequestDispatcher rd = request.getRequestDispatcher("/registrado.jsp");
				rd.forward(request, response);

			} else {
				RequestDispatcher rd = request.getRequestDispatcher("/autorizar.html");
				rd.forward(request, response);
			}

		} else {
			RequestDispatcher rd = request.getRequestDispatcher("/registrado.jsp");
			rd.forward(request, response);
		}

		/*
		 * } else { out.println("<p>Existe sessi�n</p>");
		 * if(session.getAttribute("user") == "user") { out.
		 * println("<p>En la sessi�n existe el atributo de sesi�n usuario, es reenviado a /registrado.jsp</p>"
		 * ); RequestDispatcher rd = request.getRequestDispatcher("/registrado.jsp");
		 * rd.forward(request, response); } else {
		 * out.println("<p>No existe el usuario en la sesi�n</p>"); if(usuario != null
		 * && password != null) { if((usuario == userName) && (password == pass )) {
		 * RequestDispatcher rd = request.getRequestDispatcher("/registrado.jsp");
		 * rd.forward(request, response); } else { RequestDispatcher rd =
		 * request.getRequestDispatcher("/autorizar.html"); rd.forward(request,
		 * response); } } }
		 * 
		 * }
		 */

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/*
	 * public void init(ServletConfig config) throws ServletException {
	 * super.init(config);
	 * 
	 * //Lectura de los par�metros de inicializaci�n del Servlet. userName =
	 * config.getInitParameter("usuario"); pass =
	 * config.getInitParameter("password");
	 * 
	 * 
	 * }
	 */

}
