import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {VehicleComponent} from './vehicle.component';
import {AvatarService} from "../shared/avatar.service";
import {HttpClientModule} from "@angular/common/http";
import {UserService} from "../shared/user.service";
import {AuthService} from "../shared/auth.service";
import {FormsModule} from "@angular/forms";
import {RouterTestingModule} from "@angular/router/testing";
import {ApiService} from "../shared/api.service";
import {AppModule} from "../app.module";
import {UserRole} from "../structures/user-role";
import {UserInfo} from "../structures/user-info";
import {By} from "@angular/platform-browser";
import {Model} from "../structures/model";
import {RentInfo} from "../structures/rent-info";

let getTestModel = (): Model => {
  let model = new Model();
  model.brand = 'TestBrand';
  model.model = 'TestModel';

  return model;
};

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

describe('VehicleComponent', () => {
  let component: VehicleComponent;
  let fixture: ComponentFixture<VehicleComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [
        // VehicleComponent,
        // PersonInfoComponent,
        // LandlordVehiclesComponent,
        // CustomerVehiclesComponent,
        // VehicleModelsComponent
      ],
      imports: [
        FormsModule,
        RouterTestingModule,
        HttpClientModule,
        AppModule
      ],
      providers: [
        AuthService,
        ApiService,
        AvatarService,
        UserService
      ]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(VehicleComponent);
    component = fixture.componentInstance;
    let user = new UserInfo();
    user.set(3, 'TestName', 'TestLastName', 'TestEmail', UserRole.customer);
    component.user = user;
    component.message = 'message';
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should show user info card', () => {
    let userInfoEl = fixture.debugElement.query(By.css('person-info')).nativeElement;
    expect(userInfoEl).toBeDefined();
  });

  it('should show model list for landlord', () => {
    component.user.role = UserRole.landlord;
    component.models.push(getTestModel());
    fixture.detectChanges();
    fixture.whenStable().then(() => {
      let modelListEl = fixture.debugElement.query(By.css('vehicle-models')).nativeElement;
      expect(modelListEl).toBeDefined();
    });
  });

  it('should show vehicle list for landlord', () => {
    component.user.role = UserRole.landlord;
    component.userVehicles.push(getTestRentInfo());
    fixture.detectChanges();
    fixture.whenStable().then(() => {
      let landlordVehiclesEl = fixture.debugElement.query(By.css('landlord-vehicles')).nativeElement;
      expect(landlordVehiclesEl).toBeDefined();
    });
  });

  it('should show rented list for customer', () => {
    component.userVehicles.push(getTestRentInfo());
    fixture.detectChanges();
    fixture.whenStable().then(() => {
      let customerVehiclesEl = fixture.debugElement.query(By.css('customer-vehicles')).nativeElement;
      expect(customerVehiclesEl).toBeDefined();
    });
  });

  it('should show available list for customer', () => {
    component.availableVehicles.push(getTestRentInfo());
    fixture.detectChanges();
    fixture.whenStable().then(() => {
      let vehiclesForRentEl = fixture.debugElement.query(By.css('vehicles-for-rent')).nativeElement;
      expect(vehiclesForRentEl).toBeDefined();
    });
  });
});
