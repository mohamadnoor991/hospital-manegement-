import { Component, NgModule, OnInit } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { Patient } from './models/patient';
import { PatientService } from './services/patient.service';
import { response } from 'express';
import { error } from 'console';
import { HttpClient, HttpClientModule, HttpErrorResponse } from '@angular/common/http';
import { CommonModule, NgFor } from '@angular/common';
import { Form, FormsModule, NgForm } from '@angular/forms';
import { toUnicode } from 'punycode';

@Component({
  selector: 'app-root',
  exportAs: `addForm`,
  standalone: true,
  imports: [RouterOutlet, NgFor,FormsModule,HttpClientModule, CommonModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})

export class AppComponent implements OnInit {
  //Solve two problem in one by using ! non-null
  public patients!: Patient[];
  public editPatient: Patient | undefined | null;
  public deletePatient: Patient | null | undefined;

  constructor(private patientService: PatientService){}

  /**
   * ngInit() use to call the functions inside it when the component initialized.
   */
  ngOnInit(): void {
    this.getPatients();
  }

  /**
   * implement the logic of services in functions that can use 
   * the service to send and receive the data.
   */
  public getPatients(): void {
    // where this is an observable so will make request over the network, that will
    // take time, so we will subscribe to this Observable.
    this.patientService.getPatients().subscribe((response: Patient[]) =>{
      this.patients = response;
    }, (error: HttpErrorResponse) => {
      alert(error.message);
    });
  }

  public onAddPatient(addForm: NgForm): void {
    //this is the service making the call to the backend,
    // so we subscribe to notify when something coming back from backend(server)
    this.patientService.addPatient(addForm.value).subscribe((response: Patient)=>{
      console.log(response);
      this.getPatients();
      addForm.reset();
    },
      (error: HttpErrorResponse)=>{
        alert(error.message);
        addForm.reset();
      }
      );
      //this line to close the popup window automatically after we add the patient.
      document.getElementById('add-Patient-form')?.click();

  }

  public onUpdatePatient(patient: Patient): void {
    //this is the service making the call to the backend,
    // so we subscribe to notify when something coming back from backend(server)
    this.patientService.updatePatient(patient).subscribe((response: Patient)=>{
      console.log(response);
      this.getPatients();},
      (error: HttpErrorResponse)=>{
        alert(error.message)
      }
      );
  }

  public onDeletePatient(patientId: number): void {
        //this is the service making the call to the backend,
    // so we subscribe to notify when something coming back from backend(server)
    this.patientService.deletePatient(patientId).subscribe((response: void)=>{
      console.log(response);
      this.getPatients();},
      (error: HttpErrorResponse)=>{
        alert(error.message)
      }
      );

  }

  public searchPatients(key: string): void {
    console.log(key);
    const results: Patient[] = [] ;
    for (const patient of this.patients){
      if (patient.l_Name.toLowerCase().indexOf(key.toLowerCase()) !== -1)
      // || employee.email.toLowerCase().indexOf(key.toLowerCase()) !== -1
      // || employee.phone.toLowerCase().indexOf(key.toLowerCase()) !== -1
      // || employee.jobTitle.toLowerCase().indexOf(key.toLowerCase()) !== -1) 
      {
        results.push(patient);

      }
      this.patients = results;
      if (results.length === 0 || !key) {
        // set the patients array from the backend.
        this.getPatients();
      }

    }

  }

  //Build the logic of the `modal` to use with different parts
  //learn hoe to build an HTML code by using the ts.[create directive and assign properties]
  // Remember: you can assign the html & css properties by using (Js and Td)
  public onOpenModal(patient: Patient| null, mode: string): void {
    const container = document.getElementById('main-container');
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-toggle', 'modal');
    if (mode === 'add') {
      button.setAttribute('data-target', '#addPatientModal');
    }
    if (mode === 'edit') {
      this.editPatient = patient;
      button.setAttribute('data-target', '#updatePatientModal');
    }
    if (mode === 'delete') {
      this.deletePatient = patient;
      button.setAttribute('data-target', '#deletePatientModal');
      
    }
    container?.appendChild(button);
    button.click();    
  }

}
