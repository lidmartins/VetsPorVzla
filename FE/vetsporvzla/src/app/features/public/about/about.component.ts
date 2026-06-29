import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-about',
  imports: [RouterLink],
  templateUrl: './about.component.html',
  styleUrl: './about.component.scss'
})
export class AboutComponent {
  values = [
    { icon: '🩺', title: 'Liderazgo veterinario', desc: 'Cada decisión clínica y cada caso pasa por médicos veterinarios colegiados.', tint: '#E3F0EE' },
    { icon: '🤝', title: 'Comunidad resiliente', desc: 'Voluntarios, rescatistas y familias venezolanas trabajando en red.', tint: '#fdecec' },
    { icon: '🛡️', title: 'Adopción responsable', desc: 'Acompañamos cada adopción para garantizar el bienestar del animal.', tint: '#F3E8F1' },
  ];
}
