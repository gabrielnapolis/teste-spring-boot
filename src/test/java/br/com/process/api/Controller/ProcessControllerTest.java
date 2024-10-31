package br.com.process.api.Controller;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import br.com.process.api.Model.ProcessModel;
import br.com.process.api.Repository.ProcessRepository;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Collections;
import java.util.Optional;

@WebMvcTest(ProcessController.class)
public class ProcessControllerTest {
     @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProcessRepository processRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void saveProcess_ShouldReturnOK() throws Exception {
        ProcessModel process = new ProcessModel("0001234-56.2024.8.26.0000", "João Teste");

        when(processRepository.existsByProcessNumber(process.getProcessNumber())).thenReturn(false);

        mockMvc.perform(post("/api/process")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(process)))
                .andExpect(status().isCreated())
                .andExpect(content().string("Processo n° 0001234-56.2024.8.26.0000 salvo com sucesso!"));
    }

    @Test
    void saveProcess_WhenProcessNumberExists_ShouldReturnConflict() throws Exception {
        ProcessModel process = new ProcessModel("0001234-56.2024.8.26.0000", "João Teste");

        when(processRepository.existsByProcessNumber(process.getProcessNumber())).thenReturn(true);

        mockMvc.perform(post("/api/process")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(process)))
                .andExpect(status().isConflict())
                .andExpect(content().string("Processo n° 0001234-56.2024.8.26.0000 já cadastrado."));
    }   

    @Test
    void getProcess_ShouldReturnListOfProcesses() throws Exception {
        ProcessModel process = new ProcessModel("0001234-56.2024.8.26.0000", "João Teste");

        when(processRepository.findAll()).thenReturn(Collections.singletonList(process));

        mockMvc.perform(get("/api/process"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].processNumber").value(process.getProcessNumber()))
                .andExpect(jsonPath("$[0].defendantName").value(process.getDefendantName()));
    }

    @Test
    void deleteProcess_WhenProcessExists_ShouldReturnOk() throws Exception {
        ProcessModel process = new ProcessModel("0001234-56.2024.8.26.0000", "João Teste");

        when(processRepository.findByProcessNumber("0001234-56.2024.8.26.0000")).thenReturn(process);

        mockMvc.perform(delete("/api/process/0001234-56.2024.8.26.0000"))
                .andExpect(status().isOk())
                .andExpect(content().string("Processo n° 0001234-56.2024.8.26.0000 excluído com sucesso."));
    }

    @Test
    void deleteProcess_WhenProcessNotExists_ShouldReturnConflit() throws Exception {

        when(processRepository.findByProcessNumber("0001234-56.2024.8.26.0000")).thenReturn(null);

        mockMvc.perform(delete("/api/process/0001234-56.2024.8.26.0000"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Processo n° 0001234-56.2024.8.26.0000 não encontrado."));
    }

    @Test
    void edit_WhenProcessExists_ShouldReturnUpdatedProcess() throws Exception {
        ProcessModel process = new ProcessModel("0001234-56.2024.8.26.0000", "João Teste");
        process.setId(1L);

        when(processRepository.findById(1L)).thenReturn(Optional.of(process));
        when(processRepository.save(any(ProcessModel.class))).thenReturn(process);

        mockMvc.perform(put("/api/process")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(process)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.processNumber").value("0001234-56.2024.8.26.0000"))
                .andExpect(jsonPath("$.defendantName").value("João Teste"));
    }
}
