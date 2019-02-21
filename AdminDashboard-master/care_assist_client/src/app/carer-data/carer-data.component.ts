import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HomeComponent } from '../home/home.component';
import { ApiServiceService } from '../api-service.service';

@Component({
  selector: 'app-carer-data',
  templateUrl: './carer-data.component.html',
  styleUrls: ['./carer-data.component.css']
})
export class CarerDataComponent implements OnInit {
  id: number;
  private sub: any;
  carer: any;
  client: any;
  carerObject: any;
  clientObject: any;
  carerReady=false;
  clientList=[];
  clients=[];
  appointments = [];
  appointmentkeys = [];

  constructor(private route: ActivatedRoute,  private dataService : ApiServiceService) {
    this.sub = this.route.params.subscribe(params => {
       this.id = +params['id']; // (+) converts string 'id' to a number
    });
    try{
      this.dataService.getCarerByIdfromDB(this.id).subscribe(
        data =>  this.carerObject=data[0],
        error => console.log("error"),
        () => this.setCarer()
      );
      this.dataService.getClientByIdfromDB(this.id).subscribe(
        data =>  this.clientObject=data[0],
        error => console.log("error"),
        () => this.setClient()
      );
      this.dataService.getClientsfromDB().subscribe(
        data => this.clientList = data,
        error => console.log("error"),
        () => this.setupClient()
      );
    } catch(error){
      console.log("Data isn't properly loaded");
    }
  }

  ngOnInit() {

  }

  setupClient(){
    this.clients = [];
    for(let client of this.clientList){
      this.clients.push(client['properties']);
    }
  }

  setClient(){
    this.client = this.clientObject;
  }

  setCarer(){
    this.carer = this.carerObject['properties'];
    this.appointments = this.carer['appointments'];
    this.appointmentkeys = Object.keys(this.appointments);
    console.log(this.compareDate(1));
  }

  compareDate(date){
    var date1 = new Date();
    var date2 = new Date(date);
    var month2 = date2 .getMonth() + 1;
    var day2 = date2 .getDate();
    var year2 = date2 .getFullYear();
    date2 = new Date(year2+"-"+month2+"-"+day2);
    var month1 = date1 .getMonth() + 1;
    var day1 = date1 .getDate();
    var year1 = date1 .getFullYear();
    date1 = new Date(year1+"-"+month1+"-"+day1);
    return date1>date2;
  }

  compareDateUpcoming(date){
    var date1 = new Date();
    var date2 = new Date(date);
    var month2 = date2 .getMonth() + 1;
    var day2 = date2 .getDate();
    var year2 = date2 .getFullYear();
    date2 = new Date(year2+"-"+month2+"-"+day2);
    var month1 = date1 .getMonth() + 1;
    var day1 = date1 .getDate();
    var year1 = date1 .getFullYear();
    date1 = new Date(year1+"-"+month1+"-"+day1);
    return date1<=date2;
  }

}
