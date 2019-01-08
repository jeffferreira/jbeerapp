import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IHop } from 'app/shared/model/hop.model';
import { HopService } from './hop.service';
import { IRecipe } from 'app/shared/model/recipe.model';
import { RecipeService } from 'app/entities/recipe';

@Component({
    selector: 'jhi-hop-update',
    templateUrl: './hop-update.component.html'
})
export class HopUpdateComponent implements OnInit {
    hop: IHop;
    isSaving: boolean;

    recipes: IRecipe[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected hopService: HopService,
        protected recipeService: RecipeService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ hop }) => {
            this.hop = hop;
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
        if (this.hop.id !== undefined) {
            this.subscribeToSaveResponse(this.hopService.update(this.hop));
        } else {
            this.subscribeToSaveResponse(this.hopService.create(this.hop));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IHop>>) {
        result.subscribe((res: HttpResponse<IHop>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
