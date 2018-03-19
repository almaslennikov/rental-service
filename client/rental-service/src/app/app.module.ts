import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';

import {AppComponent} from './app.component';
import {AppRoutingModule} from './app-routing.module';
import {PersonInfoComponent} from './person-info/person-info.component';
import {LoginComponent} from './login/login.component';
import {AuthService} from './auth.service';
import {LogoutComponent} from './logout/logout.component';
import {HttpClientModule} from "@angular/common/http";
import {ApiService} from "./api.service";
import {UserService} from "./user.service";
import {AvatarService} from "./avatar.service";


@NgModule({
  declarations: [
    AppComponent,
    PersonInfoComponent,
    LoginComponent,
    LogoutComponent,
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
