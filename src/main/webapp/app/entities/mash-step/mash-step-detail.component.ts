import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMashStep } from 'app/shared/model/mash-step.model';

@Component({
    selector: 'jhi-mash-step-detail',
    templateUrl: './mash-step-detail.component.html'
})
export class MashStepDetailComponent implements OnInit {
    mashStep: IMashStep;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ mashStep }) => {
            this.mashStep = mashStep;
        });
    }

    previousState() {
        window.history.back();
    }
}
