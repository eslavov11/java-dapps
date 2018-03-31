import { Component, OnInit } from '@angular/core';
import {NgForm} from "@angular/forms";
import {UserService} from "../../shared/services/user.service";

@Component({
  selector: 'app-user-register',
  templateUrl: './user-register.component.html',
  styleUrls: ['./user-register.component.css']
})
export class UserRegisterComponent implements OnInit {

  constructor(private userService: UserService) { }

  ngOnInit() {
  }

  onSubmit(f: NgForm) {
    const user = {
      username: f.value.username,
      password: f.value.password,
    };

    this.userService.initializeWebSocketConnection();
    // this.userService.createUser(user).subscribe(
    //   data => {
    //     alert('Registration successful');
    //     this.router.navigate(['/user/detail']);
    //   },
    //   error => {
    //   });
  }
}
