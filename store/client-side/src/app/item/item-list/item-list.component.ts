import {Component, OnInit} from '@angular/core';
import {Item} from "../../shared/models/item";
import {ItemService} from "../../shared/services/item.service";

@Component({
  selector: 'app-item-list',
  templateUrl: './item-list.component.html',
  styleUrls: ['./item-list.component.css']
})
export class ItemListComponent implements OnInit {
  items: Array<Item>;

  constructor(private itemService: ItemService) {
  }

  ngOnInit() {
    // this.itemService.getItemsForSale()
    //   .subscribe((data: Array<Item>) => {
    //     this.items = data as Array<Item>;
    //   });
  }

}
