import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { AppComponent } from './app.component';
import { UserDetailComponent } from './user/user-detail/user-detail.component';
import { UserRegisterComponent } from './user/user-register/user-register.component';


@NgModule({
  declarations: [
    AppComponent,
    UserDetailComponent,
    UserRegisterComponent
  ],
  imports: [
    BrowserModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
