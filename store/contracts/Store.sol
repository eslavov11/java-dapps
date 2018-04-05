pragma solidity ^0.4.18;

contract Store {
    address private owner;
  
    mapping(address => Customer) private customers;
    mapping(uint256 => Item) private items;
    
    uint256 private itemsCount;

    struct Customer {
        string name;
        uint256[] items;
    }

    struct Item {
        string description;
        uint256 price;
        bool sold;
    }
    
    modifier isOwner() {
      require(msg.sender == owner);
      _;
    }
    
    modifier isCustomer() {
        require(!_stringEmpty(customers[msg.sender].name));
        _;
    }
    
    modifier itemExists(uint256 item) {
        require(!_stringEmpty(items[item].description));
        _;
    }
    
    function Store() public {
        owner = msg.sender;
        itemsCount = 0;
    }
    
    function registerCustomer(string name) public {
        customers[msg.sender].name = name;
    }
    
    function getCustomer(address addr) 
        public 
        view 
        returns(
            string name,
            uint256[] customerItems
        ) 
    {
        Customer memory customer = customers[addr]; 
        return (
            customer.name,
            customer.items
        );
    }
    
    function addItem(string description, uint256 price) 
        public
        returns(uint256) 
    {
        require(!_stringEmpty(description));
        
        itemsCount++;
        items[itemsCount].description = description;
        items[itemsCount].price = price;
        items[itemsCount].sold = false;
        
        return itemsCount;
    }
    
    function getItem(uint256 item) 
        public 
        view 
        itemExists(item)
        returns(
            string description,
            uint256 price,
            bool sold
        ) 
    {
        Item memory itemStr = items[item]; 
        return (
            itemStr.description,
            itemStr.price,
            itemStr.sold
        );
    }
    
    function getItems() public view returns(uint256) {
        return itemsCount;
    }
    
    function buyItem(uint256 item) 
        public 
        payable 
        isCustomer 
    {
        require(!_stringEmpty(items[item].description) && !items[item].sold);
        customers[msg.sender].items.push(item);
        items[item].sold = true;
    }

    function _stringEmpty(string s) private pure returns(bool) {
        return keccak256("") == keccak256(s);
    }
}
