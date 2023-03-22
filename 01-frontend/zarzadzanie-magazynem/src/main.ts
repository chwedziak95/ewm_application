/// <reference types="@angular/localize" />

import 'zone.js/plugins/zone-patch-rxjs';

import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';

import { AppModule } from './app/app.module';


platformBrowserDynamic().bootstrapModule(AppModule)
  .catch(err => console.error(err));
