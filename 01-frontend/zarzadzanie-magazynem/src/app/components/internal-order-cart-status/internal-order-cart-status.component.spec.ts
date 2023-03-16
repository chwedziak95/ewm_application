import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InternalOrderCartStatusComponent } from './internal-order-cart-status.component';

describe('InternalOrderCartStatusComponent', () => {
  let component: InternalOrderCartStatusComponent;
  let fixture: ComponentFixture<InternalOrderCartStatusComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ InternalOrderCartStatusComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(InternalOrderCartStatusComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
