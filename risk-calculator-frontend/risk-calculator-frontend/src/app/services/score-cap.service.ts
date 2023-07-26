import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ScoreCap } from '../models/ScoreCap';

@Injectable({
  providedIn: 'root'
})
export class ScoreCapService {

  private baseURL = "http://localhost:8081";
  constructor(private http:HttpClient) { }

  public getScoreCap(){
    return this.http.get(this.baseURL+"/all-score-cap");
  }

  addScoreCap(scoreCap: ScoreCap): Observable<Object>{
    return this.http.post(this.baseURL+"/add-score-cap",scoreCap);
  }

  getScoreCapByCondition(condition:string):Observable<ScoreCap>{
    return this.http.get<ScoreCap>(this.baseURL+"/score-cap/"+condition);
}

updateScoreCap(condition:string,scoreCap:ScoreCap):Observable<Object>{
  return this.http.put(this.baseURL+"/score-cap/"+condition,scoreCap);
}

deleteScoreCap(condition:string):Observable<Object>{
  return this.http.delete(this.baseURL+"/score-cap/"+condition);
}
}
