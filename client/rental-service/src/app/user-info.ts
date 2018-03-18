export class UserInfo {
  email: string = '';
  id: number = 0;
  name: string = '';
  lastName: string = '';
  role: string = '';

  public set(id: number, name: string, lastName: string, email: string, role: string) {
    this.id = id;
    this.name = name;
    this.lastName = lastName;
    this.email = email;
    this.role = role;
}

}
