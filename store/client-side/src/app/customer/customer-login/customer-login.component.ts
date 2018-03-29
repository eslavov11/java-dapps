import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {NgForm} from "@angular/forms";
import {ContractService} from "../../shared/services/contract.service";
import {HttpClient} from "@angular/common/http";
import {UserService} from "../../shared/services/user.service";

@Component({
  selector: 'app-customer-login',
  templateUrl: './customer-login.component.html',
  styleUrls: ['./customer-login.component.css']
})
export class CustomerLoginComponent implements OnInit {
  private credentials = {username: '', password: ''};

  constructor(private contractService: ContractService,
              private userService: UserService,
              private router: Router,
              private http: HttpClient) {
  }

  ngOnInit() {
  }

  login() {
    this.userService.authenticate(this.credentials, () => {
      this.router.navigateByUrl('/');
    });

    return false;
  }
}
