import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Router } from '@angular/router';
import { ApiServiceService } from '../api-service.service';
import { Http } from '@angular/http';

@Component({
  selector: 'app-add-route',
  templateUrl: './add-route.component.html',
  styleUrls: ['./add-route.component.css']
})
export class AddRouteComponent implements OnInit {
  id: number;
  private sub: any;
  expression = [];
  carer: any;
  client: any;
  carerObject: any;
  clientObject: any;
  carerReady=false;
  clientList=[];
  clients=[];
  appointments = [];
  appointmentkeys = [];
  routeList = [];
  selectedClient = 0;
  startTime = [];
  endTime = [];
  token : string;
  clientsOnCheck = [];
  dateAuswahl = null;
  tempList =[];
  tempListTask =[];
  routeListNoTask =[];
  buttonState = "zur Liste hinzufügen";
  timeToDo = [];
  timeCounter = 0;
  timeList = [];

  constructor(private route: ActivatedRoute, private router: Router,  private dataService : ApiServiceService) { }

  ngOnInit() {
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

  saveRoute(){

    if(this.routeListNoTask.length != 0) {
      this.saveClient();
      this.saveCarer();
      if(window.confirm("- OK (zurück zum Carer) \n- Cancel (neue Route hinzufügen)")==true){
        this.saveClient();
        this.saveCarer();
        this.router.navigateByUrl("/carer/"+this.carer['id']);
      }else{
        this.saveClient();
        this.saveCarer();
        this.router.navigateByUrl("/route/"+this.carer['id']);
      }

    }
    else window.alert("Please add some client to list!");

  }

  saveClient(){
    let date = "";
    if(this.dateAuswahl == null){
      var d = new Date(),
            month = '' + (d.getMonth() + 1),
            day = '' + d.getDate(),
            year = d.getFullYear();

        if (month.length < 2) month = '0' + month;
        if (day.length < 2) day = '0' + day;

      date = year + "-" + month +"-"+day+"T00:00:00.000+0000";
    } else{
      var d = new Date(this.dateAuswahl),
            month = '' + (d.getMonth() + 1),
            day = '' + d.getDate(),
            year = d.getFullYear();

        if (month.length < 2) month = '0' + month;
        if (day.length < 2) day = '0' + day;

      date = year + "-" + month +"-"+day+"T00:00:00.000+0000";
    }
    for (let cid in this.routeList) {
      let id = this.routeList[cid]['clientID'];
      let tasks = this.routeList[cid]['tasks'];

      for (let client in this.clientList) {
        let clientid = this.clientList[client]['properties']['id'];
        let clientprop = this.clientList[client]['properties'];
        if(clientid == id){
          let gTask = [];
          for(let tid in tasks){
            let todo ={
              "task":clientprop['generalTasks'][tasks[tid]],
              "done": false,
              "shiftedDays": 0
            }
            gTask.push(todo);
          }
          this.clientList[client]['properties']['toDos'][date] = gTask;
          let body = {
            "properties" : this.clientList[client]['properties'],
            "_rev": this.clientList[client]['token'],
            "id":clientid,
            "type":"client"
           }
           console.log("----------save-----------");
           console.log(body);
           this.dataService.updateClient(body).then();
        }
      }
      // this.client['toDos'][date] = this.routeListNoTask;
      // this.client['appointments'] = this.appointments;
      // let body ={
      //   "properties" : this.carer,
      //   "_rev": this.token,
      //   "id":this.carer['id'],
      //   "type":"carer"
      // }
      //this.dataService.updateCarer(body).then();
    }
  }

    saveCarer(){
      let date = "";
      if(this.dateAuswahl == null){
        var d = new Date(),
              month = '' + (d.getMonth() + 1),
              day = '' + d.getDate(),
              year = d.getFullYear();

          if (month.length < 2) month = '0' + month;
          if (day.length < 2) day = '0' + day;

        date = year + "-" + month +"-"+day+"T00:00:00.000+0000";
      } else{
        var d = new Date(this.dateAuswahl),
              month = '' + (d.getMonth() + 1),
              day = '' + d.getDate(),
              year = d.getFullYear();

          if (month.length < 2) month = '0' + month;
          if (day.length < 2) day = '0' + day;

        date = year + "-" + month +"-"+day+"T00:00:00.000+0000";
      }
      this.appointments[date] = this.routeListNoTask;
      this.carer['appointments'] = this.appointments;
      let body ={
        "properties" : this.carer,
        "_rev": this.token,
        "id":this.carer['id'],
        "type":"carer"
      }
      this.dataService.updateCarer(body).then();

    }

getTasksfromHash(id){
  let viewTask = [];
  for (let item in this.tempListTask) {
      let unhash = item.split("_");
      if(id == unhash[0]){
        viewTask.push(item.substr(2,1));
      }
  }
  return viewTask;
}



  addList(){
    this.buttonState = "Liste ändern";
    this.routeList =[];
    this.routeListNoTask=[];
    for (let client in this.tempList) {
      let tasks = this.getTasksfromHash(client);

      let routeNoTask = {
        "clientID":client,
        "start": this.startTime[client],
        "end": this.endTime[client],
      };
      let route = {
        "clientID":client,
        "start": this.startTime[client],
        "end": this.endTime[client],
        "tasks": tasks
      };
      this.routeList.push(route);
      this.routeListNoTask.push(routeNoTask);
    }

    if(this.routeList.length == 0) {
      this.buttonState = "zur Liste hinzufügen";
    }

  }

  setupClient(){
    this.clients = [];
    for(let client of this.clientList){
      this.clients.push(client['properties']);
    }
    this.clientsOnCheck =[];
    for(let client of this.clientList){

      let id = client['properties']['id'];

      let firstName = client['properties']['firstName'];
      let lastName = client['properties']['lastName'];
      let tasks = [];
      let checked = false;
      let index = 0;
      for(let item of client['properties']['generalTasks']){
        let task = {
          task: item,
          id: index,
          checked: false
        };
        tasks.push(task);
        index++;
      }

      let clientOCK = {
        id : id,
        firstName: firstName,
        lastName : lastName,
        tasks : tasks,
        checked: checked
      }
      this.clientsOnCheck.push(clientOCK);

    }
  }


  addtoTemp(checked,id){

    if(checked){
      for(let client of this.clientList){
        if(client['properties']['id'] == id){
          this.tempList[id] = client;
        }
      }
      if(this.timeList.length == 0){
        this.startTime[id] = "10:00";
        if(this.timeToDo[id] == undefined)
          this.endTime[id] = "10:30";
        else
          this.endTime[id] = this.timeCalculation(this.startTime[id],this.timeToDo[id],0);
        let tlobject = {
          start: this.startTime[id],
          end: this.endTime[id],
          id: id
        }
        this.timeList.push(tlobject);
      }else{
        this.startTime[id] = this.timeCalculation(this.timeList[this.timeCounter]['end'],30,0);

        if(this.timeToDo[id] == undefined)
          this.endTime[id] = this.timeCalculation(this.startTime[id],30,0);
        else
          this.endTime[id] = this.timeCalculation(this.startTime[id],this.timeToDo[id],0);
        let tlobject = {
          start: this.startTime[id],
          end: this.endTime[id],
          id:id
        }
        this.timeList.push(tlobject);
        this.timeCounter++;
      }
    } else {
          delete this.tempList[id];
          //this.timeList.pop();
          this.timeList = this.timeList.filter(function(el) {
              return el.id !== id;
          });
          this.timeCounter--;
          if(this.timeCounter < 0) this.timeCounter = 0;
    }

  }
  setFollowingTime(id){
    let change = false;
    let lastid;
    if(this.timeList.length != undefined){
      if(this.timeList.length == 1){
        this.timeList[0]['end'] = this.timeCalculation(this.startTime[id],this.timeToDo[id],0);
      }
      for (let time in this.timeList) {
          if(change){
            this.startTime[this.timeList[time]['id']] = this.timeCalculation(this.endTime[lastid],30,0);
            this.timeList[time]['start'] = this.endTime[lastid];
            if(this.timeToDo[this.timeList[time]['id']] == undefined){
              this.endTime[this.timeList[time]['id']] = this.timeCalculation(this.startTime[this.timeList[time]['id']],30,0);
              this.timeList[time]['end'] = this.timeCalculation(this.startTime[this.timeList[time]['id']],30,0);
            }else{
              this.endTime[this.timeList[time]['id']] = this.timeCalculation(this.startTime[this.timeList[time]['id']] ,this.timeToDo[this.timeList[time]['id']],0);
              this.timeList[time]['end'] = this.timeCalculation(this.startTime[this.timeList[time]['id']],this.timeToDo[this.timeList[time]['id']],0);

            }
          }
          if(this.timeList[time]['id'] == id){
            change = true;
          }

          lastid = this.timeList[time]['id'];

      }
    }
  }

  addToTempTask(cid,id,task,checked){
    let hash = cid +"_" + id;
    if(checked){
          if(this.timeToDo[cid] == undefined) this.timeToDo[cid] = 0;
          this.timeToDo[cid] += this.clientList[cid]['properties']['generalTasks'][id]['approximateDurationInMinutes'];
          this.tempListTask[hash] = task;
          this.endTime[cid] = this.timeCalculation(this.startTime[cid], this.timeToDo[cid],0);
    } else {
          delete this.tempListTask[hash];
          let difference = this.clientList[cid]['properties']['generalTasks'][id]['approximateDurationInMinutes'];
          this.timeToDo[cid] -= this.clientList[cid]['properties']['generalTasks'][id]['approximateDurationInMinutes'];
          this.endTime[cid] = this.timeCalculation(this.endTime[cid], difference,1);
    }
  }

  setClient(){
    this.client = this.clientObject;
  }

  timeCalculation(time, diff, operation){
      let timeobj = time.split(":");
      let hour = parseInt(timeobj[0]);
      let min = parseInt(timeobj[1]);
      if(operation == 0){
        min += parseInt(diff);
        if(min >= 60){
          hour +=1;
          if(hour >23){
            hour = 0;
          }
          min %= 60;
        }
      }else{
        min -= parseInt(diff);
        if(min < 0){
          hour -=1;
          if(hour < 0){
            hour = 23;
          }
          min %= 60;
        }
      }
      min = Math.abs(min);
      let smin = "" + min;
      let shour = ""+ hour;
      if(min<10)
        smin = "0" + min;
      if(hour<10)
        shour = "0" + min;

        return shour + ":" + smin;
  }

  setCarer(){
    this.carer = this.carerObject['properties'];
    this.token = this.carerObject['token'];
    this.appointments = this.carer['appointments'];
    this.appointmentkeys = Object.keys(this.appointments);

  }

  compareDate(date1in, date2in){
    var date1 = new Date(date1in);
    var date2 = new Date(date2in);
    var month2 = date2 .getMonth() + 1;
    var day2 = date2 .getDate();
    var year2 = date2 .getFullYear();
    date2 = new Date(year2+"-"+month2+"-"+day2);
    var month1 = date1 .getMonth() + 1;
    var day1 = date1 .getDate();
    var year1 = date1 .getFullYear();
    date1 = new Date(year1+"-"+month1+"-"+day1);
    return date1==date2;
  }


    getdateFreq(dateIn, freq) {
      var tt = dateIn;

      var date = new Date(tt);
      var newdate = new Date(date);

      newdate.setDate(newdate.getDate());

      var dd = newdate.getDate();
      var mm = newdate.getMonth() + 1;
      var y = newdate.getFullYear();

      var someFormattedDate = dd + '/' + mm + '/' + y;
      return new Date(someFormattedDate);
    }

    getDateChange(){
      if(!this.dateAuswahl){
        var date2 = new Date();
        var month2 = date2 .getMonth() + 1;
        var day2 = date2 .getDate();
        var year2 = date2 .getFullYear();
        this.dateAuswahl = year2+"-"+month2+"-"+day2;
      }
      return new Date(this.dateAuswahl);
    }


}
