export class Model {
  brand: string;
  model: string;
  id: number;

  public reset() {
    this.brand = '';
    this.model = '';
    this.id = 0;
  }
}
