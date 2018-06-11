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

public class modifyExercise extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            response.setContentType("text/html;charset=UTF-8");
            HttpSession session = request.getSession();
            String nombreNuevo = request.getParameter("nombreNuevo");//Del registro del nuevo ejercicioNuevo
            String instruccionNuevo = request.getParameter("instruccionNuevo");
            String audioInstruccionNuevo = (String)session.getAttribute("audioInstruccionNuevo");
            String imagenNuevo = (String)session.getAttribute("imagenNuevo");
            String audioImagenNuevo = (String)session.getAttribute("audioImagenNuevo");
            String pistaNuevo = request.getParameter("pistaNuevo");
            String respuestaCorrectaNuevo = request.getParameter("respuestaCorrectaNuevo");
            String respuestaIncorrecta1Nuevo = request.getParameter("respuestaIncorrecta1Nuevo");
            String respuestaIncorrecta2Nuevo = request.getParameter("respuestaIncorrecta2Nuevo");
            String idUsuario = (String) session.getAttribute("idUsuario");
            
            //info del usuario a modificar
            String id = (String)session.getAttribute("id");//id del ejercicio a modificar
            System.out.println("id modifyExercise= "+id);
            
            
//            String usuario = (String)session.getAttribute("usuario");//Del administrador
//            String contrasena = (String)session.getAttribute("contrasena");//Del administrador
            String tipoAtt = (String)session.getAttribute("tipo");
//            String tipoAtt = (String)request.getParameter("tipo");//Del administrador
//            session.setAttribute("tipo", tipoAtt);//conservar sesion del administrador con su tipo
//            PrintWriter out = response.getWriter();
            
            //info del nuevo instruccionNuevo
//            System.out.println("addUser");
//            System.out.println("nuevo= "+nombreNuevo);
//            System.out.println("nuevo= "+instruccionNuevo);
//            System.out.println("nuevo= "+audioInstruccionNuevo);
//            System.out.println("nuevo= "+imagenNuevo);
            
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
                
                //Para cargar el documento xml
                Document doc = builder.build(BD);//documentos para contruir base de datos
                //Se obtiene el elemento raiz del xml
                Element raiz = doc.getRootElement();
                //Lista de nodos almacenados, lo que esta contenido entre las etiquetas de raiz
                List lista = raiz.getChildren("EJERCICIO");
                
                //Para recorrer el arbol de nodos
                for(int i=0;i<lista.size();i++){//Por cada elemento 
                    //Se procesa un elemento de la lista
                    Element element = (Element)lista.get(i);//guarda los datos de la lista en un arreglo de elementos
                    //encontrar el elemento con el id capturado
                    Attribute idElement = element.getAttribute("id");
                    if(idElement.getValue().matches(id)){//se ha encontrado ejercicio con id a modificar
                        //Obtiene los elementos que contiene el elemento actual
                        List lista2 = element.getChildren();//pasa los elementos a lista2
                        Element nombre = (Element)lista2.get(0);
                        Element instruccion = (Element)lista2.get(1);
                        Element audioInstruccion = (Element)lista2.get(2);
                        Element imagen = (Element)lista2.get(3);
                        Element audioImagen = (Element)lista2.get(4);
                        Element pista = (Element)lista2.get(5);
                        Element respuestaCorrecta = (Element)lista2.get(6);
                        Element respuestaIncorrecta1 = (Element)lista2.get(7);
                        Element respuestaIncorrecta2 = (Element)lista2.get(8);
                        
                        nombre.setText(nombreNuevo);
                        instruccion.setText(instruccionNuevo);
                        audioInstruccion.setText(audioInstruccionNuevo);
                        imagen.setText(imagenNuevo);
                        audioImagen.setText(audioImagenNuevo);
                        pista.setText(pistaNuevo);
                        respuestaCorrecta.setText(respuestaCorrectaNuevo);
                        respuestaIncorrecta1.setText(respuestaIncorrecta1Nuevo);
                        respuestaIncorrecta2.setText(respuestaIncorrecta2Nuevo);
                        
                        //Se crea serializador xml (para guardar en el xml)
                        XMLOutputter xmlo =new XMLOutputter();

                        //validar que si escriba bien el archivo, guardar los cambios al archivo
        //                try (FileWriter fw = new FileWriter(rutaAbsoluta+"\\BD.xml")){
                        try (FileWriter fw = new FileWriter(rutaAbsoluta)){
                            xmlo.setFormat(Format.getPrettyFormat());//Formato de salida al xml
                            xmlo.output(doc, fw);//se escribe en el archivo
                            fw.flush();
                        }
                        response.sendRedirect("adminEjercicios");
                    }
                    
                } 
            }
            catch(Exception e){
                e.printStackTrace();
            }
    }
            
            
    }
