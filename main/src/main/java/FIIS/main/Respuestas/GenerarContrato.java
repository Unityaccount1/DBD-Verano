package FIIS.main.Respuestas;

import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.*;

//Seccion para generar y enviar al prospecto el contrato respectivo
public class GenerarContrato {
    @RequestMapping("/GenerarContrato")
    public String[] GeneracionContrato()throws Exception{
        int i=0;
        //Crear el tamaño de la clausula sino saltara una excepcion
        String[] clausulas=new String[10];
        String estado="preliminar";
        Class.forName("org.postgresql.Driver");
        Connection conexion = DriverManager.getConnection("jdbc:postgresql://34.230.110.100:5432/d1d8j80vn6i3ra",
                "mfyhcsjzbxwmvg", "7617d9ab6fef644e27a569932e09ed3d7e9df713e28997b4a127bc070aaa265f");
        Statement st1=conexion.createStatement();
        String Clausulas = "select clausula from clausulas_contrato ";
        ResultSet rs = st1.executeQuery(Clausulas);
        while(rs.next()){
            clausulas[i]=rs.getString(1);
            i++;
        }
        rs.close();
        st1.close();
        Statement st2=conexion.createStatement();
        String contrato="INSERT INTO Contrato(estado,fecha_generacion) VALUES (?,CURDATE())";
        PreparedStatement pst1=conexion.prepareStatement(contrato);
        pst1.setString(1,estado);
        pst1.executeUpdate();
        st1.close();
        conexion.close();
        return clausulas;
    }
}



