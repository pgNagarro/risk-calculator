import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { CompanyDimensionService } from 'src/app/services/company-dimension.service';
import { SharedServiceService } from 'src/app/services/shared-service.service';

import { AddCompanyDimensionComponent } from '../add-company-dimension/add-company-dimension.component';
import { UpdateCompanyDimensionComponent } from '../update-company-dimension/update-company-dimension.component';

@Component({
  selector: 'app-company-dimension',
  templateUrl: './company-dimension.component.html',
  styleUrls: ['./company-dimension.component.css']
})
export class CompanyDimensionComponent implements OnInit {
  @ViewChild('tableRef', { static: true }) tableRef!: ElementRef<HTMLTableElement>;

  companyDimension: any;
  

  constructor(private dialog: MatDialog, private router: Router, private service: CompanyDimensionService,private sharedDataService: SharedServiceService) { }

  ngOnInit(): void {
    
     this.loadData();
  }

  loadData(){
    let cds = this.service.getCompanyDimension();
    cds.subscribe((data) => {
      this.companyDimension = data;
      this.generateCompanyDimensionTable(this.companyDimension);
    });
  }

  generateCompanyDimensionTable(_output: any) {
    console.log(_output);
    const table = this.tableRef.nativeElement;

    const thead = document.createElement('thead');
   
    const headerRow = document.createElement('tr');
    headerRow.setAttribute('style','font-size:larger;');
    const name = document.createElement('th');
    name.textContent = 'Company Name';
    headerRow.appendChild(name);

    for (const val of _output[0].dimensions) {
      const header = document.createElement('th');
      header.textContent = val.dimensionName;
      headerRow.appendChild(header);
    }
    const actions = document.createElement('th');
    actions.textContent = 'Actions';
    headerRow.appendChild(actions);
    thead.appendChild(headerRow);

    const tbody = document.createElement('tbody');
    for (const val of _output) {
      const row = document.createElement('tr');
      const cname = document.createElement('td');
      cname.textContent = val.companyName;
      row.setAttribute('style','font-size:larger;');
      row.appendChild(cname);
      for (const ele of val.dimensions) {
        const value = document.createElement('td');
        value.textContent = ele.dimensionValue;
        row.appendChild(value);
      }

      const updateButton = document.createElement('button');
      updateButton.textContent = 'Update';
      updateButton.setAttribute('class', 'btn btn-info');
      updateButton.setAttribute('style', 'margin-bottom: 4px; margin-top: 4px;margin-left: 10px; background-color: #c2fbd7;border-radius: 100px;box-shadow: rgba(44, 187, 99, .2) 0 -25px 18px -14px inset, rgba(44, 187, 99, .15) 0 1px 2px, rgba(44, 187, 99, .15) 0 2px 4px, rgba(44, 187, 99, .15) 0 4px 8px, rgba(44, 187, 99, .15) 0 8px 16px, rgba(44, 187, 99, .15) 0 16px 32px;color: green ;cursor: rgb(0, 64, 124)ter;display: inline-block;font-family: CerebriSans-Regular, -apple-system, system-ui, Roboto, sans-serif;padding: 7px 20px;text-align: center;text-decoration: none;transition: all 250ms;border: 0;font-size: 16px;user-select: none;-webkit-user-select: none;touch-action: manipulation;');

      updateButton.addEventListener('click', () => {
        this.openUpdatePopup(val.companyName);
      });
      const deleteButton = document.createElement('button');
      deleteButton.textContent = 'Delete';
      deleteButton.setAttribute('class', 'DeleteButton');
      deleteButton.setAttribute('style', 'margin-bottom: 4px; margin-top: 4px;margin-left: 10px; background-color: #fbc2c2;border-radius: 100px;box-shadow: rgba(187, 44, 44, 0.2) 0 -25px 18px -14px inset, rgba(187, 44, 44, 0.15) 0 1px 2px, rgba(187, 44, 44, 0.15) 0 2px 4px, rgba(187, 44, 44, 0.15) 0 4px 8px, rgba(187, 44, 44, 0.15) 0 8px 16px, rgba(187, 44, 44, 0.15) 0 16px 32px;color: red;cursor: pointer;display: inline-block;font-family: CerebriSans-Regular, -apple-system, system-ui, Roboto, sans-serif;padding: 7px 20px;text-align: center;text-decoration: none;transition: all 250ms;border: 0;font-size: 16px;user-select: none;-webkit-user-select: none;touch-action: manipulation;');
      
      
      deleteButton.addEventListener('click', () => {
        this.service.deleteDimension(val.companyName).subscribe(data => {
          this.removeTable();
          this.loadData();
        });
      });
      row.appendChild(updateButton);
      row.appendChild(deleteButton);

      tbody.appendChild(row);
    }
    thead.setAttribute("class", "table-dark");
    table.appendChild(thead);
    table.appendChild(tbody);
  }


  openPopup() {
    var pop_up = this.dialog.open(AddCompanyDimensionComponent, {
      width: '35%',
      height: '80%',
      panelClass: 'custom-dialog'
    });
    pop_up.afterClosed().subscribe(item=>{
      this.removeTable();
      this.loadData();
    })
  }


  openUpdatePopup(cName: string) {
    this.dialog.open(UpdateCompanyDimensionComponent, {
      data: cName,
      width: '35%',
      height: '70%',
      panelClass: 'custom-dialog'
    }).afterClosed().subscribe(() => {
      this.removeTable();
      this.loadData();
    });
  }

 
  removeTable() {
    const table = this.tableRef.nativeElement;
    while (table.firstChild) {
      table.removeChild(table.firstChild);
    }
  }
}
