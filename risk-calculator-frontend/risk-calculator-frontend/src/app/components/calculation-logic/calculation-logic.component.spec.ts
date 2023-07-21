import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CalculationLogicComponent } from './calculation-logic.component';

describe('CalculationLogicComponent', () => {
  let component: CalculationLogicComponent;
  let fixture: ComponentFixture<CalculationLogicComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CalculationLogicComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CalculationLogicComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
