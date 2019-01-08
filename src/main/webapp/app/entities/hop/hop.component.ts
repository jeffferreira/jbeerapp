import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IHop } from 'app/shared/model/hop.model';
import { AccountService } from 'app/core';
import { HopService } from './hop.service';

@Component({
    selector: 'jhi-hop',
    templateUrl: './hop.component.html'
})
export class HopComponent implements OnInit, OnDestroy {
    hops: IHop[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected hopService: HopService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.hopService.query().subscribe(
            (res: HttpResponse<IHop[]>) => {
                this.hops = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInHops();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IHop) {
        return item.id;
    }

    registerChangeInHops() {
        this.eventSubscriber = this.eventManager.subscribe('hopListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
