package com.andre.os.dominio;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Cliente extends Pessoa implements Serializable {
    private static final long serialVersionUID = 1L;

    @OneToMany(mappedBy = "cliente") // Corrigindo o relacionamento e erro de digitação
    private List<OS> osList = new ArrayList<>();

    public Cliente() {
        super();
    }

    public Cliente(Integer id, String nome, String cpf, String telefone) {
        super(id, nome, cpf, telefone);
    }

    public List<OS> getOsList() {
        return osList;
    }

    public void setOsList(List<OS> osList) {
        this.osList = osList;
    }
}
