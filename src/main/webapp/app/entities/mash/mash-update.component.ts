import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IMash } from 'app/shared/model/mash.model';
import { MashService } from './mash.service';

@Component({
    selector: 'jhi-mash-update',
    templateUrl: './mash-update.component.html'
})
export class MashUpdateComponent implements OnInit {
    mash: IMash;
    isSaving: boolean;

    constructor(protected mashService: MashService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ mash }) => {
            this.mash = mash;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.mash.id !== undefined) {
            this.subscribeToSaveResponse(this.mashService.update(this.mash));
        } else {
            this.subscribeToSaveResponse(this.mashService.create(this.mash));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IMash>>) {
        result.subscribe((res: HttpResponse<IMash>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
