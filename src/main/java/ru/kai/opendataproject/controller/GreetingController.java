package ru.kai.opendataproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kai.opendataproject.service.PointService;

@Controller
@RequiredArgsConstructor
public class GreetingController {
    private final PointService pointService;
    @GetMapping("/")
    public String greet(Model model) {
        return "index";
    }
}
