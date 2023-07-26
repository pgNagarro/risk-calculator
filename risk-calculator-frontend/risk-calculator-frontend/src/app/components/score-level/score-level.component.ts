import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { AddScoreLevelComponent } from '../add-score-level/add-score-level.component';

@Component({
  selector: 'app-score-level',
  templateUrl: './score-level.component.html',
  styleUrls: ['./score-level.component.css']
})
export class ScoreLevelComponent implements OnInit {

  constructor(private dialog: MatDialog, private router: Router) { }

  ngOnInit(): void {
  }

  openPopup(){
    this.dialog.open(AddScoreLevelComponent,{
      width: '35%',
      height: '45%',
      panelClass: 'custom-dialog'
    })
  }

}
