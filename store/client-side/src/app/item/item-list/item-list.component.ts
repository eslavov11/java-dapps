import {Component, OnInit} from '@angular/core';
import {Item} from "../../shared/models/item";
import {ItemService} from "../../shared/services/item.service";
import {Web3Service} from "../../shared/services/web3.service";

@Component({
  selector: 'app-item-list',
  templateUrl: './item-list.component.html',
  styleUrls: ['./item-list.component.css']
})
export class ItemListComponent implements OnInit {
  items: Array<Item>;

  constructor(private itemService: ItemService,
              private web3Service: Web3Service) {
  }

  ngOnInit() {
    this.itemService.getItemsForSale()
      .subscribe((data: Array<Item>) => {
        this.items = data as Array<Item>;
        this.items.forEach(item => {
          item.price = this.web3Service
            .web3.utils.fromWei(item.price.toString(), 'ether');
        });
      });
  }

}
