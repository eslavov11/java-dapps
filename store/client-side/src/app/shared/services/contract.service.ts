import {Injectable} from '@angular/core';
import * as Web3 from 'web3';
import {default as contract} from 'truffle-contract';

import {ContractConfig} from '../config/contract-config';
import {Customer} from "../models/customer";
import {Item} from "../models/item";

@Injectable()
export class ContractService {
  public accounts: any[] = [];
  public account: any;
  public accountBalance: number;
  private web3: any;
  private contract;
  private PROVIDER_URL = 'https://ropsten.infura.io';  // 'http://localhost:8545' || 'http://127.0.0.1:7545' || 'https://ropsten.infura.io'

  constructor() {
    this.initWeb3();
  }

  public async loadAccounts() {
    if (this.account == null) {
      this.account = await new Promise((resolve, reject) => {
        this.web3.eth.getAccounts((err, accs) => {
          if (err != null) {
            alert('There was an error fetching your accounts.');
            return;
          }

          if (accs.length === 0) {
            alert('Couldnt get any accounts! Make sure your Ethereum client is configured correctly.');
            return;
          }

          resolve(accs[0]);
          this.accounts = accs;
        });
      });
    }

    return Promise.resolve(this.account);
  }

  public async setDefaultAccount() {
    if (this.web3.eth.defaultAccount == null) {
      this.web3.eth.defaultAccount = await new Promise((resolve, reject) => {
        this.web3.eth.getAccounts((err, accs) => {
          if (err != null) {
            alert('There was an error fetching your accounts.');
            return;
          }

          if (accs.length === 0) {
            alert('Couldnt get any accounts! Make sure your Ethereum client is configured correctly.');
            return;
          }

          resolve(accs[0]);
        });
      });
    }

    return Promise.resolve(this.web3.eth.defaultAccount);
  }

  public async getBalance(): Promise<number> {
    if (this.account != null) {
      this.accountBalance = await new Promise((resolve, reject) => {
        this.web3.eth.defaultAccount = this.account;
        this.web3.eth.getBalance(this.account, (err, balance) => {
          if (err != null) {
            alert('There was an error getting your balance.');
            return;
          }
          resolve(this.web3.fromWei(balance, 'ether'));
        });
      }) as number;
    }

    return Promise.resolve(this.accountBalance);
  }

  public async getBalanceForAccount(account: any): Promise<number> {
    const balanceFromAccount = await new Promise((resolve, reject) => {
      this.web3.eth.defaultAccount = account;
      this.web3.eth.getBalance(account, (err, balance) => {
        if (err != null) {
          alert('There was an error getting your balance.');
          return;
        }
        resolve(this.web3.fromWei(balance, 'ether'));
      });
    }) as number;

    return Promise.resolve(balanceFromAccount);
  }

  public initWeb3() {
    // Checking if Web3 has been injected by the browser (Mist/MetaMask)
    if (typeof (window as any).web3 !== 'undefined') {
      this.web3 = (window as any).web3;
    } else {
      (window as any).web3 =
        new Web3(new Web3.providers.HttpProvider(this.PROVIDER_URL));
    }

    const myContract = this.web3.eth.contract(ContractConfig.contract.abi);
    console.log(myContract);
    this.contract = myContract.at(ContractConfig.contract.address);
    // let c = new this.web3.eth.Contract(ContractConfig.contract.abi, '0xFa028Ad8D553803078af63130b059A3de1CE37Bd');
    console.log(contract);
  }

  public async getCars(carsId: number[]) {
    const cars = [];
    carsId.forEach(async cId => {
      const carObj = await new Promise((resolve, reject) => {
        this.contract.getCar(cId, function (error, result) {
          if (!error)
            resolve(result);
          else {
            resolve(false);
            console.log(error);
          }
        });
      });

      const car = new Car();
      car.id = cId;
      car.vin = this.web3.toUtf8(carObj[0]);
      car.metaIpfsHash = this.web3.toAscii(carObj[1]);
      car.seller = carObj[2];

      cars.push(car);
    });


    return cars;
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
    //TODO: items?

    return customer;
  }

  public async addItem(item: Item) {
    return await new Promise((resolve, reject) => {
      this.contract.addPart(item.description, this.web3.toWei(item.price, 'ether'), function (error, result) {
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
    item.description = this.web3.toUtf8(partObj[0]);
    item.price = this.web3.fromWei(partObj[1], 'ether').toString(10);
    item.sold = partObj[2];

    return item;
  }

  public async buyItem(itemId: number, price: number) {
    return await new Promise((resolve, reject) => {
      this.contract.buyPart(itemId, {value: this.web3.toWei(price, 'ether')},
        function (error, result) {
          if (!error)
            resolve(result);
          else
            console.error(error);
        });
    });
  }
}
