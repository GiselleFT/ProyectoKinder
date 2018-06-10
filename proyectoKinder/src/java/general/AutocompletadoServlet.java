//AutocompletadoServlet.java
package general;

import java.io.File;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import java.io.IOException;
//import moduloautobd.servicio.ServicioAutocompletado;
import java.io.PrintWriter;
import javax.servlet.http.HttpServlet;

public class AutocompletadoServlet extends HttpServlet {

    //Para no heredar se usa private
    private ArrayList palabras;

    public AutocompletadoServlet() {
        System.out.println("AutocompletadoServlet()");
    }

    public void init(ServletConfig config) {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String prefijo = request.getParameter("names");

        
//        //Para obtener la ruta absoluta del proyecto
//                String rutaAbsoluta = request.getSession().getServletContext().getRealPath("/");
//                rutaAbsoluta = rutaAbsoluta.replace("\\", "/");
//                rutaAbsoluta = rutaAbsoluta.replaceAll("/build", "");
//                rutaAbsoluta = rutaAbsoluta.concat("BD.xml");
//                File BD = new File(rutaAbsoluta);
        
        
        //Obteniendo la lista de palabras del servicio de bd
        ServicioAutocompletado sa = new ServicioAutocompletado();
        palabras = sa.obtListaPalabras();

        //Obteniendo palabras similares
        //Método estático
        ServicioComparador sc = ServicioComparador.obtInstancia(palabras);  
        ArrayList similares = sc.buscarPalabras(prefijo);

        if(similares.size() > 0) {
            response.setContentType("text/xml");
            //Meta instrucciones, no dejan huella
            response.setHeader("Cache-Control", "no-cache");

            PrintWriter salida = response.getWriter();
            salida.println("<response>");

            for (int i = 0; i < similares.size(); i++) {
                String palabra = (String) similares.get(i);
                salida.println("<grupo>" + palabra + "</grupo>");
            }

            salida.println("</response>");

            palabras = null;
            similares = null;
            sc = null;

            salida.close();
        } else {
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        }
    }
}
