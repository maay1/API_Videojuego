
package com.idat.storeplay_API.dao;

import com.idat.storeplay_API.dbase.ConexionDB;
import com.idat.storeplay_API.vo.VideojuegosVO;

import java.sql.*;
import java.util.*;
import java.util.Collection;
import org.springframework.stereotype.Repository;

@Repository
public class VideojuegoDao {
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;
    
    public VideojuegoDao(){}
    
    public Collection<VideojuegosVO> findAll(){
        List<VideojuegosVO> list = new ArrayList();
        
        try{
            conn=ConexionDB.MySQL();            
            ps=conn.prepareStatement("select * from videojuego");
            rs=ps.executeQuery();
            
            while(rs.next()){
                VideojuegosVO videojuego = new VideojuegosVO();
                
                videojuego.setId_juego(rs.getInt("id_juego"));
                videojuego.setNombre(rs.getString("nombre"));
                videojuego.setDescripcion(rs.getString("descripcion"));
                videojuego.setPrecio(rs.getBigDecimal("precio"));
                videojuego.setImagen(rs.getString("img"));
                videojuego.setCategoria(rs.getString("categoria"));
                videojuego.setStock(rs.getInt("stock"));
                videojuego.setFecha_lanzamiento(rs.getDate("fecha_lanzamiento"));
                
                list.add(videojuego);
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            cerrarConexion();
        }
        return list;
    }
    
    public VideojuegosVO findById(int id) throws ClassNotFoundException {
        VideojuegosVO videojuego = null;
        
        try {
            conn = ConexionDB.MySQL();
            ps = conn.prepareStatement("SELECT * FROM videojuego WHERE id_juego = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            
            if(rs.next()) {
                videojuego = new VideojuegosVO();
                videojuego.setId_juego(rs.getInt("id_juego"));
                videojuego.setNombre(rs.getString("nombre"));
                videojuego.setDescripcion(rs.getString("descripcion"));
                videojuego.setPrecio(rs.getBigDecimal("precio"));
                videojuego.setImagen(rs.getString("img"));
                videojuego.setCategoria(rs.getString("categoria"));
                videojuego.setStock(rs.getInt("stock"));
                videojuego.setFecha_lanzamiento(rs.getDate("fecha_lanzamiento"));
            }
            
        } catch(SQLException e) {
            e.printStackTrace();
        }finally{
            cerrarConexion();
        }
        return videojuego;
    }
    
    public void add(VideojuegosVO videojuego){
        
        try{
            conn = ConexionDB.MySQL();
            ps = conn.prepareStatement(
                    "insert into videojuego(nombre, descripcion, precio, img, categoria, stock, fecha_lanzamiento) values (?,?,?,?,?,?,?)");
            ps.setString(1, videojuego.getNombre());
            ps.setString(2, videojuego.getDescripcion());
            ps.setBigDecimal(3, videojuego.getPrecio());
            ps.setString(4, videojuego.getImagen());
            ps.setString(5, videojuego.getCategoria());
            ps.setInt(6, videojuego.getStock());
            ps.setDate(7, videojuego.getFecha_lanzamiento());
            ps.executeUpdate();
            
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            cerrarConexion();
        }
    }
    
    public void update(VideojuegosVO videojuego){
        
        try{
            conn = ConexionDB.MySQL();
            ps = conn.prepareStatement(
                    "update videojuego set nombre= ?, descripcion= ?, precio= ?, img= ?, categoria= ?, stock= ?, fecha_lanzamiento= ? where id_juego= ?");
            ps.setString(1, videojuego.getNombre());
            ps.setString(2, videojuego.getDescripcion());
            ps.setBigDecimal(3, videojuego.getPrecio());
            ps.setString(4, videojuego.getImagen());
            ps.setString(5, videojuego.getCategoria());
            ps.setInt(6, videojuego.getStock());
            ps.setDate(7, videojuego.getFecha_lanzamiento());
            ps.setInt(8, videojuego.getId_juego());
            ps.executeUpdate();
            
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            cerrarConexion();
        }
    }
    
    public void delete(int id){
        
        try{
            conn=ConexionDB.MySQL();
            ps= conn.prepareStatement("delete from videojuego where id_juego= ?");
            ps.setInt(1, id);
            
            ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            cerrarConexion();
        }
    }
    
    private void cerrarConexion(){
        
        try{
            if(rs != null) rs.close();
            if(ps != null) ps.close();
            if(conn != null) conn.close();
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
}
