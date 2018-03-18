import {Injectable} from '@angular/core';
import {UserInfo} from "./user-info";

@Injectable()
export class UserService {
  public setUser(user: UserInfo) {
    localStorage.setItem('id', user.id.toString());
    localStorage.setItem('name', user.name);
    localStorage.setItem('lastName', user.lastName);
    localStorage.setItem('email', user.email);
    localStorage.setItem('role', user.role);
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
    user.set(id, name, lastName, email, role);
    return user;
  }
}
