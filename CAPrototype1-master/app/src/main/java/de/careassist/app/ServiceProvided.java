package de.careassist.app;

import java.util.ArrayList;

public class ServiceProvided {

  private ArrayList<Service> serviceList;
  private ArrayList<Service> dupeList;
  private float sum;

  public float getSum() {
    return sum;
  }

  public ArrayList<Service> getDupeList() {
    return dupeList;
  }

  public ArrayList<Service> getServiceList() {
    return serviceList;
  }

  public void setServiceList(ArrayList<Service> serviceList) {
    this.serviceList = serviceList;
  }

/*
  public void calculate() {
    for(Service service : serviceList) {
      sum += service.getCost();
      dupeList.add(service);
    }
    serviceList.clear();
  }
  */

}