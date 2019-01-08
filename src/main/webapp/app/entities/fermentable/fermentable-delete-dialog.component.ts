import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IFermentable } from 'app/shared/model/fermentable.model';
import { FermentableService } from './fermentable.service';

@Component({
    selector: 'jhi-fermentable-delete-dialog',
    templateUrl: './fermentable-delete-dialog.component.html'
})
export class FermentableDeleteDialogComponent {
    fermentable: IFermentable;

    constructor(
        protected fermentableService: FermentableService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.fermentableService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'fermentableListModification',
                content: 'Deleted an fermentable'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-fermentable-delete-popup',
    template: ''
})
export class FermentableDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ fermentable }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(FermentableDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.fermentable = fermentable;
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
