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

public class modificarAudioImagen extends HttpServlet {

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
        session.setAttribute("banderaModificar", 3);

        //Bloque drag and drop
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");

        out.println("<title>Agregar Ejercicio Paso 2/4</title>");
        out.println("<script src='js/dropzone.js'></script>");
        out.println("<link rel='stylesheet' href='css/estilos.css'>");
        out.println("<link rel='stylesheet' href='css/dropzone.css'>");
        out.println("    <meta charset=\"utf-8\">");
        out.println("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
        out.println("    <title>INSPINIA | Empty Page</title>");
        out.println("    <link href=\"css/bootstrap.min.css\" rel=\"stylesheet\">");
        out.println("    <link href=\"font-awesome/css/font-awesome.css\" rel=\"stylesheet\">");
        out.println("    <link href=\"css/animate.css\" rel=\"stylesheet\">");
        out.println("    <link href=\"css/style.css\" rel=\"stylesheet\">");
        out.println("</head>");
        out.println("<body class=\"\">");
        out.println("    <div id=\"wrapper\">");
        out.println("    <nav class=\"navbar-default navbar-static-side\" role=\"navigation\">");
        out.println("        <div class=\"sidebar-collapse\">");
        out.println("            <ul class=\"nav metismenu\" id=\"side-menu\">");
        out.println("                <li class=\"nav-header\">");
        out.println("                    <div class=\"logo-element\">");
        out.println("                        IN+");
        out.println("                    </div>");
        out.println("                </li>");
        out.println("                <li>");
        out.println("                    <a href=\"#\"><i class=\"fa fa-user-circle\"></i> <span class=\"nav-label\">Menu profesor</span><span class=\"fa arrow\"></span></a>");
        out.println("                    <ul class=\"nav nav-second-level collapse\">");
        out.println("                        <li><a href=\"adminEjercicios\">Administrar Ejercicios</a></li>");
        out.println("                        <li><a href=\"adminGrupos\">Administrar Grupos</a></li>");
        out.println("                        <li><a href=\"verCalificacionesProfesor\">Ver Calificaciones</a></li>");
        out.println("                    </ul>");
        out.println("                </li>");
        out.println("                <li class=\"special_link\">");
        out.println("                    <a href=\"cerrarSesion\"><i class=\"fa fa-times-rectangle\"></i> <span class=\"nav-label\">Cerrar sesion</span></a>");
        out.println("                </li>");
        out.println("            </ul>");
        out.println("");
        out.println("        </div>");
        out.println("    </nav>");
        out.println("        <div id=\"page-wrapper\" class=\"gray-bg\">");
        out.println("        <div class=\"row border-bottom\">");
        out.println("        <nav class=\"navbar navbar-static-top  \" role=\"navigation\" style=\"margin-bottom: 0\">");
        out.println("        <div class=\"navbar-header\">");
        out.println("            <a class=\"navbar-minimalize minimalize-styl-2 btn btn-primary \" href=\"#\"><i class=\"fa fa-bars\"></i> </a>");
        out.println("            <form role=\"search\" class=\"navbar-form-custom\" action=\"search_results.html\">");
        out.println("                <div class=\"form-group\">");
        out.println("                    <input type=\"text\" placeholder=\"Search for something...\" class=\"form-control\" name=\"top-search\" id=\"top-search\">");
        out.println("                </div>");
        out.println("            </form>");
        out.println("        </div>");
        out.println("");
        out.println("        </nav>");
        out.println("        </div>");
        out.println("            <div class=\"row wrapper border-bottom white-bg page-heading\">");
        out.println("                <div class=\"col-sm-4\">");
        out.println("                    <h2>Bienvenido profesor: <b>" + usuario + "</b></h2>");
        out.println("                </div>");
        out.println("            </div>");

        out.println("<br />");
        out.println("<br />");
        out.println("            <div class=\"wrapper  wrapper-content animated fadeInRight\">");

        out.println("                    <div class=\"row\">");
        out.println("                <div class=\"col-lg-12\">");

        //tabla
        out.println("                <div align='center'>");
        out.println("                    <div class=\"row\">");
        out.println("                <div class=\"col-lg-12\">");
        out.println("                    <div class=\"ibox float-e-margins\">");
        out.println("                        <div class=\"ibox-title\">");
        //EMPIEZA EL DESMA DENTRO DEL CUADRO BLANCO
        out.println("<h2>Modificar - Selecciona o arrastra un archivo .mp3</h2>");
        //Se sube archivo
        out.println("<form id='dd1' action='uploadFiles' class='dropzone' method='POST' enctype = 'multipart/form-data'>");
        out.println("<input name='file' type='file' style='color:transparent'/>");
        out.println("</form>");
        //Para continuar con la creacion del ejercicio
        out.println("<br />");

        out.println("<br />");
        out.println("<br />");
        //Agregar Usuario

        out.println("<br />");
        out.println("<br />");
        out.println("<a href=\"/proyectoKinder/modificarEjercicio?id="+session.getAttribute("id")+"&first=2\" >\n" +
"                                <button  class=\"btn btn-w-m btn-danger\" type=\"submit\">Regresar</button>\n" +
"                            </a>");
        out.println("                </div>");

        out.println("");
        out.println("                        </div>");
        out.println("                    </div>");
        out.println("                </div>");

        out.println("            </div>");
        out.println("                        </div>");
        out.println("                    </div>");
        out.println("                </div>");

        out.println("            </div>");

        out.println("                </div>");
        out.println("            </div>");
        out.println("        </div>");
        out.println("        </div>");
        out.println("");
        out.println("    <script src=\"js/jquery-3.1.1.min.js\"></script>");
        out.println("    <script src=\"js/bootstrap.min.js\"></script>");
        out.println("    <script src=\"js/plugins/metisMenu/jquery.metisMenu.js\"></script>");
        out.println("    <script src=\"js/plugins/slimscroll/jquery.slimscroll.min.js\"></script>");
        out.println("    <script src=\"js/inspinia.js\"></script>");
        out.println("    <script src=\"js/plugins/pace/pace.min.js\"></script>");
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
        out.println("</body>");
        out.println("</html>");

    }

}
