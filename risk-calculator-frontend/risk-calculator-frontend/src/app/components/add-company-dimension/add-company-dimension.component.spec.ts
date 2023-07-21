import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddCompanyDimensionComponent } from './add-company-dimension.component';

describe('AddCompanyDimensionComponent', () => {
  let component: AddCompanyDimensionComponent;
  let fixture: ComponentFixture<AddCompanyDimensionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddCompanyDimensionComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddCompanyDimensionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
