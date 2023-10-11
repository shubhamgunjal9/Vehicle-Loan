import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowVerifiedListComponent } from './show-verified-list.component';

describe('ShowVerifiedListComponent', () => {
  let component: ShowVerifiedListComponent;
  let fixture: ComponentFixture<ShowVerifiedListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ShowVerifiedListComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ShowVerifiedListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
