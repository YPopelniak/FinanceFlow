package com.cydeo.controller;

import com.cydeo.model.Customer;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.swing.plaf.nimbus.State;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("customer", new Customer());
        List<String> stateList = Arrays.asList("Alaska", "Illinois","Michigan","Texas","New York");

        model.addAttribute("stateList",stateList);
        return "customer";
    }

    @PostMapping("/confirmation")
    public String confirm(@Valid @ModelAttribute("customer")Customer customer, BindingResult bindingResult, Model model){

        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(error -> System.out.println("Validation Error: " + error.getDefaultMessage()));


            List<String> stateList = Arrays.asList("Alaska", "Illinois","Michigan","Texas","New York");

            model.addAttribute("stateList",stateList);

            return "customer";
        }
        model.addAttribute("customer",customer);

        System.out.println(customer);
        return "confirmation";
    }
}
