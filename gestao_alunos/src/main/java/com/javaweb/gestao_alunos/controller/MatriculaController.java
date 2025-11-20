package com.javaweb.gestao_alunos.controller;

import com.javaweb.gestao_alunos.models.*;
import com.javaweb.gestao_alunos.repository.AlunoRepository;
import com.javaweb.gestao_alunos.repository.EdicaoRepository;
import com.javaweb.gestao_alunos.repository.MatriculaRepository;
import com.javaweb.gestao_alunos.service.AlunoService;
import com.javaweb.gestao_alunos.service.EdicaoService;
import com.javaweb.gestao_alunos.service.MatriculaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/matriculas")
public class MatriculaController {
    private final MatriculaService matriculaService;
    private final AlunoService alunoService;
    private final EdicaoService edicaoService;

    public MatriculaController(MatriculaService matriculaService, AlunoService alunoService, EdicaoService edicaoService) {
        this.matriculaService = matriculaService;
        this.alunoService = alunoService;
        this.edicaoService = edicaoService;
    }

    @GetMapping
    public String listarMatriculas(Model model){
        List<Matricula> matriculas = matriculaService.listar();
        List<Aluno> alunos = alunoService.listar();
        List<Edicao> edicoes = edicaoService.listar();

        model.addAttribute("matriculas", matriculas);
        model.addAttribute("alunos", alunos);
        model.addAttribute("edicoes", edicoes);

        return "matriculas";
    }

    @PostMapping
    public String criarMatricula(@RequestParam("alunoId") Long alunoId,
                              @RequestParam("edicaoId") Long edicaoId){

        Aluno aluno = alunoService.listarPorId(alunoId);
        Edicao edicao = edicaoService.listarPorId(edicaoId);

        if (aluno == null || edicao == null) {
            return "redirect:/matriculas";
        }

        matriculaService.salvar(aluno, edicao);

        return "redirect:/matriculas";
    }

    @PostMapping("/remover")
    public String removerMatricula(@RequestParam("id") Long id){
        matriculaService.remover(id);
        return "redirect:/matriculas";
    }
}
