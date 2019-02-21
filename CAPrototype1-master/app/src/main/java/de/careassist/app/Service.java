package de.careassist.app;

import android.content.Context;

public class Service extends GeneralTask {

  private int content;
  private int complexNumber;
  private float cost;

  public Service (String name, String[] description, String tag, int content, int complexNumber, float cost){

      super(name, description, tag);
      this.content = content;
      this.complexNumber = complexNumber;
      this.cost = cost;
  }

  public int getContent() {
    return content;
  }

  public String getContent(Context context){
        return context.getString(getContent());
    }

  public void setContent(int content) {
    this.content = content;
  }

  public Integer getComplexity() {
    return complexNumber;
  }

  public void setComplexity(int complexNumber) {
    this.complexNumber = complexNumber;
  }

  public String getCost() {return ""+cost;   }

  public void setCost(float cost) {this.cost = cost; }
}