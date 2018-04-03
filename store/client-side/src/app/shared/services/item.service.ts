import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable()
export class ItemService {
  private serverUrl = 'http://localhost:8080/';
  private itemUrl = this.serverUrl + 'item/';

  constructor(private http: HttpClient) {
  }

  public getItemsForSale() {
    this.http.get(this.serverUrl + 'token').subscribe(data => {
      const token = data['token'];
      this.http.get(this.itemUrl + 'for-sale',
        {headers: new HttpHeaders().set('X-Auth-Token', token)})
        .subscribe(response => console.log(response));
    }, () => {
    });

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
