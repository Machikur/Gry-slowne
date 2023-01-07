import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ScrabbleCellComponent } from './scrabble-cell.component';

describe('ScrabbleCellComponent', () => {
  let component: ScrabbleCellComponent;
  let fixture: ComponentFixture<ScrabbleCellComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ScrabbleCellComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ScrabbleCellComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
