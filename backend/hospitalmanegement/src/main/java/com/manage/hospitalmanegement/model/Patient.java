package com.manage.hospitalmanegement.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class Patient implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;
    private String f_Name;
    private String l_Name;
    private String email;
    private String phone;
    private String doctorResponsible;
    private String imageUrl;
    @Column(nullable = false, updatable = false)
    private String patientCode;

    public Patient(Long id, String f_Name, String l_Name, String email, String phone, String doctorResponsible, String imageUrl, String patientCode) {
        this.id = id;
        this.f_Name = f_Name;
        this.l_Name = l_Name;
        this.email = email;
        this.phone = phone;
        this.doctorResponsible = doctorResponsible;
        this.imageUrl = imageUrl;
        this.patientCode = patientCode;
    }

    public Patient() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getF_Name() {
        return f_Name;
    }

    public void setF_Name(String f_Name) {
        this.f_Name = f_Name;
    }

    public String getL_Name() {
        return l_Name;
    }

    public void setL_Name(String l_Name) {
        this.l_Name = l_Name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDoctorResponsible() {
        return doctorResponsible;
    }

    public void setDoctorResponsible(String doctorResponsible) {
        this.doctorResponsible = doctorResponsible;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPatientCode() {
        return patientCode;
    }

    public void setPatientCode(String patientCode) {
        this.patientCode = patientCode;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", f_Name='" + f_Name + '\'' +
                ", l_Name='" + l_Name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", doctorResponsible='" + doctorResponsible + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", patientCode='" + patientCode + '\'' +
                '}';
    }
}
