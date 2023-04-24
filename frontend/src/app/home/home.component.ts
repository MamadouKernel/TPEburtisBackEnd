import { Component, OnInit } from '@angular/core';
import { MenuItem } from 'primeng/api';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent implements OnInit {
  items!: MenuItem[];

  activeItem!: MenuItem;

  activeItem2!: MenuItem;

  ngOnInit() {
    this.items = [
      { label: 'Accueil', icon: 'pi pi-fw pi-home' },
      { label: 'Utilisateurs', icon: 'pi pi-fw pi-user' },
    ];

    this.activeItem = this.items[0];
  }
}
