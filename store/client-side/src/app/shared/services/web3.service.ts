import {Injectable} from '@angular/core';
declare var require: any;
var Web3 = require('web3');

@Injectable()
export class Web3Service {
  public account: any;
  public accountBalance: number;
  public web3: any;
  private contract;
  private PROVIDER_URL = 'https://ropsten.infura.io';  // 'http://localhost:8545' || 'http://127.0.0.1:7545' || 'https://ropsten.infura.io'

  constructor() {
    this.initWeb3();
  }

  public initWeb3() {
    this.web3 = new Web3(this.PROVIDER_URL);
  }

  public createAndEncryptAccount(password) {
    const account = this.web3.eth.accounts.create();

    return this.web3.eth.accounts.encrypt(account.privateKey, password);
  }

  public decryptAccount(keystoreJsonV3, password) {
    return this.web3.eth.accounts.decrypt(keystoreJsonV3, password);
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

  public async getBalance(account: any): Promise<number> {
    const balanceFromAccount = await new Promise((resolve, reject) => {
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
}
