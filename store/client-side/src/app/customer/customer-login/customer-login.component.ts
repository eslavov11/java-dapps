import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {NgForm} from "@angular/forms";
import {ContractService} from "../../shared/services/contract.service";

@Component({
  selector: 'app-customer-login',
  templateUrl: './customer-login.component.html',
  styleUrls: ['./customer-login.component.css']
})
export class CustomerLoginComponent implements OnInit {
  constructor(private contractService: ContractService,
              private router: Router) {
  }

  ngOnInit() {
  }

  async onSubmit(f: NgForm) {
    //TODO: login
    this.router.navigate(['']);
  }
}
