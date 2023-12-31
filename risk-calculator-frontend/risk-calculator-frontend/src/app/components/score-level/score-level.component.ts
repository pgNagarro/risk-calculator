import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { ScoreLevelService } from 'src/app/services/score-level.service';
import { AddScoreLevelComponent } from '../add-score-level/add-score-level.component';
import { UpdateScoreLevelComponent } from '../update-score-level/update-score-level.component';

@Component({
  selector: 'app-score-level',
  templateUrl: './score-level.component.html',
  styleUrls: ['./score-level.component.css']
})
export class ScoreLevelComponent implements OnInit {

  scoreLevels:any;
  showTable:boolean=true;
  constructor(private dialog: MatDialog, private router: Router, private service:ScoreLevelService) { }

  ngOnInit(): void {
    this.loadData();
  }

  loadData(){
    this.service.getRiskScoreLevel().subscribe((data)=>{
      this.showTable = true;
      this.scoreLevels=data;
    });
  }

  openPopup(){
    this.dialog.open(AddScoreLevelComponent,{
      width: '35%',
      height: '60%',
      panelClass: 'custom-dialog'
    }).afterClosed().subscribe(() => {
      this.showTable = false;
     this.loadData();
   });
  }

  openUpdatePopup(score:string){
    this.dialog.open(UpdateScoreLevelComponent,{
      data: score,
      width: '35%',
      height: '60%',
      panelClass: 'custom-dialog'
    }).afterClosed().subscribe(() => {
      this.showTable = false;
     this.loadData();
   });
  }

  onDelete(score:string){
    this.service.deleteRiskScoreLevel(score).subscribe(data=>{
        this.showTable = false;
        this.loadData();
    });
  }

 

}
