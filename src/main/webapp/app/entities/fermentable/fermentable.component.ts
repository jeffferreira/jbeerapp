import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IFermentable } from 'app/shared/model/fermentable.model';
import { AccountService } from 'app/core';
import { FermentableService } from './fermentable.service';

@Component({
    selector: 'jhi-fermentable',
    templateUrl: './fermentable.component.html'
})
export class FermentableComponent implements OnInit, OnDestroy {
    fermentables: IFermentable[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected fermentableService: FermentableService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.fermentableService.query().subscribe(
            (res: HttpResponse<IFermentable[]>) => {
                this.fermentables = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInFermentables();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IFermentable) {
        return item.id;
    }

    registerChangeInFermentables() {
        this.eventSubscriber = this.eventManager.subscribe('fermentableListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
