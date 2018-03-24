import {Component, EventEmitter, Input, Output} from '@angular/core';
import {RentInfo} from "../structures/rent-info";

@Component({
  selector: 'vehicles-for-rent',
  templateUrl: './vehicles-for-rent.component.html'
})
export class VehiclesForRentComponent {

  @Input() rentList: RentInfo[] = [];
  @Output() onRentVehicle = new EventEmitter<RentInfo>();

  constructor() {
  }

}
