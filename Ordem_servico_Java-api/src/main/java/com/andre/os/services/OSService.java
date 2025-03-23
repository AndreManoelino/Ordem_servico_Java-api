package com.andre.os.services;

import com.andre.os.dominio.Cliente;
import com.andre.os.dominio.OS;
import com.andre.os.dominio.Tecnico;
import com.andre.os.dominio.enums.Prioridade;
import com.andre.os.dominio.enums.Status;
import com.andre.os.dtos.OSDTO;
import com.andre.os.repositories.OSRepository;
import com.andre.os.services.exceptions.ObjectNotFoundExceptions;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OSService {

    @Autowired
    private OSRepository repo;
    @Autowired
    private TecnicoService tecnicoService;
    @Autowired
    private ClienteService clienteService;

    public OS findById(Integer id) {
        Optional<OS> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundExceptions(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + OS.class.getName()));
    }

    public List<OS> findAll() {
        return repo.findAll();
    }

    public OS create(@Valid OSDTO obj) {
        return fromDTO(obj);

    }
    private OS fromDTO(OSDTO obj) {
        OS newObj = new OS();
        newObj.setId(obj.getId());
        newObj.setObservacao(obj.getObservacao());
        newObj.setPrioridade(Prioridade.toEnum(obj.getPrioridade())); // Ele nos traz um integer ai pegamos somente o codigo de prioridade.
        newObj.setStatus(Status.toEnum(obj.getStatus()));

        Tecnico tec = tecnicoService.findById(obj.getTecnico());
        Cliente cli = clienteService.findById(obj.getCliente());

        newObj.setTecnico(tec);
        newObj.setCliente(cli);

        if(newObj.getStatus().getCod().equals(2)) {
            newObj.setDataFechamento(LocalDateTime.now());
        }
        return repo.save(newObj);

    }

    public OS update(@Valid OSDTO obj) {
        findById(obj.getId());
        return fromDTO(obj);
    }
}
