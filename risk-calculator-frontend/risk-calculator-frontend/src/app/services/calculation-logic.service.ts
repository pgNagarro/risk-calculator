import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CalculationLogic } from '../models/CalculationLogic';

@Injectable({
  providedIn: 'root'
})
export class CalculationLogicService {

  private baseURL = "http://localhost:8081";
  constructor(private http:HttpClient) { }

  public getCalculationLogicLogic(){
    return this.http.get(this.baseURL+"/all-calculation-logic");
  }

  addCalculationLogic(calculationLogic: CalculationLogic): Observable<Object>{
    return this.http.post(this.baseURL+"/add-calculation-logic",calculationLogic);
  }

  getCalculationLogicByElementName(elementName:string):Observable<CalculationLogic>{
      return this.http.get<CalculationLogic>(this.baseURL+"/calculation-logic/"+elementName);
  }

  updateCalculationLogic(elementName:string,calculationLogic:CalculationLogic):Observable<Object>{
    return this.http.put(this.baseURL+"/calculation-logic/"+elementName,calculationLogic);
  }

  deleteCalculationLogic(elementName:string):Observable<Object>{
    return this.http.delete(this.baseURL+"/calculation-logic/"+elementName);
  }
}
