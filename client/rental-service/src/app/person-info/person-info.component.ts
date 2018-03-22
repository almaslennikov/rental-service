import {Component, Input, OnInit} from '@angular/core';
import {AuthService} from '../auth.service';
import {UserInfo} from "../user-info";
import {UserService} from "../user.service";
import {AvatarService} from "../avatar.service";

@Component({
  selector: 'person-info',
  templateUrl: './person-info.component.html',
  styleUrls: ['./person-info.component.css']
})
export class PersonInfoComponent implements OnInit {

  @Input() user: UserInfo;
  @Input() vehiclesInRent;
  avatarUrl = '';

  constructor(private avatarService: AvatarService) {
  }

  ngOnInit() {
    this.avatarUrl = this.avatarService.getAvatarUrl(this.user.id);
  }

}
