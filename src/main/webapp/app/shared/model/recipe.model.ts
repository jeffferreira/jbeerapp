import { Moment } from 'moment';
import { IMash } from 'app/shared/model//mash.model';
import { IStyle } from 'app/shared/model//style.model';

export interface IRecipe {
    id?: number;
    name?: string;
    type?: string;
    brewer?: string;
    batchSize?: number;
    boilSize?: number;
    boilTime?: number;
    eficiency?: number;
    date?: Moment;
    ibu?: string;
    estAbv?: string;
    displayBatchSize?: string;
    displayOg?: string;
    displayFg?: string;
    mash?: IMash;
    style?: IStyle;
}

export class Recipe implements IRecipe {
    constructor(
        public id?: number,
        public name?: string,
        public type?: string,
        public brewer?: string,
        public batchSize?: number,
        public boilSize?: number,
        public boilTime?: number,
        public eficiency?: number,
        public date?: Moment,
        public ibu?: string,
        public estAbv?: string,
        public displayBatchSize?: string,
        public displayOg?: string,
        public displayFg?: string,
        public mash?: IMash,
        public style?: IStyle
    ) {}
}
