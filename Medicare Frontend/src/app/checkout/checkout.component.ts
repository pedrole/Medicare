import { Component, OnInit } from '@angular/core';
import { Order } from '../models/order';
import { MedicineClass } from '../MedicineClass';
import { DatePipe } from '@angular/common';
import { environment } from 'src/environments/environment';
import { LoginService } from '../login.service';
import { OrderService } from '../services/order.service';
import { UserClass } from '../UserClass';

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent implements OnInit {
  order: Order;
  orderSuccess: boolean;
  cart: MedicineClass | null = null;
  imageApi: string = `${environment.apiUrl}/${environment.images}/`

  constructor(private datePipe: DatePipe, private loginService: LoginService, private orderService: OrderService) { }

  ngOnInit(): void {
    let cartString = localStorage.getItem('cart');
    this.cart = cartString ? JSON.parse(cartString) : null;
    console.log(this.cart);


  }

  onSubmit() {
    //console.log(this.loginService.getUserDetails());

    console.log(this.loginService.getUserDetails());





    if (this.cart != null)
      this.order = {
        name: this.cart.name, category: this.cart.category, qty: 1, price: this.cart.price,
        orderDate: new Date(), expectedDate: new Date(), deliveredBy: 'DTDC Courier'
      }
    this.order.expectedDate = new Date(this.order.expectedDate.setDate(this.order.expectedDate.getDate() + 3));
    let user = new UserClass();
    user.id = this.loginService.getUserDetails().id;
    this.order.user = user;



    this.orderService.create(this.order).subscribe({
      next: (order) => {
        this.orderSuccess = true;
        //this.cart = null;
        localStorage.setItem('cart', '');

      }, error: (error) => {
        console.log(error);

        alert('Order could not be processed!');
      }

    });

  }
}
