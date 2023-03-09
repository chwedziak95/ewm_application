import { TestBed } from '@angular/core/testing';

import { CreateMaterialServiceService } from './create-material-service.service';

describe('CreateMaterialServiceService', () => {
  let service: CreateMaterialServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CreateMaterialServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
