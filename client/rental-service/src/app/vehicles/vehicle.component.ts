import {Component, OnInit} from '@angular/core';
import {ApiService} from "../shared/api.service";
import {UserInfo} from "../structures/user-info";
import {UserService} from "../shared/user.service";
import {Model} from "../structures/model";
import {UserRole} from "../structures/user-role";
import {RentInfo} from "../structures/rent-info";

@Component({
  selector: 'userVehicles',
  templateUrl: './vehicle.component.html',
  styleUrls: ['./vehicle.component.css']
})
export class VehicleComponent implements OnInit {
  models: Model[] = [];
  user = new UserInfo();
  userVehicles = [];
  availableVehicles = [];
  UserRole = UserRole;
  message: string = '';

  constructor(private api: ApiService, private userService: UserService) {
    this.user = this.userService.getUser();
  }

  ngOnInit() {
    if (this.user.role == UserRole.landlord.toUpperCase()) {
      this.message = 'owned';
      this.getVehicleModels();
      this.getOwnerVehicles();
    }
    if (this.user.role == UserRole.customer.toUpperCase()) {
      this.message = 'in rent';
      this.getAvailableVehicles();
      this.getCustomerVehicles();
    }
  }

  getVehicleModels() {
    this.api.getVehicleModels().subscribe((response: any) => {
      this.models = response.modelInfoList;
    });
  }

  onAddModel(model: Model) {
    this.api.addVehicleModel(model.brand, model.model).subscribe(() => {
      this.getVehicleModels();
    });
  }

  onAddVehicle(model) {
    this.api.addVehicle(this.user.id, model.toString()).subscribe(() => {
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
      this.userVehicles = vehicles;
    });
  }

  getCustomerVehicles() {
    this.api.getCustomerVehicles(this.user.id).subscribe(response => {
      console.log(response);
      let vehicles = [];
      response['orderInfoList'].forEach((order: any) => {
        let vehicleInfo = order.vehicleInfo;
        let info = new RentInfo;
        info.model = vehicleInfo.modelInfo;
        info.landlord = vehicleInfo.landlord;
        info.vehicleId = vehicleInfo.id;
        info.orderId = order.id;
        vehicles.push(info);
      });
      this.userVehicles = vehicles;
    })
  }

  getAvailableVehicles() {
    this.api.getAvailableVehicles().subscribe((response: any) => {
      let vehicles = [];
      response['vehicleInfoList'].forEach((vehicle: any) => {
        if (!vehicle.isBusy) {
          let info = new RentInfo;
          info.model = vehicle.modelInfo;
          info.landlord = vehicle.landlord;
          info.vehicleId = vehicle.id;
          vehicles.push(info);
        }
      });
      this.availableVehicles = vehicles;
    })
  }

  onRentVehicle(rentInfo: RentInfo) {
    this.api.rentVehicle(this.user.id, rentInfo.vehicleId).subscribe(response => {
      if (response['status'] == 'SUCCESS') {
        this.getAvailableVehicles();
        this.getCustomerVehicles();
      }
    })
  }

  onCancelRent(rentInfo: RentInfo) {
    this.api.cancelOrder(rentInfo.orderId).subscribe(response => {
      this.getAvailableVehicles();
      this.getCustomerVehicles();
    });
  }
}
