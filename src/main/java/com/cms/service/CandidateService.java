package com.cms.service;

import com.cms.model.Candidate;
import com.cms.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CandidateService {

    @Autowired
    private CandidateRepository candidateRepository;

    public List<Candidate> getAllCandidates() {
        return candidateRepository.findAll();
    }

    public List<Candidate> searchCandidates(String name) {
        return candidateRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Candidate> filterCandidates(String technology, String status) {
        if (technology != null && !technology.isEmpty() && status != null && !status.isEmpty()) {
            return candidateRepository.findByPrimaryTechnologyAndStatus(technology, status);
        } else if (technology != null && !technology.isEmpty()) {
            return candidateRepository.findByPrimaryTechnology(technology);
        } else if (status != null && !status.isEmpty()) {
            return candidateRepository.findByStatus(status);
        }
        return getAllCandidates();
    }

    public Optional<Candidate> getCandidateById(Long id) {
        return candidateRepository.findById(id);
    }

    public Candidate saveCandidate(Candidate candidate) {
        return candidateRepository.save(candidate);
    }

    public Candidate updateCandidate(Long id, Candidate candidateDetails) {
        Candidate candidate = candidateRepository.findById(id).orElseThrow(() -> new RuntimeException("Candidate not found"));
        
        candidate.setName(candidateDetails.getName());
        candidate.setEmail(candidateDetails.getEmail());
        candidate.setPhone(candidateDetails.getPhone());
        candidate.setTotalExperience(candidateDetails.getTotalExperience());
        candidate.setRelevantExperience(candidateDetails.getRelevantExperience());
        candidate.setPrimaryTechnology(candidateDetails.getPrimaryTechnology());
        candidate.setSecondaryTechnology(candidateDetails.getSecondaryTechnology());
        candidate.setCurrentCompany(candidateDetails.getCurrentCompany());
        candidate.setCurrentSalary(candidateDetails.getCurrentSalary());
        candidate.setExpectedSalary(candidateDetails.getExpectedSalary());
        candidate.setNoticePeriod(candidateDetails.getNoticePeriod());
        candidate.setInterviewDate(candidateDetails.getInterviewDate());
        candidate.setInterviewTime(candidateDetails.getInterviewTime());
        candidate.setInterviewMode(candidateDetails.getInterviewMode());
        candidate.setInterviewerName(candidateDetails.getInterviewerName());
        candidate.setStatus(candidateDetails.getStatus());
        candidate.setRemarks(candidateDetails.getRemarks());
        
        return candidateRepository.save(candidate);
    }

    public void deleteCandidate(Long id) {
        Candidate candidate = candidateRepository.findById(id).orElseThrow(() -> new RuntimeException("Candidate not found"));
        candidateRepository.delete(candidate);
    }
}
