package com.andre.os.services;

import com.andre.os.dominio.Pessoa;
import com.andre.os.dominio.Tecnico;
import com.andre.os.dtos.TecnicoDTO;
import com.andre.os.repositories.PessoaRepository;
import com.andre.os.repositories.TecnicoRepository;
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
public class TecnicoService implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Autowired
    private TecnicoRepository repository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public Tecnico findById(Integer id) {
        Optional<Tecnico> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundExceptions(
                "Objeto não encontrado com o ID: " + id + " Tipo: " + Tecnico.class.getName()));
    }

    public List<Tecnico> findAll() {
        return repository.findAll();
    }

    public Tecnico create(TecnicoDTO objDTO) {
        if (findByCPF(objDTO.getCpf()) != null) {
            throw new DataIntegrationViolationException("CPF já cadastrado na base de dados!");
        }
        Tecnico newObj = new Tecnico(null, objDTO.getNome(), objDTO.getCpf(), objDTO.getTelefone());
        return repository.save(newObj);
    }

    public Tecnico update(Integer id, @Valid TecnicoDTO objDTO) {
        Tecnico oldObj = findById(id);
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
        Tecnico obj = findById(id);
        if (obj.getOsList().size() > 0) {
            throw new DataIntegrationViolationException("Técnico possui ordens de serviço e não pode ser deletado");
        }
        repository.deleteById(id);
    }
}
