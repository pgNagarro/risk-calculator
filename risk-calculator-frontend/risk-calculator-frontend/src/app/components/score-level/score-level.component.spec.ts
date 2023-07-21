import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ScoreLevelComponent } from './score-level.component';

describe('ScoreLevelComponent', () => {
  let component: ScoreLevelComponent;
  let fixture: ComponentFixture<ScoreLevelComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ScoreLevelComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ScoreLevelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
