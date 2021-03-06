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

public class modificarEjercicio extends HttpServlet {

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

        //info del ejercicio a modificar
        String id = (String) request.getParameter("id");//id del ejercicio a modificar
        String first = (String) request.getParameter("first");
        session.setAttribute("id", id);
        System.out.println("id modificarEjercicio= " + id);

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
            //Lista de nodos almacenados, lo que esta contenido entre las etiquetas de raiz
            List lista = raiz.getChildren("EJERCICIO");

            //Para recorrer el arbol de nodos
            for (int i = 0; i < lista.size(); i++) {//Por cada elemento 
                //Se procesa un elemento de la lista
                Element element = (Element) lista.get(i);//guarda los datos de la lista en un arreglo de elementos
                //encontrar el elemento con el id capturado
                Attribute idElement = element.getAttribute("id");
                if (idElement.getValue().matches(id)) {//se ha encontrado ejercicio con id a modificar
                    //Obtiene los elementos que contiene el elemento actual
                    if (first.equals("1")) {
                        List lista2 = element.getChildren();//pasa los elementos a lista2
                        Element nombre = (Element) lista2.get(0);
                        Element instruccion = (Element) lista2.get(1);
                        Element audioInstruccion = (Element) lista2.get(2);
                        Element imagen = (Element) lista2.get(3);
                        Element audioImagen = (Element) lista2.get(4);
                        Element pista = (Element) lista2.get(5);
                        Element respuestaCorrecta = (Element) lista2.get(6);
                        Element respuestaIncorrecta1 = (Element) lista2.get(7);
                        Element respuestaIncorrecta2 = (Element) lista2.get(8);

                        session.setAttribute("nombreNuevo", nombre.getText());
                        session.setAttribute("instruccionNuevo", instruccion.getText());
                        session.setAttribute("audioInstruccionNuevo", audioInstruccion.getText());
                        session.setAttribute("imagenNuevo", imagen.getText());
                        session.setAttribute("audioImagenNuevo", audioImagen.getText());
                        session.setAttribute("pistaNuevo", pista.getText());
                        session.setAttribute("respuestaCorrectaNuevo", respuestaCorrecta.getText());
                        session.setAttribute("respuestaIncorrecta1Nuevo", respuestaIncorrecta1.getText());
                        session.setAttribute("respuestaIncorrecta2Nuevo", respuestaIncorrecta2.getText());
                    }

                    String nombreM = (String) session.getAttribute("nombreNuevo");
                    String instruccionM = (String) session.getAttribute("instruccionNuevo");
                    String audioInstruccionM = (String) session.getAttribute("audioInstruccionNuevo");
                    String imagenM = (String) session.getAttribute("imagenNuevo");
                    String audioImagenM = (String) session.getAttribute("audioImagenNuevo");
                    String pistaM = (String) session.getAttribute("pistaNuevo");
                    String respuestaCorrectaM = (String) session.getAttribute("respuestaCorrectaNuevo");
                    String respuestaIncorrecta1M = (String) session.getAttribute("respuestaIncorrecta1Nuevo");
                    String respuestaIncorrecta2M = (String) session.getAttribute("respuestaIncorrecta2Nuevo");

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
                    out.println("<form action='modificarAudioInstruccion' method='post'>");
                    out.println("<h2><b>Archivo audio instruccion actual: " + audioInstruccionM.substring(2) + "</b></h2>");
//                        out.println("<input type='hidden' name='nombreArchivo' value='"+audioInstruccionM+"'>");
//                        out.println("<input type='hidden' name='tipoModificar' value='1'>");
                    out.println("<input type='submit' value='Modificar Audio Instruccion' class=\"btn btn-w-m btn-default\">");
                    out.println("</form>");
                    

                    out.println("<form action='modificarImagen' method='post'>");
                    out.println("<h2><b>Archivo imagen actual: " + imagenM.substring(2) + "</b></h2>");
//                        out.println("<input type='hidden' name='nombreArchivo' value='"+imagenM+"'>");
//                        out.println("<input type='hidden' name='tipoModificar' value='2'>");
                    out.println("<input type='submit' value='Modificar Imagen' class=\"btn btn-w-m btn-default\">");
                    out.println("</form>");

                    out.println("<form action='modificarAudioImagen' method='post'>");
                    out.println("<h2><b>Archivo audio imagen actual: " + audioImagenM.substring(2) + "</b></h2>");
//                        out.println("<input type='hidden' name='nombreArchivo' value='"+audioImagenM+"'>");
//                        out.println("<input type='hidden' name='tipoModificar' value='3'>");
                    out.println("<input type='submit' value='Modificar Audio Imagen' class=\"btn btn-w-m btn-default\">");
                    out.println("</form>");

                    out.println("<form action='modifyExercise' method='get'>");
                    out.println("<h2><b>Nombre:</b></h2> <h3><input id='nombre' type='text' value='" + nombreM + "' name='nombreNuevo' required/></h3><br />");
                    out.println("<h2><b>Instruccion:</b></h2> <h3><input id='instruccion' type='text' value='" + instruccionM + "' name='instruccionNuevo' required/></h3><br />");
                    //out.println("<h2>Audio instruccion:</h2> <input id='audioInstruccion' type='text' value='"+audioInstruccionM+"' name='audioInstruccionNuevo' required/><br />");
                    //out.println("<h2>Imagen:</h2> <input id='imagen' type='text' value='"+imagenM+"' name='imagenNuevo' required/><br />");
                    //out.println("<h2>Audio imagen:</h2> <input id='audioImagen' type='text' value='"+audioImagenM+"' name='audioImagenNuevo' required/><br />");
                    out.println("<h2><b>Pista:</b></h2> <h3><input id='pista' type='text' value='" + pistaM + "' name='pistaNuevo' required/></h3><br />");
                    out.println("<h2><b>Respuesta correcta:</b></h2> <h3><input id='respuestaCorrecta' type='text' value='" + respuestaCorrectaM + "' name='respuestaCorrectaNuevo' required/></h3><br />");
                    out.println("<h2><b>Respuesta incorrecta 1:</b></h2> <h3><input id='respuestaIncorrecta1' type='text' value='" + respuestaIncorrecta1M + "' name='respuestaIncorrecta1Nuevo' required/></h3><br />");
                    out.println("<h2><b>Respuesta incorrecta 2:</b></h2> <h3><input id='respuestaIncorrecta2' type='text' value='" + respuestaIncorrecta2M + "' name='respuestaIncorrecta2Nuevo' required/></h3><br />");

                    out.println("<br />");
                    out.println("<br />");
                    out.println("<input type='submit' value='Modificar ejercicio' class=\"btn btn-sm btn-primary\">");
                    out.println("</form>");

                    out.println("<br />");
                    out.println("<br />");

                    out.println("<form action='adminEjercicios' method='get'>");
                    out.println("<input type='hidden' name='cancelMod' value='1'>");
                    out.println("<input type='submit' value='Cancelar' class=\"btn btn-sm btn-danger\">");
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

        } catch (JDOMException e) {
        }

    }

}
