package com.example.demo.control;

import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeSvc;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/emps")
public class EmployeeController {

    private static final Logger logger = LogManager.getLogger(EmployeeController.class);

    private final EmployeeSvc employeeSvc;

    @Autowired
    public EmployeeController(EmployeeSvc employeeSvc) {
        this.employeeSvc = employeeSvc;
    }

    @GetMapping("/list")
    public String index(Model model) {

        model.addAttribute("emps", employeeSvc.getAllSorted());

        return "list";
    }

    @GetMapping("/edit")
    public String edit(Model model, @RequestParam("id") int id) {

        Employee emp = null;
        if(id != 0) {
            try {
                emp = employeeSvc.getById(id);
            } catch(Exception e) {
                logger.error(e.getMessage(), e);
            }
        }
        if(emp == null) {
            emp = new Employee();
        }
        model.addAttribute("emp", emp);

        return "edit";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("emp") Employee emp) {

        if(emp.getId() == 0) {
            employeeSvc.add(emp);
        } else {
            employeeSvc.update(emp);
        }
        return "redirect:/emps/list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") int id) {
        try {
            employeeSvc.delete(id);
        } catch(Exception e) {
            logger.error(e.getMessage(), e);
        }
        return "redirect:/emps/list";
    }
}
