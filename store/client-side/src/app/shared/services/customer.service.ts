import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {ApiService} from "./api.service";

@Injectable()
export class CustomerService {

  constructor(private apiService: ApiService) {
  }

  public createCustomer(customer) {
    const registerHeaders = new HttpHeaders({
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    });

    return this.apiService.post('/api/customer/register',
      JSON.stringify(customer), registerHeaders).map(() => {
      console.log("Register success");
    });
  }

  public getCustomer() {
    return this.apiService.get('/api/customer');
  }
}
