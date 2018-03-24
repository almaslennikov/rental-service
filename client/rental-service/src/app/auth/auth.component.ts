import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {AuthService} from "../shared/auth.service";
import {UserInfo} from "../structures/user-info";
import {AuthMode} from "./auth-mode";

const emailPattern = '^[a-z0-9!#$%&\'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&\'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$';
const passwordPattern = '^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,16}$';

@Component({
  selector: 'login-form',
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.css']
})
export class AuthComponent implements OnInit {

  user = new UserInfo();
  userPassword: string = '';
  userConfirmedPassword: string = '';
  errorMessages = [];
  mode: AuthMode;
  AuthMode = AuthMode;

  constructor(
    private route: Router,
    private authService: AuthService) {
  }

  ngOnInit() {
    this.authService.logout();
    console.log(this.authService.isAuthorized());
    if (this.route.routerState.snapshot.url.includes('login')) {
      this.mode = AuthMode.login;
    } else {
      this.mode = AuthMode.register;
    }
  }

  onSubmit() {
    console.log(this.authService.isAuthorized());
    if (!this.authService.isAuthorized()) {
      if (this.validate()) {
        if (this.mode == AuthMode.login) {
          this.authService.handleAuth(this.user.email, this.userPassword).subscribe(isAuthorized => {
            if (isAuthorized) {
              this.route.navigate(['']);
            } else {
              this.errorMessages.push("Invalid login or password");
            }
          });
        } else {
          this.authService.handleRegister(this.user, this.userPassword).subscribe(isRegistered => {
            if (isRegistered) {
              this.route.navigate(['login']);
            } else {
              this.errorMessages.push("Such user already exists");
            }
          });
        }
      }
    }
  }

  private validate(): boolean {
    this.errorMessages = [];
    let validationResult = true;
    if (this.mode == AuthMode.register) {
      if (!this.user.name.length) {
        this.errorMessages.push('Name is required');
        validationResult = false;
      }
      if (!this.user.lastName.length) {
        this.errorMessages.push('Last name is required');
        validationResult = false;
      }
      if (!this.user.role) {
        this.errorMessages.push('UserRole is required');
        validationResult = false;
      }
      if (this.userPassword != this.userConfirmedPassword) {
        this.errorMessages.push('Passwords must match');
        validationResult = false;
      }
    }
    if (!this.user.email) {
      this.errorMessages.push('E-mail is required');
      validationResult = false;
    }
    if (!this.user.email.match(emailPattern) && this.user.email) {
      this.errorMessages.push('Incorrect e-mail');
      validationResult = false;
    }
    if (!this.userPassword) {
      this.errorMessages.push('Password is required');
      validationResult = false;
    }
    if (!this.userPassword.match(passwordPattern) && this.userPassword) {
      this.errorMessages.push('Password must contain at least 1 letter and 1 digit');
      this.errorMessages.push('Password should contain only letters and digits');
      validationResult = false;
    }

    return validationResult;
  }

}
