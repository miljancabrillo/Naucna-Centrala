import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TasksAndProccessesComponent } from './tasks-and-proccesses.component';

describe('TasksAndProccessesComponent', () => {
  let component: TasksAndProccessesComponent;
  let fixture: ComponentFixture<TasksAndProccessesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TasksAndProccessesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TasksAndProccessesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
