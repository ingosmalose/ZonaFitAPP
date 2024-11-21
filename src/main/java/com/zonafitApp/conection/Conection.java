package com.zonafitApp.conection;
import com.mysql.cj.jdbc.ConnectionGroup;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conection {

    
    public static Connection getConection(){
        Connection connecting = null;
        var db = "zona_fit_db";
        var url = "jdbc:mysql://localhost:3306/"+db;
        var user = "root";
        var pass = "@Ing.123+";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connecting = DriverManager.getConnection(url,user,pass);
        }catch(Exception e){
            System.out.println("Error connection DB : "+e.getMessage());
        }
        return connecting;

    }

    public static void closeConection(Connection conexion) throws SQLException {
        conexion.close();
        //return  conexion;
    }

    public static void main(String[] args){
        var conection = Conection.getConection();
        if(conection!=null) {
            System.out.println("Succesful : " + conection);

        }else
            System.out.println("Error");
    }

}
