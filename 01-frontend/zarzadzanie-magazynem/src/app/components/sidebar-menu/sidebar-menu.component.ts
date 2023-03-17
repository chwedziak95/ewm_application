import { Component } from '@angular/core';

@Component({
  selector: 'app-sidebar-menu',
  templateUrl: './sidebar-menu.component.html',
  styleUrls: ['./sidebar-menu.component.css']
})
export class SidebarMenuComponent {
  showIcons = false;

  onMouseEnter() {
    this.showIcons = true;
  }

  onMouseLeave() {
    this.showIcons = false;
  }
}
