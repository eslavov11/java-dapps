import {Injectable} from '@angular/core';
// import {default as contract} from 'truffle-contract';

import {ContractConfig} from '../config/contract-config';
import {Customer} from '../models/customer';
import {Item} from '../models/item';
import {Web3Service} from './web3.service';
import {User} from "../models/user";

@Injectable()
export class ContractService {
  private contract: any;

  constructor(private web3Service: Web3Service) {
    this.initContract();
  }

  public initContract() {
    this.contract = new this.web3Service.web3.eth.Contract(ContractConfig.contract.abi);
    this.contract.options.address = ContractConfig.contract.address;
    console.log(this.contract);
  }

  public async registerCustomer(username: string) {
    return new Promise((resolve, reject) => {
      this.contract.methods.registerCustomer(username)
        .send({
          from: this.web3Service.account.address,
          gas: 400000
        }).then(result => {
        resolve(result);
      });
    });
  }

  public async getCustomer(account: any) {
    const customerObj = await new Promise((resolve, reject) => {
      this.contract.methods.getCustomer(account).call()
        .then(result => {
          resolve(result);
        });
    }) as any;

    if (!customerObj[0]) {
      return null;
    }

    const customer = new Customer();
    customer.address = account;
    customer.username = customerObj[0];
    customer.items = [];

    customerObj.customerItems.forEach(async itemId => {
      const item = await this.getItem(itemId);
      customer.items.push(item);
    });

    return customer;
  }

  public async addItem(item: Item) {
    return await new Promise((resolve, reject) => {
      this.contract.methods.addItem(item.description, item.price)
        .send({
          from: this.web3Service.account.address,
          gas: 400000
        })
        .then(result => {
          resolve(result);
        });
    });
  }

  public async getItem(id: number) {
    const itemObj = await new Promise((resolve, reject) => {
      this.contract.methods.getItem(id).call()
        .then(result => {
          resolve(result);
        });
    }) as any;

    const item = new Item();
    item.id = id;
    item.description = itemObj.description;
    item.price = this.web3Service
      .web3.utils.fromWei(itemObj.price.toString(), 'ether')
    item.sold = itemObj.sold;

    return item;
  }

  public async buyItem(currentUser: User, itemId: number, price: number) {
    const customer = await this.getCustomer(this.web3Service.account.address);
    if (!customer) {
      await this.registerCustomer(currentUser.username);
    }

    return await new Promise((resolve, reject) => {
      this.contract.methods.buyItem(itemId)
        .send({
          value: this.web3Service.web3.utils.toWei(price, 'ether'),
          from: this.web3Service.account.address,
          gas: 400000
        })
        .then(result => {
          resolve(result);
        });
    });
  }
}
