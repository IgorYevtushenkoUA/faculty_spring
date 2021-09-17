package com.example.faculty.controller;

import com.example.faculty.entety.Student;
import com.example.faculty.enums.ROLE;
import com.example.faculty.service.RoleService;
import com.example.faculty.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @GetMapping("/register")
    public String registrationGet(Model model) {
        model.addAttribute("userForm", new Student());
        return "register";
    }

    @PostMapping("/register")
    public String registrationPost(@ModelAttribute("userForm") Student userForm, BindingResult bindingResult, Model model) {
        userForm.setEnable(true);

        if (bindingResult.hasErrors())
            return "register";

        if (!userService.save(userForm, roleService.findByName(ROLE.ROLE_STUDENT.name()))) {
            model.addAttribute("userError", "Registration error");
            return "register";
        }
        return "redirect:/";
    }

}
