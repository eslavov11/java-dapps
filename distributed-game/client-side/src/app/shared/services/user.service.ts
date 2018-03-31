import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpResponse} from '@angular/common/http';
import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';
import 'rxjs/add/operator/map';
import {HttpParams} from '@angular/common/http';

@Injectable()
export class UserService {
  private serverUrl = 'http://localhost:8080/';
  private stompClient;

  constructor(private http: HttpClient) {
  }

  public createUser(user) {
    return this.http.post(this.serverUrl + 'register', user);
  }

  public initializeWebSocketConnection() {
    const ws = new SockJS(this.serverUrl);
    this.stompClient = Stomp.over(ws);
    const that = this;
    this.stompClient.connect({}, function(frame) {
      that.stompClient.subscribe('/topic/greetings', (message) => {
        if (message.body) {
          console.log(message.body);
        }
      });
    });
  }

}
