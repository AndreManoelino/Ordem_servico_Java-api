package com.andre.os.resources;

// Importação das classes necessárias

import com.andre.os.dominio.Cliente;

import com.andre.os.dtos.ClienteDTO;
import com.andre.os.dtos.TecnicoDTO;
import com.andre.os.services.ClienteService;
import com.andre.os.services.TecnicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin("*")
// Indica que esta classe é um controlador REST
@RestController
// Define o endpoint base da API para "tecnicos"
@RequestMapping(value = "/clientes")
public class ClienteResoruce {

    // Injeta automaticamente o serviço de Técnico
    @Autowired
    private ClienteService service;

    // Método que busca um técnico por ID via GET (exemplo: /tecnicos/1)
    @GetMapping(value = "/{id}")
    public ResponseEntity<ClienteDTO> findById(@PathVariable Integer id) {
        // Chama o serviço para buscar o técnico e converte para DTO
        ClienteDTO objDTO = new ClienteDTO(service.findById(id));
        // Retorna o técnico encontrado com status HTTP 200 (OK)
        return ResponseEntity.ok().body(objDTO);
    }

    // Método que retorna todos os técnicos cadastrados via GET (exemplo: /tecnicos)
    @GetMapping
    public ResponseEntity<List<ClienteDTO>> findAll() {
        // Busca todos os técnicos e converte para DTO usando Stream API
        List<ClienteDTO> listDTO = service.findAll()
                .stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());

        // Retorna a lista com status HTTP 200 (OK)
        return ResponseEntity.ok().body(listDTO);
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> create(@Valid @RequestBody ClienteDTO objDTO) {
        Cliente newObj = service.create(objDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
    /*Atualiza um cliente no banco de dados */
    @PutMapping(value = "/{id}")
    public ResponseEntity<ClienteDTO> update(@PathVariable Integer id, @Valid @RequestBody ClienteDTO objDTO) {
        ClienteDTO newObj = new ClienteDTO(service.update(id, objDTO));
        return ResponseEntity.ok().body(newObj);
    }

    /*Delete cliente*/
    @DeleteMapping(value="/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();

    }
}
