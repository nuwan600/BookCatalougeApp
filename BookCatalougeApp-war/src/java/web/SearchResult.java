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
import java.util.Iterator;
import java.util.List;
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
@WebServlet(name = "SearchResult", urlPatterns = {"/SearchResult"})
public class SearchResult extends HttpServlet {

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
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SearchResult</title>");
             out.println("<link rel='stylesheet' type='text/css' href='css.css' media='screen'/>");     
            
            out.println("</head>");
            out.println("<body>");
             out.println("<div id='container'>");
            out.println("<h1 align='center'>Search Result</h1>");
             out.println("<div id='header'>");
            
           out.println("</div>");
           out.println("<div id='menu'> ");
    out.println("<ul>");
     out.println(" <li><a href='DisplayAllBooks' title=''>HOME</a></li>");
     out.println(" <li><a href='ListAuthors' title=''>List Authors</a></li>");
   out.println(" </ul>");
  out.println("</div>");
  out.println("<div id='content'> ");
            String elementComboValue = request.getParameter("elementCombo");
            String SearchInput = request.getParameter("SearchInput");
            BookEntity Bobj=new BookEntity();
            
            if (elementComboValue.equalsIgnoreCase("Author Name")) {
                List<AuthorEntity> Aset = authorEntityFacade.findAll();
                Iterator<AuthorEntity> iterator = Aset.iterator();
                while (iterator.hasNext()) {
                    AuthorEntity AE = iterator.next();
                    if (AE.getName().equalsIgnoreCase(SearchInput)) {
                        Bobj=bookEntityFacade.find(AE.getBook().getId());
                    }
                }
            } else if (elementComboValue.equalsIgnoreCase("ISBN")) {
                List<BookEntity> Bset = bookEntityFacade.findAll();
                Iterator<BookEntity> iterator = Bset.iterator();
                while (iterator.hasNext()) {
                    BookEntity BE = iterator.next();
                    if (BE.getIsbn().equalsIgnoreCase(SearchInput)) {
                        Bobj=BE;
                    }
                }
            } else if (elementComboValue.equalsIgnoreCase("Title")) {
                List<BookEntity> Bset = bookEntityFacade.findAll();
                Iterator<BookEntity> iterator = Bset.iterator();
                while (iterator.hasNext()) {
                    BookEntity BE = iterator.next();
                    if (BE.getTitle().equalsIgnoreCase(SearchInput)) {
                        Bobj=BE;
                    }
                }
            }
            else if (elementComboValue.equalsIgnoreCase("BID")) {
                List<BookEntity> Bset = bookEntityFacade.findAll();
                Iterator<BookEntity> iterator = Bset.iterator();
                while (iterator.hasNext()) {
                    BookEntity BE = iterator.next();
                    if (BE.getId()==Long.parseLong(SearchInput)) {
                        Bobj=BE;
                    }
                }
            }
            
            out.println("<table>");
                out.println("<thead>");
                    out.println("<tr>");
                        out.println("<th>Book ID</th>");
                        out.println("<th>ISBN</th>");
                        out.println("<th>TITLE</th>");
                        out.println("<th>PRICE</th>");
                        out.println("<th>YEAR</th>");
                        out.println("<th>LANGUAGE</th>");
                        out.println("<th>AUTHORS</th>");
                        out.println("<th>Update Book</th>");
                out.println("</tr>");
                out.println("</thead>");
                out.println("<tbody>");
                BookEntity elem = Bobj;
                out.println(" <tr>");
                out.println("<td>");
                out.println(elem.getId() + "<br /> ");
                out.println("</td>");
                out.println("<td>");
                out.println(elem.getIsbn() + "<br /> ");
                out.println("</td>");
                out.println("<td>");
                out.println(elem.getTitle() + "<br /> ");
                out.println("</td>");
                out.println("<td>");
                out.println(elem.getPrice() + "<br /> ");
                out.println("</td>");
                out.println("<td>");
                out.println(elem.getYear() + "<br /> ");
                out.println("</td>");
                out.println("<td>");
                out.println(elem.getLanguage() + "<br /> ");
                out.println("</td>");
               
                out.println("<td><div>");
                
                Set<AuthorEntity> A_Set=elem.getAuthors();
                for (Iterator its = A_Set.iterator(); its.hasNext();) {
                AuthorEntity A_elem = (AuthorEntity) its.next();
                 out.println("<form method='post' action='DeleteAuthorServelet'>"); 
                 out.println("<input type='hidden' name='AuthorID' value='"+A_elem.getId()+"'>");
                 out.println("<input type='hidden' name='BookID' value='"+elem.getId()+"'>");
                 out.println("<input type='hidden' name='whatType' value='allBooks'>");
                out.println(A_elem.getName() + "   <br/>");
                out.println("<input type='submit' value='Delete This Author'/> ");
                out.println("</form>");
                }
                out.println("</div></td>");
                 out.println("<td>");
                out.println("<form method='post' action='EditBookOperation'>");
                out.println("<input type='hidden' name='BookID' value='"+elem.getId()+"'>");
                out.println("<input type='submit' value='Update'/> ");
                out.println("</form>");
                out.println("</td>");
                
                out.println(" </tr>");
                
                out.println("</tbody>");
            out.println("</table> <br/><br/><br/><br/>");
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
