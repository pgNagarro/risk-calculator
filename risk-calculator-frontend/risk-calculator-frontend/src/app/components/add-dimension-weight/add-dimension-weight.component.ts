import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, FormControl } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { DimensionWeight } from 'src/app/models/DimensionWeight';
import { DimensionWeights } from 'src/app/models/DimensionWeights';
import { CompanyDimensionService } from 'src/app/services/company-dimension.service';
import { DimensionWeightService } from 'src/app/services/dimension-weight.service';

@Component({
  selector: 'app-add-dimension-weight',
  templateUrl: './add-dimension-weight.component.html',
  styleUrls: ['./add-dimension-weight.component.css']
})
export class AddDimensionWeightComponent implements OnInit {

  companyDimension: any;
  dimensionWeight:any;
  // items = ['Item 1', 'Item 2', 'Item 3', 'Item 4', 'Item 5'];
  items: string[] = [];
  selectedItems: string[] = [];
  formGroup: FormGroup;
  weights: Record<string, number> = {};
  dimensionWeights:DimensionWeights;

  constructor(private formBuilder: FormBuilder, private companyDimensionService: CompanyDimensionService
    ,private dimensionWeightService:DimensionWeightService, private ref:MatDialogRef<AddDimensionWeightComponent>) { }


  ngOnInit(): void {
    this.dimensionWeights = new DimensionWeights();

    let cds = this.companyDimensionService.getCompanyDimension();
    let dws = this.dimensionWeightService.getDimensionWeight();

    cds.subscribe((data) => {
      this.companyDimension = data;
      console.log(this.companyDimension);
      for (const val of this.companyDimension[0].dimensions) {
        this.items.push(val.dimensionName);
        console.log(val.dimensionName);
      }

    });

    dws.subscribe((data) => {
      this.dimensionWeight = data;

      for(const val of this.dimensionWeight){
        this.selectedItems.push(val.dimension);
      }
    });

  }


  onSubmit() {

    const totalWeight = Object.values(this.weights).reduce((acc, weight) => acc + (weight || 0), 0);
    console.log(this.selectedItems);
    // Check if the totalWeight is equal to 100 before submitting
    if (totalWeight !== 100) {
      alert('The sum of all individual weights should be equal to 100.');
      return;
    }

    console.log(this.selectedItems);
    console.log(this.weights);
    
    for(const ele of this.selectedItems){
      
      let newDimensionWeight = new DimensionWeight(ele,this.getWeightForItem(ele));
      this.dimensionWeights.addDimensions(newDimensionWeight);
     
    }

    this.dimensionWeightService.addDimensionWeight(this.dimensionWeights).subscribe((data) => {
      console.log(this.dimensionWeights);
    });

      this.ref.close();
      window.location.reload();

  }

  getWeightForItem(item: string): number {
    return this.weights[item] || 0; // Return the weight or 0 if it doesn't exist
}

isValidWeight(weight: any): boolean {
  // Check if the weight is a whole number between 1 and 100
  return Number.isInteger(weight) && weight >= 1 && weight <= 100;
}


}
