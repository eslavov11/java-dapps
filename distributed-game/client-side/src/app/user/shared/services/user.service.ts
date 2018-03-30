import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders, HttpResponse} from '@angular/common/http';
import 'rxjs/add/operator/map';
import {HttpParams} from "@angular/common/http";

@Injectable()
export class UserService {
  private serverUrl = 'http://localhost:8080/';

  constructor(private http: HttpClient) {
  }

  public createUser(user) {
    return this.http.post(this.serverUrl + 'register', user);
  }
}
