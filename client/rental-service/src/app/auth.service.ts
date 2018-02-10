import {Injectable} from '@angular/core';
import {Observable} from "rxjs/Observable";

@Injectable()
export class AuthService {

  get isAuthorised(): boolean {
    return AuthService.checkIfAuthorized();
  }

  constructor() { }

  handleAuth(name: string, password: string): Observable<boolean> {
    //TODO Add correct handle of auth
    console.log('1');
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
