import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomerVehiclesComponent } from './customer-vehicles.component';

describe('CustomerVehiclesComponent', () => {
  let component: CustomerVehiclesComponent;
  let fixture: ComponentFixture<CustomerVehiclesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CustomerVehiclesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CustomerVehiclesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
