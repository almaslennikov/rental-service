import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LandlordVehiclesComponent } from './landlord-vehicles.component';

describe('LandlordVehiclesComponent', () => {
  let component: LandlordVehiclesComponent;
  let fixture: ComponentFixture<LandlordVehiclesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LandlordVehiclesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LandlordVehiclesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
