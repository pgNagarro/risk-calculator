import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http'

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ViewComponent } from './components/view/view.component';
import { CompanyDimensionComponent } from './components/company-dimension/company-dimension.component';
import { DimensionWeightComponent } from './components/dimension-weight/dimension-weight.component';
import { CalculationLogicComponent } from './components/calculation-logic/calculation-logic.component';
import { ScoreLevelComponent } from './components/score-level/score-level.component';
import { ScoreCapComponent } from './components/score-cap/score-cap.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatDialogModule} from '@angular/material/dialog';

import { AddCompanyDimensionComponent } from './components/add-company-dimension/add-company-dimension.component';
import {MatButtonModule} from '@angular/material/button';
import {MatIconModule} from '@angular/material/icon';
import { UpdateCompanyDimensionComponent } from './components/update-company-dimension/update-company-dimension.component';





@NgModule({
  declarations: [
    AppComponent,
    ViewComponent,
    CompanyDimensionComponent,
    DimensionWeightComponent,
    CalculationLogicComponent,
    ScoreLevelComponent,
    ScoreCapComponent,
    AddCompanyDimensionComponent,
    UpdateCompanyDimensionComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    BrowserAnimationsModule,
    MatDialogModule,
    MatButtonModule,
    MatIconModule,
    ReactiveFormsModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
