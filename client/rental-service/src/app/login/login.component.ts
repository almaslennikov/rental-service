import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {AuthService} from "../auth.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private route: Router,
    private authService: AuthService) {
  }

  ngOnInit() {
    this.initForm();
  }

  initForm() {
    this.loginForm = this.formBuilder.group({
      name: ['', [
        Validators.required,
        Validators.pattern(/[A-z]/)
      ]],
      password: ['password', [
        Validators.required,
        Validators.pattern(/[A-z]/)
      ]]
    })
  }

  isControlInvalid(controlName: string): boolean {
    const control = this.loginForm.controls[controlName];
    return control.invalid && control.touched;
  }

  onSubmit() {
    const controls = this.loginForm.controls;

    console.log('1');
    if (this.loginForm.valid && !this.authService.isAuthorised) {
      console.log('2');
      this.authService.handleAuth(this.loginForm.value.name, this.loginForm.value.password).subscribe(isAuthorized => {
        console.log('3');
        if(isAuthorized) {
          this.route.navigate(['']);
        }
      });
    } else {
      Object.keys(controls).forEach(controlName => controls[controlName].markAsTouched());
    }
  }

}
