/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alumno;

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
        
        String idGrupo = (String)session.getAttribute("idGrupoRA");
        String idAlumno = (String)session.getAttribute("idAlumnoRA");
        String idProfesor = (String)session.getAttribute("idProfesorRA");
        String nombreAlumno = (String)session.getAttribute("nombreAlumnoRA");
        String idEjercicio1 = (String)session.getAttribute("idEjercicio1");
        String idEjercicio2 = (String)session.getAttribute("idEjercicio2");
        String idEjercicio3 = (String)session.getAttribute("idEjercicio3");
        
        System.out.println("-------------INFO. QUE RECIBO PARA EXAMEN-----------");
        System.out.println(idGrupo);
        System.out.println(idAlumno);
        System.out.println(idProfesor);
        System.out.println(nombreAlumno);
        System.out.println(idEjercicio1);
        System.out.println(idEjercicio2);
        System.out.println(idEjercicio3);
        System.out.println("----------------------------------------------------");
        
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
        out.println("                    Calificación:");
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
        double calif = (resultado*10/9);
        int calificacio = (int)calif;
        String calificacion = String.valueOf(calificacio);
        out.println("<h3><b>" + calificacio + "</b></h3>");
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
        
        try {
                //Contruye un documento JDOM usando SAX, para procesar xml
                SAXBuilder builder = new SAXBuilder();
                //Para obtener la ruta absoluta del proyecto
                String rutaAbsoluta = request.getSession().getServletContext().getRealPath("/");
                rutaAbsoluta = rutaAbsoluta.replace("\\", "/");
                rutaAbsoluta = rutaAbsoluta.replaceAll("/build", "");
                rutaAbsoluta = rutaAbsoluta.concat("BD.xml");
                File BD = new File(rutaAbsoluta);
                //Para cargar el documento xml
                Document doc = builder.build(BD);//documentos para contruir base de datos
                //Se obtiene el elemento raiz del xml
                Element raiz = doc.getRootElement();
                //Crea los elementos que conforman a un Ejercicio con los valores del nuevo registro
                Element ronda_alumno = new Element("RONDA_ALUMNO");
                Element nombreAlumnoF = new Element("nombreAlumno");
                Element calificacionF = new Element("calificacion");
                Element ejercicio1 = new Element("idEjercicio");
                Element ejercicio2 = new Element("idEjercicio");
                Element ejercicio3 = new Element("idEjercicio");

                //Lista de nodos almacenados, lo que esta contenido entre las etiquetas de raiz
                List lista = raiz.getChildren("RONDA_ALUMNO");
                String id = "";
                int id2;
                //Obtiene informacion del último elemento añadido, para asignar ID
                if (lista.size() == 0) {
                    id = "1";
                } else {
                    Element e = (Element) lista.get(lista.size() - 1);
                    id = e.getAttributeValue("idRonda");
                    id2 = Integer.parseInt(id) + 1;
                    id = "" + id2;//para ultimo id
                }

                ronda_alumno.setAttribute("idRonda", id);
                ronda_alumno.setAttribute("idGrupo", idGrupo);
                ronda_alumno.setAttribute("idAlumno", idAlumno);
                ronda_alumno.setAttribute("idProfesor", idProfesor);
                

                nombreAlumnoF.setText(nombreAlumno);//setText lo que va entre etiqueta de apertura y cierre
                calificacionF.setText(calificacion);
                ejercicio1.setText(idEjercicio1);
                ejercicio2.setText(idEjercicio2);
                ejercicio3.setText(idEjercicio3);

                //Agregar contenido de los elementos a nodo padre (EJERCICIO)
                ronda_alumno.addContent(nombreAlumnoF);
                ronda_alumno.addContent(calificacionF);
                ronda_alumno.addContent(ejercicio1);
                ronda_alumno.addContent(ejercicio2);
                ronda_alumno.addContent(ejercicio3);

                //Agregar contenido de USUARIO a raiz
                raiz.addContent(ronda_alumno);

                //Se crea serializador xml (para guardar en el xml)
                XMLOutputter xmlo = new XMLOutputter();

                //validar que si escriba bien el archivo, guardar los cambios al archivo
//                try (FileWriter fw = new FileWriter(rutaAbsoluta+"\\BD.xml")){
                try (FileWriter fw = new FileWriter(rutaAbsoluta)) {
                    xmlo.setFormat(Format.getPrettyFormat());//Formato de salida al xml
                    xmlo.output(doc, fw);//se escribe en el archivo
                    fw.flush();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
    }
}
