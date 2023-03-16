import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InternalOrderCartComponent } from './internal-order-cart.component';

describe('InternalOrderCartComponent', () => {
  let component: InternalOrderCartComponent;
  let fixture: ComponentFixture<InternalOrderCartComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ InternalOrderCartComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(InternalOrderCartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
