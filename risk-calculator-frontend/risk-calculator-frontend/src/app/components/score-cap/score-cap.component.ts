import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { AddScoreCapComponent } from '../add-score-cap/add-score-cap.component';

@Component({
  selector: 'app-score-cap',
  templateUrl: './score-cap.component.html',
  styleUrls: ['./score-cap.component.css']
})
export class ScoreCapComponent implements OnInit {

  constructor(private dialog: MatDialog) { }

  ngOnInit(): void {
  }

  openPopup(){
      this.dialog.open(AddScoreCapComponent,{
        width: '35%',
        height: '60%',
        panelClass: 'custom-dialog'
      });
  }

}
