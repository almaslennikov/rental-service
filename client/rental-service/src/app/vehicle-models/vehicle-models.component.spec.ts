import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { VehicleModelsComponent } from './vehicle-models.component';
import {AvatarService} from "../shared/avatar.service";
import {HttpClientModule} from "@angular/common/http";
import {UserService} from "../shared/user.service";
import {AuthService} from "../shared/auth.service";
import {FormsModule} from "@angular/forms";
import {RouterTestingModule} from "@angular/router/testing";
import {ApiService} from "../shared/api.service";
import {Model} from "../structures/model";
import {By} from "@angular/platform-browser";

let getTestModel = (): Model => {
  let model = new Model();
  model.brand = 'TestBrand';
  model.model = 'TestModel';

  return model;
};

describe('VehicleModelsComponent', () => {
  let component: VehicleModelsComponent;
  let fixture: ComponentFixture<VehicleModelsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ VehicleModelsComponent ],
      imports: [
        FormsModule,
        RouterTestingModule,
        HttpClientModule
      ],
      providers: [
        AuthService,
        ApiService,
        AvatarService,
        UserService
      ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(VehicleModelsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should show inputs with empty list', () => {
    component.models = [];
    fixture.detectChanges();

    let inputBrandEl = fixture.debugElement.query(By.css('.input.brand')).nativeElement;
    expect(inputBrandEl).toBeDefined();

    let inputModelEl = fixture.debugElement.query(By.css('.input.model')).nativeElement;
    expect(inputModelEl).toBeDefined();
  });

  it('should show add button with empty list', () => {
    component.models = [];
    fixture.detectChanges();

    let inputBrandEl = fixture.debugElement.query(By.css('.button')).nativeElement;
    expect(inputBrandEl).toBeDefined();
  });

  it('should show inputs with non-empty list', () => {
    component.models.push(getTestModel());
    fixture.detectChanges();

    let inputBrandEl = fixture.debugElement.query(By.css('.input.brand')).nativeElement;
    expect(inputBrandEl).toBeDefined();

    let inputModelEl = fixture.debugElement.query(By.css('.input.model')).nativeElement;
    expect(inputModelEl).toBeDefined();
  });

  it('should show add button with non-empty list', () => {
    component.models.push(getTestModel());
    fixture.detectChanges();

    let buttonEl = fixture.debugElement.query(By.css('.button')).nativeElement;
    expect(buttonEl).toBeDefined();
  });

  it('should correctly show model brand and name', () => {
    let testModel = getTestModel();
    component.models.push(testModel);
    fixture.detectChanges();

    let brandEl = fixture.debugElement.query(By.css('td.brand')).nativeElement;
    expect(brandEl.textContent).toContain(testModel.brand);

    let modelEl = fixture.debugElement.query(By.css('td.model')).nativeElement;
    expect(modelEl.textContent).toContain(testModel.model);
  });
});
