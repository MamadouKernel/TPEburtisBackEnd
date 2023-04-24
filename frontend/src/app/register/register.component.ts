import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { RegisterService } from '../services/register.service';
import { User } from '../model/user';
import { MessageService } from 'primeng/api';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
})
export class RegisterComponent implements OnInit {
  inscriptionForm: FormGroup;
  users: User[] = [];


  constructor(
    private formBuilder: FormBuilder,
    private registerService: RegisterService,
    private messageService: MessageService,
    private router: Router
  ) {
    this.inscriptionForm = this.formBuilder.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      email: ['', Validators.email],
      password: ['', Validators.required],
    });
  }

  ngOnInit(): void {}

  createUser(): void {
    const user: User = this.inscriptionForm.value;
    this.registerService.createUser(user).subscribe({
      next:(response: any) => {
        console.log(response);
        this.users.push(response); // Ajouter le nouvel utilisateur dans le tableau des utilisateurs
        this.inscriptionForm.reset();
        this.messageService.add({ severity: 'success', summary: 'Success', detail: 'Enregsitrement effecté avec succès',life: 3000 });
        setTimeout(() => {
          this.router.navigate(['/connexion']);
        }, 3000);
      },
      error:(error: any) => {
        console.error(error);
        let errorMessage: string;
        if (error.status === 400) {
          errorMessage = 'Les informations fournies sont invalides. Veuillez vérifier et réessayer.';
        } else if (error.status === 409) {
          errorMessage = 'Un utilisateur avec cet e-mail existe déjà. Veuillez utiliser une adresse e-mail différente.';
        } else {
          errorMessage = 'Une erreur inattendue s\'est produite. Veuillez réessayer plus tard.';
        }
        this.messageService.add({ severity: 'error', summary: 'Erreur', detail: errorMessage,life: 3000 });
      }}
    );
  }

}
