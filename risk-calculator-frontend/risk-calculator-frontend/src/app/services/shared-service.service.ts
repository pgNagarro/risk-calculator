import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SharedServiceService {

  private showCompanyDimensionSubject = new BehaviorSubject<boolean>(true);
  public showCompanyDimension$: Observable<boolean> = this.showCompanyDimensionSubject.asObservable();

  toggleCompanyDimension(show: boolean): void {
    this.showCompanyDimensionSubject.next(show);
  }
}
