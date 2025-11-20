package com.javaweb.gestao_alunos.service;

import com.javaweb.gestao_alunos.models.Curso;
import com.javaweb.gestao_alunos.repository.CursoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoService {
    private final CursoRepository cursoRepository;

    public CursoService(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    public List<Curso> listar(){
        return cursoRepository.findAll();
    }

    public Curso salvar(Curso curso){
        return cursoRepository.save(curso);
    }

    public Curso listarPorId(Long id){
        return cursoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Curso não encontrado!"));
    }

    public List<Curso> listarPorId(List<Long> cursosIds){
        return cursoRepository.findAllById(cursosIds);
    }

    public void remover(Long id){
        cursoRepository.deleteById(id);
    }
}
