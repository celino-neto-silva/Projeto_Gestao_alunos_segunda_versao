package com.javaweb.gestao_alunos.service;

import com.javaweb.gestao_alunos.models.UnidadeCurricular;
import com.javaweb.gestao_alunos.repository.UnidadeCurricularRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnidadeCurricularService {
    private final UnidadeCurricularRepository unidadeCurricularRepository;

    public UnidadeCurricularService(UnidadeCurricularRepository unidadeCurricularRepository) {
        this.unidadeCurricularRepository = unidadeCurricularRepository;
    }

    public List<UnidadeCurricular> listar(){
        return unidadeCurricularRepository.findAll();
    }

    public UnidadeCurricular salvar(UnidadeCurricular unidadeCurricular){
        return unidadeCurricularRepository.save(unidadeCurricular);
    }

    public UnidadeCurricular listarPorId(Long id){
        return unidadeCurricularRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Unidade Curricular não encontrada!"));
    }

    public List<UnidadeCurricular> listarPorId(List<Long> unidadescurricularesIds){
        return unidadeCurricularRepository.findAllById(unidadescurricularesIds);
    }

    public void remover(Long id){
        unidadeCurricularRepository.deleteById(id);
    }
}
