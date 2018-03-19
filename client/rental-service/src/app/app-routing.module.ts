import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {PersonInfoComponent} from './person-info/person-info.component';
import {LoginComponent} from './login/login.component';
import {LogoutComponent} from "./logout/logout.component";

const routes: Routes = [
  {path: 'info', component: PersonInfoComponent},
  {path: 'login', component: LoginComponent},
  {path: 'register', component: LoginComponent},
  {path: 'logout', component: LogoutComponent},
  {path: '', redirectTo: 'info', pathMatch: 'full'}
];

@NgModule({
  exports: [RouterModule],
  imports: [RouterModule.forRoot(routes)],
})
export class AppRoutingModule {
}
