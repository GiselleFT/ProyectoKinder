package general;

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

public class formInicioSesion extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         
            response.setContentType("text/html;charset=UTF-8");
            String usuario = (String)request.getParameter("usuario");
            String contrasena = (String)request.getParameter("contrasena");
            
            HttpSession session = request.getSession();
            session.setAttribute("usuario", usuario);
            session.setAttribute("contrasena", contrasena);
            
            PrintWriter out = response.getWriter();
            
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
            for(int i=0;i<lista.size();i++){//Por cada usuario 
                Element ele = (Element)lista.get(i);//guarda los datos de la lista en un arreglo de elementos
                List lista2 = ele.getChildren();//pasa los elementos a lista2
                Element usuario2 = (Element)lista2.get(1);//se crea usuario2 para comparar con usuario
                Element contrasena2 = (Element)lista2.get(2);//se crea contrasena2 para comprar con pass
                if(usuario2.getTextTrim().matches(usuario)){//valida si el usuario se encuentra en la bd
                    if(contrasena2.getTextTrim().matches(contrasena)){//valida si el pasword coincide con el id
                        Attribute tipo = ele.getAttribute("tipo");
                        session.setAttribute("tipo", tipo.getValue());
                        System.out.println("Tipo=" + tipo.getValue());
                        if(tipo.getValue().matches("1")){//Administrador
                            response.sendRedirect("menuAdministrador");
                        }
                        else if(tipo.getValue().matches("2")){//Profesor
                            response.sendRedirect("menuProfesor");
                        }
                        else if(tipo.getValue().matches("3")){//Alumno
                            response.sendRedirect("menuAlumno");
                        }
                        else{//no existe ese usuario
                            response.sendRedirect("login.html");
                        }
                    }else
                        out.println("Incorrect Password for "+ usuario);//indica error de contraseña
                }else{
                String cadena=ele.getTextTrim();
                out.println(cadena);
                out.println("<br>");
                }
            }
            out.println("<center><h1>"+usuario+"!Unregistered user in the system</h1></center></br>");
            out.println("<center><h1>"+usuario+"<a href='index.html'>!Back to Login</a></h1></center>");
            out.println("</body>");
            out.println("</html>");
        }catch(JDOMException e){}
    }

}