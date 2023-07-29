export class CalculationLogic {
    elementName!: string;
    formula!: string;

    constructor(elementName:string, formula:string){
        this.elementName=elementName;
        this.formula=formula;
    }    
}