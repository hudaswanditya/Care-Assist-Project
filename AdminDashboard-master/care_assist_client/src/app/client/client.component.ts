import { Component, OnInit } from '@angular/core';
import { ApiServiceService } from '../api-service.service';

@Component({
  selector: 'app-client',
  templateUrl: './client.component.html',
  styleUrls: ['./client.component.css']
})
export class ClientComponent implements OnInit {
  clientList : any;
  clients =[];

  constructor(private dataService : ApiServiceService){
  }

  ngOnInit(){
    this.init();
  }
  setupClient(){
    this.clients = [];
    for(let client of this.clientList){
      this.clients.push(client['properties']);
    }
  }

  init(){
    this.dataService.getClientsfromDB().subscribe(
      data => this.clientList = data,
      error => console.log("error"),
      () => this.setupClient()
    );
  }

  getImagePath(name){
    let fullName = name.split(" ");
    let imagePath = fullName[0];

    return imagePath;
  }


}
