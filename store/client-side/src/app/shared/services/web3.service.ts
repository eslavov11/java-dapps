import {Injectable} from '@angular/core';

declare const require: any;
const Web3 = require('web3');

@Injectable()
export class Web3Service {
  public account: any;
  public web3: any;
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

  public initAccount(keystoreJson, password) {
    const account = this.decryptAccount(keystoreJson, password);
    this.account = account;
    this.web3.eth.accounts.wallet.add(this.account);
  }

  public async getBalance(): Promise<number> {
    return await new Promise((resolve, reject) => {
      const address = this.web3.eth.accounts.wallet[0].address;
      this.web3.eth.getBalance(address)
        .then(x => resolve(this.web3.utils.fromWei(x.toString(), 'ether')));
    }) as number;
  }
}
