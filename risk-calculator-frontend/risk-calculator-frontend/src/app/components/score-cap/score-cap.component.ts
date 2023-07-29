import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { ScoreCapService } from 'src/app/services/score-cap.service';
import { AddScoreCapComponent } from '../add-score-cap/add-score-cap.component';
import { UpdateScoreCapComponent } from '../update-score-cap/update-score-cap.component';

@Component({
  selector: 'app-score-cap',
  templateUrl: './score-cap.component.html',
  styleUrls: ['./score-cap.component.css']
})
export class ScoreCapComponent implements OnInit {

  scoreCaps:any;

  constructor(private dialog: MatDialog, private service:ScoreCapService, private router: Router) { }

  ngOnInit(): void {
    this.service.getScoreCap().subscribe((data)=>{
      this.scoreCaps=data;
    })
  }

  openPopup(){
      this.dialog.open(AddScoreCapComponent,{
        width: '35%',
        height: '60%',
        panelClass: 'custom-dialog'
      });
  }

  openUpdatePopup(condition:string){
    this.dialog.open(UpdateScoreCapComponent,{
      data: condition,
      width: '35%',
      height: '50%',
      panelClass: 'custom-dialog'
    });
  }

  onDelete(condition:string){
      this.service.deleteScoreCap(condition).subscribe((data)=>{
          console.log(data);
          this.reloadComponent();
      });
  }

  reloadComponent() {
    this.router.routeReuseStrategy.shouldReuseRoute = () => false;
    const currentUrl = this.router.url + '?';
    this.router.navigateByUrl(currentUrl).then(() => {
      this.router.navigated = false;
      this.router.navigate([this.router.url]);
    });
  }

}
