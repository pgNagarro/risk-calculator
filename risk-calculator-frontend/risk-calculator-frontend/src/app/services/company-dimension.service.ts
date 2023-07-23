import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CompanyDimension } from '../models/CompanyDimension'

@Injectable({
  providedIn: 'root'
})
export class CompanyDimensionService {

  private baseURL = "http://localhost:8081";
  constructor(private http: HttpClient) { }

  addCompanyDimension(companyDimension: CompanyDimension): Observable<Object> {
    return this.http.post(this.baseURL+"/add-company-dimension", companyDimension);
  }

  public getCompanyDimension() {
    return this.http.get(this.baseURL+"/company-dimension");
  }

  getDimensionByCompanyName(companyName: string): Observable<CompanyDimension> {
    return this.http.get<CompanyDimension>(this.baseURL+"/company-dimension/"+companyName);
  }

  updateDimension(companyName: string, companyDimension: CompanyDimension): Observable<Object> {
    return this.http.put(this.baseURL + "/company-dimension/"+companyName, companyDimension);
  }

  deleteDimension(companyName: string): Observable<Object> {
    return this.http.delete(this.baseURL + "/company-dimension/"+companyName);
  }
}
