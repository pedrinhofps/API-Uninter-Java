package com.uninter.gerenciadortarefas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uninter.gerenciadortarefas.model.Tarefa;

@Repository // Anotação que define a interface como um componente de repositório do Spring
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
    // JpaRepository<Tarefa, Long> significa que este repositório gerencia
    // a entidade 'Tarefa' e que o tipo da chave primária é 'Long'.
    // O Spring Data JPA implementará automaticamente os métodos básicos de CRUD.
}