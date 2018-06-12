package administrador;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class agregarUsuario extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String usuario = (String) session.getAttribute("usuario");
        String tipoAtt = (String) session.getAttribute("tipo");
        PrintWriter out = response.getWriter();

        //info del administrador
//            System.out.println("agregarUsuario");
//            System.out.println("admin= "+usuario);
//            System.out.println("admin= "+contrasena);
//            System.out.println("admin= "+tipoAtt);
        //********Valida Tipo Usuario****************//
        if (!tipoAtt.equals("1")) {
            response.sendRedirect("login.html");
        }
        //*******************************************//

        

//------------------------------------------------------------------------------------------------------------
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
        out.println("<li class=\"nav-header\">");
        out.println("<div class=\"dropdown profile-element\">");
        out.println("	<span>");
        out.println("    	<img alt=\"image\" class=\"img-circle\" src=\"img/munequito.png\" width=\"100\" height=\"100\"/>");
        out.println("    </span>");
        out.println("</div>");
        out.println("</li>");
        out.println("                <li>");
        out.println("                    <a href=\"#\"><i class=\"fa fa-user-circle\"></i> <span class=\"nav-label\">Menu administrador</span><span class=\"fa arrow\"></span></a>");
        out.println("                    <ul class=\"nav nav-second-level collapse\">");
        out.println("                        <li><a href=\"administrarUsuario\">Administrar Usuarios</a></li>");
        out.println("                        <li><a href=\"administrarGrupos\">Administrar Grupos</a></li>");
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

        
        out.println("<form action='addUser' method='get'>");
        out.println("<h2>Nombre:</h2> <input id='nombre' type='text' name='nombreNuevo' required/><br />");
        out.println("<h2>Usuario:</h2> <input id='usuario' type='text' name='usuarioNuevo' required/><br />");
        out.println("<h2>Contrasena:</h2> <input id='contrasena' type='password' name='contrasenaNuevo' required/><br />");
        out.println("<h2>Tipo:</h2> <select id='tipo' name='tipoNuevo'/>"//Combobox
                + "<option>Administrador</option>"
                + "<option>Profesor</option>"
                + "<option>Alumno</option></select>"
                + "<br />");
        out.println("<input type='hidden' name='tipo' value=" + tipoAtt + ">");//Del administrador
        out.println("<br />");
        out.println("<br />");
        
        
        out.println("<h3><input type='submit' class=\"btn btn-w-m btn-primary\" value='Agregar usuario'></h3>");
        out.println("</form>");

        out.println("<br />");
        out.println("<br />");
        //Agregar Usuario

        
        out.println("<form action='administrarUsuario' method='get'>");
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
