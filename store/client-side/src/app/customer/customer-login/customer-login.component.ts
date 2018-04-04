import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {NgForm} from "@angular/forms";
import {ContractService} from "../../shared/services/contract.service";
import {HttpClient} from "@angular/common/http";
import {UserService} from "../../shared/services/user.service";
import {AuthService} from "../../shared/services/auth.service";
import {Web3Service} from "../../shared/services/web3.service";
import {Customer} from "../../shared/models/customer";
import {CustomerService} from "../../shared/services/customer.service";

@Component({
  selector: 'app-customer-login',
  templateUrl: './customer-login.component.html',
  styleUrls: ['./customer-login.component.css']
})
export class CustomerLoginComponent implements OnInit {
  private credentials = {username: '', password: ''};

  constructor(private web3Service: Web3Service,
              private authService: AuthService,
              private customerService: CustomerService,
              private router: Router,
              private http: HttpClient) {
  }

  ngOnInit() {
  }

  async login(f: NgForm) {
    this.credentials.username = f.value.username;
    this.credentials.password = f.value.password;
    this.authService.login(this.credentials).subscribe(
      data => {
        this.customerService.getCustomer().subscribe(customer => {
          this.web3Service.initAccount(customer.keystoreJson, this.credentials.password);
          this.router.navigate(['/']);
        }, error => {
        });
      },
      error => {
      });

    return false;
  }
}
