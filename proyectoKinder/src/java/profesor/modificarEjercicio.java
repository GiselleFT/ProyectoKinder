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

public class modificarEjercicio extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
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
            
            //info del ejercicio a modificar
            String id = (String)request.getParameter("id");//id del ejercicio a modificar
            session.setAttribute("id", id);
            System.out.println("id modificarEjercicio= "+id);
            
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
                
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Modificar Ejercicio</title>");
                out.println("<link rel='stylesheet' href='css/estilos.css'>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Modificar Ejercicio</h1>");
                
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
                        
                        
                        String nombreM = nombre.getText();
                        String instruccionM = instruccion.getText();
                        String audioInstruccionM = audioInstruccion.getText();
                        String imagenM = imagen.getText();
                        String audioImagenM = audioImagen.getText();
                        String pistaM = pista.getText();
                        String respuestaCorrectaM = respuestaCorrecta.getText();
                        String respuestaIncorrecta1M = respuestaIncorrecta1.getValue();
                        String respuestaIncorrecta2M = respuestaIncorrecta2.getValue();
                        
                        //Modificar archivos multimedia
                        out.println("<form action='modificarAudioInstruccion' method='post'>");
                        out.println("<h6>Archivo audio instruccion: "+audioInstruccionM+"</h6>");
//                        out.println("<input type='hidden' name='nombreArchivo' value='"+audioInstruccionM+"'>");
//                        out.println("<input type='hidden' name='tipoModificar' value='1'>");
                        out.println("<input type='submit' value='Modificar Audio Instruccion'>");
                        out.println("</form>");
                        
                        out.println("<form action='modificarImagen' method='post'>");
                        out.println("<h6>Archivo imagen: "+imagenM+"</h6>");
//                        out.println("<input type='hidden' name='nombreArchivo' value='"+imagenM+"'>");
//                        out.println("<input type='hidden' name='tipoModificar' value='2'>");
                        out.println("<input type='submit' value='Modificar Imagen'>");
                        out.println("</form>");
                        
                        out.println("<form action='modificarAudioImagen' method='post'>");
                        out.println("<h6>Archivo audio imagen: "+audioImagenM+"</h6>");
//                        out.println("<input type='hidden' name='nombreArchivo' value='"+audioImagenM+"'>");
//                        out.println("<input type='hidden' name='tipoModificar' value='3'>");
                        out.println("<input type='submit' value='Modificar Audio Imagen'>");
                        out.println("</form>");
                        
                        
                        out.println("<form action='modifyExercise' method='get'>");
                        out.println("<h6>Nombre:</h6> <input id='nombre' type='text' value='"+nombreM+"' name='nombreNuevo' required/><br />");
                        out.println("<h6>Instruccion:</h6> <input id='instruccion' type='text' value='"+instruccionM+"' name='instruccionNuevo' required/><br />");
                        //out.println("<h6>Audio instruccion:</h6> <input id='audioInstruccion' type='text' value='"+audioInstruccionM+"' name='audioInstruccionNuevo' required/><br />");
                        //out.println("<h6>Imagen:</h6> <input id='imagen' type='text' value='"+imagenM+"' name='imagenNuevo' required/><br />");
                        //out.println("<h6>Audio imagen:</h6> <input id='audioImagen' type='text' value='"+audioImagenM+"' name='audioImagenNuevo' required/><br />");
                        out.println("<h6>Pista:</h6> <input id='pista' type='text' value='"+pistaM+"' name='pistaNuevo' required/><br />");
                        out.println("<h6>Respuesta correcta:</h6> <input id='respuestaCorrecta' type='text' value='"+respuestaCorrectaM+"' name='respuestaCorrectaNuevo' required/><br />");
                        out.println("<h6>Respuesta incorrecta 1:</h6> <input id='respuestaIncorrecta1' type='text' value='"+respuestaIncorrecta1M+"' name='respuestaIncorrecta1Nuevo' required/><br />");
                        out.println("<h6>Respuesta incorrecta 2:</h6> <input id='respuestaIncorrecta2' type='text' value='"+respuestaIncorrecta2M+"' name='respuestaIncorrecta2Nuevo' required/><br />");
                        
                        
                        out.println("<br />");
                        out.println("<br />");
                        out.println("<input type='submit' value='Modificar ejercicio'>");
                        out.println("</form>");
                        
                    }
                    
                }
                
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
