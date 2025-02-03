package com.manage.hospitalmanegement.service;

import com.manage.hospitalmanegement.exception.UserNotFoundException;
import com.manage.hospitalmanegement.model.Patient;
import com.manage.hospitalmanegement.repo.PatientReop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import java.util.List;
import java.util.UUID;

@Service
//@Transactional
public class PatientService {
    private final PatientReop patientReop;

    @Autowired
    public PatientService(PatientReop patientReop) {
        this.patientReop = patientReop;
    }

    public Patient addPatient(Patient patient){
        patient.setPatientCode(UUID.randomUUID().toString());
        return patientReop.save(patient);
    }

    public List<Patient> findAllPatients(){
        return patientReop.findAll();
    }

    public Patient updatePatient(Patient patient){
        return patientReop.save(patient);
    }

    public void deletePatient(Long id){
        patientReop.deletePatientById(id);
    }

    //Add by me to test
//    public void deletePatientBylname(String lName){
//        patientReop.deletePatientByLname(lName);
//    }

    public Patient findPatientById(Long id) {
        return patientReop.findPatientById(id).orElseThrow(()-> new UserNotFoundException("User with id" + id + "can not be found"));
    }
}
