import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, FormControl } from '@angular/forms';
import { CompanyDimensionService } from 'src/app/services/company-dimension.service';

@Component({
  selector: 'app-add-dimension-weight',
  templateUrl: './add-dimension-weight.component.html',
  styleUrls: ['./add-dimension-weight.component.css']
})
export class AddDimensionWeightComponent implements OnInit {

  companyDimension: any;
  // items = ['Item 1', 'Item 2', 'Item 3', 'Item 4', 'Item 5'];
  items: string[] = [];
  selectedItems: string[] = [];
  formGroup: FormGroup;
  selectedItemsWithWeights: { item: string; weight: number }[] = [];

  constructor(private formBuilder: FormBuilder, private companyDimensionService: CompanyDimensionService) { }


  ngOnInit(): void {

    let cds = this.companyDimensionService.getCompanyDimension();
    cds.subscribe((data) => {
      this.companyDimension = data;
      console.log(this.companyDimension);
      for (const val of this.companyDimension[0].dimensions) {
        this.items.push(val.dimensionName);
        console.log(val.dimensionName);
      }

    });

  }


  onSubmit() {

  }



}
