// vet-layout.component.ts
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
