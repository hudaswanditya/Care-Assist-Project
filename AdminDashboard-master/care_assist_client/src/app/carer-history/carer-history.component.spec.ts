import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CarerHistoryComponent } from './carer-history.component';

describe('CarerHistoryComponent', () => {
  let component: CarerHistoryComponent;
  let fixture: ComponentFixture<CarerHistoryComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CarerHistoryComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CarerHistoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
