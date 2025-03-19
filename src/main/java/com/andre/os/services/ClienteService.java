package com.andre.os.services;

import com.andre.os.dominio.Cliente;
import com.andre.os.dominio.Pessoa;

import com.andre.os.dtos.ClienteDTO;

import com.andre.os.dtos.TecnicoDTO;
import com.andre.os.repositories.ClienteRepository;
import com.andre.os.repositories.PessoaRepository;

import com.andre.os.services.exceptions.DataIntegrationViolationException;
import com.andre.os.services.exceptions.ObjectNotFoundExceptions;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Autowired
    private ClienteRepository repository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public Cliente findById(Integer id) {
        Optional<Cliente> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundExceptions(
                "Objeto não encontrado com o ID: " + id + " Tipo: " + Cliente.class.getName()));
    }

    public List<Cliente> findAll() {
        return repository.findAll();
    }

    public Cliente create(@Valid TecnicoDTO objDTO) {
        if (findByCPF(objDTO.getCpf()) != null) {
            throw new DataIntegrationViolationException("CPF já cadastrado na base de dados!");
        }
        Cliente newObj = new Cliente(null, objDTO.getNome(), objDTO.getCpf(), objDTO.getTelefone());
        return repository.save(newObj);
    }

    public Cliente update(Integer id, @Valid ClienteDTO objDTO) {
        Cliente oldObj = findById(id);
        if (findByCPF(objDTO.getCpf()) != null && !findByCPF(objDTO.getCpf()).getId().equals(id)) {
            throw new DataIntegrationViolationException("CPF já cadastrado na base de dados!");
        }
        oldObj.setNome(objDTO.getNome());
        oldObj.setCpf(objDTO.getCpf());
        oldObj.setTelefone(objDTO.getTelefone());
        return repository.save(oldObj);
    }

    private Pessoa findByCPF(String cpf) {
        Pessoa obj = pessoaRepository.findByCPF(cpf); // Corrigido: 'cpf' diretamente, sem 'obj.DTO'
        if (obj != null) {
            return obj;
        }
        return null;
    }


    public void delete(Integer id) {
        Cliente obj = findById(id);
        if (obj.getOsList().size() > 0) {
            throw new DataIntegrationViolationException("Pessoa possui ordens de serviço e não pode ser deletado");
        }
        repository.deleteById(id);
    }
}
