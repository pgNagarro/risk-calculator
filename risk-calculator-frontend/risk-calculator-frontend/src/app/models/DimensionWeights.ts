import { DimensionWeight } from "./DimensionWeight";

export class DimensionWeights {

    dimensionWeights!: DimensionWeight[];

    constructor() {
        this.dimensionWeights = [];
      }
    
      addDimensions(dimensionWeight: DimensionWeight) {
        this.dimensionWeights.push(dimensionWeight);
      }
}