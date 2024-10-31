package br.com.process.api.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.process.api.Model.ProcessModel;

@Repository
public interface ProcessRepository extends JpaRepository<ProcessModel, Long> {
    boolean existsByProcessNumber(String processNumber);
}