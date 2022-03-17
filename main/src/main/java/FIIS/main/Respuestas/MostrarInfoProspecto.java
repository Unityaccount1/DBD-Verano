package FIIS.main.Respuestas;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class MostrarInfoProspecto {
    @RequestMapping("/InfoProspecto")
    public ArrayList<InfoProspecto> MostrarP(@RequestParam String RucIngresado) throws Exception {
        String ruc,razonS,estadoC,cond,direcF,provF,depF,distF,direcC,provC,depC,distC;
        String[] url_docA=new String[6];
        String[] tipo_docA=new String[6];
        ArrayList<InfoProspecto> arregloInfo = new ArrayList<>();
        Class.forName("org.postgresql.Driver");
        Connection conexion = DriverManager.getConnection("jdbc:postgresql://34.230.110.100:5432", "mfyhcsjzbxwmvg", "7617d9ab6fef644e27a569932e09ed3d7e9df713e28997b4a127bc070aaa265f");
        Statement st1 = conexion.createStatement();
        String Prospecto = "select E.ruc,E.razon_social,E.condicion,E.estado_contribuyente," +
                "E.departamento_fiscal,E.provincia_fiscal,E.distrito_fiscal,E.direccion_exacta_fiscal"+
                "E.departamento_comercial,E.provincia_comercial,E.distrito_comercial,E.direccion_exacta_comercial,"+
                "D.tipo_documento,D.url_documento from Empresa E, Documento D where E.ruc=´RucIngresado´";
        ResultSet rs = st1.executeQuery(Prospecto);
        while (rs.next()) {
            ruc = rs.getString(1);
            razonS = rs.getString(2);
            cond = rs.getString(3);
            estadoC = rs.getString(4);
            depF = rs.getString(5);
            provF = rs.getString(6);
            distF=rs.getNString(7);
            direcF=rs.getString(8);
            depC = rs.getString(9);
            provC = rs.getString(10);
            distC=rs.getNString(11);
            direcC=rs.getString(12);
            InfoProspecto InfoP;
            for(int i=0;i<6;i++){
                tipo_docA[i]=rs.getString(13);
                url_docA[i]=rs.getString(14);
            }
            InfoP=CampoProspecto(ruc,razonS,cond,estadoC,depF,provF,distF,direcF,depC,provC,distC,direcC,tipo_docA,url_docA);

            arregloInfo.add(InfoP);
        }
        rs.close();
        st1.close();
        return arregloInfo;
    }
    public InfoProspecto CampoProspecto(String Ruc,String Razon,String Cond,String Estado,String DepaF,String ProvF,
                                        String DistritoF,String direccionF,String DepaC,String ProvC,
                                        String DistritoC,String direccionC,String[] tipo,String[] Url){
        InfoProspecto objetoI=new InfoProspecto();
        objetoI.setRuc(Ruc);
        objetoI.setRazonS(Razon);
        objetoI.setCond(Cond);
        objetoI.setEstadoC(Estado);
        objetoI.setDepF(DepaF);
        objetoI.setProvF(ProvF);
        objetoI.setDistF(DistritoF);
        objetoI.setDirecF(direccionF);
        objetoI.setDepC(DepaC);
        objetoI.setProvC(ProvC);
        objetoI.setDistC(DistritoC);
        objetoI.setDirecC(direccionC);
        objetoI.setUrl_doc(tipo);
        objetoI.setTipo_doc(Url);
        return objetoI;
    }
}
