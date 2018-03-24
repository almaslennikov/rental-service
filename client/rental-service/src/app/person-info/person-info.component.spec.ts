import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {PersonInfoComponent} from './person-info.component';
import {AvatarService} from "../shared/avatar.service";
import {HttpClientModule} from "@angular/common/http";
import {UserService} from "../shared/user.service";
import {AuthService} from "../shared/auth.service";
import {FormsModule} from "@angular/forms";
import {RouterTestingModule} from "@angular/router/testing";
import {ApiService} from "../shared/api.service";
import {UserInfo} from "../structures/user-info";
import {UserRole} from "../structures/user-role";
import {By} from "@angular/platform-browser";

describe('PersonInfoComponent', () => {
  let component: PersonInfoComponent;
  let fixture: ComponentFixture<PersonInfoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [PersonInfoComponent],
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
    fixture = TestBed.createComponent(PersonInfoComponent);
    component = fixture.componentInstance;

    let user = new UserInfo();
    user.set(3, 'TestName', 'TestLastName', 'TestEmail', UserRole.customer);
    component.user = user;
    component.message = 'message';

    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should get correct avatar url', () => {
    let imageEl = fixture.debugElement.query(By.css('img')).nativeElement;
    let avatarService: AvatarService = TestBed.get(AvatarService);
    expect(imageEl.src).toContain(avatarService.getAvatarUrl(component.user.id));
  });

  it('should display correct user name and lastName', () => {
    let nameEl = fixture.debugElement.query(By.css('.header')).nativeElement;
    expect(nameEl.textContent).toEqual(component.user.name + ' ' + component.user.lastName);
  });

  it('should display correct user e-mail', () => {
    let emailEl = fixture.debugElement.query(By.css('.email')).nativeElement;
    expect(emailEl.textContent).toEqual(component.user.email);
  });

  it('should display correct user role', () => {
    let roleEl = fixture.debugElement.query(By.css('.role')).nativeElement;
    expect(roleEl.textContent.toLowerCase()).toContain(component.user.role);
  });

  it('should display correct message', () => {
    let messageEl = fixture.debugElement.query(By.css('.message')).nativeElement;
    expect(messageEl.textContent).toContain(component.message);
  });
});
