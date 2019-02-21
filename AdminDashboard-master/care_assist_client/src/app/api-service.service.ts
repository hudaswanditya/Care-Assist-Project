import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Headers, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/toPromise';


@Injectable()
export class ApiServiceService {


  uri = "http://localhost:3000";

  constructor(private http : Http) { }

  getCarersfromDB(){
    return this.http.get(this.uri + '/carer')
    .map(res => res.json());
  }

  getClientsfromDB(){
    return this.http.get(this.uri + '/client')
    .map(res => res.json());
  }

  getCarerByIdfromDB(id){
    let url = this.uri + '/carer/' + id;
    return this.http.get(url)
    .map(res => res.json());
  }

  getClientByIdfromDB(id){
    return this.http.get(this.uri + '/client/' + id)
    .map(res => res.json());
  }

  updateCarer(body){
    let headers = new Headers({ 'Content-Type': 'application/json' });
    let options = new RequestOptions({ headers: headers });
    let url = this.uri + '/create';
    console.log("-----------service--------------");
    console.log(body);
    return this.http.put(url, JSON.stringify(body), options).toPromise()
         .then(body)
               .catch(this.handleErrorPromise);
  }

  updateClient(body){
    let headers = new Headers({ 'Content-Type': 'application/json' });
    let options = new RequestOptions({ headers: headers });
    let url = this.uri + '/create';
    console.log("-----------service--------------");
    console.log(body);
    return this.http.put(url, JSON.stringify(body), options).toPromise()
         .then(body)
               .catch(this.handleErrorPromise);
  }

  // private handleErrorObservable (error: Response | any) {
  // 	console.error(error.message || error);
  // 	return Observable.throw(error.message || error);
  // }
  private handleErrorPromise (error: Response | any) {
  	console.error(error.message || error);
  	return Promise.reject(error.message || error);
  }

  success(){
    return "Successful!";
  }

}
