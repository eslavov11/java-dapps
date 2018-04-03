import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpResponse} from '@angular/common/http';
import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';
import 'rxjs/add/operator/map';

@Injectable()
export class UserService {
  private serverUrl = 'http://localhost:5555/';
  private stompClient;

  constructor(private http: HttpClient) {
  }

  public createUser(user) {
    const ws = new SockJS(this.serverUrl + 'socket');
    this.stompClient = Stomp.over(ws);
    const _this = this;
    this.stompClient.connect({}, function(frame) {
      _this.stompClient.subscribe('/topic/profile', (message) => {
        if (message.body) {
          console.log(message.body);
        }
      });

      _this.stompClient.send('/app/register', {}, JSON.stringify(user));
    });
  }

}
