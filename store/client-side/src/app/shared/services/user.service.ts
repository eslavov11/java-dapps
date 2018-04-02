import {Injectable} from "@angular/core";
import { ApiService } from './api.service';

@Injectable()
export class UserService {
  public currentUser;

  constructor(private apiService: ApiService) {
  }

  public initUser() {
    const promise = this.apiService.get('/api/refresh').toPromise()
      .then(res => {
        if (res.access_token !== null) {
          return this.getMyInfo().toPromise()
            .then(user => {
              this.currentUser = user;
            });
        }
      })
      .catch(() => null);
    return promise;
  }

  public getMyInfo() {
    return this.apiService.get('/api/whoami').map(user => this.currentUser = user);
  }
}
