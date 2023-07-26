import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { ScoreLevelService } from 'src/app/services/score-level.service';
import { AddScoreLevelComponent } from '../add-score-level/add-score-level.component';

@Component({
  selector: 'app-score-level',
  templateUrl: './score-level.component.html',
  styleUrls: ['./score-level.component.css']
})
export class ScoreLevelComponent implements OnInit {

  scoreLevels:any;
  constructor(private dialog: MatDialog, private router: Router, private service:ScoreLevelService) { }

  ngOnInit(): void {
    this.service.getRiskScoreLevel().subscribe((data)=>{
      this.scoreLevels=data;
    });
  }

  openPopup(){
    this.dialog.open(AddScoreLevelComponent,{
      width: '35%',
      height: '45%',
      panelClass: 'custom-dialog'
    })
  }

}
