import { UserClass } from "../UserClass";

export interface Order {
    name: string;
    category: string;
    qty: number;
    price: number;
    orderDate: Date;
    expectedDate: Date;
    deliveredBy: string;
    user?: UserClass;
  }