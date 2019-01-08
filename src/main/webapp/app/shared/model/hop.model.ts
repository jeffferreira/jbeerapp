import { IRecipe } from 'app/shared/model//recipe.model';

export interface IHop {
    id?: number;
    name?: string;
    origin?: string;
    alpha?: number;
    amount?: number;
    use?: string;
    time?: number;
    displayTime?: string;
    displayAmount?: string;
    recipe?: IRecipe;
}

export class Hop implements IHop {
    constructor(
        public id?: number,
        public name?: string,
        public origin?: string,
        public alpha?: number,
        public amount?: number,
        public use?: string,
        public time?: number,
        public displayTime?: string,
        public displayAmount?: string,
        public recipe?: IRecipe
    ) {}
}
