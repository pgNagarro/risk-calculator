import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { CalculationLogic } from 'src/app/models/CalculationLogic';
import { CalculationLogicService } from 'src/app/services/calculation-logic.service';
import { CompanyDimensionService } from 'src/app/services/company-dimension.service';
import { DimensionWeightService } from 'src/app/services/dimension-weight.service';

@Component({
  selector: 'app-update-calculation-logic',
  templateUrl: './update-calculation-logic.component.html',
  styleUrls: ['./update-calculation-logic.component.css']
})
export class UpdateCalculationLogicComponent implements OnInit {

  calculationLogicForm: FormGroup;
 

  dropdownOptions: { value: string; label: string }[] = [];
  formulaElements: string[] = [];
  formula: string = '';

  selectedOperation: string | null = null;
  minMaxOptions: string[] = [];
  
  companyDimension:any;
  dimensionWeight:any;
  allCalculationLogic:any;

  calculationLogic!:CalculationLogic;
  elementName:string;
  formulaValue:string;



  constructor(@Inject(MAT_DIALOG_DATA) public data: string,private formBuilder: FormBuilder, private companyDimensionService:CompanyDimensionService,
    private dimensionWeightService:DimensionWeightService, private calculationLogicService:CalculationLogicService,
    private ref:MatDialogRef<UpdateCalculationLogicComponent>) {}

  ngOnInit() {
    this.calculationLogicForm = this.formBuilder.group({
      dropdownOption: [''],
      option1: [''], 
      option2: [''],
    });
    
    this.elementName = this.data;
    this.calculationLogicService.getCalculationLogicByElementName(this.elementName).subscribe((data)=>{
        this.calculationLogic=data;
        this.formulaValue=this.calculationLogic?.formula;
    });
      



    this.companyDimensionService.getCompanyDimension().subscribe((data)=>{
      this.companyDimension=data;
      
      for(const val of this.companyDimension[0].dimensions){
        this.dropdownOptions.push({ value: val.dimensionName, label: val.dimensionName });
      }
    });

    this.dimensionWeightService.getDimensionWeight().subscribe((data)=>{
        this.dimensionWeight=data;

        for(const ele of this.dimensionWeight){
          this.dropdownOptions.push({ value: `${ele.dimension} weight`, label: `${ele.dimension} weight` });
        }
    });

    this.calculationLogicService.getCalculationLogicLogic().subscribe((data)=>{
        this.allCalculationLogic=data;

        for(const ele of this.allCalculationLogic){
          this.dropdownOptions.push({ value: ele.elementName, label: ele.elementName });
        }
    });
    this.dropdownOptions.push({ value:'total_risk_capped_score', label: 'total_risk_capped_score' });

  }




  onElementSelect(event: any) {
    const selectedElement = event.target.value;
    if (selectedElement) {
      if (!this.isLastElementOption()) {
       
        this.formulaElements.push(selectedElement);
        this.calculationLogicForm.get('dropdownOption')?.setValue('');
      } else {
        alert('Invalid formula: Cannot add formula element after a formula element!');
      }
    }
  }

  onOperationSelect(event: any) {
    const selectedOperation = event.target.value;
    if ((selectedOperation && !this.isLastElementOperation()) || (selectedOperation && this.isLastElementOperation() && (selectedOperation === "min" || selectedOperation === "max"))) {
      if (this.isFormulaEmpty() && !this.isValidFirstOperation(selectedOperation)) {
        alert("Invalid formula: Formula cannot start with this operation.");
        return;
      }
      if (selectedOperation === "min" || selectedOperation === "max") {
        this.selectedOperation = selectedOperation;
        // Do not reset minMaxOptions here, wait until both options are selected
        this.calculationLogicForm.get("option1")?.setValidators(Validators.required);
        this.calculationLogicForm.get("option2")?.setValidators(Validators.required);
      } else {
        this.formulaElements.push(selectedOperation);
        this.calculationLogicForm.get("operation")?.setValue("");
      }
      this.updateFormula(); // Update the formula after selecting an operation
    }else {
      alert("Invalid formula: Cannot add operation after Operation (Only min and max operation can be added)");
    }
  }
  
  
  

  // Helper function to check if the formula is empty
isFormulaEmpty(): boolean {
  return this.formulaElements.length === 0;
}

// Helper function to check if the selectedElement is a valid first operation
isValidFirstOperation(selectedElement: string): boolean {
  return ['min', 'max'].includes(selectedElement);
}

updateFormula() {
  this.formula = '';
  for (let i = 0; i < this.formulaElements.length; i++) {
    const element = this.formulaElements[i];
    if (element === "min" || element === "max") {
      const option1 = this.minMaxOptions[0];
      const option2 = this.minMaxOptions[1];
      if (option1 && option2) {
        this.formula += `${element}(${option1},${option2})`;
        this.formulaElements.splice(i + 1, 2); // Remove the processed options from formulaElements
      } else {
        this.formula += element;     
      }
    } else {
     
      if(((i-1)>=0) && (this.formulaElements[i-1]== "min" || this.formulaElements[i-1]== "max")){
          this.formula+='('+element+',';    
      }
      else if(((i-2)>=0) && (this.formulaElements[i-2]== "min" || this.formulaElements[i-2]== "max")){
        this.formula+=element+')';    
      }
      else{
        this.formula += element;
      }
    }
  }
}


  undoSelection() {
    if(this.formulaElements.length>=3){
      if(this.formulaElements[this.formulaElements.length-3]=='min' || this.formulaElements[this.formulaElements.length-3]=='min'){
        this.formulaElements.pop();
        this.formulaElements.pop();
        this.formulaElements.pop();
      }
      else{
        this.formulaElements.pop();
      }
    }
    else{
      this.formulaElements.pop();
    }
    
    this.updateFormula();
  }

  isLastElementOption(): boolean {
    return this.formulaElements.length > 0 && this.dropdownOptions.some(option => option.value === this.formulaElements[this.formulaElements.length - 1]);
  }

  isLastElementOperation(): boolean {
    return this.formulaElements.length > 0 && '+-/*%'.includes(this.formulaElements[this.formulaElements.length - 1]);
  }
  

  onSubmit() {

    if (this.isLastElementOperation()) {
      alert('Invalid formula: Formula should not end with an operation.');
      return;
    }

    if (this.calculationLogicForm.invalid && this.isEmptyString(this.formula)) {
      console.log('form invalid ');
      return;
    }

   this.calculationLogic.formula = this.formula;
   this.calculationLogicService.updateCalculationLogic(this.elementName.toLowerCase(),this.calculationLogic).subscribe((data)=>{
      console.log(data);
      this.ref.close();
   });

    // Optionally, you can reset the form after submission
    this.calculationLogicForm.reset();
  }


   containsNumber(inputString: string): boolean {
    const regex = /\d/;
    return regex.test(inputString);
  }

   isEmptyString(inputString: string): boolean {
    return inputString.trim() === '';
  }


  onMinOrMaxOptionSelect(optionNumber: number, event: any) {
    const selectedOption = event.target.value;
    if (selectedOption) {
      this.minMaxOptions[optionNumber - 1] = selectedOption;
      // Check if both options are selected
      if (this.minMaxOptions[0] && this.minMaxOptions[1]) {
        // Add the "min" or "max" operation to the formula
        const operation = this.selectedOperation === "min" ? "min" : "max";
        this.formulaElements.push(operation);
        // Add the selected options to the formula
        this.formulaElements.push(...this.minMaxOptions);
        // Reset the selectedOperation and minMaxOptions variables
        this.selectedOperation = null;
        this.minMaxOptions = [];
        this.calculationLogicForm.get("operation")?.setValue("");
        this.calculationLogicForm.get("option1")?.setValue("");
        this.calculationLogicForm.get("option2")?.setValue("");
        this.updateFormula(); // Update the formula after adding "min" or "max" operation
      }
    }
  }
  

}
