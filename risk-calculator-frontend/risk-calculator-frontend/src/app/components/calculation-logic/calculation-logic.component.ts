import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { CalculationLogicService } from 'src/app/services/calculation-logic.service';
import { AddCalculationLogicComponent } from '../add-calculation-logic/add-calculation-logic.component';
import { UpdateCalculationLogicComponent } from '../update-calculation-logic/update-calculation-logic.component';

@Component({
  selector: 'app-calculation-logic',
  templateUrl: './calculation-logic.component.html',
  styleUrls: ['./calculation-logic.component.css']
})
export class CalculationLogicComponent implements OnInit {

  allCalculationLogic:any;
  showTable:boolean=true;

  constructor(private dialog: MatDialog, private calculationLogicService:CalculationLogicService) { }

  ngOnInit(): void {
    this.loadData();
  }

  loadData(){
    this.calculationLogicService.getCalculationLogicLogic().subscribe((data)=>{
      this.showTable = true;
      this.allCalculationLogic=data;
  });
  }

  openPopup(){
    this.dialog.open(AddCalculationLogicComponent,{
      width: '70%',
      height: '100%',
      panelClass: 'custom-dialog'
    }).afterClosed().subscribe(() => {
      this.showTable = false;
      this.loadData();
    });
  }

  updatePopup(elementName:string){
    this.dialog.open(UpdateCalculationLogicComponent,{
      data: elementName,
      width: '70%',
      height: '100%',
      panelClass: 'custom-dialog'
    }).afterClosed().subscribe(() => {
       this.showTable = false;
      this.loadData();
    });
  }

  deleteRiskCalc(elementName:string){
    this.calculationLogicService.deleteCalculationLogic(elementName).subscribe((data)=>{
    this.showTable = false;
    this.loadData();
    });
  }

}
