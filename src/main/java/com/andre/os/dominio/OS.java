package com.andre.os.dominio;

import com.andre.os.dominio.enums.Prioridade;
import com.andre.os.dominio.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class OS {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataAbertura;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataFechamento;

    private Integer prioridade;
    private String observacao;
    private Integer status;

    @ManyToOne
    @JoinColumn(name = "tecnico_id") // Corrigido o nome da coluna
    private Tecnico tecnico;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    public OS() {
        super();
        this.dataAbertura = LocalDateTime.now();
        this.prioridade = Prioridade.BAIXA.getCod(); // Garantindo que não seja null
        this.status = Status.ABERTO.getCod(); // Garantindo que não seja null
    }

    public OS(Integer id, Prioridade prioridade, String observacao, Status status, Tecnico tecnico, Cliente cliente) {
        super();
        this.id = id;
        this.dataAbertura = LocalDateTime.now();
        this.prioridade = (prioridade != null) ? prioridade.getCod() : Prioridade.BAIXA.getCod();
        this.observacao = observacao;
        this.status = (status != null) ? status.getCod() : Status.ABERTO.getCod();
        this.tecnico = tecnico;
        this.cliente = cliente;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(LocalDateTime dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public LocalDateTime getDataFechamento() {
        return dataFechamento;
    }

    public void setDataFechamento(LocalDateTime dataFechamento) {
        this.dataFechamento = dataFechamento;
    }

    public Prioridade getPrioridade() {
        return Prioridade.toEnum(this.prioridade);
    }

    public void setPrioridade(Prioridade prioridade) {
        this.prioridade = prioridade.getCod();
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Status getStatus() {
        return Status.toEnum(this.status);
    }

    public void setStatus(Status status) {
        this.status = status.getCod();
    }

    public Tecnico getTecnico() {
        return tecnico;
    }

    public void setTecnico(Tecnico tecnico) {
        this.tecnico = tecnico;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        OS other = (OS) obj;
        return id != null && id.equals(other.id);
    }
}
