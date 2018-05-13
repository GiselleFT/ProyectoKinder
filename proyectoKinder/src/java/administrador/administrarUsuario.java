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
            HttpSession session = request.getSession();
            String usuario = (String)session.getAttribute("usuario");
            String contrasena = (String)session.getAttribute("contrasena");
            String tipoAtt = (String)session.getAttribute("tipo");
            PrintWriter out = response.getWriter();
            //*****************************//
            if(!tipoAtt.equals("1")){
               response.sendRedirect("login.html");
            }
            //*****************************//
            try{
            SAXBuilder builder = new SAXBuilder();//contructor sax
            File BD = new File("C:\\Users\\Giselle\\Documents\\GitHub\\ProyectoKinder\\proyectoKinder\\web\\BD.xml");//ruta de base de datos
            Document doc = builder.build(BD);//documentos para contruir base de datos
            Element raiz = doc.getRootElement();//raiz de los datos
            List lista = raiz.getChildren();//llena lista con los datos de la base de datos
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet1</title>");
            out.println("<link href='estilos.css' type='text/css' rel='stylesheet'>");
            out.println("</head>");
            out.println("<body>");
            out.println("<form action='agregarUsuario' method='post'>");
            out.println("<input type='submit' value='Crear Usuario'>");
            out.println("</form>");
            out.println("<table border='3'>");
           
                out.println("<tr>");
                out.println("<th>Usuario</th>");
                out.println("<th>Nombre</th>"); 
                out.println("<th>Tipo</th>"); 
                out.println("<th>Modificar</th>"); 
                out.println("<th>Eliminar</th>"); 
                out.println("</tr>");
                
            String type = "";
            for(int i=0;i<lista.size();i++){//Por cada usuario 
                Element ele = (Element)lista.get(i);//guarda los datos de la lista en un arreglo de elementos
                List lista2 = ele.getChildren();//pasa los elementos a lista2
                Element nombre = (Element)lista2.get(0);
                Element usuario2 = (Element)lista2.get(1);//se crea usuario2 para comparar con usuario
                Attribute tipo = ele.getAttribute("tipo");
                
                if(tipo.getValue().matches("1")){
                    type = "Administrador";
                }
                else if(tipo.getValue().matches("2")){
                    type = "Profesor";
                }
                else if(tipo.getValue().matches("3")){
                    type = "Alumno";
                }
                else{
                    type = "No definido";
                }
                out.println("<tr>");
                    out.println("<td>");
                    out.println(nombre.getValue());
                    out.println("</td>");
                    out.println("<td>");
                    out.println(usuario2.getValue());
                    out.println("</td>");
                    out.println("<td>");
                    out.println(type);
                    out.println("</td>");
                    out.println("<td>");
                    out.println("<button type='button' onclick='modificarUsuario'>Modificar</button>");
                    out.println("</td>");
                    out.println("<td>");
                    out.println("<button type='button' onclick='eliminarUsuario'>Eliminar</button>");
                    out.println("</td>");
                out.println("</tr>"); 
            }
            out.println("</table>");
            out.println("</body>");
            out.println("</html>");
        }catch(JDOMException e){}
    }

}