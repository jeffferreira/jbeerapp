import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { IRecipe } from 'app/shared/model/recipe.model';
import { RecipeService } from './recipe.service';
import { IMash } from 'app/shared/model/mash.model';
import { MashService } from 'app/entities/mash';
import { IStyle } from 'app/shared/model/style.model';
import { StyleService } from 'app/entities/style';

@Component({
    selector: 'jhi-recipe-update',
    templateUrl: './recipe-update.component.html'
})
export class RecipeUpdateComponent implements OnInit {
    recipe: IRecipe;
    isSaving: boolean;

    mashes: IMash[];

    styles: IStyle[];
    date: string;

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected recipeService: RecipeService,
        protected mashService: MashService,
        protected styleService: StyleService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ recipe }) => {
            this.recipe = recipe;
            this.date = this.recipe.date != null ? this.recipe.date.format(DATE_TIME_FORMAT) : null;
        });
        this.mashService.query({ filter: 'recipe-is-null' }).subscribe(
            (res: HttpResponse<IMash[]>) => {
                if (!this.recipe.mash || !this.recipe.mash.id) {
                    this.mashes = res.body;
                } else {
                    this.mashService.find(this.recipe.mash.id).subscribe(
                        (subRes: HttpResponse<IMash>) => {
                            this.mashes = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.styleService.query().subscribe(
            (res: HttpResponse<IStyle[]>) => {
                this.styles = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.recipe.date = this.date != null ? moment(this.date, DATE_TIME_FORMAT) : null;
        if (this.recipe.id !== undefined) {
            this.subscribeToSaveResponse(this.recipeService.update(this.recipe));
        } else {
            this.subscribeToSaveResponse(this.recipeService.create(this.recipe));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IRecipe>>) {
        result.subscribe((res: HttpResponse<IRecipe>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackStyleById(index: number, item: IStyle) {
        return item.id;
    }
}
