import { Component, OnInit } from '@angular/core';
import {UserService} from "../../shared/services/user.service";

@Component({
  selector: 'app-user-detail',
  templateUrl: './user-detail.component.html',
  styleUrls: ['./user-detail.component.css']
})
export class UserDetailComponent implements OnInit {

  constructor(private userService: UserService) { }

  ngOnInit() {
  }

}
