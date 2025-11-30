/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.idat.storeplay_API.resources;

import com.idat.storeplay_API.dao.VideojuegoDao;
import com.idat.storeplay_API.vo.VideojuegosVO;
import java.sql.SQLException;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class VideojuegoResources {
    @Autowired
    private VideojuegoDao videojuegosDAO;
    
    //obtener - get
    @GetMapping("/videojuego")
    public Collection<VideojuegosVO> getVideojuego(){
        return videojuegosDAO.findAll();
    }
    
     //obtener x id - by id
    @GetMapping("/videojuego/{id}")
    public ResponseEntity<VideojuegosVO> getVideojuegoXId(@PathVariable int id) throws ClassNotFoundException{
        VideojuegosVO juego = videojuegosDAO.findById(id);
        if(juego != null){
            return ResponseEntity.ok(juego);
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    
    //agregar - add
    @PostMapping("/agregar/videojuego")
    public ResponseEntity<String> addVideojuego(@RequestBody VideojuegosVO videojuego){
        try{
            videojuegosDAO.add(videojuego);
            return ResponseEntity.status(HttpStatus.CREATED).body("Videojuego agregado exitosamente");         
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: "+ e.getMessage());
        }
    }
    
    //actualizar - update
    @PutMapping("/actualizar/videojuego/{id}")
    public ResponseEntity<String> updateVideojuego(@PathVariable int id,@RequestBody VideojuegosVO videojuego){
        try{
            videojuego.setId_juego(id);
            videojuegosDAO.update(videojuego);
            return ResponseEntity.status(HttpStatus.CREATED).body("Videojuego actualizado con exito");
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: "+ e.getMessage());
        }
    }
    
    //eliminar - delete
    @DeleteMapping("/eliminar/videojuego/{id}")
    public ResponseEntity<String> deleteVideojuego(@PathVariable int id){
        try{
            videojuegosDAO.delete(id);
            return ResponseEntity.ok("Videojuego eliminado exitosamente");
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                               .body("Error: " + e.getMessage());
        }
    }
    
    @GetMapping("/api")
    public String ping(){
        return "ping";
    }
}
