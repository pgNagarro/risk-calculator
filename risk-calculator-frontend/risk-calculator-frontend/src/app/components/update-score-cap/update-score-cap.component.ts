import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ScoreCap } from 'src/app/models/ScoreCap';
import { ScoreCapService } from 'src/app/services/score-cap.service';

@Component({
  selector: 'app-update-score-cap',
  templateUrl: './update-score-cap.component.html',
  styleUrls: ['./update-score-cap.component.css']
})
export class UpdateScoreCapComponent implements OnInit {

  condition:string;
  scoreCap!:ScoreCap;

  totalScoreCappedValue:number;
  isFormSubmitted: boolean = false;

  constructor(@Inject(MAT_DIALOG_DATA) public data: string,private scoreCapService:ScoreCapService,private ref:MatDialogRef<UpdateScoreCapComponent>) { }

  ngOnInit(): void {
    this.condition=this.data;
  
    this.scoreCapService.getScoreCapByCondition(this.condition).subscribe((data)=>{
      this.scoreCap=data;
      this.totalScoreCappedValue=this.scoreCap?.totalRiskCappedScore
    });
  }

  onSubmit(){

    this.isFormSubmitted = true;

    if (this.isFormValid()) {
      this.scoreCap.totalRiskCappedScore=this.totalScoreCappedValue;
      this.scoreCapService.updateScoreCap(this.condition,this.scoreCap).subscribe((data)=>{
        console.log(data);
        this.ref.close();
      });
    }else {
      // If the form is invalid, show an alert
      alert('Please fill in all required fields correctly.');
    }

  }

  private isFormValid(): boolean {
    // Check if all required fields have been filled and are valid
    return (
      this.totalScoreCappedValue !== undefined && this.totalScoreCappedValue >= 1 && this.totalScoreCappedValue <= 100
    );
  }

}
