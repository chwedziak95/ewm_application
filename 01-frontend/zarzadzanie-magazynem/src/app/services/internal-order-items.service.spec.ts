import { TestBed } from '@angular/core/testing';

import { InternalOrderItemsService } from './internal-order-items.service';

describe('InternalOrderItemsService', () => {
  let service: InternalOrderItemsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(InternalOrderItemsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
