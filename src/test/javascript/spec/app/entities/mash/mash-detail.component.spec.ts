/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JbeerappTestModule } from '../../../test.module';
import { MashDetailComponent } from 'app/entities/mash/mash-detail.component';
import { Mash } from 'app/shared/model/mash.model';

describe('Component Tests', () => {
    describe('Mash Management Detail Component', () => {
        let comp: MashDetailComponent;
        let fixture: ComponentFixture<MashDetailComponent>;
        const route = ({ data: of({ mash: new Mash(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JbeerappTestModule],
                declarations: [MashDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(MashDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(MashDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.mash).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
