import {Injectable} from '@angular/core';
declare var require: any;
var Web3 = require('web3');
import {default as contract} from 'truffle-contract';

import {ContractConfig} from '../config/contract-config';
import {Customer} from "../models/customer";
import {Item} from "../models/item";

@Injectable()
export class AuthService {
  public accounts: any[] = [];
  public account: any;
  public accountBalance: number;
  private web3: any;
  private contract;
  private PROVIDER_URL = 'https://ropsten.infura.io';  // 'http://localhost:8545' || 'http://127.0.0.1:7545' || 'https://ropsten.infura.io'

  constructor() {
    this.initWeb3();
  }
}
