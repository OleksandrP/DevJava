package ua.controller.admin;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import ua.entity.Ingredient;
import ua.service.IngredientService;
import ua.validator.IngredientValidator;

@Controller
@RequestMapping("/admin/ingredient")
@SessionAttributes("ingredient")
public class IngredientController {

	@Autowired
	private IngredientService ingredientService;
	
	@InitBinder("ingredient")
	protected void bind(WebDataBinder binder){
		binder.setValidator(new IngredientValidator(ingredientService));
	}
	
	@ModelAttribute("ingredient")
	public Ingredient getForm(){
		return new Ingredient();
	}
	
	@GetMapping
	public String show(Model model){
		model.addAttribute("ingredients", ingredientService.findAll());
		return "admin-ingredient";
	}
	
	@GetMapping("/update/{id}")
	public String update(@PathVariable Long id, Model model){
		model.addAttribute("ingredient", ingredientService.findOne(id));
		return show(model);
	}
	
	@GetMapping("delete/{id}")
	public String delete(@PathVariable Long id){
		ingredientService.delete(id);
		return "redirect:/admin/ingredient";
	}
	
	@PostMapping
	public String save(@ModelAttribute("ingredient") @Valid Ingredient ingredient, BindingResult br, Model model, SessionStatus status){
		if(br.hasErrors()) return show(model);
		ingredientService.save(ingredient);
		status.setComplete();
		return "redirect:/admin/ingredient";
	}
}
