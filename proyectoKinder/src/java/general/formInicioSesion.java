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
            //Recuperar parametros de login
            String usuario = request.getParameter("usuario");
            String contrasena = request.getParameter("contrasena");
            
            boolean existeUsuario = false;

            //Subir parametros a la sesion
            HttpSession session = request.getSession();
            session.setAttribute("usuario", usuario);
            session.setAttribute("contrasena", contrasena);

            PrintWriter out = response.getWriter();

            try{
                //Contruye un documento JDOM usando SAX, para procesar xml
                SAXBuilder builder = new SAXBuilder();
                //Para obtener la ruta absoluta del proyecto
//                String rutaAbsoluta = request.getSession().getServletContext().getRealPath("/");
//                rutaAbsoluta = rutaAbsoluta.replaceAll("'\build'", "");
//                rutaAbsoluta = rutaAbsoluta.replaceFirst("\\build", "\\web\\BD.xml");
//                System.out.println("formInicioSesion ruta:" + rutaAbsoluta);
//                System.out.println("RUTA ABSOLUTA= "+rutaAbsoluta);
                //Ruta absoluta del archivo BD.xml
                File BD = new File("\\bd\\BD.xml");
//                System.out.println("ruta = " + ruta.getAbsolutePath());
//                File BD = new File("C:\\Users\\Giselle\\Documents\\GitHub\\ProyectoKinder\\proyectoKinder\\web\\BD.xml");              
                //Para cargar el documento xml
                Document doc = builder.build(BD);//documentos para contruir base de datos
                //Se obtiene el elemento raiz del xml
                Element raiz = doc.getRootElement();
                //Lista de nodos almacenados, lo que esta contenido entre las etiquetas de raiz
                List lista = raiz.getChildren();
                
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Inicio Sesion</title>");
                out.println("<link rel='stylesheet' href='css/estilos.css'>");
                out.println("</head>");
                out.println("<body>");
                
                //Para recorrer el arbol de nodos
                for(int i=0;i<lista.size();i++){//Por cada elemento  
                    //Se procesa un elemento de la lista
                    Element element = (Element)lista.get(i);//guarda los datos de la lista en un arreglo de elementos
                    //Obtiene los elementos que contiene el elemento actual
                    List lista2 = element.getChildren();//pasa los elementos a lista2
                    //Se crea usuario2 para comparar con usuario
                    Element usuario2 = (Element)lista2.get(1);
                    //se crea contrasena2 para comprar con contrasena
                    Element contrasena2 = (Element)lista2.get(2);
                   
                    if(usuario2.getTextTrim().matches(usuario)){//valida si el usuario se encuentra en la bd
                        existeUsuario = true;
                        if(contrasena2.getTextTrim().matches(contrasena)){//valida si la contraseña coincide
                            Attribute tipo = element.getAttribute("tipo");
                            //Sube a sesion el tipo de usuario que logro iniciar sesion
                            session.setAttribute("tipo", tipo.getValue());//getValue, retorna el valor textual del atributo
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
                        }
                        else{//contraseña incorrecta
                            out.println("<center><h1>Contrasena incorrecta para: "+usuario+"</h1></center></br>");//indica error de contraseña
                            out.println("<center><h1><a href='login.html'>!Regresar al Login</a></h1></center>");
                            break;//rompe ciclo for
                        }
                    }//Si no existe el usuario (aun)
                    else{
                        existeUsuario = false;
                        //String cadena=element.getTextTrim();
//                        out.println(cadena);
//                        out.println("<br>");
                    }
                }
                if(!existeUsuario){//Si no existe el usuario
                    out.println("<center><h1>"+usuario+" !Usuario no registrado en el sistema</h1></center></br>");
                    out.println("<center><h1><a href='login.html'>!Regresar al Login</a></h1></center>");
                }
                out.println("</body>");
                out.println("</html>");
        }catch(JDOMException e){}
    }

}