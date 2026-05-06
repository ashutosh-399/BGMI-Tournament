import { Component } from '@angular/core';
import { AuthRoutingModule } from "../modules/auth/auth-routing-module";

@Component({
  selector: 'app-landing',
  imports: [AuthRoutingModule],
  templateUrl: './landing.html',
  styleUrl: './landing.css',
})
export class Landing {
  
}
