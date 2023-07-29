import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateScoreCapComponent } from './update-score-cap.component';

describe('UpdateScoreCapComponent', () => {
  let component: UpdateScoreCapComponent;
  let fixture: ComponentFixture<UpdateScoreCapComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UpdateScoreCapComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdateScoreCapComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
