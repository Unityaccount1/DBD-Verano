package FIIS.main.Respuestas;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;
import java.util.ArrayList;

//Seccion exclusiva para el administrador donde podra realizar consultas predeterminadas a la base de datos
//contabilizar los prospectos y filtrarlos segun el estado de su solicitud
//añadir o disminuir la cantidad de clausulas del contrato
//Redirigir a la seccion del afiliador
@RestController
public class Reportes {
    @RequestMapping("/Reportes")
    public ArrayList<ObtenerReporte> GeneracionReporte()throws Exception{
        int numsol=1,solpendiente=1,numdocobs=1,numperdep=1;
        ArrayList<ObtenerReporte> DatosReporte=new ArrayList<>();
        Class.forName("org.postgresql.Driver");
        Connection conexion = DriverManager.getConnection("jdbc:postgresql://34.230.110.100:5432/d1d8j80vn6i3ra",
                "mfyhcsjzbxwmvg", "7617d9ab6fef644e27a569932e09ed3d7e9df713e28997b4a127bc070aaa265f");
        Statement st1=conexion.createStatement();
        String NumSol = "select COUNT(*) from SOLICITUD";
        ResultSet rs = st1.executeQuery(NumSol);
        while(rs.next()){
            numsol=rs.getInt(1);
        }
        rs.close();
        st1.close();
        Statement st2=conexion.createStatement();
        String solpde="Select COUNT(*) FROM SOLICITUD WHERE ESTADO='PENDIENTE'";
        ResultSet rs2=st2.executeQuery(solpde);
        while(rs2.next()){
            solpendiente=rs2.getInt(1);
        }
        rs2.close();
        st2.close();
        Statement st3=conexion.createStatement();
        String NumDocObs="SELECT COUNT(*) FROM SOLICITUD WHERE ESTADO='OBSERVADO'";
        ResultSet rs3=st3.executeQuery(NumDocObs);
        while(rs3.next()){
            numdocobs=rs3.getInt(1);
        }
        rs3.close();
        st3.close();
        Statement st4=conexion.createStatement();
        String NumPersDep="SELECT DEPARTAMENTO_COMERCIAL FROM EMPRESA WHERE DEPARTAMENTO_FISCAL='LIMA'";
        ResultSet rs4=st4.executeQuery(NumPersDep);
        while(rs4.next()){
            numperdep=rs4.getInt(1);
        }
        rs4.close();
        st4.close();
        conexion.close();
        ObtenerReporte ReporteFinal;
        ReporteFinal=ObjetoReporte(numsol,solpendiente,numdocobs,numperdep);
        DatosReporte.add(ReporteFinal);
        return DatosReporte;
    }
    public ObtenerReporte ObjetoReporte(int Nsol,int NPen,int NSolObs,int NPerLima){
        ObtenerReporte reporteF=new ObtenerReporte();
        reporteF.setNumsol(Nsol);
        reporteF.setNumpend(NPen);
        reporteF.setNumobs(NSolObs);
        reporteF.setNumdep(NPerLima);
        return reporteF;
    }
}
