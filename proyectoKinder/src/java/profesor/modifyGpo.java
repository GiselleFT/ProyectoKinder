package profesor;

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

public class modifyGpo extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            response.setContentType("text/html;charset=UTF-8");
            HttpSession session = request.getSession();
            String [] idEjercicios = request.getParameterValues("ejercicioSeleccionado");
            
            //info del usuario a modificar
            String idGrupo = (String)session.getAttribute("idGrupo");//id del grupo a modificar
            System.out.println("id modifyGpo= "+idGrupo);
            
            
            String usuario = (String)session.getAttribute("usuario");//Del administrador
            String tipoAtt = (String)request.getParameter("tipo");//Del administrador
            session.setAttribute("tipo", tipoAtt);//conservar sesion del administrador con su tipo
            PrintWriter out = response.getWriter();
            
            
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
                //Para cargar el documento xml
                Document doc = builder.build(BD);//documentos para contruir base de datos
                //Se obtiene el elemento raiz del xml
                Element raiz = doc.getRootElement();
                //Lista de nodos almacenados, lo que esta contenido entre las etiquetas de raiz
                List lista = raiz.getChildren("EJERCICIOS_GRUPO");
                
                //Para recorrer el arbol de nodos
                for(int i=0;i<lista.size();i++){//Por cada elemento 
                    //Se procesa un elemento de la lista
                    Element element = (Element)lista.get(i);//guarda los datos de la lista en un arreglo de elementos
                    //encontrar el elemento con el idGrupo capturado
                    Attribute idElement = element.getAttribute("idGrupo");
                    if(idElement.getValue().matches(idGrupo)){//se ha encontrado grupo con idGrupo a modificar
                        //Obtiene los elementos que contiene el elemento actual
                        List lista2 = element.getChildren();//pasa los elementos a lista2
                                                
                        
                        //Elimina todos los alumnos
                        element.removeChildren("idEjercicio");
                        //Agrega los alumnos 
                        for (int k = 0; k < idEjercicios.length; k++) {
                            Element idEjercicio = new Element("idEjercicio");
                            idEjercicio.setText(idEjercicios[k]);
                            element.addContent(idEjercicio);
                        }
                        
                        //Se crea serializador xml (para guardar en el xml)
                        XMLOutputter xmlo =new XMLOutputter();

                        //validar que si escriba bien el archivo, guardar los cambios al archivo
                        try (FileWriter fw = new FileWriter(rutaAbsoluta)){
                            xmlo.setFormat(Format.getPrettyFormat());//Formato de salida al xml
                            xmlo.output(doc, fw);//se escribe en el archivo
                            fw.flush();
                        }
                        response.sendRedirect("adminGrupos");
                    }
                    
                } 
            }
            catch(Exception e){
                e.printStackTrace();
            }
    }
            
            
    }
