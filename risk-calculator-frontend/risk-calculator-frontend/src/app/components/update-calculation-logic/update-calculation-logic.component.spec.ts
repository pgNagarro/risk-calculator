import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateCalculationLogicComponent } from './update-calculation-logic.component';

describe('UpdateCalculationLogicComponent', () => {
  let component: UpdateCalculationLogicComponent;
  let fixture: ComponentFixture<UpdateCalculationLogicComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UpdateCalculationLogicComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdateCalculationLogicComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
