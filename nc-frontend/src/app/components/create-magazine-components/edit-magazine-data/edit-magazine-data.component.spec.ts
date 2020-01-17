import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditMagazineDataComponent } from './edit-magazine-data.component';

describe('EditMagazineDataComponent', () => {
  let component: EditMagazineDataComponent;
  let fixture: ComponentFixture<EditMagazineDataComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditMagazineDataComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditMagazineDataComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
