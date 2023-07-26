import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { DimensionWeight } from '../models/DimensionWeight';
import { DimensionWeights } from '../models/DimensionWeights';

@Injectable({
  providedIn: 'root'
})
export class DimensionWeightService {

  private baseURL = "http://localhost:8081";
  constructor(private http:HttpClient) { }

  public getDimensionWeight(){
    return this.http.get(this.baseURL+"/all-dimension-weight");
  }

  addDimensionWeight(dimensionWeights: DimensionWeights): Observable<Object>{
    return this.http.post(this.baseURL+"/add-dimension-weight",dimensionWeights);
  }

  getDimensionWeightByDimension(dimension:string):Observable<DimensionWeight>{
    return this.http.get<DimensionWeight>(this.baseURL+"/dimension-weight/"+dimension);
}

updateDimensionWeight(dimension:string,dimensionWeight:DimensionWeight):Observable<Object>{
  return this.http.put(this.baseURL+"/dimension-weight/"+dimension,dimensionWeight);
}

deleteDimensionWeight(dimension:string):Observable<Object>{
  return this.http.delete(this.baseURL+"/dimension-weight/"+dimension);
}

}
