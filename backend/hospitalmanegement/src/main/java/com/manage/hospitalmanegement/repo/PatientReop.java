package com.manage.hospitalmanegement.repo;

import com.manage.hospitalmanegement.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatientReop extends JpaRepository<Patient, Long> {
    void deletePatientById(Long id);

//    void deletePatientByLname(String lname);

    Optional<Patient> findPatientById(Long id);
}
