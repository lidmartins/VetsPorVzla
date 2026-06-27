import { Component } from '@angular/core';

@Component({
  selector: 'app-shelters',
  imports: [],
  templateUrl: './shelters.component.html',
  styleUrl: './shelters.component.scss'
})
export class SheltersComponent {
  shelters = [
    {
      name: 'Refugio Veterinario La Guaira', state: 'La Guaira', addr: 'Av. La Playa, Sector Catia La Mar',
      status: 'open', statusLabel: 'Abierto', dogs: 12, maxDogs: 20, cats: 8, maxCats: 15,
      services: ['Atención veterinaria', 'Alimentación', 'Baño y aseo', 'Medicamentos'],
    },
    {
      name: 'Centro Animal Miranda', state: 'Miranda', addr: 'Calle Maturín, Los Teques',
      status: 'full', statusLabel: 'Lleno', dogs: 20, maxDogs: 20, cats: 15, maxCats: 15,
      services: ['Atención veterinaria', 'Cirugías de emergencia', 'Alimentación'],
    },
    {
      name: 'Refugio Caracas Norte', state: 'Distrito Capital', addr: 'Urb. La Urbina, Caracas',
      status: 'open', statusLabel: 'Abierto', dogs: 7, maxDogs: 25, cats: 3, maxCats: 20,
      services: ['Atención veterinaria', 'Alimentación', 'Transporte de rescate'],
    },
  ];
}
