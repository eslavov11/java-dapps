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
      this.contract.getCustomer(account, function (error, result) {
        if (!error) {
          resolve(result);
        } else {
          resolve(false);
          console.log(error);
        }
      });
    });

    if (!customerObj) {
      return null;
    }

    const customer = new Customer();
    customer.address = account;
    customer.username = customerObj[0];
    customer.items = [];
    //Todo: CUSTOMEROBJ[1].c ???
    //customerObj[1].toArray().forEach(itemId => );

    return customer;
  }

  public async addItem(item: Item) {
    return await new Promise((resolve, reject) => {
      this.contract.addPart(item.description, item.price,
        function (error, result) {
          if (!error) {
            resolve(result);
          }
          else {
            console.error(error);
          }
        });
    });
  }

  public async getItem(id: number) {
    const itemObj = await new Promise((resolve, reject) => {
      this.contract.getPartForSale(id, function (error, result) {
        if (!error) {
          resolve(result);
        } else {
          console.error(error);
        }
      });
    });

    const item = new Item();
    item.id = id;
    item.description = this.web3Service.web3.toUtf8(itemObj[0]);
    item.price = itemObj[1]; // this.web3Service.web3.fromWei(itemObj[1], 'ether').toString(10);
    item.sold = itemObj[2];

    return item;
  }

  public async buyItem(currentUser: User, itemId: number, price: number) {
    const customer = await this.getCustomer(this.web3Service.account.address);
    if (!customer) {
      await this.registerCustomer(currentUser.username);
    }

    return await new Promise((resolve, reject) => {
      this.contract.buyPart(itemId, {
          value: price,
          from: this.web3Service.account.address,
          gas: 400000
        },
        function (error, result) {
          if (!error) {
            resolve(result);
          } else {
            console.error(error);
          }
        });
    });
  }
}
