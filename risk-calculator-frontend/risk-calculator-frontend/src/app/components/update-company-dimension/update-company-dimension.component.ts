import { Component, Inject, OnInit } from '@angular/core';

import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ActivatedRoute, Router } from '@angular/router';
import { CompanyDimension } from 'src/app/models/CompanyDimension';
import { CompanyDimensionService } from 'src/app/services/company-dimension.service';


@Component({
  selector: 'app-update-company-dimension',
  templateUrl: './update-company-dimension.component.html',
  styleUrls: ['./update-company-dimension.component.css']
})
export class UpdateCompanyDimensionComponent implements OnInit {


  companyName:string;
  companyDimension: CompanyDimension;


  constructor(private service:CompanyDimensionService,@Inject(MAT_DIALOG_DATA) public data: string, private route:ActivatedRoute,
  private router:Router,private ref:MatDialogRef<UpdateCompanyDimensionComponent>) {}

  ngOnInit(): void {
    this.companyName = this.data;
   
      this.service.getDimensionByCompanyName(this.companyName).subscribe(data=>{
        this.companyDimension=data;
      },error=>console.log(error));
  }

  submit() {
      this.service.updateDimension(this.companyName,this.companyDimension).subscribe(data=>{
        this.ref.close();
        window.location.reload();
        console.log(this.companyName+"fdsafsdafsda"+this.companyDimension.dimensions[0].dimensionValue);
      });
      
  }
}
