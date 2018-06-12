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

public class administrarGrupos extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        //Recuperamos la sesion
        HttpSession session = request.getSession();
        String usuario = (String) session.getAttribute("usuario");
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
            List lista = raiz.getChildren("GRUPO");

//************************************************************************************************
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

            out.println("        </div>");
            out.println("");
            out.println("        </nav>");
            out.println("        </div>");
            out.println("            <div class=\"row wrapper border-bottom white-bg page-heading\">");
            out.println("                <div class=\"col-sm-4\">");
            out.println("                    <h2>Bienvenido administrador: " + usuario + "</h2>");
            out.println("                </div>");
            out.println("            </div>");

            out.println("<br />");
            out.println("<br />");
            if (request.getParameter("eliminado")!=null) {
                out.println("<div class=\"alert alert-danger\" role=\"alert\"><H4>REGISTRO ELIMINADO</H4></div>");
                
            }
            if (request.getParameter("cancelAddGrupo")!=null) {
                out.println("<div class=\"alert alert-danger\" role=\"alert\"><H4>OPERACIÓN CANCELADA</H4></div>");
                
            }
            if (request.getParameter("addGrupo")!=null) {
                out.println("<div class=\"alert alert-success\" role=\"alert\"><H4>REGISTRO EXITOSO!</H4></div>");
                
            }
            if (request.getParameter("cancelModGrupo")!=null) {
                out.println("<div class=\"alert alert-danger\" role=\"alert\"><H4>MODIFICACIÓN CANCELADA</H4></div>");
                
            }
            if (request.getParameter("modGrupo")!=null) {
                out.println("<div class=\"alert alert-success\" role=\"alert\"><H4>MODIFICACIÓN EXITOSA!</H4></div>");
                
            }
            out.println("<form action='agregarGrupo' method='post'>");
            out.println("<input type='submit' value='Crear Grupo' align='center' class=\"btn btn-w-m btn-success\">");
            out.println("</form>");

            out.println("<br />");
            out.println("<br />");
            out.println("            <div class=\"wrapper  wrapper-content animated fadeInRight\">");

            out.println("                    <div class=\"row\">");
            out.println("                <div class=\"col-lg-12\">");
            out.println("                    <div class=\"ibox float-e-margins\">");
            out.println("                        <div class=\"ibox-title\">");
            out.println("                            <h5>Grupos </h5>");
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
            out.println("<div class=\"input-group\"><input type=\"text\" id='names' onkeyup='loadDoc(this.value)' placeholder=\"Buscar...\" class=\"input-sm form-control\"> </div>");
            out.println("                                <br />");
            out.println("                                </div>");
            out.println("                            </div>");
            out.println("                            <div class=\"table-responsive\">");
            out.println("                                <table id='miTabla' class=\"footable table table-stripped\" data-page-size=\"32\" data-filter=#filter>");
            //out.println("                                    <thead>");//ESTO
            out.println("                                    <tr>");
            out.println("<th>ID Grupo</th>");
            out.println("<th>Grupo</th>");
            out.println("<th>ID Profesor</th>");
            out.println("<th>Nombre Profesor</th>");
            out.println("<th>Inscritos</th>");
            out.println("<th>Modificar</th>");
            out.println("<th>Eliminar</th>");
            out.println("                                    </tr>");
            //out.println("                                    </thead>");//ESTO
            String type = "";

            //Para recorrer el arbol de nodos
            for (int i = 0; i < lista.size(); i++) {//Por cada elemento  
                //Se procesa un elemento de la lista
                Element element = (Element) lista.get(i);//guarda los datos de la lista en un arreglo de elementos
                //Obtiene los elementos que contiene el elemento actual
                List lista2 = element.getChildren();//pasa los elementos a lista2
                //Se recupera idGrupo de grupo
                Attribute idGrupo = element.getAttribute("id");
                //Se recupera grupo
                Element grupo = (Element) lista2.get(0);
                //Se recupera usuario 
                Element idProfesor = (Element) lista2.get(1);
                //Se recupera usuario 
                Element nombreProfesor = (Element) lista2.get(2);
                //Se recupera usuario 
                Element inscritos = (Element) lista2.get(3);
                out.println("<tr>");
                out.println("<td>");
                out.println(idGrupo.getValue());
                out.println("</td>");
                out.println("<td>");
                out.println(grupo.getValue());
                out.println("</td>");
                out.println("<td>");
                out.println(idProfesor.getValue());
                out.println("</td>");
                out.println("<td>");
                out.println(nombreProfesor.getValue());
                out.println("</td>");
                out.println("<td>");
                out.println(inscritos.getValue());
                out.println("</td>");
                out.println("<td>");
                //Por medio del id, se localiza al usuario por modificar
                out.println("<form action='modificarGrupo' method='post'>");
                out.println("<input type='hidden' name='idGrupo' value=" + idGrupo.getValue() + ">");//Del administrador
                out.println("<input type='submit' value='Modificar' class=\"btn btn-w-m btn-default\">");
                out.println("</form>");
                //out.println("<button type='button' onclick='modificarUsuario()'>Modificar</button>");
                out.println("</td>");
                out.println("<td>");
                //Por medio del id, se localiza al usuario por eliminar
                out.println("<form action='eliminarGrupo' method='post'>");
                out.println("<input type='hidden' name='idGrupo' value=" + idGrupo.getValue() + ">");//Del administrador
                out.println("<input type='submit' value='Eliminar' class=\"btn btn-w-m btn-danger\">");
                out.println("</form>");
                out.println("</td>");
                out.println("</tr>");
                //out.println("                                    </tbody>"); //ESTO
            }
            out.println("                                </table>");

            out.println("<br />");
            out.println("<br />");

            //Tabla dinamica conforme a busqueda
            out.println("                            </div>");
            out.println("<form action='menuAdministrador' method='get'>");
            out.println("<input type='submit' value='Menu Administrador' class=\"btn btn-sm btn-warning\">");
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
                    + "        var table = \"<tr><th>ID Grupo</th><th>Grupo</th><th>ID Profesor</th><th>Nombre Profesor</th><th>Inscritos</th><th>Modificar</th><th>Eliminar</th></tr>\";\n"
                    + "                var x = xmlDoc.getElementsByTagName(\"GRUPO\");\n"
                    + "                    for (i = 0; i < x.length; i++) {\n"
                    + "                        //Contiene el nombre del profesor\n"
                    + "                        var elemento = x[i].getElementsByTagName('nombreProfesor')[0].childNodes[0].nodeValue;\n"
                    + "                        //Extraer del nombre del profesor la cadena del tamaño del prefijo a comparar\n"
                    + "                        var aux = elemento.substring(0,nombreProfesor.length);\n"
                    + "                        //Si el prefijo coincide con el prefijo del profesor lo muestra en la tabla\n"
                    + "                        if(nombreProfesor === aux){\n"
                    + "                            table += \"<tr><td>\" +\n"
                    + "                            x[i].getAttribute('id') +\n"
                    + "                            \"</td><td>\"+\n"
                    + "                            x[i].getElementsByTagName('grupo')[0].childNodes[0].nodeValue +\n"
                    + "                            \"</td><td>\"+\n"
                    + "                            x[i].getElementsByTagName('idProfesor')[0].childNodes[0].nodeValue + \n"
                    + "                            \"</td><td>\"+\n"
                    + "                            x[i].getElementsByTagName('nombreProfesor')[0].childNodes[0].nodeValue +\n"
                    + "                            \"</td><td>\"+\n"
                    + "                            x[i].getElementsByTagName('inscritos')[0].childNodes[0].nodeValue +\n"
                    + "                            \"</td><td>\"+\n"
                    + "                            \"<form action='modificarGrupo' method='post'>\"+\n"
                    + "                            \"<input type='hidden' name='idGrupo' value='\"+x[i].getAttribute('id')+\"'>\"+\n"
                    + "                            \"<input type='submit' value='Modificar'>\"+\n"
                    + "                            \"</form>\"+\n"
                    + "                            \"</td><td>\"+\n"
                    + "                            \"<form action='eliminarGrupo' method='post'>\"+\n"
                    + "                            \"<input type='hidden' name='idGrupo' value='\"+x[i].getAttribute('id')+\"'>\"+\n"
                    + "                            \"<input type='submit' value='Eliminar'>\"+\n"
                    + "                            \"</form>\"+\n"
                    + "                            \"</td></tr>\";\n"
                    + "                        }\n"
                    + "                }\n"
                    + "                document.getElementById(\"miTabla\").innerHTML = table;\n"
                    + "            }        ");

            out.println("</script>");
            out.println("</body>");
            out.println("</html>");
            out.println("");
        } catch (JDOMException e) {
        }
    }

}

//package administrador;
//
//import java.io.File;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.List;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import org.jdom.Attribute;
//import org.jdom.Document;
//import org.jdom.Element;
//import org.jdom.JDOMException;
//import org.jdom.input.SAXBuilder;
//
//public class administrarGrupos extends HttpServlet {
//    
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//         
//            response.setContentType("text/html;charset=UTF-8");
//            //Recuperamos la sesion
//            HttpSession session = request.getSession();
//            String usuario = (String)session.getAttribute("usuario");
//            String contrasena = (String)session.getAttribute("contrasena");
//            String tipoAtt = (String)session.getAttribute("tipo");
//            PrintWriter out = response.getWriter();
//            
//            //info del administrador
////            System.out.println("administrarUsuario");
////            System.out.println("admin= "+usuario);
////            System.out.println("admin= "+contrasena);
////            System.out.println("admin= "+tipoAtt);
//
//            //********Valida Tipo Usuario****************//
//            if(!tipoAtt.equals("1")){
//               response.sendRedirect("login.html");
//            }
//            //*******************************************//
//            
//            try{
//                //Contruye un documento JDOM usando SAX, para procesar xml
//                SAXBuilder builder = new SAXBuilder();
//                //Para obtener la ruta absoluta del proyecto
//                String rutaAbsoluta = request.getSession().getServletContext().getRealPath("/");
//                rutaAbsoluta = rutaAbsoluta.replace("\\", "/");
//                rutaAbsoluta = rutaAbsoluta.replaceAll("/build", "");
//                rutaAbsoluta = rutaAbsoluta.concat("BD.xml");
//                File BD = new File(rutaAbsoluta);
//                
//                Document doc = builder.build(BD);//documentos para contruir base de datos
//                //Se obtiene el elemento raiz del xml
//                Element raiz = doc.getRootElement();
//                //Lista de nodos almacenados, lo que esta contenido entre las etiquetas de raiz
//                List lista = raiz.getChildren("GRUPO");
//                
//                out.println("<!DOCTYPE html>");
//                out.println("<html>");
//                out.println("<head>");
//                out.println("<title>Administrar Grupos</title>");
//                out.println("<link rel='stylesheet' href='css/estilos.css'>");
//                
//                out.println("<style type=\"text/css\">\n"
//                        + "            .mouseOut {\n"
//                        + "                background: #708090;\n"
//                        + "                color: #FFFAFA;\n"
//                        + "            }\n"
//                        + "            .mouseOver {\n"
//                        + "                background: #FFFAFA;\n"
//                        + "                color: #000000;\n"
//                        + "            }\n"
//                        + "        </style>");
//                out.println("<script type='text/javascript'>\n" +
//                        "    \n"
//                        + "    var xmlHttp;\n"
//                        + "    var completeDiv;\n"
//                        + "    var inputField;\n"
//                        + "    var nameTable;\n"
//                        + "    var nameTableBody;\n"
//                        + "\n"
//                        + "            \n"
//                        + "    function createXMLHttpRequest() {\n"
//                        + "        //Se verifica si el navegador es de Microsoft\n"
//                        + "        if (window.ActiveXObject) {\n"
//                        + "            xmlHttp = new ActiveXObject('Microsoft.XMLHTTP');\n"
//                        + "        } else if (window.XMLHttpRequest) {\n"
//                        + "            xmlHttp = new XMLHttpRequest();\n"
//                        + "        }\n"
//                        + "    }\n"
//                        + "\n"
//                        + "    //Inicializamos las variables de html, al final del código se ve el uso de las variables\n"
//                        + "    function initVars() {\n"
//                        + "        //Accesamos a las variables por su ID\n"
//                        + "        inputField = document.getElementById('names');\n"
//                        + "        nameTable = document.getElementById('name_table');\n"
//                        + "        completeDiv = document.getElementById('popup');\n"
//                        + "        nameTableBody = document.getElementById('name_table_body');\n"
//                        + "    }\n"
//                        + "\n"
//                        + "    \n"
//                        + "    function findNames() {	//Primer método en ser llamado\n"
//                        + "        //Inicializamos las variables\n"
//                        + "        initVars();\n"
//                        + "        //Leemos el valor del text field, si es mayor que cero\n"
//                        + "        //Es decir el usuario tecleo algo, puedes hacer algo sino\n"
//                        + "        if (inputField.value.length > 0) {\n"
//                        + "            \n"
//                        + "            //Si tecleo algo, invocamos al método\n"
//                        + "            createXMLHttpRequest();\n"
//                        + "            //Le concatenamos lo que haya tecleado el usuario en la caja de texto\n"
//                        + "            //La función escape convierte los caracteres en valor de porcentajes \"Solo algunos caracteres\"\n"
//                        + "            var url = \"http://localhost:8083/proyectoKinder/administrarGrupos?names=\" + escape(inputField.value);\n"
//                        + "            //Realizamos comunicación asíncrona, esto se denota con el argumento número tres\n"
//                        + "            xmlHttp.open('GET', url, true);\n"
//                        + "            //Establecemos la función de retrollamada cuando se detecte un cambio de estado\n"
//                        + "            xmlHttp.onreadystatechange = callback;\n"
//                        + "            //Se manda al método send\n"
//                        + "            xmlHttp.send(null);\n"
//                        + "        } else {\n"
//                        + "            //Limpia los nombres\n"
//                        + "            clearNames();\n"
//                        + "        }\n"
//                        + "    }\n"
//                        + "\n"
//                        + "    function callback() {\n"
//                        + "        //Verificamos que obtenga algo\n"
//                        + "        if (xmlHttp.readyState == 4) {\n"
//                        + "           \n"
//                        + "           //Verificamos que el ódigo sea 200\n"
//                        + "            if (xmlHttp.status == 200) {\n"
//                        + "                //Nos comunicamos con el servidor, usando el responseXML,\n"
//                        + "                setNames(xmlHttp.responseXML.getElementsByTagName('name'));\n"
//                        + "            } else if (xmlHttp.status == 204) {\n"
//                        + "                clearNames();\n"
//                        + "            }\n"
//                        + "        }\n"
//                        + "    }\n"
//                        + "\n"
//                        + "    function setNames(the_names) {\n"
//                        + "        clearNames();\n"
//                        + "        var size = the_names.length;\n"
//                        + "        //Calcula el tamaño de columnas\n"
//                        + "        setOffsets();   \n"
//                        + "        \n"
//                        + "        var row, cell, txtNode;\n"
//                        + "        //Aquí obtenemos todos los elementos del XML\n"
//                        + "        for (var i = 0; i < size; i++) {\n"
//                        + "            \n"
//                        + "            var nextNode = the_names[i].firstChild.data;\n"
//                        + "            //Creamos de forma dinámica un nodo\n"
//                        + "            row = document.createElement('tr');\n"
//                        + "            cell = document.createElement('td');\n"
//                        + "            //Accedemos a la clase className usando la palabra reservada this estamos accediendo a cell\n"
//                        + "            cell.onmouseout = function () {\n"
//                        + "                this.className = 'mouseOver';\n"
//                        + "            };\n"
//                        + "            cell.onmouseover = function () {\n"
//                        + "                this.className = 'mouseOut';\n"
//                        + "            };\n"
//                        + "            //Se añaden propiedades a diferencia de los servlets que eran para recordar\n"
//                        + "            cell.setAttribute('bgcolor', '#FFFAFA');\n"
//                        + "            cell.setAttribute('border', '0');\n"
//                        + "            cell.onclick = function () {\n"
//                        + "                populateName(this);\n"
//                        + "            };\n"
//                        + "            //Obtenemos el texto de ese nodo\n"
//                        + "            //document es un \"objeto\" que representa la estructura de nuestro HTML\n"
//                        + "            txtNode = document.createTextNode(nextNode);\n"
//                        + "            //Agregamos un hijo\n"
//                        + "            cell.appendChild(txtNode);\n"
//                        + "            //Lo añadimos a la fila\n"
//                        + "            row.appendChild(cell);\n"
//                        + "            //Lo añadimos a al tabla\n"
//                        + "            nameTableBody.appendChild(row);\n"
//                        + "        }\n"
//                        + "    }\n"
//                        + "\n"
//                        + "\n"
//                        + "    function setOffsets() {\n"
//                        + "        var end = inputField.offsetWidth;\n"
//                        + "        var left = calculateOffsetLeft(inputField);\n"
//                        + "        var top = calculateOffsetTop(inputField) + inputField.offsetHeight;\n"
//                        + "        completeDiv.style.border = 'black 1px solid';\n"
//                        + "        completeDiv.style.left = left + 'px';\n"
//                        + "        completeDiv.style.top = top + 'px';\n"
//                        + "        nameTable.style.width = end + 'px';\n"
//                        + "    }\n"
//                        + "\n"
//                        + "    function calculateOffsetLeft(field) {\n"
//                        + "        return calculateOffset(field, 'offsetLeft');\n"
//                        + "    }\n"
//                        + "\n"
//                        + "    function calculateOffsetTop(field) {\n"
//                        + "        return calculateOffset(field, 'offsetTop');\n"
//                        + "    }\n"
//                        + "\n"
//                        + "    function calculateOffset(field, attr) {\n"
//                        + "        var offset = 0;\n"
//                        + "        while (field) {\n"
//                        + "            offset += field[attr];\n"
//                        + "            field = field.offsetParent;\n"
//                        + "        }\n"
//                        + "        return offset;\n"
//                        + "    }\n"
//                        + "\n"
//                        + "    function populateName(cell) {\n"
//                        + "        inputField.value = cell.firstChild.nodeValue;\n"
//                        + "        clearNames();\n"
//                        + "    }\n"
//                        + "\n"
//                        + "    function clearNames() {\n"
//                        + "        var ind = nameTableBody.childNodes.length;\n"
//                        + "        for (var i = ind - 1; i >= 0; i--) {\n"
//                        + "            nameTableBody.removeChild(nameTableBody.childNodes[i]);\n"
//                        + "        }\n"
//                        + "\n"
//                        + "        completeDiv.style.border = 'none';\n"
//                        + "    }\n"
//                        + "</script>");
//                out.println("</head>");
//                out.println("<body>");
//                
//                //out.println("<div class='contenido'>");
//                out.println("<h1>Administrar Grupos</h1>");
//                out.println("<br />");
//                //Agregar Usuario
//                out.println("<form action='agregarGrupo' method='post'>");
//                out.println("<input type='submit' value='Crear Grupo'>");
//                out.println("</form>");
//                
//                out.println("<br />");
//                out.println("<br />");
//                
//                
//                
//                
//                //Buscar
//                out.println("Buscar: <input type='text' size='20' id='names'\n"
//                        +      "onkeyup='findNames();' style='height:20;' maxlength='20'/>");
//                out.println("<div style='position:absolute;' id='popup'>\n"
//                        + "		<table id='name_table' bgcolor='#FFFAFA' border='0'\n"
//                        + "			cellspacing='0' cellpadding='0'/>\n"
//                        + "			<tbody id='name_table_body'></tbody>\n"
//                        + "		</table>\n"
//                        + "	</div>");
//                
//                
//                //out.println("</div>");
//                
//                out.println("<br />");
//                out.println("<br />");
//                
//                //Mostrar tabla de usuarios registrados
//                out.println("<table border='3'>");
//                    //Columnas
//                    out.println("<tr>");
//                    out.println("<th>ID Grupo</th>");
//                    out.println("<th>Grupo</th>");
//                    out.println("<th>ID Profesor</th>");
//                    out.println("<th>Nombre Profesor</th>");
//                    out.println("<th>Inscritos</th>"); 
//                    out.println("<th>Modificar</th>"); 
//                    out.println("<th>Eliminar</th>"); 
//                    out.println("</tr>");
//
//                String type = "";
//                
//                //Para recorrer el arbol de nodos
//                for(int i=0;i<lista.size();i++){//Por cada elemento  
//                    //Se procesa un elemento de la lista
//                    Element element = (Element)lista.get(i);//guarda los datos de la lista en un arreglo de elementos
//                    //Obtiene los elementos que contiene el elemento actual
//                    List lista2 = element.getChildren();//pasa los elementos a lista2
//                    //Se recupera idGrupo de grupo
//                    Attribute idGrupo = element.getAttribute("id");
//                    //Se recupera grupo
//                    Element grupo = (Element)lista2.get(0);
//                    //Se recupera usuario 
//                    Element idProfesor = (Element)lista2.get(1); 
//                    //Se recupera usuario 
//                    Element nombreProfesor = (Element)lista2.get(2);
//                    //Se recupera usuario 
//                    Element inscritos = (Element)lista2.get(3);
//                    
//                    
//                    //Se crea una fila para desplegar info de usuario
//                    out.println("<tr>");
//                        out.println("<td>");
//                        out.println(idGrupo.getValue());
//                        out.println("</td>");
//                        out.println("<td>");
//                        out.println(grupo.getValue());
//                        out.println("</td>");
//                        out.println("<td>");
//                        out.println(idProfesor.getValue());
//                        out.println("</td>");
//                        out.println("<td>");
//                        out.println(nombreProfesor.getValue());
//                        out.println("</td>");
//                        out.println("<td>");
//                        out.println(inscritos.getValue());
//                        out.println("</td>");
//                        out.println("<td>");
//                        //Por medio del id, se localiza al usuario por modificar
//                        out.println("<form action='modificarGrupo' method='post'>");
//                        out.println("<input type='hidden' name='idGrupo' value="+idGrupo.getValue()+">");//Del administrador
//                        out.println("<input type='submit' value='Modificar'>");
//                        out.println("</form>");
//                        //out.println("<button type='button' onclick='modificarUsuario()'>Modificar</button>");
//                        out.println("</td>");
//                        out.println("<td>");
//                        //Por medio del id, se localiza al usuario por eliminar
//                        out.println("<form action='eliminarGrupo' method='post'>");
//                        out.println("<input type='hidden' name='idGrupo' value="+idGrupo.getValue()+">");//Del administrador
//                        out.println("<input type='submit' value='Eliminar'>");
//                        out.println("</form>");
//                        out.println("</td>");
//                    out.println("</tr>"); 
//                }
//                out.println("</table>");
//                
//                out.println("<br />");
//                out.println("<br />");
//                //Agregar Usuario
//                out.println("<form action='menuAdministrador' method='get'>");
//                out.println("<input type='submit' value='Menu Administrador'>");
//                out.println("</form>");
//                
//                out.println("</body>");
//                out.println("</html>");
//        }catch(JDOMException e){}
//    }
//
//}
