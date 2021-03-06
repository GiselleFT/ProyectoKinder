package profesor;

import java.io.*;
import java.util.*;
 
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class uploadFiles extends HttpServlet {
   
    public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
        HttpSession session = request.getSession();
        String idUsuario = (String) session.getAttribute("idUsuario");
        int banderaArchivo = (Integer) session.getAttribute("banderaArchivo");
        int banderaModificar = (Integer) session.getAttribute("banderaModificar");

        /* Se checa si se tiene una peticion de subida de archivo multiparte
        Al momento de envío de informacion por parte del cliente al 
            servidor HTTP.
            un archivo se divide en secciones por el protocolo HTTP
            Si no es multiparte no se puede subir el archivo*/
        if (!ServletFileUpload.isMultipartContent(request)) {
            throw new IllegalArgumentException("Request is not multipart, please 'multipart/form-data' enctype for your form.");
        }

        //Crea un manejador para subida de archivos
        ServletFileUpload uploadHandler = new ServletFileUpload(new DiskFileItemFactory());
        
        PrintWriter out = response.getWriter();
        
        //Para obtener la ruta donde queremos almacenar los archivos
        String rutaAbsoluta = request.getSession().getServletContext().getRealPath("/");
        rutaAbsoluta = rutaAbsoluta.replace("\\", "/");
        rutaAbsoluta = rutaAbsoluta.replaceAll("/build", "");
        rutaAbsoluta = rutaAbsoluta.concat("/archivos/");
//        System.out.println(rutaAbsoluta);
//        System.out.println(new File(rutaAbsoluta));
        
        
        try {
            //Parsea la solicitud para obtener los elementos de tipo archivo
            List<FileItem> items = uploadHandler.parseRequest(request);
            //Se iteran los archivos
            for (FileItem item : items) {
                //Si no hay un campo formado
                if (!item.isFormField()) {
                    //Se crea el archivo en la carpeta destino con el nombre de archivo
                    File file = new File(rutaAbsoluta, idUsuario+"_"+item.getName());
                    String contentType = item.getContentType();
                    /*Para continuar con el flujo de la creacion de un ejercicio
                    Se sube a sesion el nombre del archivo para recuperarlo
                    y guardar en el xml el nombre
                    Se checa que el tipo de archivo segun el paso sea el indicado
                    */
                    System.out.println("Bandera= " + banderaArchivo);
                    if(banderaArchivo == 1||banderaModificar == 1){ 
                            item.write(file);//Se escribe el archivo
                            System.out.println("Archivo subido");
                            session.setAttribute("audioInstruccionNuevo", idUsuario+"_"+item.getName());
                            System.out.println("*"
                        + "*"
                        + "*\n*\n*\n*\n*\n*\n*\n*\n*SAM1"+banderaArchivo+"\n*\n*\n*\n*"+session.getAttribute("audioInstruccionNuevo")+"*\n*\n*\n*\n*\n*\n*\n*\n*\n*\n*\n*\n*");
                        session.setAttribute("banderaArchivo", 0);
                        session.setAttribute("banderaModificar", 0);
                        
                        
                    } 
                    else if(banderaArchivo == 2||banderaModificar == 2){
                            item.write(file);//Se escribe el archivo
                            System.out.println("Archivo subido");
                            session.setAttribute("imagenNuevo", idUsuario+"_"+item.getName());
                            System.out.println("*"
                        + "*"
                        + "*\n*\n*\n*\n*\n*\n*\n*\n*SAM2\n*\n*\n*\n*"+session.getAttribute("imagenNuevo")+"*\n*\n*\n*\n*\n*\n*\n*\n*\n*\n*\n*\n*");
                        session.setAttribute("banderaArchivo", 0);
                        session.setAttribute("banderaModificar", 0);
                    } 
                    else if(banderaArchivo == 3||banderaModificar == 3){
                            item.write(file);//Se escribe el archivo
                            System.out.println("Archivo subido");
                            session.setAttribute("audioImagenNuevo", idUsuario+"_"+item.getName());
                            session.setAttribute("banderaArchivo", 0);
                            session.setAttribute("banderaModificar", 0);
                        
                    }
                }
                
            }
        } catch (FileUploadException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            out.close();
        }

    }
}
