import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';

@Injectable()
export class ApiService {

  constructor(private http: HttpClient) {
  }

  register() {

  }

  login(email: string, password: string) {
    let httpParams = new HttpParams();

    httpParams = httpParams.append('email', email);
    httpParams = httpParams.append('password', password);

    return this.http.get('http://localhost:8080/authorizeUser', {params: httpParams});
  }

}
