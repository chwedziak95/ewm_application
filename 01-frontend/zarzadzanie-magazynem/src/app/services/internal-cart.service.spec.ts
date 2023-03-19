import { TestBed } from '@angular/core/testing';

import { InternalCartService } from './internal-cart.service';

describe('InternalCartService', () => {
  let service: InternalCartService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(InternalCartService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
