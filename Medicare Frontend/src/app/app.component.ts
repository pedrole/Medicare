import { Component, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { LoginService } from './login.service';
import { ERole } from "./enums/ERole";
import { NavigationEnd, NavigationStart, Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  private currentRole = '';

  constructor(public loginService: LoginService, private router: Router) {
    console.log(loginService.isLoggedIn());


  }



  title = 'capstone';
  ERole = ERole;

  logout() {
    this.loginService.logout();
    this.router.navigate(['/login']);
    //window.location.reload();
  }

  ngOnInit() {
    this.router.events.subscribe(event => {
      if (event instanceof NavigationEnd) {
        this.onRouteNavigation();
      }

    });
  }

  onRouteNavigation() {
    // Your method logic here

  }
  onSubmit(value: string): void {
    this.router.routeReuseStrategy.shouldReuseRoute = function () {
      return false;
    }
    this.router.onSameUrlNavigation = 'reload';

    if (value)
      this.router.navigate(['/medicine-search', value]);
    else
      this.router.navigate(['/medicine-search']);



    /*  this.medicine = this.originalMedicine.filter(m => m.name.toLocaleLowerCase().
       includes(value.toLowerCase())); */



  }
}
