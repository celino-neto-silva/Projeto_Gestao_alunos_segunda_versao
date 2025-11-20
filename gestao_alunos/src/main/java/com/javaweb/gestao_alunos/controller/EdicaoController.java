package com.javaweb.gestao_alunos.controller;

import com.javaweb.gestao_alunos.models.Curso;
import com.javaweb.gestao_alunos.models.Edicao;
import com.javaweb.gestao_alunos.models.UnidadeCurricular;
import com.javaweb.gestao_alunos.service.CursoService;
import com.javaweb.gestao_alunos.service.EdicaoService;
import com.javaweb.gestao_alunos.service.UnidadeCurricularService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/edicoes")
public class EdicaoController {
    private final EdicaoService edicaoService;
    private final CursoService cursoService;
    private final UnidadeCurricularService unidadeCurricularService;

    public EdicaoController(EdicaoService edicaoService, CursoService cursoService, UnidadeCurricularService unidadeCurricularService) {
        this.edicaoService = edicaoService;
        this.cursoService = cursoService;
        this.unidadeCurricularService = unidadeCurricularService;
    }

    @GetMapping
    public String listarEdicoes(Model model){
        List<Edicao> edicoes = edicaoService.listar();
        List<Curso> cursos = cursoService.listar();
        List<UnidadeCurricular> unidadescurriculares = unidadeCurricularService.listar();

        model.addAttribute("edicoes", edicoes);
        model.addAttribute("unidadescurriculares", unidadescurriculares);
        model.addAttribute("cursos", cursos);

        return "edicoes";
    }

    @PostMapping
    public String criarEdicao(@RequestParam("cursoId") Long cursoId,
                              @RequestParam("unidadecurricularId") Long unidadecurricularId,
                              @RequestParam String descricao) {

        Curso curso = cursoService.listarPorId(cursoId);
        UnidadeCurricular uc = unidadeCurricularService.listarPorId(unidadecurricularId);

        if (curso == null || uc == null) {
            return "redirect:/edicoes";
        }
        edicaoService.salvar(curso, uc, descricao);

        return "redirect:/edicoes";
    }

    @PostMapping("/remover")
    public String removerEdicao(@RequestParam("id") Long id){
        edicaoService.remover(id);
        return "redirect:/edicoes";
    }
}

