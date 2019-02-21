import { Component, OnInit } from '@angular/core';
import { ApiServiceService } from '../api-service.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  carerList =[];
  clientList : any;
  carers =[];
  clients =[];
  carer : object;
  client : object;
  carerObject : object;
  clientObject : object;
  constructor(private dataService : ApiServiceService){
  }
  ngOnInit(){
    this.init();
  }
  setupCarer(){
    this.carers = [];
    for(let carer of this.carerList){
      this.carers.push(carer['properties']);
    }
  }

  setupClient(){
    this.clients = [];
    for(let client of this.clientList){
      this.clients.push(client);
    }
  }

  init(){
    this.dataService.getCarersfromDB().subscribe(
      data => this.carerList = data,
      error => console.log("error"),
      () => this.setupCarer()
    );
    this.dataService.getClientsfromDB().subscribe(
      data => this.clientList = data,
      error => console.log("error"),
      () => this.setupClient()
    );
  }

  setCarerInstance(){
    this.carer = this.carerObject;
  }

  getClientById(id){
    return this.dataService.getClientByIdfromDB(id);
  }
  getCarerById(id){
    return this.dataService.getCarerByIdfromDB(id);
  }

}
