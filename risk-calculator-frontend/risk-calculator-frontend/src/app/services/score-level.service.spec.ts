import { TestBed } from '@angular/core/testing';

import { ScoreLevelService } from './score-level.service';

describe('ScoreLevelService', () => {
  let service: ScoreLevelService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ScoreLevelService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
