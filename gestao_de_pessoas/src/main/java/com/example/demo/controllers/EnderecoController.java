package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.models.Endereco;
import com.example.demo.repositories.EnderecoRepository;

@RestController
@RequestMapping("/api/enderecos")
public class EnderecoController {

    @Autowired
    private EnderecoRepository enderecoRepository;

    // Endpoint para obter todos os endereços
    @GetMapping("/")
    public Iterable<Endereco> getAllEnderecos() {
        return enderecoRepository.findAll();
    }

    // Endpoint para obter um endereço pelo ID
    @GetMapping("/{id}")
    public Endereco getEnderecoById(@PathVariable Long id) {
        return enderecoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Endereco not found with id " + id));
    }

    // Endpoint para criar um novo endereço
    @PostMapping("/")
    public Endereco createEndereco(@RequestBody Endereco endereco) {
        return enderecoRepository.save(endereco);
    }

    // Endpoint para atualizar um endereço existente
    @PutMapping("/{id}")
    public Endereco updateEndereco(@PathVariable Long id, @RequestBody Endereco enderecoDetails) {
        Endereco endereco = enderecoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Endereco not found with id " + id));

        endereco.setLogradouro(enderecoDetails.getLogradouro());
        endereco.setCep(enderecoDetails.getCep());
        endereco.setNumero(enderecoDetails.getNumero());
        endereco.setCidade(enderecoDetails.getCidade());
        endereco.setEstado(enderecoDetails.getEstado());
        endereco.setPrincipal(enderecoDetails.isPrincipal());
        endereco.setPessoa(enderecoDetails.getPessoa());

        return enderecoRepository.save(endereco);
    }

    // Endpoint para excluir um endereço pelo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEndereco(@PathVariable Long id) {
        Endereco endereco = enderecoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Endereco not found with id " + id));

        enderecoRepository.delete(endereco);

        return ResponseEntity.ok().build();
    }
}
