import { TestBed } from '@angular/core/testing';

import { DimensionWeightService } from './dimension-weight.service';

describe('DimensionWeightService', () => {
  let service: DimensionWeightService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DimensionWeightService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
