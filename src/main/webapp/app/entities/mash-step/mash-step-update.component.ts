import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IMashStep } from 'app/shared/model/mash-step.model';
import { MashStepService } from './mash-step.service';
import { IMash } from 'app/shared/model/mash.model';
import { MashService } from 'app/entities/mash';

@Component({
    selector: 'jhi-mash-step-update',
    templateUrl: './mash-step-update.component.html'
})
export class MashStepUpdateComponent implements OnInit {
    mashStep: IMashStep;
    isSaving: boolean;

    mashes: IMash[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected mashStepService: MashStepService,
        protected mashService: MashService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ mashStep }) => {
            this.mashStep = mashStep;
        });
        this.mashService.query().subscribe(
            (res: HttpResponse<IMash[]>) => {
                this.mashes = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.mashStep.id !== undefined) {
            this.subscribeToSaveResponse(this.mashStepService.update(this.mashStep));
        } else {
            this.subscribeToSaveResponse(this.mashStepService.create(this.mashStep));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IMashStep>>) {
        result.subscribe((res: HttpResponse<IMashStep>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackMashById(index: number, item: IMash) {
        return item.id;
    }
}
