import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DimensionWeightComponent } from './dimension-weight.component';

describe('DimensionWeightComponent', () => {
  let component: DimensionWeightComponent;
  let fixture: ComponentFixture<DimensionWeightComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DimensionWeightComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DimensionWeightComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
