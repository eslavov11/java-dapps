export const ContractConfig = {
  contract:
    {
      address: '0xa2e31c16a0a0ed6654a77423db37fdacbb52fcb1',
      abi: [
        {
          "constant": false,
          "inputs": [
            {
              "name": "name",
              "type": "string"
            }
          ],
          "name": "registerCustomer",
          "outputs": [],
          "payable": false,
          "stateMutability": "nonpayable",
          "type": "function"
        },
        {
          "constant": true,
          "inputs": [
            {
              "name": "item",
              "type": "uint256"
            }
          ],
          "name": "getItem",
          "outputs": [
            {
              "name": "description",
              "type": "string"
            },
            {
              "name": "price",
              "type": "uint256"
            },
            {
              "name": "sold",
              "type": "bool"
            }
          ],
          "payable": false,
          "stateMutability": "view",
          "type": "function"
        },
        {
          "constant": true,
          "inputs": [],
          "name": "getItems",
          "outputs": [
            {
              "name": "",
              "type": "uint256"
            }
          ],
          "payable": false,
          "stateMutability": "view",
          "type": "function"
        },
        {
          "constant": false,
          "inputs": [
            {
              "name": "description",
              "type": "string"
            },
            {
              "name": "price",
              "type": "uint256"
            }
          ],
          "name": "addItem",
          "outputs": [
            {
              "name": "",
              "type": "uint256"
            }
          ],
          "payable": false,
          "stateMutability": "nonpayable",
          "type": "function"
        },
        {
          "constant": true,
          "inputs": [
            {
              "name": "addr",
              "type": "address"
            }
          ],
          "name": "getCustomer",
          "outputs": [
            {
              "name": "name",
              "type": "string"
            },
            {
              "name": "customerItems",
              "type": "uint256[]"
            }
          ],
          "payable": false,
          "stateMutability": "view",
          "type": "function"
        },
        {
          "constant": false,
          "inputs": [
            {
              "name": "item",
              "type": "uint256"
            }
          ],
          "name": "buyItem",
          "outputs": [],
          "payable": true,
          "stateMutability": "payable",
          "type": "function"
        },
        {
          "inputs": [],
          "payable": false,
          "stateMutability": "nonpayable",
          "type": "constructor"
        }
      ]
    }
}
