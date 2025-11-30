/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.idat.storeplay_API.resources;

import com.idat.storeplay_API.repository.VideojuegoRepository;
import com.idat.storeplay_API.vo.VideojuegosVO;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
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
    private VideojuegoRepository videojuegoRepository;
    
    @Autowired
    private jakarta.persistence.EntityManager entityManager;
    
    @GetMapping("/crear-tabla")
    public ResponseEntity<String> crearTabla() {
        try {
            // Crear tabla videojuegos_api con auto-incremental
            entityManager.createNativeQuery(
                "CREATE TABLE IF NOT EXISTS videojuegos_api (" +
                "id_juego SERIAL PRIMARY KEY, " +
                "nombre VARCHAR(255) NOT NULL, " +
                "descripcion TEXT, " +
                "precio DECIMAL(10,2), " +
                "img VARCHAR(700), " +
                "categoria VARCHAR(100), " +
                "stock INT, " +
                "fecha_lanzamiento DATE)"
            ).executeUpdate();
            
            return ResponseEntity.ok(" Tabla 'videojuegos_api' creada correctamente con AUTO-INCREMENT");
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                               .body("Error creando tabla: " + e.getMessage());
        }
    }
    
    // Endpoint para inicializar datos de prueb
    @GetMapping("/inicializar")
    public ResponseEntity<String> inicializarDatos() {
        try {
            try {
                videojuegoRepository.count(); // Intenta contar para ver si la tabla existe
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                   .body("❌ La tabla no existe. Ejecuta primero: /api/crear-tabla");
            }
            
            // Verificar si ya hay datos
            if (videojuegoRepository.count() > 0) {
                return ResponseEntity.ok("Ya existen datos. Total: " + videojuegoRepository.count() + " registros.");
            }
            // Crear datos de prueba
            List<VideojuegosVO> juegosDemo = Arrays.asList(
                crearVideojuego("Elden Ring", "RPG de mundo abierto creado por FromSoftware y George R. R. Martin", 
                               new BigDecimal("59.99"), "https://imgs.search.brave.com/VVZ3N3jClTDgVQFgWnSP0FHRATGMzwb6dHgf8WhJXps/rs:fit:500:0:1:0/g:ce/aHR0cHM6Ly9zdGF0/aWMuYmFuZGFpbmFt/Y29lbnQuZXUvaGln/aC9lbGRlbi1yaW5n/L2VsZGVuLXJpbmcv/MDAtcGFnZS1zZXR1/cC9lbGRlbi1yaW5n/LWdhbWUtdGh1bWJu/YWlsLmpwZw", 
                               "RPG", 80, "2022-02-25"),
                
                crearVideojuego("Cyberpunk 2077", "RPG futurista en Night City con mundo abierto y narrativa profunda", 
                               new BigDecimal("59.99"), "https://imgs.search.brave.com/WjNcwJgrwU-u60RhtExi7lZ68n3TdQogaU0qyLimEZY/rs:fit:500:0:1:0/g:ce/aHR0cHM6Ly9tZWRp/YS5nYW1lc3RvcC5j/b20vaS9nYW1lc3Rv/cC8xMDE3ODUxMV8x/MTA5NDU5NF8xMTA5/NDcyMF8xMTA5NDcy/MV8xMTA5NDcyMl8x/MTA5NDc0NTJfU0NS/MDIvQ3liZXJwdW5r/LTIwNzctLS1QbGF5/U3RhdGlvbi00P3c9/MTI1NiZoPTY2NCZm/bXQ9YXV0bw", 
                               "RPG", 120, "2020-12-10"),
                crearVideojuego("Red Dead Redemption 2", "Aventura épica en el Salvaje Oeste con historia cinematográfica", 
                               new BigDecimal("59.99"), "https://imgs.search.brave.com/Kj8cdW9rxEWgMTwqV_S0fMDtrlB6VIiW99aw20mLkQQ/rs:fit:500:0:1:0/g:ce/aHR0cHM6Ly9pLnl0/aW1nLmNvbS92aV93/ZWJwL0pYWktMZkxa/a3Y4L21heHJlc2Rl/ZmF1bHQud2VicA", 
                               "Acción", 90, "2019-11-05"),
                
                crearVideojuego("Hogwarts Legacy", "RPG ambientado en el mundo mágico de Harry Potter", 
                               new BigDecimal("59.99"), "https://imgs.search.brave.com/ZYcq7HNaJRp6UD8URp0lr-BqCk6zGp6kMa-BMMl2PVM/rs:fit:500:0:1:0/g:ce/aHR0cHM6Ly91cGxv/YWQud2lraW1lZGlh/Lm9yZy93aWtpcGVk/aWEvY29tbW9ucy8z/LzMzL0hvZ3dhcnRz/X0xlZ2FjeV9wcm9t/b3Rpb25hbF9waG90/b19ob3Jpem9udGFs/LmpwZw", 
                               "Aventura", 70, "2023-02-10"),
                
                crearVideojuego("Baldur's Gate 3", "RPG basado en Dungeons & Dragons con narrativa y decisiones profundas", 
                               new BigDecimal("59.99"), "https://imgs.search.brave.com/JQ1kunsnVUYC33_vaL_gCYpWjYGfZphCGK-WpRu-Pvk/rs:fit:500:0:1:0/g:ce/aHR0cHM6Ly9pLnl0/aW1nLmNvbS92aV93/ZWJwL0FFODhNR2l6/VXFnL21heHJlc2Rl/ZmF1bHQud2VicA", 
                               "RPG", 65, "2023-08-03"),
                
                crearVideojuego("Resident Evil 4 Remake", "Reimaginación del clásico survival horror con gráficos modernos", 
                               new BigDecimal("59.99"), "https://imgs.search.brave.com/_ut922l1DjRWAbMpcnsMAbA2OqM4i3-heoab3mqknJY/rs:fit:500:0:1:0/g:ce/aHR0cHM6Ly93YWxs/cGFwZXJjYXZlLmNv/bS93cC93cDEyMTE2/NzI3LmpwZw", 
                               "Horror", 85, "2023-03-24"),
                
                crearVideojuego("The Witcher 3: Wild Hunt", "RPG de mundo abierto con una de las mejores narrativas del género", 
                               new BigDecimal("39.99"), "https://imgs.search.brave.com/avEDY8PSloh82JW1O0EsyXcS1U3WniEQEyL6NLtlS0g/rs:fit:500:0:1:0/g:ce/aHR0cHM6Ly93YWxs/cGFwZXJjYXZlLmNv/bS93cC93cDI5NDE4/NTYuanBn", 
                               "RPG", 150, "2015-05-18"),
                
                crearVideojuego("God of War", "Aventura nórdica de Kratos y Atreus con combate intenso", 
                               new BigDecimal("49.99"), "https://imgs.search.brave.com/vT_EDsE8vt67QxLerK-fdnagkagaNy-_53Qs4JAXNDs/rs:fit:500:0:1:0/g:ce/aHR0cHM6Ly9pLnl0/aW1nLmNvbS92aV93/ZWJwLzZrUUVIcjA2/c2RjL21heHJlc2Rl/ZmF1bHQud2VicA", 
                               "Acción", 110, "2022-01-14"),
                
                crearVideojuego("Counter-Strike 2", "La evolución del FPS competitivo más jugado del mundo", 
                               new BigDecimal("15.63"), "https://imgs.search.brave.com/mvx4H0qLhRSbkqbvVS5rXjnD-ZAbX-U04aJf8n8ZiIE/rs:fit:500:0:1:0/g:ce/aHR0cHM6Ly93YWxs/cGFwZXJzLmNvbS9p/bWFnZXMvZmVhdHVy/ZWQvY291bnRlci1z/dHJpa2UtZ2xvYmFs/LW9mZmVuc2l2ZS1w/aWN0dXJlcy1iNWd4/MXlnMWVlZ2w3N2V3/LmpwZw", 
                               "Shooter", 9999, "2023-09-27"),
                
                crearVideojuego("Among Us", "Juego social de deducción con impostores y tripulantes", 
                               new BigDecimal("4.99"), "https://imgs.search.brave.com/Ap81GcNDi1jkrh1FsHAkeyEC0V-Sl1s8IQXc7QhZkBk/rs:fit:500:0:1:0/g:ce/aHR0cHM6Ly9hcnRp/Y2xlcy1pbWcuc2Z0/Y2RuLm5ldC90X2Fy/dGljbGVfY292ZXJf/bS9hdXRvLW1hcHBp/bmctZm9sZGVyL3Np/dGVzLzMvMjAyMi8w/OC9BbW9uZy1Vcy1p/bi1WUi5qcGc", 
                               "Party", 300, "2018-11-16")
            );
            
            // Guardar todos - ¡MÁS SIMPLE!
            videojuegoRepository.saveAll(juegosDemo);
            
            return ResponseEntity.status(HttpStatus.CREATED)
                               .body("¡Datos inicializados correctamente! Se insertaron " + juegosDemo.size() + " videojuegos.");
                               
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                               .body("Error al inicializar datos: " + e.getMessage());
        }
    }
    
    // Método auxiliar para crear objetos VideojuegosVO
    private VideojuegosVO crearVideojuego(String nombre, String descripcion, BigDecimal precio, 
                                         String imagen, String categoria, int stock, String fechaLanzamiento) {
        try {
            VideojuegosVO juego = new VideojuegosVO();
            juego.setNombre(nombre);
            juego.setDescripcion(descripcion);
            juego.setPrecio(precio);
            juego.setImagen(imagen);
            juego.setCategoria(categoria);
            juego.setStock(stock);
            
            // Convertir String a Date
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date utilDate = dateFormat.parse(fechaLanzamiento);
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            juego.setFecha_lanzamiento(sqlDate);
            
            return juego;
        } catch (Exception e) {
            throw new RuntimeException("Error al crear videojuego: " + e.getMessage(), e);
        }
    }
    
    // Obtener todos - GET
    @GetMapping("/videojuego")
    public List<VideojuegosVO> getVideojuego(){
        return videojuegoRepository.findAll();
    }
    
    // Obtener por ID - GET
    @GetMapping("/videojuego/{id}")
    public ResponseEntity<VideojuegosVO> getVideojuegoXId(@PathVariable int id){
        Optional<VideojuegosVO> juego = videojuegoRepository.findById(id);
        if(juego.isPresent()){
            return ResponseEntity.ok(juego.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    
    // Agregar - POST
    @PostMapping("/agregar/videojuego")
    public ResponseEntity<VideojuegosVO> addVideojuego(@RequestBody VideojuegosVO videojuego){
        VideojuegosVO nuevoJuego = videojuegoRepository.save(videojuego);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoJuego);
    }
    
    // Actualizar - PUT
    @PutMapping("/actualizar/videojuego/{id}")
    public ResponseEntity<VideojuegosVO> updateVideojuego(@PathVariable int id, @RequestBody VideojuegosVO videojuego){
        if (!videojuegoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        videojuego.setId_juego(id);
        VideojuegosVO juegoActualizado = videojuegoRepository.save(videojuego);
        return ResponseEntity.ok(juegoActualizado);
    }
    
    // Eliminar - DELETE
    @DeleteMapping("/eliminar/videojuego/{id}")
    public ResponseEntity<String> deleteVideojuego(@PathVariable int id){
        if (!videojuegoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        videojuegoRepository.deleteById(id);
        return ResponseEntity.ok("Videojuego eliminado exitosamente");
    }
    
    // Endpoint de prueba
    @GetMapping("/test")
    public String test(){
        return "API funcionando - " + new java.util.Date();
    }
}