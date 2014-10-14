/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import ejb.AuthorEntity;
import ejb.AuthorEntityFacade;
import ejb.BookEntity;
import ejb.BookEntityFacade;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;
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
@WebServlet(name = "AddAuthorOperation", urlPatterns = {"/AddAuthorOperation"})
public class AddAuthorOperation extends HttpServlet {
    @EJB
    private AuthorEntityFacade authorEntityFacade;
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
            out.println("<title>Servlet AddAuthorOperation</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddAuthorOperation at " + request.getContextPath() + "</h1>");
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
        String AuthorName = request.getParameter("AName");
        String BookId = request.getParameter("BId");
        BookEntity B_elem = (BookEntity) bookEntityFacade.find(Long.parseLong(BookId));
        if(B_elem != null)
        {
        AuthorEntity Aob=new AuthorEntity();
        Aob.setName(AuthorName);
        Aob.setBook(B_elem);
        authorEntityFacade.create(Aob);
        
        Set<AuthorEntity> AS=B_elem.getAuthors();
        AS.add(Aob);
        bookEntityFacade.edit(B_elem);
        response.sendRedirect("ListAuthors?usrID=Sucessfully Inserted");
        }
        else{
            response.sendRedirect("ListAuthors?usrID=Fail to insert due to Not existing Book ID");
        }
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
