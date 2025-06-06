package org.example.gestao_vagas.modules.company.useCases;

import org.example.gestao_vagas.exceptions.UserFoundException;
import org.example.gestao_vagas.modules.company.entities.CompanyEntity;
import org.example.gestao_vagas.modules.company.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateCompanyUseCase {
    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public CompanyEntity execute(CompanyEntity companyEntity) {
        this.companyRepository.findByUsernameOrEmail(companyEntity.getUsername(), companyEntity.getEmail()).ifPresent((user -> {
            throw new UserFoundException("User already exists with username or email: " + user.getUsername());
        }));
        var passwordEncoded = this.passwordEncoder.encode(companyEntity.getPassword());
        companyEntity.setPassword(passwordEncoded);

        return this.companyRepository.save(companyEntity);
    }
}
