import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CompanyDimensionComponent } from './company-dimension.component';

describe('CompanyDimensionComponent', () => {
  let component: CompanyDimensionComponent;
  let fixture: ComponentFixture<CompanyDimensionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CompanyDimensionComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CompanyDimensionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
