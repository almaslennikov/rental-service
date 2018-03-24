import {inject, TestBed} from '@angular/core/testing';

import {ApiService} from './api.service';
import {AvatarService} from "./avatar.service";
import {HttpClientModule} from "@angular/common/http";
import {UserService} from "./user.service";
import {AuthService} from "./auth.service";
import {FormsModule} from "@angular/forms";
import {RouterTestingModule} from "@angular/router/testing";

describe('ApiService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
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
    });
  });

  it('should be created', inject([ApiService], (service: ApiService) => {
    expect(service).toBeTruthy();
  }));
});
