import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { MatDialog,MatDialogConfig } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { CompanyDimensionService } from 'src/app/services/company-dimension.service';

import { AddCompanyDimensionComponent } from '../add-company-dimension/add-company-dimension.component';
import { UpdateCompanyDimensionComponent } from '../update-company-dimension/update-company-dimension.component';

@Component({
  selector: 'app-company-dimension',
  templateUrl: './company-dimension.component.html',
  styleUrls: ['./company-dimension.component.css']
})
export class CompanyDimensionComponent implements OnInit {
  @ViewChild('tableRef', { static: true }) tableRef!: ElementRef<HTMLTableElement>;

  companyDimension:any;

  constructor(private dialog:MatDialog, private router:Router, private service:CompanyDimensionService) { }

  ngOnInit(): void {

    let cds = this.service.getCompanyDimension();
    cds.subscribe((data)=>{
      this.companyDimension=data;
      this.generateCompanyDimensionTable(this.companyDimension);
    })
  }

  generateCompanyDimensionTable(_output:any){
    console.log(_output);
    const table = this.tableRef.nativeElement;

    const thead = document.createElement('thead');
    const headerRow = document.createElement('tr');
    const name = document.createElement('th');
      name.textContent = 'Company Name';
      headerRow.appendChild(name);

    for(const val of _output[0].dimensions){
      const header = document.createElement('th');
      header.textContent = val.dimensionName;
      headerRow.appendChild(header);
    }
    const actions = document.createElement('th');
    actions.textContent='Actions';
    headerRow.appendChild(actions);
    thead.appendChild(headerRow);

    const tbody = document.createElement('tbody');
    for (const val of _output) {
      const row = document.createElement('tr');
      const cname = document.createElement('td');
      cname.textContent = val.companyName;
      row.appendChild(cname);
      for(const ele of val.dimensions){
        const value = document.createElement('td');
        value.textContent = ele.dimensionValue;
        row.appendChild(value);
      }

      const updateButton = document.createElement('button');
      updateButton.textContent='Update';
      updateButton.setAttribute('class','btn btn-info');
      updateButton.setAttribute('style','background-color:#0ed9ff;');
      updateButton.addEventListener('click',()=>{
        //this.router.navigate(['update-risk-score',val.companyName]);
        this.openUpdatePopup(val.companyName);
      });
      const deleteButton = document.createElement('button');
      deleteButton.textContent='Delete';
      deleteButton.setAttribute('class','btn btn-danger');
      deleteButton.setAttribute('style','margin-left: 10px; background-color:#dc3545;color:white;');
      deleteButton.addEventListener('click',()=>{
          this.service.deleteDimension(val.companyName).subscribe(data=>{
              this.generateCompanyDimensionTable(_output);
              // this.router.navigate(['/view']);
          });
      });
      row.appendChild(updateButton);
      row.appendChild(deleteButton);

      tbody.appendChild(row);
    }
    thead.setAttribute("class","table-dark");
    table.appendChild(thead);
    table.appendChild(tbody);
  }

  
  openPopup(){
    this.dialog.open(AddCompanyDimensionComponent,{
      width:'35%',
      height:'70%',
      panelClass: 'custom-dialog'
    })
  }


  openUpdatePopup(cName:string){
    this.dialog.open(UpdateCompanyDimensionComponent,{
      data:cName,
      width:'35%',
      height:'70%',
      panelClass: 'custom-dialog'
    })
  }

}
