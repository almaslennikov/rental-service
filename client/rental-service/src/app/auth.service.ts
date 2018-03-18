import {Injectable} from '@angular/core';
import {Observable} from "rxjs/Rx";
import {HttpClient} from "@angular/common/http";

class CreateUserResponse {
  public token: string;
}

@Injectable()
export class AuthService {

  get isAuthorised(): boolean {
    return AuthService.checkIfAuthorized();
  }

  constructor(private http: HttpClient) {
  }

  handleAuth(name: string, password: string): Observable<boolean> {
    //TODO Add correct handle of auth
    localStorage.setItem('authToken', 'asdasdasda');
    console.log('1');
    return Observable.create(observer => {
      console.log('1');
      observer.next(AuthService.checkIfAuthorized());
    });
  }

  static checkIfAuthorized(): boolean {
    return localStorage.getItem('authToken') != '';
  }

  logout(): Observable<boolean> {
    localStorage.setItem('authToken', '');
    return Observable.create(observer => {
      observer.next(AuthService.checkIfAuthorized());
    });
  }
}
