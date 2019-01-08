/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { JbeerappTestModule } from '../../../test.module';
import { MashStepDeleteDialogComponent } from 'app/entities/mash-step/mash-step-delete-dialog.component';
import { MashStepService } from 'app/entities/mash-step/mash-step.service';

describe('Component Tests', () => {
    describe('MashStep Management Delete Component', () => {
        let comp: MashStepDeleteDialogComponent;
        let fixture: ComponentFixture<MashStepDeleteDialogComponent>;
        let service: MashStepService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JbeerappTestModule],
                declarations: [MashStepDeleteDialogComponent]
            })
                .overrideTemplate(MashStepDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(MashStepDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(MashStepService);
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
