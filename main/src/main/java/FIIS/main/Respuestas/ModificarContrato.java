package FIIS.main.Respuestas;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

//Insertar clausulas
public class ModificarContrato {
    @RequestMapping("/InsertarClausula")
    public String ModificaC(@RequestParam String clausula) throws Exception{

        Class.forName("org.postgresql.Driver");
        Connection conexion = DriverManager.getConnection("jdbc:postgresql://34.230.110.100:5432/d1d8j80vn6i3ra",
                "mfyhcsjzbxwmvg", "7617d9ab6fef644e27a569932e09ed3d7e9df713e28997b4a127bc070aaa265f");
        Statement st1=conexion.createStatement();
        String InsertarClausula ="INSERT INTO CLAUSULAS_CONTRATO(clausulas) VALUES (?)";
        PreparedStatement pst1=conexion.prepareStatement(InsertarClausula);
        pst1.setString(1,clausula);
        pst1.executeUpdate();
        st1.close();
        conexion.close();
        return "Insercion de clausula exitosa";
    }

}
