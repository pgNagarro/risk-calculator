import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddDimensionWeightComponent } from './add-dimension-weight.component';

describe('AddDimensionWeightComponent', () => {
  let component: AddDimensionWeightComponent;
  let fixture: ComponentFixture<AddDimensionWeightComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddDimensionWeightComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddDimensionWeightComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
