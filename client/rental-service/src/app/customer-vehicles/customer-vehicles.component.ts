import {Component, EventEmitter, Input, Output} from '@angular/core';
import {RentInfo} from "../rent-info";

@Component({
  selector: 'customer-vehicles',
  templateUrl: './customer-vehicles.component.html'
})
export class CustomerVehiclesComponent {

  @Input() rentedVehicles: RentInfo[] = [];
  @Output() onCancelRent = new EventEmitter<RentInfo>();

  constructor() {
  }
}
