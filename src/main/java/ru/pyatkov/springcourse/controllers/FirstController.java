package ru.pyatkov.springcourse.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/first")
public class FirstController {
    @GetMapping("/hello")
    public String helloPage(@RequestParam(value = "name", required = false) String name,
                            @RequestParam(value = "surname", required = false) String surname,
                            Model model) {
        //System.out.println("В клиенте были переданы параметры " + name + " и " + surname);
        model.addAttribute("message", "В клиенте были переданы параметры " + name + " и " + surname);
        return "first/hello";
    }

    @GetMapping("/goodbye")
    public String goodbyePage() {
        return "first/goodbye";
    }

    @GetMapping("/calculator")
    public String calculate(@RequestParam(value = "a", required = false) int a,
                            @RequestParam(value = "b", required = false) int b,
                            @RequestParam(value = "action", required = false) String action,
                            Model model) {
        switch (action) {
            case "multiplication":
                model.addAttribute("result", a * b);
                return "first/calculator";
            case "addition":
                model.addAttribute("result", a + b);
                return "first/calculator";
            case "subtraction":
                model.addAttribute("result", a - b);
                return "first/calculator";
            case "division":
                model.addAttribute("result", a / b);
                return "first/calculator";
            default:
                return "first/calculator";
        }
    }
}
