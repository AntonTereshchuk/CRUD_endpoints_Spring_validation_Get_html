package com.example.map_elements_reading_on_web_server;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HtmlController {

    @GetMapping("/getHtml")
    public String getHtml() {
        return "index.html";
    }

}
