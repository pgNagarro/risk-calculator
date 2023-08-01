import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ScoreLevel } from 'src/app/models/ScoreLevel';
import { ScoreLevelService } from 'src/app/services/score-level.service';

@Component({
  selector: 'app-update-score-level',
  templateUrl: './update-score-level.component.html',
  styleUrls: ['./update-score-level.component.css']
})
export class UpdateScoreLevelComponent implements OnInit {

  score:string;
  scoreLevel!:ScoreLevel;

  levelValue:string;

  constructor(@Inject(MAT_DIALOG_DATA) public data: string,private service:ScoreLevelService,private ref:MatDialogRef<UpdateScoreLevelComponent>) { }

  ngOnInit(): void {
    this.score=this.data;
    console.log(this.score);

    this.service.getRiskScoreLevelByScore(this.score).subscribe((data)=>{
    this.scoreLevel=data;
    this.levelValue = this.scoreLevel?.level;
    console.log(this.scoreLevel);
    });
  }


  submit(){
    const regex = /^[A-Za-z\s]+$/;
    if (regex.test(this.levelValue)) {
      // Validation passed, update the 'scoreLevel' object
      this.scoreLevel.level = this.levelValue.toLowerCase();
      this.service.updateRiskScoreLevel(this.score, this.scoreLevel).subscribe(data => {
        console.log(data);
        this.ref.close();
      });
    } else {
      // Validation failed, display error or perform other actions as needed
      alert('Validation failed. Only letters and spaces are allowed.');
    }
  }

  

}
