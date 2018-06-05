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
        int banderaArchivo = (Integer) session.getAttribute("banderaArchivo");
        
        if (!ServletFileUpload.isMultipartContent(request)) {
            throw new IllegalArgumentException("Request is not multipart, please 'multipart/form-data' enctype for your form.");
        }

     ServletFileUpload uploadHandler = new ServletFileUpload(new DiskFileItemFactory());
     PrintWriter out = response.getWriter();
     String rutaAbsoluta = request.getSession().getServletContext().getRealPath("/");
            rutaAbsoluta = rutaAbsoluta.replace("\\", "/");
            rutaAbsoluta = rutaAbsoluta.replaceAll("/build", "");
            rutaAbsoluta = rutaAbsoluta.concat("/archivos/");
            System.out.println(rutaAbsoluta);
     System.out.println(new File(rutaAbsoluta));
     try {
         List<FileItem> items = uploadHandler.parseRequest(request);
         
         for (FileItem item : items) {
             
             if (!item.isFormField()) {
                     File file = new File(rutaAbsoluta, item.getName());
                     item.write(file);
                     System.out.println("uploaded");
             }
             if(banderaArchivo == 1){
                 session.setAttribute("audioInstruccionNuevo", item.getName());
             }
             else if(banderaArchivo == 2){
                 session.setAttribute("imagenNuevo", item.getName());
             }
             else{
                 session.setAttribute("audioImagenNuevo", item.getName());
                 session.setAttribute("banderaArchivo", 1);
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
