import {Injectable} from '@angular/core';
import {UserInfo} from "./user-info";
import {UserRole} from "./user-role";

@Injectable()
export class UserService {
  public setUser(user: UserInfo) {
    this.setUserFromParameters(user.id, user.name, user.lastName, user.email, user.role);
  }

  public setUserFromParameters(id: number, name: string, lastName: string, email: string, role: string) {
    localStorage.setItem('id', id.toString());
    localStorage.setItem('name', name);
    localStorage.setItem('lastName', lastName);
    localStorage.setItem('email', email);
    localStorage.setItem('role', role);
  }

  public getUser(): UserInfo {
    let user = new UserInfo();
    let id: number = Number(localStorage.getItem('id'));
    let name = localStorage.getItem('name');
    let lastName = localStorage.getItem('lastName');
    let email = localStorage.getItem('email');
    let role = localStorage.getItem('role');
    user.set(id, name, lastName, email, role as UserRole);
    return user;
  }
}
