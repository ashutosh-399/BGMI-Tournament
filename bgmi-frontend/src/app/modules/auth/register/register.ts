import { Component } from '@angular/core';
import { Auth } from '../../../core/services/auth';
import { Router, RouterLink } from '@angular/router';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-register',
  imports: [FormsModule, RouterLink],
  templateUrl: './register.html',
  styleUrl: './register.css',
})
export class Register {

  registerData = {
    name: '',
    email: '',
    phone: '',
    password: ''
};

constructor(private auth: Auth, private router: Router){}

onRegister(form: any) {

  if(form.invalid){
    alert("Plese fill all required fileds");
    console.log("Plese fill all required fileds")
    return;
  }

  this.auth.register(this.registerData).subscribe(() => {
    alert('Registered Successfully');
    this.router.navigate(["/login"]);
  });
}
}
