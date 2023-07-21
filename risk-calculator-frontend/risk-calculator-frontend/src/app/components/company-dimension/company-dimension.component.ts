import { Component, OnInit } from '@angular/core';
import { MatDialog,MatDialogConfig } from '@angular/material/dialog';

import { AddCompanyDimensionComponent } from '../add-company-dimension/add-company-dimension.component';

@Component({
  selector: 'app-company-dimension',
  templateUrl: './company-dimension.component.html',
  styleUrls: ['./company-dimension.component.css']
})
export class CompanyDimensionComponent implements OnInit {

  constructor(private dialog:MatDialog) { }

  ngOnInit(): void {
  }

  
  openPopup(){
    this.dialog.open(AddCompanyDimensionComponent,{
      width:'35%',
      height:'70%',
      panelClass: 'custom-dialog'
    })
  }

}
