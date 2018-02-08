/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw;


import edu.eci.pdsw.stubs.datasourcestub.Client;
import edu.eci.pdsw.stubs.datasourcestub.ClientNotFoundException;
import edu.eci.pdsw.stubs.datasourcestub.DataSourceStub;
import java.io.IOException;
import java.io.Writer;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author 2098325
 */

@WebServlet(
    urlPatterns = "/claseNueva"
)
public class claseNueva extends HttpServlet{
    private DataSourceStub base;
    private Client clien;    
    
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        base=DataSourceStub.getInstance();
        try {
            
            Writer responseWriter=resp.getWriter();
            Optional <Integer> optName = Optional.of(Integer.parseInt(req.getParameter("Iden")));
            Integer name = optName.get();
            clien=base.getClientById(name);
            String HTML=
                  "<html>"+
                "<head><title>Ejemplo de tabla sencilla</title></head>"+
                "<body>"+
                 "<h1>Listado de cursos</h1>"+
                 "<table>"+
                "<tr>"+
                  "<td><strong>Curso</strong></td>"+
                  "<td><strong>Horas</strong></td>"+
                  "<td><strong>Horario</strong></td>"+
                "</tr>"+
                "<tr>"+
                  "<td>SS</td>"+
                  "<td>20</td>"+
                  "<td>16:00 - 20:00</td>"+
                "</tr>"+
                "<tr>"+
                  "<td>HTML</td>"+
                  "<td>20</td>"+
                  "<td>16:00 - 20:00</td>"+
                "</tr>"+
                "<tr>"+
                  "<td>Dreamweaver</td>"+
                  "<td>60</td>"+
                  "<td>16:00 - 20:00</td>"+
                "</tr>"+
                "</table>"+
                "</body>"+
                "</html>";
                    
                   


            responseWriter.write("OK"+name+"!");
            responseWriter.write("Correo"+clien.getAddress()+"!");
            responseWriter.write(HTML);
            responseWriter.flush();
            
            
        } catch (ClientNotFoundException ex) {
            Logger.getLogger(claseNueva.class.getName()).log(Level.SEVERE, null, ex);
        }
        //responseWriter.write("OK"+name1+"!");
        //responseWriter.flush();
    }
    
 
    
}
