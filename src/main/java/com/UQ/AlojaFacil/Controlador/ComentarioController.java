package com.UQ.AlojaFacil.Controlador;

import com.UQ.AlojaFacil.Negocio.Servicio.ComentarioServicio;
import com.UQ.AlojaFacil.Negocio.dto.ComentarioDTO;
import com.UQ.AlojaFacil.Negocio.dto.PublicarComentarioDTO;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@Schema(description = "Publicar comentario")
@RequestMapping("/api/v0/comentario")
public class ComentarioController {

    private final ComentarioServicio comentarioServicio;

    @PostMapping
    public ResponseEntity<ComentarioDTO>crearComentario(@Parameter(description = "Publicar comentario",
                                                                    required = true)
                                                        @RequestBody PublicarComentarioDTO publicarComentarioDTO){
        log.debug("POST /api/v0/comentario -Creando nuevo comentario{}",publicarComentarioDTO.getIdInmueble());

        ComentarioDTO nuevoComentario=comentarioServicio.crearComentario(publicarComentarioDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoComentario);

    }
}
