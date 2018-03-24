import {Model} from "./model";
import {UserInfo} from "./user-info";

export class RentInfo {
  model: Model;
  landlord: UserInfo;
  vehicleId: number;
  orderId: number;
}
