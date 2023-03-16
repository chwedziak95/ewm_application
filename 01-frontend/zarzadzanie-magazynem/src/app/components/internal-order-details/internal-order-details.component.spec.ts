import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InternalOrderDetailsComponent } from './internal-order-details.component';

describe('InternalOrderDetailsComponent', () => {
  let component: InternalOrderDetailsComponent;
  let fixture: ComponentFixture<InternalOrderDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ InternalOrderDetailsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(InternalOrderDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
