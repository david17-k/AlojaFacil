package com.UQ.AlojaFacil.Excepciones;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {


    public GlobalExceptionHandler(){
        System.out.println("EXCEPCION LANZADA");
    }


    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String,Object>> handleIllegalArgument(IllegalArgumentException e){
        Map<String,Object>excepciones=new HashMap<>();
        excepciones.put("Mesaje",e.getMessage());
        excepciones.put("Codigo", HttpStatus.BAD_REQUEST.value());
        excepciones.put("timeStat", LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(excepciones);
    }
}
