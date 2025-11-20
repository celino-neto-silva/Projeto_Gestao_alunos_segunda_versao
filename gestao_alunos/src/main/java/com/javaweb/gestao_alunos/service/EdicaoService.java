package com.javaweb.gestao_alunos.service;

import com.javaweb.gestao_alunos.models.Curso;
import com.javaweb.gestao_alunos.models.Edicao;
import com.javaweb.gestao_alunos.models.UnidadeCurricular;
import com.javaweb.gestao_alunos.repository.CursoRepository;
import com.javaweb.gestao_alunos.repository.EdicaoRepository;
import com.javaweb.gestao_alunos.repository.UnidadeCurricularRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EdicaoService {
    private final EdicaoRepository edicaoRepository;
    private final CursoRepository cursoRepository;
    private final UnidadeCurricularRepository unidadeCurricularRepository;

    public EdicaoService(EdicaoRepository edicaoRepository, CursoRepository cursoRepository, UnidadeCurricularRepository unidadeCurricularRepository) {
        this.edicaoRepository = edicaoRepository;
        this.cursoRepository = cursoRepository;
        this.unidadeCurricularRepository = unidadeCurricularRepository;
    }

    public List<Edicao> listar(){
        return edicaoRepository.findAll();
    }

    public Boolean salvar(Curso curso, UnidadeCurricular unidadeCurricular, String descricao){
        Edicao edicao = new Edicao();

        edicao.setCurso(curso);
        edicao.setUnidadecurricular(unidadeCurricular);
        edicao.setDescricao(descricao);

        edicaoRepository.save(edicao);

        return true;
    }

    public Edicao listarPorId(Long id){
        return edicaoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Edição não encontrada!"));
    }

    public List<Edicao> listarPorId(List<Long> edicoesIds){
        return edicaoRepository.findAllById(edicoesIds);
    }
    public void remover(Long id){
        edicaoRepository.deleteById(id);
    }
}
