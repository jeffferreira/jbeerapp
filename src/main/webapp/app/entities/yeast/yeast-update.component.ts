import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IYeast } from 'app/shared/model/yeast.model';
import { YeastService } from './yeast.service';
import { IRecipe } from 'app/shared/model/recipe.model';
import { RecipeService } from 'app/entities/recipe';

@Component({
    selector: 'jhi-yeast-update',
    templateUrl: './yeast-update.component.html'
})
export class YeastUpdateComponent implements OnInit {
    yeast: IYeast;
    isSaving: boolean;

    recipes: IRecipe[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected yeastService: YeastService,
        protected recipeService: RecipeService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ yeast }) => {
            this.yeast = yeast;
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
        if (this.yeast.id !== undefined) {
            this.subscribeToSaveResponse(this.yeastService.update(this.yeast));
        } else {
            this.subscribeToSaveResponse(this.yeastService.create(this.yeast));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IYeast>>) {
        result.subscribe((res: HttpResponse<IYeast>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
