package FIIS.main.Respuestas;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
@RestController
public class MostrarProspectos {
    @RequestMapping(value = "/MostrarProspectos")
    public ArrayList<ObtenerProspecto> MostrarP() throws Exception {
        String nombre, apellidoPat, apellidoMat, estado, fecha;
        int codigo;

        ArrayList<ObtenerProspecto> arregloProspecto = new ArrayList<>();
        Class.forName("org.postgresql.Driver");
        Connection conexion = DriverManager.getConnection("jdbc:postgresql://34.230.110.100:5432/d1d8j80vn6i3ra",
                "mfyhcsjzbxwmvg", "7617d9ab6fef644e27a569932e09ed3d7e9df713e28997b4a127bc070aaa265f");
        Statement st1 = conexion.createStatement();
        String Prospecto = "select S.codigo_solicitud,P.apellido_paterno,P.apellido_materno,P.nombres,S.estado,S.fecha_inicio from Persona P,Solicitud S";
        ResultSet rs = st1.executeQuery(Prospecto);
        while (rs.next()) {
            codigo = rs.getInt(1);
            apellidoPat = rs.getString(2);
            apellidoMat = rs.getString(3);
            nombre = rs.getString(4);
            estado = rs.getString(5);
            fecha = rs.getString(6);
            ObtenerProspecto atributos;
            atributos=ObjetoProspecto(codigo,apellidoPat,apellidoMat,nombre,estado,fecha);
            arregloProspecto.add(atributos);
        }
        rs.close();
        st1.close();
        conexion.close();
        return arregloProspecto;
    }
    public ObtenerProspecto ObjetoProspecto(int cod,String apelpat,String apelmat,String nombre,String estado,String fecha){
        ObtenerProspecto objetoP=new ObtenerProspecto();
        objetoP.setCodigo(cod);
        objetoP.setApelpat(apelpat);
        objetoP.setApelmat(apelmat);
        objetoP.setNombre(nombre);
        objetoP.setEstado(estado);
        objetoP.setFecha(fecha);
        return objetoP;
    }
}
