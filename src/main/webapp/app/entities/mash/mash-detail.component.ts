import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMash } from 'app/shared/model/mash.model';

@Component({
    selector: 'jhi-mash-detail',
    templateUrl: './mash-detail.component.html'
})
export class MashDetailComponent implements OnInit {
    mash: IMash;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ mash }) => {
            this.mash = mash;
        });
    }

    previousState() {
        window.history.back();
    }
}
