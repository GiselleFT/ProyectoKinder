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

public class modifyGroup extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            response.setContentType("text/html;charset=UTF-8");
            HttpSession session = request.getSession();
            String nombreGrupoNuevo = (String)request.getParameter("nombreGrupoNuevo");//Del registro del nuevo idProfesorNuevo
            String idProfesorNuevo = (String)request.getParameter("idProfesorNuevo");//Del registro del nuevo idProfesorNuevo
            String [] idAlumnos = request.getParameterValues("alumnoSeleccionado");
            
            //info del usuario a modificar
            String idGrupo = (String)session.getAttribute("idGrupo");//id del usuario a modificar
            System.out.println("id modifyGroup= "+idGrupo);
            
            
            String usuario = (String)session.getAttribute("usuario");//Del administrador
            String contrasena = (String)session.getAttribute("contrasena");//Del administrador
            String tipoAtt = (String)request.getParameter("tipo");//Del administrador
            session.setAttribute("tipo", tipoAtt);//conservar sesion del administrador con su tipo
            PrintWriter out = response.getWriter();
            
            //info del nuevo usuarioNuevo
//            System.out.println("addUser");
//            System.out.println("nuevo= "+nombreNuevo);
//            System.out.println("nuevo= "+usuarioNuevo);
//            System.out.println("nuevo= "+contrasenaNuevo);
//            System.out.println("nuevo= "+tipoNuevo);
            
            //info del administrador
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
                List lista = raiz.getChildren("GRUPO");
                
                //Para recorrer el arbol de nodos
                for(int i=0;i<lista.size();i++){//Por cada elemento 
                    //Se procesa un elemento de la lista
                    Element element = (Element)lista.get(i);//guarda los datos de la lista en un arreglo de elementos
                    //encontrar el elemento con el idGrupo capturado
                    Attribute idElement = element.getAttribute("id");
                    if(idElement.getValue().matches(idGrupo)){//se ha encontrado usuario con idGrupo a modificar
                        //Obtiene los elementos que contiene el elemento actual
                        List lista2 = element.getChildren();//pasa los elementos a lista2
                        //Nombre
                        Element nombreGrupo = (Element)lista2.get(0);
                        //Se crea idProfesor para comparar con usuario
                        Element idProfesor = (Element)lista2.get(1);
                        //se crea nombreProfesor para comprar con contrasena
                        Element nombreProfesor = (Element)lista2.get(2);
                        //se crea nombreProfesor para comprar con contrasena
                        Element inscritos = (Element)lista2.get(3);
                        
                        System.out.println("NOMBRE DEL GRUPO= " +nombreGrupoNuevo);
                        nombreGrupo.setText(nombreGrupoNuevo);
                        idProfesor.setText(idProfesorNuevo);
                        //Buscar profesor por su id para obtener su nombre
                        List listaUsuarios = raiz.getChildren("USUARIO");
                        for(int j=0;j<listaUsuarios.size();j++){//Por cada elemento
                            Element elementProfesor = (Element)listaUsuarios.get(j);
                            Attribute idTipo = elementProfesor.getAttribute("tipo");
                            Attribute idUsuario = elementProfesor.getAttribute("id");//IMPORTANTE
                            if(idTipo.getValue().matches("2") && idUsuario.getValue().matches(idProfesorNuevo)){
                                List infoProfesor = elementProfesor.getChildren();
                                Element nomProfesor = (Element)infoProfesor.get(0);//Obtiene el nombre
                                nombreProfesor.setText(nomProfesor.getText());
                            }  
                        }
                        
                        
                        inscritos.setText(idAlumnos.length+"");
                        
                        //Elimina todos los alumnos
                        element.removeChildren("idAlumno");
                        //Agrega los alumnos 
                        for (int k = 0; k < idAlumnos.length; k++) {
                            Element idAlumno = new Element("idAlumno");
                            idAlumno.setText(idAlumnos[k]);
                            element.addContent(idAlumno);
                        }
                        
                        //Se crea serializador xml (para guardar en el xml)
                        XMLOutputter xmlo =new XMLOutputter();

                        //validar que si escriba bien el archivo, guardar los cambios al archivo
        //                try (FileWriter fw = new FileWriter(rutaAbsoluta+"\\BD.xml")){
                        try (FileWriter fw = new FileWriter(rutaAbsoluta)){
                            xmlo.setFormat(Format.getPrettyFormat());//Formato de salida al xml
                            xmlo.output(doc, fw);//se escribe en el archivo
                            fw.flush();
                        }
                        response.sendRedirect("administrarGrupos?modGrupo=1");
                    }
                    
                } 
            }
            catch(Exception e){
                e.printStackTrace();
            }
    }
            
            
    }