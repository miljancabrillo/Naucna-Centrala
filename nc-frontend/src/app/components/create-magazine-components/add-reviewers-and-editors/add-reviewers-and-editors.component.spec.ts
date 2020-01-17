import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddReviewersAndEditorsComponent } from './add-reviewers-and-editors.component';

describe('AddReviewersAndEditorsComponent', () => {
  let component: AddReviewersAndEditorsComponent;
  let fixture: ComponentFixture<AddReviewersAndEditorsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddReviewersAndEditorsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddReviewersAndEditorsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
