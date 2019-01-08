/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { JbeerappTestModule } from '../../../test.module';
import { FermentableDeleteDialogComponent } from 'app/entities/fermentable/fermentable-delete-dialog.component';
import { FermentableService } from 'app/entities/fermentable/fermentable.service';

describe('Component Tests', () => {
    describe('Fermentable Management Delete Component', () => {
        let comp: FermentableDeleteDialogComponent;
        let fixture: ComponentFixture<FermentableDeleteDialogComponent>;
        let service: FermentableService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JbeerappTestModule],
                declarations: [FermentableDeleteDialogComponent]
            })
                .overrideTemplate(FermentableDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(FermentableDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(FermentableService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete', inject(
                [],
                fakeAsync(() => {
                    // GIVEN
                    spyOn(service, 'delete').and.returnValue(of({}));

                    // WHEN
                    comp.confirmDelete(123);
                    tick();

                    // THEN
                    expect(service.delete).toHaveBeenCalledWith(123);
                    expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                })
            ));
        });
    });
});
