import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable()
export class ItemService {
  private ITEM_URL = '/api/item/';

  constructor(private http: HttpClient) {
  }

  public getItemsForSale() {
    return this.http.get(this.ITEM_URL + 'for-sale');
  }

  public getItem(id) {
    return this.http.get(this.ITEM_URL + id);
  }

  public addItem(item) {
    return this.http.post(this.ITEM_URL + 'add', item);
  }

  public buyItem(id) {
    return this.http.post(this.ITEM_URL + id + '/buy', null);
  }
}
