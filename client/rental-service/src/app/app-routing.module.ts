import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {PersonInfoComponent} from './person-info/person-info.component';
import {AuthComponent} from './auth/auth.component';
import {LogoutComponent} from "./logout/logout.component";
import {VehicleComponent} from "./vehicles/vehicle.component";

const routes: Routes = [
  {path: 'login', component: AuthComponent},
  {path: 'register', component: AuthComponent},
  {path: 'logout', component: LogoutComponent},
  {path: '', component: VehicleComponent}
];

@NgModule({
  exports: [RouterModule],
  imports: [RouterModule.forRoot(routes)],
})
export class AppRoutingModule {
}
