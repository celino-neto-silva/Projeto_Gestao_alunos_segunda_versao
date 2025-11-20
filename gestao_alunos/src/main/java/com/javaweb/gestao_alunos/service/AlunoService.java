package com.javaweb.gestao_alunos.service;

import com.javaweb.gestao_alunos.models.Aluno;
import com.javaweb.gestao_alunos.repository.AlunoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlunoService {
    private final List<Aluno> alunos = new ArrayList<>();
    private final AlunoRepository alunoRepository;

    public AlunoService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    public List<Aluno> listar(){
        return alunoRepository.findAll();
    }

    public Aluno salvar(Aluno aluno){
        return alunoRepository.save(aluno);
    }

    public Aluno listarPorId(Long id){
        return alunoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado!"));
    }

    public List<Aluno> listarPorId(List<Long> alunosIds){
        return alunoRepository.findAllById(alunosIds);
    }

    public void remover(Long id){
        alunoRepository.deleteById(id);
    }
}
