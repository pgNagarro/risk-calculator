import { TestBed } from '@angular/core/testing';

import { CalculationLogicService } from './calculation-logic.service';

describe('CalculationLogicService', () => {
  let service: CalculationLogicService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CalculationLogicService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
