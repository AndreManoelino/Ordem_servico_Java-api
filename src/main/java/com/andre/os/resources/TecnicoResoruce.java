package com.andre.os.resources;

// Importação das classes necessárias

import com.andre.os.dominio.Tecnico;
import com.andre.os.dtos.TecnicoDTO;
import com.andre.os.services.TecnicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

// Indica que minha API  ela pode receber requisições de mulytiplas fontes
@CrossOrigin("*")
// Indica que esta classe é um controlador REST
@RestController
// Define o endpoint base da API para "tecnicos"
@RequestMapping(value = "/tecnicos")
public class TecnicoResoruce {

    // Injeta automaticamente o serviço de Técnico
    @Autowired
    private TecnicoService service;

    // Método que busca um técnico por ID via GET (exemplo: /tecnicos/1)
    @GetMapping(value = "/{id}")
    public ResponseEntity<TecnicoDTO> findById(@PathVariable Integer id) {
        // Chama o serviço para buscar o técnico e converte para DTO
        TecnicoDTO objDTO = new TecnicoDTO(service.findById(id));
        // Retorna o técnico encontrado com status HTTP 200 (OK)
        return ResponseEntity.ok().body(objDTO);
    }

    // Método que retorna todos os técnicos cadastrados via GET (exemplo: /tecnicos)
    @GetMapping
    public ResponseEntity<List<TecnicoDTO>> findAll() {
        // Busca todos os técnicos e converte para DTO usando Stream API
        List<TecnicoDTO> listDTO = service.findAll()
                .stream().map(obj -> new TecnicoDTO(obj)).collect(Collectors.toList());

        // Retorna a lista com status HTTP 200 (OK)
        return ResponseEntity.ok().body(listDTO);
    }

    @PostMapping
    public ResponseEntity<TecnicoDTO> create(@Valid @RequestBody TecnicoDTO objDTO) {
        Tecnico newObj = service.create(objDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
    /*Atualiza um Técnico no banco de dados */
    @PutMapping(value = "/{id}")
    public ResponseEntity<TecnicoDTO> update(@PathVariable Integer id,@Valid @RequestBody TecnicoDTO objDTO) {
        TecnicoDTO newObj = new TecnicoDTO(service.update(id, objDTO));
        return ResponseEntity.ok().body(newObj);
    }

    /*Delete Tecnico*/
    @DeleteMapping(value="/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();

    }
}
