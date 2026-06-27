import { environment } from '../../../environments/environment';

const base = environment.apiUrl;

export const API = {
  ANIMAL: `${base}/animal`,
} as const;
