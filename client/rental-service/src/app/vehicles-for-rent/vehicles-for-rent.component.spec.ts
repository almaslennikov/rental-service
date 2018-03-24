import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { VehiclesForRentComponent } from './vehicles-for-rent.component';
import {RentInfo} from "../structures/rent-info";
import {UserRole} from "../structures/user-role";
import {UserInfo} from "../structures/user-info";
import {Model} from "../structures/model";
import {By} from "@angular/platform-browser";

let getTestRentInfo = (): RentInfo => {
  let info = new RentInfo();
  info.orderId = 0;
  info.vehicleId = 0;
  let model = new Model();
  model.brand = 'TestBrand';
  model.model = 'TestModel';
  let landlord = new UserInfo();
  landlord.set(3, 'TestName', 'TestLastName', 'TestEmail', UserRole.landlord);
  info.model = model;
  info.landlord = landlord;
  return info;
};

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

  it('should not show table with empty vehicles list', () => {
    component.rentList = [];
    fixture.detectChanges();

    let tableEl = fixture.debugElement.query(By.css('table'));

    expect(tableEl).toBeNull();
  });

  it('should show message with empty vehicles list', () => {
    component.rentList = [];
    fixture.detectChanges();

    let messageEl = fixture.debugElement.query(By.css('.message .header')).nativeElement;

    expect(messageEl.textContent).toContain('There are no available vehicles');
  });

  it('should show table with non-empty vehicles list', () => {
    component.rentList.push(getTestRentInfo());
    fixture.detectChanges();

    let tableEl = fixture.debugElement.query(By.css('table'));

    expect(tableEl).toBeDefined();
  });

  it('should not show message with non-empty vehicles list', () => {
    component.rentList.push(getTestRentInfo());
    fixture.detectChanges();

    let messageEl = fixture.debugElement.query(By.css('.message .header'));

    expect(messageEl).toBeNull();
  });

  it('should correctly show model name in table', () => {
    let testRentInfo = getTestRentInfo();
    component.rentList.push(testRentInfo);
    fixture.detectChanges();

    let modelEl = fixture.debugElement.query(By.css('td.model')).nativeElement;

    expect(modelEl.textContent).toContain(testRentInfo.model.brand + ' ' + testRentInfo.model.model);
  });

  it('should correctly show landlord name in table', () => {
    let testRentInfo = getTestRentInfo();
    component.rentList.push(testRentInfo);
    fixture.detectChanges();

    let landlordEl = fixture.debugElement.query(By.css('td.landlord')).nativeElement;

    expect(landlordEl.textContent).toContain(testRentInfo.landlord.name + ' ' + testRentInfo.landlord.lastName);
  });

  it('should show cancel button for table row', () => {
    let testRentInfo = getTestRentInfo();
    component.rentList.push(testRentInfo);
    fixture.detectChanges();

    let buttonEl = fixture.debugElement.query(By.css('td .button')).nativeElement;

    expect(buttonEl.textContent).toContain('Rent');
  });
});
