import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { LoginService } from '../services/login.service';
import { User } from '../model/user';
import { MessageService } from 'primeng/api';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  
  loginForm: FormGroup;
  loginUsers: User[]=[];

  constructor(
    private fb: FormBuilder,
    private serviceLogin:LoginService,
    private messageService: MessageService,
    private router: Router
    ) { 
    this.loginForm = this.fb.group({
      email: ['',Validators.email],
      password: ['',Validators.required]
    });
  };

  ngOnInit() {};
  
  loginUser(){
    const loginuser = this.loginForm.value;
    this.serviceLogin.loginUser(loginuser).subscribe(
      {
        next:(response:any) => {
          console.log(response);
          this.loginUsers.push(response);
          localStorage.setItem('token',"responseToken");
          this.loginForm.reset();
          this.messageService.add({ severity: 'success', summary: 'Success', detail: 'Connexion effecté avec succès',life: 3000 });
          setTimeout(() => {
            this.router.navigate(['/home']);
          }, 3000);
      },
      error:(error:any) => {
        console.log(error);
        this.messageService.add({ severity: 'error', summary: 'Erreur', detail: error.message, life: 3000 });
      }}
    );
  }
  authGuard(){
    return this.loginUsers.length > 0;
  }
}
