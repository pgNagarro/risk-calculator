import { TestBed } from '@angular/core/testing';

import { CompanyDimensionService } from './company-dimension.service';

describe('CompanyDimensionService', () => {
  let service: CompanyDimensionService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CompanyDimensionService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
