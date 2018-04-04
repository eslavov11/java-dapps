import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {ContractService} from "../../shared/services/contract.service";
import {ItemService} from "../../shared/services/item.service";
import {NgForm} from "@angular/forms";
import {Web3Service} from "../../shared/services/web3.service";
import {Item} from "../../shared/models/item";

@Component({
  selector: 'app-item-add',
  templateUrl: './item-add.component.html',
  styleUrls: ['./item-add.component.css']
})
export class ItemAddComponent implements OnInit {
  constructor(private contractService: ContractService,
              private itemService: ItemService,
              private web3Service: Web3Service,
              private router: Router) {
  }

  ngOnInit() {
  }

  async onSubmit(f: NgForm) {
    const item = {
      description: f.value.description,
      price: f.value.price,
    };

    item.price = this.web3Service.web3.utils.toWei(item.price, 'ether');

    await this.contractService.addItem(item as Item);
    this.itemService.addItem(item).subscribe(
      data => {
        alert('Item added successfully.');
        this.router.navigate(['/']);
      },
      error => {
        alert('Error adding item.');
      });
  }
}
