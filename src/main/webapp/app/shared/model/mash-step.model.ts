import { IMash } from 'app/shared/model//mash.model';

export interface IMashStep {
    id?: number;
    name?: string;
    type?: string;
    stepTime?: number;
    stepTemp?: number;
    description?: string;
    infuseTemp?: string;
    displayStepTemp?: string;
    displayInfuseAmt?: string;
    mashStep?: IMash;
}

export class MashStep implements IMashStep {
    constructor(
        public id?: number,
        public name?: string,
        public type?: string,
        public stepTime?: number,
        public stepTemp?: number,
        public description?: string,
        public infuseTemp?: string,
        public displayStepTemp?: string,
        public displayInfuseAmt?: string,
        public mashStep?: IMash
    ) {}
}
