/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alumno;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author sam-y
 */
public class puntaje extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //Recuperamos la sesion
        HttpSession session = request.getSession();
        String usuario = (String) session.getAttribute("usuario");
        String tipoAtt = (String) session.getAttribute("tipo");
        PrintWriter out = response.getWriter();

        //info del alumno
//            System.out.println("menuAlumno");
//            System.out.println("alumno= "+usuario);
//            System.out.println("alumno= "+contrasena);
//            System.out.println("alumno= "+tipoAtt);
        //********Valida Tipo Usuario****************//
        if (!tipoAtt.equals("3")) {
            response.sendRedirect("login.html");
        }
        //*******************************************//
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("    <meta charset=\"utf-8\">");
        out.println("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
        out.println("    <title>Menu Alumno</title>");
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
        out.println("                    <a href=\"#\"><i class=\"fa fa-user-circle\"></i> <span class=\"nav-label\">Menu alumno</span><span class=\"fa arrow\"></span></a>");
        out.println("                    <ul class=\"nav nav-second-level collapse\">");
        out.println("                        <li><a href=\"resolverEjercicios\">Resolver Ejercicios</a></li>");
        out.println("                        <li><a href=\"verCalificaciones\">Ver Calificaciones</a></li>");
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
        out.println("                <center>");
        out.println("                    <h2><font color=\"blue\"><b>Resultados (progreso): </b></font></h2>");
        out.println("                    <br/>");
        out.println("                    <p>");

        out.println("<h3>Ejercicio 1 resuelto en " + (session.getAttribute("clics1")) + " intento(s)</h3>");//contenido que se va a desplegar dentro de la pagina web
        out.println("<h3>Ejercicio 2 resuelto en " + session.getAttribute("clics2") + " intento(s)</h3>");
        out.println("<h3>Ejercicio 3 resuelto en " + request.getParameter("clics3") + " intento(s)</h3>");
//Este es el cambio
        out.println("                    </p>");
        out.println("                    <br/>");
        out.println("                    <h2><font color=\"blue\"><b>");
        out.println("                    Puntuacion final:");
        out.println("                    </b></font></h2>");
        out.println("                    <br/>");
        int calif1 = Integer.parseInt((String) session.getAttribute("clics1"));
        int calif2 = Integer.parseInt((String) session.getAttribute("clics2"));
        int calif3 = Integer.parseInt(request.getParameter("clics3"));
        System.out.println(calif1 + " - " + calif2 + " - " + calif3);
        if (calif1 < 3) {
            if (calif1 < 2) {
                if (calif1 == 1) {
                    calif1 =3;
                }
                else{
                    calif1 = -10; //nunca deberia llegar
                }
            }
            else{
                calif1=1;
            }
        }
        else{
            calif1=0;
        }
        if (calif2 < 3) {
            if (calif2 < 2) {
                if (calif2 == 1) {
                    calif2 =3;
                }
                else{
                    calif2 = -10; //nunca deberia llegar
                }
            }
            else{
                calif2=1;
            }
        }
        else{
            calif2=0;
        }
        if (calif3 < 3) {
            if (calif3 < 2) {
                if (calif3 == 1) {
                    calif3 =3;
                }
                else{
                    calif3 = -10; //nunca deberia llegar
                }
            }
            else{
                calif3=1;
            }
        }
        else{
            calif3=0;
        }
        int resultado = calif1 + calif2 + calif3;
        out.println("<h3><b>" + resultado + " / 9</b></h3>");
        out.println("                    <br/>");
        out.println("                    <br/>");
        if (resultado==9) {
            out.println("<h1><font color='green'><b>FELICIDADES, LO HICISTE INCREIBLE!!</b></font></h1>");
        }
        else if (resultado>=5) {
            out.println("<h1><font color='green'><b>FELICIDADES, SIGUE ESFORZANDOTE!!</b></font></h1>");
        }
        else{
            out.println("<h1><font color='red'><b>UPS... NECESITAMOS REPASAR MÁS</b></font></h1>");
        }
        out.println("                    <br/>");
        out.println("                    <br/>");
        out.println("<form action='menuAlumno' method='get'>");
        out.println("<input type='submit' value='Continuar' class=\"btn btn-sm btn-success\">");
        out.println("</form>");
        out.println("                </center>");
        out.println("            </div>");
        out.println("        </div>");
        out.println("        </div>");
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
