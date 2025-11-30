/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.idat.storeplay_API.repository;

import com.idat.storeplay_API.vo.VideojuegosVO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author mar_j
 */
public interface VideojuegoRepository extends JpaRepository<VideojuegosVO, Integer>{
    
    
}
