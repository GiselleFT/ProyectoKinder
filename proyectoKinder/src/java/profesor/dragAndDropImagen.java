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

        String usuario = (String) session.getAttribute("usuario");//Del administrador
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
        out.println("<h2>Selecciona o arrastra un archivo .jpeg o .mp4</h2>");
        //Se sube archivo
        out.println("<form id='dd2' action='uploadFiles' class='dropzone' method='POST' enctype = 'multipart/form-data'>");
        out.println("<input name='file' type='file' style='color:transparent'/>");
        out.println("</form>");
        //Para continuar con la creacion del ejercicio
        out.println("<br />");
        out.println("<h3><b>Paso 3/4</b></h3>");
        out.println("<form action='dragAndDropAudioImagen' method='POST'>");
        out.println("<input type='submit' class=\"btn btn-w-m btn-primary\" value='Continuar'/>");
        out.println("</form>");

        out.println("<br />");
        out.println("<br />");
        //Agregar Usuario

        out.println("<form action='adminEjercicios' method='get'>");
        out.println("<h3><input type='submit' value='Cancelar' class=\"btn btn-sm btn-danger\"></h3>");
        out.println("</form>");
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
                + "            Dropzone.options.dd2 = {\n"
                + "                maxFiles: 1,\n"
                + "                addRemoveLinks: true,\n"
                + "                acceptedFiles: '.jpg,.mp4',\n"
                + "                dictDefaultMessage: 'Arrastra archivo .jpg en este drop',\n"
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
        //Bloque drag and drop
    }
}
