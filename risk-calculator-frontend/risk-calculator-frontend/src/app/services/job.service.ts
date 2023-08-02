import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class JobService {

  private baseURL = "http://localhost:8081";
  constructor(private http:HttpClient) { }

  public getJobData(){
    return this.http.get(this.baseURL+"/job-status");
  }
}
