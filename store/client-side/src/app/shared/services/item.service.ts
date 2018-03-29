import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable()
export class ItemService {
  private itemUrl = 'http://localhost:8080/item/';

  constructor(private http: HttpClient) {
  }

  public getItemsForSale() {
    return this.http.get(this.itemUrl + 'for-sale');
  }

  public getItem(id) {
    return this.http.get(this.itemUrl + id);
  }

  public addItem(item) {
    return this.http.post(this.itemUrl + 'add', item);
  }

  public buyItem(id) {
    return this.http.post(this.itemUrl + id + '/buy', null);
  }
}
