import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { CompanyDimension } from 'src/app/models/CompanyDimension';
import { Dimension } from 'src/app/models/Dimension';
import { CompanyDimensionService } from 'src/app/services/company-dimension.service';
import { SharedServiceService } from 'src/app/services/shared-service.service';
import { ViewComponent } from '../view/view.component';



@Component({
  selector: 'app-add-company-dimension',
  templateUrl: './add-company-dimension.component.html',
  styleUrls: ['./add-company-dimension.component.css']
})
export class AddCompanyDimensionComponent implements OnInit {
  
  companyDimension : CompanyDimension;

  dimensionForm!: FormGroup;

  constructor(private fb: FormBuilder,private service:CompanyDimensionService, 
    private router:Router,private ref:MatDialogRef<AddCompanyDimensionComponent>,private sharedService:SharedServiceService) { }

  ngOnInit() {
    this.createDimensionForm();
  }

  createDimensionForm() {
    this.dimensionForm = this.fb.group({
      companyName: ['', [Validators.required, this.noNumbersValidator]],
      dimensionName: ['', [Validators.required, this.noNumbersValidator]],
      dimensionValue: ['', [Validators.required, Validators.pattern(/^\d+(\.\d{1,2})?$/),Validators.min(0), Validators.max(100)]]
    });
  }


  submit() {
    if (this.dimensionForm.valid) {

      const formData = this.dimensionForm.value; // Get the form values as an object

      // Access the individual form fields using the form control names
      const companyName = formData.companyName;
      const dimensionName = formData.dimensionName;
      const dimensionValue = formData.dimensionValue;
      // Perform form submission here
      console.log(this.dimensionForm.value);
      // Reset the form after successful submission
      this.dimensionForm.reset();
      this.saveCompanyDimension(companyName,dimensionName,dimensionValue);

    } else {
      // Mark all fields as touched to display validation errors
      this.dimensionForm.markAllAsTouched();
    }
  }

  noNumbersValidator(control: AbstractControl): { [key: string]: any } | null {
    const containsNumber = /\d/.test(control.value);
    return containsNumber ? { 'containsNumber': true } : null;
  }


  saveCompanyDimension(cName: string, dName: string, dValue: number){

    

  if(!this.companyDimension){
    this.companyDimension= new CompanyDimension(cName);
  }
      
    
    
    console.log(dName+' '+dValue);

    let dimensions = new Dimension(dName,dValue);
    this.companyDimension.addDimensions(dimensions);

    this.service.addCompanyDimension(this.companyDimension).subscribe(data=>{
      console.log(this.companyDimension);
      console.log(data);
      this.ref.close();
     window.location.reload();
    },error=>console.error(error));

  }

 

  
}
