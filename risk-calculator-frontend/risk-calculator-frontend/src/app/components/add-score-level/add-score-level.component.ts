import { Component, OnInit } from '@angular/core';
import { ScoreLevel } from 'src/app/models/ScoreLevel';
import { ScoreLevelService } from 'src/app/services/score-level.service';

@Component({
  selector: 'app-add-score-level',
  templateUrl: './add-score-level.component.html',
  styleUrls: ['./add-score-level.component.css']
})
export class AddScoreLevelComponent implements OnInit {

  scoreLevel:ScoreLevel;

  formData: any = {};

  constructor(private service:ScoreLevelService) { }

  ngOnInit(): void {
  }

  onSubmit() {
    // Check if the form is valid
    if (this.isFormValid()) {
      // Convert lowerRange and upperRange to a string in the format '1-20'
      this.formData.range = this.formData.lowerRange + '-' + this.formData.upperRange;
      console.log(this.formData.range);
      this.scoreLevel = new ScoreLevel(this.formData.range,this.formData.level);

      this.service.addRiskScoreLevel(this.scoreLevel).subscribe((data)=>{
        console.log(data);
      });

      // Process the form data or submit it to the server
      console.log('Form submitted successfully!', this.formData);
    }
  }

  isFormValid(): boolean {
    const lowerRange = parseInt(this.formData.lowerRange, 10);
    const upperRange = parseInt(this.formData.upperRange, 10);
    const levelRegex = /^[A-Za-z\s]+$/;  // Regular expression to match only letters

    // Check if the values are between 1 and 100
    if (lowerRange < 1 || lowerRange > 100 || upperRange < 1 || upperRange > 100) {
      alert('Please enter values between 1 and 100 for both Lower Range and Upper Range.');
      return false;
    }

    // Check if the upper range is greater than the lower range
    if (upperRange <= lowerRange) {
      alert('Upper Range should be greater than Lower Range.');
      return false;
    }

    // Check if the level contains only letters (no numbers)
    if (!this.formData.level || !levelRegex.test(this.formData.level.trim())) {
      alert('Level should be a non-empty string and contain only letters and spaces.');
      return false;
    }

    return true;
  }

}
