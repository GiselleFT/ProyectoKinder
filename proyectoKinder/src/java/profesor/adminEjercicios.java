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

public class adminEjercicios extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         
            response.setContentType("text/html;charset=UTF-8");
            //Recuperamos la sesion
            HttpSession session = request.getSession();
            String usuario = (String)session.getAttribute("usuario");
            String contrasena = (String)session.getAttribute("contrasena");
            String tipoAtt = (String)session.getAttribute("tipo");
            String idUsuario = (String)session.getAttribute("idUsuario");
            System.out.println("ID DE USUARIO! " +idUsuario);
            PrintWriter out = response.getWriter();
            
            //info del profesor
//            System.out.println("menuProfesor");
//            System.out.println("prof= "+usuario);
//            System.out.println("prof= "+contrasena);
//            System.out.println("prof= "+tipoAtt);
            
            //********Valida Tipo Usuario****************//
            if(!tipoAtt.equals("2")){
               response.sendRedirect("login.html");
            }
            //*******************************************//
            
            try{
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
                List lista = raiz.getChildren("EJERCICIO");
                
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Administrar Ejercicios</title>");
                out.println("<link rel='stylesheet' href='css/estilos.css'>");
                out.println("</head>");
                out.println("<body>");
                
                out.println("<h1>Administrar Ejercicios</h1>");
                out.println("<br />");
                //Agregar Usuario
                out.println("<form action='agregarEjercicio' method='post'>");
                out.println("<input type='submit' value='Crear Ejercicio'>");
                out.println("</form>");
                
                out.println("<br />");
                out.println("<br />");
                
                //Mostrar tabla de usuarios registrados
                out.println("<table border='3'>");
                //Columnas
                out.println("<tr>");
                out.println("<th>ID</th>");
                out.println("<th>Nombre</th>");
                out.println("<th>Ver</th>");
                out.println("<th>Modificar</th>"); 
                out.println("<th>Eliminar</th>"); 
                out.println("</tr>");
                //Para recorrer el arbol de nodos
                for(int i=0;i<lista.size();i++){//Por cada elemento  
                    //Se procesa un elemento de la lista
                    Element element = (Element)lista.get(i);//guarda los datos de la lista en un arreglo de elementos
                    //Obtiene los elementos que contiene el elemento actual
                    List lista2 = element.getChildren();//pasa los elementos a lista2
                    //Se recupera nombre
                    Element nombre = (Element)lista2.get(0);
                    //Se recupera ID de ejercicio
                    Attribute id = element.getAttribute("id");
                    //Se recupera ID de profesor creador
                    Attribute idProfesor = element.getAttribute("idProfesor");
                    //Solo se pueden mostrar los ejercicios que el profesor es autor
                    if(idUsuario.matches(idProfesor.getValue())){
                        //Se crea una fila para desplegar info de ejercicio
                        out.println("<tr>");
                            out.println("<td>");
                            out.println(id.getValue());
                            out.println("</td>");
                            out.println("<td>");
                            out.println(nombre.getText());
                            out.println("</td>");
                            //Por medio del id, se localiza al ejercicio por ver
                            out.println("<td>");
                            out.println("<form action='verEjercicio' method='post'>");
                            out.println("<input type='hidden' name='id' value="+id.getValue()+">");//Del ejercicio
                            out.println("<input type='submit' value='Ver'>");
                            out.println("</form>");
                            out.println("</td>");
                            //Por medio del id, se localiza al ejercicio por modificar
                            out.println("<td>");
                            out.println("<form action='modificarEjercicio' method='post'>");
                            out.println("<input type='hidden' name='id' value="+id.getValue()+">");//Del ejercicio
                            out.println("<input type='submit' value='Modificar'>");
                            out.println("</form>");
                            out.println("</td>");
                            //Por medio del id, se localiza al ejercicio por eliminar
                            out.println("<td>");
                            out.println("<form action='eliminarEjercicio' method='post'>");
                            out.println("<input type='hidden' name='id' value="+id.getValue()+">");//Del ejercicio
                            out.println("<input type='submit' value='Eliminar'>");
                            out.println("</form>");
                            out.println("</td>");
                        out.println("</tr>"); 
                    } 
                }
                out.println("</table>");
                
                out.println("<br />");
                out.println("<br />");
                
                out.println("<form action='menuProfesor' method='get'>");
                out.println("<input type='submit' value='Menu Profesor'>");
                out.println("</form>");
                
                out.println("</body>");
                out.println("</html>");
        }catch(JDOMException e){}
    }

}