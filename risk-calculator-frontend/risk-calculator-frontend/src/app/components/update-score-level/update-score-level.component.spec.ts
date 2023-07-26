import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateScoreLevelComponent } from './update-score-level.component';

describe('UpdateScoreLevelComponent', () => {
  let component: UpdateScoreLevelComponent;
  let fixture: ComponentFixture<UpdateScoreLevelComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UpdateScoreLevelComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdateScoreLevelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
