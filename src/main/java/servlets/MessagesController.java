package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.MessagesDAO;
import dto.MessagesDTO;

@WebServlet("*.message")
public class MessagesController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uri = request.getRequestURI();

		try {
			if (uri.equals("/input.message")) {
				String writer = request.getParameter("writer");
				String msg = request.getParameter("msg");
				MessagesDAO dao = MessagesDAO.getinstance();
				int result = dao.insert(writer, msg);
				response.sendRedirect("index.html");
			} else if (uri.equals("/output.message")) {
				MessagesDAO dao = MessagesDAO.getinstance();
				List<MessagesDTO> list = dao.selectAll();
				request.setAttribute("list", list);
				request.getRequestDispatcher("outputView.jsp").forward(request, response);
				
			} else if (uri.equals("/delete.message")) {
				int seq = Integer.parseInt(request.getParameter("delSeq"));
				MessagesDAO dao = new MessagesDAO();
				int result = dao.delete(seq);
				response.sendRedirect("output.message");
			} else if (uri.equals("/update.message")) {
				String writer = request.getParameter("writer");
				String msg = request.getParameter("msg");
				int seq = Integer.parseInt(request.getParameter("seq"));

				MessagesDAO dao = MessagesDAO.getinstance();
				int result = dao.update(seq, writer, msg);
				response.sendRedirect("output.message");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("error.html");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
