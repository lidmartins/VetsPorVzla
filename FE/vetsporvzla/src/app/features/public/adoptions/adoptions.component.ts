import { Component } from '@angular/core';

@Component({
  selector: 'app-adoptions',
  imports: [],
  templateUrl: './adoptions.component.html',
  styleUrl: './adoptions.component.scss'
})
export class AdoptionsComponent {
  activeTab: 'list' | 'apply' = 'list';

  adoptData = [
    { name: 'Canela', age: '2 años', size: 'Mediano', health: 'Sano', status: 'DISPONIBLE', badgeBg: '#2F9E63', icon: '🐕', bg: 'linear-gradient(140deg,#E3F0EE,#cfe6e2)' },
    { name: 'Simón',  age: '6 meses', size: 'Pequeño', health: 'En tratamiento', status: 'EN PROCESO', badgeBg: '#F26B4E', icon: '🐕', bg: 'linear-gradient(140deg,#FBE7E0,#f5d6cc)' },
    { name: 'Maya',   age: '4 años', size: 'Grande', health: 'Sano', status: 'DISPONIBLE', badgeBg: '#2F9E63', icon: '🐕', bg: 'linear-gradient(140deg,#E3F0EE,#cfe6e2)' },
    { name: 'Toby',   age: '1 año', size: 'Mediano', health: 'Sano', status: 'ADOPTADO', badgeBg: '#9aa49f', icon: '🐕', bg: 'linear-gradient(140deg,#F4F0E8,#ece7dc)' },
    { name: 'Nube',   age: '3 años', size: 'Pequeño', health: 'Sano', status: 'DISPONIBLE', badgeBg: '#2F9E63', icon: '🐈', bg: 'linear-gradient(140deg,#F3E8F1,#e8d4f0)' },
    { name: 'Coco',   age: '8 meses', size: 'Mediano', health: 'Recuperándose', status: 'EN PROCESO', badgeBg: '#F26B4E', icon: '🐈', bg: 'linear-gradient(140deg,#FBE7E0,#f5d6cc)' },
  ];

  setTab(tab: 'list' | 'apply') {
    this.activeTab = tab;
  }
}
