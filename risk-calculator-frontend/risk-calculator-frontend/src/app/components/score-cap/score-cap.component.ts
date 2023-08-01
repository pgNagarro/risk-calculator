import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { ScoreCapService } from 'src/app/services/score-cap.service';
import { AddScoreCapComponent } from '../add-score-cap/add-score-cap.component';
import { UpdateScoreCapComponent } from '../update-score-cap/update-score-cap.component';

@Component({
  selector: 'app-score-cap',
  templateUrl: './score-cap.component.html',
  styleUrls: ['./score-cap.component.css']
})
export class ScoreCapComponent implements OnInit {

  scoreCaps:any;
  showTable:boolean=true;

  constructor(private dialog: MatDialog, private service:ScoreCapService, private router: Router) { }

  ngOnInit(): void {
    this.loadData();
  }

  loadData(){
    this.service.getScoreCap().subscribe((data)=>{
      this.showTable=true;
      this.scoreCaps=data;
    });
  }

  openPopup(){
      this.dialog.open(AddScoreCapComponent,{
        width: '35%',
        height: '60%',
        panelClass: 'custom-dialog'
      }).afterClosed().subscribe(() => {
        this.showTable = false;
       this.loadData();
     });
  }

  openUpdatePopup(condition:string){
    this.dialog.open(UpdateScoreCapComponent,{
      data: condition,
      width: '35%',
      height: '50%',
      panelClass: 'custom-dialog'
    }).afterClosed().subscribe(() => {
      this.showTable = false;
     this.loadData();
   });
  }

  onDelete(condition:string){
      this.service.deleteScoreCap(condition).subscribe((data)=>{
          console.log(data);
          this.showTable = false;
          this.loadData();
      });
  }

}
