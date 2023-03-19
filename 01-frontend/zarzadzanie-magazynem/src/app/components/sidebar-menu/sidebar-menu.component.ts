import { Component, Renderer2, ElementRef } from '@angular/core';

@Component({
  selector: 'app-sidebar-menu',
  templateUrl: './sidebar-menu.component.html',
  styleUrls: ['./sidebar-menu.component.css']
})
export class SidebarMenuComponent {
  hoveredButton: HTMLElement | null = null;
  timeout: any;

  constructor(private renderer: Renderer2, private el: ElementRef) {}

  onMouseEnter(event: MouseEvent) {
    this.hoveredButton = (event.target as HTMLElement).closest('.menu-item');
    if (this.hoveredButton) {
      this.timeout = setTimeout(() => {
        this.renderer.addClass(this.hoveredButton, 'hovered');
      }, 1000);
    }
  }

  onMouseLeave(event: MouseEvent) {
    if (this.hoveredButton) {
      clearTimeout(this.timeout);
      this.renderer.removeClass(this.hoveredButton, 'hovered');
    }
  }
}
