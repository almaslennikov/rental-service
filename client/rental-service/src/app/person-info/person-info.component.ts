import { Component, OnInit } from '@angular/core';
import {AuthService} from '../auth.service';

@Component({
  selector: 'app-person-info',
  templateUrl: './person-info.component.html',
  styleUrls: ['./person-info.component.css']
})
export class PersonInfoComponent implements OnInit {

  public status: string;

  constructor(private authService: AuthService) {
    if(this.authService.isAuthorised) {
      this.status = "Authorized!";
    } else {
      this.status = "Not authorized!";
    }
  }

  ngOnInit() {
  }

}
