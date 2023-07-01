import { Component, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { MedicineClass } from '../MedicineClass';
import { FileUploadService } from '../file-upload.service';
import { environment } from 'src/environments/environment';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-medicine-search',
  templateUrl: './medicine-search.component.html',
  styleUrls: ['./medicine-search.component.css']
})
export class MedicineSearchComponent implements OnInit, OnChanges {


  imageApi: string = `${environment.apiUrl}/${environment.images}/`

  constructor(private service: FileUploadService, private router: Router ,
    private activatedroute:ActivatedRoute) { }
  ngOnChanges(changes: SimpleChanges): void {
   
    
  }

  medicine: MedicineClass[]; originalMedicine: MedicineClass[];


  ngOnInit(): void {
    let search : string=this.activatedroute.snapshot.paramMap.get('search') || '';
    
    
    this.service.getAllMedicine().subscribe(result1 => {
      this.medicine = result1.filter(m => m.name.toLocaleLowerCase().
      includes(search.toLowerCase()));;
      this.originalMedicine = this.medicine;
    });
  }

  buyNow(medicine: MedicineClass) {

    /*let cart: MedicineClass[] = JSON.parse(localStorage.getItem('cart') ?? '{}');
    cart.push(medicine);*/
    localStorage.setItem('cart', JSON.stringify(medicine));

    this.router.navigate(['/checkout']);

  }

  
  


}
