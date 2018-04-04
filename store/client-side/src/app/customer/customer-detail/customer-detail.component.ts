import {Component, OnDestroy, OnInit} from '@angular/core';
import {ContractService} from "../../shared/services/contract.service";
import {Customer} from "../../shared/models/customer";
import {ActivatedRoute} from "@angular/router";
import {CustomerService} from "../../shared/services/customer.service";
import {Web3Service} from "../../shared/services/web3.service";
import {User} from "../../shared/models/user";
import {UserService} from '../../shared/services/user.service';

@Component({
  selector: 'app-customer-detail',
  templateUrl: './customer-detail.component.html',
  styleUrls: ['./customer-detail.component.css']
})
export class CustomerDetailComponent implements OnInit {
  customer: Customer;
  balance: number;
  currentUser: User;

  private sub: any;

  constructor(private contractService: ContractService,
              private customerService: CustomerService,
              private userService: UserService,
              private web3Service: Web3Service,
              private route: ActivatedRoute,) {
    this.customer = new Customer();
  }

  async ngOnInit() {
    this.balance = await this.web3Service.getBalance();
    this.currentUser = this.userService.currentUser;
    this.customer.username = this.currentUser.username;
    this.customer.address = this.web3Service.web3.eth.accounts.wallet[0].address;
    this.customer.items = [];
    const customerContract = await this.contractService.getCustomer(this.customer.address);
    if (customerContract) {
      this.customer = customerContract;
    }
  }
}
