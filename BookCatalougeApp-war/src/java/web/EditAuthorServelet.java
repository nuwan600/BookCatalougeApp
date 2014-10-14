/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import ejb.AuthorEntity;
import ejb.AuthorEntityFacade;
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
@WebServlet(name = "EditAuthorServelet", urlPatterns = {"/EditAuthorServelet"})
public class EditAuthorServelet extends HttpServlet {
    @EJB
    private AuthorEntityFacade authorEntityFacade;

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
            out.println("<title>Edit Author</title>"); 
            out.println("<link rel='stylesheet' type='text/css' href='css.css' media='screen'/>");     
             
            out.println("</head>");
            out.println("<body>");
             out.println("<div id='container'>");
            out.println("<h1 align='center'>Edit Author Details</h1>");

           out.println("<div id='menu'> ");
    out.println("<ul>");
     out.println(" <li><a href='DisplayAllBooks' title=''>HOME</a></li>");
     out.println(" <li><a href='ListAuthors' title=''>List Authors</a></li>");
   out.println(" </ul>");
  out.println("</div>");
  out.println("<div id='content'> ");
            String AuthorID = request.getParameter("AuthorID");
             AuthorEntity A_elem = (AuthorEntity) authorEntityFacade.find(Long.parseLong(AuthorID));
             out.println("<table>");
             out.println("<form method='post' action='ListAuthors'>");
             out.println("<input type='hidden' name='AuthorID' value='"+AuthorID+"'>");
             out.println("<tr><td>Author Name  :</td><td> <input type='text' name='AName' value='"+A_elem.getName()+"'></td></tr>");
             out.println("<tr><td>Book ID      :</td><td> <input type='text' name='ABID' value='"+A_elem.getBook().getId()+"'></td></tr>");
             out.println("<tr><td><input type='submit' value='Update'/></td></tr> ");
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
