import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { VehiclesForRentComponent } from './vehicles-for-rent.component';

describe('VehiclesForRentComponent', () => {
  let component: VehiclesForRentComponent;
  let fixture: ComponentFixture<VehiclesForRentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ VehiclesForRentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(VehiclesForRentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
