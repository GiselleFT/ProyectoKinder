package profesor;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class agregarEjercicio extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String usuario = (String) session.getAttribute("usuario");
        String tipoAtt = (String) session.getAttribute("tipo");
        String idUsuario = (String) session.getAttribute("idUsuario");
        PrintWriter out = response.getWriter();
        

        //info del profesor
//            System.out.println("menuProfesor");
//            System.out.println("prof= "+usuario);
//            System.out.println("prof= "+contrasena);
//            System.out.println("prof= "+tipoAtt);
        //********Valida Tipo Usuario****************//
        if (!tipoAtt.equals("2")) {
            response.sendRedirect("login.html");
        }
        //*******************************************//

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
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
        out.println("                    <a href=\"#\"><i class=\"fa fa-user-circle\"></i> <span class=\"nav-label\">Menu administrador</span><span class=\"fa arrow\"></span></a>");
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
        out.println("<form action='dragAndDropInstruccion' class='dropzone' method='post'>");
        out.println("<h2>Nombre:</h2> <input id='nombre' type='text' name='nombreNuevo' required/><br />");
        out.println("<h2>Instruccion:</h2> <input id='instruccion' type='text' name='instruccionNuevo' required/><br />");
        //out.println("<h2>Audio Instruccion:</h2> <input id='audioInstruccion' type='text' name='audioInstruccionNuevo' required/><br />");
        //out.println("<h2>Imagen:</h2> <input id='imagen' type='text' name='imagenNuevo' required/><br />");
        //out.println("<h2>Audio Imagen:</h2> <input id='audioImagen' type='text' name='audioImagenNuevo' required/><br />");
        out.println("<h2>Pista:</h2> <input id='pista' type='text' name='pistaNuevo' required/><br />");
        out.println("<h2>Respuesta correcta:</h2> <input id='respuestaCorrecta' type='text' name='respuestaCorrectaNuevo' required/><br />");
        out.println("<h2>Respuesta incorrecta 1:</h2> <input id='respuestaIncorrecta1' type='text' name='respuestaIncorrecta1Nuevo' required/><br />");
        out.println("<h2>Respuesta incorrecta 2:</h2> <input id='respuestaIncorrecta2' type='text' name='respuestaIncorrecta2Nuevo' required/><br />");
        out.println("<br />");
        out.println("<br />");
        out.println("<h3><b>Paso 1/4</b></h3>");
        out.println("<h3><input type='submit' class=\"btn btn-w-m btn-primary\" value='Continuar'></h3>");
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
        out.println("</body>");
        out.println("</html>");
        out.println("");
    }

}
