import { TestBed } from '@angular/core/testing';

import { ScoreCapService } from './score-cap.service';

describe('ScoreCapService', () => {
  let service: ScoreCapService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ScoreCapService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
