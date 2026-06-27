import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-home',
  imports: [RouterLink],
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss'
})
export class HomeComponent {
  features = [
    { icon: '🔴', bg: '#FEF2F2', color: '#DC4A4A', title: 'Mascotas Perdidas', desc: 'Reporta y busca mascotas perdidas durante emergencias. Red activa en todo el país.', link: '/perdidas', linkLabel: 'Ver reportes', go: '#DC4A4A' },
    { icon: '🟢', bg: '#F0FDF4', color: '#2F9E63', title: 'Mascotas Encontradas', desc: 'Encontraste un animal y quieres ayudarlo a regresar a casa.', link: '/encontradas', linkLabel: 'Ver reportes', go: '#2F9E63' },
    { icon: '🏠', bg: '#F0F7F5', color: '#0F766E', title: 'Refugios Temporales', desc: 'Red de refugios coordinados por veterinarios con capacidad en tiempo real.', link: '/refugios', linkLabel: 'Ver refugios', go: '#0F766E' },
    { icon: '💜', bg: '#F5F3FF', color: '#7E5BD0', title: 'Adopciones', desc: 'Da un hogar permanente a animales rescatados durante emergencias.', link: '/adopciones', linkLabel: 'Ver animales', go: '#7E5BD0' },
  ];

  steps = [
    { ic: '📱', bg: '#E9F2F0', num: '01', title: 'Reportas el caso', desc: 'Completa el formulario con foto, descripción y ubicación en menos de 2 minutos.' },
    { ic: '🩺', bg: '#FEF3E2', num: '02', title: 'Revisión veterinaria', desc: 'Un veterinario de nuestra red verifica y valida el reporte en menos de 1 hora.' },
    { ic: '🤝', bg: '#FEF2F2', num: '03', title: 'Coordinación de rescate', desc: 'Activamos el refugio más cercano y coordinamos el traslado seguro del animal.' },
    { ic: '🏡', bg: '#F5F3FF', num: '04', title: 'Reencuentro familiar', desc: 'Notificamos a los dueños y facilitamos el reencuentro con seguimiento veterinario.' },
  ];
}
