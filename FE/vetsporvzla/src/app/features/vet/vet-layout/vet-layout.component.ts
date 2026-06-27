// vet-layout.component.ts
import {Component, inject} from '@angular/core';
import {RouterLink, RouterLinkActive, RouterOutlet} from '@angular/router';
import {AuthService} from '../../../core/auth/auth.service';

@Component({
  selector: 'app-vet-layout',
  standalone: true,
  imports: [RouterOutlet, RouterLink, RouterLinkActive],
  templateUrl: './vet-layout.component.html',
  styleUrl: './vet-layout.component.scss'
})
export class VetLayoutComponent {
  private auth = inject(AuthService);
  logout() { this.auth.logout(); }
}
