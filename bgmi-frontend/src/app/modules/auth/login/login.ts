import { Component } from '@angular/core';
import { Auth } from '../../../core/services/auth';
import { Router, RouterLink } from '@angular/router';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-login',
  imports: [FormsModule, RouterLink],
  templateUrl: './login.html',
  styleUrl: './login.css',
})
export class Login {
  loginForm = {
    email: '',
    password: '',
  };

  constructor(
    private auth: Auth,
    private router: Router,
  ) {}

  onLogin(form: any) {
    this.auth.login(this.loginForm).subscribe((res: any) => {
      localStorage.setItem('token', res.token);
      localStorage.setItem('role', res.role);
      
      console.log(localStorage.getItem('token'));
      console.log(localStorage.getItem('role'));

      if(res.role === 'ROLE_ADMIN') {
        console.log("if block executed ");
        this.router.navigate(['/admin'])
      } else {
        console.log("else / is executed ");
        this.router.navigate(['/']);
      }
    });
  }
}
