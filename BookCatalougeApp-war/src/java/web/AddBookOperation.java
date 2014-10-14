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
import java.util.HashSet;
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
@WebServlet(name = "AddBookOperation", urlPatterns = {"/AddBookOperation"})
public class AddBookOperation extends HttpServlet {
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
        String ISBN = request.getParameter("ISBN");
        String Title = request.getParameter("Title");
        double Price = Double.parseDouble(request.getParameter("Price"));
        int Year =Integer.parseInt(request.getParameter("Year"));
        String Language = request.getParameter("Language");
        String[] AUList = request.getParameterValues("AU");
        BookEntity bob=new BookEntity();
        bob.setIsbn(ISBN);
        bob.setLanguage(Language);
        bob.setPrice(Price);
        bob.setTitle(Title);
        bob.setYear(Year);
        bookEntityFacade.create(bob);
        Set<AuthorEntity> AS=new HashSet<AuthorEntity>();
        if(AUList != null){
        for (String i : AUList){
            if(!(i.equalsIgnoreCase("")))
            {
           AuthorEntity AE=new AuthorEntity();
           AE.setName(i);
           AE.setBook(bob);
           authorEntityFacade.create(AE);
           AS.add(AE);
            }
        }
        bob.setAuthors(AS);
        bookEntityFacade.edit(bob);
        }
        response.sendRedirect("DisplayAllBooks");
        
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
