import { TestBed } from '@angular/core/testing';

import { InternalOrderService } from './internal-order.service';

describe('InternalOrderService', () => {
  let service: InternalOrderService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(InternalOrderService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
