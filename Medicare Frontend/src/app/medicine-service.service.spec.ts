import { TestBed } from '@angular/core/testing';

import { MedicineService } from './medicine.service';

describe('MedicineServiceService', () => {
  let service: MedicineService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MedicineService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
