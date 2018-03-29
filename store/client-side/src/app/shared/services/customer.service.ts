import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable()
export class CustomerService {

  constructor(private http:HttpClient) {}

  private customerUrl = 'http://localhost:8080/customer/';

  public createCustomer(customer) {
    return this.http.post(this.customerUrl + 'register', customer);
  }

  public getCustomer() {
    return this.http.get(this.customerUrl + 'profile');
  }
}
