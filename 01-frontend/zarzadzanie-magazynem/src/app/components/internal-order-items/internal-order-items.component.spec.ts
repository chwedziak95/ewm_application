import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InternalOrderItemsComponent } from './internal-order-items.component';

describe('InternalOrderItemsComponent', () => {
  let component: InternalOrderItemsComponent;
  let fixture: ComponentFixture<InternalOrderItemsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ InternalOrderItemsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(InternalOrderItemsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
