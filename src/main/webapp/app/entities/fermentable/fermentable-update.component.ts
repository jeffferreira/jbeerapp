import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IFermentable } from 'app/shared/model/fermentable.model';
import { FermentableService } from './fermentable.service';
import { IRecipe } from 'app/shared/model/recipe.model';
import { RecipeService } from 'app/entities/recipe';

@Component({
    selector: 'jhi-fermentable-update',
    templateUrl: './fermentable-update.component.html'
})
export class FermentableUpdateComponent implements OnInit {
    fermentable: IFermentable;
    isSaving: boolean;

    recipes: IRecipe[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected fermentableService: FermentableService,
        protected recipeService: RecipeService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ fermentable }) => {
            this.fermentable = fermentable;
        });
        this.recipeService.query().subscribe(
            (res: HttpResponse<IRecipe[]>) => {
                this.recipes = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.fermentable.id !== undefined) {
            this.subscribeToSaveResponse(this.fermentableService.update(this.fermentable));
        } else {
            this.subscribeToSaveResponse(this.fermentableService.create(this.fermentable));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IFermentable>>) {
        result.subscribe((res: HttpResponse<IFermentable>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackRecipeById(index: number, item: IRecipe) {
        return item.id;
    }
}
