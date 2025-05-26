package org.example.gestao_vagas.modules.company.controller;

import jakarta.validation.Valid;
import org.example.gestao_vagas.exceptions.UserFoundException;
import org.example.gestao_vagas.modules.company.entities.CompanyEntity;
import org.example.gestao_vagas.modules.company.useCases.CreateCompanyUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    private CreateCompanyUseCase createCompanyUseCase;

    @PostMapping("/")
    public ResponseEntity<Object> create(
            @Valid
            @RequestBody CompanyEntity companyEntity
    ) {
        try {
            var result = createCompanyUseCase.execute(companyEntity);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());

        }
    }
}
