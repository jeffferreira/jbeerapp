/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JbeerappTestModule } from '../../../test.module';
import { MashComponent } from 'app/entities/mash/mash.component';
import { MashService } from 'app/entities/mash/mash.service';
import { Mash } from 'app/shared/model/mash.model';

describe('Component Tests', () => {
    describe('Mash Management Component', () => {
        let comp: MashComponent;
        let fixture: ComponentFixture<MashComponent>;
        let service: MashService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JbeerappTestModule],
                declarations: [MashComponent],
                providers: []
            })
                .overrideTemplate(MashComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(MashComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(MashService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new Mash(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.mashes[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
