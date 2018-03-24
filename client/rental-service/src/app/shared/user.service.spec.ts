import { TestBed, inject } from '@angular/core/testing';

import { UserService } from './user.service';
import {AvatarService} from "./avatar.service";
import {HttpClientModule} from "@angular/common/http";
import {AuthService} from "./auth.service";
import {FormsModule} from "@angular/forms";
import {RouterTestingModule} from "@angular/router/testing";
import {ApiService} from "./api.service";

describe('UserService', () => {
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

  it('should be created', inject([UserService], (service: UserService) => {
    expect(service).toBeTruthy();
  }));
});
