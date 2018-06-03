package general;

import administrador.addGroup;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import org.xml.sax.SAXException;

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
            //Contruye un documento JDOM usando SAX, para procesar xml
            SAXBuilder builder = new SAXBuilder();
            //Para obtener la ruta absoluta del proyecto
            String rutaAbsoluta = request.getSession().getServletContext().getRealPath("/");
            rutaAbsoluta = rutaAbsoluta.replace("\\", "/");
            rutaAbsoluta = rutaAbsoluta.replaceAll("/build", "");
            //Ruta absoluta del archivo BD.xml
            rutaAbsoluta = rutaAbsoluta.concat("BD.xml");
            File BD = new File(rutaAbsoluta);
            
            
            //Para obtener la ruta absoluta del proyecto XSD
            String rutaEsquema = request.getSession().getServletContext().getRealPath("/");
            rutaEsquema = rutaEsquema.replace("\\", "/");
            rutaEsquema = rutaEsquema.replaceAll("/build", "");
            rutaEsquema = rutaEsquema.concat("esquemaUsuario.xsd");
            /*Bloque para checar si el xml es bien conformado y valido respecto
            a su esquema xsd
            */
            validador validador = new validador();
            try {
                //Error en la validacion
                if(!validador.checkAll(rutaAbsoluta, rutaEsquema, out)){
                    System.out.println("ENTRE EN NO ES VALIDO");
                    response.sendRedirect("errorValidacion?errorConforme="+validador.getErrorConforme()+"&errorValido="
                            +validador.getErrorValido());
                }
                else{
                    //Para cargar el documento xml
                    Document doc = builder.build(BD);//documentos para contruir base de datos
                    //Se obtiene el elemento raiz del xml
                    Element raiz = doc.getRootElement();
                    //Lista de USUARIOS almacenados, lo que esta contenido entre las etiquetas de raiz
                    List lista = raiz.getChildren("USUARIO");
                    
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Inicio Sesion</title>");
                    out.println("<link rel='stylesheet' href='css/estilos.css'>");
                    out.println("</head>");
                    out.println("<body>");
                    
//                System.out.println("Tamano lista: " + lista.size());
//Para recorrer el arbol de nodos de la lista
for(int i=0;i<lista.size();i++){//Por cada elemento
    //Se procesa un elemento de la lista
    Element element = (Element)lista.get(i);//Uno de los elementos de la lista
    //Obtiene los elementos que contiene el elemento actual
    List lista2 = element.getChildren();//pasa los elementos a lista2
    //Se crea usuario2 para comparar con usuario
    Element usuario2 = (Element)lista2.get(1);
    //se crea contrasena2 para comprar con contrasena
    Element contrasena2 = (Element)lista2.get(2);
    
    if(usuario2.getText().matches(usuario)){//valida si el usuario se encuentra en la bd
        existeUsuario = true;
        if(contrasena2.getText().matches(contrasena)){//valida si la contraseña coincide
            Attribute tipo = element.getAttribute("tipo");
            //Sube a sesion el tipo de usuario que logro iniciar sesion
            session.setAttribute("tipo", tipo.getValue());//getValue, retorna el valor textual del atributo
            System.out.println("Inicio sesion Tipo=" + tipo.getValue());
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
            out.println("<center><h1>Contraseña incorrecta para: "+usuario+"</h1></center></br>");//indica error de contraseña
            out.println("<center><h1><a href='login.html'>!Regresar al Login</a></h1></center>");
            break;//rompe ciclo for
        }
    }
    else{//Si no existe el usuario (aun)
        existeUsuario = false;
    }
}
if(!existeUsuario){//Si no existe el usuario
    out.println("<center><h1>"+usuario+" !Usuario no registrado en el sistema</h1></center></br>");
    out.println("<center><h1><a href='login.html'>!Regresar al Login</a></h1></center>");
}
out.println("</body>");
out.println("</html>");

                }
                
            } catch (JDOMException ex) {
                System.out.println("Error en validacion");
                Logger.getLogger(addGroup.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SAXException ex) {
                System.out.println("Error en validacion");
                Logger.getLogger(addGroup.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

}