package profesor;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class dragAndDropInstruccion extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         
            response.setContentType("text/html;charset=UTF-8");
            HttpSession session = request.getSession();
        String nombreNuevo = (String) request.getParameter("nombreNuevo");//Del registro del nuevo ejercicioNuevo
        session.setAttribute("nombreNuevo", nombreNuevo);
        String instruccionNuevo = (String) request.getParameter("instruccionNuevo");
        session.setAttribute("instruccionNuevo", instruccionNuevo);
        String audioInstruccionNuevo = "";/*(String) request.getParameter("audioInstruccionNuevo");*/
        session.setAttribute("audioInstruccionNuevo", audioInstruccionNuevo);
        String imagenNuevo =""; /*(String)request.getParameter("imagenNuevo");*/
        session.setAttribute("imagenNuevo", imagenNuevo);
        String audioImagenNuevo = ""; /*(String) request.getParameter("audioImagenNuevo");*/
        session.setAttribute("audioImagenNuevo", audioImagenNuevo);
        String pistaNuevo = (String) request.getParameter("pistaNuevo");
        session.setAttribute("pistaNuevo", pistaNuevo);
        String respuestaCorrectaNuevo = (String) request.getParameter("respuestaCorrectaNuevo");
        session.setAttribute("respuestaCorrectaNuevo", respuestaCorrectaNuevo);
        String respuestaIncorrecta1Nuevo = (String) request.getParameter("respuestaIncorrecta1Nuevo");
        session.setAttribute("respuestaIncorrecta1Nuevo", respuestaIncorrecta1Nuevo);
        String respuestaIncorrecta2Nuevo = (String) request.getParameter("respuestaIncorrecta2Nuevo");
        session.setAttribute("respuestaIncorrecta2Nuevo", respuestaIncorrecta2Nuevo);

        session.setAttribute("banderaArchivo", 1);
        String usuario = (String) session.getAttribute("usuario");//Del administrador
        String contrasena = (String) session.getAttribute("contrasena");//Del administrador
        String tipoAtt = (String) session.getAttribute("tipo");
        //session.setAttribute("tipo", tipoAtt);//conservar sesion del administrador con su tipo
        String idUsuario = (String) session.getAttribute("idUsuario");
        PrintWriter out = response.getWriter();

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
"<form action='dragAndDropImagen' method=\"POST\">\n" +
"        <input type=\"submit\" value='Siguiente'/>\n" +
"</form>");
    }

}