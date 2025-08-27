package com.uninter.gerenciadortarefas.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity // Anotação que informa ao Spring que esta classe é uma entidade do banco
public class Tarefa {

    @Id // Marca o campo como a chave primária da tabela
    @GeneratedValue(strategy = GenerationType.IDENTITY) // O ID será gerado automaticamente pelo banco
    private Long id;

    private String nome; 
    private String dataEntrega; 
    private String responsavel; 
    
    // Getters e Setters (necessários para o Spring/JPA)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(String dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }
}