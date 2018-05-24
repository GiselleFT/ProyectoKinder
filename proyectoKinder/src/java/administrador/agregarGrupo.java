package administrador;

import java.io.File;
import java.io.FileWriter;
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
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

public class agregarGrupo extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {  
            response.setContentType("text/html;charset=UTF-8");
            HttpSession session = request.getSession();
            String usuario = (String)session.getAttribute("usuario");
            String contrasena = (String)session.getAttribute("contrasena");
            String tipoAtt = (String)session.getAttribute("tipo");
            PrintWriter out = response.getWriter();
            
            //info del administrador
//            System.out.println("agregarUsuario");
//            System.out.println("admin= "+usuario);
//            System.out.println("admin= "+contrasena);
//            System.out.println("admin= "+tipoAtt);
            
            //********Valida Tipo Usuario****************//
            if(!tipoAtt.equals("1")){
               response.sendRedirect("login.html");
            }
            //*******************************************//
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Agregar Grupo</title>");
            out.println("<link rel='stylesheet' href='css/estilos.css'>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Agregar Grupo</h1>");
            
            out.println("<form action='addGroup' method='get'>");
            out.println("<h6>Nombre grupo:</h6> <input id='nombre' type='text' name='nombreGrupoNuevo' required/><br />");
            
            try{
                //Contruye un documento JDOM usando SAX, para procesar xml
                SAXBuilder builder = new SAXBuilder();
                //Para obtener la ruta absoluta del proyecto
//                String rutaAbsoluta = request.getSession().getServletContext().getRealPath("/");
//                rutaAbsoluta = rutaAbsoluta.replaceAll("'\'", "'\\'");
//                System.out.println("formInicioSesion ruta:" + rutaAbsoluta);
//                System.out.println("RUTA ABSOLUTA= "+rutaAbsoluta);
                //Ruta absoluta del archivo BD.xml
//                File BD = new File("C:\\Users\\Giselle\\Documents\\GitHub\\ProyectoKinder\\proyectoKinder\\web\\BD.xml");              
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
//                List lista = raiz.getChildren();
                List lista = raiz.getChildren("USUARIO");
//                List idProfesores = null;
//                List nombresProfesores = null;
                out.println("<h6>Profesor:</h6> <select id='idProfesorNuevo' name='idProfesorNuevo'>");//Combobox    
//                List nombresAlumnos = null;
                //Para recorrer el arbol de nodos
                for(int i=0;i<lista.size();i++){//Por cada elemento 
                    //Se procesa un elemento de la lista
                    Element element = (Element)lista.get(i);//guarda los datos de la lista en un arreglo de elementos
                    //encontrar el elemento con el id capturado
                    Attribute idTipo = element.getAttribute("tipo");
                    Attribute idUsuario = element.getAttribute("id");//IMPORTANTE
                    if(idTipo.getValue().matches("2")){//se ha encontrado usuario Profesor
                       List lista2 = element.getChildren();//pasa los elementos a lista2
                       Element nombreProfesor = (Element)lista2.get(0);//Obtiene el nombre
//                       idProfesores.add(idUsuario.getValue());
//                       nombresProfesores.add(nombreProfesor.getText());
                        out.println("<option value='"+idUsuario.getValue()+"'>"+nombreProfesor.getText()+"</option>");
//                        System.out.println("NOMBRE PROFESORRR= " +nombreProfesor.getText());
                    }
                }
                out.println("</select>");
                
                
                out.println("<br />");
                out.println("<h6>Agregar Alumnos:</h6>");//Combobox
                out.println("<br />");
                for(int i=0;i<lista.size();i++){//Por cada elemento 
                    //Se procesa un elemento de la lista
                    Element element = (Element)lista.get(i);//guarda los datos de la lista en un arreglo de elementos
                    //encontrar el elemento con el id capturado
                    Attribute idTipo = element.getAttribute("tipo");
                    Attribute idUsuario = element.getAttribute("id");//IMPORTANTE
                    if(idTipo.getValue().matches("3")){//se ha encontrado usuario Profesor
                       List lista2 = element.getChildren();//pasa los elementos a lista2
                       Element nombreAlumno = (Element)lista2.get(0);//Obtiene el nombre
//                       idProfesores.add(idUsuario.getValue());
//                       nombresProfesores.add(nombreProfesor.getText());
                        out.println("<input type='checkbox' id='alumnoSeleccionado' name='alumnoSeleccionado' value='"+idUsuario.getValue()+"'>"+nombreAlumno.getText()+"<br>");
//                        System.out.println("NOMBRE PROFESORRR= " +nombreProfesor.getText());
                    }
                }
               
            out.println("<input type='hidden' name='tipo' value="+tipoAtt+">");//Del administrador
            out.println("<br />");
            out.println("<br />");
            out.println("<input type='submit' value='Agregar Grupo'>");
            out.println("</form>");
            
            
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