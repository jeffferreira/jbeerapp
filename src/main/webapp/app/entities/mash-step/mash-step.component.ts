import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IMashStep } from 'app/shared/model/mash-step.model';
import { AccountService } from 'app/core';
import { MashStepService } from './mash-step.service';

@Component({
    selector: 'jhi-mash-step',
    templateUrl: './mash-step.component.html'
})
export class MashStepComponent implements OnInit, OnDestroy {
    mashSteps: IMashStep[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected mashStepService: MashStepService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.mashStepService.query().subscribe(
            (res: HttpResponse<IMashStep[]>) => {
                this.mashSteps = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInMashSteps();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IMashStep) {
        return item.id;
    }

    registerChangeInMashSteps() {
        this.eventSubscriber = this.eventManager.subscribe('mashStepListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
