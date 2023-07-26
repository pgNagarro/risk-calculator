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
      const lowerRange = Math.round(parseFloat(this.formData.lowerRange));
      const upperRange = Math.round(parseFloat(this.formData.upperRange));

      // Convert lowerRange and upperRange to a string in the format '1-20'
      this.formData.range = lowerRange + '-' + upperRange;
    console.log(this.formData.range);

      // Fetch existing score levels from the service
      this.service.getRiskScoreLevel().subscribe((existingLevels) => {
        // Check if the new range collides with any existing range
        if (this.isRangeColliding(existingLevels)) {
          alert('The new range collides with an existing range. Please enter non-overlapping range values.');
        } else {
          // If the range is not colliding with any existing range, proceed to add it
          this.scoreLevel = new ScoreLevel(this.formData.range, this.formData.level);

          this.service.addRiskScoreLevel(this.scoreLevel).subscribe((data) => {
            console.log(data);
          });

          // Process the form data or submit it to the server
          console.log('Form submitted successfully!', this.formData);
        }
      });
    }
  }

  isRangeColliding(existingLevels: any): boolean {
    const newLowerRange = parseInt(this.formData.lowerRange, 10);
    const newUpperRange = parseInt(this.formData.upperRange, 10);

    // Check if the new range collides with any of the existing ranges
    for (const level of existingLevels) {
      const [existingLower, existingUpper] = level.score.split('-').map((value: string) => parseInt(value, 10));

      if (
        (newLowerRange >= existingLower && newLowerRange <= existingUpper) ||
        (newUpperRange >= existingLower && newUpperRange <= existingUpper) ||
        (existingLower >= newLowerRange && existingLower <= newUpperRange) ||
        (existingUpper >= newLowerRange && existingUpper <= newUpperRange)
      ) {
        return true; // Colliding ranges found
      }
    }

    return false; // No colliding ranges found
  }

  isFormValid(): boolean {
    const lowerRange = parseInt(this.formData.lowerRange, 10);
    const upperRange = parseInt(this.formData.upperRange, 10);
    const levelRegex = /^[A-Za-z\s]+$/;  // Regular expression to match only letters
  
    // Check if the values are valid integers between 1 and 100
    if (
      isNaN(lowerRange) || isNaN(upperRange) ||
      lowerRange < 1 || lowerRange > 100 || upperRange < 1 || upperRange > 100
    ) {
      alert('Please enter valid integer values between 1 and 100 for both Lower Range and Upper Range.');
      return false;
    }
  
    // Check if the values are whole numbers
    if (!this.isValidWeight(lowerRange) || !this.isValidWeight(upperRange)) {
      alert('Lower Range and Upper Range must be whole numbers.');
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

  isValidWeight(weight: any): boolean {
    // Check if the weight is a whole number between 1 and 100
    return Number.isInteger(weight);
  }

}
