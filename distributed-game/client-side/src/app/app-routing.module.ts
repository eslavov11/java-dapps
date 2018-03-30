import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {UserRegisterComponent} from "./user/user-register/user-register.component";
import {UserDetailComponent} from "./user/user-detail/user-detail.component";

const routes: Routes = [
  {path: 'user/detail', component: UserDetailComponent},
  {path: '', component: UserRegisterComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
