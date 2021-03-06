package alumno;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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

/**
 *
 * @author sam-y
 */
public class ejercicio1 extends HttpServlet {

    @Override
    //protected, solo los miembros de la clase los puede ocupar 
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            //objeto request obtiene informacion de la peticion del cliente
            //objeto response que encapsula pero va del servidor al cliente, al revés de request
            //throws es porque podría arrojar una excepcion, como entrada y salida de datos o ServeletException
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
        if (!tipoAtt.equals("3")) {
            response.sendRedirect("login.html");
        }
        //*******************************************//
        String id = (String) request.getParameter("id");//id del ejercicio a modificar

        String nombreM = null, instruccionM = null, audioInstruccionM = null, imagenM = null,
                audioImagenM = null, pistaM = null, respuestaCorrectaM = null, respuestaIncorrecta1M = null,
                respuestaIncorrecta2M = null;
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

            List lista_nombreUsuario = raiz.getChildren("USUARIO");
            for (int i = 0; i < lista_nombreUsuario.size(); i++) {
                Element elem1 = (Element) lista_nombreUsuario.get(i);
                List lista_nombre = elem1.getChildren();
                Attribute idElement = elem1.getAttribute("id");
                if (idElement.getValue().matches(idUsuario)) {
                    Element nombre = (Element) lista_nombre.get(0);
                    session.setAttribute("nombreAlumnoRA", nombre.getText());
                }
            }

            List lista_grupos = raiz.getChildren("GRUPO");
            Element elem;
            Attribute idElem = null;
            int bandera = 0;
            for (int i = 0; i < lista_grupos.size(); i++) {
                elem = (Element) lista_grupos.get(i);
                List lista_grupos2 = elem.getChildren();
                Attribute idElement = elem.getAttribute("id");
                if (lista_grupos2.size() > 4) {
                    for (int j = 4; j < lista_grupos2.size(); j++) {
                        Element id_alumno = (Element) lista_grupos2.get(j);
                        if (id_alumno.getText().equals(idUsuario)) { //si uno de los alumnos en un grupo soy yo (el alumno logueado)
                            session.setAttribute("idGrupoRA", idElement.getValue());
                            session.setAttribute("idAlumnoRA", idUsuario);
                            idElem = elem.getAttribute("id");
                            bandera = 1;
                            break;
                        }
                    }
                } else {
                    response.sendRedirect("menuAlumno?error=0");
                }

                if (bandera == 1) {
                    break;
                }
            }
            if (idElem != null) {
                System.out.println("Pertenece a un grupo");
            } else {
                System.out.println("No pertenece a ningun grupo");
            }
            System.out.println(idElem.getValue() + "*************************************************************");
            //----------------------------------------------------------------------------------------------------------
            List lista_ejerciciosGrupos = raiz.getChildren("EJERCICIOS_GRUPO");
            Element elemento;
            int indice = -1;
            for (int q = 0; q < lista_ejerciciosGrupos.size(); q++) {
                elemento = (Element) lista_ejerciciosGrupos.get(q);
                if (elemento.getAttribute("idGrupo").getValue().equals(idElem.getValue())) {
                    indice = q;
                    Attribute idElemento = elemento.getAttribute("idProfesor");
                    session.setAttribute("idProfesorRA", idElemento.getValue());
                }
            }
            if (indice != -1) {
                System.out.println("ENCONTRO EL EJERCICIO GRUPO QUE BUSCABAMOS");
            } else {
                System.out.println("NO EXISTE EL EJERCICIO GRUPO QUE BUSCABAMOS");
            }
            //--------------------------------------------------------------------------------------------
            
            System.out.println(indice);
            elemento = (Element) lista_ejerciciosGrupos.get(indice);//Este ya es el conjunto de ejercicios que quiero
            
            List id_ejercicios = elemento.getChildren();

            //Lista de nodos almacenados, lo que esta contenido entre las etiquetas de raiz
            List lista = raiz.getChildren("EJERCICIO");
            if (id_ejercicios.size() < 3) {
                response.sendRedirect("menuAlumno?error=0");
            }
            Integer ejer1 = (int) (Math.random() * id_ejercicios.size());

            System.out.println(ejer1 + " - Numero Random");

            session.setAttribute("random1", ejer1);
            Element element = null;
            for (int i = 0; i < lista.size(); i++) {
                element = (Element) lista.get(i);
                Attribute idElement = element.getAttribute("id");
                Element a = (Element) id_ejercicios.get(ejer1);
                if (idElement.getValue().equals(a.getValue())) {
                    session.setAttribute("idEjercicio1", idElement.getValue());
                    System.out.println("Encontré el ejercicio que buscaba");
                    break;
                }
            }

            //Se procesa un elemento de la lista
            //guarda los datos de la lista en un arreglo de elementos
            //encontrar el elemento con el id capturado
            Attribute idProfesorCreador = element.getAttribute("idProfesor");
            idProfesorCreador.getIntValue();

            //se ha encontrado ejercicio con id a modificar
            //Obtiene los elementos que contiene el elemento actual
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

            nombreM = nombre.getText();
            instruccionM = instruccion.getText();
            audioInstruccionM = audioInstruccion.getText();
            imagenM = imagen.getText();
            audioImagenM = audioImagen.getText();
            pistaM = pista.getText();
            respuestaCorrectaM = respuestaCorrecta.getText();
            respuestaIncorrecta1M = respuestaIncorrecta1.getText();
            respuestaIncorrecta2M = respuestaIncorrecta2.getText();
            System.out.println("******\n**********\n**********\n");
            System.out.println(nombreM);
            System.out.println("******\n**********\n**********\n");
        } catch (JDOMException e) {
            System.out.println("SE MUERE");

        }
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
        out.println("                    <h2>Bienvenido alumno: <b>" + usuario + "</b></h2>");
        out.println("                </div>");
        out.println("            </div>");

        out.println("<br />");
        out.println("<br />");
        out.println("                <div align='center'>");
        out.println("            <div class=\"wrapper  wrapper-content animated fadeInRight\">");

        out.println("                    <div class=\"row\">");
        out.println("                <div class=\"col-lg-12\">");

        //tabla
        out.println("                    <div class=\"row\">");
        out.println("                <div class=\"col-lg-12\">");
        out.println("                    <div class=\"ibox float-e-margins\">");
        out.println("                        <div id='audio' class=\"ibox-title\">");

        //out.println("<h1>" + nombreM + "</h1>");//contenido que se va a desplegar dentro de la pagina web
        out.println("<h1><b>" + instruccionM + "</b></h1>");
        out.println("<audio  src='archivos/" + audioInstruccionM + "' controls ></audio>");
        out.println("<br/>");
        out.println("<br/>");
        out.println("<br/>");
        if (imagenM.endsWith(".jpg")) {
            out.println("<image onclick='reproducir()' src='archivos/" + imagenM + "' width='400' height='270'/>");
            out.println("<br/>");
            out.println("<center>");
            out.println("<h3>Haz click en la imagen para escuchar!</h3>");
            out.println("</center>");
            //out.println("<audio src='archivos/" + audioImagenM + "'></audio><a>   </a>");
        } else {
            out.println("<video controls><source src='archivos/" + imagenM + "' type=\"video/mp4\"></video>");
        }
        out.println("<br/>");
        out.println("<br/>");
        out.println("<button onclick='myFunction()' class=\"btn btn-w-m btn-info\"><h4>Pista!</h4></button>");
        out.println("<br/>");
        out.println("<br/>");
        out.println("<b><h2 id='demo'></h2></b>");
        out.println("<br/>");
        out.println("<br/>");

        int numero = (int) (Math.random() * 3);
        ArrayList<String> respuestas = new ArrayList<String>();
        respuestas.add(respuestaCorrectaM);
        respuestas.add(respuestaIncorrecta1M);
        respuestas.add(respuestaIncorrecta2M);

        out.println("<button onclick='myFunction2()' id='r1' class=\"btn btn-w-m btn-primary\"><h2 >" + respuestas.get(numero) + "</h2></button>");
        out.println("<button onclick='myFunction3()' id='r2' class=\"btn btn-w-m btn-primary\"><h2 >" + respuestas.get((numero + 1) % 3) + "</h2></button>");
        out.println("<button onclick='myFunction4()' id='r3' class=\"btn btn-w-m btn-primary\"><h2 >" + respuestas.get((numero + 2) % 3) + "</h2></button>");
        out.println("<br/>");
        out.println("<br/>");
        out.println("<b><font color=\"green\"><h2 id='ganar'></h2></font></b>");
        out.println("<b><font color=\"red\"><h3 id='perder'></h3></font></b>");
        out.println("<script>\n"
                + "var countClicks = 0;"
                + "function myFunction2() {\n"
                + "  if(" + respuestas.get(numero).equals(respuestaCorrectaM) + "){"
                + "var x = document.getElementById(\"r1\");"
                + "x.setAttribute(\"class\",'btn btn-w-m btn-success');"
                + "  document.getElementById(\"ganar\").innerHTML = 'RESPUESTA CORRECTA!!';\n"
                + "  document.getElementById(\"perder\").innerHTML = '';\n"
                + "  document.getElementById(\"r2\").disabled = true;\n"
                + "  document.getElementById(\"r3\").disabled = true;\n"
                + "var m = document.getElementById('contador');\n"
                + "countClicks++;"
                + "m.setAttribute('value',countClicks);"
                + "  document.getElementById(\"siguiente\").disabled = false;\n"
                + "}\n"
                + "else{"
                + "countClicks++;"
                + "var x = document.getElementById(\"r1\");"
                + "x.setAttribute(\"class\",'btn btn-w-m btn-danger');"
                + "  document.getElementById(\"perder\").innerHTML = 'Respuesta incorrecta, intenta de nuevo';\n"
                + "}"
                + "}\n"
                + "</script>");
        out.println("<script>\n"
                + "function myFunction3() {\n"
                + "  if(" + respuestas.get((numero + 1) % 3).equals(respuestaCorrectaM) + "){"
                + "var x = document.getElementById(\"r2\");"
                + "x.setAttribute(\"class\",'btn btn-w-m btn-success');"
                + "  document.getElementById(\"ganar\").innerHTML = 'RESPUESTA CORRECTA!!';\n"
                + "  document.getElementById(\"perder\").innerHTML = '';\n"
                + "  document.getElementById(\"r1\").disabled = true;\n"
                + "  document.getElementById(\"r3\").disabled = true;\n"
                + "var m = document.getElementById('contador');\n"
                + "countClicks++;"
                + "m.setAttribute('value',countClicks);"
                + "  document.getElementById(\"siguiente\").disabled = false;\n"
                + "}\n"
                + "else{"
                + "countClicks++;"
                + "var x = document.getElementById(\"r2\");"
                + "x.setAttribute(\"class\",'btn btn-w-m btn-danger');"
                + "  document.getElementById(\"perder\").innerHTML = 'Respuesta incorrecta, intenta de nuevo';\n"
                + "}"
                + "}\n"
                + "</script>");
        out.println("<script>\n"
                + "function myFunction4() {\n"
                + "  if(" + respuestas.get((numero + 2) % 3).equals(respuestaCorrectaM) + "){"
                + "var x = document.getElementById(\"r3\");"
                + "x.setAttribute(\"class\",'btn btn-w-m btn-success');"
                + "  document.getElementById(\"ganar\").innerHTML = 'RESPUESTA CORRECTA!!';\n"
                + "  document.getElementById(\"perder\").innerHTML = '';\n"
                + "  document.getElementById(\"r1\").disabled = true;\n"
                + "  document.getElementById(\"r2\").disabled = true;\n"
                + "var m = document.getElementById('contador');\n"
                + "countClicks++;"
                + "m.setAttribute('value',countClicks);"
                + "  document.getElementById(\"siguiente\").disabled = false;\n"
                + "}\n"
                + "else{"
                + "countClicks++;"
                + "var x = document.getElementById(\"r3\");"
                + "x.setAttribute(\"class\",'btn btn-w-m btn-danger');"
                + "  document.getElementById(\"perder\").innerHTML = 'Respuesta incorrecta, intenta de nuevo';\n"
                + "}"
                + "}\n"
                + "</script>");

        out.println("<script>\n"
                + "function myFunction() {\n"
                + "  document.getElementById(\"demo\").innerHTML = '" + pistaM + "';\n"
                + "}\n"
                + "</script>");
        out.println("<script>\n"
                + "function reproducir() {\n"
                + "var x = document.createElement(\"audio\");\n"
                + "x.setAttribute(\"src\",'archivos/" + audioImagenM + "');\n"
                + "document.getElementById(\"audio\").appendChild(x);"
                + "x.autoplay = true;\n"
                + "}\n"
                + "</script>");

        out.println("                </div>");
        out.println("                </div>");
        out.println("                        </div>");
        out.println("                    </div>");
        out.println("                </div>");
        out.println("                </div>");
        out.println("<form action='ejercicio2' method='get'>");
        out.println("<input type=\"hidden\" id=\"contador\" name=\"clics\">");
        out.println("<input type='submit' id='siguiente' value='Siguiente' class=\"btn btn-sm btn-default\"  disabled>");
        out.println("</form>");
        out.println("                        </div>");

        out.println("                    </div>");
        out.println("                </div>");

        out.println("            </div>");

        out.println("                </div>");
        out.println("            </div>");
        out.println("        </div>");
        out.println("        </div>");
        out.println("</body>");
        out.println("</html>");//nodo raiz, aqui termina
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
