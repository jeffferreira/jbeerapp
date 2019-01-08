import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IFermentable } from 'app/shared/model/fermentable.model';

@Component({
    selector: 'jhi-fermentable-detail',
    templateUrl: './fermentable-detail.component.html'
})
export class FermentableDetailComponent implements OnInit {
    fermentable: IFermentable;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ fermentable }) => {
            this.fermentable = fermentable;
        });
    }

    previousState() {
        window.history.back();
    }
}
