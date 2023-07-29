import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddCalculationLogicComponent } from './add-calculation-logic.component';

describe('AddCalculationLogicComponent', () => {
  let component: AddCalculationLogicComponent;
  let fixture: ComponentFixture<AddCalculationLogicComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddCalculationLogicComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddCalculationLogicComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
