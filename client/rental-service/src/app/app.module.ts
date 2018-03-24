import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';

import {AppComponent} from './app.component';
import {AppRoutingModule} from './app-routing.module';
import {PersonInfoComponent} from './person-info/person-info.component';
import {AuthComponent} from './auth/auth.component';
import {AuthService} from './shared/auth.service';
import {LogoutComponent} from './logout/logout.component';
import {HttpClientModule} from "@angular/common/http";
import {ApiService} from "./shared/api.service";
import {UserService} from "./shared/user.service";
import {AvatarService} from "./shared/avatar.service";
import {VehicleComponent} from './vehicles/vehicle.component';
import {LandlordVehiclesComponent} from './landlord-vehicles/landlord-vehicles.component';
import {CustomerVehiclesComponent} from './customer-vehicles/customer-vehicles.component';
import {VehicleModelsComponent} from './vehicle-models/vehicle-models.component';
import {VehiclesForRentComponent} from './vehicles-for-rent/vehicles-for-rent.component';
import {APP_BASE_HREF} from '@angular/common';


@NgModule({
  declarations: [
    AppComponent,
    PersonInfoComponent,
    AuthComponent,
    LogoutComponent,
    VehicleComponent,
    LandlordVehiclesComponent,
    CustomerVehiclesComponent,
    VehicleModelsComponent,
    VehiclesForRentComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [AuthService, ApiService, UserService, AvatarService, {provide: APP_BASE_HREF, useValue : '/' }],
  bootstrap: [AppComponent]
})
export class AppModule {
}
