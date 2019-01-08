import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IMash } from 'app/shared/model/mash.model';
import { AccountService } from 'app/core';
import { MashService } from './mash.service';

@Component({
    selector: 'jhi-mash',
    templateUrl: './mash.component.html'
})
export class MashComponent implements OnInit, OnDestroy {
    mashes: IMash[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected mashService: MashService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.mashService.query().subscribe(
            (res: HttpResponse<IMash[]>) => {
                this.mashes = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInMashes();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IMash) {
        return item.id;
    }

    registerChangeInMashes() {
        this.eventSubscriber = this.eventManager.subscribe('mashListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
