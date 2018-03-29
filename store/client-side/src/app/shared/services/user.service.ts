import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from '@angular/common/http';

@Injectable()
export class UserService {
  public authenticated = false;
  private serverUrl = 'http://localhost:8080/';

  constructor(private http: HttpClient) {
  }

  public authenticate(credentials, callback) {

    const headers = new HttpHeaders(credentials ? {
      authorization: 'Basic ' + btoa(credentials.username + ':' + credentials.password)
    } : {});

    this.http.get(this.serverUrl + 'user', {headers: headers}).subscribe(response => {
      if (response['name']) {
        this.authenticated = true;
      } else {
        this.authenticated = false;
      }

      return callback && callback();
    });
  }
}
