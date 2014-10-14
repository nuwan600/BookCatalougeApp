/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import ejb.AuthorEntityFacade;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ejb.AuthorEntity;
import ejb.BookEntity;
import ejb.BookEntityFacade;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 *
 * @author nuwan600
 */
@WebServlet(name = "ListAuthors", urlPatterns = {"/ListAuthors"})
public class ListAuthors extends HttpServlet {
    @EJB
    private BookEntityFacade bookEntityFacade;
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
            List<AuthorEntity> Aset=authorEntityFacade.findAll();
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ListAuthors</title>"); 
            out.println("<link rel='stylesheet' type='text/css' href='css.css' media='screen'/>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1 align='center'>Add/List Authors</h1>");
             out.println("<table class=main>");
                out.println("<thead>");
                    out.println("<tr>");
                        out.println("<th>Author ID</th>");
                        out.println("<th>Author Name</th>");
                        out.println("<th>Written Book</th>");
                        out.println("<th>Delete Author</th>");
                        out.println("<th>Edit Author</th>");
                out.println("</tr>");
                out.println("</thead>");
                out.println("<tbody>");
                for (Iterator it = Aset.iterator(); it.hasNext();) { 
                AuthorEntity A_elem = (AuthorEntity) it.next();
                out.println(" <tr>");
                
                out.println("<td>");
                out.println(A_elem.getId() + "<br /> ");
                out.println("</td>");
                out.println("<td>");
                out.println(A_elem.getName() + "<br /> ");
                out.println("</td>");
                out.println("<td>");
                BookEntity bEN=(BookEntity)A_elem.getBook();
                out.println(bEN.getTitle() + "<br /> ");
                out.println("</td>");
                out.println("<td>");
                 out.println("<form method='post' action='DeleteAuthorServelet'>");
                out.println("<input type='hidden' name='AuthorID' value='"+A_elem.getId()+"'>");
                 out.println("<input type='hidden' name='BookID' value='"+A_elem.getBook().getId()+"'>");
                 out.println("<input type='hidden' name='whatType' value='author'>");
                out.println("<input type='submit' value='Delete'/> ");
                out.println("</form>");
                out.println("</td>");
                out.println("<td>");
                 out.println("<form method='post' action='EditAuthorServelet'>");
                out.println("<input type='hidden' name='AuthorID' value='"+A_elem.getId()+"'>");
                out.println("<input type='submit' value='Update'/> ");
                out.println("</form>");
                out.println("</td>");
                out.println(" </tr>");

                }
                out.println("</tbody>");
            out.println("</table> <br/><br/><br/><br/>");
            out.println("<div class='AddAU'>");
            out.println("<h3>Insert Author</h3>");
            out.println("<table>");
            out.println("<form method='post' action='AddAuthorOperation'>");
             out.println("<tr><td>Author Name :</td> <td><input type='text' name='AName'></td></tr>");
             out.println("<tr><td>Book ID : </td><td><input type='text' name='BId'></td></tr>");
             out.println("<tr><td><input type='submit' value='Add Author'/></td></tr>");
            out.println("</form>");
            out.println("</table>");
            out.println("</div>");
            out.println("<div class='Link'>");
            out.println("<a href='DisplayAllBooks'>Go back to Display All Books</a><br/>");
            String usrID = request.getParameter("usrID").toString();
            out.println(usrID);
            out.println("</div>");
            out.println("<br/>");
            out.println("<br/>");
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
        String AuthorID= request.getParameter("AuthorID");
        String A_NewName = request.getParameter("AName");
        String A_BID = request.getParameter("ABID");
        
        AuthorEntity A_elem = (AuthorEntity) authorEntityFacade.find(Long.parseLong(AuthorID));
        BookEntity B_elem = (BookEntity) bookEntityFacade.find(Long.parseLong(A_BID));
        
        A_elem.setName(A_NewName);
        A_elem.setBook(B_elem);
        authorEntityFacade.edit(A_elem);
        
        Set<AuthorEntity> AES=B_elem.getAuthors();
        Iterator<AuthorEntity> iterator = AES.iterator();
        while (iterator.hasNext()) {
            AuthorEntity AE = iterator.next();
            if (AE.getId() == (Long.parseLong(AuthorID))) {
                iterator.remove();
            }
        }
        AES.add(A_elem);
        B_elem.setAuthors(AES);
        bookEntityFacade.edit(B_elem);
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
