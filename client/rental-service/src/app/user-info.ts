import {UserRole} from "./user-role";

export class UserInfo {
  email: string;
  id: number;
  name: string;
  lastName: string;
  role = UserRole.customer;

  public set(id: number, name: string, lastName: string, email: string, role: UserRole) {
    this.id = id;
    this.name = name;
    this.lastName = lastName;
    this.email = email;
    this.role = role;
  }

}
