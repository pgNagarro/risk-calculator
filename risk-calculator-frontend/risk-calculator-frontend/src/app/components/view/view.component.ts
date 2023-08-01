import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { SharedServiceService } from 'src/app/services/shared-service.service';

@Component({
  selector: 'app-view',
  templateUrl: './view.component.html',
  styleUrls: ['./view.component.css']
})
export class ViewComponent implements OnInit {
  

  showCompanyDimension:boolean;
  showDimensionWeight:boolean;
  showCalculationLogic:boolean;
  showScoreLevel:boolean;
  showScoreCap:boolean;
  showText:boolean;

  constructor(private sharedDataService:SharedServiceService,private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.showCompanyDimension = false;
    this.showDimensionWeight = false;
    this.showCalculationLogic = false;
    this.showScoreLevel = false;
    this.showScoreCap = false;
    this.showText=true;
  }
  

  companyDimension(){
   const showTableDimension = document.getElementById('companyDimension');
   const showTableWeight = document.getElementById('dimensionWeight');
   const showTableLogic = document.getElementById('calculationLogic');
   const showTableLevel = document.getElementById('scoreLevel');
   const showTableCap = document.getElementById('scoreCap');
    this.showCompanyDimension=true;
    this.showDimensionWeight=false;
    this.showCalculationLogic=false;
    this.showScoreLevel=false;
    this.showScoreCap=false;
    this.showText=false;
    showTableDimension?.classList.add('active');
    showTableWeight?.classList.remove('active');
    showTableLogic?.classList.remove('active');
    showTableLevel?.classList.remove('active');
    showTableCap?.classList.remove('active');
  }

  dimensionWeight(){
    const showTableDimension = document.getElementById('companyDimension');
    const showTableWeight = document.getElementById('dimensionWeight');
    const showTableLogic = document.getElementById('calculationLogic');
    const showTableLevel = document.getElementById('scoreLevel');
    const showTableCap = document.getElementById('scoreCap');
    this.showCompanyDimension=false;
    this.showDimensionWeight=true;
    this.showCalculationLogic=false;
    this.showScoreLevel=false;
    this.showScoreCap=false;
    this.showText=false;
    showTableDimension?.classList.remove('active');
    showTableWeight?.classList.add('active');
    showTableLogic?.classList.remove('active');
    showTableLevel?.classList.remove('active');
    showTableCap?.classList.remove('active');
    
  }

  calculationLogic(){
    const showTableDimension = document.getElementById('companyDimension');
   const showTableWeight = document.getElementById('dimensionWeight');
   const showTableLogic = document.getElementById('calculationLogic');
   const showTableLevel = document.getElementById('scoreLevel');
   const showTableCap = document.getElementById('scoreCap');
    this.showCompanyDimension=false;
    this.showDimensionWeight=false;
    this.showCalculationLogic=true;
    this.showScoreLevel=false;
    this.showScoreCap=false;
    this.showText=false;
    showTableDimension?.classList.remove('active');
    showTableWeight?.classList.remove('active');
    showTableLogic?.classList.add('active');
    showTableLevel?.classList.remove('active');
    showTableCap?.classList.remove('active');
  }

  scoreLevel(){
    const showTableDimension = document.getElementById('companyDimension');
   const showTableWeight = document.getElementById('dimensionWeight');
   const showTableLogic = document.getElementById('calculationLogic');
   const showTableLevel = document.getElementById('scoreLevel');
   const showTableCap = document.getElementById('scoreCap');

    this.showCompanyDimension=false;
    this.showDimensionWeight=false;
    this.showCalculationLogic=false;
    this.showScoreLevel=true;
    this.showScoreCap=false;
    this.showText=false;
    showTableDimension?.classList.remove('active');
    showTableWeight?.classList.remove('active');
    showTableLogic?.classList.remove('active');
    showTableLevel?.classList.add('active');
    showTableCap?.classList.remove('active');
  }

  scoreCap(){
    const showTableDimension = document.getElementById('companyDimension');
   const showTableWeight = document.getElementById('dimensionWeight');
   const showTableLogic = document.getElementById('calculationLogic');
   const showTableLevel = document.getElementById('scoreLevel');
   const showTableCap = document.getElementById('scoreCap');
    this.showCompanyDimension=false;
    this.showDimensionWeight=false;
    this.showCalculationLogic=false;
    this.showScoreLevel=false;
    this.showScoreCap=true;
    this.showText=false;
    showTableDimension?.classList.remove('active');
    showTableWeight?.classList.remove('active');
    showTableLogic?.classList.remove('active');
    showTableLevel?.classList.remove('active');
    showTableCap?.classList.add('active');
  }

}
