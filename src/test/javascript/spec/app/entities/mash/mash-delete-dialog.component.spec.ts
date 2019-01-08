/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { JbeerappTestModule } from '../../../test.module';
import { MashDeleteDialogComponent } from 'app/entities/mash/mash-delete-dialog.component';
import { MashService } from 'app/entities/mash/mash.service';

describe('Component Tests', () => {
    describe('Mash Management Delete Component', () => {
        let comp: MashDeleteDialogComponent;
        let fixture: ComponentFixture<MashDeleteDialogComponent>;
        let service: MashService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JbeerappTestModule],
                declarations: [MashDeleteDialogComponent]
            })
                .overrideTemplate(MashDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(MashDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(MashService);
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
