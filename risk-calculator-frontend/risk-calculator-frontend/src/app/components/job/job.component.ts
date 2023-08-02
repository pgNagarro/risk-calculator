import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { JobService } from 'src/app/services/job.service';

@Component({
  selector: 'app-job',
  templateUrl: './job.component.html',
  styleUrls: ['./job.component.css']
})
export class JobComponent implements OnInit {

  @ViewChild('tableRef', { static: true }) tableRef!: ElementRef<HTMLTableElement>;

  results:any;
  currentPage: number = 1;
  itemsPerPage: number = 5;

  constructor(private service:JobService) { }

  ngOnInit(): void {
    let resp = this.service.getJobData();
    resp.subscribe((data)=>{
      this.results=data;
    });
    console.log(this.results);
 }

 get paginatedResults(): any[] {
  const startIndex = (this.currentPage - 1) * this.itemsPerPage;
  return this.results.slice(startIndex, startIndex + this.itemsPerPage);
}

get totalPages(): number {
  return Math.ceil(this.results.length / this.itemsPerPage);
}

// Implement a function to change the current page
changePage(page: number): void {
  this.currentPage = page;
}

get pageNumbers(): number[] {
  return Array(this.totalPages).fill(0).map((_, i) => i + 1);
}

}
