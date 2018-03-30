import {Component, OnInit} from '@angular/core';
import {ContractService} from '../../shared/services/contract.service';
import {Router} from '@angular/router';
import {NgForm} from '@angular/forms';
import {CustomerService} from "../../shared/services/customer.service";
import {Customer} from "../../shared/models/customer";

@Component({
  selector: 'app-customer-register',
  templateUrl: './customer-register.component.html',
  styleUrls: ['./customer-register.component.css']
})
export class CustomerRegisterComponent implements OnInit {
  constructor(private contractService: ContractService,
              private customerService: CustomerService,
              private router: Router) {
  }

  async ngOnInit() {
    // await this.contractService.loadAccounts();
    // await this.contractService.getBalance();
  }

  async onSubmit(f: NgForm) {
    // await this.contractService.registerCustomer(f.value.name);

    const customer = {
      username: f.value.username,
      password: f.value.password,
    };
    this.customerService.createCustomer(customer);
    this.router.navigate(['']);
  }
}
