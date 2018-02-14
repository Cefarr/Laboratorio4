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
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
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
        Writer responseWriter=resp.getWriter();
        System.out.println("llego");
        Optional <Integer> optName = Optional.of(Integer.parseInt(req.getParameter("Iden")));                    
        Integer name = optName.get();
        try {

            clien=base.getClientById(name);
            System.out.println("mirar el usuario"+clien.getName());
            if(clien.equals(null)){
                responseWriter.write("Usuario no encontrado"+clien.getSallary());
                System.out.println("321321312ver"+clien.equals(req));            
            
            }else{


            System.out.println("llego3");
            String HTML="<!DOCTYPE html>"+
                    
                    "<html>"+
                    "<head><title>Ejemplo de tabla sencilla</title></head>"+
                    "<body>"+
                    "<h1>Listado de cursos</h1>"+
                    "<table>"+
                    "<tr>"+
                    "<td><strong>Nombre</strong></td>"+
                    "<td><strong>Correo Electronico</strong></td>"+
                    "<td><strong>Dirrecion</strong></td>"+
                    "<td><strong>Salario</strong></td>"+
                    "</tr>"+
                    "<tr>"+
                    "<td>"+clien.getName()+"</td>"+
                    "<td>"+clien.getEmail()+"</td>"+
                    "<td>"+clien.getAddress()+"</td>"+
                    "<td>"+clien.getSallary()+"</td>"+
                    "</tr>"+
                    "</table>"+
                    "</body>"+
                    "</html>";
                    responseWriter.write(HTML);
                    responseWriter.flush();                

            }
        } catch (ClientNotFoundException ex) {
            resp.setStatus(2);            
            Logger.getLogger(claseNueva.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //responseWriter.write("OK"+name1+"!");
        //responseWriter.flush();
    }
    
 
    
}
