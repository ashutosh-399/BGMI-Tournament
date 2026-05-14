import { Component } from '@angular/core';
import { Match } from '../../core/services/match';

@Component({
  selector: 'app-bgmi-match',
  imports: [],
  templateUrl: './bgmi-match.html',
  styleUrl: './bgmi-match.css',
})
export class BgmiMatch {

  matches: any[] = [];

  matches5: any[] = [];
  matches10: any[] = [];
  matches20: any[] = [];
  
  constructor(private matchService: Match) {};

  ngOnInit() {
    console.log("Component loaded");
    this.matchService.getAllMatch().subscribe({
      next: (data: any) => {
        console.log("Api Data", data)
        this.matches = data;

        this.matches5 = this.matches.filter(m => m.entryFees == 5);
        this.matches10 = this.matches.filter(m => m.entryFees ==10);
        this.matches20 = this.matches.filter(m => m.entryFees ==20);
        
      } 
    });
  }


}
