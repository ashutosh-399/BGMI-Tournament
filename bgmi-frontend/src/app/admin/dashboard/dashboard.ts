import { Component } from '@angular/core';
import { Navbar } from '../../shared/navbar/navbar';

@Component({
  selector: 'app-dashboard',
  imports: [],
  templateUrl: './dashboard.html',
  styleUrl: './dashboard.css',
})
export class Dashboard {
  activeSection: String  = "dashboard";

  showMoreMenu = false;

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

  
}
