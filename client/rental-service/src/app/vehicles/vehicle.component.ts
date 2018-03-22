import { Component, OnInit } from '@angular/core';
import {ApiService} from "../api.service";
import {UserInfo} from "../user-info";
import {UserService} from "../user.service";
import {Model} from "../model";
import {UserRole} from "../user-role";

@Component({
  selector: 'vehicles',
  templateUrl: './vehicle.component.html',
  styleUrls: ['./vehicle.component.css']
})
export class VehicleComponent implements OnInit {

  brandToAdd: string = '';
  modelToAdd: string = '';
  models: Model[] = [];
  user = new UserInfo();
  vehiclesInRent = [];
  UserRole = UserRole;

  constructor(private api: ApiService, private userService: UserService) {
    this.user = this.userService.getUser();
  }

  ngOnInit() {
    this.getVehicleModels();
    if (this.user.role == UserRole.landlord.toUpperCase()) this.getOwnerVehicles();
    if (this.user.role == UserRole.customer.toUpperCase()) {}
  }

  getVehicleModels() {
    this.api.getVehicleModels().subscribe((response: any) => {
      this.models = response.modelInfoList;
      console.log(this.models);
    });
  }

  addModel() {
    this.api.addVehicleModel(this.brandToAdd, this.modelToAdd).subscribe(response => {
      this.brandToAdd = '';
      this.modelToAdd = '';
      this.getVehicleModels();
    });
  }

  onAddVehicle(model) {
    this.api.addVehicle(this.user.id, model.toString()).subscribe(response => {
      this.getOwnerVehicles();
    });
  }

  getOwnerVehicles() {
    this.api.getOwnerVehicles(this.user.id).subscribe((response: any) => {
      console.log(response);
      let vehicles = [];
      response['vehicleInfoList'].forEach((vehicle: any) => {
        vehicles.push({
          brand: vehicle.modelInfo.brand,
          model: vehicle.modelInfo.model,
          status: vehicle.isBusy ? 'Busy' : 'Free'
        });
      });
      this.vehiclesInRent = vehicles;
    });
  }
}
