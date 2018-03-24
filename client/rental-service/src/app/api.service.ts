import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {UserInfo} from "./user-info";

@Injectable()
export class ApiService {

  apiUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) {
  }

  register(user: UserInfo, password: string) {
    let httpParams = new HttpParams();

    httpParams = httpParams.append('name', user.name);
    httpParams = httpParams.append('lastName', user.lastName);
    httpParams = httpParams.append('email', user.email);
    httpParams = httpParams.append('role', user.role.toUpperCase());
    httpParams = httpParams.append('password', password);

    return this.http.post(this.apiUrl + '/users/new-user', httpParams);
  }

  login(email: string, password: string) {
    let httpParams = new HttpParams();

    httpParams = httpParams.set('email', email);
    httpParams = httpParams.set('password', password);

    return this.http.get(this.apiUrl + '/users/authorize-user', {params: httpParams});
  }

  getVehicleModels() {
    return this.http.get(this.apiUrl + '/models/available-models');
  }

  addVehicleModel(brand: string, model: string) {
    let httpParams = new HttpParams();

    httpParams = httpParams.set('brand', brand);
    httpParams = httpParams.set('model', model);

    return this.http.post(this.apiUrl + '/models/new-model', httpParams);
  }

  addVehicle(ownerId: number, modelId: number) {
    let httpParams = new HttpParams();

    httpParams = httpParams.set('ownerId', ownerId.toString());
    httpParams = httpParams.set('modelId', modelId.toString());

    return this.http.post(this.apiUrl + '/vehicles/new-vehicle', httpParams);
  }

  getOwnerVehicles(ownerId: number) {
    let httpParams = new HttpParams();

    httpParams = httpParams.set('id', ownerId.toString());

    return this.http.get(this.apiUrl + '/vehicles/vehicles-by-landlord-id', {params: httpParams});
  }

  getCustomerVehicles(customerId: number) {
    let httpParams = new HttpParams();

    httpParams = httpParams.set('id', customerId.toString());

    return this.http.get(this.apiUrl + '/orders/orders-by-customer-id', {params: httpParams});
  }

  getAvailableVehicles() {
    return this.http.get(this.apiUrl + '/vehicles/available-vehicles');
  }

  rentVehicle(customerId: number, vehicleId: number) {
    let httpParams = new HttpParams();

    httpParams = httpParams.set('customerId', customerId.toString());
    httpParams = httpParams.set('vehicleId', vehicleId.toString());

    return this.http.post(this.apiUrl + '/orders/order-vehicle', httpParams);
  }

  cancelOrder(orderId: number) {
    let httpParams = new HttpParams();

    httpParams = httpParams.set('id', orderId.toString());

    return this.http.delete(this.apiUrl + '/orders/order', {params: httpParams});
  }
}
