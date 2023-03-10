import { TestBed } from '@angular/core/testing';

import { CreateMaterialService } from './create-material.service';

describe('CreateMaterialService', () => {
  let service: CreateMaterialService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CreateMaterialService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
