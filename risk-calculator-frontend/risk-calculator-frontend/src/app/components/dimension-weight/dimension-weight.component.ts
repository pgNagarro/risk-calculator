import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { AddDimensionWeightComponent } from '../add-dimension-weight/add-dimension-weight.component';

@Component({
  selector: 'app-dimension-weight',
  templateUrl: './dimension-weight.component.html',
  styleUrls: ['./dimension-weight.component.css']
})
export class DimensionWeightComponent implements OnInit {

  constructor(private dialog: MatDialog, private router: Router) { }

  ngOnInit(): void {
  }

  openPopup(){
    this.dialog.open(AddDimensionWeightComponent, {
      width: '50%',
      height: '85%',
      panelClass: 'custom-dialog'
    })
  }

}
