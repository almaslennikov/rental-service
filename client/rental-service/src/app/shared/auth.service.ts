import {Injectable} from '@angular/core';
import {Observable} from "rxjs/Rx";
import {ApiService} from "./api.service";
import {map} from "rxjs/operators";
import {UserService} from "./user.service";
import {UserInfo} from "../structures/user-info";

@Injectable()
export class AuthService {

  constructor(private api: ApiService, private user: UserService) {
  }

  handleAuth(email: string, password: string): Observable<boolean> {
    return this.api.login(email, password).pipe(map((response: any) => {
      if (response.status == 'SUCCESS') {
        let userInfo = response.userInfo;
        localStorage.setItem('authToken', userInfo.id);
        this.user.setUserFromParameters(userInfo.id, userInfo.name, userInfo.lastName, userInfo.email, userInfo.role);
      }
      return response['status'] == 'SUCCESS';
    }));
  }

  handleRegister(user: UserInfo, password: string): Observable<boolean> {
    return this.api.register(user, password).pipe(map((response: any) => {
      return response['status'] == 'SUCCESS';
    }));
  }

  isAuthorized(): boolean {
    return localStorage.getItem('authToken') != '';
  }

  logout() {
    localStorage.setItem('authToken', '');
  }
}
