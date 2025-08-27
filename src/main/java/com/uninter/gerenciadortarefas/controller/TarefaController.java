package com.uninter.gerenciadortarefas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.uninter.gerenciadortarefas.model.Tarefa;
import com.uninter.gerenciadortarefas.repository.TarefaRepository;

import java.util.List;
import java.util.Optional;

@RestController 
@RequestMapping("/tarefas") // Mapeia todas as requisições que começam com /tarefas para este controlador
public class TarefaController {

    @Autowired // Injeção de dependência: o Spring gerenciará a instância do repositório
    private TarefaRepository tarefaRepository;

    // Endpoint para CRIAR uma nova tarefa (HTTP POST) [cite: 12]
    @PostMapping
    public ResponseEntity<Tarefa> criarTarefa(@RequestBody Tarefa tarefa) {
        Tarefa novaTarefa = tarefaRepository.save(tarefa);
        return new ResponseEntity<>(novaTarefa, HttpStatus.CREATED);
    }

    // Endpoint para CONSULTAR TODAS as tarefas (HTTP GET) [cite: 16]
    @GetMapping
    public ResponseEntity<List<Tarefa>> listarTarefas() {
        List<Tarefa> tarefas = tarefaRepository.findAll();
        return new ResponseEntity<>(tarefas, HttpStatus.OK);
    }

    // Endpoint para CONSULTAR uma tarefa por ID (HTTP GET) [cite: 17]
    @GetMapping("/{id}")
    public ResponseEntity<Tarefa> buscarTarefaPorId(@PathVariable Long id) {
        Optional<Tarefa> tarefa = tarefaRepository.findById(id);
        return tarefa.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                     .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Endpoint para ATUALIZAR uma tarefa (HTTP PUT) [cite: 18]
    @PutMapping("/{id}")
    public ResponseEntity<Tarefa> atualizarTarefa(@PathVariable Long id, @RequestBody Tarefa tarefaDetalhes) {
        return tarefaRepository.findById(id)
            .map(tarefa -> {
                tarefa.setNome(tarefaDetalhes.getNome());
                tarefa.setDataEntrega(tarefaDetalhes.getDataEntrega());
                tarefa.setResponsavel(tarefaDetalhes.getResponsavel());
                Tarefa tarefaAtualizada = tarefaRepository.save(tarefa);
                return new ResponseEntity<>(tarefaAtualizada, HttpStatus.OK);
            })
            .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Endpoint para REMOVER uma tarefa (HTTP DELETE) [cite: 20]
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTarefa(@PathVariable Long id) {
        return tarefaRepository.findById(id)
            .map(tarefa -> {
                tarefaRepository.delete(tarefa);
                return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
            })
            .orElseGet(() -> new ResponseEntity<Void>(HttpStatus.NOT_FOUND));
    }
}