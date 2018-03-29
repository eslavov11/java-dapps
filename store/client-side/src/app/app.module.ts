import {BrowserModule} from "@angular/platform-browser";
import {NgModule} from "@angular/core";
import {AppComponent} from "./app.component";
import {NavComponent} from "./nav/nav.component";
import {ItemListComponent} from "./item/item-list/item-list.component";
import {CustomerDetailComponent} from "./customer/customer-detail/customer-detail.component";
import {CustomerRegisterComponent} from "./customer/customer-register/customer-register.component";
import {AppRoutingModule} from "./app-routing.module";
import {HttpClientModule} from "@angular/common/http";
import {FormsModule} from "@angular/forms";
import {ContractService} from "./shared/services/contract.service";
import {CustomerLoginComponent} from "./customer/customer-login/customer-login.component";
import {ItemService} from "./shared/services/item.service";
import {CustomerService} from "./shared/services/customer.service";
import {AuthService} from "./shared/services/auth.service";
import {Web3Service} from "./shared/services/web3.service";
import {UserService} from "./shared/services/user.service";

@NgModule({
  declarations: [
    AppComponent,
    NavComponent,
    ItemListComponent,
    CustomerDetailComponent,
    CustomerRegisterComponent,
    CustomerLoginComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule
  ],
  providers: [ContractService, Web3Service, UserService, ItemService, CustomerService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
