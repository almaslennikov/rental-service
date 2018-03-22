import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
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

    return this.http.post('http://localhost:8080/users/new-user', httpParams);
  }

  login(email: string, password: string) {
    let httpParams = new HttpParams();

    httpParams = httpParams.set('email', email);
    httpParams = httpParams.set('password', password);

    return this.http.get('http://localhost:8080/users/authorize-user', {params: httpParams});
  }

  getVehicleModels() {
    return this.http.get('http://localhost:8080/models/available-models');
  }

  addVehicleModel(brand: string, model: string) {
    let httpParams = new HttpParams();

    httpParams = httpParams.set('brand', brand);
    httpParams = httpParams.set('model', model);

    return this.http.post('http://localhost:8080/models/new-model', httpParams);
  }

  addVehicle(ownerId: number, modelId: number) {
    let httpParams = new HttpParams();

    httpParams = httpParams.set('ownerId', ownerId.toString());
    httpParams = httpParams.set('modelId', modelId.toString());

    return this.http.post('http://localhost:8080/vehicles/new-vehicle', httpParams);
  }

  getOwnerVehicles(ownerId: number) {
    let httpParams = new HttpParams();

    httpParams = httpParams.set('id', ownerId.toString());

    return this.http.get('http://localhost:8080/vehicles/vehicles-by-landlord-id', {params: httpParams});
  }

}
