import {Component, OnInit} from '@angular/core';
import {ContractService} from '../shared/services/contract.service';
import {UserService} from '../shared/services/user.service';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.css']
})
export class NavComponent implements OnInit {
  private balance: number;


  constructor(private contractService: ContractService,
              private userService: UserService,
              private http: HttpClient,
              private router: Router) {
    // this.userService.authenticate(undefined, undefined);
    this.balance = 0;
  }

  async ngOnInit() {
    // await this.contractService.loadAccounts();
    // await this.contractService.getBalance();
    //
    // this.balance = this.contractService.accountBalance;
  }

  authenticated() {
    return this.userService.authenticated;
  }

  logout() {
    // this.http.post('logout', {}).finally(() => {
    //   this.userService.authenticated = false;
    //   this.router.navigateByUrl('/');
    // }).subscribe();
  }

}
