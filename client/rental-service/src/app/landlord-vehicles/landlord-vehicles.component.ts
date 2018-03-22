import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Model} from "../model";

@Component({
  selector: 'landlord-vehicles',
  templateUrl: './landlord-vehicles.component.html',
  styleUrls: ['./landlord-vehicles.component.css']
})
export class LandlordVehiclesComponent implements OnInit {

  @Input() vehicleList;
  @Input() modelList: Model[];

  @Output() vehicleToAdd: Model;
  @Output() onAddVehicle: EventEmitter<Model> = new EventEmitter();

  constructor() { }

  ngOnInit() {
  }

}
