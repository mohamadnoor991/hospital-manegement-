package com.manage.hospitalmanegement.controller;

import com.manage.hospitalmanegement.model.Patient;
import com.manage.hospitalmanegement.service.PatientService;
import jakarta.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patient")
//@Controller
public class PatientResource {
    private final PatientService patientService;

    public PatientResource(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/findall")
    public ResponseEntity<List<Patient>> getAllPatients() {
        List<Patient> patients = patientService.findAllPatients();
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable("id") Long id) {
        Patient patient = patientService.findPatientById(id);
        return new ResponseEntity<>(patient, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Patient> addPatient(@RequestBody Patient patient) {
        Patient newPatient = patientService.addPatient(patient);
        return  new ResponseEntity<>(patient, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Patient> updatePatient(@RequestBody Patient patient) {
        Patient updatePatient = patientService.updatePatient(patient);
        return  new ResponseEntity<>(patient, HttpStatus.CREATED);
    }

    @Transactional //for What ??
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletePatient(@PathVariable("id") Long id) {
         patientService.deletePatient(id);
        return  new ResponseEntity<>(HttpStatus.OK);
    }

//    @DeleteMapping("/delete/{lName}")
//    public ResponseEntity<?> deletePatient(@PathVariable("lName") String lName) {
//        patientService.deletePatientBylname(lName);
//        return  new ResponseEntity<>(HttpStatus.OK);
//    }
}
