import { Component, OnInit } from '@angular/core';
import { ScoreCap } from 'src/app/models/ScoreCap';
import { ScoreLevel } from 'src/app/models/ScoreLevel';
import { ScoreCapService } from 'src/app/services/score-cap.service';
import { ScoreLevelService } from 'src/app/services/score-level.service';

@Component({
  selector: 'app-add-score-cap',
  templateUrl: './add-score-cap.component.html',
  styleUrls: ['./add-score-cap.component.css']
})
export class AddScoreCapComponent implements OnInit {


  scoreLevel:any;
  scoreCap!:ScoreCap;

  numberOfLevels: number;
  dropdownOptions: string[] = []; 
  dropdownValue: string;
  totalRiskCappedScore: number;
  isFormSubmitted: boolean = false;

  constructor(private scoreLevelService:ScoreLevelService, private scoreCapService:ScoreCapService) { }

  ngOnInit(): void {
    this.scoreLevelService.getRiskScoreLevel().subscribe((data) => {
      this.scoreLevel = data;
      this.populateDropdownOptions();
    });
  }

  onSubmit(){
      // Set the form submission flag to true
    this.isFormSubmitted = true;

    // Check if the form is valid
    if (this.isFormValid()) {
      // If the form is valid, handle the form submission logic
      console.log('Form submitted successfully!');
      // Add your additional form submission logic here

      const numberName = this.convertNumberToName(this.numberOfLevels);
      const resultString = `${numberName} "${this.dropdownValue}"`;
      console.log('Form submitted successfully!');
      console.log('Result String:', resultString);
    
      this.scoreCap=new ScoreCap(resultString,this.totalRiskCappedScore);

      this.scoreCapService.addScoreCap(this.scoreCap).subscribe((data)=>{
          console.log(data);
      });


    } else {
      // If the form is invalid, show an alert
      alert('Please fill in all required fields correctly.');
    }
    
  }


  private populateDropdownOptions() {
    const uniqueOptions = new Set<string>();
    for (const val of this.scoreLevel) {
      const lowercaseLevel = val.level.toLowerCase();
      uniqueOptions.add(lowercaseLevel);
    }
    this.dropdownOptions = Array.from(uniqueOptions);
  }

  private isFormValid(): boolean {
    // Check if all required fields have been filled and are valid
    return (
      this.numberOfLevels !== undefined && this.numberOfLevels >= 1 && this.numberOfLevels <= 100 &&
      this.dropdownValue !== undefined &&
      this.totalRiskCappedScore !== undefined && this.totalRiskCappedScore >= 1 && this.totalRiskCappedScore <= 100
    );
  }

  private convertNumberToName(number: number): string {
    const numberNames: string[] = [
      "", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine",
      "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen",
      "Seventeen", "Eighteen", "Nineteen"
    ];

    const tensNames: string[] = ["", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"];

    if (number === 0) {
      return "Zero";
    } else if (number < 20) {
      return numberNames[number];
    } else if (number < 100) {
      const tensDigit = Math.floor(number / 10);
      const unitDigit = number % 10;
      return tensNames[tensDigit] + (unitDigit !== 0 ? " " + numberNames[unitDigit] : "");
    } else if (number === 100) {
      return "One Hundred";
    } else {
      return "Number out of range";
    }
  }

}
