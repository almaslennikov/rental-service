import {Component, Input, OnInit} from '@angular/core';
import {AuthService} from '../shared/auth.service';
import {UserInfo} from "../structures/user-info";
import {UserService} from "../shared/user.service";
import {AvatarService} from "../shared/avatar.service";

@Component({
  selector: 'person-info',
  templateUrl: './person-info.component.html',
  styleUrls: ['./person-info.component.css']
})
export class PersonInfoComponent implements OnInit {

  @Input() user: UserInfo;
  @Input() message;
  avatarUrl = '';

  constructor(private avatarService: AvatarService) {
  }

  ngOnInit() {
    this.avatarUrl = this.avatarService.getAvatarUrl(this.user.id);
  }

}
