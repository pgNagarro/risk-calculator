import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddScoreLevelComponent } from './add-score-level.component';

describe('AddScoreLevelComponent', () => {
  let component: AddScoreLevelComponent;
  let fixture: ComponentFixture<AddScoreLevelComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddScoreLevelComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddScoreLevelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
