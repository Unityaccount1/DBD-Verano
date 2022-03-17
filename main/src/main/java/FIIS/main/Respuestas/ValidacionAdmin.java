package FIIS.main.Respuestas;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
@Controller
public class ValidacionAdmin {
    @RequestMapping("/ValidarAdmin")
    public String ValidaUsuario(@RequestParam String correo, @RequestParam String clave) throws Exception {
        String usuario2, clave2;
        int contador = 0;
        Class.forName("org.postgresql.Driver");
        Connection conexion = DriverManager.getConnection("jdbc:postgresql://34.230.110.100:5432/d1d8j80vn6i3ra",
                "mfyhcsjzbxwmvg", "7617d9ab6fef644e27a569932e09ed3d7e9df713e28997b4a127bc070aaa265f");
        Statement st1 = conexion.createStatement();
        String datovalidar = "select correo,contrasenia from usuario ";
        ResultSet rs = st1.executeQuery(datovalidar);
        while (rs.next()) {
            usuario2 = rs.getString(1);
            clave2 = rs.getString(2);
            if ((usuario2.equals(correo)) && (clave2.equals(clave))) {
                contador = contador + 1;
            }
            System.out.print("Usuario:" + usuario2 + "Clave:" + clave2 + "El contador es:" + contador);
        }
        rs.close();
        st1.close();
        conexion.close();
        if (contador == 1) {
            return "redirect:/Admin.html";
        } else {
            return "Usuario o clave incorrectos";
        }
    }

    //Por implementar validacion del afiliador dando valores para pasar el flujo desde el html recomendable: int=1,2,3

}
