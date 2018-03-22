import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';

import {AppComponent} from './app.component';
import {AppRoutingModule} from './app-routing.module';
import {PersonInfoComponent} from './person-info/person-info.component';
import {AuthComponent} from './auth/auth.component';
import {AuthService} from './auth.service';
import {LogoutComponent} from './logout/logout.component';
import {HttpClientModule} from "@angular/common/http";
import {ApiService} from "./api.service";
import {UserService} from "./user.service";
import {AvatarService} from "./avatar.service";
import { VehicleComponent } from './vehicles/vehicle.component';
import { LandlordVehiclesComponent } from './landlord-vehicles/landlord-vehicles.component';
import { CustomerVehiclesComponent } from './customer-vehicles/customer-vehicles.component';


@NgModule({
  declarations: [
    AppComponent,
    PersonInfoComponent,
    AuthComponent,
    LogoutComponent,
    VehicleComponent,
    LandlordVehiclesComponent,
    CustomerVehiclesComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [AuthService, ApiService, UserService, AvatarService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
