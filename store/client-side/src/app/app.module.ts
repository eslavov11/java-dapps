import {BrowserModule} from '@angular/platform-browser';
import {Injectable, NgModule} from '@angular/core';
import {AppComponent} from './app.component';
import {NavComponent} from './nav/nav.component';
import {ItemListComponent} from './item/item-list/item-list.component';
import {CustomerDetailComponent} from './customer/customer-detail/customer-detail.component';
import {CustomerRegisterComponent} from './customer/customer-register/customer-register.component';
import {AppRoutingModule} from './app-routing.module';
import {HTTP_INTERCEPTORS, HttpClientModule, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {FormsModule} from '@angular/forms';
import {ContractService} from './shared/services/contract.service';
import {CustomerLoginComponent} from './customer/customer-login/customer-login.component';
import {ItemService} from './shared/services/item.service';
import {CustomerService} from './shared/services/customer.service';
import {Web3Service} from './shared/services/web3.service';
import {UserService} from './shared/services/user.service';
import { ItemDetailComponent } from './item/item-detail/item-detail.component';
import { ItemAddComponent } from './item/item-add/item-add.component';
import {AuthService} from "./shared/services/auth.service";
import {ApiService} from "./shared/services/api.service";

@Injectable()
export class XhrInterceptor implements HttpInterceptor {

  intercept(req: HttpRequest<any>, next: HttpHandler) {
    const xhr = req.clone({
      headers: req.headers.set('X-Requested-With', 'XMLHttpRequest')
    });
    return next.handle(xhr);
  }
}

@NgModule({
  declarations: [
    AppComponent,
    NavComponent,
    CustomerDetailComponent,
    CustomerRegisterComponent,
    CustomerLoginComponent,
    ItemListComponent,
    ItemDetailComponent,
    ItemAddComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule
  ],
  providers: [ContractService, Web3Service, UserService, ItemService, CustomerService, AuthService, ApiService,
    {provide: HTTP_INTERCEPTORS, useClass: XhrInterceptor, multi: true}],
  bootstrap: [AppComponent]
})
export class AppModule {
}
