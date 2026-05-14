import { Component } from '@angular/core';
import { Navbar } from '../../shared/navbar/navbar';
import { FormsModule } from '@angular/forms';
import { Match } from '../../core/services/match';

@Component({
  selector: 'app-dashboard',
  imports: [FormsModule],
  templateUrl: './dashboard.html',
  styleUrl: './dashboard.css',
})
export class Dashboard {

  activeSection: String  = "dashboard";
  showMoreMenu = false;
  match: any = {};
  matches: any[] = [];


  constructor(private matchService: Match) {};


  closeMore(){
    this.showMoreMenu = false;
  }

  toggleMoreMenu(event: Event) {
    event.stopPropagation();
    this.showMoreMenu = !this.showMoreMenu;
  }


  selectMenu(section: String) {
    this.activeSection = section;
    this.showMoreMenu = false;
  }

  changeSection(section: String) {
    this.activeSection = section;
    this.showMoreMenu = false;
  }

  createMatch() {
    this.matchService.createMatch(this.match).subscribe(res => {
      alert("Match Created");
      this.match = {};
    });
  }


}
