package com.spring.boot.web.controller;

import java.util.Calendar;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.boot.web.employee.entity.Employee;
import com.spring.boot.web.employee.repo.EmployeeRepository;


@Controller
@RequestMapping("/registrationPage")
public class EmployeeController {

	@Autowired
	EmployeeRepository repository;

	@GetMapping
	public String showRegistrationPage(Employee employee) {

		return "userReg.html";
	}

	/*
	 * @RequestMapping("/") public String showHomePage(@RequestParam(name="name",
	 * required=false, defaultValue="World") String name, Model model) {
	 * model.addAttribute("name", name); return "index"; }
	 */

	@GetMapping("/all")
	public @ResponseBody Iterable<Employee> getEmployees() {
		return repository.findAll();
	}

	/*
	 * @PostMapping public @ResponseBody Employee create(@RequestBody Employee
	 * employee) { return repository.save(employee); }
	 */

	@PostMapping
	public String registerEmployee(@ModelAttribute("employee") @RequestBody @Valid Employee employee,
			BindingResult bindingResult, ModelMap model, RedirectAttributes redirectAttributes) {

		redirectAttributes.addFlashAttribute("message", "Failed saving employee");
		redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
		if (bindingResult.hasErrors()) {
			return "userReg.html";
		}
		String uid;
		Calendar c = Calendar.getInstance();
		c.setTime(employee.getBirthday());

		uid = (employee.getLastName().length() < 2 ? employee.getLastName()
				: employee.getLastName().substring(0, 2).toUpperCase())
				+ (!employee.getMiddleName().isBlank()
						? (employee.getMiddleName().length() < 2 ? employee.getMiddleName()
								: employee.getMiddleName().substring(0, 2).toUpperCase())
						: "AA")
				+ (employee.getFirstName().length() < 2 ? employee.getFirstName()
						: employee.getFirstName().substring(0, 2).toUpperCase())
				+ (Integer.toString(c.get(Calendar.YEAR))) + (Integer.toString(c.get(Calendar.MONTH) + 1))
				+ (Integer.toString(c.get(Calendar.DAY_OF_MONTH)));

		employee.setUid(uid);
		
		Employee emp = repository.findByUid(uid);
		if (emp != null) {
			redirectAttributes.addFlashAttribute("message", "Employee already exists");
			redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
			return "redirect:/registrationPage";
		}
		Employee result = repository.save(employee);
		redirectAttributes.addFlashAttribute("message",
				String.format("Employee \"%s\" successfully saved.", result.getFirstName()));
		redirectAttributes.addFlashAttribute("alertClass", "alert-success");

		model.addAttribute("employee", new Employee());
		return "redirect:/registrationPage";
	}

	/*
	 * @PutMapping public Employee update(@RequestBody Employee employee) { return
	 * repository.save(employee); }
	 */

	@GetMapping("/{id}")
	public Employee getEmployee(@PathVariable("id") Long id) {
		return repository.findById(id).orElse(null);
	}

	@DeleteMapping("/delete/{id}")
	public void deleteEmployee(@PathVariable("id") Long id) {
		repository.deleteById(id);
	}

}
