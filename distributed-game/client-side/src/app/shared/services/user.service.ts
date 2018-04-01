import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpResponse} from '@angular/common/http';
import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';
import 'rxjs/add/operator/map';
import {HttpParams} from '@angular/common/http';
import {$} from 'protractor';

@Injectable()
export class UserService {
  private serverUrl = 'http://localhost:5555/';
  private stompClient;

  constructor(private http: HttpClient) {
  }

  public createUser(user) {
    return this.http.post(this.serverUrl + 'register', user);
  }

  public initializeWebSocketConnection() {
    const ws = new SockJS(this.serverUrl + 'socket');
    this.stompClient = Stomp.over(ws);
    const _this = this;
    this.stompClient.connect({}, function(frame) {
      _this.stompClient.subscribe('/user/profile', (message) => {
        if (message.body) {
          console.log(message.body);
        }
      });

      _this.stompClient.send('/user/register', {}, JSON.stringify({'name': 'yoooooo'}));
    });
  }

}
