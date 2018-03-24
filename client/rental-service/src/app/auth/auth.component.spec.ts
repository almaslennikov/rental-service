import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {AuthComponent} from './auth.component';
import {FormsModule} from "@angular/forms";
import {RouterTestingModule} from "@angular/router/testing";
import {AvatarService} from "../shared/avatar.service";
import {UserService} from "../shared/user.service";
import {AuthService} from "../shared/auth.service";
import {ApiService} from "../shared/api.service";
import {HttpClientModule} from "@angular/common/http";
import {AuthMode} from "./auth-mode";
import {By} from "@angular/platform-browser";

describe('AuthComponent', () => {
  let component: AuthComponent;
  let fixture: ComponentFixture<AuthComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [AuthComponent],
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
    fixture = TestBed.createComponent(AuthComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should not display name input in login mode', () => {
    component.mode = AuthMode.login;
    fixture.detectChanges();
    let nameEl = fixture.debugElement.query(By.css('input[name="name"]'));
    expect(nameEl).toBeNull();
  });

  it('should display name input in register mode', () => {
    component.mode = AuthMode.register;
    fixture.detectChanges();
    let nameEl = fixture.debugElement.query(By.css('input[name="name"]'));
    expect(nameEl).toBeDefined();
  });

  it('should display mode name on button', () => {
    component.mode = AuthMode.register;
    fixture.detectChanges();
    let buttonEl = fixture.debugElement.query(By.css('.button')).nativeElement;
    expect(buttonEl.textContent.toLowerCase()).toContain(component.mode.toLowerCase());
  });

  it('should show error message on empty form submit', () => {
    fixture.debugElement.query(By.css('.button')).nativeElement.click();
    let errorMessageEl = fixture.debugElement.query(By.css('.error'));
    expect(errorMessageEl).toBeDefined();
  });
});
