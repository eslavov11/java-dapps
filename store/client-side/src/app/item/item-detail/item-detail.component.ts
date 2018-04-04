import {Component, OnDestroy, OnInit} from '@angular/core';
import {ContractService} from '../../shared/services/contract.service';
import {CustomerService} from '../../shared/services/customer.service';
import {Web3Service} from '../../shared/services/web3.service';
import {ActivatedRoute, Router} from '@angular/router';
import {Item} from '../../shared/models/item';
import {UserService} from '../../shared/services/user.service';
import {ItemService} from '../../shared/services/item.service';

@Component({
  selector: 'app-item-detail',
  templateUrl: './item-detail.component.html',
  styleUrls: ['./item-detail.component.css']
})
export class ItemDetailComponent implements OnInit, OnDestroy {
  item: Item;
  private sub: any;

  constructor(private contractService: ContractService,
              private itemService: ItemService,
              private web3Service: Web3Service,
              private userService: UserService,
              private route: ActivatedRoute,
              private router: Router) {
    this.item = new Item();
  }

  async ngOnInit() {
    this.sub = this.route.params.subscribe(params => {
      this.load(params);
    });
  }

  public authenticated() {
    return !!this.userService.currentUser;
  }

  async load(params: any) {
    this.item.id = +params['id'];
    this.item = await this.contractService.getItem(this.item.id);
  }

  async buy() {
    await this.contractService
      .buyItem(this.userService.currentUser, this.item.id, this.item.price);
    this.itemService.buyItem(this.item.id).subscribe(
      data => {
        alert('Item bought successfully');
        this.router.navigate(['/']);
      },
      error => {
        alert('Error buying item.');
        this.router.navigate(['/']);
      });
  }

  ngOnDestroy() {
    this.sub.unsubscribe();
  }
}
