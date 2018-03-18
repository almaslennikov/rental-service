import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {AuthService} from "../auth.service";
import {UserLoginInfo} from "./user-login-info";

@Component({
  selector: 'login-form',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit{

  user = new UserLoginInfo();

  constructor(
    private route: Router,
    private authService: AuthService) {
  }

  ngOnInit() {
    this.authService.logout();
  }

  onSubmit() {
    console.log(this.authService.isAuthorized());
    if (!this.authService.isAuthorized()) {
      this.authService.handleAuth(this.user.email, this.user.password).subscribe(isAuthorized => {
        console.log(isAuthorized);
        if (isAuthorized) {
          this.route.navigate(['']);
        }
      });
    }
  }

}
