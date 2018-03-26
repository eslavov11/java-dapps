import {Component} from '@angular/core';
// import * as Web3 from 'web3';
declare var require: any;
var Web3 = require('web3');

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  private web3: any;
  title = 'app';

  constructor() {
    this.initWeb3();
  }

  public initWeb3() {
    // 'https://rinkeby.infura.io' || 'http://127.0.0.1:7545'
    this.web3 = new Web3('https://ropsten.infura.io');
    this.web3.eth.getBalance("0x6B79F5949489D7d592828fA0A113a133E46214ae")
      .then(console.log);
     this.web3.eth.getBalance("0x001EAa68D3a259D75105406234ffc32f2a80fa5f")
      .then(console.log);

    // var acc = this.web3.eth.accounts.create();
    // console.log(acc)
    let tx = {
      to: '0x6B79F5949489D7d592828fA0A113a133E46214ae',
      value: '200000000000000000',
      gas: 4000000
    };

    let _this = this;
    this.web3.eth.accounts.signTransaction(tx, '0x8f8468e0b2485db62b93d39fcbe222aa08bd4a01e9d54cfbc91ea1027d12b3ee')
      .then(function (transaction) {
        _this.web3.eth.sendSignedTransaction(transaction.rawTransaction)
          .on('receipt', console.log);
      });
    ;


  }
}
