import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {CustomerRegisterComponent} from './customer/customer-register/customer-register.component';
import {CustomerDetailComponent} from './customer/customer-detail/customer-detail.component';
import {CustomerLoginComponent} from './customer/customer-login/customer-login.component';
import {ItemAddComponent} from "./item/item-add/item-add.component";
import {ItemDetailComponent} from "./item/item-detail/item-detail.component";
import {ItemListComponent} from "./item/item-list/item-list.component";

const routes: Routes = [
  {path: 'customer/register', component: CustomerRegisterComponent},
  {path: 'customer/login', component: CustomerLoginComponent},
  {path: 'customer/detail', component: CustomerDetailComponent},
  {path: 'item/add', component: ItemAddComponent},
  {path: 'item/detail/:id', component: ItemDetailComponent},
  {path: 'item/list', component: ItemListComponent},
  {path: '', component: ItemListComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
