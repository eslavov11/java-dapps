import {Item} from "./item";

export class Customer {
  address: string;
  name: string;
  username: string;
  password: string;
  utcJson: string;
  items: Item[];
}
