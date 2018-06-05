package profesor;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class dragAndDropImagen extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            HttpSession session = request.getSession();
            session.setAttribute("banderaArchivo", 2);
            out.println("<!DOCTYPE html>");
            out.println("<script src=\"js/dropzone.js\"></script>\n" +
"<link rel=\"stylesheet\" href=\"css/dropzone.css\">\n" +
"<p>\n" +
"  This is the most minimal example of Dropzone. The upload in this example\n" +
"  doesn't work, because there is no actual server to handle the file upload.\n" +
"</p>\n" +
"\n" +
"<!-- Change /upload-target to your upload address -->\n" +
"<form id=\"my-awesome-dropzone\" action=\"uploadFiles\" class=\"dropzone\" method=\"POST\">\n" +
"    <div class=\"fallback\">\n" +
"        <input name=\"file\" type=\"file\" multiple/>\n" +
"    </div>\n" +
"</form>\n" +
"<form action='dragAndDropAudioImagen' method=\"POST\">\n" +
"        <input type=\"submit\" value='Siguiente'/>\n" +
"</form>");
    }

}