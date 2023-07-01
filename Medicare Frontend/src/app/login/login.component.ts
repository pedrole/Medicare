import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { DataService } from '../data.service';
import { UserClass } from '../UserClass';
import { EmailValidator } from '@angular/forms';
import { Credentials } from '../models/credentials';
import { LoginService } from '../login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  
  isSubmitted= false;
  constructor(private loginService: LoginService, private router: Router) { }



  ngOnInit(): void {
  }

  user={
   
    password:'',
    email:'',

  }
  //add record
  checkUser():void{
    const credentials: Credentials = {
      email: this.user.email,
      password: this.user.password

    }

    
     
   
    if(!credentials.email){
      alert('please provide email');
      return;
    }
    
    if(!credentials.password){
      alert('please provide password');
      return;
    }
    //get user
    
    //this.service.getUserByEmail(this.user.email);
    this.loginService.generateToken(credentials).subscribe({
      next: (response) => {
        //user is logged in
       
      
        const result =  this.loginService.loginUser(response.token);
       
        
        this.loginService.getCurrentUser().subscribe({
          next: (admin) => {
            this.loginService.setUserDetails(admin);
            //redirect: to admin dashboard
           
            this.router.navigate(['/dashboard'])
          }, error: (error) => {
            console.log(error);
          }
        })
      }, error: (error) => {
    
        alert('Invalid Credentials!');
      }
    })
     
    
  }
}
