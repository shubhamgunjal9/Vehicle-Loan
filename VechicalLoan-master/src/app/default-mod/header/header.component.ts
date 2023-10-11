import { Component } from '@angular/core';
import { MatIconRegistry } from '@angular/material/icon';
import { DomSanitizer } from '@angular/platform-browser';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent {

  constructor(private matIconRegistry: MatIconRegistry,
    private domSanitizer: DomSanitizer)
    {
      this.matIconRegistry.addSvgIcon(
        "projecticon",
        this.domSanitizer.bypassSecurityTrustResourceUrl("/assets/onlinelogomaker-011623-1727-9181.svg"))
    }
}
