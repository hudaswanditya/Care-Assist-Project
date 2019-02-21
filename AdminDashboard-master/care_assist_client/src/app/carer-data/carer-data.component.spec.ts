import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CarerDataComponent } from './carer-data.component';

describe('CarerDataComponent', () => {
  let component: CarerDataComponent;
  let fixture: ComponentFixture<CarerDataComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CarerDataComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CarerDataComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
