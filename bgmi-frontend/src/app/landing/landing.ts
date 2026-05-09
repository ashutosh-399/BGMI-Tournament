import { Component } from '@angular/core';
import { AuthRoutingModule } from "../modules/auth/auth-routing-module";
import { Navbar } from '../shared/navbar/navbar';

@Component({
  selector: 'app-landing',
  imports: [AuthRoutingModule, Navbar],
  templateUrl: './landing.html',
  styleUrl: './landing.css',
})
export class Landing {
  
}
