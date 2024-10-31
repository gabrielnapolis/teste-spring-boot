package br.com.process.api.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.process.api.Model.ProcessModel;
import br.com.process.api.Repository.ProcessRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class ProcessController {

    @Autowired
    private ProcessRepository processRepository;

    @PostMapping("/process")
    public ResponseEntity<String> saveProcess(@RequestBody @Valid ProcessModel processDto) {
        if (processRepository.existsByProcessNumber(processDto.getProcessNumber())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Processo n° " + processDto.getProcessNumber() + " já cadastrado.");
        }
        ProcessModel process = new ProcessModel();
        process.setProcessNumber(processDto.getProcessNumber());
        processRepository.save(process);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Processo " + processDto.getProcessNumber() + " salvo com sucesso.");
    }
}
