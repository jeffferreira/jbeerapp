/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { JbeerappTestModule } from '../../../test.module';
import { HopDeleteDialogComponent } from 'app/entities/hop/hop-delete-dialog.component';
import { HopService } from 'app/entities/hop/hop.service';

describe('Component Tests', () => {
    describe('Hop Management Delete Component', () => {
        let comp: HopDeleteDialogComponent;
        let fixture: ComponentFixture<HopDeleteDialogComponent>;
        let service: HopService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JbeerappTestModule],
                declarations: [HopDeleteDialogComponent]
            })
                .overrideTemplate(HopDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(HopDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(HopService);
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
