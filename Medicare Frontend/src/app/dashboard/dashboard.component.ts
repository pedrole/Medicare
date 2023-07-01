import { Component, OnInit } from '@angular/core';
import { FileUploadService } from '../file-upload.service';
import { MedicineClass } from '../MedicineClass';
import { environment } from 'src/environments/environment';
import { Route, Router } from '@angular/router';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  medicines: MedicineClass[] ; lastestMedicnes: MedicineClass[];
  imageApi: string = `${environment.apiUrl}/${environment.images}/`

  constructor(private service: FileUploadService,private router:Router) { }

  ngOnInit(): void {
    this.service.getAllMedicine().subscribe(result => {
      this.lastestMedicnes = result.slice(-2);
      this.medicines = this.getRandomElements(result, 3);
      console.log(result);
      
      

    })
  }

  getRandomElements<T>(array: T[], count: number): T[] {
    const shuffled = array.sort(() => Math.random() - 0.5);
    return shuffled.slice(0, count);
  }

  isActive(index: number) {
    if (index === 0)
      return true;
    return false;
  }

  buyNow(medicine: MedicineClass) {

    /*let cart: MedicineClass[] = JSON.parse(localStorage.getItem('cart') ?? '{}');
    cart.push(medicine);*/
    localStorage.setItem('cart', JSON.stringify(medicine));

    this.router.navigate(['/checkout']);

  }


}
