/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import ejb.AuthorEntity;
import ejb.BookEntity;
import ejb.BookEntityFacade;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author nuwan600
 */
@WebServlet(name = "EditBookOperation", urlPatterns = {"/EditBookOperation"})
public class EditBookOperation extends HttpServlet {
    @EJB
    private BookEntityFacade bookEntityFacade;

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /*
             * TODO output your page here. You may use following sample code.
             */
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet EditBookOperation</title>"); 
                out.println("<link rel='stylesheet' type='text/css' href='css.css' media='screen'/>");                
            out.println("</head>");
            out.println("<body>");
            out.println("<div id='container'>");
            out.println("<h1 align='center'>Edit Book Details</h1>");

           out.println("<div id='menu'> ");
    out.println("<ul>");
     out.println(" <li><a href='DisplayAllBooks' title=''>HOME</a></li>");
     out.println(" <li><a href='ListAuthors' title=''>List Authors</a></li>");
   out.println(" </ul>");
  out.println("</div>");
  out.println("<div id='content'> ");
             String BookID = request.getParameter("BookID");
             BookEntity B_elem = (BookEntity) bookEntityFacade.find(Long.parseLong(BookID));
             out.println("<table>");
            out.println("<form method='post' action='DisplayAllBooks'>");
            out.println("<input type='hidden' name='BookID' value='"+B_elem.getId()+"'>");
            out.println("<tr><td>ISBN      :</td><td> <input type='text' name='ISBN' value='"+B_elem.getIsbn()+"'></td></tr>");
             out.println("<tr><td>Title     :</td><td> <input type='text' name='Title' value='"+B_elem.getTitle()+"'></td></tr>");
             out.println("<tr><td>Price     :</td><td> <input type='text' name='Price' value='"+B_elem.getPrice()+"'></td></tr>");
             out.println("<tr><td>Year      :</td><td> <input type='text' name='Year' value='"+B_elem.getYear()+"'></td></tr>");
             out.println("<tr><td>Language  : </td><td><input type='text' name='Language' value='"+B_elem.getLanguage()+"'></td></tr>");
             out.println("<tr><td><input type='submit' value='Update'/> </td></tr>");
             out.println("</form>");
             out.println("</table>");
             out.println("<br/>");
            out.println("<br/>");
             out.println("<div style='clear: both;'></div>");
            out.println("</div>");
            out.println("<div id='footer'> <span>Copyright © 2014 | All Rights Reserved Bookstore </span> ");
            out.println("</div>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        } finally {            
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
