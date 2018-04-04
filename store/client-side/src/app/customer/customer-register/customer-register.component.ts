import {Component, OnInit} from '@angular/core';
import {ContractService} from '../../shared/services/contract.service';
import {Router} from '@angular/router';
import {NgForm} from '@angular/forms';
import {CustomerService} from "../../shared/services/customer.service";
import {Customer} from "../../shared/models/customer";
import {Web3Service} from "../../shared/services/web3.service";

@Component({
  selector: 'app-customer-register',
  templateUrl: './customer-register.component.html',
  styleUrls: ['./customer-register.component.css']
})
export class CustomerRegisterComponent implements OnInit {
  constructor(private contractService: ContractService,
              private customerService: CustomerService,
              private web3Service: Web3Service,
              private router: Router) {
  }

  ngOnInit() {
  }

  async onSubmit(f: NgForm) {
    const customer = {
      username: f.value.username,
      password: f.value.password,
      keystoreJson: null
    };
    const keystoreJson = this.web3Service.createAndEncryptAccount(customer.password);
    customer.keystoreJson = JSON.stringify(keystoreJson);
    this.customerService.createCustomer(customer).subscribe(
      data => {
        alert('Registration successful');
        this.router.navigate(['/customer/login']);
      },
      error => {
      });
  }
}
