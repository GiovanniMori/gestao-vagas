package org.example.gestao_vagas.modules.candidate.useCases;

import org.example.gestao_vagas.modules.candidate.CandidateEntity;
import org.example.gestao_vagas.modules.candidate.CandidateRepository;
import org.example.gestao_vagas.exceptions.UserFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateCandidateUseCase {
    @Autowired
    private CandidateRepository candidateRepository;

    public CandidateEntity execute(CandidateEntity candidateEntity) {
        this.candidateRepository.findByUsernameOrEmail(candidateEntity.getUsername(),
                candidateEntity.getEmail()).ifPresent(user -> {
            throw new UserFoundException("User already exists with username or email: " + user.getUsername());
        });

        return this.candidateRepository.save(candidateEntity);
    }
}