import {Component, EventEmitter, Output} from '@angular/core';
import {Router} from '@angular/router';
import {AuthService} from "../auth.service";
import {UserLoginInfo} from "./user-login-info";

@Component({
  selector: 'login-form',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  @Output() user = new UserLoginInfo();

  constructor(
    private route: Router,
    private authService: AuthService) {
  }

  onSubmit() {
    if (!this.authService.isAuthorised) {
      this.authService.handleAuth(this.user.username, this.user.password).subscribe(isAuthorized => {
        if(isAuthorized) {
          this.route.navigate(['']);
        }
      });
    }
  }

}
