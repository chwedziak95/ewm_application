import { Component, OnInit } from '@angular/core';
import * as $ from 'jquery';
import * as Popper from 'popper.js';
import './sidebar-menu.component.js';

@Component({
  selector: 'app-sidebar-menu',
  templateUrl: './sidebar-menu.component.html',
  styleUrls: ['./sidebar-menu.component.css']
})
export class SidebarMenuComponent implements OnInit {
  constructor() {}

  ngOnInit() {
    $('.sidebar-menu').SidebarMenu({
      accordionMenu: true,
      startCollapsed: false,
      slideSpeed: 200
    });
  }
}
