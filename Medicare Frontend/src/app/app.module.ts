import { HttpClient, HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule,ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { UserlistComponent } from './userlist/userlist.component';
import { UserdetailsComponent } from './userdetails/userdetails.component';
import { FormComponent } from './form/form.component';
import { EditUserComponent } from './edit-user/edit-user.component';
import { LoginComponent } from './login/login.component';
import { MedicineComponent } from './medicine/medicine.component';
import { MedDetailsComponent } from './med-details/med-details.component';
import { MedicineFormComponent } from './medicine-form/medicine-form.component';
import { EditMedicineComponent } from './edit-medicine/edit-medicine.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { authInterceptorProviders } from './auth.interceptor';
import { MedicineSearchComponent } from './medicine-search/medicine-search.component';
import { CheckoutComponent } from './checkout/checkout.component';
import { DatePipe } from '@angular/common';
import { ImagePipe } from './image.pipe';
import {  errorInterceptorProviders } from './interceptors/error.interceptor';

@NgModule({
  declarations: [
    AppComponent,
    UserlistComponent,
    UserdetailsComponent,
    FormComponent,
    EditUserComponent,
    LoginComponent,
    MedicineComponent,
    MedDetailsComponent,
    MedicineFormComponent,
    EditMedicineComponent,
    DashboardComponent,
    MedicineSearchComponent,
    CheckoutComponent,
    ImagePipe
    
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    AppRoutingModule,
    ReactiveFormsModule,
    
  ],
  providers: [authInterceptorProviders, DatePipe, errorInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule { }
