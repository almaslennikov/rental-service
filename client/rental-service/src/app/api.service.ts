import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {UserInfo} from "./user-info";

@Injectable()
export class ApiService {

  constructor(private http: HttpClient) {
  }

  register(user: UserInfo, password: string) {
    let httpParams = new HttpParams();

    httpParams = httpParams.append('name', user.name);
    httpParams = httpParams.append('lastName', user.lastName);
    httpParams = httpParams.append('email', user.email);
    httpParams = httpParams.append('role', user.role.toUpperCase());
    httpParams = httpParams.append('password', password);

    return this.http.get('http://localhost:8080/addNewUser', {params: httpParams});
  }

  login(email: string, password: string) {
    let httpParams = new HttpParams();

    httpParams = httpParams.append('email', email);
    httpParams = httpParams.append('password', password);

    return this.http.get('http://localhost:8080/authorizeUser', {params: httpParams});
  }

}
