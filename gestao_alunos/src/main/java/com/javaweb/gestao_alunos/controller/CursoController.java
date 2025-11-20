package com.javaweb.gestao_alunos.controller;

import com.javaweb.gestao_alunos.models.Curso;
import com.javaweb.gestao_alunos.repository.CursoRepository;
import com.javaweb.gestao_alunos.service.CursoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cursos")
public class CursoController {
    private final CursoService cursoService;

    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @GetMapping
    public String listarCurso(Model model){
        List<Curso> cursos = cursoService.listar();
        model.addAttribute("cursos", cursos);
        model.addAttribute("novoCurso", new Curso());
        return "cursos";
    }

    @PostMapping
    public String criarCurso(@ModelAttribute("novoCurso") Curso novoCurso){
        if(novoCurso.getNome() != null && !novoCurso.getNome().isBlank()){
            cursoService.salvar(novoCurso);
        }
        return "redirect:/cursos";
    }

    @PostMapping("/remover")
    public String removerCurso(@RequestParam("id") Long id){
        cursoService.remover(id);
        return "redirect:/cursos";
    }
}
