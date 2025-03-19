package com.andre.os.services;


import com.andre.os.dominio.Cliente;
import com.andre.os.dominio.OS;
import com.andre.os.dominio.Tecnico;
import com.andre.os.dominio.enums.Prioridade;
import com.andre.os.dominio.enums.Status;
import com.andre.os.repositories.ClienteRepository;
import com.andre.os.repositories.OSRepository;
import com.andre.os.repositories.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DBServices {
    @Autowired
    TecnicoRepository tecnicoRepository;
    @Autowired
    ClienteRepository clienteRepository;
    @Autowired
    OSRepository osRepository;


    public void instanciaDB() {


        Tecnico t1 = new Tecnico(null, "Andre Manoelino", "144.785.300-84", "(031) 98888-4444");
        Tecnico t2 = new Tecnico(null, "Pedro Henrique", "641.760.040-88", "(031) 9 7774-2356");
        Cliente c1 = new Cliente(null, "Shopping do cidadão", "598.508.200-80", "(031) 97777-3333");
        Cliente c2 = new Cliente(null, "Valid","242.307.160-44","(031) 95555-5555");
        OS os1 = new OS(null, Prioridade.ALTA, "Teste de Create OS", Status.ENCERRADO, t1, c1);
        OS os2 = new OS(null, Prioridade.ALTA, "Teste de Create OS", Status.ENCERRADO, t1, c1);

        t1.getOsList().add(os1);
        c1.getOsList().add(os1);


        tecnicoRepository.saveAll(Arrays.asList(t1,t2));
        clienteRepository.saveAll(Arrays.asList(c1,c2));
        osRepository.saveAll(Arrays.asList(os1,os2));

    }
}
