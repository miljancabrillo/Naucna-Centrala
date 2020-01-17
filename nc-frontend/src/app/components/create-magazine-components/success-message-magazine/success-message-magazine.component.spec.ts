import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SuccessMessageMagazineComponent } from './success-message-magazine.component';

describe('SuccessMessageMagazineComponent', () => {
  let component: SuccessMessageMagazineComponent;
  let fixture: ComponentFixture<SuccessMessageMagazineComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SuccessMessageMagazineComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SuccessMessageMagazineComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
