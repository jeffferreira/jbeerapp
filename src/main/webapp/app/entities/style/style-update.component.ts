import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IStyle } from 'app/shared/model/style.model';
import { StyleService } from './style.service';

@Component({
    selector: 'jhi-style-update',
    templateUrl: './style-update.component.html'
})
export class StyleUpdateComponent implements OnInit {
    style: IStyle;
    isSaving: boolean;

    constructor(protected styleService: StyleService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ style }) => {
            this.style = style;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.style.id !== undefined) {
            this.subscribeToSaveResponse(this.styleService.update(this.style));
        } else {
            this.subscribeToSaveResponse(this.styleService.create(this.style));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IStyle>>) {
        result.subscribe((res: HttpResponse<IStyle>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
