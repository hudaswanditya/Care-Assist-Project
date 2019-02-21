import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { RouterModule } from '@angular/router';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import { AppComponent } from './app.component';
import { ApiServiceService } from './api-service.service';
import { CarerHistoryComponent } from './carer-history/carer-history.component';
import { CarerDataComponent } from './carer-data/carer-data.component';
import { HomeComponent } from './home/home.component';
import { CommonModule } from '@angular/common';
import { AddRouteComponent } from './add-route/add-route.component';
import { ClientInfoComponent } from './client-info/client-info.component';
import { ClientComponent } from './client/client.component';
import {OrderByPipe} from "./orderby.pipe";

@NgModule({
  declarations: [
    AppComponent,
    CarerHistoryComponent,
    CarerDataComponent,
    HomeComponent,
    AddRouteComponent,
    ClientInfoComponent,
    ClientComponent,
    OrderByPipe

  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    CommonModule,
    RouterModule.forRoot([
      { path: '', redirectTo: 'carer', pathMatch: 'full'},
      { path: 'carer', component: HomeComponent },
      { path: 'carer/:id', component: CarerDataComponent },
      { path: 'client', component: ClientComponent},
      { path: 'client/:id', component: ClientInfoComponent },
      { path: 'carer/:id/route', component: AddRouteComponent }
    ])
  ],
  providers: [ApiServiceService, HomeComponent],
  bootstrap: [AppComponent]
})
export class AppModule { }
