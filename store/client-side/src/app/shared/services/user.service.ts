import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders, HttpResponse} from '@angular/common/http';
import 'rxjs/add/operator/map';
import {HttpParams} from "@angular/common/http";

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

  public login2(credentials) {

    const headers = new HttpHeaders({
      'Content-Type': 'application/x-www-form-urlencoded'
    });

    const body = new HttpParams()
      .set('username', credentials.username)
      .set('password', credentials.password);

    return this.http.post(this.serverUrl + 'login', body.toString(), {headers: headers});
  }

  public login(credentials) {
    // creating base64 encoded String from user name and password
    const base64Credential: string = btoa(credentials.username + ':' + credentials.password);
    const headers = new HttpHeaders({
      'Accept': 'application/json',
      "Authorization":"Basic " + base64Credential
    });

    return this.http.get(this.serverUrl + 'login', {headers: headers}).map((response: Response) => {
      // login successful if there's a jwt token in the response
      let user = (<any>response).principal;// the returned user object is a principal object
      if (user) {
        // store user details  in local storage to keep user logged in between page refreshes
        localStorage.setItem('currentUser', JSON.stringify(user));
      }
    });
  }

  public logout() {
    return this.http.post(this.serverUrl + 'logout', {}).map((response: Response) => {
      localStorage.removeItem('currentUser');
    });
  }
}
