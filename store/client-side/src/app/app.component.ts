import {Component} from '@angular/core';
import {Router} from "@angular/router";
import {ContractService} from "./shared/services/contract.service";
import {Web3Service} from "./shared/services/web3.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  constructor(private router: Router,
              private contractService: ContractService,
              private web3Service: Web3Service) {
    this.web3Service.initWeb3();
    this.contractService.initContract();
  }
}
