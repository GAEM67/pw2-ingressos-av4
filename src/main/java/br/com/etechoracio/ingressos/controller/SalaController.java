package br.com.etechoracio.ingressos.controller;

import br.com.etechoracio.ingressos.entity.Sala;
import br.com.etechoracio.ingressos.repository.SalaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/salas")
public class SalaController {

    @Autowired
    private SalaRepository salaRepository;

    private List<Long> idsExistentes = new ArrayList<>(List.of(1L, 2L, 3L, 4L, 5L));

    private Long gerarIdAleatorio() {
        return new Random().nextLong(5) + 1;
    }

    @GetMapping
    public List<Sala> listar() {
        return salaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sala> buscarPorId(@PathVariable Long id) {
        var sala = salaRepository.findById(id);
        if (sala.isPresent()) {
            return ResponseEntity.ok(sala.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Sala> cadastrar(@RequestBody Sala sala) {
        var novaSala = salaRepository.save(sala);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaSala);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sala> atualizar(@RequestBody Sala sala, @PathVariable Long id) {
        var existe = salaRepository.findById(id);
        if (existe.isPresent()) {
            sala.setId(id);
            var salaAtualizada = salaRepository.save(sala);
            return ResponseEntity.ok(salaAtualizada);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        var existe = salaRepository.findById(id);
        if (existe.isPresent()) {
            salaRepository.delete(existe.get());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}