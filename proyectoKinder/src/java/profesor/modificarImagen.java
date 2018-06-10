package profesor;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

public class modificarImagen extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        //Se recuperan los parametros del formulario y se suben a sesion 
//            String audioInstruccionM = (String)request.getParameter("audioInstruccionM");//Del registro del nuevo ejercicioNuevo
//            session.setAttribute("audioInstruccionM", audioInstruccionM);

        String usuario = (String) session.getAttribute("usuario");
        String tipoAtt = (String) session.getAttribute("tipo");
        String idUsuario = (String) session.getAttribute("idUsuario");
        String idEjercicio = (String) session.getAttribute("id");
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
        session.setAttribute("banderaModificar", 2);

        //Bloque drag and drop
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Modificar Audio Instruccion</title>");
        out.println("<script src='js/dropzone.js'></script>");
        out.println("<link rel='stylesheet' href='css/estilos.css'>");
        out.println("<link rel='stylesheet' href='css/dropzone.css'>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Modificar Audio Instruccion</h1>");
        out.println("<h2>Selecciona un archivo .mp3</h2>");
        //Se sube archivo
        out.println("<form id='dd1' action='uploadFiles' class='dropzone' method='POST' enctype = 'multipart/form-data'>");
        out.println("<input name='file' type='file' style='color:transparent'/>");
        out.println("</form>");

        out.println("<br />");
        out.println("<br />");


        out.println("</body>");
        out.println("</html>");

        //Para restringir tipos de archivos
        out.println("<script>\n"
                + "            Dropzone.options.dd1 = {\n"
                + "                maxFiles: 1,\n"
                + "                addRemoveLinks: true,\n"
                + "                acceptedFiles: '.mp3',\n"
                + "                dictDefaultMessage: 'Arrastra archivo .mp3 en este drop',\n"
                + "                init: function() {\n"
                + "                    var self = this;\n"
                + "                    self.options.addRemoveLinks = true;\n"
                + "                    self.options.dictRemoveFile = 'Delete';\n"
                + "                    this.on('complete', function (file) {\n"
                + "                        setTimeout(3000);\n"
                + "                        swal('Añadido correctamente','Da click en el boton','success').then((value) => {                                    \n"
                + "                            setTimeout(1000);\n"
                + "                            this.removeFile(file); \n"
                + "                        });\n"
                + "                    });\n"
                + "                }    \n"
                + "            };\n"
                + "        </script>");

        out.println("<br />");
        out.println("<br />");

        out.println("<a href=\"http://localhost:29201/proyectoKinder/modificarEjercicio?id="+session.getAttribute("id")+"&first=2\" >\n" +
"                                <button  type=\"submit\">Regresar</button>\n" +
"                            </a>");
        
        out.println("<script>\n" +
"            Dropzone.options.dd1 = {\n" +
"                maxFiles: 1,\n" +
"                addRemoveLinks: true,\n" +
"                acceptedFiles: '.mp3',\n" +
"                dictDefaultMessage: 'Arrastra archivo .mp3 en este drop',\n" +
"                init: function() {\n" +
"                    var self = this;\n" +
"                    self.options.addRemoveLinks = true;\n" +
"                    self.options.dictRemoveFile = 'Delete';\n" +
"                    this.on('complete', function (file) {\n" +
"                        setTimeout(3000);\n" +
"                        swal('Añadido correctamente','Da click en el boton','success').then((value) => {                                    \n" +
"                            setTimeout(1000);\n" +
"                            this.removeFile(file); \n" +
"                        });\n" +
"                    });\n" +
"                }    \n" +
"            };\n" +
"        </script>");
        out.println("</body>");
        out.println("</html>");
    }

}
