import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ReviewMagazineDataAdminComponent } from './review-magazine-data-admin.component';

describe('ReviewMagazineDataAdminComponent', () => {
  let component: ReviewMagazineDataAdminComponent;
  let fixture: ComponentFixture<ReviewMagazineDataAdminComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ReviewMagazineDataAdminComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ReviewMagazineDataAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
