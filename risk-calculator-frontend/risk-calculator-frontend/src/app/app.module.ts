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
import { AddDimensionWeightComponent } from './components/add-dimension-weight/add-dimension-weight.component';
import { MatSelectModule } from '@angular/material/select';
import { MatCardModule } from '@angular/material/card';
import { CompanyDimensionService } from './services/company-dimension.service';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { AddScoreLevelComponent } from './components/add-score-level/add-score-level.component';
import { AddScoreCapComponent } from './components/add-score-cap/add-score-cap.component';
import { UpdateScoreLevelComponent } from './components/update-score-level/update-score-level.component';




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
    AddDimensionWeightComponent,
    AddScoreLevelComponent,
    AddScoreCapComponent,
    UpdateScoreLevelComponent,
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
    MatSelectModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
  ],
  providers: [CompanyDimensionService],
  bootstrap: [AppComponent]
})
export class AppModule { }
