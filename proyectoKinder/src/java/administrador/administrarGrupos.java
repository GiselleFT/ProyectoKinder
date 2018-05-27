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
            String usuario = (String)session.getAttribute("usuario");
            String contrasena = (String)session.getAttribute("contrasena");
            String tipoAtt = (String)session.getAttribute("tipo");
            PrintWriter out = response.getWriter();
            
            //info del administrador
//            System.out.println("administrarUsuario");
//            System.out.println("admin= "+usuario);
//            System.out.println("admin= "+contrasena);
//            System.out.println("admin= "+tipoAtt);

            //********Valida Tipo Usuario****************//
            if(!tipoAtt.equals("1")){
               response.sendRedirect("login.html");
            }
            //*******************************************//
            
            try{
                //Contruye un documento JDOM usando SAX, para procesar xml
                SAXBuilder builder = new SAXBuilder();
                //Para obtener la ruta absoluta del proyecto
//                String rutaAbsoluta = request.getSession().getServletContext().getRealPath("/");
//                rutaAbsoluta = rutaAbsoluta.replaceAll("'\'", "'\\'");
//                System.out.println("RUTA ABSOLUTA= "+rutaAbsoluta);
                //Ruta absoluta del archivo BD.xml
//                File BD = new File("C:\\Users\\Giselle\\Documents\\GitHub\\ProyectoKinder\\proyectoKinder\\web\\BD.xml");
//                System.out.println("administrarUsuario ruta:" + rutaAbsoluta);
//                File BD = new File(rutaAbsoluta+"\\BD.xml");
//                File BD = new File("xml/BD.xml");
                //Para cargar el documento xml
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
                List lista = raiz.getChildren("GRUPO");
                
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Administrar Grupos</title>");
                out.println("<link rel='stylesheet' href='css/estilos.css'>");
                out.println("</head>");
                out.println("<body>");
                
                //out.println("<div class='contenido'>");
                out.println("<h1>Administrar Grupos</h1>");
                out.println("<br />");
                //Agregar Usuario
                out.println("<form action='agregarGrupo' method='post'>");
                out.println("<input type='submit' value='Crear Grupo'>");
                out.println("</form>");
                
                
                //out.println("</div>");
                
                out.println("<br />");
                out.println("<br />");
                
                //Mostrar tabla de usuarios registrados
                out.println("<table border='3'>");
                    //Columnas
                    out.println("<tr>");
                    out.println("<th>ID Grupo</th>");
                    out.println("<th>Grupo</th>");
                    out.println("<th>ID Profesor</th>");
                    out.println("<th>Nombre Profesor</th>");
                    out.println("<th>Inscritos</th>"); 
                    out.println("<th>Modificar</th>"); 
                    out.println("<th>Eliminar</th>"); 
                    out.println("</tr>");

                String type = "";
                
                //Para recorrer el arbol de nodos
                for(int i=0;i<lista.size();i++){//Por cada elemento  
                    //Se procesa un elemento de la lista
                    Element element = (Element)lista.get(i);//guarda los datos de la lista en un arreglo de elementos
                    //Obtiene los elementos que contiene el elemento actual
                    List lista2 = element.getChildren();//pasa los elementos a lista2
                    //Se recupera idGrupo de grupo
                    Attribute idGrupo = element.getAttribute("id");
                    //Se recupera grupo
                    Element grupo = (Element)lista2.get(0);
                    //Se recupera usuario 
                    Element idProfesor = (Element)lista2.get(1); 
                    //Se recupera usuario 
                    Element nombreProfesor = (Element)lista2.get(2);
                    //Se recupera usuario 
                    Element inscritos = (Element)lista2.get(3);
                    
                    
                    //Se crea una fila para desplegar info de usuario
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
                        out.println("<input type='hidden' name='idGrupo' value="+idGrupo.getValue()+">");//Del administrador
                        out.println("<input type='submit' value='Modificar'>");
                        out.println("</form>");
                        //out.println("<button type='button' onclick='modificarUsuario()'>Modificar</button>");
                        out.println("</td>");
                        out.println("<td>");
                        //Por medio del id, se localiza al usuario por eliminar
                        out.println("<form action='eliminarGrupo' method='post'>");
                        out.println("<input type='hidden' name='idGrupo' value="+idGrupo.getValue()+">");//Del administrador
                        out.println("<input type='submit' value='Eliminar'>");
                        out.println("</form>");
                        out.println("</td>");
                    out.println("</tr>"); 
                }
                out.println("</table>");
                
                out.println("<br />");
                out.println("<br />");
                //Agregar Usuario
                out.println("<form action='menuAdministrador' method='get'>");
                out.println("<input type='submit' value='Menu Administrador'>");
                out.println("</form>");
                
                out.println("</body>");
                out.println("</html>");
        }catch(JDOMException e){}
    }

}