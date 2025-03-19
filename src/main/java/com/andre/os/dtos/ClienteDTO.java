package com.andre.os.dtos;

import com.andre.os.dominio.Cliente;
import com.andre.os.dominio.Tecnico;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

import java.io.Serial;
import java.io.Serializable;

public class ClienteDTO extends TecnicoDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Integer id;

    @NotEmpty(message = "Campo nome obrigátorio.....")
    @Size(min = 3, max = 100, message = "O nome deve conter no mínimo 3 caracteres iniciais.")
    private String nome;

    @CPF
    @NotEmpty(message = "Campo CPF obrigátorio....")
    private String cpf;

    @NotEmpty(message = "O campo Telefone é obrigátorio....")
    private String telefone;

    // Construtor padrão adicionado
    public ClienteDTO() {}

    // Construtor com o Cliente como parâmetro
    public ClienteDTO(Cliente byId) {
        this.id = byId.getId();
        this.nome = byId.getNome();
        this.cpf = byId.getCpf();
        this.telefone = byId.getTelefone();
    }

    // Construtor com o Tecnico como parâmetro
    public ClienteDTO(Tecnico obj) {
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.cpf = obj.getCpf();
        this.telefone = obj.getTelefone();
    }

    // Getters e Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
