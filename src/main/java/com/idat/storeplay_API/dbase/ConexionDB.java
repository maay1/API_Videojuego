package com.idat.storeplay_API.dbase;

import java.sql.*;

public class ConexionDB {
    
    public static Connection MySQL() throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.jdbc.Driver");
        Connection c= DriverManager.getConnection("jdbc:mysql://localhost:3306/tienda_videojuegos","root","123456");     
        return c;      
    }    
}
