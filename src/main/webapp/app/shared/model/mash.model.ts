export interface IMash {
    id?: number;
    name?: string;
    ph?: number;
}

export class Mash implements IMash {
    constructor(public id?: number, public name?: string, public ph?: number) {}
}
