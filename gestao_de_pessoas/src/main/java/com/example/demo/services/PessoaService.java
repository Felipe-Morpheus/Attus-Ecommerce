package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Pessoa;
import com.example.demo.repositories.PessoaRepository;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    // Método para listar todas as pessoas
    public List<Pessoa> listarPessoas() {
        return pessoaRepository.findAll();
    }

    // Método para obter uma pessoa por ID
    public Pessoa obterPessoaPorId(Long id) {
        Optional<Pessoa> pessoaOptional = pessoaRepository.findById(id);
        return pessoaOptional.orElse(null);
    }

    // Método para criar uma nova pessoa
    public Pessoa criarPessoa(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    // Método para atualizar uma pessoa existente
    public Pessoa atualizarPessoa(Long id, Pessoa pessoaAtualizada) {
        Optional<Pessoa> pessoaOptional = pessoaRepository.findById(id);
        if (pessoaOptional.isPresent()) {
            Pessoa pessoaExistente = pessoaOptional.get();
            pessoaExistente.setNome(pessoaAtualizada.getNome());
            pessoaExistente.setSobrenome(pessoaAtualizada.getSobrenome());
            pessoaExistente.setEmail(pessoaAtualizada.getEmail());
            // Atualize outros campos, se necessário
            return pessoaRepository.save(pessoaExistente);
        } else {
            return null;
        }
    }

    // Método para excluir uma pessoa por ID
    public boolean excluirPessoa(Long id) {
        Optional<Pessoa> pessoaOptional = pessoaRepository.findById(id);
        if (pessoaOptional.isPresent()) {
            pessoaRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}