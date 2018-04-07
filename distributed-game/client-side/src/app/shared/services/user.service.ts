import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpResponse} from '@angular/common/http';
import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';
import 'rxjs/add/operator/map';
import {User} from "../models/user";

@Injectable()
export class UserService {
  private serverUrl = 'http://localhost:8072/';
  private stompClient;

  public currentUser: User;

  constructor(private http: HttpClient) {
    this.currentUser = new User();
  }

  public createUser(user) {
    const ws = new SockJS(this.serverUrl + 'socket');
    this.stompClient = Stomp.over(ws);
    const _this = this;
    this.stompClient.connect({}, function (frame) {
      _this.stompClient.subscribe('/topic/profile', (message) => {
        if (message.body) {
          _this.currentUser = JSON.parse(message.body) as User;
        }
      });

      _this.stompClient.send('/app/register', {}, JSON.stringify(user));
    });
  }

}
