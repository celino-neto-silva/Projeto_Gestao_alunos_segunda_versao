package com.javaweb.gestao_alunos.service;

import com.javaweb.gestao_alunos.models.Aluno;
import com.javaweb.gestao_alunos.models.Edicao;
import com.javaweb.gestao_alunos.models.Matricula;
import com.javaweb.gestao_alunos.repository.AlunoRepository;
import com.javaweb.gestao_alunos.repository.EdicaoRepository;
import com.javaweb.gestao_alunos.repository.MatriculaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatriculaService {
    private final MatriculaRepository matriculaRepository;
    private final AlunoRepository alunoRepository;
    private final EdicaoRepository edicaoRepository;

    public MatriculaService(MatriculaRepository matriculaRepository, AlunoRepository alunoRepository, EdicaoRepository edicaoRepository) {
        this.matriculaRepository = matriculaRepository;
        this.alunoRepository = alunoRepository;
        this.edicaoRepository = edicaoRepository;
    }

    public List<Matricula> listar(){
        return matriculaRepository.findAll();
    }

    public Boolean salvar(Aluno aluno, Edicao edicao){
        Matricula matricula = new Matricula();

        matricula.setAluno(aluno);
        matricula.setEdicao(edicao);

        matriculaRepository.save(matricula);

        return true;
    }

    public void remover(Long id){
        matriculaRepository.deleteById(id);
    }
}
