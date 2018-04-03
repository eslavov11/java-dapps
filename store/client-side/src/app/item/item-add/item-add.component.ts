import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {ContractService} from "../../shared/services/contract.service";
import {ItemService} from "../../shared/services/item.service";
import {NgForm} from "@angular/forms";

@Component({
  selector: 'app-item-add',
  templateUrl: './item-add.component.html',
  styleUrls: ['./item-add.component.css']
})
export class ItemAddComponent implements OnInit {
  constructor(private contractService: ContractService,
              private itemService: ItemService,
              private router: Router) {
  }
  ngOnInit() {
  }

  async onSubmit(f: NgForm) {
    // await this.contractService.registerCustomer(f.value.name);

    const item = {
      description: f.value.description,
      price: f.value.price,
    };
    this.itemService.addItem(item).subscribe(
      data => {
        alert('Item added successfully.');
        this.router.navigate(['/']);
      },
      error => {
      });
  }
}
