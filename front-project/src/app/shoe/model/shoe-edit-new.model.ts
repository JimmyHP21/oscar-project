export class ShoeEditNewModel {
  id: number;
  size: number;
  category: string;
  color: string;
  price;
  brand: string;
  quantity: number;
  description: string;
  dateRegister;


  /**
   * Constructor
   *
   * @param shoe
   */
  constructor(shoe?) {
    shoe = shoe || {};
    this.id = shoe.id;
    this.size = shoe.size;
    this.category = shoe.category;
    this.color = shoe.color;
    this.price = shoe.price;
    this.brand = shoe.brand;
    this.quantity = shoe.quantity;
    this.description = shoe.description;
    this.dateRegister = shoe.dateRegister;
  }
}
