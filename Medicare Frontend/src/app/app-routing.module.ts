import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from './dashboard/dashboard.component';
import { EditMedicineComponent } from './edit-medicine/edit-medicine.component';
import { EditUserComponent } from './edit-user/edit-user.component';
import { FormComponent } from './form/form.component';
import { LoginComponent } from './login/login.component';
import { MedDetailsComponent } from './med-details/med-details.component';
import { MedicineFormComponent } from './medicine-form/medicine-form.component';
import { MedicineComponent } from './medicine/medicine.component';
import { UserdetailsComponent } from './userdetails/userdetails.component';
import { UserlistComponent } from './userlist/userlist.component';
import { AdminGuard } from './admin-guard.guard';
import { MedicineSearchComponent } from './medicine-search/medicine-search.component';
import { CheckoutComponent } from './checkout/checkout.component';
import { CustomerGuard } from './customer.guard';

const routes: Routes = [
  { path: '', redirectTo: '/dashboard', pathMatch: 'full' },
  {"path":"users",component:UserlistComponent, canActivate:  [AdminGuard]},
  {"path":"form",component:FormComponent},
  {"path":"med-form",component:MedicineFormComponent},
  {"path":"login",component:LoginComponent},
  {"path":"medicare",component:MedicineComponent , canActivate:  [AdminGuard]} ,
  {"path":"details/:id",component:UserdetailsComponent , canActivate:  [AdminGuard]},
  {"path":"medDetails/:id",component:MedDetailsComponent , canActivate:  [AdminGuard]} ,
  {"path":"edit/:id",component:EditUserComponent , canActivate:  [AdminGuard]},
  {"path":"edit-med/:id",component:EditMedicineComponent , canActivate:  [AdminGuard]},
  {"path":"dashboard",component:DashboardComponent},
  {"path":"medicine-search",component:MedicineSearchComponent},
  {"path":"medicine-search/:search",component:MedicineSearchComponent},
  
  {"path":"checkout",component:CheckoutComponent,  canActivate:  [CustomerGuard]},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
