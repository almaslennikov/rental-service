import {Component, OnInit} from '@angular/core';
import {AuthService} from '../auth.service';
import {UserInfo} from "../user-info";
import {UserService} from "../user.service";
import {AvatarService} from "../avatar.service";

@Component({
  selector: 'app-person-info',
  templateUrl: './person-info.component.html',
  styleUrls: ['./person-info.component.css']
})
export class PersonInfoComponent implements OnInit {

  user: UserInfo;
  avatarUrl = '';

  constructor(private authService: AuthService, private userService: UserService, private avatarService: AvatarService) {
    this.user = this.userService.getUser();
    this.avatarUrl = this.avatarService.getAvatarUrl(this.user.id);
  }

  ngOnInit() {
  }

}
