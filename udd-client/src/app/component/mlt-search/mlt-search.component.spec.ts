import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MltSearchComponent } from './mlt-search.component';

describe('MltSearchComponent', () => {
  let component: MltSearchComponent;
  let fixture: ComponentFixture<MltSearchComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MltSearchComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MltSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
