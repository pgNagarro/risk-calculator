import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { ResultService } from 'src/app/services/result.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  @ViewChild('tableRef', { static: true }) tableRef!: ElementRef<HTMLTableElement>;

  results:any;

  constructor(private service:ResultService) { }

  ngOnInit(): void {
    let resp = this.service.getResult();
    resp.subscribe((data)=>{
      this.results=data;
      this.generateTable(this.results);
    });
    console.log(this.results);
 }

 generateTable(_output: any){
  const table = this.tableRef.nativeElement;

  const thead = document.createElement('thead');
    const headerRow = document.createElement('tr');
    const name = document.createElement('th');
      name.textContent = 'Company Name';
      headerRow.appendChild(name);

    for(const val of _output[0].values){
      const header = document.createElement('th');
      header.textContent = val.elementName;
      headerRow.appendChild(header);
    }
    thead.appendChild(headerRow);

    const tbody = document.createElement('tbody');
    for (const val of _output) {
      const row = document.createElement('tr');
      const cname = document.createElement('td');
      cname.textContent = val.companyName;
      row.appendChild(cname);
      for(const ele of val.values){
        const value = document.createElement('td');
        value.textContent = ele.elementValue;
        row.appendChild(value);
      }
      tbody.appendChild(row);
    }
    thead.setAttribute("class","table-dark");
    table.appendChild(thead);
    table.appendChild(tbody);
 }

 recalculate(){
  window.location.reload();
 }
}
