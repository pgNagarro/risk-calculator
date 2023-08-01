import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SharedServiceService {

  showCompanyDimensionSubject = new Subject<boolean>();
  showDimensionWeightSubject = new Subject<boolean>();
  showCalculationLogicSubject = new Subject<boolean>();
  showScoreLevelSubject = new Subject<boolean>();
  showScoreCapSubject = new Subject<boolean>();

  // Methods to update values in the components
  updateShowCompanyDimension(value: boolean) {
    this.showCompanyDimensionSubject.next(value);
    console.log(this.showCompanyDimensionSubject.next(value));
  }

  updateShowDimensionWeight(value: boolean) {
    this.showDimensionWeightSubject.next(value);
  }

  updateShowCalculationLogic(value: boolean) {
    this.showCalculationLogicSubject.next(value);
  }

  updateShowScoreLevel(value: boolean) {
    this.showScoreLevelSubject.next(value);
  }

  updateShowScoreCap(value: boolean) {
    this.showScoreCapSubject.next(value);
  }
}

