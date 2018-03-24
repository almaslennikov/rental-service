import {Component, EventEmitter, Input, Output} from '@angular/core';
import {Model} from "../model";

@Component({
  selector: 'vehicle-models',
  templateUrl: './vehicle-models.component.html'
})
export  class VehicleModelsComponent {

  @Input() models: Model[] = [];

  @Output('onAddModel') onAddModelEvent = new EventEmitter<Model>();

  model = new Model;

  constructor() {
    this.model.reset();
  }

  onAddModel() {
    this.onAddModelEvent.emit(this.model);
    this.model.reset();
  }
}
