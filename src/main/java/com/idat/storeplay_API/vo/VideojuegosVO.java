
package com.idat.storeplay_API.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Table(name = "videojuego")
public class VideojuegosVO {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_juego;
    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    @Column(name = "img", length= 700)
    private String imagen;
    private String categoria;
    private int stock;
    private Date fecha_lanzamiento;
    
    public VideojuegosVO() {
    }

    public VideojuegosVO(int id_juego, String nombre, String descripcion, BigDecimal precio, String imagen, String categoria, int stock, Date fecha_lanzamiento) {
        this.id_juego = id_juego;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.imagen = imagen;
        this.categoria = categoria;
        this.stock = stock;
        this.fecha_lanzamiento = fecha_lanzamiento;
    }
    
    public int getId_juego() {
        return id_juego;
    }

    public void setId_juego(int id_juego) {
        this.id_juego = id_juego;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre1) {
        this.nombre = nombre1;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Date getFecha_lanzamiento() {
        return fecha_lanzamiento;
    }

    public void setFecha_lanzamiento(Date fecha_lanzamiento) {
        this.fecha_lanzamiento = fecha_lanzamiento;
    }
    
}
