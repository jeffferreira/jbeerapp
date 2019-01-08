import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IHop } from 'app/shared/model/hop.model';

@Component({
    selector: 'jhi-hop-detail',
    templateUrl: './hop-detail.component.html'
})
export class HopDetailComponent implements OnInit {
    hop: IHop;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ hop }) => {
            this.hop = hop;
        });
    }

    previousState() {
        window.history.back();
    }
}
