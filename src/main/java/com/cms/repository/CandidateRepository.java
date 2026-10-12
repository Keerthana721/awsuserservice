package com.cms.repository;

import com.cms.model.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long>, JpaSpecificationExecutor<Candidate> {
    List<Candidate> findByNameContainingIgnoreCase(String name);
    List<Candidate> findByPrimaryTechnologyAndStatus(String technology, String status);
    List<Candidate> findByPrimaryTechnology(String technology);
    List<Candidate> findByStatus(String status);
}
