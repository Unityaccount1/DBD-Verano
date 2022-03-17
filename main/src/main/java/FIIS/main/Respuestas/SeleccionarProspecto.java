package FIIS.main.Respuestas;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.ArrayList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SeleccionarProspecto {
    @RequestMapping("/SelectProspecto")
    public String SelectProspecto() throws Exception {
        String nombre,apellidoPat,apellidoMat,

        ArrayList<ObtenerProspecto> arregloProspecto=new ArrayList<>();
        Class.forName("org.postgresql.Driver");
        Connection conexion= DriverManager.getConnection("jdbc:postgresql://1.1.1.1","ola","ola2");
        Statement st1=conexion.createStatement();
        String Prospecto = "select P.apellido_paterno,P.apellido_materno,P.nombre,S.estado,S.Fecha_inicio from Persona P,Solicitud S";
        ResultSet rs = st1.executeQuery(Prospecto);
         = rs.getString(1);
         = rs.getString(2);
                    //ObtenerUsuario atributos;
                    //atributos=ObjetoUsuario(usuario2,clave2);
                    //arreglousuario.add(atributos);

                rs.close();
                st1.close();

}
