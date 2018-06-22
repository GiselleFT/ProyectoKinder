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

public class adminGpo extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {  
            response.setContentType("text/html;charset=UTF-8");
            HttpSession session = request.getSession();
            String usuario = (String)session.getAttribute("usuario");
            String tipoAtt = (String)session.getAttribute("tipo");
            String idUsuario = (String) session.getAttribute("idUsuario");
            
            
            PrintWriter out = response.getWriter();
            
            //info del administrador
//            System.out.println("agregarUsuario");
//            System.out.println("admin= "+usuario);
//            System.out.println("admin= "+contrasena);
//            System.out.println("admin= "+tipoAtt);
            

            //********Valida Tipo Usuario****************//
            if(!tipoAtt.equals("2")){
               response.sendRedirect("login.html");
            }
            //*******************************************//
            
            //info del usuario a modificar
            String idGrupo = (String)request.getParameter("id");//id del grupo a modificar
            session.setAttribute("idGrupo", idGrupo);
            System.out.println("id ejerciciosGrupo= "+idGrupo);
            
            try{
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
            out.println("                        <li><a href=\"adminEjercicios\">Administrar Usuarios</a></li>");
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
            
            //Lista de nodos almacenados, lo que esta contenido entre las etiquetas de raiz
            List listaEjerciciosGrupo = raiz.getChildren("EJERCICIOS_GRUPO");
            boolean existe = false;
                //Para recorrer el arbol de nodos
                for(int i=0;i<listaEjerciciosGrupo.size();i++){//Por cada elemento 
                    //Se procesa un elemento de la listaEjerciciosGrupo
                    Element ejercicioGrupo = (Element)listaEjerciciosGrupo.get(i);
                    //encontrar el elemento con el idGrupo capturado
                    Attribute idEjGrupo = ejercicioGrupo.getAttribute("idGrupo");
                    if(idEjGrupo.getValue().matches(idGrupo)){//se ha encontrado el grupo a modificar
                        existe = true;
                        //Esta lista contiene los elementos que estan chequeados
                        List infoEjercicioGrupo = ejercicioGrupo.getChildren();//pasa los elementos a infoEjercicioGrupo
                        List listaEjercicios = raiz.getChildren("EJERCICIO");
                        for (int j = 0; j < listaEjercicios.size(); j++) {
                            //DE TODOS LOS EJERCICIOS
                            Element ejercicio = (Element)listaEjercicios.get(j);//1 ejercicio
                            List infoUnEjercicio = ejercicio.getChildren();
                            Element nombreEjercicio = (Element)infoUnEjercicio.get(0);
                            Attribute idEjercicio = ejercicio.getAttribute("id");
                            Attribute idEjProfesor = ejercicio.getAttribute("idProfesor");
                            boolean ejercicioChequeado = false;
                            //Solo los ejercicios que crea el profesor
                            if(idUsuario.matches(idEjProfesor.getValue())){
                                for (int k = 0; k < infoEjercicioGrupo.size(); k++) {
                                    Element idEx = (Element)infoEjercicioGrupo.get(k);
                                    //Si de la lista de ej. se encuentra un elemento chequeado
                                    if(idEx.getText().matches(idEjercicio.getValue())){
                                        ejercicioChequeado = true;
                                        out.println("<input type='checkbox' id='ejercicioSeleccionado' name='ejercicioSeleccionado' value='"+idEx.getText()+"' checked>"+nombreEjercicio.getText()+"<br>");
                                    }
                                }
                                if(ejercicioChequeado == false){
                                    out.println("<input type='checkbox' id='ejercicioSeleccionado' name='ejercicioSeleccionado' value='"+idEjercicio.getValue()+"'>"+nombreEjercicio.getText()+"<br>");
                                }
                                else{
                                    ejercicioChequeado = false;
                                }
                            }
                        } 
                    }
                        
                    out.println("<input type='hidden' name='tipo' value=" + tipoAtt + ">");//Del administrador
                    out.println("<br />");
                    out.println("<br />");
                    out.println("<br />");
                    out.println("<br />");
                    out.println("</h3><h3><input type='submit' class=\"btn btn-w-m btn-primary\" value='Modificar Grupo'></h3>");
                    out.println("</form>");
                        
                    }
                    
                
                
                out.println("<br />");
            out.println("<br />");
            //Agregar Usuario

            out.println("<form action='adminGrupos' method='get'>");
            out.println("<input type='hidden' name='cancelMod' value='1'>");
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
        
        }catch(JDOMException e){}
                        
}
}

