import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, Validators } from '@angular/forms';


@Component({
  selector: 'app-add-company-dimension',
  templateUrl: './add-company-dimension.component.html',
  styleUrls: ['./add-company-dimension.component.css']
})
export class AddCompanyDimensionComponent implements OnInit {

  dimensionForm!: FormGroup;

  constructor(private fb: FormBuilder) { }

  ngOnInit() {
    this.createDimensionForm();
  }

  createDimensionForm() {
    this.dimensionForm = this.fb.group({
      companyName: ['', [Validators.required, this.noNumbersValidator]],
      dimensionName: ['', [Validators.required, this.noNumbersValidator]],
      dimensionValue: ['', [Validators.required, Validators.pattern(/^\d+(\.\d{1,2})?$/)]]
    });
  }

  onFormSubmit() {
    if (this.dimensionForm.valid) {
      // Perform form submission here
      console.log(this.dimensionForm.value);
      // Reset the form after successful submission
      this.dimensionForm.reset();
    } else {
      // Mark all fields as touched to display validation errors
      this.dimensionForm.markAllAsTouched();
    }
  }

  noNumbersValidator(control: AbstractControl): { [key: string]: any } | null {
    const containsNumber = /\d/.test(control.value);
    return containsNumber ? { 'containsNumber': true } : null;
  }

}
