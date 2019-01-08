export interface IStyle {
    id?: number;
    name?: string;
    category?: string;
    type?: string;
}

export class Style implements IStyle {
    constructor(public id?: number, public name?: string, public category?: string, public type?: string) {}
}
