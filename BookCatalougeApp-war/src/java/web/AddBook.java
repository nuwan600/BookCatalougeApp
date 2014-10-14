/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 *@author nuwan600
 */
@WebServlet(name = "AddBook", urlPatterns = {"/AddBook"})
public class AddBook extends HttpServlet {

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
            out.println("<title>Servlet AddBook</title>");  
            out.println("<link rel='stylesheet' type='text/css' href='css.css' media='screen'/>");   
            
            out.println("<SCRIPT language='javascript'>"); 
            out.println("var x=1;");
                out.println("function add() {"); 
 
                  //Create an input type dynamically.
                out.println("var txt=document.createTextNode('Author Name '+x+' : ');");
                 out.println("var element = document.createElement('input');");
                 out.println("var t=document.createElement('br');");
 
                     //Assign different attributes to the element.
                 out.println(" element.setAttribute('type','text');"); 
                 out.println("element.setAttribute('name','AU');"); 
 
 
                 out.println("var foo = document.getElementById('fooBar');"); 
 
                //Append the element in page (in span).
                out.println("foo.appendChild(t);"); 
                out.println("foo.appendChild(txt);");
                out.println("foo.appendChild(element);");
                out.println("x++;");
 
                out.println("}"); 
                out.println("</SCRIPT>"); 
         
             out.println("</head>");
             out.println("<body>"); 
             out.println("<div id='container'>");
             out.println("<h1 align='center'>Add Books</h1>");
           
           out.println("<div id='menu'> ");
    out.println("<ul>");
     out.println(" <li><a href='DisplayAllBooks' title=''>HOME</a></li>");
     out.println(" <li><a href='ListAuthors' title=''>List Authors</a></li>");
   out.println(" </ul>");
  out.println("</div>");
  out.println("<div id='content'> ");
             out.println("<form  method='post' action='AddBookOperation'>");
             out.println("<table>");
             out.println("<tr><td>ISBN : </td><td>: <input type='text' name='ISBN'></td></tr>");
             out.println("<tr><td>Title : </td><td>: <input type='text' name='Title'></td></tr>");
             out.println("<tr><td>Price : </td><td>: <input type='text' name='Price'></td></tr>");
             out.println("<tr><td>Year :  </td><td>: <input type='text' name='Year'></td></tr>");
             out.println("<tr><td>Language : </td><td>: <input type='text' name='Language'></td></tr>");
             out.println("<tr><td><b>Add any number of Authors Dynamically here : </b></td>");
             out.println("<td><INPUT type='button' value='Add' onclick='add()'/></td></tr>");
             out.println("<tr><td><div id='fooBar'></div></td></tr>");
             out.println("<tr><td><input type='submit'></td></tr>");
             out.println("</table>");
             out.println("</form>");
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
