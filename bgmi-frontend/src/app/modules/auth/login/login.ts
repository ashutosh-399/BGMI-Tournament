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
    email: "",
    password: ""
  };

  constructor(private auth: Auth, private router: Router){}

  onLogin(form: any){
      this.auth.login(this.loginForm).subscribe((res: any) => {
      localStorage.setItem("token", res.token);
      console.log("Login Success")
      this.router.navigate(["/"])
    })
  }
}
