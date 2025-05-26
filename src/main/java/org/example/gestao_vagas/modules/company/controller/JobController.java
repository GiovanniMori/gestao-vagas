package org.example.gestao_vagas.modules.company.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.example.gestao_vagas.modules.company.dto.CreateJobDTO;
import org.example.gestao_vagas.modules.company.entities.JobEntity;
import org.example.gestao_vagas.modules.company.useCases.CreateJobUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/job")
public class JobController {
    @Autowired
    private CreateJobUseCase createJobUseCase;

    @PostMapping
    public ResponseEntity<JobEntity> create(@Valid
                                            @RequestBody CreateJobDTO createJobDTO,
                                            HttpServletRequest request
    ) {
        try {
            var companyId = request.getAttribute("company_id");
            var jobEntity = JobEntity.builder()
                    .benefits(createJobDTO.getBenefits())
                    .description(createJobDTO.getDescription())
                    .level(createJobDTO.getLevel()).build();
            JobEntity createdJob = this.createJobUseCase.execute(jobEntity);
            return ResponseEntity.ok(createdJob);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}