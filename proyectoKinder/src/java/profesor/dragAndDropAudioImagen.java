package profesor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

public class dragAndDropAudioImagen extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();//Del registro del nuevo ejercicioNuevo
        
        String usuario = (String) session.getAttribute("usuario");//Del administrador
        String contrasena = (String) session.getAttribute("contrasena");//Del administrador
        String tipoAtt = (String) session.getAttribute("tipo");
        //session.setAttribute("tipo", tipoAtt);//conservar sesion del administrador con su tipo
        String idUsuario = (String) session.getAttribute("idUsuario");

        //info del profesor
//            System.out.println("menuProfesor");
//            System.out.println("prof= "+usuario);
//            System.out.println("prof= "+contrasena);
        System.out.println("prof= " + tipoAtt);

        //********Valida Tipo Usuario****************//
        if (!tipoAtt.equals("2")) {
            response.sendRedirect("login.html");
        }
        //*******************************************//
        
        
        out.println("<!DOCTYPE html>");
        out.println("<script src=\"js/dropzone.js\"></script>\n"
                + "<link rel=\"stylesheet\" href=\"css/dropzone.css\">\n"
                + "<p>\n"
                + "  This is the most minimal example of Dropzone. The upload in this example\n"
                + "  doesn't work, because there is no actual server to handle the file upload.\n"
                + "</p>\n"
                + "\n"
                + "<!-- Change /upload-target to your upload address -->\n"
                + "<form id=\"my-awesome-dropzone\" action=\"uploadFiles\" class=\"dropzone\" method=\"POST\">\n"
                + "    <div class=\"fallback\">\n"
                + "        <input name=\"file\" type=\"file\" multiple/>\n"
                + "    </div>\n"
                + "</form>\n"
                + "<form action='addExercise' method=\"POST\">\n"
                + "        <input type=\"submit\" value='Crear'/>\n"
                + "</form>");
        
        

    }

}
