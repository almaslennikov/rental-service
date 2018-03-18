import {Component, OnInit} from '@angular/core';
import {AuthService} from '../auth.service';
import {UserInfo} from "../user-info";
import {UserService} from "../user.service";


@Component({
  selector: 'app-person-info',
  templateUrl: './person-info.component.html',
  styleUrls: ['./person-info.component.css']
})
export class PersonInfoComponent implements OnInit {

  user: UserInfo;

  constructor(private authService: AuthService, private userService: UserService) {
    this.user = this.userService.getUser();
  }

  ngOnInit() {
  }

}
