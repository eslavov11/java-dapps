import {Injectable} from '@angular/core';
import {default as contract} from 'truffle-contract';

import {ContractConfig} from '../config/contract-config';
import {Customer} from "../models/customer";
import {Item} from "../models/item";
import {Web3Service} from "./web3.service";

@Injectable()
export class ContractService {
  private contract: any;

  constructor(private web3Service: Web3Service) {
    this.initContract();
  }

  public initContract() {
    const myContract = this.web3Service.web3.eth.contract(ContractConfig.contract.abi);
    console.log(myContract);
    this.contract = myContract.at(ContractConfig.contract.address);
    console.log(contract);
  }

  public async registerCustomer(name: string) {
    this.contract.registerCustomer(name, function (error, result) {
      if (!error)
        console.log(result);
      else
        console.error(error);
    });

    await this.getBalance();
  }

  public async getCustomer(account: any) {
    const customerObj = await new Promise((resolve, reject) => {
      this.contract.getCustomer(account, function (error, result) {
        if (!error)
          resolve(result);
        else {
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
    customer.name = customerObj[0];
    customer.items = [];
    //Todo: CUSTOMEROBJ[1].c ???
    //customerObj[1].toArray().forEach(itemId => );

    return customer;
  }

  public async addItem(item: Item) {
    return await new Promise((resolve, reject) => {
      this.contract.addPart(item.description, this.web3Service.web3.toWei(item.price, 'ether'), function (error, result) {
        if (!error)
          resolve(result);
        else
          console.error(error);
      });
    });
  }

  public async getItem(id: number) {
    const itemObj = await new Promise((resolve, reject) => {
      this.contract.getPartForSale(id, function (error, result) {
        if (!error)
          resolve(result);
        else
          console.error(error);
      });
    });

    const item = new Item();
    item.id = id;
    item.description = this.web3Service.web3.toUtf8(partObj[0]);
    item.price = this.web3Service.web3.fromWei(partObj[1], 'ether').toString(10);
    item.sold = partObj[2];

    return item;
  }

  public async buyItem(itemId: number, price: number) {
    return await new Promise((resolve, reject) => {
      this.contract.buyPart(itemId, {value: this.web3Service.web3.toWei(price, 'ether')},
        function (error, result) {
          if (!error)
            resolve(result);
          else
            console.error(error);
        });
    });
  }
}
