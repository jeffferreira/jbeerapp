import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMashStep } from 'app/shared/model/mash-step.model';
import { MashStepService } from './mash-step.service';

@Component({
    selector: 'jhi-mash-step-delete-dialog',
    templateUrl: './mash-step-delete-dialog.component.html'
})
export class MashStepDeleteDialogComponent {
    mashStep: IMashStep;

    constructor(protected mashStepService: MashStepService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.mashStepService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'mashStepListModification',
                content: 'Deleted an mashStep'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-mash-step-delete-popup',
    template: ''
})
export class MashStepDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ mashStep }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(MashStepDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.mashStep = mashStep;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
