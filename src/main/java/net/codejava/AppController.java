package net.codejava;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AppController {
	@Autowired
	private EmployeeService service;
	
	@RequestMapping("/")
	public String viewHomePage(Model model, @Param("keyword") String keyword) {
		List<Employee> listEmployees = service.listAll(keyword);
		model.addAttribute("listEmployees", listEmployees);
		model.addAttribute("keyword", keyword);
		
		return "index";
	}
	
	@RequestMapping("/new")
	public String showNewProductForm(Model model) {
		Employee employee = new Employee();
		model.addAttribute("employee", employee);
		
		return "new_employee";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveEmployee(@ModelAttribute("employee") Employee employee) {
		service.save(employee);
		
		return "redirect:/";
	}
	
	@RequestMapping("/update/{id}")
	public ModelAndView showEditEmployeeForm(@PathVariable(name = "id") Long id) {
		ModelAndView mav = new ModelAndView("update_employee");
		
		Employee employee = service.get(id);
		mav.addObject("employee", employee);
		
		return mav;
	}	
	
	@RequestMapping("/delete/{id}")
	public String deleteEmployee(@PathVariable(name = "id") Long id) {
		service.delete(id);
		
		return "redirect:/";
	}
}
