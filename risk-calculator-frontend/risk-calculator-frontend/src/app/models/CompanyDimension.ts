import { Dimension } from "./Dimension";



export class CompanyDimension {
    companyName!:string;
    dimensions!: Dimension[];

    constructor(companyName: string) {
        this.companyName = companyName;
        this.dimensions = [];
      }
    
      addDimensions(dimension: Dimension) {
        this.dimensions.push(dimension);
      }
}