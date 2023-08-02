import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { JobComponent } from './components/job/job.component';
import { ResultComponent } from './components/result/result.component';
import { ViewComponent } from './components/view/view.component';

const routes: Routes = [
  {path:'view',component:ViewComponent},
  {path:'',component:HomeComponent},
  {path:'home',component:HomeComponent},
  {path:'result',component:ResultComponent},
  {path:'job',component:JobComponent},
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
