import {Component, OnInit} from '@angular/core';
import {ContractService} from '../shared/services/contract.service';
import {UserService} from '../shared/services/user.service';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';
import {User} from "../shared/models/user";

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.css']
})
export class NavComponent implements OnInit {
  private balance: number;
  // private currentUser: User;


  constructor(private contractService: ContractService,
              private userService: UserService,
              private http: HttpClient,
              private router: Router) {
    this.balance = 0;
  }

  async ngOnInit() {
    // await this.contractService.loadAccounts();
    // await this.contractService.getBalance();
    //
    // this.balance = this.contractService.accountBalance;
  }

  logout() {
    this.userService.logout()
      .subscribe(
        data => {
          this.router.navigate(['/customer/login']);
        },
        error => {

        });
  }

  authenticated() {
    return !!JSON.parse(localStorage.getItem('currentUser'));
  }
}
