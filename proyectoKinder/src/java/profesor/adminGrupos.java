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

public class adminGrupos extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        //Recuperamos la sesion
        HttpSession session = request.getSession();
        String usuario = (String) session.getAttribute("usuario");
        String tipoAtt = (String) session.getAttribute("tipo");
        String idUsuario = (String) session.getAttribute("idUsuario");
        System.out.println("ID DE USUARIO! " + idUsuario);
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

        try {
            //Contruye un documento JDOM usando SAX, para procesar xml
            SAXBuilder builder = new SAXBuilder();
            //Para obtener la ruta absoluta del proyecto
            String rutaAbsoluta = request.getSession().getServletContext().getRealPath("/");
            rutaAbsoluta = rutaAbsoluta.replace("\\", "/");
            rutaAbsoluta = rutaAbsoluta.replaceAll("/build", "");
            rutaAbsoluta = rutaAbsoluta.concat("BD.xml");
            File BD = new File(rutaAbsoluta);

            Document doc = builder.build(BD);//documentos para contruir base de datos
            //Se obtiene el elemento raiz del xml
            Element raiz = doc.getRootElement();
            //Lista de nodos almacenados, lo que esta contenido entre las etiquetas de raiz
            List lista = raiz.getChildren("GRUPO");

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
            
            out.println("        </div>");
            out.println("");
            out.println("        </nav>");
            out.println("        </div>");
            out.println("            <div class=\"row wrapper border-bottom white-bg page-heading\">");
            out.println("                <div class=\"col-sm-4\">");
            out.println("                    <h2>Bienvenido profesor: " + usuario + "</h2>");
            out.println("                </div>");
            out.println("            </div>");
            out.println("<br />");
//            if (request.getParameter("eliminado")!=null) {
//                out.println("<div class=\"alert alert-danger\" role=\"alert\"><H4>REGISTRO ELIMINADO</H4></div>");
//                
//            }
//            if (request.getParameter("error")!=null) {
//                out.println("<div class=\"alert alert-danger\" role=\"alert\"><H4>NO FUE POSIBLE CREAR EL EJERCICIO - ARCHIVOS FALTANTES</H4></div>");
//                
//            }
//            if (request.getParameter("listo")!=null) {
//                out.println("<div class=\"alert alert-success\" role=\"alert\"><H4>REGISTRO EXITOSO!</H4></div>");
//                
//            }
            if (request.getParameter("cancelMod")!=null) {
                out.println("<div class=\"alert alert-danger\" role=\"alert\"><H4>MODIFICACIÓN CANCELADA</H4></div>");
                
            }
//            if (request.getParameter("mod")!=null) {
//                out.println("<div class=\"alert alert-success\" role=\"alert\"><H4>MODIFICACIÓN EXITOSA!</H4></div>");
//                
//            }
            out.println("            <div class=\"wrapper  wrapper-content animated fadeInRight\">");
            out.println("                    <div class=\"row\">");
            out.println("                <div class=\"col-lg-12\">");
            out.println("                    <div class=\"ibox float-e-margins\">");
            out.println("                        <div class=\"ibox-title\">");
            out.println("                            <h5>Grupos</h5>");
            out.println("                            <div class=\"ibox-tools\">");
            out.println("                                <a class=\"collapse-link\">");
            out.println("                                    <i class=\"fa fa-chevron-up\"></i>");
            out.println("                                </a>");
            out.println("                                <a class=\"dropdown-toggle\" data-toggle=\"dropdown\" href=\"#\">");
            out.println("                                    <i class=\"fa fa-wrench\"></i>");
            out.println("                                </a>");
            out.println("                                <a class=\"close-link\">");
            out.println("                                    <i class=\"fa fa-times\"></i>");
            out.println("                                </a>");
            out.println("                            </div>");
            out.println("                        </div>");
            out.println("                        <div class=\"ibox-content\">");
            out.println("                            <div class=\"row\">");
            out.println("<div class=\"col-sm-200\">");
            out.println("<div class=\"input-group\"><input type=\"text\" id='names' onkeyup='loadDoc(this.value)' placeholder=\"Search\" class=\"input-sm form-control\"> <span class=\"input-group-btn\">");
            out.println("</span></div>");
            out.println("                                </div>");
            out.println("                            </div>");
            out.println("                            <div class=\"table-responsive\">");
            out.println("                                <table id='miTabla' class=\"footable table table-stripped\" data-page-size=\"32\" data-filter=#filter>");
            out.println("                                    <thead>");
            out.println("                                    <tr>");
            out.println("<th>ID</th>");
            out.println("<th>Nombre</th>");
            out.println("<th>Administrar</th>");
            out.println("                                    </tr>");
            out.println("                                    </thead>");
            

            //Para recorrer el arbol de nodos
            for (int i = 0; i < lista.size(); i++) {//Por cada elemento  
                //Se procesa un elemento de la lista
                Element element = (Element) lista.get(i);//guarda los datos de la lista en un arreglo de elementos
                //Obtiene los elementos que contiene el elemento actual
                List lista2 = element.getChildren();//pasa los elementos a lista2
                //Se recupera nombre
                Element idProfesor = (Element) lista2.get(1);
                //Se recupera ID de ejercicio
                Attribute id = element.getAttribute("id");
              
                //Solo se pueden mostrar los grupos del profesor
                if (idUsuario.matches(idProfesor.getText())) {
                    Element grupo = (Element) lista2.get(0);
                    //Se crea una fila para desplegar info de ejercicio
                    out.println("                                    <tbody>");
                    out.println("<tr>");
                    out.println("<td>");
                    out.println(id.getValue());
                    out.println("</td>");
                    out.println("<td>");
                    out.println(grupo.getText());
                    out.println("</td>");
                    //Por medio del id, se localiza al ejercicio por ver
                    out.println("<td>");
                    out.println("<form action='adminGpo' method='post'>");
                    out.println("<input type='hidden' name='id' value=" + id.getValue() + ">");//Del grupo
                    out.println("<input type='submit' value='Administrar' class=\"btn btn-w-m btn-default\">");
                    out.println("</form>");
                    out.println("</td>");
                    out.println("</tr>");
                    out.println("                                    </tbody>");
                }
            }

            //----------------------
            out.println("                                </table>");
            
            
            //Tabla dinamica conforme a busqueda
                out.println("<script>");
                out.println("function loadDoc(nombreProfesor){\n"
                    + "        if(document.getElementById(\"names\").value.length > 0){\n"
                    + "        var xhttp = new XMLHttpRequest();\n"
                    + "                        xhttp.onreadystatechange = function(){\n"
                    + "                        if(xhttp.readyState == 4 && xhttp.status == 200) {\n"
                    + "                            cargarTabla(xhttp, nombreProfesor);\n"
                    + "                        }\n"
                    + "            };\n"
                    + "            xhttp.open(\"GET\", \"BD.xml\", true);\n"
                    + "            xhttp.send();\n"
                    + "        }\n"
                    + "    }");
                out.println("function cargarTabla(xml, nombreProfesor){\n"
                    + "        var i, j;\n"
                    + "        var xmlDoc = xml.responseXML;\n"
                    + "        var table = \"<tr><th>ID</th><th>Nombre</th><th>Administrar</th></tr>\";\n"
                    + "                var x = xmlDoc.getElementsByTagName(\"GRUPO\");\n"
                    + "                    for (i = 0; i < x.length; i++) {\n"
                    + "                        //Contiene el nombre del grupo\n"
                    + "                        var elemento = x[i].getElementsByTagName('grupo')[0].childNodes[0].nodeValue;\n"
                    + "                        //Extraer del nombre del profesor la cadena del tamaño del prefijo a comparar\n"
                    + "                        var aux = elemento.substring(0,nombreProfesor.length);\n"
                    + "                        //Si el prefijo coincide con el prefijo del profesor lo muestra en la tabla\n"
                    + "                        if(nombreProfesor === aux){\n"
                    + "                            table += \"<tr><td>\" +\n"
                    + "                            x[i].getAttribute('id') +\n"
                    + "                            \"</td><td>\"+\n"
                    + "                            x[i].getElementsByTagName('grupo')[0].childNodes[0].nodeValue + \n"
                    + "                            \"</td><td>\"+\n"
                    + "                            \"<form action='adminGpo' method='post'>\"+\n"
                    + "                            \"<input type='hidden' name='id' value='\"+x[i].getAttribute('id')+\"'>\"+\n"
                    + "                            \"<input type='submit' value='Administrar'>\"+\n"
                    + "                            \"</form>\"+\n"
                    + "                            \"</td></tr>\";\n"
                    + "                        }\n"
                    + "                }\n"
                    + "                document.getElementById(\"miTabla\").innerHTML = table;\n"
                    + "            }        ");
                out.println("</script>");
            
            
            out.println("                            </div>");
            
            out.println("");
            out.println("                        </div>");
            out.println("                    </div>");
            out.println("                </div>");
            out.println("");
            out.println("            </div>");
            out.println("<form action='menuProfesor' method='get'>");
            out.println("<input type='submit' value='Menu Profesor' class=\"btn btn-sm btn-warning\">");
            out.println("</form>");
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
        } catch (JDOMException e) {
        }
    }

}
