import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { DimensionWeightService } from 'src/app/services/dimension-weight.service';
import { AddDimensionWeightComponent } from '../add-dimension-weight/add-dimension-weight.component';

@Component({
  selector: 'app-dimension-weight',
  templateUrl: './dimension-weight.component.html',
  styleUrls: ['./dimension-weight.component.css']
})
export class DimensionWeightComponent implements OnInit {

  dimensionWeights:any;
  showTable : boolean = true;

  constructor(private dialog: MatDialog, private router: Router, private dimensionWeightService:DimensionWeightService) { }

  ngOnInit(): void {
    this.loadData();
  }

  loadData(){
    this.dimensionWeightService.getDimensionWeight().subscribe((data)=>{
      this.showTable = true;
      this.dimensionWeights=data;
      console.log(this.dimensionWeights);
  });
  }

  openPopup(){
    this.dialog.open(AddDimensionWeightComponent, {
      width: '50%',
      height: '85%',
      panelClass: 'custom-dialog'
    }).afterClosed().subscribe(() => {
      this.showTable = false;
      this.loadData();
    });
  }

 

}
