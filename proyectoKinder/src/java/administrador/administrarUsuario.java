package administrador;

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

public class administrarUsuario extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        //Recuperamos la sesion
        HttpSession session = request.getSession();
        String usuario = (String) session.getAttribute("usuario");
        String contrasena = (String) session.getAttribute("contrasena");
        String tipoAtt = (String) session.getAttribute("tipo");
        PrintWriter out = response.getWriter();

        //info del administrador
//            System.out.println("administrarUsuario");
//            System.out.println("admin= "+usuario);
//            System.out.println("admin= "+contrasena);
//            System.out.println("admin= "+tipoAtt);
        //********Valida Tipo Usuario****************//
        if (!tipoAtt.equals("1")) {
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
//                List lista = raiz.getChildren();
            List lista = raiz.getChildren("USUARIO");
//*******************************************************************************************
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
            out.println("                        <li><a href=\"administrarUsuario\">Administrar Usuarios</a></li>");
            out.println("                        <li><a href=\"administrarGrupos\">Administrar Grupos</a></li>");
            out.println("                    </ul>");
            out.println("                </li>");
            out.println("                <li class=\"special_link\">");
            out.println("                    <a href=\"login.html\"><i class=\"fa fa-times-rectangle\"></i> <span class=\"nav-label\">Cerrar sesion</span></a>");
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
            out.println("                    <h2>Bienvenido administrador: </h2>");
            out.println("                </div>");
            out.println("            </div>");
            out.println("<form action='agregarUsuario' method='post'>");
            out.println("<input type='submit' value='Crear Usuario'>");
            out.println("</form>");

            out.println("<br />");
            out.println("<br />");
            out.println("            <div class=\"wrapper  wrapper-content animated fadeInRight\">");
            
            out.println("                    <div class=\"row\">");
            out.println("                <div class=\"col-lg-12\">");
            out.println("                    <div class=\"ibox float-e-margins\">");
            out.println("                        <div class=\"ibox-title\">");
            out.println("                            <h5>Custom rnsive table </h5>");
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
            out.println("<div class=\"input-group\"><input type=\"text\" placeholder=\"Search\" class=\"input-sm form-control\"> <span class=\"input-group-btn\">");
            out.println("<button type=\"button\" class=\"btn btn-sm btn-primary\"> Go!</button> </span></div>");
            out.println("                                </div>");
            out.println("                            </div>");
            out.println("                            <div class=\"table-responsive\">");
            out.println("                                <table class=\"footable table table-stripped\" data-page-size=\"32\" data-filter=#filter>");
            out.println("                                    <thead>");
            out.println("                                    <tr>");
            out.println("<th>Usuario</th>");
            out.println("<th>Nombre</th>");
            out.println("<th>Tipo</th>");
            out.println("<th>Modificar</th>");
            out.println("<th>Eliminar</th>");
            out.println("                                    </tr>");
            out.println("                                    </thead>");
            String type = "";

            //Para recorrer el arbol de nodos
            for (int i = 0; i < lista.size(); i++) {//Por cada elemento  
                //Se procesa un elemento de la lista
                Element element = (Element) lista.get(i);//guarda los datos de la lista en un arreglo de elementos
                //Obtiene los elementos que contiene el elemento actual
                List lista2 = element.getChildren();//pasa los elementos a lista2
                //Se recupera nombre
                Element nombre = (Element) lista2.get(0);
                //Se recupera usuario 
                Element usuario2 = (Element) lista2.get(1);
                //Se recupera tipo de usuario
                Attribute tipo = element.getAttribute("tipo");

                if (tipo.getValue().matches("1")) {
                    type = "Administrador";
                } else if (tipo.getValue().matches("2")) {
                    type = "Profesor";
                } else if (tipo.getValue().matches("3")) {
                    type = "Alumno";
                } else {
                    type = "No definido";
                }

                //Se recupera id de usuario
                Attribute id = element.getAttribute("id");
                out.println("                                    <tbody>");
                out.println("                                    <tr>");
                out.println("                                        <td>" + nombre.getValue() + "</td>");
                out.println("                                        <td>" + usuario2.getValue() + "</td>");
                out.println("                                        <td>" + type + "</td>");
                out.println("                                        <td>");
                out.println("<form action='modificarUsuario' method='post'>");
                out.println("<input type='hidden' name='id' value=" + id.getValue() + ">");//Del administrador
                out.println("<input type='submit' value='Modificar'>");
                out.println("</form>");

                out.println("                                        </td>");
                out.println("                                        <td>");
                out.println("<form action='eliminarUsuario' method='post'>");

                out.println("<input type='hidden' name='id' value=" + id.getValue() + ">");//Del administrador
                out.println("<input type='submit' value='Eliminar'>");
                out.println("</form>");
                out.println("                                        </td>");
                out.println("                                    </tr>");
                out.println("                                    </tbody>");
            }
                out.println("                                </table>");
                out.println("                            </div>");
                out.println("<form action='menuAdministrador' method='get'>");
            out.println("<input type='submit' value='Menu Administrador'>");
            out.println("</form>");
                out.println("");
                out.println("                        </div>");
                out.println("                    </div>");
                out.println("                </div>");
                out.println("");
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

//*******************************************************************************************
                
        } catch (JDOMException e) {
        }
    }

}
