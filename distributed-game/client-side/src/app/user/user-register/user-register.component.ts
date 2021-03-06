import { Component, OnInit } from '@angular/core';
import {NgForm} from "@angular/forms";
import {UserService} from "../../shared/services/user.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-user-register',
  templateUrl: './user-register.component.html',
  styleUrls: ['./user-register.component.css']
})
export class UserRegisterComponent implements OnInit {

  constructor(private userService: UserService,
              private router: Router) { }

  ngOnInit() {
  }

  onSubmit(f: NgForm) {
    const user = {
      username: f.value.username,
      password: f.value.password,
    };

    this.userService.createUser(user);
    this.router.navigate(['/user/detail']);
  }
}
