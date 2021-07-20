package br.edu.ifpe.jeffersonsilva.gestaoprofessor.controller;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.edu.ifpe.jeffersonsilva.gestaoprofessor.dao.ProfessorDAO;
import br.edu.ifpe.jeffersonsilva.gestaoprofessor.model.Professor;
import br.edu.ifpe.jeffersonsilva.gestaoprofessor.model.UnidadeFederacao;

@Controller
@RequestMapping("/professores")
public class ProfessorController {

	@Autowired
	ProfessorDAO professor;

	@PostMapping("/{id}/delete")
	public ModelAndView deleteProfessor(@PathVariable Long id) {
		int cod_matricula = (int) id.intValue();
		try {
			ProfessorDAO.deletarProfessores(cod_matricula);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ModelAndView("redirect:/professores");
	}

	@PostMapping("/{id}")
	public ModelAndView updateProfessor(@PathVariable Long id, @Valid Professor professor,
			BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			ModelAndView mv = new ModelAndView("professores/edit");
			mv.addObject("UnidadeFederacao", UnidadeFederacao.values());
			return mv;

		} else {
			int cod_matricula = (int) id.intValue();

			try {
				professor.setCod_matricula(cod_matricula);
				ProfessorDAO.alterarProfessores(professor);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return new ModelAndView("redirect:/professores");
		}
	}

	@GetMapping("/{id}/edit")
	public ModelAndView edit(@PathVariable Long id, Professor professor) {
		ModelAndView mv = new ModelAndView("professores/edit");
		int cod_matricula = (int) id.intValue();

		try {
			professor = ProfessorDAO.consultarProfessores(cod_matricula);

			if (professor != null) {
				mv.addObject("professor", professor);
				mv.addObject("UnidadeFederacao", UnidadeFederacao.values());
				mv.addObject("professorId", id);
				return mv;
			} else {
				return new ModelAndView("redirect:/professores");
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mv;
	}

	@PostMapping("")
	public ModelAndView createProfessor(@Valid Professor professor, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			ModelAndView mv = new ModelAndView("professores/new");
			mv.addObject("UnidadeFederacao", UnidadeFederacao.values());
			return mv;

		}
		try {

			ProfessorDAO.adiciona(professor);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new ModelAndView("redirect:/professores");
	}

	@GetMapping("/new")
	public ModelAndView novoProfessor() {
		ModelAndView mv = new ModelAndView("professores/new");
		mv.addObject("UnidadeFederacao", UnidadeFederacao.values());
		return mv;
	}

	@GetMapping("")
	public ModelAndView index() {
//		Professor professor1 = new Professor(1, "Jeff", "Rua Gaia", "Maraial", "PE");
//		Professor professor2 = new Professor(2, "Jeffim", "Rua Gaia2", "Maraial", "PE");
//		Professor professor3 = new Professor(3, "Jeffer", "Rua Gaia3", "Maraial", "PE");
//		Professor professor4 = new Professor(4, "Jefferson", "Rua Gaia3", "Maraial", "PE");
//
//		List<Professor> professores = Arrays.asList(professor1, professor2, professor3, professor4);

		List<Professor> professores = null;
		try {
			professores = professor.consultarTodosProfessores();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ModelAndView mv = new ModelAndView("professores/index");
		mv.addObject("ListaProfessores", professores);

		return mv;
	}
}