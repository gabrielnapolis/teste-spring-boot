package br.com.process.api.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.process.api.Model.ProcessModel;

@RestController
@RequestMapping("/api")
public class ProcessController {
    @GetMapping("")
    public String mensagem() {
        return "Hello World!";
    }
    
    @PostMapping("/process")
    public ProcessModel process(@RequestBody ProcessModel p){
        return p;
    }
}
