import { IRecipe } from 'app/shared/model//recipe.model';

export interface IFermentable {
    id?: number;
    name?: string;
    type?: string;
    amount?: number;
    origin?: string;
    supplier?: string;
    displayAmount?: string;
    fermentable?: IRecipe;
}

export class Fermentable implements IFermentable {
    constructor(
        public id?: number,
        public name?: string,
        public type?: string,
        public amount?: number,
        public origin?: string,
        public supplier?: string,
        public displayAmount?: string,
        public fermentable?: IRecipe
    ) {}
}
