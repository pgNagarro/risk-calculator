import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ScoreLevel } from '../models/ScoreLevel';

@Injectable({
  providedIn: 'root'
})
export class ScoreLevelService {

  private baseURL = "http://localhost:8081";
  constructor(private http:HttpClient) { }

  public getRiskScoreLevel(){
    return this.http.get(this.baseURL+"/all-score-level");
  }

  addRiskScoreLevel(riskScoreLevel: ScoreLevel): Observable<Object>{
    return this.http.post(this.baseURL+"/add-score-level",riskScoreLevel);
  }

  
  getRiskScoreLevelByScore(score:string):Observable<ScoreLevel>{
    return this.http.get<ScoreLevel>(this.baseURL+"/score-level/"+score);
}

updateRiskScoreLevel(score:string,riskScoreLevel:ScoreLevel):Observable<Object>{
  return this.http.put(this.baseURL+"/score-level/"+score,riskScoreLevel);
}

deleteRiskScoreLevel(score:string):Observable<Object>{
  return this.http.delete(this.baseURL+"/score-level/"+score);
}
}
