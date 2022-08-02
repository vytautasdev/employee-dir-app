package com.vytautasdev.employeedirapp.controller;

import com.vytautasdev.employeedirapp.entity.Employee;
import com.vytautasdev.employeedirapp.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // add mapping for "/list"
    @GetMapping("/list")
    public String listEmployees(Model model) {

        // get employees from db
        var employeesList = employeeService.findAll();

        model.addAttribute("employees", employeesList);

        return "employees/list-employees";
    }

    // add mapping for "/showFormForAdd"
    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model) {

        // create model attribute to bind form data
        var employee = new Employee();

        model.addAttribute("employee", employee);

        return "employees/employee-form";
    }

    // add mapping for "/showFormForUpdate"
    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("employeeId") int id, Model model) {

        // get the employee from the service
        var employee = employeeService.findById(id);

        // set employee as a model attribute to pre-populate the form
        model.addAttribute("employee", employee);

        // send over to the form
        return "employees/employee-form";
    }

    // add mapping for "/save"
    @PostMapping("/save")
    public String saveEmployee(@Valid @ModelAttribute("employee") Employee employee, Errors errors) {

        if (errors.getErrorCount() > 0) {
            return "employees/employee-form";
        } else {

            // save the employee
            employeeService.save(employee);

            // use a redirect to prevent duplicate submissions
            return "redirect:/employees/list";
        }
    }

    // add mapping for "/delete"
    @GetMapping("/delete")
    public String deleteEmployee(@RequestParam("employeeId") int id) {

        // delete the employee
        employeeService.deleteById(id);

        // use a redirect to prevent duplicate submissions
        return "redirect:/employees/list";
    }


    @GetMapping("/search")
    public String search(@RequestParam("employeeName") String name,
                         Model model) {

        // search employee by name
        List<Employee> employee = employeeService.searchBy(name);

        // add to the spring model
        model.addAttribute("employees", employee);

        // send to /employees/list
        return "/employees/list-employees";
    }
}
