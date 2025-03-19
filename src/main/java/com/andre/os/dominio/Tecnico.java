package com.andre.os.dominio;

import com.andre.os.dtos.TecnicoDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.validation.Valid;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Tecnico extends Pessoa implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @OneToMany(mappedBy = "tecnico") // Corrigindo o relacionamento
    private List<OS> osList = new ArrayList<>();

    public Tecnico() {
        super();
    }

    public Tecnico(Integer id, String nome, String cpf, String telefone) {
        super(id, nome,cpf, telefone);  // Chama o construtor da superclasse Pessoa
        this.cpf = cpf;
    }

    @Valid
    private String cpf;

    public List<OS> getOsList() {
        return osList;
    }

    public void setOsList(List<OS> osList) {
        this.osList = osList;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
