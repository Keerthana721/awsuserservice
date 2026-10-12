package com.cms.controller;

import com.cms.commons.ApiResponse;
import com.cms.model.Candidate;
import com.cms.service.CandidateService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;


@Slf4j
@RestController
@RequestMapping("/api/candidates")
public class CandidateController {

    @Autowired
    private CandidateService candidateService;
    @GetMapping
    public List<Candidate> getAllCandidates(@RequestParam(required = false) String technology,
                                            @RequestParam(required = false) String status) {
        if (technology != null || status != null) {
            return candidateService.filterCandidates(technology, status);
        }
        return candidateService.getAllCandidates();
    }

    @GetMapping("/search")
    public List<Candidate> searchCandidates(@RequestParam String name) {
        return candidateService.searchCandidates(name);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Candidate> getCandidateById(@PathVariable Long id) {
        return candidateService.getCandidateById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Candidate>> create(
            @RequestBody Candidate candidate) {

        try {
            Candidate saved = candidateService.saveCandidate(candidate);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponse<>(
                            true,
                            "Candidate created successfully",
                            saved
                    ));

        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ApiResponse<Candidate>(
                            false,
                            "Candidate data conflicts with database constraints",
                            null
                    ));

        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<Candidate>(
                            false,
                            "Unable to create candidate",
                            null
                    ));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Candidate> updateCandidate(@PathVariable Long id, @RequestBody Candidate candidateDetails) {
        try {
            Candidate updatedCandidate = candidateService.updateCandidate(id, candidateDetails);
            return ResponseEntity.ok(updatedCandidate);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCandidate(@PathVariable Long id) {
        try {
            candidateService.deleteCandidate(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
