import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ResultService {

  private baseURL = "http://localhost:8081";
  constructor(private http:HttpClient) { }

  public getResult(){
    return this.http.get(this.baseURL+"/result");
  }
}
