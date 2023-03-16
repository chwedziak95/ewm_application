import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InternalOrderListComponent } from './internal-order-list.component';

describe('InternalOrderListComponent', () => {
  let component: InternalOrderListComponent;
  let fixture: ComponentFixture<InternalOrderListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ InternalOrderListComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(InternalOrderListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
