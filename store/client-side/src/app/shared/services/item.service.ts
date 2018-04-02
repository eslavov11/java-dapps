import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable()
export class ItemService {

  constructor(private http: HttpClient) {
  }

  public getItemsForSale() {
    return this.http.get('/api/item/for-sale');
  }

  public getItem(id) {
    return this.http.get('/api/item/' + id);
  }

  public addItem(item) {
    return this.http.post('/api/item/' + 'add', item);
  }

  public buyItem(id) {
    return this.http.post('/api/item/' + id + '/buy', null);
  }
}
