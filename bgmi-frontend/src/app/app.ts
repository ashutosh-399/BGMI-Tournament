import { Component, signal } from '@angular/core';
import { NavigationEnd, Router, RouterOutlet } from '@angular/router';
import { Navbar } from './shared/navbar/navbar';
import { filter } from 'rxjs';
import { Footer } from './shared/footer/footer';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, Navbar],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('bgmi-frontend');


  showNavbar = true;
  

  constructor(
    private router: Router
  ) {
    this.router.events
      .pipe(filter(event => event instanceof NavigationEnd))
      .subscribe((event: any) => {

        const url = event.urlAfterRedirects;

        //  hide navbar on auth pages
        if (url.startsWith('/auth')) {
          this.showNavbar = false;
        } else {
          this.showNavbar = true;
        }

      });
  }

}
