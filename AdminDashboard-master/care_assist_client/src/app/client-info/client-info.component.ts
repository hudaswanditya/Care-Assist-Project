import { Component, OnInit } from '@angular/core';
import { ApiServiceService } from '../api-service.service';
import { ActivatedRoute } from '@angular/router';
import { ClientComponent } from '../client/client.component';

@Component({
  selector: 'app-client-info',
  templateUrl: './client-info.component.html',
  styleUrls: ['./client-info.component.css']
})
export class ClientInfoComponent implements OnInit {

  clientObject: any;
  id: number;
  private sub: any;
  client: any;
  constructor(private dataService : ApiServiceService,private route: ActivatedRoute,){
  }

  ngOnInit(){
    this.sub = this.route.params.subscribe(params => {
       this.id = +params['id'];
       this.dataService.getClientByIdfromDB(this.id).subscribe(
         data => this.clientObject = data,
         error => console.log("error"),
         () => this.setupClient()
       );
     });
  }

  setupClient(){
    this.client = this.clientObject[0]['properties'];
  }

  getImagePath(name){
    let fullName = name.split(" ");
    let imagePath = fullName[0];

    return imagePath;
  }
}
