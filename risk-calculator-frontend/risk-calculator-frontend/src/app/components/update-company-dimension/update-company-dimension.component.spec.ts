import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateCompanyDimensionComponent } from './update-company-dimension.component';

describe('UpdateCompanyDimensionComponent', () => {
  let component: UpdateCompanyDimensionComponent;
  let fixture: ComponentFixture<UpdateCompanyDimensionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UpdateCompanyDimensionComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdateCompanyDimensionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
