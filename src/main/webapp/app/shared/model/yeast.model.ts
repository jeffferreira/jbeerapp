import { IRecipe } from 'app/shared/model//recipe.model';

export interface IYeast {
    id?: number;
    name?: string;
    type?: string;
    laboratory?: string;
    productId?: string;
    yeast?: IRecipe;
}

export class Yeast implements IYeast {
    constructor(
        public id?: number,
        public name?: string,
        public type?: string,
        public laboratory?: string,
        public productId?: string,
        public yeast?: IRecipe
    ) {}
}
