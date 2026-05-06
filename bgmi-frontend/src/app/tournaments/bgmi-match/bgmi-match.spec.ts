import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BgmiMatch } from './bgmi-match';

describe('BgmiMatch', () => {
  let component: BgmiMatch;
  let fixture: ComponentFixture<BgmiMatch>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BgmiMatch]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BgmiMatch);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
