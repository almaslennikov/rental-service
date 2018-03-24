import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LandlordVehiclesComponent } from './landlord-vehicles.component';
import {AvatarService} from "../shared/avatar.service";
import {HttpClientModule} from "@angular/common/http";
import {UserService} from "../shared/user.service";
import {AuthService} from "../shared/auth.service";
import {FormsModule} from "@angular/forms";
import {RouterTestingModule} from "@angular/router/testing";
import {ApiService} from "../shared/api.service";
import {UserRole} from "../structures/user-role";
import {UserInfo} from "../structures/user-info";

describe('LandlordVehiclesComponent', () => {
  let component: LandlordVehiclesComponent;
  let fixture: ComponentFixture<LandlordVehiclesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LandlordVehiclesComponent ],
      imports: [
        FormsModule,
        RouterTestingModule,
        HttpClientModule
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
    fixture = TestBed.createComponent(LandlordVehiclesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should show user info card', () => {

    expect(component).toBeTruthy();
  });
});
